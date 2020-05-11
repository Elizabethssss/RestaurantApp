package com.restaurant.command.menu;

import com.restaurant.domain.DishType;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
import com.restaurant.service.DishService;
import com.restaurant.service.LunchService;
import com.restaurant.service.util.Localization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuCommandTest {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.getDefault());
    private static final DishType DISH_TYPE = DishType.DESSERT;
    private static final LunchType LUNCH_TYPE = LunchType.BREAKFAST;
    private static final LunchType LUNCH_TYPE2 = LunchType.LUNCH;
    private static final LunchType LUNCH_TYPE3 = LunchType.HOLIDAY;
    private static final String PAGE = "1";
    private static List<Lunch> LUNCH_LIST = new ArrayList<>();

    @Mock
    private HttpServletRequest request;
    @Mock
    private DishService dishService;
    @Mock
    private LunchService lunchService;
    @Mock
    private Localization localization;
    @InjectMocks
    private MenuCommand menuCommand;

    @Test
    public void showShouldReturnDishPage() {
        when(request.getParameter("type")).thenReturn(DISH_TYPE.name());
        when(request.getParameter("page")).thenReturn(PAGE);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(dishService.count(DISH_TYPE.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/menu.jsp", url);
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(dishService).count(DISH_TYPE.name());
    }

    @Test
    public void showShouldReturnMenuCommand() {
        when(request.getParameter("type")).thenReturn(DISH_TYPE.name());
        when(request.getParameter("page")).thenReturn(PAGE + 5);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(dishService.count(DISH_TYPE.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/menu"));
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(dishService).count(DISH_TYPE.name());
    }

    @Test
    public void showShouldReturnMenuCommand2() {
        when(request.getParameter("type")).thenReturn(DISH_TYPE.name());
        when(request.getParameter("page")).thenReturn("0");
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(dishService.count(DISH_TYPE.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/menu"));
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(dishService).count(DISH_TYPE.name());
    }

    @Test
    public void showShouldReturnLunchPage() {
        when(request.getParameter("type")).thenReturn(LUNCH_TYPE.name());
        when(request.getParameter("page")).thenReturn(PAGE);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(lunchService.count(LUNCH_TYPE.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/menu.jsp", url);
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(lunchService).count(LUNCH_TYPE.name());
    }

    @Test
    public void showShouldReturnLunchPage2() {
        when(request.getParameter("type")).thenReturn(LUNCH_TYPE.name());
        when(request.getParameter("page")).thenReturn(PAGE);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(lunchService.count(LUNCH_TYPE.name())).thenReturn(5);
        LUNCH_LIST.add(Lunch.builder().withDishes(new ArrayList<>()).build());
        when(lunchService.getLunchesByType(anyString(), any())).thenReturn(LUNCH_LIST);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertEquals("pages/menu.jsp", url);
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(lunchService).count(LUNCH_TYPE.name());
        verify(lunchService).getLunchesByType(anyString(), any());
    }

    @Test
    public void showShouldReturnLunchMenuCommand() {
        when(request.getParameter("type")).thenReturn(LUNCH_TYPE2.name());
        when(request.getParameter("page")).thenReturn(PAGE + 5);
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(lunchService.count(LUNCH_TYPE2.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/menu"));
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(lunchService).count(LUNCH_TYPE2.name());
    }

    @Test
    public void showShouldReturnLunchMenuCommand2() {
        when(request.getParameter("type")).thenReturn(LUNCH_TYPE3.name());
        when(request.getParameter("page")).thenReturn("0");
        when(localization.getLocalizationBundle(request)).thenReturn(RESOURCE_BUNDLE);
        when(lunchService.count(LUNCH_TYPE3.name())).thenReturn(5);

        String url = menuCommand.show(request);

        assertNotNull(url);
        assertTrue(url.contains("/menu"));
        verify(request, times(2)).getParameter(any());
        verify(localization).getLocalizationBundle(request);
        verify(lunchService).count(LUNCH_TYPE3.name());
    }

    @Test
    public void execute() {
        String url = menuCommand.execute(request);
        assertNotNull(url);
    }
}