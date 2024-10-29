package Dottore;

public class Successivi {
    private String nomeDottore;
    private String cognome;
    private String nome;
    private String codiceFiscale;
    private String prossimaVisita;
    private String data;
    private String ora;

    // Costruttore
    public Successivi(String nomeDottore, String cognome, String nome, String codiceFiscale, String prossimaVisita, String data, String ora) {
        this.nomeDottore = nomeDottore;
        this.cognome = cognome;
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.prossimaVisita = prossimaVisita;
        this.data = data;
        this.ora = ora;
    }

	public String getNomeDottore() {
		return nomeDottore;
	}

	public void setNomeDottore(String nomeDottore) {
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getProssimaVisita() {
		return prossimaVisita;
	}

	public void setProssimaVisita(String prossimaVisita) {
		this.prossimaVisita = prossimaVisita;
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

