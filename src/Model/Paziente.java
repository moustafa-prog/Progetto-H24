package Model;

public class Paziente {
    private String cognome;
    private String nome;
    private String dataDiNascita;
    private String codiceFiscale;
    private String nomeDottore;
    private String Tipo_di_utente;
    public Paziente(String cognome, String nome, String dataDiNascita, String codiceFiscale, String nomeDottore,String Tipo_di_utente) {
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.nomeDottore = nomeDottore;
        this.Tipo_di_utente = Tipo_di_utente;
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

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNomeDottore() {
		return nomeDottore;
	}

	public void setNomeDottore(String nomeDottore) {
		this.nomeDottore = nomeDottore;
	}

	public String getTipo_di_utente() {
		return Tipo_di_utente;
	}

	public void setTipo_di_utente(String tipo_di_utente) {
		Tipo_di_utente = tipo_di_utente;
	}


	

}
