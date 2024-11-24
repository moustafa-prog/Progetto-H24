package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.InventarioMateriale;

public class InventarioDAOImp implements InventarioDAO {
    

	private String tipoDiUtente; // Campo per ricordare il tipo di utente

    public InventarioDAOImp(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }
    public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }
  public String getTipoDiUtente() {
        return tipoDiUtente;
    }

    @Override
    public void addMateriale(InventarioMateriale materiale) throws SQLException {
        String sql = "INSERT INTO Assistenza (NOME_MATERIALE, QUANTITA_DISPONIBILE, QUANTITA_MINIMA, DESCRIZIONE, STATO_ORDINE, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materiale.getNomeMateriale());
            pstmt.setInt(2, materiale.getQuantitaDisponibile());
            pstmt.setInt(3, materiale.getQuantitaMinima());
            pstmt.setString(4, materiale.getDescrizione());
            pstmt.setString(5, materiale.getStatoOrdine());
            pstmt.setString(6, tipoDiUtente); 
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteMateriale(String nomeMateriale) throws SQLException {
        String sql = "DELETE FROM Assistenza WHERE NOME_MATERIALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeMateriale);
            pstmt.executeUpdate();
        }
    }

    @Override
   
    public List<InventarioMateriale> getAllMateriali() throws SQLException {
        String sql = "SELECT * FROM Assistenza WHERE Tipo_di_utente = ?";
        List<InventarioMateriale> materiali = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoDiUtente); // Imposta il tipo di utente
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    materiali.add(new InventarioMateriale(
                        rs.getString("NOME_MATERIALE"),
                        rs.getInt("QUANTITA_DISPONIBILE"),
                        rs.getInt("QUANTITA_MINIMA"),
                        rs.getString("DESCRIZIONE"),
                        rs.getString("STATO_ORDINE"),
                        rs.getString("Tipo_di_utente")
                    ));
                }
            }
        }
        return materiali;
    }


    @Override
    public void orderMateriale(String nomeMateriale) throws SQLException {
        String sql = "UPDATE Assistenza SET STATO_ORDINE = 'Da ordinare' WHERE NOME_MATERIALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomeMateriale);
            pstmt.executeUpdate();
        }
    }
}
