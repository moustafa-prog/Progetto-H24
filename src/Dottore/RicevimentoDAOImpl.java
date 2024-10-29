package Dottore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RicevimentoDAOImpl implements RicevimentoDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/progetto";
    private static final String USER = "root";
    private static final String PASS = "Moustafa2001";
	private static final String successiviTableName = "";

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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    // Metodo che carica i ricevimenti da una tabella "Successivi" specifica per la data e il tipo di ricevimento
    public List<Ricevimenti> getRicevimentiByDate(String tipoRicevimento, String data) throws SQLException {
        String successiviTableName = "Successivi_" + tipoRicevimento; // Nome della tabella Successivi_tipoRicevimento
        String sql = "SELECT COGNOME, NOME, DATA, ORA, CODICE_FISCALE FROM " + successiviTableName + " WHERE DATA = ?";
        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, data);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ricevimenti.add(new Ricevimenti(
                            rs.getString("COGNOME"),
                            rs.getString("NOME"),
                            rs.getString("DATA"),
                            rs.getString("ORA"),
                            rs.getString("CODICE_FISCALE")));
                }
            }
        }

        return ricevimenti;
    }

    // Metodo per ottenere tutti i ricevimenti dalla tabella dei ricevimenti
    @Override
    public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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

	@Override
	public List<Ricevimenti> getRicevimentiByDate(String data) throws SQLException {
		// TODO Auto-generated method stub
		return getRicevimentiByDate(data,"Successivi_" +successiviTableName);
	}
}
