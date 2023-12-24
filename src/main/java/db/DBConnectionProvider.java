package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private DBConnectionProvider() {
    }

    private static final DBConnectionProvider provider = null;
    private Connection connection;
    private final String DB_URL = "jdbc:mysql://localhost:3306/eshop1";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";

    public static DBConnectionProvider getInstance() {
        if (provider == null) {
            return new DBConnectionProvider();
        }
        return provider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
