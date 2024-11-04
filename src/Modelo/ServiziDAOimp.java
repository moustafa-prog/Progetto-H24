package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Persone.Servizi;

public class ServiziDAOimp implements ServiziDAO {
    
    private String tableName;

    public ServiziDAOimp(String tipo) {
        this.tableName = "servicepanel_" + tipo;
    }

    @Override
    public void addServizi(Servizi servizi) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (PREZZO, DESCRIZIONE_DELLE_MALATTIA, TRATTAMENTO, CODICE_FISCALE) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Debug: stampa i valori dei parametri
            System.out.println("Prezzo: " + servizi.getPrezzo());
            System.out.println("Descrizione Malattia: " + servizi.getDescrizioneMalattia());
            System.out.println("Trattamento: " + servizi.getTrattamento());
            System.out.println("Codice Fiscale: " + servizi.getCodiceFiscale());

            pstmt.setString(1, servizi.getPrezzo());
            pstmt.setString(2, servizi.getDescrizioneMalattia());
            pstmt.setString(3, servizi.getTrattamento());
            pstmt.setString(4, servizi.getCodiceFiscale());

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Righe inserite: " + rowsAffected); // Verifica che l'inserimento sia riuscito
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteServizi(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM "+ tableName + "WHERE CODICE_FISCALE  = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Servizio eliminato con successo per CODICE_FISCALE: " + codiceFiscale);
            } else {
                System.out.println("Nessun servizio trovato per CODICE_FISCALE: " + codiceFiscale);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione del servizio: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public List<Servizi> searchByCodiceFiscale(String codiceFiscale) throws SQLException {
        List<Servizi> services = new ArrayList<>();
        String sql = "SELECT  PREZZO, DESCRIZIONE_DELLE_MALATTIA, TRATTAMENTO, CODICE_FISCALE FROM " + tableName + " WHERE CODICE_FISCALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codiceFiscale);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    services.add(new Servizi(
                        rs.getString("PREZZO"),
                        rs.getString("DESCRIZIONE_DELLE_MALATTIA"),
                        rs.getString("TRATTAMENTO"),
                        rs.getString("CODICE_FISCALE")
                    ));
                }
            }
        }
        return services;
    }

    @Override
    public List<Servizi> getAllServices() throws SQLException {
        List<Servizi> services = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = DataBaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                services.add(new Servizi(
                    rs.getString("PREZZO"),
                    rs.getString("DESCRIZIONE_DELLE_MALATTIA"),
                    rs.getString("TRATTAMENTO"),
                    rs.getString("CODICE_FISCALE")
                ));
            }
        }
        return services;
    }
   
}
