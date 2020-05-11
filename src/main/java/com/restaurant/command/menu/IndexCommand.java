package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
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
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.restaurant.command.util.Util.JSP;
import static com.restaurant.command.util.Util.RESPONSE_TYPE;

public class IndexCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;
    private final LunchService lunchService;
    private final Localization localization;

    public IndexCommand(OrderService orderService, DishService dishService, LunchService lunchService, Localization localization) {
        this.orderService = orderService;
        this.dishService = dishService;
        this.lunchService = lunchService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        final ResourceBundle bundle = localization.getLocalizationBundle(request);
        request.setAttribute("bundle", bundle);
        int inBasket = 0;

        Optional<Order> formingOrder = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
        if(!formingOrder.isPresent()) {
            Order order = Order.builder()
                    .withStatus(OrderStatus.FORMED)
                    .withCost(0)
                    .withCreatedAt(LocalDate.now())
                    .withUser(user)
                    .build();
            orderService.addOrder(order);
        }
        else {
            Map<Dish, Integer> dishIntegerMap = dishService.getDishesByOrderId(formingOrder.get().getId());
            for (Map.Entry<Dish, Integer> entry : dishIntegerMap.entrySet()) {
                inBasket += entry.getValue();
            }
            Map<Lunch, Integer> lunchIntegerMap = lunchService.getLunchesByOrderId(formingOrder.get().getId());
            for (Map.Entry<Lunch, Integer> entry : lunchIntegerMap.entrySet()) {
                inBasket += entry.getValue();
            }
        }

        session.setAttribute("inBasket", inBasket);
        request.setAttribute("hActive", true);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/index.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/index.jsp";
    }
}
