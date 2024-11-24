package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    // URL e credenziali per la connessione al database
    private static final String URL = "jdbc:mysql://localhost:3306/progetto";
    private static final String USER = "root";
    private static final String PASSWORD = "20012001";
    
    // Variabile per memorizzare l'unica istanza della connessione
    private static Connection instance;

    // Costruttore privato per impedire la creazione di nuove istanze
    private DataBaseConnection() {
        // Impedisce la creazione esterna dell'istanza
    }

    // Metodo pubblico per ottenere l'istanza della connessione
    public static synchronized Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione stabilita con il database");
        }
        return instance;
    }
}
