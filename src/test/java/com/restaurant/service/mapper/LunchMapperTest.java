package com.restaurant.service.mapper;

import com.restaurant.domain.Dish;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LunchMapperTest {
    private static final Long ID = 1L;
    private static final String NAME = "Ice cream";
    private static final String DESCRIPTION = "Chocolate";
    private static final String IMG = "ice-cream.jpg";
    private static final LunchType LUNCH_TYPE = LunchType.LUNCH;
    private static final List<Dish> DISH_LIST = new ArrayList<>();
    private static final List<DishEntity> DISH_ENTITY_LIST = new ArrayList<>();
    private static final Dish DISH = Dish.builder().build();
    private static final DishEntity DISH_ENTITY = DishEntity.builder().build();

    @Mock
    private DishMapper dishMapper;
    @InjectMocks
    private LunchMapper lunchMapper;

    @Test
    public void mapEntityToDomain() {
        DISH_ENTITY_LIST.add(DISH_ENTITY);
        when(dishMapper.mapEntityToDomain(any())).thenReturn(DISH);
        final LunchEntity lunchEntity = LunchEntity.builder()
                .withId(ID)
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withImg(IMG)
                .withLunchType(LUNCH_TYPE)
                .withDishEntities(DISH_ENTITY_LIST)
                .build();

        final Lunch lunch = lunchMapper.mapEntityToDomain(lunchEntity);
        assertThat("Mapping id is failed", lunch.getId(), is(ID));
        assertThat("Mapping name is failed", lunch.getName(), is(NAME));
        assertThat("Mapping description is failed", lunch.getDescription(), is(DESCRIPTION));
        assertThat("Mapping image is failed", lunch.getImg(), is(IMG));
        assertThat("Mapping lunch type is failed", lunch.getLunchType(), is(LUNCH_TYPE));
        verify(dishMapper).mapEntityToDomain(any());
    }

    @Test
    public void mapDomainToEntity() {
        DISH_LIST.add(DISH);
        when(dishMapper.mapDomainToEntity(any())).thenReturn(DISH_ENTITY);
        final Lunch lunch = Lunch.builder()
                .withId(ID)
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withImg(IMG)
                .withLunchType(LUNCH_TYPE)
                .withDishes(DISH_LIST)
                .build();

        final LunchEntity lunchEntity = lunchMapper.mapDomainToEntity(lunch);
        assertThat("Mapping id is failed", lunchEntity.getId(), is(ID));
        assertThat("Mapping name is failed", lunchEntity.getName(), is(NAME));
        assertThat("Mapping description is failed", lunchEntity.getDescription(), is(DESCRIPTION));
        assertThat("Mapping image is failed", lunchEntity.getImg(), is(IMG));
        assertThat("Mapping lunch type is failed", lunchEntity.getLunchType(), is(LUNCH_TYPE));
        verify(dishMapper).mapDomainToEntity(any());
    }
}