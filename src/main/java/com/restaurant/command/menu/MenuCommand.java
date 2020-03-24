package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.dao.Page;
import com.restaurant.domain.Dish;
import com.restaurant.service.DishService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MenuCommand implements Command {
    private final DishService dishService;

    public MenuCommand(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    public String show(HttpServletRequest request) {
        final String dishType = request.getParameter("type");
        final int numberOfDishes = dishService.count(dishType);
        request.setAttribute("numberOfDishes", numberOfDishes);

        int pageNumber = Integer.parseInt(request.getParameter("page"));
        int totalPages = (int) (numberOfDishes/9.) + 1;
        if(pageNumber < 1 || pageNumber > totalPages) {
            pageNumber = 1;
        }
        final Page page = new Page( (pageNumber - 1) * 9, 9 );

        List<Dish> dishes = dishService.getDishesByType(dishType, page);

        request.setAttribute("dishes", dishes);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("dishType", dishType);
        request.setAttribute("mActive", true);
        return "pages/menu.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return "pages/menu.jsp";
    }
}
