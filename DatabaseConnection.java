package hotel.management;
import java.sql.*;

public class DatabaseConnection {
    static final String URL = "jdbc:mysql://localhost:3306/Hotel_Management";
    static final String USER = "root";  
    static final String PASSWORD = "MOHAMMEDAKMAL@2005";  

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
