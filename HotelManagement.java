package hotel.management;

import java.sql.*;
import java.util.Scanner;

public class HotelManagement {
	
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.connect();
             Scanner scanner = new Scanner(System.in)) {
            
            while (true) {
                System.out.println("\nHotel Management System");
                System.out.println("1. Add Customer");
                System.out.println("2. View Rooms");
                System.out.println("3. Book a Room");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        Customer.addCustomer(conn, scanner);
                        break;
                    case 2:
                        Room.viewRooms(conn);
                        break;
                    case 3:
                        Booking.bookRoom(conn, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
