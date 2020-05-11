package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.dao.Page;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.restaurant.command.util.Util.JSP;
import static com.restaurant.command.util.Util.RESPONSE_TYPE;

public class MyOrdersCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;
    private final LunchService lunchService;
    private final Localization localization;

    public MyOrdersCommand(OrderService orderService, DishService dishService, LunchService lunchService, Localization localization) {
        this.orderService = orderService;
        this.dishService = dishService;
        this.lunchService = lunchService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        final int numberOfOrders = orderService.count(user.getId());
        request.setAttribute("numberOfOrders", numberOfOrders);

        int pageNumber = Integer.parseInt(request.getParameter("page"));
        int totalPages = (int) (numberOfOrders/7.) + 1;
        if(pageNumber < 1 || pageNumber > totalPages) {
            request.setAttribute("responseType", "servlet");
            return "/myOrders?page=1&lang=" + session.getAttribute("locale");
        }
        final Page page = new Page( (pageNumber - 1) * 7, 7 );

        final List<Order> orders = orderService.getOrdersExceptFormed(user.getId(), page);
        parseOrderWithDishesAndLunches(orders);

        setAttributes(request, pageNumber, totalPages, orders);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/myOrders.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/myOrders.jsp";
    }

    private void parseOrderWithDishesAndLunches(List<Order> orders) {
        for (Order order : orders) {
            order.getLunches().putAll(lunchService.getLunchesByOrderId(order.getId()));
            order.getDishes().putAll(dishService.getDishesByOrderId(order.getId()));
            for (Map.Entry<Lunch, Integer> entry : order.getLunches().entrySet()) {
                entry.getKey().getDishes().addAll(dishService.getDishesByLunchId(entry.getKey().getId()));
            }
        }
    }

    private void setAttributes(HttpServletRequest request, int pageNumber, int totalPages, List<Order> orders) {
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("orders", orders);
        request.setAttribute("oActive", true);
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
    }
}
