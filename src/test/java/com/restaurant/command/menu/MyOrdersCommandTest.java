package com.restaurant.command.menu;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyOrdersCommandTest {
    private static final User USER = User.builder().withId(1L).build();
    private static final Order ORDER = Order.builder().withId(1L).withDishes(new HashMap<>()).withLunches(new HashMap<>()).build();
    private static final Dish DISH = Dish.builder().withId(1L).build();
    private static final Lunch LUNCH = Lunch.builder().withId(1L).withDishes(new ArrayList<>()).build();
    private static List<Order> ORDER_LIST;
    private static List<Dish> DISH_LIST = new ArrayList<>();
    private static Map<Dish, Integer> DISH_INTEGER_MAP = new HashMap<>();
    private static Map<Lunch, Integer> LUNCH_INTEGER_MAP = new HashMap<>();

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
    private MyOrdersCommand myOrdersCommand;

    @Before
    public void setUp() {
        ORDER_LIST = new ArrayList<>();
        ORDER_LIST.add(ORDER);
    }

    @Test
    public void showShouldReturnPageUrl() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.count(USER.getId())).thenReturn(5);
        when(request.getParameter("page")).thenReturn("1");

        when(orderService.getOrdersExceptFormed(eq(USER.getId()), any())).thenReturn(ORDER_LIST);
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = myOrdersCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/myOrders.jsp", url);
        verify(request).getSession();
        verify(session, times(1)).getAttribute(any());
        verify(orderService).count(USER.getId());
        verify(request).getParameter(any());
        verify(orderService).getOrdersExceptFormed(eq(USER.getId()), any());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void showShouldReturnPageUrl2() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.count(USER.getId())).thenReturn(5);
        when(request.getParameter("page")).thenReturn("1");
        LUNCH_INTEGER_MAP.put(LUNCH, 1);
        DISH_LIST.add(DISH);
        when(orderService.getOrdersExceptFormed(eq(USER.getId()), any())).thenReturn(ORDER_LIST);
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);
        when(dishService.getDishesByLunchId(anyLong())).thenReturn(DISH_LIST);

        final String url = myOrdersCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/myOrders.jsp", url);
        verify(request).getSession();
        verify(session, times(1)).getAttribute(any());
        verify(orderService).count(USER.getId());
        verify(request).getParameter(any());
        verify(orderService).getOrdersExceptFormed(eq(USER.getId()), any());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
        verify(dishService).getDishesByLunchId(anyLong());
    }

    @Test
    public void showShouldReturnMyOrdersCommand() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.count(USER.getId())).thenReturn(5);
        when(request.getParameter("page")).thenReturn("0");

        final String url = myOrdersCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/myOrders"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).count(USER.getId());
        verify(request).getParameter(any());
    }

    @Test
    public void showShouldReturnMyOrdersCommand2() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.count(USER.getId())).thenReturn(5);
        when(request.getParameter("page")).thenReturn("100");

        final String url = myOrdersCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/myOrders"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).count(USER.getId());
        verify(request).getParameter(any());
    }

    @Test
    public void execute() {
        final String url = myOrdersCommand.execute(request);
        assertNotNull(url);
    }
}