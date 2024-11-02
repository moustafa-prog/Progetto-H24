package Persone;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;


public class SuccessivoDAOIMP implements SuccessiviDAO {

    private String tableName;

    public SuccessivoDAOIMP(String tableName) {
        this.tableName = tableName; // "successivi_denti", "successivi_occhi", "successivi_ortopedico"
    }

		
		
		public void addSuccessivo(Successivi successivo) throws SQLException {
	        String query = "INSERT INTO " + tableName + " ( cognome, nome, codice_fiscale, prossima_visita, data, ora) VALUES (?, ?, ?, ?, ?, ?)";
 try (Connection conn = DataBaseConnection.getInstance();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	   
	            pstmt.setString(1, successivo.getCognome());
	            pstmt.setString(2, successivo.getNome());
	            pstmt.setString(3, successivo.getCodiceFiscale());
	            pstmt.setString(4, successivo.getProssimaVisita());
	            pstmt.setString(5, successivo.getData());
	            pstmt.setString(6, successivo.getOra());

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