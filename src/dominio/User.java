package dominio;

public class User {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String dataNascita;
    private String indirizzio;
    private String tipoUtente;
    private String idSpecializzazione;

    // Costruttore
    public User(String email, String password, String nome, String cognome,
                String codiceFiscale, String dataNascita, String indirizzio,
                String tipoUtente, String idSpecializzazione) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataNascita = dataNascita;
        this.indirizzio = indirizzio;
        this.tipoUtente = tipoUtente;
        this.idSpecializzazione = idSpecializzazione;
    }

    
	

	// Getter e Setter
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getDataNascita() { return dataNascita; }
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }
    public String getIndirizzio() { return indirizzio; }
    public void setIndirizzio(String indirizzio) {
        this.indirizzio = indirizzio;
    }
    public String getTipoUtente() { return tipoUtente; }
    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }
    public String getIdSpecializzazione() { return idSpecializzazione; }
    public void setIdSpecializzazione(String idSpecializzazione) {
        this.idSpecializzazione = idSpecializzazione;
    }




	

    
}
