package com.restaurant.command.purchase;

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
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketCommandTest {
    private static final String ACTION = "action";
    private static final String TYPE = "type";
    private static final String ID_STR = "id";
    private static JSONObject JSON_OBJECT;
    private static final Long ID = 1L;
    private static final String PLUS = "plus";
    private static final String MINUS = "minus";
    private static final String REMOVE = "remove";
    private static final String TYPE_DISH = "dish";
    private static final String TYPE_LUNCH = "lunch";
    private static final User USER = User.builder().withId(1L).build();
    private static final Order ORDER = Order.builder().withId(1L).withDishes(new HashMap<>()).withLunches(new HashMap<>()).build();
    private static final Dish DISH = Dish.builder().withId(1L).build();
    private static final Lunch LUNCH = Lunch.builder().withId(1L).withDishes(new ArrayList<>()).build();
    private static final OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static Map<Dish, Integer> DISH_INTEGER_MAP;
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
    private BasketCommand basketCommand;

    @Before
    public void setUp() {
        JSON_OBJECT = new JSONObject();
        DISH_INTEGER_MAP = new HashMap<>();
        DISH_INTEGER_MAP.put(DISH, 1);
        LUNCH_INTEGER_MAP = new HashMap<>();
        LUNCH_INTEGER_MAP.put(LUNCH, 1);
    }

    @Test
    public void showWithEmptyMaps() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/basket.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
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
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/basket.jsp", url);
        verify(request).getSession();
        verify(session).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executePlusWithDish() {
        JSON_OBJECT.put(ACTION, PLUS);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executePlusWithDish2() {
        JSON_OBJECT.put(ACTION, PLUS);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithDish() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithDish2() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, ID);
        DISH_INTEGER_MAP.replace(DISH, 5);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithDish3() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeRemoveWithDish() {
        JSON_OBJECT.put(ACTION, REMOVE);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeRemoveWithDish2() {
        JSON_OBJECT.put(ACTION, REMOVE);
        JSON_OBJECT.put(TYPE, TYPE_DISH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executePlusWithLunch() {
        JSON_OBJECT.put(ACTION, PLUS);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executePlusWithLunch2() {
        JSON_OBJECT.put(ACTION, PLUS);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithLunch() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithLunch2() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, ID);
        LUNCH_INTEGER_MAP.replace(LUNCH, 5);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeMinusWithLunch3() {
        JSON_OBJECT.put(ACTION, MINUS);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeRemoveWithLunch() {
        JSON_OBJECT.put(ACTION, REMOVE);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, ID);
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }

    @Test
    public void executeRemoveWithLunch2() {
        JSON_OBJECT.put(ACTION, REMOVE);
        JSON_OBJECT.put(TYPE, TYPE_LUNCH);
        JSON_OBJECT.put(ID_STR, "5");
        when(request.getParameter("data")).thenReturn(String.valueOf(JSON_OBJECT));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(session.getAttribute("inBasket")).thenReturn(5);
        when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));
        when(dishService.getDishesByOrderId(ORDER.getId())).thenReturn(DISH_INTEGER_MAP);
        when(lunchService.getLunchesByOrderId(ORDER.getId())).thenReturn(LUNCH_INTEGER_MAP);

        final String url = basketCommand.execute(request);

        assertNotNull(url);
        verify(request).getParameter(any());
        verify(request).getSession();
        verify(session, times(2)).getAttribute(any());
        verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS ,USER.getId());
        verify(dishService).getDishesByOrderId(ORDER.getId());
        verify(lunchService).getLunchesByOrderId(ORDER.getId());
    }
}