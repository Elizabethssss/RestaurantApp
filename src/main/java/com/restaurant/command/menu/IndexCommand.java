package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class IndexCommand implements Command {
    final OrderService orderService;
    final DishService dishService;

    public IndexCommand(OrderService orderService, DishService dishService) {
        this.orderService = orderService;
        this.dishService = dishService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        int inBasket = 0;
//        final ResourceBundle bundle = localization.getLocalizationBundle(request);
//        request.setAttribute("bundle", bundle);

        Optional<Order> formingOrder = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
        if(!formingOrder.isPresent()) {
            Order order = Order.builder()
                    .withStatus(OrderStatus.FORMED)
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
        }

        session.setAttribute("inBasket", inBasket);
        request.setAttribute("hActive", true);
        request.setAttribute("responseType", "jsp");
        return "pages/index.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
//        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        request.setAttribute("responseType", "jsp");
        return "pages/index.jsp";
    }
}
