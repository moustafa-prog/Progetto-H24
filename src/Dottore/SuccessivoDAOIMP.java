package Dottore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;

public class SuccessivoDAOIMP implements SuccessiviDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/progetto";
    private static final String USER = "root";
    private static final String PASS = "Moustafa2001";
    private String tableName;

    public SuccessivoDAOIMP(String tableName) {
        this.tableName = tableName; // Ad esempio "successivi_denti", "successivi_occhi", "successivi_ortopedico"
    }

		@SuppressWarnings("unused")
		private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
	    }
		public void addSuccessivo(Successivi successivo) throws SQLException {
			// TODO Auto-generated method stub
			String sql = "INSERT INTO " + tableName + " (NOME_DOTTORE, COGNOME, NOME, CODICE_FISCALE, PROSSIMA_VISITA, DATA, ORA) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setString(1, successivo.getNomeDottore());
	            pstmt.setString(2, successivo.getCognome());
	            pstmt.setString(3, successivo.getNome());
	            pstmt.setString(4, successivo.getCodiceFiscale());
	            pstmt.setString(5, successivo.getProssimaVisita());
	            pstmt.setString(6, successivo.getData());
	            pstmt.setString(7, successivo.getOra());

	            pstmt.executeUpdate();
	        }
	    }
			

		@Override
		public void eliminaTutti() throws SQLException {
			// TODO Auto-generated method stub
			String sql = "DELETE FROM " + tableName;
	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             Statement stmt = conn.createStatement()) {

	            stmt.executeUpdate(sql);
	        }
	    
		}

	
}