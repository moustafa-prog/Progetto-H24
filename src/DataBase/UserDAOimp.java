package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;
import view.Login;



public class UserDAOimp implements UserDAO<Login>{

	@SuppressWarnings("unused")
	private String tipoDiUtente;

    public void setTipoDiUtente(String tipoDiUtente) {
        this.tipoDiUtente = tipoDiUtente;
    }

    public boolean verificaCredenziali(String email, String password, String tipoDiUtente, String ID) {
    	if (tipoDiUtente == null || tipoDiUtente.trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di utente non può essere null o vuoto.");
        }
        String sql = "SELECT * FROM Dottore WHERE Email = ? AND password = ? AND Tipo_di_utente = ? AND ID = ?";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, tipoDiUtente);
            pst.setString(4, ID);

            ResultSet rs = pst.executeQuery();
            return rs.next();  // Se il risultato esiste, le credenziali sono corrette.
            
        } catch (SQLException e) {
        	System.out.println("Errore durante la verifica delle credenziali:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public String getNomeDottore(String Nome) {
        String NomeDottore = null;
        String query = "SELECT Nome FROM Dottore WHERE Email = ?";

        try (Connection conn = DataBaseConnection.getInstance();
                PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, Nome);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                NomeDottore = resultSet.getString("Nome");
             
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del nome del dottore: " + e.getMessage());
            e.printStackTrace();
        }
        return NomeDottore;
    }
	
    public boolean registraUtente(User user) {
        String insertSQL = "INSERT INTO Dottore (Email, Password, Nome, Cognome, Codice_Fiscale, Data_Nascita, Indirizzo, Tipo_di_utente, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getInstance();
             PreparedStatement pst = conn.prepareStatement(insertSQL)) {

            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getNome());
            pst.setString(4, user.getCognome());
            pst.setString(5, user.getCodiceFiscale());
            pst.setString(6, user.getDataNascita());
            pst.setString(7, user.getIndirizzio());
            pst.setString(8, user.getTipoUtente());
            pst.setString(9, user.getIdSpecializzazione());

            int rowsAffected = pst.executeUpdate();
            System.out.println("Righe inserite: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public boolean registraDottore(String email, String password, String nome, String cognome, String codiceFiscale, String dataNascita, String indirizzio, String Tipo_utente, String ID) throws SQLException {
	    if (email.isEmpty() || password.isEmpty() || nome.isEmpty() || cognome.isEmpty()) {
	        return false; // Non registrare se i campi obbligatori sono vuoti
	    }
	   
	    
	    // Logica per la registrazione nel database
		User user = new User(email, password, nome, cognome, codiceFiscale, dataNascita, indirizzio, Tipo_utente, ID);
		System.out.println("Registrazione utente completata con successo");
		registraUtente(user); // Chiama il DAO per inserire l'utente nel database
		return true; // Registrazione riuscita
	}
	
	
	 
    
    }

