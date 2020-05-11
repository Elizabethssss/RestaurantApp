package com.restaurant.dao.impl;

import com.restaurant.dao.LunchDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.LunchType;
import com.restaurant.entity.DishEntity;
import com.restaurant.entity.LunchEntity;
import com.restaurant.exception.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of Lunch DAO
 */

public class LunchDaoImpl extends AbstractDao<LunchEntity> implements LunchDao {
    public static final String SAVE_QUERY = "INSERT INTO lunches VALUES (DEFAULT, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE lunches SET name = ?, description = ?, img = ?, lunch_type = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM lunches WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT lunches.*, dishes.* FROM lunches LEFT JOIN dishes_lunches dl on " +
            "lunches.id = dl.lunch_id LEFT JOIN dishes ON dl.dish_id = dishes.id WHERE lunches.id = ?;";
    public static final String FIND_PAGE_QUERY = "SELECT * FROM lunches  WHERE lunch_type = ? LIMIT ? OFFSET ?;";
    public static final String COUNT_ALL_QUERY = "SELECT COUNT(*) FROM lunches WHERE lunch_type = ?;";

    public static final String FIND_BY_ORDER_ID_QUERY = "select * from lunches right join orders_lunches ol" +
            " on lunches.id = ol.lunch_id where order_id = ?;";

    public LunchDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public boolean save(LunchEntity object) {
        return save(object, SAVE_QUERY);
    }

    @Override
    public Optional<LunchEntity> findById(long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    public boolean update(LunchEntity object) {
        return update(object, UPDATE_QUERY);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, DELETE_QUERY);
    }

    @Override
    public List<LunchEntity> findAll(Object obj, Page page) {
        return findAll(obj, page, FIND_PAGE_QUERY);
    }

    @Override
    public int count(Object obj) {
        return count(obj, COUNT_ALL_QUERY);
    }

    @Override
    public Map<LunchEntity, Integer> getLunchesByOrderId(Long orderId) {
        Map<LunchEntity, Integer> entityMap = new LinkedHashMap<>();
        try (Connection connection = getConnector().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ORDER_ID_QUERY)) {
            ps.setObject(1, orderId);
            try(final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LunchEntity entity = parseResultSet(rs);
                    if(entityMap.isEmpty() || entityMap.keySet().stream()
                            .noneMatch(lunchEntity -> lunchEntity.getId().equals(entity.getId()))) {
                        entityMap.put(entity, 1);
                    }
                    else {
                        for (Map.Entry<LunchEntity, Integer> entry : entityMap.entrySet()) {
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
    protected LunchEntity parseResultSet(ResultSet rs) throws SQLException {
        List<DishEntity> dishEntities = new ArrayList<>();
        return LunchEntity.builder()
                .withId(rs.getLong("lunches.id"))
                .withName(rs.getString("lunches.name"))
                .withDescription(rs.getString("description"))
                .withImg(rs.getString("lunches.img"))
                .withLunchType(LunchType.valueOf(rs.getString("lunch_type")))
                .withDishEntities(dishEntities)
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, LunchEntity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
        statement.setString(3, object.getImg());
        statement.setString(4, object.getLunchType().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, LunchEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(5, object.getId());
    }
}
