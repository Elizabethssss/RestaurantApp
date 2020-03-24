package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveDishCommand implements Command {
    private final OrderService orderService;

    public RemoveDishCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String show(HttpServletRequest request) {
        HttpSession session = request.getSession();
        final long orderDishId = Long.parseLong(request.getParameter("id"));
        int inBasket = (int) session.getAttribute("inBasket");
//        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        orderService.deleteOrderDishById(orderDishId);
        inBasket--;

//        request.setAttribute("inBasket", inBasket);
        session.setAttribute("inBasket", inBasket);
        return "pages/basket.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/basket.jsp";
    }
}
