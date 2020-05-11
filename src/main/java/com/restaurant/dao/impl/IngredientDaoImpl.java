package com.restaurant.dao.impl;

import com.restaurant.dao.IngredientDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.entity.IngredientEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of Ingredient DAO
 */

public class IngredientDaoImpl extends AbstractDao<IngredientEntity> implements IngredientDao {
    public static final String SAVE_QUERY = "INSERT INTO ingredients VALUES (DEFAULT, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE ingredients SET name = ?, img = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM ingredients WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM ingredients WHERE id = ?;";
    public static final String FIND_BY_DISH_ID_QUERY = "select * from ingredients left join dishes_ingredients " +
            "on ingredients.id = dishes_ingredients.ingredient_id where dishes_ingredients.dish_id = ?;";

    public IngredientDaoImpl(HikariCPManager connector) {
        super(connector);
    }

    @Override
    public List<IngredientEntity> findIngredientsByDishId(Long dishId) {
        return findAllByParam(dishId, FIND_BY_DISH_ID_QUERY);
    }

    @Override
    public boolean save(IngredientEntity object) {
        return save(object, SAVE_QUERY);
    }

    @Override
    public Optional<IngredientEntity> findById(long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    public boolean update(IngredientEntity object) {
        return update(object, UPDATE_QUERY);
    }

    @Override
    public boolean delete(long id) {
        return delete(id, DELETE_QUERY);
    }

    @Override
    protected IngredientEntity parseResultSet(ResultSet rs) throws SQLException {
        return IngredientEntity.builder()
                .withId(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withImg(rs.getString("img"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, IngredientEntity object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getImg());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, IngredientEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(3, object.getId());
    }
}
