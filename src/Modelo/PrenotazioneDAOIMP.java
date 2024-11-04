package Modelo;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;

import Persone.Prenotazione;


public class PrenotazioneDAOIMP implements PrenotazioneDAO {

    private String tableName;

    public PrenotazioneDAOIMP(String tableName) {
        this.tableName = tableName; // "successivi_denti", "successivi_occhi", "successivi_ortopedico"
    }

		
		
		public void addPrenotazione(Prenotazione prenotazione) throws SQLException {
	        String query = "INSERT INTO " + tableName + " ( cognome, nome, codice_fiscale, prossima_visita, data, ora) VALUES (?, ?, ?, ?, ?, ?)";
 try (Connection conn = DataBaseConnection.getInstance();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	   
	            pstmt.setString(1, prenotazione.getCognome());
	            pstmt.setString(2, prenotazione.getNome());
	            pstmt.setString(3, prenotazione.getCodiceFiscale());
	            pstmt.setString(4, prenotazione.getProssimaVisita());
	            pstmt.setString(5, prenotazione.getData());
	            pstmt.setString(6, prenotazione.getOra());

	            pstmt.executeUpdate();
	        }
 
	    }
			

		@Override
		public void eliminaTutti() throws SQLException {
			// TODO Auto-generated method stub
			String sql = "DELETE FROM " + tableName;
	        try (Connection conn = DataBaseConnection.getInstance();
	             Statement stmt = conn.createStatement()) {

	            stmt.executeUpdate(sql);
	        }
	    
		}

	
}