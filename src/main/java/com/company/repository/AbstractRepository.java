package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractRepository {

    private static final String connectionUrl = "jdbc:mysql://srv4-mysql.bedrockhost.pl:3306/s3140_MyHorse?useSSL=false";
    private static final String username = "u3140_1hFsuG3YtT";
    private static final String password = ".D0uAG7rTvZ6TMRIPhtgzj=f";
    protected static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, username, password);
    }

}
