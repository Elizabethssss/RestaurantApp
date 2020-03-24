package com.restaurant.command.user;

import com.restaurant.command.Command;
import com.restaurant.domain.Role;
import com.restaurant.domain.User;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignUpCommand implements Command {
    private final UserService userService;
    private final OrderService orderService;
//    private final Localization localization;

    public SignUpCommand(UserService userService, OrderService orderService) {
//    public SignUpCommand(UserService userService, Localization localization) {
        this.userService = userService;
        this.orderService = orderService;
//        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        //request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute("rightSide", true);
        return "pages/authorization.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String username = request.getParameter("username").trim();
        final String email = request.getParameter("email").trim();
        final String password1 = request.getParameter("password1");
        final String password2 = request.getParameter("password2");
        final User user = User.builder()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password1)
                .withRole(Role.USER)
                .withOrders(new ArrayList<>())
                .build();

        Map<String, String> errorsMessages = new HashMap<>();
        if(!password1.equals(password2)) {
            errorsMessages.put("passwordError", "Passwords are not equal!");
        } else {
            errorsMessages.putAll(userService.register(user));
        }
        if(errorsMessages.isEmpty()) {
            final HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("inBasket", 0);
            return "/index";
        }
        else {
            setErrorAttributes(request, username, email, errorsMessages);
            return "pages/authorization.jsp";
        }
    }

    private void setErrorAttributes(HttpServletRequest request, String username,
                                    String email, Map<String, String> errorsMessages) {
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.setAttribute("errorsMessages", errorsMessages);
        request.setAttribute("rightSide", true);
    }

}
