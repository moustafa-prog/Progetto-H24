package Controller;



import Model.Prenotazione;
import Model.Ricevimenti;
import Model.ServiziPanel;
import Model.User;
import Model.Visiti;
import servizi.Servizi;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;






public class DottoreController {
	
	
	public DottoreController() {
        this.servizio = new Servizi();
    }
	

	private Servizi servizio;
   private String tipoUtente;
	
	
	//private REGDAOimp registrazioneDAO;


	

	public DottoreController(String tipoUtente) {
	    this.servizio = new Servizi(); // Inizializza Servizi
        servizio.setTipoDiUtente(tipoUtente);
        this.tipoUtente = tipoUtente;
        this.servizio.setTipoDiUtente(tipoUtente);
        
	    if (tipoUtente == null || tipoUtente.trim().isEmpty()) {
	        throw new IllegalArgumentException("Il tipo di utente non può essere null o vuoto.");
	        
	    }
	    
	}
	
	public boolean conregUtente(User user) {
		return servizio.serRegisUtente(user);
	}

	public void setTipoDiUtente(String tipoDiUtente) {
	    servizio.setTipoDiUtente(tipoDiUtente); // Passa il tipo di utente al servizio
	}
	    public void aggiungiPrenotazione(Prenotazione prenotazione) throws SQLException {
	        servizio.aggiungiPrenotazione(prenotazione);
	    }

	    public void eliminaTuttePrenotazioni() throws SQLException {
	        servizio.eliminaTuttePrenotazioni();
	    }
   
	    public boolean converificred(String email, String password, String tipoUtente, String id) {
	        return servizio.serverificaCredenziali(email, password, tipoUtente, id);
	    }
	
	
	
	
	public void conHomepage(String Tipo_utente, String nomeDottore) {
		servizio.homePage(Tipo_utente, nomeDottore);;
	}
	
	
	
	
	public String congetnomedott(String nome) {
		return servizio.sergetnomedottore(nome);
	};
	
	public List<Visiti> getVisitiByDate(LocalDate date) throws SQLException {
        return servizio.getVisitiByDate(date);
    }

    public void deleteVisiti(String codiceFiscale) throws SQLException {
        servizio.deleteVisiti(codiceFiscale);
    }
	 public List<Ricevimenti> getAllRicevimenti() throws SQLException {
	        return servizio.getAllRicevimenti();
	    }

	    public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
	        servizio.addRicevimento(ricevimento);
	    }

	    public void deleteRicevimento(String codiceFiscale) throws SQLException {
	        servizio.deleteRicevimento(codiceFiscale);
	    }
	    public void addServizi(ServiziPanel servizi) throws SQLException {
	    	servizio.addServizi(servizi);
	    }
	    public void deleteServizi(String codiceFiscale) throws SQLException {
	    	servizio.deleteServizi(codiceFiscale);
	    }
	    public List<ServiziPanel> searchByCodiceFiscale(String codiceFiscale) throws SQLException {
	    	return servizio.searchByCodiceFiscale(codiceFiscale);
	    }


		public String getTipoUtente() {
			return tipoUtente;
		}


		public void setTipoUtente(String tipoUtente) {
			this.tipoUtente = tipoUtente;
		}


		
	


	
}
