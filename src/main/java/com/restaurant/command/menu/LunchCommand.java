package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.restaurant.command.util.Util.*;

public class LunchCommand implements Command {
    private final LunchService lunchService;
    private final OrderService orderService;
    private final DishService dishService;
    private final Localization localization;

    public LunchCommand(LunchService lunchService, OrderService orderService,
                        DishService dishService, Localization localization) {
        this.lunchService = lunchService;
        this.orderService = orderService;
        this.dishService = dishService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Long lunchId = Long.valueOf(request.getParameter("id"));
        final Lunch lunch = lunchService.getLunchById(lunchId).orElse(Lunch.builder().build());
        final String message = (String) session.getAttribute(MESSAGE);
        session.removeAttribute(MESSAGE);

        lunch.getDishes().addAll(dishService.getDishesByLunchId(lunch.getId()));
        int price = lunch.getPrice();
        String weight = lunch.getWeight();
        if(lunch.getTimeFrom().compareTo(LocalTime.now()) > 0 ||
                lunch.getTimeTo().compareTo(LocalTime.now()) < 0) {
            request.setAttribute("disabled", true);
        }

        setShowAttributes(request, lunch, message, price, weight);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/lunch.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        int inBasket = (int) session.getAttribute("inBasket");
        final ResourceBundle bundle = localization.getLocalizationBundle(request);

        String message = bundle.getString("wrong.time");

        final long lunchId = Long.parseLong(request.getParameter("id"));
        final Optional<Lunch> lunch = lunchService.getLunchById(lunchId);
        if(lunch.isPresent() && lunch.get().getTimeFrom().compareTo(LocalTime.now()) < 0 &&
                    lunch.get().getTimeTo().compareTo(LocalTime.now()) > 0) {
                Optional<Order> formingOrder = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
                if(formingOrder.isPresent()) {
                    final Long orderId = formingOrder.get().getId();
                    orderService.addLunchToOrder(orderId, lunchId);
                }

                inBasket++;
                message = bundle.getString("added.lunch");
            }

        setExecuteAttributes(request, session, user, inBasket, bundle, message);
        request.setAttribute(RESPONSE_TYPE, SERVLET);
        return "/lunch?id=" + lunchId + "&lang=" + session.getAttribute("locale");
    }

    private void setShowAttributes(HttpServletRequest request, Lunch lunch, String message, int price, String weight) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute("price", price);
        request.setAttribute("weight", weight);
        request.setAttribute("lunch", lunch);
        request.setAttribute(MESSAGE, message);
    }

    private void setExecuteAttributes(HttpServletRequest request, HttpSession session, User user, int inBasket, ResourceBundle bundle, String message) {
        session.setAttribute("inBasket", inBasket);
        session.setAttribute("user", user);
        session.setAttribute(MESSAGE, message);
        request.setAttribute("bundle", bundle);
    }
}
