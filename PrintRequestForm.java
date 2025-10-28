package com.spqs.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.spqs.model.PrintRequest;
import com.spqs.util.DBConnection;

public class PrintRequestForm extends JPanel {
    private JTextField copiesField;
    private JComboBox<String> colorTypeBox, sideTypeBox, prepaidBox;
    private JLabel fileLabel;
    private File selectedFile;

    public PrintRequestForm(String userId) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("New Print Request Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // File upload
        JButton uploadButton = new JButton("Upload File");
        fileLabel = new JLabel("No file selected");
        uploadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                fileLabel.setText(selectedFile.getName());
            }
        });

        // Color type
        colorTypeBox = new JComboBox<>(new String[]{"Black & White", "Color"});

        // Side type
        sideTypeBox = new JComboBox<>(new String[]{"One Side", "Double Side"});

        // Copies
        copiesField = new JTextField();

        // Prepaid
        prepaidBox = new JComboBox<>(new String[]{"Yes", "No"});

        // Submit button
        JButton submitButton = new JButton("Submit Request");
        submitButton.addActionListener(e -> submitRequest(userId));

        // Add components to form
        formPanel.add(new JLabel("Select File:"));
        formPanel.add(uploadButton);
        formPanel.add(new JLabel("File Selected:"));
        formPanel.add(fileLabel);
        formPanel.add(new JLabel("Color Type:"));
        formPanel.add(colorTypeBox);
        formPanel.add(new JLabel("Side Type:"));
        formPanel.add(sideTypeBox);
        formPanel.add(new JLabel("Copies:"));
        formPanel.add(copiesField);
        formPanel.add(new JLabel("Prepaid:"));
        formPanel.add(prepaidBox);

        add(formPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void submitRequest(String studentId) {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select a file to upload!");
            return;
        }

        try {
            int copies = Integer.parseInt(copiesField.getText());
            String color = (String) colorTypeBox.getSelectedItem();
            String side = (String) sideTypeBox.getSelectedItem();
            String prepaid = (String) prepaidBox.getSelectedItem();

            PrintRequest req = new PrintRequest();
            req.setStudentId(Integer.parseInt(studentId));
            req.setDocumentName(selectedFile.getName());
            req.setCopies(copies);
            req.setSideOption(side);
            req.setColorOption(color);
            req.setPaymentOption(prepaid);
            req.setPaid(prepaid.equalsIgnoreCase("Yes"));
            req.setStatus("Pending");

            boolean success = dao.PrintRequestDAO.saveRequest(req);

            if (success)
                JOptionPane.showMessageDialog(this, "✅ Print Request Submitted!");
            else
                JOptionPane.showMessageDialog(this, "❌ Error saving request!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Copies must be a number!");
        }
    }

}
