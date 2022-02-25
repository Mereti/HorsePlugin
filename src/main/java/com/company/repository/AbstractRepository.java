package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractRepository {

    private static final String connectionUrl = "jdbc:mysql://145.239.91.58:3306/s4_myhorse?serverTimezone=UTC";
    private static final String username = "u4_ebUAEPLkzs";
    private static final String password = "c^9=8p1XeTX!.B!845G67xI4";

    protected static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, username, password);
    }

}
