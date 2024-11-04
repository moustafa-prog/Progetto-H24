package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Persone.User;

public class REGDAOimp implements RegistrazioneDAO {
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
    }
	public void registraUtente1(User user) throws SQLException {
		// TODO Auto-generated method stub
	       String insertSQL = "INSERT INTO user (email, password, Nome, Cognome, Codice_fiscale, Data_di_Nascita, Indirizzio, Tipo_di_utente, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (Connection con = getConnection();
	             PreparedStatement pstmt = con.prepareStatement(insertSQL)) {

	        	pstmt.setString(1, user.getEmail());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getNome());
	            pstmt.setString(4, user.getCognome());
	            pstmt.setString(5, user.getCodiceFiscale());
	            pstmt.setString(6, user.getDataNascita());
	            pstmt.setString(7, user.getIndirizzio());
	            pstmt.setString(8, user.getTipoUtente());
	            pstmt.setString(9, user.getIdSpecializzazione());

	            pstmt.executeUpdate();
	            int rowsAffected = pstmt.executeUpdate();
	            System.out.println("Righe inserite: " + rowsAffected);  // Aggiungi questa linea per debug
	            }
	    
	}
	@Override
	public void registraUtente(User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}

		

	

}
