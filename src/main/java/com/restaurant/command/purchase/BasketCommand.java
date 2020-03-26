package com.restaurant.command.purchase;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.OrderService;
import org.json.JSONObject;

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

        final Optional<Order> order = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, userId);
        final Long orderId = order.get().getId();
        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(orderId);
        long totalPrice = 0;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            totalPrice += entry.getKey().getPrice();
        }

        request.setAttribute("dishes", dishes);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("responseType", "jsp");
        return "pages/basket.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final JSONObject jsonObject = new JSONObject(request.getParameter("data"));
        final String dishAction = jsonObject.getString("dishAction");
        final Long dishId = jsonObject.getLong("dishId");

        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        int inBasket = (int) session.getAttribute("inBasket");
        final Optional<Order> order = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
        final Long orderId = order.get().getId();
        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(orderId);

        double numOfItems = 0;
        double priceOfItems = 0;
        double totalPrice = 0;

        if(dishAction.equals("plus")) {
            for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
                if(entry.getKey().getId().equals(dishId)) {
                    dishes.replace(entry.getKey(), entry.getValue() + 1);
                    orderService.addDishToOrder(orderId, dishId);
                    numOfItems = entry.getValue();
                    priceOfItems = entry.getKey().getPrice() * entry.getValue();
                    inBasket++;
                }
            }
        }
        else if(dishAction.equals("minus")) {
            for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
                if(entry.getKey().getId().equals(dishId) && entry.getValue() > 1) {
                    dishes.replace(entry.getKey(), entry.getValue() - 1);
                    orderService.deleteOrderDishById(orderId, dishId, 1);
                    numOfItems = entry.getValue();
                    priceOfItems = entry.getKey().getPrice() * entry.getValue();
                    inBasket--;
                }
                else if(entry.getKey().getId().equals(dishId) && entry.getValue() == 1) {
                    numOfItems = 1;
                    priceOfItems = entry.getKey().getPrice();
                }
            }
        }
        else if(dishAction.equals("remove")) {
            for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
                if(entry.getKey().getId().equals(dishId)) {
                    inBasket -= entry.getValue();
                    orderService.deleteOrderDishById(orderId, dishId, entry.getValue());
                    dishes.replace(entry.getKey(), 0);
                }
            }
        }

        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                totalPrice += entry.getKey().getPrice();
            }
        }

        JSONObject json = new JSONObject();
        json.put("numOfItems", numOfItems);
        json.put("priceOfItems", priceOfItems);
        json.put("totalPrice", totalPrice);
        json.put("totalDishes", inBasket);

        String answer = json.toString();

        session.setAttribute("inBasket", inBasket);
        request.setAttribute("responseType", "json");
        return answer;
    }
}
