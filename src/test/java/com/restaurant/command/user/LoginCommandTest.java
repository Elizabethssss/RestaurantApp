package com.restaurant.command.user;

import com.restaurant.domain.Order;
import com.restaurant.domain.User;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import com.restaurant.service.util.Localization;
import org.apache.commons.codec.digest.DigestUtils;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    private static final String VALID_EMAIL = "liza@gmail.com";
    private static final String VALID_PASS = DigestUtils.md5Hex("123");
    private static final String NOT_VALID_PASS = "abc";
    private static final List<Order> LIST = new ArrayList<>();
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    private static final User USER = User.builder()
            .withId(1L)
            .withEmail(VALID_EMAIL)
            .withPassword(VALID_PASS)
            .build();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @Mock
    private OrderService orderService;
    @Mock
    private Localization localization;
    @InjectMocks
    private LoginCommand loginCommand;

    @Test
    public void show() {
        String url = loginCommand.show(request);
        assertNotNull(url);
    }

    @Test
    public void executeShouldReturnPageUrl() {
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password")).thenReturn(NOT_VALID_PASS);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(userService.login(VALID_EMAIL, DigestUtils.md5Hex(NOT_VALID_PASS))).thenReturn(Optional.empty());

        String url = loginCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/authorization.jsp", url);
        verify(userService).login(VALID_EMAIL, DigestUtils.md5Hex(NOT_VALID_PASS));
        verify(request, times(2)).getParameter(anyString());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldReturnServletUrl() {
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password")).thenReturn(VALID_PASS);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(userService.login(VALID_EMAIL, DigestUtils.md5Hex(VALID_PASS))).thenReturn(Optional.of(USER));
        when(request.getSession()).thenReturn(session);
        when(orderService.getOrdersByUserId(USER.getId())).thenReturn(LIST);
//        when(session.setAttribute("user", user))

        String url = loginCommand.execute(request);

        assertNotNull(url);
        assertTrue(url.contains("/index"));
        verify(userService).login(VALID_EMAIL, DigestUtils.md5Hex(VALID_PASS));
        verify(request, times(2)).getParameter(anyString());
        verify(localization).getLocalizationBundle(request);
        verify(request).getSession();
        verify(orderService).getOrdersByUserId(USER.getId());
    }
}