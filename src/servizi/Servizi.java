package servizi;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DataBase.PrenotazioneDAOIMP;
import DataBase.UserDAOimp;
import DataBase.VisitiDAOimp;
import Model.Dottore;
import Model.Prenotazione;
import Model.Ricevimenti;
import Model.ServiziPanel;
import Model.User;
import Model.Visiti;
import view.HomePageDentista;
import view.HomePageOculista;
import view.HomePageOrtopedico;
import DataBase.RicevimentoDAOImpl;
import DataBase.ServiziDAOimp;


public class Servizi {
    public UserDAOimp userdaoimp;

	public Dottore dottore;
	private VisitiDAOimp visitiDAO;
	private Object registrazioneDAO;
	private PrenotazioneDAOIMP prenotazioneDAO;
	private RicevimentoDAOImpl ricevimentiDAO;
	private Object serviziDAO;
	private ServiziDAOimp service;
	 public Servizi(Object registrazioneDAO, Object serviziDAO) {
	        this.userdaoimp = new UserDAOimp();
	        this.setRegistrazioneDAO(registrazioneDAO);
	        this.setServiziDAO(serviziDAO);
	        
	        
	    }
	
	
	public Servizi() {
		// TODO Auto-generated constructor stub
		 this.userdaoimp = new UserDAOimp();
		 this.ricevimentiDAO = new RicevimentoDAOImpl("DEFAULT"); 
		 this.prenotazioneDAO = new PrenotazioneDAOIMP();
		 this.visitiDAO = new VisitiDAOimp();
		 this.service = new ServiziDAOimp("Daldnd");
	}


	
	
	public void setTipoDiUtente(String tipoDiUtente) {
		  ricevimentiDAO.setTipoDiUtente(tipoDiUtente);
		prenotazioneDAO.setTipoDiUtente(tipoDiUtente);
		visitiDAO.setTipoDiUtente(tipoDiUtente);
		service.setTipoDiUtente(tipoDiUtente);
	        if (ricevimentiDAO != null) {
	            ricevimentiDAO.setTipoDiUtente(tipoDiUtente);
	        } else {
	            throw new IllegalStateException("RicevimentoDAOImpl non è stato inizializzato.");
	        }
	    }// Imposta il tipo di utente
	
	public List<Ricevimenti> getAllRicevimenti() throws SQLException {
        if (ricevimentiDAO != null) {
            return ricevimentiDAO.getAllRicevimenti();
        } else {
            throw new IllegalStateException("RicevimentoDAOImpl non è stato inizializzato.");
        }
    }

	public void addRicevimento(Ricevimenti ricevimento) throws SQLException {
        if (ricevimentiDAO != null) {
            ricevimentiDAO.addRicevimento(ricevimento);
        } else {
            throw new IllegalStateException("RicevimentoDAOImpl non è stato inizializzato.");
        }
    }

    public void deleteRicevimento(String codiceFiscale) throws SQLException {
        if (ricevimentiDAO != null) {
            ricevimentiDAO.deleteRicevimento(codiceFiscale);
        } else {
            throw new IllegalStateException("RicevimentoDAOImpl non è stato inizializzato.");
        }
    }
	
    public boolean serverificaCredenziali(String email, String password, String tipoUtente, String id) {
    	 if (userdaoimp == null) {
    	        throw new IllegalStateException("UserDAOimp non è stato inizializzato.");
    	    }
    	return userdaoimp.verificaCredenziali(email, password, tipoUtente, id);
    }
    public void addServizi(ServiziPanel servizi) throws SQLException {
    	service.addServizi(servizi);
    }
    public void deleteServizi(String codiceFiscale) throws SQLException {
    	service.deleteServizi(codiceFiscale);
    }
    public List<ServiziPanel> searchByCodiceFiscale(String codiceFiscale) throws SQLException {
    	return service.searchByCodiceFiscale(codiceFiscale);
    }
    public void aggiungiPrenotazione(Prenotazione prenotazione) throws SQLException {
        prenotazioneDAO.addPrenotazione(prenotazione);
    }

    public void eliminaTuttePrenotazioni() throws SQLException {
        prenotazioneDAO.eliminaTutti();
    }
	
	
	public boolean serRegisUtente(User user) {
		return userdaoimp.registraUtente(user);
	}
	
	public String sergetnomedottore(String nome) {
		
		return userdaoimp.getNomeDottore(nome);
		
	}
	public void setTipoDiVisiti(String tipoDiVisiti) {
        visitiDAO = new VisitiDAOimp(); // Cambia dinamicamente il tipo di visitatore
    }

    public List<Visiti> getVisitiByDate(LocalDate date) throws SQLException {
        return visitiDAO.getVisitiByDate(date);
    }

    public void deleteVisiti(String codiceFiscale) throws SQLException {
        visitiDAO.deleteVisiti(codiceFiscale);
    }
	
	public void homePage(String Tipo_utente, String nomeDottore) {
		switch (Tipo_utente) {
		case "ORTOPEDICO":
		 new HomePageOrtopedico(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		case "OCULISTA":
		 new HomePageOculista(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		case "DENTISTA":
		 new HomePageDentista(nomeDottore, Tipo_utente).setVisible(true);
		 break;
		}
		 
	}


	public Object getServiziDAO() {
		return serviziDAO;
	}


	public void setServiziDAO(Object serviziDAO) {
		this.serviziDAO = serviziDAO;
	}


	public Object getRegistrazioneDAO() {
		return registrazioneDAO;
	}


	public void setRegistrazioneDAO(Object registrazioneDAO) {
		this.registrazioneDAO = registrazioneDAO;
	}
}
