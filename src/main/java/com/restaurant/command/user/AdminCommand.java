package com.restaurant.command.user;

import com.restaurant.command.Command;
import com.restaurant.dao.Page;
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
import java.util.List;
import java.util.Map;

import static com.restaurant.command.util.Util.*;

public class AdminCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;
    private final LunchService lunchService;
    private final Localization localization;

    public AdminCommand(OrderService orderService, DishService dishService, LunchService lunchService, Localization localization) {
        this.orderService = orderService;
        this.dishService = dishService;
        this.lunchService = lunchService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        if (!user.getRole().name().equals("ADMIN")) {
            request.setAttribute(RESPONSE_TYPE, SERVLET);
            return "/index&lang=" + session.getAttribute(LOCALE);
        }

        final int numberOfOrders = orderService.countOrdersByStatus(OrderStatus.SENT);
        request.setAttribute("numberOfOrders", numberOfOrders);

        int pageNumber = Integer.parseInt(request.getParameter("page"));
        int totalPages = (int) (numberOfOrders / 10.) + 1;
        if (pageNumber < 1 || pageNumber > totalPages) {
            request.setAttribute(RESPONSE_TYPE, SERVLET);
            return "/admin?page=1&lang=" + session.getAttribute(LOCALE);
        }
        final Page page = new Page((pageNumber - 1) * 10, 10);

        final List<Order> orders = orderService.getOrdersByStatus(OrderStatus.SENT, page);
        parseOrderWithDishesAndLunches(orders);

        setAttributes(request, pageNumber, totalPages, orders);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/admin.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Long orderId = Long.valueOf(request.getParameter("id"));
        orderService.updateOrderStatus(orderId, OrderStatus.CONFIRMED);

        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, SERVLET);
        return "/admin?page=1&lang=" + session.getAttribute(LOCALE);
    }

    private void parseOrderWithDishesAndLunches(List<Order> orders) {
        for (Order order : orders) {
            order.getDishes().putAll(dishService.getDishesByOrderId(order.getId()));
            order.getLunches().putAll(lunchService.getLunchesByOrderId(order.getId()));
            for (Map.Entry<Lunch, Integer> entry : order.getLunches().entrySet()) {
                entry.getKey().getDishes().addAll(dishService.getDishesByLunchId(entry.getKey().getId()));
            }
        }
    }

    private void setAttributes(HttpServletRequest request, int pageNumber, int totalPages, List<Order> orders) {
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("orders", orders);
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute("aActive", true);
    }
}
