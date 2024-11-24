package DataBase;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;

import Model.Prenotazione;


public class PrenotazioneDAOIMP implements PrenotazioneDAO {


	private String tipoDiUtente;


    public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

		
		
    @Override
    public void addPrenotazione(Prenotazione prenotazione) throws SQLException {
        String query = "INSERT INTO Prenotazione (cognome, nome, codice_fiscale, data, ora, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, prenotazione.getCognome());
            pstmt.setString(2, prenotazione.getNome());
            pstmt.setString(3, prenotazione.getCodiceFiscale());
            pstmt.setString(4, prenotazione.getData());
            pstmt.setString(5, prenotazione.getOra());
            pstmt.setString(6, tipoDiUtente); // Usa il tipo passato dall'oggetto Prenotazione
            pstmt.executeUpdate();
        }
    }

		@Override
		public void eliminaTutti() throws SQLException {
			// TODO Auto-generated method stub
			String sql = "DELETE FROM Prenotazione WHERE CODICE_FISCALE = ?" ;
	        try (Connection conn = DataBaseConnection.getInstance();
	             Statement stmt = conn.createStatement()) {

	            stmt.executeUpdate(sql);
	        }
	    
		}

	
}