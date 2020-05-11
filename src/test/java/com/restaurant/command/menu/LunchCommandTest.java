package com.restaurant.command.menu;

import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LunchCommandTest {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    public static final OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static final Order ORDER = Order.builder().withId(1L).build();
    private static final User USER = User.builder().withId(1L).build();
    private static final Lunch LUNCH = Lunch.builder()
            .withId(1L)
            .withLunchType(LunchType.LUNCH)
            .withDishes(new ArrayList<>())
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private LunchService lunchService;
    @Mock
    private DishService dishService;
    @Mock
    private OrderService orderService;
    @Mock
    private Localization localization;
    @InjectMocks
    private LunchCommand lunchCommand;

    @Test
    public void show() {
        if (LUNCH.getTimeFrom().compareTo(LocalTime.now()) < 0 && LUNCH.getTimeTo().compareTo(LocalTime.now()) > 0) {
            when(request.getSession()).thenReturn(session);
            when(request.getParameter("id")).thenReturn(String.valueOf(LUNCH.getId()));
            when(lunchService.getLunchById(LUNCH.getId())).thenReturn(Optional.of(LUNCH));
            when(session.getAttribute("message")).thenReturn("Message");

            final String url = lunchCommand.show(request);

            assertNotNull(url);
            assertEquals("pages/lunch.jsp", url);
            verify(request).getSession();
            verify(request).getParameter(any());
            verify(lunchService).getLunchById(LUNCH.getId());
            verify(session).getAttribute(any());
        }
    }

    @Test
    public void show2() {
        if (LUNCH.getTimeFrom().compareTo(LocalTime.now()) > 0 || LUNCH.getTimeTo().compareTo(LocalTime.now()) < 0) {
            when(request.getSession()).thenReturn(session);
            when(request.getParameter("id")).thenReturn(String.valueOf(LUNCH.getId()));
            when(lunchService.getLunchById(LUNCH.getId())).thenReturn(Optional.of(LUNCH));
            when(session.getAttribute("message")).thenReturn("Message");

            final String url = lunchCommand.show(request);

            assertNotNull(url);
            assertEquals("pages/lunch.jsp", url);
            verify(request).getSession();
            verify(request).getParameter(any());
            verify(lunchService).getLunchById(LUNCH.getId());
            verify(session).getAttribute(any());
        }
    }

    @Test
    public void execute() {
        if(LUNCH.getTimeFrom().compareTo(LocalTime.now()) < 0 && LUNCH.getTimeTo().compareTo(LocalTime.now()) > 0) {
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("user")).thenReturn(USER);
            when(session.getAttribute("inBasket")).thenReturn(1);
            when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
            when(request.getParameter("id")).thenReturn(String.valueOf(LUNCH.getId()));
            when(lunchService.getLunchById(LUNCH.getId())).thenReturn(Optional.of(LUNCH));
            when(orderService.getOrderByStatusAndUserId(ORDER_STATUS, USER.getId())).thenReturn(Optional.of(ORDER));

            final String url = lunchCommand.execute(request);

            assertNotNull(url);
            assertTrue(url.contains("/lunch"));
            verify(request).getSession();
            verify(request).getParameter(any());
            verify(session, times(3)).getAttribute(any());
            verify(localization).getLocalizationBundle(request);
            verify(lunchService).getLunchById(LUNCH.getId());
            verify(orderService).getOrderByStatusAndUserId(ORDER_STATUS, USER.getId());
        }
    }

    @Test
    public void execute2() {
        if (LUNCH.getTimeFrom().compareTo(LocalTime.now()) > 0 || LUNCH.getTimeTo().compareTo(LocalTime.now()) < 0) {
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("user")).thenReturn(USER);
            when(session.getAttribute("inBasket")).thenReturn(1);
            when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
            when(request.getParameter("id")).thenReturn(String.valueOf(LUNCH.getId()));
            when(lunchService.getLunchById(LUNCH.getId())).thenReturn(Optional.of(LUNCH));

            final String url = lunchCommand.execute(request);

            assertNotNull(url);
            assertTrue(url.contains("/lunch"));
            verify(request).getSession();
            verify(request).getParameter(any());
            verify(session, times(3)).getAttribute(any());
            verify(localization).getLocalizationBundle(request);
            verify(lunchService).getLunchById(LUNCH.getId());
        }
    }
}