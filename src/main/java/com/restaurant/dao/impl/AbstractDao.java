package com.restaurant.dao.impl;

import com.restaurant.dao.GenericDao;
import com.restaurant.dao.Page;
import com.restaurant.dao.connection.HikariCPManager;
import com.restaurant.exception.DataBaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    protected static final String ERROR_MESSAGE = "Can't process sql '%s'; Error: %s";

    private final HikariCPManager connector;

    public AbstractDao(HikariCPManager connector) {
        this.connector = connector;
    }

    public HikariCPManager getConnector() {
        return connector;
    }

    protected boolean save(T object, String query) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            prepareStatementForInsert(ps, object);
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in saving to db", e);
        }
        return false;
    }

    protected boolean update(T object, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            prepareStatementForUpdate(ps, object);
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in updating db", e);
        }
        return false;
    }

    protected boolean delete(long id, String query) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            if(id != 0 && ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in deleting from db", e);
        }
        return false;
    }

    protected Optional<T> findById(long id, String query) {
        return findByParam(id, query);
    }

    protected <P> Optional<T> findByParam(P param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, param);
            try(final ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return Optional.ofNullable(parseResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in getting by parameter from db", e);
        }
        return Optional.empty();
    }

    protected <P> List<T> findAllByParam(P param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, param);
            try(final ResultSet rs = ps.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSet(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in getting all by parameter from db", e);
        }
    }

    protected List<T> findAll(Object obj, Page page, String query) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, obj);
            ps.setInt(2, page.getRecordNumber());
            ps.setInt(3, page.getPageNumber());
            try(final ResultSet rs = ps.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(parseResultSet(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in getting all from db", e);
        }
    }

    protected int count(Object obj, String query) {
        try(Connection connection = connector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, obj);
            try(final ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt(1);
                }
                return count;
            }
         } catch (SQLException e) {
            LOGGER.error(String.format(ERROR_MESSAGE, query, e));
            throw new DataBaseException("Error in counting elements in db", e);
        }
    }


    protected abstract T parseResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement preparedStatement, T object) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T object) throws SQLException;
}
