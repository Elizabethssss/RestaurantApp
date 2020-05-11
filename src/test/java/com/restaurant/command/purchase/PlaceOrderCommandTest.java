package com.restaurant.command.purchase;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.OrderService;
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
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaceOrderCommandTest {
    private static final OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static final User USER = User.builder().withId(1L).build();
    private static final Order ORDER = Order.builder().withId(1L).build();
    private static final Dish DISH = Dish.builder().withId(1L).build();
    private static final Lunch LUNCH = Lunch.builder().withId(1L).withDishes(new ArrayList<>()).build();
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
    @InjectMocks
    private PlaceOrderCommand placeOrderCommand;

    @Test
    public void showWithEmptyMaps() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = placeOrderCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/myOrders"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS, USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void showWithFullMaps() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        DISH_INTEGER_MAP.put(DISH, 1);
        LUNCH_INTEGER_MAP.put(LUNCH, 1);
        DISH_LIST.add(DISH);
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);
        when(dishService.getDishesByLunchId(anyLong())).thenReturn(DISH_LIST);

        final String url = placeOrderCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/myOrders"));
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS, USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
        verify(dishService).getDishesByLunchId(anyLong());
    }

    @Test
    public void execute() {
        String url = placeOrderCommand.execute(request);
        assertNotNull(url);
    }
}