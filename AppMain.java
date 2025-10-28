package main;

import db.DBUtil;
import view.LoginFrame;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class AppMain {
    public static void main(String[] args) {
        // Test database connection (MySQL)
		Connection conn = DBUtil.getConnection();
		if (conn != null) {
		    System.out.println("✅ Database connected successfully!");
		} else {
		    System.out.println("❌ Database connection failed!");
		}

		// Launch the login window
		SwingUtilities.invokeLater(() -> {
		    new LoginFrame().setVisible(true);
		});
    }
}
