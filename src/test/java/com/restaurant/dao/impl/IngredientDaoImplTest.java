package com.restaurant.dao.impl;

import com.restaurant.dao.IngredientDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.entity.IngredientEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IngredientDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";

    private HikariCPManager manager;
    private IngredientEntity ingredientEntity;
    private IngredientEntity testIngredientEntity;
    private IngredientDao ingredientDao;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        try {
            manager = new HikariCPManager(DB_PROP_PATH);
            Connection connection = manager.getConnection();
            Statement statement = connection.createStatement();
            String schemaQuery = new String(Files.readAllBytes(Paths.get(SCHEMA_PATH)));
            statement.execute(schemaQuery);
            String dataQuery = new String(Files.readAllBytes(Paths.get(DATA_PATH)));
            statement.execute(dataQuery);
            statement.close();
            connection.close();
            ingredientDao = new IngredientDaoImpl(manager);
            setEntities();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        manager.shutdown();
    }

    @Test
    public void findIngredientsByDishId() {
        assertTrue(ingredientDao.findIngredientsByDishId(1L).size() > 0);
    }

    @Test
    public void save() {
        assertTrue(ingredientDao.save(testIngredientEntity));
    }

    @Test
    public void findById() {
        assertTrue(ingredientDao.findById(ingredientEntity.getId()).isPresent());
    }

    @Test
    public void update() {
        assertTrue(ingredientDao.update(ingredientEntity));
    }

    @Test
    public void delete() {
        assertFalse(ingredientDao.delete(testIngredientEntity.getId()));
    }

    private void setEntities() {
        ingredientEntity = IngredientEntity.builder()
                .withId(2L)
                .withName("Basil")
                .withImg("basil.png")
                .build();
        testIngredientEntity = IngredientEntity.builder()
                .withId(20L)
                .withName("Ice")
                .withImg("ice.png")
                .build();
    }
}