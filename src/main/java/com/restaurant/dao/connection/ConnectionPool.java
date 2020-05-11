package com.restaurant.dao.connection;

import java.sql.Connection;

/**
 * Manages a data source connection.
 *
 * @see Connection
 */

public interface ConnectionPool {

    /**
     * Receives connection from pool
     *
     * @return received connection
     */
    Connection getConnection();

    /**
     * Closes connection.
     */
    void shutdown();
}
