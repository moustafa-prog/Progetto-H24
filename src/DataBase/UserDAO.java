package DataBase;

import dominio.User;

public interface UserDAO <Login>{

	boolean verificaCredenziali(String username, String password, String specializzazione, String idSpecializzazione);

	String getNomeDottore(String username);

	boolean registraUtente(User user);

}


//String tipoUtente, String idUtente,