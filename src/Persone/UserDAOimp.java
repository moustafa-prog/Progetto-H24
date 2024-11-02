package Persone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDAOimp implements UserDAO<Login>{

	   

    public boolean verificaCredenziali(String email, String password, String tipoUtente, String idUtente) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND Tipo_di_utente = ? AND ID = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
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
    public String getNomeDottore(String username) {
        String NomeDottore = null;
        String query = "SELECT nome FROM user WHERE email = ?";

        try (Connection conn = DataBaseConnection.getInstance();
                PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                NomeDottore = resultSet.getString("nome");
             
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del nome del dottore: " + e.getMessage());
            e.printStackTrace();
        }
        return NomeDottore;
    }
    
    }

