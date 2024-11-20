package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dominio.Visiti;

public class VisitiDAOimp implements VisitiDAO {
    
	private String tipoVisiti;

    public VisitiDAOimp(String tipoVisiti) {
        this.tipoVisiti = tipoVisiti;
       
    }

    @SuppressWarnings("unused")
	@Override

	public List<Visiti> getVisitiByDate(LocalDate date) throws SQLException {
	    List<Visiti> visiti = new ArrayList<>();
	    String sql = "SELECT cognome, nome, data, ora, codice_fiscale FROM Prenotazione WHERE data = ? AND Tipo_di_utente = ?";
	    
	    try (Connection conn = DataBaseConnection.getInstance();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        if (conn == null) {
	            System.out.println("Errore: la connessione al database non è stata stabilita.");
	            return visiti;
	        }
	        pstmt.setDate(1, java.sql.Date.valueOf(date)); // Usa java.sql.Date per il parametro data
	        pstmt.setString(2, tipoVisiti); // Passa il tipoVisiti come parametro

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                visiti.add(new Visiti(
	                    rs.getString("cognome"),
	                    rs.getString("nome"),
	                    rs.getString("data"),
	                    rs.getString("ora"),
	                    rs.getString("codice_fiscale")
	                ));
	            }
	        }
	    }
	    return visiti;
	}


    @SuppressWarnings("unused")
	@Override
    public void deleteVisiti(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM Prenotazione WHERE codice_fiscale = ?";
        System.out.println("Esecuzione della query di eliminazione: " + sql);
        System.out.println("Parametro codice_fiscale impostato su: " + codiceFiscale);

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.out.println("Errore: la connessione al database non è stata stabilita.");
                return;
            }
            pstmt.setString(1, codiceFiscale);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Numero di righe eliminate: " + rowsAffected);
        }
    }
}
