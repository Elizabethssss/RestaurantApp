package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class BuyingCommand implements Command {
    final OrderService orderService;

    public BuyingCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String show(HttpServletRequest request) {
//        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        return "pages/dish.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
//        final ResourceBundle bundle = localization.getLocalizationBundle(request);

        final long dishId = Long.parseLong(request.getParameter("id"));
        Optional<Order> formingOrder = orderService.getOrderByStatus(OrderStatus.FORMED);
//        user.getOrders().add(formingOrder.get());
        final Long orderId = formingOrder.get().getId();
        orderService.addDishToOrder(orderId, dishId);

        int inBasket = (int) session.getAttribute("inBasket");
        final String message = "Dish has been added to basket!";
        inBasket++;

        session.setAttribute("inBasket", inBasket);
        session.setAttribute("user", user);
        session.setAttribute("message", message);

        return "/dish?id=" + dishId;
//        return "/exhibition?id=" + exhibitionId + "&page=" + page + "&lang=" + session.getAttribute("locale");
    }
}
