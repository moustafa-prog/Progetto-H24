package Persone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RicevimentoDAOImpl implements RicevimentoDAO {
 
	

    private String tableName;
  
	@SuppressWarnings("unused")
	private String tipoRicevimento;

    // Costruttore che accetta il tipo di ricevimento
    public RicevimentoDAOImpl(String tipoRicevimento) {
        this.tipoRicevimento = tipoRicevimento;
        this.tableName = "recivementi_" + tipoRicevimento;
    }

    // Metodo per aggiungere un ricevimento alla tabella dei ricevimenti
    public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (COGNOME, NOME, DATA, ORA, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ricevimento.getCognome());
            pstmt.setString(2, ricevimento.getNome());
            pstmt.setString(3, ricevimento.getData());
            pstmt.setString(4, ricevimento.getOra());
            pstmt.setString(5, ricevimento.getCodiceFiscale());

            pstmt.executeUpdate();
        }
    }

    // Metodo per eliminare un ricevimento dalla tabella dei ricevimenti
    @Override
    public void deleteRicevimento(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE CODICE_FISCALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    
    @Override
    public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ricevimenti.add(new Ricevimenti(
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("DATA"),
                        rs.getString("ORA"),
                        rs.getString("CODICE_FISCALE")));
            }
        }

        return ricevimenti;
    }


	

	

}
