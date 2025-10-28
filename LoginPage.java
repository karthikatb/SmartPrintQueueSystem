package com.spqs.view;

import com.spqs.controller.UserController;
import com.spqs.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("College ID:"));
        JTextField collegeIdField = new JTextField();
        panel.add(collegeIdField);

        panel.add(new JLabel("Password:"));
        JPasswordField passField = new JPasswordField();
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        panel.add(new JLabel()); // spacing
        panel.add(loginBtn);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        // ---------------------
        // Login button action
        // ---------------------
        loginBtn.addActionListener(e -> {
            String collegeId = collegeIdField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (collegeId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            try {
                UserController controller = new UserController();
                User user = controller.loginUser(collegeId, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(this, "Welcome " + user.getName());

                    dispose(); // close login window
                    if (user.getRole().equalsIgnoreCase("Student")) {
                        new StudentHomePage(user.getName());
                    } else if (user.getRole().equalsIgnoreCase("Operator")) {
                        new OperatorDashboard(user.getName());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid College ID or Password!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
