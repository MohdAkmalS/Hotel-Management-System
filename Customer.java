package hotel.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
    int id;
    String name;
    String phone;
    String email;

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public static void addCustomer(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO customer (name, phone, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.executeUpdate();

            // Get the auto-generated ID
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    System.out.println("Customer added successfully! Assigned ID: " + generatedId);
                }
            }
        }
    }

    public static void viewCustomers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM customer";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Phone: %s, Email: %s\n",
                        rs.getInt("cust_id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"));
            }
        }
    }
}
