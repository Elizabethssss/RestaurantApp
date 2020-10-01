package com.restaurant.dao.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.DishType;
import com.restaurant.entity.DishEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.testng.Assert.*;

public class DishDaoImplTestNG {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";
    private static final Page PAGE = new Page(0, 5);
    private static final Long ID = 1L;

    private HikariCPManager manager;
    private DishEntity dishEntity;
    private DishEntity testDishEntity;
    private DishDao dishDao;

    @BeforeMethod
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
            dishDao = new DishDaoImpl(manager);
            setEntities();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDishesByOrderId() {
        assertTrue(dishDao.getDishesByOrderId(8L).size() > 0);
    }

    @Test
    public void getDishesByLunchId() {
        assertTrue(dishDao.getDishesByLunchId(ID).size() > 0);
    }

    @Test
    public void findAll() {
        assertTrue(dishDao.findAll(DishType.SNACK.name(), PAGE).size() > 0);
    }

    @Test
    public void count() {
        assertTrue(dishDao.count(DishType.SNACK.name()) > 0);
    }

    @Test
    public void save() {
        assertTrue(dishDao.save(testDishEntity));
    }

    @Test
    public void findById() {
        assertTrue(dishDao.findById(dishEntity.getId()).isPresent());
    }

    @Test
    public void update() {
        assertTrue(dishDao.update(dishEntity));
    }

    @Test
    public void delete() {
        assertFalse(dishDao.delete(testDishEntity.getId()));
    }

    private void setEntities() {
        dishEntity = DishEntity.builder()
                .withId(1L)
                .withName("Beef tartare")
                .withAbout("Hand-chop beef and serve it up with an array of traditional goodies like capers, chives," +
                        " shallots, and a runny egg yolk. But don’t stop there. Beef tartare can really be anything " +
                        "you want—that’s why it’s so amazing.")
                .withDishType(DishType.SNACK)
                .withPrice(265)
                .withWeight(160)
                .withImage("tartar_iz_goviadini.jpg")
                .build();
        testDishEntity = DishEntity.builder()
                .withId(5L)
                .withName("Ice cream")
                .withAbout("Chocolate ice cream")
                .withDishType(DishType.DESSERT)
                .withPrice(130)
                .withWeight(70)
                .withImage("chocolate_ice_cream.jpg")
                .build();
    }

    @AfterMethod
    public void tearDown() {
        manager.shutdown();
    }
}