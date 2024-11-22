package servizi;

import java.sql.SQLException;

import DataBase.RicevimentoDAO;
import DataBase.UserDAOimp;
import Model.Dottore;
import Model.User;
import Model.Visiti;
import view.HomePageDentista;
import view.HomePageOculista;
import view.HomePageOrtopedico;


public class Servizi {
    public UserDAOimp userdaoimp;

	public Dottore dottore;
	public RicevimentoDAO ricDao;

	private Object registrazioneDAO;

	private Object serviziDAO;
	 public Servizi(Object registrazioneDAO, Object serviziDAO) {
	        this.userdaoimp = new UserDAOimp();
	        this.setRegistrazioneDAO(registrazioneDAO);
	        this.setServiziDAO(serviziDAO);
	    }
	
	
	public void ricevimento(Visiti visita) throws SQLException {
		ricDao.addRicevimento(visita);
		dottore.AddRicevimenti(visita);
	}
	
	
	
	public boolean serverificaCredenziali(String email, String password, String Tipo_utente, String ID) {
		
		return userdaoimp.verificaCredenziali(email, password, Tipo_utente, ID);
		
		
	}
	
	
	public boolean serRegisUtente(User user) {
		return userdaoimp.registraUtente(user);
	}
	
	public String sergetnomedottore(String nome) {
		
		return userdaoimp.getNomeDottore(nome);
		
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
