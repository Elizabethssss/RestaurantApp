package com.restaurant.command.menu;

import com.restaurant.command.Command;
import com.restaurant.dao.Page;
import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.util.Localization;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.restaurant.command.util.Util.*;

public class MenuCommand implements Command {
    private final DishService dishService;
    private final LunchService lunchService;
    private final Localization localization;

    public MenuCommand(DishService dishService, LunchService lunchService, Localization localization) {
        this.dishService = dishService;
        this.lunchService = lunchService;
        this.localization = localization;
    }

    @Override
    public String show(HttpServletRequest request) {
        final String dishType = request.getParameter("type");
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        final Page page = new Page((pageNumber - 1) * 9, 9);
        request.setAttribute("bundle", localization.getLocalizationBundle(request));

        if (dishType.equals("BREAKFAST") || dishType.equals("LUNCH") || dishType.equals("HOLIDAY")) {
            final int numberOfLunches = lunchService.count(dishType);
            request.setAttribute("numberOfLunches", numberOfLunches);

            int totalPages = (int) (numberOfLunches / 9.) + (numberOfLunches % 9 == 0 ? 0 : 1);
            if (pageNumber < 1 || pageNumber > totalPages) {
                request.setAttribute(RESPONSE_TYPE, SERVLET);
                return "/menu?type=" + dishType + "&page=1";
            }
            parseLunches(request, dishType, page, totalPages);
        }
        else {
            final int numberOfDishes = dishService.count(dishType);
            request.setAttribute("numberOfDishes", numberOfDishes);

            int totalPages = (int) (numberOfDishes / 9.) + (numberOfDishes % 9 == 0 ? 0 : 1);
            if (pageNumber < 1 || pageNumber > totalPages) {
                request.setAttribute(RESPONSE_TYPE, SERVLET);
                return "/menu?type=" + dishType + "&page=1";
            }
            parseDishes(request, dishType, page, totalPages);
        }

        setAttributes(request, dishType, pageNumber);
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/menu.jsp";
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("bundle", localization.getLocalizationBundle(request));
        request.setAttribute(RESPONSE_TYPE, JSP);
        return "pages/menu.jsp";
    }

    private void parseDishes(HttpServletRequest request, String dishType, Page page, int totalPages) {
        List<Dish> dishes = dishService.getDishesByType(dishType, page);
        request.setAttribute("dishes", dishes);
        request.setAttribute("totalPages", totalPages);
    }

    private void parseLunches(HttpServletRequest request, String dishType, Page page, int totalPages) {
        List<Lunch> lunches = lunchService.getLunchesByType(dishType, page);
        Map<Lunch, Integer> lunchMap = new LinkedHashMap<>();

        for (Lunch lunch : lunches) {
            lunch.getDishes().addAll(dishService.getDishesByLunchId(lunch.getId()));
            int price = lunch.getPrice();
            lunchMap.put(lunch, price);
        }
        request.setAttribute("lunchMap", lunchMap);
        request.setAttribute("totalPages", totalPages);
    }

    private void setAttributes(HttpServletRequest request, String dishType, int pageNumber) {
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("dishType", dishType);
        request.setAttribute("mActive", true);
    }
}
