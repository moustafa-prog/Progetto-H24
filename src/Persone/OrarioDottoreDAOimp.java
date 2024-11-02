package Persone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrarioDottoreDAOimp implements OrarioDottoreDAO {
    private String tableName;

    // Costruttore che accetta il tipo di specializzazione
    public OrarioDottoreDAOimp(String tipoSpecializzazione) {
        this.tableName = "orario_dottore_" + tipoSpecializzazione;
    }

    @Override
    public void addOrarioDottore(String cognome, String nome, String email, String numeroDiTelefono, String orario) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cognome);
            pstmt.setString(2, nome);
            pstmt.setString(3, email);
            pstmt.setString(4, numeroDiTelefono);
            pstmt.setString(5, orario);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrarioDottore(String email) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE EMAIL = ?";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<String[]> getAllOrariDottore() throws SQLException {
        List<String[]> orariDottore = new ArrayList<>();
        String sql = "SELECT COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO FROM " + tableName;
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                orariDottore.add(new String[]{
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("NUMERO_DI_TELEFONO"),
                        rs.getString("ORARIO")
                });
            }
        }

        return orariDottore;
    }
}
