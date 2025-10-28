package controller;

import dao.UserDAO;
import model.User;
import view.*;

import javax.swing.*;
import java.sql.SQLException;

public class UserController {
    private static final UserDAO userDAO = new UserDAO();

    public static void register(String email, String collegeId, String password, String dept, int year, String role, JFrame frame) {
        try {
            User user = new User(email, collegeId, password, dept, year, role);
            userDAO.registerUser(user);
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            frame.dispose();
            new LoginFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void login(String email, String password, JFrame frame) {
        try {
            User user = userDAO.login(email, password);
            if (user == null) {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Welcome " + user.getRole().toUpperCase() + "!");
                frame.dispose();
                if (user.getRole().equals("student")) {
                    new StudentDashboard(user);
                } else {
                    new OperatorDashboard();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
