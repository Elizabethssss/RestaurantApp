package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.injector.ApplicationInjector;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;
import com.restaurant.service.validator.CreditCardValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.restaurant.command.util.Util.*;

public class CreditCardCommand implements Command {
    private final OrderService orderService;
    private final CreditCardValidator creditCardValidator;
    private final Localization localization;

    public CreditCardCommand(OrderService orderService, Localization localization) {
        this.orderService = orderService;
        this.creditCardValidator = ApplicationInjector.getCreditCardValidator();
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final Long orderId = Long.valueOf(request.getParameter("id"));
        final Order order = orderService.getOrderById(orderId).orElse(Order.builder().build());

        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        setAttributes(request, orderId, order.getCost());
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/creditCard.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String cardNumber = request.getParameter("cardNumber").trim();
        final String expiredMonth = request.getParameter("expiredMonth").trim();
        final String expiredYear = request.getParameter("expiredYear").trim();
        final String cvv = request.getParameter("cvv");

        final Long orderId = Long.valueOf(request.getParameter("id"));
        final Order order = orderService.getOrderById(orderId).orElse(Order.builder().build());

        final HttpSession session = request.getSession();
        final ResourceBundle bundle = localization.getLocalizationBundle(request);
        request.setAttribute("bundle", bundle);

        Map<String, String> errorsMessages = new HashMap<>(creditCardValidator
                .validate(cardNumber, expiredMonth, expiredYear, cvv, bundle));

        if(errorsMessages.isEmpty()) {
            orderService.updateOrderStatus(orderId, OrderStatus.PAYED);
            request.setAttribute(RESPONSE_TYPE, SERVLET);
            return "/myOrders?page=1&lang=" + session.getAttribute("locale");
        }
        else {
            setErrorAttributes(request, cardNumber, expiredMonth, expiredYear, errorsMessages);
            setAttributes(request, orderId, order.getCost());
            request.setAttribute(RESPONSE_TYPE, JSP);
            return "pages/creditCard.jsp";
        }
    }

    private void setErrorAttributes(HttpServletRequest request, String cardNumber, String expiredMonth,
                                    String expiredYear, Map<String, String> errorsMessages) {
        request.setAttribute("cardNumber", cardNumber);
        request.setAttribute("expiredMonth", expiredMonth);
        request.setAttribute("expiredYear", expiredYear);
        request.setAttribute("errorsMessages", errorsMessages);
    }

    private void setAttributes(HttpServletRequest request, Long orderId, int total) {
        request.setAttribute("orderId", orderId);
        request.setAttribute("total", total);
    }
}
