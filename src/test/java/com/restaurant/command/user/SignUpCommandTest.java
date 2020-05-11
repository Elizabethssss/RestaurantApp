package com.restaurant.command.user;

import com.restaurant.service.UserService;
import com.restaurant.service.util.Localization;
import org.apache.commons.codec.digest.DigestUtils;
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
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignUpCommandTest {
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    public static Map<String, String> ERROR_MAP;
    public static final String VALID_USERNAME = "Liza";
    public static final String VALID_EMAIL = "liza@gmail.com";
    public static final String VALID_PASS = DigestUtils.md5Hex("123");

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @Mock
    private Localization localization;
    @InjectMocks
    private SignUpCommand signUpCommand;

    @Before
    public void setUp() {
        ERROR_MAP = new HashMap<>();
    }

    @Test
    public void show() {
        String url = signUpCommand.show(request);
        assertNotNull(url);
    }

    @Test
    public void executeShouldReturnPageUrl() {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password1")).thenReturn("");
        when(request.getParameter("password2")).thenReturn("123");
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);

        String url = signUpCommand.execute(request);

        assertNotNull(url);
        assertEquals("pages/authorization.jsp", url);
        verify(request, times(4)).getParameter(anyString());
        verify(localization).getLocalizationBundle(request);
    }

    @Test
    public void executeShouldReturnServletUrl() {
        when(request.getParameter("username")).thenReturn(VALID_USERNAME);
        when(request.getParameter("email")).thenReturn(VALID_EMAIL);
        when(request.getParameter("password1")).thenReturn(VALID_PASS);
        when(request.getParameter("password2")).thenReturn(VALID_PASS);
        when(request.getSession()).thenReturn(session);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(userService.register(any(), eq(RESOURCE_BUNDLE))).thenReturn(ERROR_MAP);

        String url = signUpCommand.execute(request);

        assertNotNull(url);
       assertTrue(url.contains("/login"));
        verify(request, times(4)).getParameter(anyString());
        verify(request).getSession();
        verify(localization).getLocalizationBundle(request);
        verify(userService).register(any(), eq(RESOURCE_BUNDLE));
    }

}