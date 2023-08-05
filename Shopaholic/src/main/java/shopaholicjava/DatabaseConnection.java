package shopaholicjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/shopaholic";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Thisis4mySQL";

    private static Connection connection;

    /**
     * Establishes a database connection.
     *
     * @return The Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Create a new connection using the DriverManager
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException If a database access error occurs.
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
