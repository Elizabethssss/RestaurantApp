package com.restaurant.command.user;

import com.restaurant.command.Command;
import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import com.restaurant.service.util.Localization;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.restaurant.command.util.Util.*;

public class LoginCommand implements Command {
    private final UserService userService;
    private final OrderService orderService;
    private final Localization localization;

    public LoginCommand(UserService userService, OrderService orderService, Localization localization) {
        this.userService = userService;
        this.orderService = orderService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/authorization.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email").trim();
        final String password = DigestUtils.md5Hex(request.getParameter("password"));

        final ResourceBundle bundle = localization.getLocalizationBundle(request);
        request.setAttribute("bundle", bundle);

        Optional<User> checkUser = userService.login(email, password);

        if (!checkUser.isPresent()) {
            setErrorAttributes(request, email, bundle);
            request.setAttribute(RESPONSE_TYPE, JSP);
            return "pages/authorization.jsp";
        } else {
            final HttpSession session = request.getSession();
            setUserToSession(session, checkUser.get());
            request.setAttribute(RESPONSE_TYPE, SERVLET);
            return "/index?lang=" + session.getAttribute("locale");
        }
    }

    private void setUserToSession(HttpSession session, User checkUser) {
        final Long userId = checkUser.getId();
        final List<Order> orders = orderService.getOrdersByUserId(userId);
        User user = User.builder()
                .withId(userId)
                .withUsername(checkUser.getUsername())
                .withEmail(checkUser.getEmail())
                .withPassword(checkUser.getPassword())
                .withRole(checkUser.getRole())
                .withOrders(orders)
                .build();
        session.setAttribute("user", user);
    }

    private void setErrorAttributes(HttpServletRequest request, String email, ResourceBundle bundle) {
        request.setAttribute("email", email);
        request.setAttribute("error", true);
        request.setAttribute("errorMessage", bundle.getString("wrong.login"));
    }
}
