package com.restaurant.command.user;

import com.restaurant.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.restaurant.command.util.Util.*;

public class LogoutCommand implements Command {
    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();

        request.setAttribute(RESPONSE_TYPE, SERVLET);
        return "/login?lang=en";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/login.jsp";
    }
}
