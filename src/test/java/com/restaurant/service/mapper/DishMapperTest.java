package com.restaurant.service.mapper;

import com.restaurant.domain.Dish;
import com.restaurant.domain.DishType;
import com.restaurant.entity.DishEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class DishMapperTest {
    private static final Long ID = 1L;
    private static final String NAME = "Ice cream";
    private static final String ABOUT = "Chocolate";
    private static final DishType DISH_TYPE = DishType.DESSERT;
    private static final double PRICE = 123;
    private static final int WEIGHT = 175;
    private static final String IMG = "ice-cream.jpg";

    @InjectMocks
    private DishMapper dishMapper;

    @Test
    public void mapEntityToDomain() {
        final DishEntity dishEntity = DishEntity.builder()
                .withId(ID)
                .withName(NAME)
                .withAbout(ABOUT)
                .withDishType(DISH_TYPE)
                .withPrice(PRICE)
                .withWeight(WEIGHT)
                .withImage(IMG)
                .build();

        final Dish dish = dishMapper.mapEntityToDomain(dishEntity);
        assertThat("Mapping id is failed", dish.getId(), is(ID));
        assertThat("Mapping name is failed", dish.getName(), is(NAME));
        assertThat("Mapping about is failed", dish.getAbout(), is(ABOUT));
        assertThat("Mapping dish type is failed", dish.getDishType(), is(DISH_TYPE));
        assertThat("Mapping price is failed", dish.getPrice(), is(PRICE));
        assertThat("Mapping weight is failed", dish.getWeight(), is(WEIGHT));
        assertThat("Mapping image is failed", dish.getImg(), is(IMG));
    }

    @Test
    public void mapDomainToEntity() {
        final Dish dish = Dish.builder()
                .withId(ID)
                .withName(NAME)
                .withAbout(ABOUT)
                .withDishType(DISH_TYPE)
                .withPrice(PRICE)
                .withWeight(WEIGHT)
                .withImage(IMG)
                .build();

        final DishEntity dishEntity = dishMapper.mapDomainToEntity(dish);
        assertThat("Mapping id is failed", dishEntity.getId(), is(ID));
        assertThat("Mapping name is failed", dishEntity.getName(), is(NAME));
        assertThat("Mapping about is failed", dishEntity.getAbout(), is(ABOUT));
        assertThat("Mapping dish type is failed", dishEntity.getDishType(), is(DISH_TYPE));
        assertThat("Mapping price is failed", dishEntity.getPrice(), is(PRICE));
        assertThat("Mapping weight is failed", dishEntity.getWeight(), is(WEIGHT));
        assertThat("Mapping image is failed", dishEntity.getImg(), is(IMG));
    }
}