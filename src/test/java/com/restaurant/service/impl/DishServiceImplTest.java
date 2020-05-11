package com.restaurant.service.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Dish;
import com.restaurant.domain.DishType;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;
import com.restaurant.service.mapper.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DishServiceImplTest {
    private final static Long ID = 1L;
    private final static Page PAGE = new Page(5, 0);
    private final static DishType DISH_TYPE = DishType.DESSERT;
    private static List<DishEntity> DISH_ENTITY_LIST;
    private static DishEntity DISH_ENTITY = DishEntity.builder().withId(ID).build();
    private static Dish DISH = Dish.builder().withId(ID).build();
    private static Map<DishEntity, Integer> DISH_ENTITY_INTEGER_MAP;

    @Mock
    private DishDao dishDao;
    @Mock
    private Mapper<DishEntity, Dish> dishMapper;
    @InjectMocks
    private DishServiceImpl dishService;

    @Before
    public void setUp() {
        DISH_ENTITY_LIST = new ArrayList<>();
        DISH_ENTITY_LIST.add(DISH_ENTITY);
        DISH_ENTITY_INTEGER_MAP = new HashMap<>();
        DISH_ENTITY_INTEGER_MAP.put(DISH_ENTITY, 1);
    }

    @Test
    public void getDishById() {
        when(dishDao.findById(ID)).thenReturn(Optional.of(DISH_ENTITY));
        when(dishMapper.mapEntityToDomain(any())).thenReturn(DISH);

        final Optional<Dish> dish = dishService.getDishById(ID);

        assertEquals(Optional.of(DISH), dish);
        verify(dishDao).findById(ID);
        verify(dishMapper).mapEntityToDomain(any());
    }

    @Test
    public void getDishesByOrderId() {
        when(dishDao.getDishesByOrderId(ID)).thenReturn(DISH_ENTITY_INTEGER_MAP);
        when(dishMapper.mapEntityToDomain(any())).thenReturn(DISH);

        Map<Dish, Integer> dishIntegerMap = dishService.getDishesByOrderId(ID);

        assertTrue(dishIntegerMap.size() > 0);
        verify(dishDao).getDishesByOrderId(ID);
        verify(dishMapper).mapEntityToDomain(any());
    }

    @Test
    public void getDishesByLunchId() {
        when(dishDao.getDishesByLunchId(ID)).thenReturn(DISH_ENTITY_LIST);
        when(dishMapper.mapEntityToDomain(any())).thenReturn(DISH);

        final List<Dish> dishList = dishService.getDishesByLunchId(ID);

        assertTrue(dishList.size() > 0);
        verify(dishDao).getDishesByLunchId(ID);
        verify(dishMapper).mapEntityToDomain(any());
    }

    @Test
    public void getDishesByType() {
        when(dishDao.findAll(eq(DISH_TYPE.name()), any())).thenReturn(DISH_ENTITY_LIST);
        when(dishMapper.mapEntityToDomain(any())).thenReturn(DISH);

        final List<Dish> dishList = dishService.getDishesByType(DISH_TYPE.name(), PAGE);

        assertTrue(dishList.size() > 0);
        verify(dishDao).findAll(eq(DISH_TYPE.name()), any());
        verify(dishMapper).mapEntityToDomain(any());
    }

    @Test
    public void count() {
        when(dishDao.count(DISH_TYPE.name())).thenReturn(10);

        assertTrue(dishService.count(DISH_TYPE.name()) > 0);

        verify(dishDao).count(DISH_TYPE.name());
    }
}