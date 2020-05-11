package com.restaurant.dao.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.LunchDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.DishType;
import com.restaurant.domain.Lunch;
import com.restaurant.domain.LunchType;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class LunchDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";
    private static final Page PAGE = new Page(0, 5);
    private static final Long ID = 1L;

    private HikariCPManager manager;
    private LunchEntity lunchEntity;
    private LunchEntity testLunchEntity;
    private LunchDao lunchDao;

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
            lunchDao = new LunchDaoImpl(manager);
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
    public void findAll() {
        assertTrue(lunchDao.findAll(LunchType.BREAKFAST.name(), PAGE).size() > 0);
    }

    @Test
    public void count() {
        assertTrue(lunchDao.count(LunchType.BREAKFAST.name()) > 0);
    }

    @Test
    public void getLunchesByOrderId() {
        assertTrue(lunchDao.getLunchesByOrderId(11L).size() > 0);
    }

    @Test
    public void save() {
        assertTrue(lunchDao.save(testLunchEntity));
    }

    @Test
    public void findById() {
        assertTrue(lunchDao.findById(lunchEntity.getId()).isPresent());
    }

    @Test
    public void update() {
        assertTrue(lunchDao.update(lunchEntity));
    }

    @Test
    public void delete() {
        assertFalse(lunchDao.delete(testLunchEntity.getId()));
    }

    private void setEntities() {
        lunchEntity = LunchEntity.builder()
                .withId(1L)
                .withName("Classic Pancakes with Latte")
                .withDescription("A pancake is a flat cake, often thin and round, prepared from a starch-based batter " +
                        "that may contain eggs, milk and butter and cooked on a hot surface such as a griddle or frying " +
                        "pan, often frying with oil or butter.")
                .withImg("breakfast1.jpg")
                .withLunchType(LunchType.BREAKFAST)
                .build();
        testLunchEntity = LunchEntity.builder()
                .withId(5L)
                .withName("Ice cream")
                .withDescription("Chocolate with ice cream")
                .withImg("ice_lunch.jpg")
                .withLunchType(LunchType.LUNCH)
                .build();
    }
}