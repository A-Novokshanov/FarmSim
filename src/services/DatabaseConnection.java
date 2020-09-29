package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:sqlite::resource:gamedatabase.db";

    /***
     * Establishes connection to database
     * @return A stable connection to the database.
     */
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

