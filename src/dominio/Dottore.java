package dominio;


import java.util.Date;

public class Dottore extends User {

	String email;
	String password;
	String specializzazione;
	String idSpecializzazione;
	String nome;
	String cognome;
	String codice_fiscale;
	Date data_nascita;
	String indrizzio;
	String tipo;
	
	public Dottore(String email, String password, String nome, String cognome, String codiceFiscale, String dataNascita,
            String indirizzo, String specializzazione, String idSpecializzazione) {
		super(email, password, nome, cognome, codiceFiscale, dataNascita, indirizzo, idSpecializzazione, idSpecializzazione);
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.specializzazione = specializzazione;
		this.idSpecializzazione = idSpecializzazione;
	}
	
	public String getemail() {return email;}
	public String getpassword() {return password;}
	public String getnome() {return nome;}
	public String getspecializzazione() {return specializzazione;}
	public String getidSpecializzazione() {return idSpecializzazione;}
	
	public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public String getIdSpecializzazione() {
        return idSpecializzazione;
    }

    public void setIdSpecializzazione(String idSpecializzazione) {
        this.idSpecializzazione = idSpecializzazione;
    }
	
	public Visiti AddRicevimenti(Visiti visita){
		
		return visita;
		
	}

		
	
	
	
	
}	
