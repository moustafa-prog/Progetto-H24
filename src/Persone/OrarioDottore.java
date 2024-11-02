package Persone;

public class OrarioDottore {
    private String cognome;
    private String nome;
    private String email;
    private String numeroDiTelefono;
    private String orario;

    // Costruttore completo
    public OrarioDottore(String cognome, String nome, String email, String numeroDiTelefono, String orario) {
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.numeroDiTelefono = numeroDiTelefono;
        this.orario = orario;
    }

    // Getter e Setter per ogni campo
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    @Override
    public String toString() {
        return "OrarioDottore{" +
                "cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", numeroDiTelefono='" + numeroDiTelefono + '\'' +
                ", orario='" + orario + '\'' +
                '}';
    }
}
