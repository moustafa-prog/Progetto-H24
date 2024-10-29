package Dottore;

@SuppressWarnings("hiding")
public interface UserDAO <Login>{

	boolean verificaCredenziali(String username, String password, String tipoUtente, String idUtente);

}
