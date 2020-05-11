package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;

import static com.restaurant.command.util.Util.*;

public class PlaceOrderCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;
    private final LunchService lunchService;

    public PlaceOrderCommand(OrderService orderService, DishService dishService, LunchService lunchService) {
        this.orderService = orderService;
        this.dishService = dishService;
        this.lunchService = lunchService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        final Order order = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId())
                .orElse(Order.builder().build());

        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(order.getId());
        final Map<Lunch, Integer> lunches = lunchService.getLunchesByOrderId(order.getId());
        int totalPrice = 0;

        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            entry.getKey().getDishes().addAll(dishService.getDishesByLunchId(entry.getKey().getId()));
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        orderService.updateOrderCostAndStatus(totalPrice, order.getId());
        Order newOrder = Order.builder()
                .withStatus(OrderStatus.FORMED)
                .withCost(0)
                .withCreatedAt(LocalDate.now())
                .withUser(user)
                .build();
        orderService.addOrder(newOrder);

        session.setAttribute("inBasket", 0);
        request.setAttribute(RESPONSE_TYPE, SERVLET);
        return "/myOrders?page=1&lang=" + session.getAttribute("locale");
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/basket.jsp";
    }
}
