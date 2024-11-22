package Model;

public class Prenotazione {
   
    private String cognome;
    private String nome;
    private String codiceFiscale;
 
    private String data;
    private String ora;
    private String TipoDiUtente;
    // Costruttore
    public Prenotazione( String cognome, String nome, String codiceFiscale, String data, String ora, String TipoDiUtente) {
   
        this.cognome = cognome;
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
      
        this.data = data;
        this.ora = ora;
    }

	
	public String getTipoDiUtente() {
		return TipoDiUtente;
	}


	public void setTipoDiUtente(String tipoDiUtente) {
		TipoDiUtente = tipoDiUtente;
	}


	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}


}

