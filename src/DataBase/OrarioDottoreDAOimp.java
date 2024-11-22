package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrarioDottoreDAOimp implements OrarioDottoreDAO {
    private String tipoDiUtente; // Tipo di utente associato al login

    // Costruttore che accetta il tipo di utente
    public OrarioDottoreDAOimp(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    // Metodo per impostare o aggiornare il tipo di utente
    public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    // Metodo per ottenere il tipo di utente corrente
    public String getTipoDiUtente() {
        return tipoDiUtente;
    }

    @Override
    public void addOrarioDottore(String cognome, String nome, String email, String numeroDiTelefono, String orario) throws SQLException {
        String sql = "INSERT INTO Orario_Dottore(COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO_DOTTORE, Tipo_di_utente) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cognome);
            pstmt.setString(2, nome);
            pstmt.setString(3, email);
            pstmt.setString(4, numeroDiTelefono);
            pstmt.setString(5, orario);
            pstmt.setString(6, tipoDiUtente); // Usa il tipo di utente del login
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrarioDottore(String email) throws SQLException {
        String sql = "DELETE FROM Orario_Dottore WHERE EMAIL = ? AND Tipo_di_utente = ?";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, tipoDiUtente); // Filtra in base al tipo di utente
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<String[]> getAllOrariDottore() throws SQLException {
        List<String[]> orariDottore = new ArrayList<>();
        String sql = "SELECT COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO_DOTTORE FROM Orario_Dottore WHERE Tipo_di_utente = ?";
        
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoDiUtente); // Filtra in base al tipo di utente
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orariDottore.add(new String[]{
                            rs.getString("COGNOME"),
                            rs.getString("NOME"),
                            rs.getString("EMAIL"),
                            rs.getString("NUMERO_DI_TELEFONO"),
                            rs.getString("ORARIO_DOTTORE")
                    });
                }
            }
        }

        return orariDottore;
    }
}
