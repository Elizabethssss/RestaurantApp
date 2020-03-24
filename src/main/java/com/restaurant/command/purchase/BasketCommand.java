package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

public class BasketCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;

    public BasketCommand(OrderService orderService, DishService dishService) {
        this.orderService = orderService;
        this.dishService = dishService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        final long userId = user.getId();
//        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        final Optional<Order> order = orderService.getOrderByStatus(OrderStatus.FORMED);
        final Long orderId = order.get().getId();
        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(orderId);
        long totalPrice = 0;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            totalPrice += entry.getKey().getPrice();
        }

        request.setAttribute("dishes", dishes);
        request.setAttribute("totalPrice", totalPrice);
        return "pages/basket.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/basket.jsp";
    }
}
