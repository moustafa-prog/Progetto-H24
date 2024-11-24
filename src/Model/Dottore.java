package Model;



public class Dottore extends User {

    private String email;
    private String password;
    private String specializzazione;
    private String idSpecializzazione;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String dataNascita;
    private String indirizzo;

    public Dottore(String email, String password, String nome, String cognome, String codiceFiscale, String dataNascita,
                   String indirizzo, String specializzazione, String idSpecializzazione) {
        super(email, password, nome, cognome, codiceFiscale, dataNascita, indirizzo, specializzazione, idSpecializzazione);
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.specializzazione = specializzazione;
        this.idSpecializzazione = idSpecializzazione;
        this.cognome=cognome; 
        this.codiceFiscale=codiceFiscale; 
        this.dataNascita=dataNascita; 
        this.indirizzo=indirizzo; 
        
    }

    public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

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

    public void DeleteRicevimet(String codiceFiscale) {
        // Logica concreta per eliminare il ricevimento
        System.out.println("Ricevimento con codice fiscale " + codiceFiscale + " eliminato.");
    }


	public Ricevimenti AddRicevimenti(Ricevimenti ricevimento){
		
		return ricevimento;
		
	}


	
}
