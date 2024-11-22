package Controller;

import DataBase.REGDAOimp;
import DataBase.UserDAO;
import Model.User;
import Model.Visiti;
import servizi.Servizi;
import java.sql.SQLException;




public class DottoreController {
	
	public Servizi servizio;
	@SuppressWarnings("rawtypes")
	public UserDAO userDAO;
	
	
	
	private REGDAOimp registrazioneDAO;
	
	
	

	public DottoreController() {
	    this.servizio = new Servizi( registrazioneDAO,  servizio);
	}
	
	public boolean conregUtente(User user) {
		return servizio.serRegisUtente(user);
	}

	
	
	
	 
	
	 
	
	public boolean converificred(String email, String password, String Tipo_utente, String ID){
		return servizio.serverificaCredenziali(email, password, Tipo_utente, ID);
	};
	
	
	
	
	
	public void conHomepage(String Tipo_utente, String nomeDottore) {
		servizio.homePage(Tipo_utente, nomeDottore);;
	}
	
	
	
	
	public String congetnomedott(String nome) {
		return servizio.sergetnomedottore(nome);
	};
	
	public void addRicevimento(Visiti visita) throws SQLException{
		
		servizio.ricevimento(visita);
	
	
	}

	
	

}
