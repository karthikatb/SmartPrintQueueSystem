package com.spqs.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage {

    public RegisterPage() {
        JFrame frame = new JFrame("Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Name
        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        // Email
        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        // College ID
        panel.add(new JLabel("College ID:"));
        JTextField collegeField = new JTextField();
        panel.add(collegeField);

        // Password
        panel.add(new JLabel("Password:"));
        JPasswordField passField = new JPasswordField();
        panel.add(passField);

        // Department
        panel.add(new JLabel("Department:"));
        JTextField deptField = new JTextField();
        panel.add(deptField);

        // Year of Study
        panel.add(new JLabel("Year of Study:"));
        String[] years = {"1", "2", "3", "4"};
        JComboBox<String> yearCombo = new JComboBox<>(years);
        panel.add(yearCombo);

        // Role selection
        panel.add(new JLabel("Role:"));
        JPanel rolePanel = new JPanel(new FlowLayout());
        JRadioButton studentBtn = new JRadioButton("Student");
        JRadioButton operatorBtn = new JRadioButton("Operator");
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(studentBtn);
        roleGroup.add(operatorBtn);
        rolePanel.add(studentBtn);
        rolePanel.add(operatorBtn);
        panel.add(rolePanel);

        // ---------------------------
        // Buttons: Login and Register
        // ---------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);

        panel.add(new JLabel()); // Empty label for spacing
        panel.add(buttonPanel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // ---------------------------
        // ActionListener for Login
        // ---------------------------
        loginBtn.addActionListener(e -> {
            new LoginPage();
            frame.dispose(); // Close the registration page
        });

        // ---------------------------
        // ActionListener for Register
        // ---------------------------
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String collegeId = collegeField.getText().trim();
                String password = new String(passField.getPassword()).trim();
                String department = deptField.getText().trim();
                String year = (String) yearCombo.getSelectedItem();
                String role = studentBtn.isSelected() ? "Student" : operatorBtn.isSelected() ? "Operator" : "";

                // ---------------------------
                // VALIDATION SECTION
                // ---------------------------
                if (name.isEmpty() || email.isEmpty() || collegeId.isEmpty() ||
                    password.isEmpty() || department.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "All fields must be filled before submitting.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Email validation
                if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a valid email address (e.g., user@example.com).",
                            "Invalid Email",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!name.matches("^[A-Za-z ]+$")) {
                    JOptionPane.showMessageDialog(frame,
                            "Name must be in characters",
                            "Invalid Name",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Password validation
                if (password.length() < 4) {
                    JOptionPane.showMessageDialog(frame,
                            "Password must be at least 4 characters long.",
                            "Weak Password",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // College ID validation
                if (!collegeId.matches("[A-Za-z0-9]+")) {
                    JOptionPane.showMessageDialog(frame,
                            "College ID must contain only letters and numbers.",
                            "Invalid College ID",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Department validation
                if (!department.matches("^[A-Za-z ]+$")) {
                    JOptionPane.showMessageDialog(frame,
                            "Department name should contain only alphabets.",
                            "Invalid Department",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ---------------------------
                // Save user to DB if valid
                // ---------------------------
                try {
                    java.sql.Connection conn = com.spqs.util.DBConnection.getConnection();
                    String sql = "INSERT INTO users (name, email, college_id, password, department, year_of_study, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setString(2, email);
                    pst.setString(3, collegeId);
                    pst.setString(4, password);
                    pst.setString(5, department);
                    pst.setInt(6, Integer.parseInt(year));
                    pst.setString(7, role);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Registration successful for " + role + "!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    pst.close();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error saving data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}
