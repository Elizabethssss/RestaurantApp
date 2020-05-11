package com.restaurant.command.user;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.Role;
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
public class AdminCommandTest {
    private static final User USER = User.builder().withId(1L).withRole(Role.USER).build();
    private static final User ADMIN = User.builder().withId(2L).withRole(Role.ADMIN).build();
    private static final Order ORDER = Order.builder().withId(1L)
            .withDishes(new HashMap<>()).withLunches(new HashMap<>()).build();
    private static final Lunch LUNCH = Lunch.builder().withId(1L).withDishes(new ArrayList<>()).build();
    public static final OrderStatus ORDER_STATUS = OrderStatus.SENT;
    private static List<Order> ORDER_LIST;
    private static Map<Dish, Integer> DISH_INTEGER_MAP = new HashMap<>();
    private static Map<Lunch, Integer> LUNCH_INTEGER_MAP;

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
    private AdminCommand adminCommand;

    @Before
    public void setUp() {
        ORDER_LIST = new ArrayList<>();
        LUNCH_INTEGER_MAP = new HashMap<>();
    }

    @Test
    public void showShouldReturnPageUrl() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(ADMIN);
        when(orderService.countOrdersByStatus(ORDER_STATUS)).thenReturn(10);
        when(request.getParameter("page")).thenReturn("1");
        when(orderService.getOrdersByStatus(eq(ORDER_STATUS), any())).thenReturn(ORDER_LIST);

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/admin.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute(any());
        verify(request).getParameter(any());
        verify(orderService).countOrdersByStatus(ORDER_STATUS);
        verify(orderService).getOrdersByStatus(eq(ORDER_STATUS), any());
    }

    @Test
    public void showShouldReturnPageUrl2() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(ADMIN);
        when(orderService.countOrdersByStatus(ORDER_STATUS)).thenReturn(10);
        when(request.getParameter("page")).thenReturn("1");
        ORDER_LIST.add(ORDER);
        when(orderService.getOrdersByStatus(eq(ORDER_STATUS), any())).thenReturn(ORDER_LIST);
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/admin.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute(any());
        verify(request).getParameter(any());
        verify(orderService).countOrdersByStatus(ORDER_STATUS);
        verify(orderService).getOrdersByStatus(eq(ORDER_STATUS), any());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void showShouldReturnPageUrl3() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(ADMIN);
        when(orderService.countOrdersByStatus(ORDER_STATUS)).thenReturn(10);
        when(request.getParameter("page")).thenReturn("1");
        ORDER_LIST.add(ORDER);
        LUNCH_INTEGER_MAP.put(LUNCH, 1);
        when(orderService.getOrdersByStatus(eq(ORDER_STATUS), any())).thenReturn(ORDER_LIST);
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/admin.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute(any());
        verify(request).getParameter(any());
        verify(orderService).countOrdersByStatus(ORDER_STATUS);
        verify(orderService).getOrdersByStatus(eq(ORDER_STATUS), any());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void showShouldReturnIndexCommand() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/index"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
    }

    @Test
    public void showShouldReturnAdminCommand() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(ADMIN);
        when(orderService.countOrdersByStatus(ORDER_STATUS)).thenReturn(10);
        when(request.getParameter("page")).thenReturn("0");

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/admin"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(request).getParameter(any());
        verify(orderService).countOrdersByStatus(ORDER_STATUS);
    }

    @Test
    public void showShouldReturnAdminCommand2() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(ADMIN);
        when(orderService.countOrdersByStatus(ORDER_STATUS)).thenReturn(10);
        when(request.getParameter("page")).thenReturn("1000");

        final String url = adminCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/admin"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(request).getParameter(any());
        verify(orderService).countOrdersByStatus(ORDER_STATUS);
    }


    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(ORDER.getId()));

        final String url = adminCommand.execute(request);

        assertNotNull(url);
        assertTrue(url.contains("/admin"));
        verify(request).getSession();
        verify(request).getParameter(any());
    }
}