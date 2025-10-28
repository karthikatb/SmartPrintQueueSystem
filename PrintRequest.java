package model;

public class PrintRequest {
    private int id;
    private int studentId;
    private String documentName;
    private String sides;
    private String colorMode;
    private int copies;
    private String paymentType;
    private boolean isPaid;
    private String status;

    public PrintRequest(int studentId, String documentName, String sides, String colorMode, int copies, String paymentType, boolean isPaid) {
        this.studentId = studentId;
        this.documentName = documentName;
        this.sides = sides;
        this.colorMode = colorMode;
        this.copies = copies;
        this.paymentType = paymentType;
        this.isPaid = isPaid;
        this.status = "Pending";
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public String getDocumentName() { return documentName; }
    public String getSides() { return sides; }
    public String getColorMode() { return colorMode; }
    public int getCopies() { return copies; }
    public String getPaymentType() { return paymentType; }
    public boolean isPaid() { return isPaid; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
