package view;

import controller.PrintController;
import model.PrintRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OperatorDashboard extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public OperatorDashboard() {
        setTitle("Operator Dashboard - Print Queue");
        setSize(800, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"ID", "Doc Name", "Student ID", "Sides", "Color", "Copies", "Paid", "Status"}, 0);
        table = new JTable(model);
        refreshTable();

        JButton processBtn = new JButton("Mark as Printed");
        processBtn.addActionListener(e -> handleMarkPrinted());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(processBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<PrintRequest> requests = PrintController.getAllRequests();
        if (requests != null) {
            for (PrintRequest r : requests) {
                model.addRow(new Object[]{
                        r.getId(),
                        r.getDocumentName(),
                        r.getStudentId(),
                        r.getSides(),
                        r.getColorMode(),
                        r.getCopies(),
                        r.isPaid() ? "Yes" : "No",
                        r.getStatus()
                });
            }
        }
    }

    private void handleMarkPrinted() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Select a request to mark as printed.");
            return;
        }
        int id = (int) model.getValueAt(selected, 0);
        PrintController.updateStatus(id, "Completed");
        JOptionPane.showMessageDialog(this, "Marked as printed!");
        refreshTable();
    }
}
