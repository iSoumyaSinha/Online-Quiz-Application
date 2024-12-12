package Main.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Soumya@13";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

