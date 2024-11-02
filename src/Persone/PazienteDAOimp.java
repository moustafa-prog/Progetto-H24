package Persone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PazienteDAOimp implements PazienteDAO {
    

    private String tableName;

    // Costruttore che prende il tipo di paziente
    public PazienteDAOimp(String tipoPaziente) {
        this.tableName = "paziente_" + tipoPaziente; // Imposta il nome della tabella specifica
    }

    public void addPaziente(Paziente paziente) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (COGNOME, NOME, DATA_DI_NASCITA, CODICE_FISCALE, NOME_DOTTORE) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paziente.getCognome());
            pstmt.setString(2, paziente.getNome());
            pstmt.setString(3, paziente.getDataDiNascita());
            pstmt.setString(4, paziente.getCodiceFiscale());
            pstmt.setString(5, paziente.getNomeDottore());

            pstmt.executeUpdate();
        }
    }

    public void deletePaziente(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE CODICE_FISCALE = ?";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    public List<Paziente> getAllPazienti() throws SQLException {
        List<Paziente> pazienti = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                pazienti.add(new Paziente(
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("DATA_DI_NASCITA"),
                        rs.getString("CODICE_FISCALE"),
                        rs.getString("NOME_DOTTORE")
                ));
            }
        }

        return pazienti;
    }
}
