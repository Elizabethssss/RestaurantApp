package com.restaurant.service.impl;

import com.restaurant.dao.IngredientDao;
import com.restaurant.domain.Ingredient;
import com.restaurant.entity.IngredientEntity;
import com.restaurant.service.mapper.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {
    private final static Long ID = 1L;
    private static List<IngredientEntity> INGREDIENT_ENTITY_LIST = new ArrayList<>();
    private static IngredientEntity INGREDIENT_ENTITY =IngredientEntity.builder().build();
    private static Ingredient INGREDIENT =Ingredient.builder().build();

    @Mock
    private IngredientDao ingredientDao;
    @Mock
    private Mapper<IngredientEntity, Ingredient> ingredientMapper;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Before
    public void setUp() {
        INGREDIENT_ENTITY_LIST.add(INGREDIENT_ENTITY);
    }

    @Test
    public void findIngredientsByDishIdShouldReturnList() {
        when(ingredientDao.findIngredientsByDishId(anyLong())).thenReturn(INGREDIENT_ENTITY_LIST);
        when(ingredientMapper.mapEntityToDomain(any())).thenReturn(INGREDIENT);

        List<Ingredient> ingredientList = ingredientService.findIngredientsByDishId(ID);

        assertTrue(ingredientList.size() > 0);
        verify(ingredientDao).findIngredientsByDishId(anyLong());
        verify(ingredientMapper).mapEntityToDomain(any());
    }
}