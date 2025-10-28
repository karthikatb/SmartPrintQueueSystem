package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/smart_print_db";
    private static final String USER = "root";  // your MySQL username
    private static final String PASSWORD = "karthika_2006";  // your MySQL password (if any)

    // Private constructor to prevent object creation
    private DBUtil() {}

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database Successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found. Please add the connector JAR to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed. Check URL/username/password.");
            e.printStackTrace();
        }

        return conn;
    }
}
