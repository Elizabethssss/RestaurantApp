package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Ingredient;
import com.restaurant.service.DishService;
import com.restaurant.service.IngredientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class DishCommand implements Command {
    private final DishService dishService;
    private final IngredientService ingredientService;

    public DishCommand(DishService dishService, IngredientService ingredientService) {
        this.dishService = dishService;
        this.ingredientService = ingredientService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Long dishId = Long.valueOf(request.getParameter("id"));
        final Optional<Dish> dish = dishService.getDishById(dishId);
        final List<Ingredient> ingredients = ingredientService.findIngredientsByDishId(dishId);
        final String message = (String) session.getAttribute("message");
        session.removeAttribute("message");

        request.setAttribute("dish", dish.get());
        request.setAttribute("ingredients", ingredients);
        request.setAttribute("message", message);
        return "pages/dish.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/dish.jsp";
    }
}
