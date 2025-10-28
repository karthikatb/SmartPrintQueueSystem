package dao;

import db.DBUtil;
import model.PrintRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintRequestDAO {

    public void addPrintRequest(PrintRequest request) throws SQLException {
        String sql = "INSERT INTO print_requests(student_id, document_name, sides, color_mode, copies, payment_type, is_paid, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, request.getStudentId());
            ps.setString(2, request.getDocumentName());
            ps.setString(3, request.getSides());
            ps.setString(4, request.getColorMode());
            ps.setInt(5, request.getCopies());
            ps.setString(6, request.getPaymentType());
            ps.setBoolean(7, request.isPaid());
            ps.setString(8, request.getStatus());
            ps.executeUpdate();
        }
    }

    public List<PrintRequest> getRequestsByStudent(int studentId) throws SQLException {
        List<PrintRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM print_requests WHERE student_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrintRequest req = new PrintRequest(
                        rs.getInt("student_id"),
                        rs.getString("document_name"),
                        rs.getString("sides"),
                        rs.getString("color_mode"),
                        rs.getInt("copies"),
                        rs.getString("payment_type"),
                        rs.getBoolean("is_paid")
                );
                req.setId(rs.getInt("id"));
                req.setStatus(rs.getString("status"));
                list.add(req);
            }
        }
        return list;
    }

    public List<PrintRequest> getAllRequests() throws SQLException {
        List<PrintRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM print_requests ORDER BY id ASC";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PrintRequest req = new PrintRequest(
                        rs.getInt("student_id"),
                        rs.getString("document_name"),
                        rs.getString("sides"),
                        rs.getString("color_mode"),
                        rs.getInt("copies"),
                        rs.getString("payment_type"),
                        rs.getBoolean("is_paid")
                );
                req.setId(rs.getInt("id"));
                req.setStatus(rs.getString("status"));
                list.add(req);
            }
        }
        return list;
    }

    public void updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE print_requests SET status=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}
