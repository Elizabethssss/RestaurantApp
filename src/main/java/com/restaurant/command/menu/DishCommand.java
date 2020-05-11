package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Ingredient;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.IngredientService;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.restaurant.command.util.Util.*;

public class DishCommand implements Command {

    private final DishService dishService;
    private final IngredientService ingredientService;
    private final OrderService orderService;
    private final Localization localization;

    public DishCommand(DishService dishService, IngredientService ingredientService,
                       OrderService orderService, Localization localization) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.orderService = orderService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Long dishId = Long.valueOf(request.getParameter("id"));
        final Optional<Dish> dish = dishService.getDishById(dishId);
        final List<Ingredient> ingredients = ingredientService.findIngredientsByDishId(dishId);
        final String message = (String) session.getAttribute(MESSAGE);
        session.removeAttribute(MESSAGE);

        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute("dish", dish.orElse(Dish.builder().build()));
        request.setAttribute("ingredients", ingredients);
        request.setAttribute(MESSAGE, message);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/dish.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");
        final ResourceBundle bundle = localization.getLocalizationBundle(request);

        final long dishId = Long.parseLong(request.getParameter("id"));
        Optional<Order> formingOrder = orderService.getOrderByStatusAndUserId(OrderStatus.FORMED, user.getId());
        if (formingOrder.isPresent()) {
            final Long orderId = formingOrder.get().getId();
            orderService.addDishToOrder(orderId, dishId);
        }

        int inBasket = (int) session.getAttribute("inBasket");
        final String message = bundle.getString("added.dish");
        inBasket++;

        session.setAttribute("inBasket", inBasket);
        session.setAttribute("user", user);
        session.setAttribute(MESSAGE, message);

        request.setAttribute("bundle", bundle);
        request.setAttribute(RESPONSE_TYPE, SERVLET);
        return "/dish?id=" + dishId + "&lang=" + session.getAttribute("locale");
    }
}
