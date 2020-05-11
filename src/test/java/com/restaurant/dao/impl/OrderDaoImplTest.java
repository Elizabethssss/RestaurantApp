package com.restaurant.dao.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.LunchDao;
import com.restaurant.dao.OrderDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    private static final String DB_PROP_PATH = "h2_test_db";
    private static final String SCHEMA_PATH = "src/test/resources/schema.sql";
    private static final String DATA_PATH = "src/test/resources/data.sql";
    private static final Page PAGE = new Page(0, 5);
    private static final Long ID = 2L;

    private HikariCPManager manager;
    private OrderEntity orderEntity;
    private OrderEntity testOrderEntity;
    private OrderDao orderDao;
    private LunchDao lunchDao;
    private DishDao dishDao;

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
            orderDao = new OrderDaoImpl(manager);
            lunchDao = new LunchDaoImpl(manager);
            dishDao = new DishDaoImpl(manager);
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
    public void getOrdersByUserId() {
        assertTrue(orderDao.getOrdersByUserId(ID).size() > 0);
    }

    @Test
    public void getOrderByStatusAndUserId() {
        assertTrue(orderDao.getOrderByStatusAndUserId(orderEntity.getStatus().name(), ID).isPresent());
    }

    @Test
    public void addDishToOrder() {
        final Map<DishEntity, Integer> dishesByOrderId = dishDao.getDishesByOrderId(orderEntity.getId());
        final int size = dishesByOrderId.values().stream().reduce(0, Integer::sum);
        orderDao.addDishToOrder(orderEntity.getId(), ID);
        final Map<DishEntity, Integer> dishesByOrderId1 = dishDao.getDishesByOrderId(orderEntity.getId());
        final int size1 = dishesByOrderId1.values().stream().reduce(0, Integer::sum);
        assertNotEquals(size, size1);
    }

    @Test
    public void updateOrderStatus() {
        final OrderStatus orderEntityStatus = orderEntity.getStatus();
        orderDao.updateOrderStatus(orderEntity.getId(), testOrderEntity.getStatus().name());
        final OrderEntity orderEntity = orderDao.findById(this.orderEntity.getId()).orElse(OrderEntity.builder().build());
        final OrderStatus orderStatus = orderEntity.getStatus();
        assertNotEquals(orderEntityStatus, orderStatus);
    }

    @Test
    public void updateOrderCostAndStatus() {
        final OrderStatus orderEntityStatus = orderEntity.getStatus();
        final int orderEntityCost = orderEntity.getCost();
        orderDao.updateOrderCostAndStatus(testOrderEntity.getCost(), orderEntity.getId());
        final OrderEntity orderEntity = orderDao.findById(this.orderEntity.getId()).orElse(OrderEntity.builder().build());
        final OrderStatus orderStatus = orderEntity.getStatus();
        final int orderCost = orderEntity.getCost();
        assertNotEquals(orderEntityStatus, orderStatus);
        assertNotEquals(orderEntityCost, orderCost);
    }

    @Test
    public void getOrdersByStatus() {
        assertTrue(orderDao.getOrdersByStatus(orderEntity.getStatus().name(), PAGE).size() > 0);
    }

    @Test
    public void countByStatus() {
        assertTrue(orderDao.countByStatus(orderEntity.getStatus().name()) > 0);
    }

    @Test
    public void addLunchToOrder() {
        final Map<LunchEntity, Integer> lunchesByOrderId = lunchDao.getLunchesByOrderId(orderEntity.getId());
        final int size = lunchesByOrderId.values().stream().reduce(0, Integer::sum);
        orderDao.addLunchToOrder(orderEntity.getId(), ID);
        final Map<LunchEntity, Integer> lunchesByOrderId1 = lunchDao.getLunchesByOrderId(orderEntity.getId());
        final int size1 = lunchesByOrderId1.values().stream().reduce(0, Integer::sum);
        assertNotEquals(size, size1);
    }

    @Test
    public void deleteOrderDishById() {
        final Map<DishEntity, Integer> dishesByOrderId = dishDao.getDishesByOrderId(orderEntity.getId());
        final int size = dishesByOrderId.values().stream().reduce(0, Integer::sum);
        orderDao.deleteOrderDishById(orderEntity.getId(), ID, 1);
        final Map<DishEntity, Integer> dishesByOrderId1 = dishDao.getDishesByOrderId(orderEntity.getId());
        final int size1 = dishesByOrderId1.values().stream().reduce(0, Integer::sum);
        assertNotEquals(size, size1);
    }

    @Test
    public void deleteOrderLunchById() {
        final Map<LunchEntity, Integer> lunchesByOrderId = lunchDao.getLunchesByOrderId(orderEntity.getId());
        final int size = lunchesByOrderId.values().stream().reduce(0, Integer::sum);
        orderDao.deleteOrderLunchById(orderEntity.getId(), ID, 1);
        final Map<LunchEntity, Integer> lunchesByOrderId1 = lunchDao.getLunchesByOrderId(orderEntity.getId());
        final int size1 = lunchesByOrderId1.values().stream().reduce(0, Integer::sum);
        assertNotEquals(size, size1);
    }

    @Test
    public void findAll() {
        assertTrue(orderDao.findAll(ID, PAGE).size() > 0);
    }

    @Test
    public void count() {
        assertTrue(orderDao.count(ID) > 0);
    }

    @Test
    public void save() {
        assertTrue(orderDao.save(testOrderEntity));
    }

    @Test
    public void findById() {
        assertTrue(orderDao.findById(orderEntity.getId()).isPresent());
    }

    @Test
    public void update() {
        assertTrue(orderDao.update(orderEntity));
    }

    @Test
    public void delete() {
        assertFalse(orderDao.delete(testOrderEntity.getId()));
    }

    private void setEntities() {
        orderEntity = OrderEntity.builder()
                .withId(8L)
                .withStatus(OrderStatus.PAYED)
                .withCost(1020)
                .withCreatedAt(LocalDate.of(2020, 3, 27))
                .withUserEntity(UserEntity.builder().withId(2L).build())
                .build();
        testOrderEntity = OrderEntity.builder()
                .withId(3L)
                .withStatus(OrderStatus.CONFIRMED)
                .withCost(10420)
                .withCreatedAt(LocalDate.of(2020,4, 8))
                .withUserEntity(UserEntity.builder().withId(3L).build())
                .build();
    }
}