package com.restaurant.dao.impl;

import com.restaurant.dao.UserDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.Role;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    public static final String SAVE_QUERY = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ?, role_id = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM users WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?;";
    public static final String FIND_BY_EMAIL_QUERY = "select * from users where email = ?;";
//    public static final String FIND_BY_EMAIL_QUERY = "select * from users left join orders on users.id = orders.user_id where email = ?;";

    public UserDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    public boolean save(UserEntity object) {
        return save(object, SAVE_QUERY);
    }

    @Override
    public Optional<UserEntity> findById(long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    public boolean update(UserEntity object) {
        return update(object, UPDATE_QUERY);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, DELETE_QUERY);
    }

//    @Override
//    protected UserEntity parseResultSet(ResultSet rs) throws SQLException {
//        return UserEntity.builder()
//                .withId(rs.getLong("id"))
//                .withUsername(rs.getString("username"))
//                .withEmail(rs.getString("email"))
//                .withPassword(rs.getString("password"))
//                .withRole(Role.valueOf(rs.getString("role")))
//                .build();
//    }

    @Override
    protected UserEntity parseResultSet(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getLong("users.id"))
                .withUsername(resultSet.getString("users.username"))
                .withEmail(resultSet.getString("users.email"))
                .withPassword(resultSet.getString("users.password"))
                .withRole(Role.valueOf(resultSet.getString("users.role")))
//                .withOrders(extractOrdersFromResultSet(resultSet))
                .build();
    }

    public List<OrderEntity> extractOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        List<OrderEntity> orderEntities = new ArrayList<>();
        do {
            OrderEntity orderEntity = OrderEntity.builder()
                    .withId(resultSet.getLong("orders.id"))
                    .withStatus(OrderStatus.valueOf(resultSet.getString("status")))
                    .withCreatedAt(resultSet.getDate("created_at").toLocalDate())
                    .build();
            orderEntities.add(orderEntity);
        } while (resultSet.next());
        return orderEntities;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserEntity object) throws SQLException {
        statement.setString(1, object.getUsername());
        statement.setString(2, object.getEmail());
        statement.setString(3, object.getPassword());
        statement.setString(4, object.getRole().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(5, object.getId());
    }
}
