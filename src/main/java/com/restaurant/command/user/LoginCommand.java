package com.restaurant.command.user;

import com.restaurant.command.Command;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;
    private final OrderService orderService;
//    private final Localization localization;

    public LoginCommand(UserService userService, OrderService orderService) {
//    public LoginCommand(UserService userService, Localization localization) {
        this.userService = userService;
        this.orderService = orderService;
//        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
//        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/authorization.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email").trim();
        final String password = DigestUtils.md5Hex(request.getParameter("password"));

//        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        Optional<User> checkUser = userService.login(email, password);

        if (!checkUser.isPresent()) {
            request.setAttribute("email", email);
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "Wrong email or password!");
            return "pages/authorization.jsp";
        } else {
            setUserToSession(request, checkUser);
            return "/index";
//            return "/index?lang=" + session.getAttribute("locale");
        }
    }

    private void setUserToSession(HttpServletRequest request, Optional<User> checkUser) {
        final HttpSession session = request.getSession();
        final Long userId = checkUser.get().getId();
        final List<Order> orders = orderService.getOrdersByUserId(userId);
        User user = User.builder()
                .withId(userId)
                .withUsername(checkUser.get().getUsername())
                .withEmail(checkUser.get().getEmail())
                .withPassword(checkUser.get().getPassword())
                .withRole(checkUser.get().getRole())
                .withOrders(orders)
                .build();
        session.setAttribute("user", user);
    }
}
