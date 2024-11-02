package Persone;

@SuppressWarnings("hiding")
public interface UserDAO <Login>{

	boolean verificaCredenziali(String username, String password, String tipoUtente, String idUtente);

	String getNomeDottore(String username);

}
