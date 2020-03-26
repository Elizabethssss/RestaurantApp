package com.restaurant.dao.impl;

import com.restaurant.dao.OrderDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.OrderEntity;
import com.restaurant.exception.DataBaseException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<OrderEntity> implements OrderDao {
    public static final String SAVE_QUERY = "INSERT INTO orders VALUES (DEFAULT, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE orders SET status = ?, created_at = ?, user_id = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM orders WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?;";
    public static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    public static final String FIND_BY_STATUS_AND_USER_ID_QUERY = "SELECT * FROM orders WHERE status = ? AND user_id = ?";

    public static final String SAVE_ORDER_DISH_QUERY = "INSERT INTO orders_dishes VALUES (DEFAULT, ?, ?);";
    public static final String DELETE_ORDER_DISH_QUERY = "DELETE FROM orders_dishes WHERE order_id= ? AND dish_id = ? LIMIT 1;";

    public OrderDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        return findAllByParam(userId, FIND_BY_USER_ID_QUERY);
    }

    @Override
    public Optional<OrderEntity> getOrderByStatusAndUserId(String status, Long userId) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_BY_STATUS_AND_USER_ID_QUERY)) {
            ps.setString(1, status);
            ps.setLong(2, userId);
            try(final ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return Optional.ofNullable(parseResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, FIND_BY_STATUS_AND_USER_ID_QUERY, e));
            throw new DataBaseException("Error in getting from db", e);
        }
        return Optional.empty();
    }


    @Override
    public void addDishToOrder(Long orderId, Long dishId) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(SAVE_ORDER_DISH_QUERY)) {
            ps.setLong(1, orderId);
            ps.setLong(2, dishId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, SAVE_ORDER_DISH_QUERY, e));
            throw new DataBaseException("Error in saving to db", e);
        }
    }

    @Override
    public void deleteOrderDishById(Long orderId, Long dishId, Integer quantity) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_DISH_QUERY)) {
            ps.setLong(1, orderId);
            ps.setLong(2, dishId);
            while (quantity != 0) {
                ps.executeUpdate();
                quantity--;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, DELETE_ORDER_DISH_QUERY, e));
            throw new DataBaseException("Error in deleting from db", e);
        }
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
    protected OrderEntity parseResultSet(ResultSet rs) throws SQLException {
        return OrderEntity.builder()
                .withId(rs.getLong("id"))
                .withStatus(OrderStatus.valueOf(rs.getString("status")))
                .withCreatedAt(rs.getDate("created_at").toLocalDate())
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderEntity object) throws SQLException {
        statement.setString(1, object.getStatus().name());
        statement.setDate(2, Date.valueOf(object.getCreatedAt()));
        statement.setLong(3, object.getUserEntity().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(4, object.getId());
    }
}
