package Modelo;

import java.sql.SQLException;

import Persone.User;

public interface RegistrazioneDAO {

 void registraUtente(User user) throws SQLException;
	
	
}
