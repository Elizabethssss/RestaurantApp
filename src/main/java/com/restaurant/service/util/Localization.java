package com.restaurant.service.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class Localization {

    public ResourceBundle getLocalizationBundle(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String lang = (String) session.getAttribute("locale");

        ResourceBundle bundle;
        if (lang.equals("ru")) {
            bundle = ResourceBundle.getBundle("locale/messages_ru", new UTF8Control());
        } else {
            bundle = ResourceBundle.getBundle("locale/messages_en", new UTF8Control());
        }
        return bundle;
    }
}