package com.restaurant.command.menu;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Ingredient;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.IngredientService;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DishCommandTest {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    private static List<Ingredient> INGREDIENT_LIST = new ArrayList<>();
    private static final OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static final Ingredient INGREDIENT = Ingredient.builder().build();
    private static final User USER = User.builder().withId(1L).build();
    private static final Order ORDER = Order.builder().withId(1L).build();
    private static final Dish DISH = Dish.builder().withId(1L).build();


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private DishService dishService;
    @Mock
    private IngredientService ingredientService;
    @Mock
    private OrderService orderService;
    @Mock
    private Localization localization;
    @InjectMocks
    private DishCommand dishCommand;

    @Before
    public void setUp() {
        INGREDIENT_LIST.add(INGREDIENT);
    }

    @Test
    public void show() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(DISH.getId()));
        when(dishService.getDishById(anyLong())).thenReturn(Optional.of(DISH));
        when(ingredientService.findIngredientsByDishId(DISH.getId())).thenReturn(INGREDIENT_LIST);
        when(session.getAttribute("message")).thenReturn("Message");

        final String url = dishCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/dish.jsp", url);
        verify(request).getSession();
        verify(request).getParameter(anyString());
        verify(dishService).getDishById(anyLong());
        verify(ingredientService).findIngredientsByDishId(DISH.getId());
        verify(session).getAttribute("message");
    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(request.getParameter("id")).thenReturn(String.valueOf(DISH.getId()));
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(session.getAttribute("inBasket")).thenReturn(1);
        when(session.getAttribute("locale")).thenReturn("");

        final String url = dishCommand.execute(request);

        assertNotNull(url);
        assertTrue(url.contains("/dish"));
        verify(request).getSession();
        verify(localization).getLocalizationBundle(request);
        verify(request).getParameter(anyString());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS, USER.getId());
        verify(session, times(3)).getAttribute(any());
    }
}