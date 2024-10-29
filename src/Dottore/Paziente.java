package Dottore;

public class Paziente {
    private String cognome;
    private String nome;
    private String dataDiNascita;
    private String codiceFiscale;
    private String nomeDottore;

    public Paziente(String cognome, String nome, String dataDiNascita, String codiceFiscale, String nomeDottore) {
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.nomeDottore = nomeDottore;
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


	

}
