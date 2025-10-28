package model;

public class User {
    private int id;
    private String email;
    private String collegeId;
    private String password;
    private String department;
    private int year;
    private String role;
    private double wallet;

    // Constructors
    public User(String email, String collegeId, String password, String department, int year, String role) {
        this.email = email;
        this.collegeId = collegeId;
        this.password = password;
        this.department = department;
        this.year = year;
        this.role = role;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public String getCollegeId() { return collegeId; }
    public String getPassword() { return password; }
    public String getDepartment() { return department; }
    public int getYear() { return year; }
    public String getRole() { return role; }
    public double getWallet() { return wallet; }

    public void setWallet(double wallet) { this.wallet = wallet; }
}
