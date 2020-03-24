package com.restaurant.dao.impl;

import com.restaurant.dao.DishDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.Dish;
import com.restaurant.domain.DishType;
import com.restaurant.entity.DishEntity;
import com.restaurant.exception.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DishDaoImpl extends AbstractDao<DishEntity> implements DishDao {
    public static final String SAVE_QUERY = "INSERT INTO dishes VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE dishes SET name = ?, about = ?, dish_type = ?," +
            " price = ?, weight = ?, img = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM dishes WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM dishes WHERE id = ?;";
    public static final String FIND_PAGE_BY_DISH_TYPE_QUERY = "SELECT * FROM dishes WHERE dish_type = ? LIMIT ? OFFSET ?;";
    public static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM dishes WHERE dish_type = ?;";

    public static final String FIND_BY_ORDER_ID_QUERY = "select * from dishes left join orders_dishes on " +
            "orders_dishes.dish_id = dishes.id where order_id = ?;";

    public DishDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public Map<DishEntity, Integer> getDishesByOrderId(Long orderId) {
        Map<DishEntity, Integer> entityMap = new LinkedHashMap<>();
        try (Connection connection = getConnector().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ORDER_ID_QUERY)) {
            ps.setObject(1, orderId);
            try(final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DishEntity entity = parseResultSet(rs);
                    if(entityMap.isEmpty() || entityMap.keySet().stream()
                            .noneMatch(dishEntity -> dishEntity.getId().equals(entity.getId()))) {
                        entityMap.put(entity, 1);
                    }
                    else {
                        for (Map.Entry<DishEntity, Integer> entry : entityMap.entrySet()) {
                            if (entry.getKey().getId().equals(entity.getId())) {
                                entityMap.replace(entry.getKey(), entry.getValue() + 1);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, FIND_BY_ORDER_ID_QUERY, e));
            throw new DataBaseException("Error in getting by parameter from db", e);
        }
        return entityMap;
    }

    @Override
    public List<DishEntity> findAll(Object obj, Page page) {
        return findAll(obj, page, FIND_PAGE_BY_DISH_TYPE_QUERY);
    }

    @Override
    public int count(Object obj) {
        return count(obj, COUNT_ALL_QUERY);
    }

    @Override
    public boolean save(DishEntity object) {
        return save(object, SAVE_QUERY);
    }

    @Override
    public Optional<DishEntity> findById(long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    public boolean update(DishEntity object) {
        return update(object, UPDATE_QUERY);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, DELETE_QUERY);
    }

    @Override
    protected DishEntity parseResultSet(ResultSet rs) throws SQLException {
        return DishEntity.builder()
                .withId(rs.getLong("dishes.id"))
                .withName(rs.getString("name"))
                .withAbout(rs.getString("about"))
                .withDishType(DishType.valueOf(rs.getString("dish_type")))
                .withPrice(rs.getDouble("price"))
                .withWeight(rs.getInt("weight"))
                .withImage(rs.getString("img"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, DishEntity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getAbout());
        statement.setString(3, object.getDishType().name());
        statement.setDouble(4, object.getPrice());
        statement.setInt(5, object.getWeight());
        statement.setString(6, object.getImg());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, DishEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(7, object.getId());
    }
}
