package com.restaurant.service.mapper;

import com.restaurant.domain.Ingredient;
import com.restaurant.entity.IngredientEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientMapperTest {
    private static final Long ID = 1L;
    private static final String NAME = "Tomato";
    private static final String IMG = "tomato.png";

    @InjectMocks
    private IngredientMapper ingredientMapper;

    @Test
    public void mapEntityToDomain() {
        final IngredientEntity ingredientEntity = IngredientEntity.builder()
                .withId(ID)
                .withName(NAME)
                .withImg(IMG)
                .build();

        final Ingredient ingredient = ingredientMapper.mapEntityToDomain(ingredientEntity);
        assertThat("Mapping id is failed", ingredient.getId(), is(ID));
        assertThat("Mapping name is failed", ingredient.getName(), is(NAME));
        assertThat("Mapping image is failed", ingredient.getImg(), is(IMG));
    }

    @Test
    public void mapDomainToEntity() {
        final Ingredient ingredient = Ingredient.builder()
                .withId(ID)
                .withName(NAME)
                .withImg(IMG)
                .build();

        final IngredientEntity ingredientEntity = ingredientMapper.mapDomainToEntity(ingredient);
        assertThat("Mapping id is failed", ingredientEntity.getId(), is(ID));
        assertThat("Mapping name is failed", ingredientEntity.getName(), is(NAME));
        assertThat("Mapping image is failed", ingredientEntity.getImg(), is(IMG));
    }
}