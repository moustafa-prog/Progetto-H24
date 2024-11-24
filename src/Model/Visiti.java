package Model;

public class Visiti {

	private String cognome;
    private String nome;
    private String data;
    private String ora;
    private String codiceFiscale;


    public Visiti(String cognome, String nome, String data, String ora, String codiceFiscale) {
        this.cognome = cognome;
        this.nome = nome;
        this.data = data;
        this.ora = ora;
        this.codiceFiscale = codiceFiscale;
     
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    

}
