package com.restaurant.command.purchase;

import com.restaurant.domain.Order;
import com.restaurant.service.OrderService;
import com.restaurant.service.util.Localization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardCommandTest {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    private static final Order ORDER = Order.builder().withId(1L).withCost(123).build();
    private static final String RIGHT_CARD_NUMBER = "378282246310005";
    private static final String RIGHT_CVV = "378";
    private static final String RIGHT_MONTH = "03";
    private static final String RIGHT_YEAR = "24";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private OrderService orderService;
    @Mock
    private Localization localization;
    @InjectMocks
    private CreditCardCommand creditCardCommand;

    @Test
    public void show() {
        when(request.getParameter("id")).thenReturn(String.valueOf(ORDER.getId()));
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(orderService.getOrderById(ORDER.getId())).thenReturn(Optional.of(ORDER));

        final String url = creditCardCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(orderService).getOrderById(ORDER.getId());
    }

    @Test
    public void executeShouldReturnCommandUrl() {
        when(request.getParameter("cardNumber")).thenReturn(RIGHT_CARD_NUMBER);
        when(request.getParameter("expiredMonth")).thenReturn(RIGHT_MONTH);
        when(request.getParameter("expiredYear")).thenReturn(RIGHT_YEAR);
        when(request.getParameter("cvv")).thenReturn(RIGHT_CVV);
        when(request.getParameter("id")).thenReturn(String.valueOf(ORDER.getId()));
        when(orderService.getOrderById(ORDER.getId())).thenReturn(Optional.of(ORDER));
        when(request.getSession()).thenReturn(session);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        final String url = creditCardCommand.execute(request);

        assertNotNull(url);
        assertTrue(url.contains("/myOrders"));
        verify(request, times(5)).getParameter(any());
        verify(orderService).getOrderById(ORDER.getId());
        verify(request).getSession();
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldReturnPageUrl() {
        when(request.getParameter("cardNumber")).thenReturn(RIGHT_CARD_NUMBER + "a");
        when(request.getParameter("expiredMonth")).thenReturn(RIGHT_MONTH);
        when(request.getParameter("expiredYear")).thenReturn(RIGHT_YEAR);
        when(request.getParameter("cvv")).thenReturn(RIGHT_CVV);
        when(request.getParameter("id")).thenReturn(String.valueOf(ORDER.getId()));
        when(orderService.getOrderById(ORDER.getId())).thenReturn(Optional.of(ORDER));
        when(request.getSession()).thenReturn(session);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        final String url = creditCardCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/creditCard.jsp", url);
        verify(request, times(5)).getParameter(any());
        verify(orderService).getOrderById(ORDER.getId());
        verify(request).getSession();
        verify(localization).getLocalizationBundle(request);
    }
}