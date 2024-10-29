package Dottore;

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
    public String getPassword() { return password; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public String getDataNascita() { return dataNascita; }
    public String getIndirizzio() { return indirizzio; }
    public String getTipoUtente() { return tipoUtente; }
    public String getIdSpecializzazione() { return idSpecializzazione; }

    
}
