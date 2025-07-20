package hotel.management;

import java.sql.*;

public class Room {
    int roomId;
    String roomType;
    double price;
    String status;

    public Room(int roomId, String roomType, double price, String status) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
    }

    public static void viewRooms(Connection conn) throws SQLException {
        String sql = "SELECT * FROM room";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("Room ID: %d, Type: %s, Price: %.2f, Status: %s\n",
                        rs.getInt("room_id"), rs.getString("room_type"), 
                        rs.getDouble("price"), rs.getString("status"));
            }
        }
    }
}
