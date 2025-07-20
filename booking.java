package hotel.management;

import java.sql.*;
import java.util.Scanner;

class Booking {

    public static void bookRoom(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Room ID to Book: ");
        int roomId = scanner.nextInt();

        // Check if the room exists and is available
        String checkRoomSql = "SELECT status FROM room WHERE room_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkRoomSql)) {
            checkStmt.setInt(1, roomId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Room ID does not exist!");
                    return;
                } else {
                    String roomStatus = rs.getString("status");
                    if (!roomStatus.equalsIgnoreCase("Available")) {
                        System.out.println("Room is not available!");
                        return;
                    }
                }
            }
        }

        // Insert booking details
        String bookSql = "INSERT INTO booking (cust_id, room_id, status) VALUES (?, ?, 'Confirmed')";
        try (PreparedStatement bookStmt = conn.prepareStatement(bookSql)) {
            bookStmt.setInt(1, customerId);
            bookStmt.setInt(2, roomId);
            bookStmt.executeUpdate();
        }

        // Update room status to "Occupied"
        String updateRoomSql = "UPDATE room SET status = 'Occupied' WHERE room_id = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateRoomSql)) {
            updateStmt.setInt(1, roomId);
            updateStmt.executeUpdate();
        }
        System.out.println();
        System.out.println("Room booked successfully!");
    }
}
