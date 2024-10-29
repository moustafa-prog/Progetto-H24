package Dottore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiziDAOimp implements ServiziDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/progetto";
    private static final String USER = "root";
    private static final String PASS = "Moustafa2001";

    private String tableName;

    public ServiziDAOimp(String tipo) {
        this.tableName = "servicepanel_" + tipo;
    }

    @Override
    public void addServizi(Servizi servizi) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (NOME_DOTTORE, PREZZO, DESCRIZIONE_DELLE_MALATTIA, TRATTAMENTO, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, servizi.getNomeDottore());
            pstmt.setString(2, servizi.getPrezzo());
            pstmt.setString(3, servizi.getDescrizioneMalattia());
            pstmt.setString(4, servizi.getTrattamento());
            pstmt.setString(5, servizi.getCodiceFiscale());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteServizi(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE CODICE_FISCALE = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Servizi> searchByCodiceFiscale(String codiceFiscale) throws SQLException {
        List<Servizi> services = new ArrayList<>();
        String sql = "SELECT NOME_DOTTORE, PREZZO, DESCRIZIONE_DELLE_MALATTIA, TRATTAMENTO, CODICE_FISCALE FROM " + tableName + " WHERE CODICE_FISCALE = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codiceFiscale);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    services.add(new Servizi(
                        rs.getString("NOME_DOTTORE"),
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                services.add(new Servizi(
                    rs.getString("NOME_DOTTORE"),
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
