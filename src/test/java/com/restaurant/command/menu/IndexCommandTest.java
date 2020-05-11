package com.restaurant.command.menu;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IndexCommandTest {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    private static final OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static Map<Dish, Integer> DISH_INTEGER_MAP = new HashMap<>();
    private static Map<Lunch, Integer> LUNCH_INTEGER_MAP = new HashMap<>();
    private static final Long ID = 1L;
    private static final User USER = User.builder().withId(ID).build();
    private static final Order ORDER = Order.builder().withId(ID).build();
    private static final Dish DISH = Dish.builder().withId(ID).build();
    private static final Lunch LUNCH = Lunch.builder().withId(ID).build();


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private OrderService orderService;
    @Mock
    private DishService dishService;
    @Mock
    private LunchService lunchService;
    @Mock
    private Localization localization;
    @InjectMocks
    private IndexCommand indexCommand;

    @Before
    public void setUp() {
        DISH_INTEGER_MAP.put(DISH, 1);
        LUNCH_INTEGER_MAP.put(LUNCH, 1);
    }

    @Test
    public void showWithExistingOrder() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(orderService.getOrderByStatusAndUserId(eq(ORDER_STATUS), anyLong())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        String url = indexCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/index.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(localization).getLocalizationBundle(request);
        verify(orderService).getOrderByStatusAndUserId(eq(ORDER_STATUS), anyLong());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void showWithNewOrder() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(orderService.getOrderByStatusAndUserId(eq(ORDER_STATUS), anyLong())).thenReturn(Optional.empty());


        String url = indexCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/index.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute("user");
        verify(localization).getLocalizationBundle(request);
        verify(orderService).getOrderByStatusAndUserId(eq(ORDER_STATUS), anyLong());
    }

    @Test
    public void execute() {
        String url = indexCommand.execute(request);
        assertNotNull(url);
    }
}