package Dottore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Dottore";
    private static final String USER = "root";
    private static final String PASSWORD = "Moustafa2001";
    @SuppressWarnings("unused")
	private static final String DRIVER = "jdbc:mysql://localhost:3306/Progetto";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
