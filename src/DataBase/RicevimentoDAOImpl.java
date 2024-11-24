package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Ricevimenti;
import Model.Visiti;

public class RicevimentoDAOImpl implements RicevimentoDAO {
 
	

	private String tipoDiUtente; // Campo per ricordare il tipo di utente


	public RicevimentoDAOImpl(String tipoDiUtente) {
		// TODO Auto-generated constructor stub
	}


	public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }


    // Metodo per aggiungere un ricevimento alla tabella dei ricevimenti
    public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
        String sql = "INSERT INTO Ricevimenti (COGNOME, NOME, DATA, ORA, CODICE_FISCALE, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ricevimento.getCognome());
            pstmt.setString(2, ricevimento.getNome());
            pstmt.setString(3, ricevimento.getData());
            pstmt.setString(4, ricevimento.getOra());
            pstmt.setString(5, ricevimento.getCodiceFiscale());
            pstmt.setString(6, tipoDiUtente); // Aggiunge automaticamente il tipo di utente

            pstmt.executeUpdate();
        }
    }
    // Metodo per eliminare un ricevimento dalla tabella dei ricevimenti
    @Override
    public void deleteRicevimento(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM Ricevimenti WHERE CODICE_FISCALE = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        }
    }

    // Metodo per ottenere tutti i ricevimenti dalla tabella
   
  
    public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        String sql = "SELECT * FROM Ricevimenti WHERE Tipo_di_utente = ?";
        List<Ricevimenti> ricevimenti = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipoDiUtente); // Usa il tipo di utente come filtro
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ricevimenti.add(new Ricevimenti(
                    rs.getString("COGNOME"),
                    rs.getString("NOME"),
                    rs.getString("DATA"),
                    rs.getString("ORA"),
                    rs.getString("CODICE_FISCALE"),
                    rs.getString("Tipo_di_utente")
                ));
            }
        }

        return ricevimenti;
    }
    // Metodo specifico per aggiungere un ricevimento con un oggetto `Visiti`
    @Override
    public void addRicevimento(Visiti visita) throws SQLException {
        String sql = "INSERT INTO Ricevimenti (COGNOME, NOME, DATA, ORA, CODICE_FISCALE, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, visita.getCognome());
            pstmt.setString(2, visita.getNome());
            pstmt.setString(3, visita.getData());
            pstmt.setString(4, visita.getOra());
            pstmt.setString(5, visita.getCodiceFiscale());
            pstmt.setString(6, tipoDiUtente); // Aggiunge automaticamente il tipo di utente

            pstmt.executeUpdate();
        }
    }


	
	


	
}