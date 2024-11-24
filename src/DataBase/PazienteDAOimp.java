package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Paziente;

public class PazienteDAOimp implements PazienteDAO {

    private String tipoDiUtente; // Tipo di utente associato al login

    // Costruttore per inizializzare il tipo di utente
    public PazienteDAOimp(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    // Getter e Setter per tipoDiUtente
    public String getTipoDiUtente() {
        return tipoDiUtente;
    }

    public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    /**
     * Aggiunge un nuovo paziente nel database
     */
    @Override
    public void addPaziente(Paziente paziente) throws SQLException {
        String sql = "INSERT INTO Paziente (COGNOME, NOME, DATA_DI_NASCITA, CODICE_FISCALE, NOME_DOTTORE, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta i parametri della query
            pstmt.setString(1, paziente.getCognome());
            pstmt.setString(2, paziente.getNome());
            pstmt.setString(3, paziente.getDataDiNascita());
            pstmt.setString(4, paziente.getCodiceFiscale());
            pstmt.setString(5, paziente.getNomeDottore());
            pstmt.setString(6, tipoDiUtente);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Paziente aggiunto con successo. Righe inserite: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del paziente: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina un paziente dal database in base al codice fiscale
     */
    @Override
    public void deletePaziente(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM Paziente WHERE CODICE_FISCALE = ? AND Tipo_di_utente = ?";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta i parametri della query
            pstmt.setString(1, codiceFiscale);
            pstmt.setString(2, tipoDiUtente);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Paziente eliminato con successo per CODICE_FISCALE: " + codiceFiscale);
            } else {
                System.out.println("Nessun paziente trovato per CODICE_FISCALE: " + codiceFiscale);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del paziente: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Recupera tutti i pazienti associati al tipo di utente
     */
    @Override
    public List<Paziente> getAllPazienti() throws SQLException {
        List<Paziente> pazienti = new ArrayList<>();
        String sql = "SELECT COGNOME, NOME, DATA_DI_NASCITA, CODICE_FISCALE, NOME_DOTTORE, Tipo_di_utente FROM Paziente WHERE Tipo_di_utente = ?";

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta i parametri della query
            pstmt.setString(1, tipoDiUtente);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Paziente paziente = new Paziente(
                            rs.getString("COGNOME"),
                            rs.getString("NOME"),
                            rs.getString("DATA_DI_NASCITA"),
                            rs.getString("CODICE_FISCALE"),
                            rs.getString("NOME_DOTTORE"),
                            rs.getString("Tipo_di_utente")
                    );
                    pazienti.add(paziente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il recupero dei pazienti: " + e.getMessage());
            throw e;
        }

        return pazienti;
    }
}
