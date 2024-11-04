package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Persone.InventarioMateriale;

public class InventarioDAOImp implements InventarioDAO {
    

    private String tableName;

    public InventarioDAOImp(String tipo) {
        this.tableName = "inventario_materiali_" + tipo;
    }

    @Override
    public void addMateriale(InventarioMateriale materiale) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (NOME_MATERIALE, QUANTITA_DISPONIBILE, QUANTITA_MINIMA, DESCRIZIONE, STATO_ORDINE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materiale.getNomeMateriale());
            pstmt.setInt(2, materiale.getQuantitaDisponibile());
            pstmt.setInt(3, materiale.getQuantitaMinima());
            pstmt.setString(4, materiale.getDescrizione());
            pstmt.setString(5, materiale.getStatoOrdine());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteMateriale(String nomeMateriale) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE NOME_MATERIALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeMateriale);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<InventarioMateriale> getAllMateriali() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        List<InventarioMateriale> materiali = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                materiali.add(new InventarioMateriale(
                    rs.getString("NOME_MATERIALE"),
                    rs.getInt("QUANTITA_DISPONIBILE"),
                    rs.getInt("QUANTITA_MINIMA"),
                    rs.getString("DESCRIZIONE"),
                    rs.getString("STATO_ORDINE")
                ));
            }
        }
        return materiali;
    }

    @Override
    public void orderMateriale(String nomeMateriale) throws SQLException {
        String sql = "UPDATE " + tableName + " SET STATO_ORDINE = 'Da ordinare' WHERE NOME_MATERIALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeMateriale);
            pstmt.executeUpdate();
        }
    }
}
