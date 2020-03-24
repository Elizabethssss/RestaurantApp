package com.restaurant.dao.connection;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    void shutdown();
}
