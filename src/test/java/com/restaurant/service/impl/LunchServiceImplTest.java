package com.restaurant.service.impl;

import com.restaurant.dao.LunchDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
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
public class LunchServiceImplTest {
    private final static Long ID = 1L;
    private final static Page PAGE = new Page(5, 0);
    private final static LunchType LUNCH_TYPE = LunchType.BREAKFAST;
    private static List<LunchEntity> LUNCH_ENTITY_LIST = new ArrayList<>();
    private static LunchEntity LUNCH_ENTITY = LunchEntity.builder().withId(ID).build();
    private static Lunch LUNCH = Lunch.builder().withId(ID).build();
    private static Map<LunchEntity, Integer> LUNCH_ENTITY_INTEGER_MAP = new HashMap<>();

    @Mock
    private LunchDao lunchDao;
    @Mock
    private Mapper<LunchEntity, Lunch> lunchMapper;
    @InjectMocks
    private LunchServiceImpl lunchService;

    @Before
    public void setUp() {
        LUNCH_ENTITY_LIST.add(LUNCH_ENTITY);
        LUNCH_ENTITY_INTEGER_MAP.put(LUNCH_ENTITY, 1);
    }

    @Test
    public void getLunchesByType() {
        when(lunchDao.findAll(eq(LUNCH_TYPE.name()), any())).thenReturn(LUNCH_ENTITY_LIST);
        when(lunchMapper.mapEntityToDomain(any())).thenReturn(LUNCH);

        final List<Lunch> lunchList = lunchService.getLunchesByType(LUNCH_TYPE.name(), PAGE);

        assertTrue(lunchList.size() > 0);
        verify(lunchDao).findAll(eq(LUNCH_TYPE.name()), any());
        verify(lunchMapper).mapEntityToDomain(any());
    }

    @Test
    public void count() {
        when(lunchDao.count(LUNCH_TYPE.name())).thenReturn(10);

        assertTrue(lunchService.count(LUNCH_TYPE.name()) > 0);

        verify(lunchDao).count(LUNCH_TYPE.name());
    }

    @Test
    public void getLunchById() {
        when(lunchDao.findById(ID)).thenReturn(Optional.of(LUNCH_ENTITY));
        when(lunchMapper.mapEntityToDomain(any())).thenReturn(LUNCH);

        Optional<Lunch> lunch = lunchService.getLunchById(ID);

        assertEquals(lunch, Optional.of(LUNCH));
        verify(lunchDao).findById(ID);
        verify(lunchMapper).mapEntityToDomain(any());
    }

    @Test
    public void getLunchesByOrderId() {
        when(lunchDao.getLunchesByOrderId(ID)).thenReturn(LUNCH_ENTITY_INTEGER_MAP);
        when(lunchMapper.mapEntityToDomain(any())).thenReturn(LUNCH);

        Map<Lunch, Integer> lunchIntegerMap = lunchService.getLunchesByOrderId(ID);

        assertTrue(lunchIntegerMap.size() > 0);
        verify(lunchDao).getLunchesByOrderId(ID);
        verify(lunchMapper).mapEntityToDomain(any());
    }
}