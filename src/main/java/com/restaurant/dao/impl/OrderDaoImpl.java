package com.restaurant.dao.impl;

import com.restaurant.dao.OrderDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;
import com.restaurant.exception.DataBaseException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of Order DAO
 */

public class OrderDaoImpl extends AbstractDao<OrderEntity> implements OrderDao {
    public static final String SAVE_QUERY = "INSERT INTO orders VALUES (DEFAULT, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE orders SET status = ?, cost = ?, created_at = ?, user_id = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM orders WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?;";
    public static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    public static final String FIND_BY_STATUS_AND_USER_ID_QUERY = "SELECT * FROM orders WHERE status = ? AND user_id = ?";
    public static final String FIND_PAGE_QUERY = "SELECT * FROM orders WHERE user_id = ? AND status != 'FORMED' ORDER BY id DESC LIMIT ? OFFSET ?;";
    public static final String UPDATE_STATUS_QUERY = "UPDATE orders SET status = ? WHERE id= ?;";
    public static final String UPDATE_COST_AND_STATUS_QUERY = "UPDATE orders SET status = 'SENT', cost = ? WHERE id= ?;";
    public static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM orders WHERE status != 'FORMED' AND user_id = ?;";
    public static final String FIND_PAGE_BY_STATUS_QUERY = "SELECT orders.*, users.email FROM orders LEFT JOIN users ON " +
            "orders.user_id = users.id WHERE status = ? ORDER BY orders.id DESC LIMIT ? OFFSET ?;";
    public static final String COUNT_BY_STATUS_QUERY = "SELECT COUNT(*) FROM orders WHERE status = ?;";

    public static final String SAVE_ORDER_DISH_QUERY = "INSERT INTO orders_dishes VALUES (DEFAULT, ?, ?);";
    public static final String SAVE_ORDER_LUNCH_QUERY = "INSERT INTO orders_lunches VALUES (DEFAULT, ?, ?);";
    public static final String DELETE_ORDER_DISH_QUERY = "DELETE FROM orders_dishes WHERE order_id= ? AND dish_id = ? LIMIT 1;";
    public static final String DELETE_ORDER_LUNCH_QUERY = "DELETE FROM orders_lunches WHERE order_id= ? AND lunch_id = ? LIMIT 1;";

    public OrderDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        return findAllByParam(userId, FIND_BY_USER_ID_QUERY);
    }

    @Override
    public Optional<OrderEntity> getOrderByStatusAndUserId(String status, Long userId) {
        return findByTwoParams(status, userId, FIND_BY_STATUS_AND_USER_ID_QUERY);
    }

    @Override
    public void addDishToOrder(Long orderId, Long dishId) {
        executeDataUpdateWithTwoParams(orderId, dishId, SAVE_ORDER_DISH_QUERY);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        executeDataUpdateWithTwoParams(status, orderId, UPDATE_STATUS_QUERY);
    }

    @Override
    public void updateOrderCostAndStatus(int totalPrice, Long orderId) {
        executeDataUpdateWithTwoParams(totalPrice, orderId, UPDATE_COST_AND_STATUS_QUERY);
    }

    @Override
    public List<OrderEntity> getOrdersByStatus(String status, Page page) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_PAGE_BY_STATUS_QUERY)) {
            ps.setObject(1, status);
            ps.setInt(2, page.getRecordNumber());
            ps.setInt(3, page.getPageNumber());
            try(final ResultSet rs = ps.executeQuery()) {
                List<OrderEntity> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSetWithUser(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, FIND_PAGE_BY_STATUS_QUERY, e));
            throw new DataBaseException("Error in getting all from db", e);
        }
    }

    @Override
    public int countByStatus(String status) {
        return count(status, COUNT_BY_STATUS_QUERY);
    }

    @Override
    public void addLunchToOrder(Long orderId, long lunchId) {
        executeDataUpdateWithTwoParams(orderId, lunchId, SAVE_ORDER_LUNCH_QUERY);
    }

    @Override
    public void deleteOrderDishById(Long orderId, Long dishId, Integer quantity) {
        executeDataUpdateSeveralTimesWithTwoParams(orderId, dishId, quantity, DELETE_ORDER_DISH_QUERY);
    }

    @Override
    public void deleteOrderLunchById(Long orderId, Long lunchId, int quantity) {
        executeDataUpdateSeveralTimesWithTwoParams(orderId, lunchId, quantity, DELETE_ORDER_LUNCH_QUERY);
    }

    @Override
    public boolean save(OrderEntity object) {
        return save(object, SAVE_QUERY);
    }

    @Override
    public Optional<OrderEntity> findById(long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    public boolean update(OrderEntity object) {
        return update(object, UPDATE_QUERY);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, DELETE_QUERY);
    }

    @Override
    public List<OrderEntity> findAll(Object obj, Page page) {
        return findAll(obj, page, FIND_PAGE_QUERY);
    }

    @Override
    public int count(Object obj) {
        return count(obj, COUNT_ALL_QUERY);
    }

    @Override
    protected OrderEntity parseResultSet(ResultSet rs) throws SQLException {
        return OrderEntity.builder()
                .withId(rs.getLong("id"))
                .withStatus(OrderStatus.valueOf(rs.getString("status")))
                .withCost(rs.getInt("cost"))
                .withCreatedAt(rs.getDate("created_at").toLocalDate())
                .withUserEntity(UserEntity.builder().withId(rs.getLong("user_id")).build())
                .build();
    }

    private OrderEntity parseResultSetWithUser(ResultSet rs) throws SQLException {
        return OrderEntity.builder()
                .withId(rs.getLong("id"))
                .withStatus(OrderStatus.valueOf(rs.getString("status")))
                .withCost(rs.getInt("cost"))
                .withCreatedAt(rs.getDate("created_at").toLocalDate())
                .withUserEntity(UserEntity.builder().withId(rs.getLong("user_id"))
                        .withEmail(rs.getString("email")).build())
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderEntity object) throws SQLException {
        statement.setString(1, object.getStatus().name());
        statement.setInt(2, object.getCost());
        statement.setDate(3, Date.valueOf(object.getCreatedAt()));
        statement.setLong(4, object.getUserEntity().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(5, object.getId());
    }
}
