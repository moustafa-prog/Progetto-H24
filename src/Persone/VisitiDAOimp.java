package Persone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitiDAOimp implements VisitiDAO {
    private String tableName;
    @SuppressWarnings("unused")
	private String tipoVisiti;

    public VisitiDAOimp(String tipoVisiti) {
        this.tipoVisiti = tipoVisiti;
        this.tableName = "successivi_" + tipoVisiti;
    }

    @SuppressWarnings("unused")
	@Override
    public List<Visiti> getVisitiByDate(LocalDate date) throws SQLException {
        List<Visiti> visiti = new ArrayList<>();  // Inizializza una lista vuota
        System.out.println("Esecuzione di getVisitiByDate per la data: " + date);  // Messaggio di debug
        System.out.println("Nome della tabella utilizzata: " + tableName);
        String sql = "SELECT cognome, nome, data, ora, codice_fiscale FROM " + tableName + " WHERE data = ?";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.out.println("Errore: la connessione al database non è stata stabilita.");
                return visiti; // ritorna una lista vuota se la connessione non è stabilita
            }
            pstmt.setString(1, date.toString()); // Imposta la data come parametro
            System.out.println("Parametro data impostato su: " + date.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {  // Controlla se il result set è vuoto
                    System.out.println("Nessun risultato trovato per la data: " + date);
                }
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
        System.out.println("Numero di visitatori trovati: " + visiti.size());  // Messaggio di debug
        return visiti;  // Ritorna sempre una lista, anche se vuota
    }

    @SuppressWarnings("unused")
	@Override
    public void deleteVisiti(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE codice_fiscale = ?";
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
