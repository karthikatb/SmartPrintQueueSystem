/*package com.spqs.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class StudentHomePage extends JFrame {

    private String studentName;

    public StudentHomePage(String studentName) {
        this.studentName = studentName;
        setupPage();
    }

    private void setupPage() {
        setTitle("Student Home - " + studentName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Profile tab
        JPanel profilePanel = new JPanel();
        profilePanel.add(new JLabel("Profile Page - Welcome " + studentName));
        tabbedPane.addTab("Profile", profilePanel);

        // History tab
        JPanel historyPanel = new JPanel();
        historyPanel.add(new JLabel("History of Print Requests"));
        tabbedPane.addTab("History", historyPanel);

        // New Print Request tab
        JPanel newPrintPanel = createNewPrintRequestPanel();
        tabbedPane.addTab("New Print Request", newPrintPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createNewPrintRequestPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel fileLabel = new JLabel("Select File:");
        JTextField fileField = new JTextField();
        JButton browseButton = new JButton("Browse");

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileField.setText(selectedFile.getAbsolutePath());
            }
        });

        JLabel colorLabel = new JLabel("Color Type:");
        String[] colorOptions = {"Black & White", "Color"};
        JComboBox<String> colorCombo = new JComboBox<>(colorOptions);

        JLabel sideLabel = new JLabel("Print Side:");
        String[] sideOptions = {"Single Side", "Double Side"};
        JComboBox<String> sideCombo = new JComboBox<>(sideOptions);

        JLabel copiesLabel = new JLabel("No. of Copies:");
        JTextField copiesField = new JTextField();

        JLabel prepaidLabel = new JLabel("Prepaid:");
        String[] prepaidOptions = {"Yes", "No"};
        JComboBox<String> prepaidCombo = new JComboBox<>(prepaidOptions);

        JButton submitButton = new JButton("Submit Request");

        // On submit button click
        submitButton.addActionListener(e -> {
            String filePath = fileField.getText();
            String color = (String) colorCombo.getSelectedItem();
            String side = (String) sideCombo.getSelectedItem();
            String prepaid = (String) prepaidCombo.getSelectedItem();
            String copies = copiesField.getText();

            if (filePath.isEmpty() || copies.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // TODO: Save to DB (via PrintRequestDAO)
                JOptionPane.showMessageDialog(this, "Print Request Submitted Successfully!");
            }
       // });
        try {
            int copiesCount = Integer.parseInt(copies);
            if (copiesCount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Number of copies must be greater than zero.",
                        "Invalid Copies",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (copiesCount > 100) { // Optional upper limit
                JOptionPane.showMessageDialog(this,
                        "You cannot request more than 100 copies at a time.",
                        "Limit Exceeded",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid numeric value for number of copies.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
   
        // ---------------------------
        // If all validation passes
        // ---------------------------
        JOptionPane.showMessageDialog(this,
                "Print Request Submitted Successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        // TODO: Save to DB (via PrintRequestDAO)
    });
   



        panel.add(fileLabel);
        panel.add(fileField);
        panel.add(new JLabel(""));
        panel.add(browseButton);
        panel.add(colorLabel);
        panel.add(colorCombo);
        panel.add(sideLabel);
        panel.add(sideCombo);
        panel.add(copiesLabel);
        panel.add(copiesField);
        panel.add(prepaidLabel);
        panel.add(prepaidCombo);
        panel.add(new JLabel(""));
        panel.add(submitButton);

        return panel;
    }

    public static void main(String[] args) {
       // new StudentHomePage("Parvathy");
    }
}
*/
package com.spqs.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class StudentHomePage extends JFrame {

    private String studentName;

    public StudentHomePage(String studentName) {
        this.studentName = studentName;
        setupPage();
    }

    private void setupPage() {
        setTitle("Student Home - " + studentName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ---------------------------
        // Profile tab
        // ---------------------------
        JPanel profilePanel = new JPanel();
        profilePanel.add(new JLabel("Profile Page - Welcome " + studentName));
        tabbedPane.addTab("Profile", profilePanel);

        // ---------------------------
        // History tab
        // ---------------------------
        JPanel historyPanel = new JPanel();
        historyPanel.add(new JLabel("History of Print Requests"));
        tabbedPane.addTab("History", historyPanel);

        // ---------------------------
        // New Print Request tab
        // ---------------------------
        JPanel newPrintPanel = createNewPrintRequestPanel();
        tabbedPane.addTab("New Print Request", newPrintPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createNewPrintRequestPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel fileLabel = new JLabel("Select File:");
        JTextField fileField = new JTextField();
        JButton browseButton = new JButton("Browse");

        // Browse button
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileField.setText(selectedFile.getAbsolutePath());
            }
        });

        JLabel colorLabel = new JLabel("Color Type:");
        String[] colorOptions = {"Black & White", "Color"};
        JComboBox<String> colorCombo = new JComboBox<>(colorOptions);

        JLabel sideLabel = new JLabel("Print Side:");
        String[] sideOptions = {"Single Side", "Double Side"};
        JComboBox<String> sideCombo = new JComboBox<>(sideOptions);

        JLabel copiesLabel = new JLabel("No. of Copies:");
        JTextField copiesField = new JTextField();

        JLabel prepaidLabel = new JLabel("Prepaid:");
        String[] prepaidOptions = {"Yes", "No"};
        JComboBox<String> prepaidCombo = new JComboBox<>(prepaidOptions);

        JButton submitButton = new JButton("Submit Request");

        // ---------------------------
        // Submit button logic
        // ---------------------------
        submitButton.addActionListener(e -> {
            String filePath = fileField.getText().trim();
            String color = (String) colorCombo.getSelectedItem();
            String side = (String) sideCombo.getSelectedItem();
            String prepaid = (String) prepaidCombo.getSelectedItem();
            String copies = copiesField.getText().trim();

            // Step 1: Check for empty fields
            if (filePath.isEmpty() || copies.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 2: Validate number of copies
            int copiesCount;
            try {
                copiesCount = Integer.parseInt(copies);

                if (copiesCount <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Number of copies must be greater than zero.",
                            "Invalid Copies",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (copiesCount > 100) { // Optional upper limit
                    JOptionPane.showMessageDialog(this,
                            "You cannot request more than 100 copies at a time.",
                            "Limit Exceeded",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid numeric value for number of copies.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 3: Optional file name validation
            String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
            if (!fileName.matches("[a-zA-Z0-9_.-]+")) {
                JOptionPane.showMessageDialog(this,
                        "File name should not contain special characters or spaces.",
                        "Invalid File Name",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 4: All validations passed
            JOptionPane.showMessageDialog(this,
                    "✅ Print Request Submitted Successfully!\n"
                            + "File: " + fileName + "\n"
                            + "Color: " + color + "\n"
                            + "Side: " + side + "\n"
                            + "Copies: " + copiesCount + "\n"
                            + "Prepaid: " + prepaid,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // TODO: Save to DB (via PrintRequestDAO)
        });

        // ---------------------------
        // Add components to panel
        // ---------------------------
        panel.add(fileLabel);
        panel.add(fileField);
        panel.add(new JLabel(""));
        panel.add(browseButton);
        panel.add(colorLabel);
        panel.add(colorCombo);
        panel.add(sideLabel);
        panel.add(sideCombo);
        panel.add(copiesLabel);
        panel.add(copiesField);
        panel.add(prepaidLabel);
        panel.add(prepaidCombo);
        panel.add(new JLabel(""));
        panel.add(submitButton);

        return panel;
    }

    public static void main(String[] args) {
        new StudentHomePage("Parvathy");
    }
}

