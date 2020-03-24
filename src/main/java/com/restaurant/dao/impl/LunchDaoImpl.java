package com.restaurant.dao.impl;

import com.restaurant.dao.LunchDao;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.domain.LunchType;
import com.restaurant.entity.LunchEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LunchDaoImpl extends AbstractDao<LunchEntity> implements LunchDao {
    public static final String SAVE_QUERY = "INSERT INTO lunches VALUES (DEFAULT, ?, ?, ?, ?, ?);";
    public static final String UPDATE_QUERY = "UPDATE lunches SET description = ?, time_from = ?," +
            " time_to = ?, img = ?, lunch_type = ? WHERE id= ?;";
    public static final String DELETE_QUERY = "DELETE FROM lunches WHERE id= ?;";
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM lunches WHERE id = ?;";

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
    protected LunchEntity parseResultSet(ResultSet rs) throws SQLException {
        return LunchEntity.builder()
                .withId(rs.getLong("id"))
                .withDescription(rs.getString("description"))
                .withTimeFrom(rs.getDate("time_from").toLocalDate())
                .withTimeTo(rs.getDate("time_to").toLocalDate())
                .withImg(rs.getString("img"))
                .withLunchType(LunchType.valueOf(rs.getString("lunch_type")))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, LunchEntity object) throws SQLException {
        statement.setString(1, object.getDescription());
        statement.setDate(2, Date.valueOf(object.getTimeFrom()));
        statement.setDate(3, Date.valueOf(object.getTimeTo()));
        statement.setString(4, object.getImg());
        statement.setString(5, object.getLunchType().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, LunchEntity object) throws SQLException {
        prepareStatementForInsert(statement, object);
        statement.setLong(6, object.getId());
    }
}
