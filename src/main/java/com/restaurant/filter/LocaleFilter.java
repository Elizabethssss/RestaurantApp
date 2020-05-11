package com.restaurant.filter;

import com.restaurant.injector.ApplicationInjector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();
        String locale = req.getParameter("lang");
        if (!Arrays.asList(ApplicationInjector.LANGUAGES).contains(locale)) {
            locale = ApplicationInjector.LANGUAGE_DEFAULT;
        }
        session.setAttribute("locale", locale);
        chain.doFilter(req, res);

    }
}
