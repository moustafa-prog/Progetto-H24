package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Visiti;

public class VisitiDAOimp implements VisitiDAO {
	private String tipoDiUtente; 
	public void setTipoDiUtente(String tipoDiUtente) {
		// TODO Auto-generated method stub
		this.tipoDiUtente = tipoDiUtente;
	}
   
	@Override


	public List<Visiti> getVisitiByDate(LocalDate date) throws SQLException {
	    List<Visiti> visiti = new ArrayList<>();
	    String sql = "SELECT cognome, nome, data, ora, codice_fiscale FROM Prenotazione WHERE data = ? AND Tipo_di_utente = ?";
	    System.out.println("Esecuzione della query: " + sql);
	    String tipoVisiti = tipoDiUtente;
		System.out.println("Parametri: data = " + date + ", Tipo_di_utente = " + tipoVisiti);

	    try (Connection conn = DataBaseConnection.getInstance();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setDate(1, java.sql.Date.valueOf(date));
	        pstmt.setString(2, tipoDiUtente);

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
	    System.out.println("Numero di risultati trovati: " + visiti.size());
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
