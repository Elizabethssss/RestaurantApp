package com.restaurant.command.user;

import com.restaurant.command.Command;
import com.restaurant.service.util.Localization;

import javax.servlet.http.HttpServletRequest;

import static com.restaurant.command.util.Util.JSP;
import static com.restaurant.command.util.Util.RESPONSE_TYPE;

public class ErrorCommand implements Command {
    private final Localization localization;

    public ErrorCommand(Localization localization) {
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/errors/error.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/errors/error.jsp";
    }
}
