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
import com.restaurant.service.util.Localization;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static com.restaurant.command.util.Util.*;

public class BasketCommand implements Command {
    private final OrderService orderService;
    private final DishService dishService;
    private final LunchService lunchService;
    private final Localization localization;
    private double numOfItems = 0;
    private double priceOfItems = 0;

    public BasketCommand(OrderService orderService, DishService dishService,
                         LunchService lunchService, Localization localization) {
        this.orderService = orderService;
        this.dishService = dishService;
        this.lunchService = lunchService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        final long userId = user.getId();
        double totalPrice = 0;

        final Optional<Order> order = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, userId);
        final Long orderId = order.get().getId();
        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(orderId);
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        Map<Lunch, Integer> lunchIntegerMap = lunchService.getLunchesByOrderId(orderId);
        for (Map.Entry<Lunch, Integer> entry : lunchIntegerMap.entrySet()) {
            entry.getKey().getDishes().addAll(dishService.getDishesByLunchId(entry.getKey().getId()));
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        setShowAttributes(request, dishes, totalPrice, lunchIntegerMap);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/basket.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final JSONObject jsonObject = new JSONObject(request.getParameter("data"));
        final String action = jsonObject.getString("action");
        final String type = jsonObject.getString("type");
        final Long id = jsonObject.getLong("id");

        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        int inBasket = (int) session.getAttribute("inBasket");
        final Optional<Order> order = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
        final Long orderId = order.get().getId();

        final Map<Dish, Integer> dishes = dishService.getDishesByOrderId(orderId);
        Map<Lunch, Integer> lunches = lunchService.getLunchesByOrderId(orderId);
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            entry.getKey().getDishes().addAll(dishService.getDishesByLunchId(entry.getKey().getId()));
        }

        if(type.equals("dish")) {
            inBasket = actionWithDishes(action, id, inBasket, orderId, dishes);
        }
        if (type.equals("lunch")) {
            inBasket = actionWithLunches(action, id, inBasket, orderId, lunches);
        }

        double totalPrice = 0;
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        String answer = getJsonObject(inBasket, numOfItems, priceOfItems, totalPrice).toString();

        session.setAttribute("inBasket", inBasket);
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSON_OBJECT);
        return answer;
    }

    private int actionWithDishes(String action, Long id, int inBasket, Long orderId, Map<Dish, Integer> dishes) {
        switch (action) {
            case "plus":
                inBasket = plusDish(id, inBasket, orderId, dishes);
                break;
            case "minus":
                inBasket = minusDish(id, inBasket, orderId, dishes);
                break;
            case "remove":
                inBasket = removeDish(id, inBasket, orderId, dishes);
                break;
            default: break;
        }
        return inBasket;
    }

    private int actionWithLunches(String action, Long id, int inBasket, Long orderId, Map<Lunch, Integer> lunches) {
        switch (action) {
            case "plus":
                inBasket = plusLunch(id, inBasket, orderId, lunches);
                break;
            case "minus":
                inBasket = minusLunch(id, inBasket, orderId, lunches);
                break;
            case "remove":
                inBasket = removeLunch(id, inBasket, orderId, lunches);
                break;
            default: break;
        }
        return inBasket;
    }

    private int plusDish(Long id, int inBasket, Long orderId, Map<Dish, Integer> dishes) {
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                dishes.replace(entry.getKey(), entry.getValue() + 1);
                orderService.addDishToOrder(orderId, id);
                numOfItems = entry.getValue();
                priceOfItems = entry.getKey().getPrice() * entry.getValue();
                inBasket++;
            }
        }
        return inBasket;
    }

    private int minusDish(Long id, int inBasket, Long orderId, Map<Dish, Integer> dishes) {
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            if (entry.getKey().getId().equals(id) && entry.getValue() > 1) {
                dishes.replace(entry.getKey(), entry.getValue() - 1);
                orderService.deleteOrderDishById(orderId, id, 1);
                numOfItems = entry.getValue();
                priceOfItems = entry.getKey().getPrice() * entry.getValue();
                inBasket--;
            } else if (entry.getKey().getId().equals(id) && entry.getValue() == 1) {
                numOfItems = 1;
                priceOfItems = entry.getKey().getPrice();
            }
        }
        return inBasket;
    }

    private int removeDish(Long id, int inBasket, Long orderId, Map<Dish, Integer> dishes) {
        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                inBasket -= entry.getValue();
                orderService.deleteOrderDishById(orderId, id, entry.getValue());
                dishes.replace(entry.getKey(), 0);
            }
        }
        priceOfItems = 0;
        numOfItems = 0;
        return inBasket;
    }

    private int plusLunch(Long id, int inBasket, Long orderId, Map<Lunch, Integer> lunches) {
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                lunches.replace(entry.getKey(), entry.getValue() + 1);
                orderService.addLunchToOrder(orderId, id);
                numOfItems = entry.getValue();
                priceOfItems = entry.getKey().getPrice() * entry.getValue();
                inBasket++;
            }
        }
        return inBasket;
    }

    private int minusLunch(Long id, int inBasket, Long orderId, Map<Lunch, Integer> lunches) {
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            if (entry.getKey().getId().equals(id) && entry.getValue() > 1) {
                lunches.replace(entry.getKey(), entry.getValue() - 1);
                orderService.deleteOrderLunchById(orderId, id, 1);
                numOfItems = entry.getValue();
                priceOfItems = entry.getKey().getPrice() * entry.getValue();
                inBasket--;
            } else if (entry.getKey().getId().equals(id) && entry.getValue() == 1) {
                numOfItems = 1;
                priceOfItems = entry.getKey().getPrice();
            }
        }
        return inBasket;
    }

    private int removeLunch(Long id, int inBasket, Long orderId, Map<Lunch, Integer> lunches) {
        for (Map.Entry<Lunch, Integer> entry : lunches.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                inBasket -= entry.getValue();
                orderService.deleteOrderLunchById(orderId, id, entry.getValue());
                lunches.replace(entry.getKey(), 0);
            }
        }
        priceOfItems = 0;
        numOfItems = 0;
        return inBasket;
    }

    private JSONObject getJsonObject(int inBasket, double numOfItems, double priceOfItems, double totalPrice) {
        JSONObject json = new JSONObject();
        json.put("numOfItems", numOfItems);
        json.put("priceOfItems", priceOfItems);
        json.put("totalPrice", totalPrice);
        json.put("totalDishes", inBasket);
        return json;
    }

    private void setShowAttributes(HttpServletRequest request, Map<Dish, Integer> dishes,
                                   double totalPrice, Map<Lunch, Integer> lunchIntegerMap) {
        request.setAttribute("dishes", dishes);
        request.setAttribute("lunchIntegerMap", lunchIntegerMap);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
    }
}
