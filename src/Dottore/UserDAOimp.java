package Dottore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAOimp implements UserDAO<Login>{

	    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto", "root", "Moustafa2001");
    }

    public boolean verificaCredenziali(String email, String password, String tipoUtente, String idUtente) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND Tipo_di_utente = ? AND ID = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, tipoUtente);
            pst.setString(4, idUtente);

            ResultSet rs = pst.executeQuery();
            return rs.next();  // Se il risultato esiste, le credenziali sono corrette.
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
        }
    }
}
