package Model;

public class InventarioMateriale {
    private String nomeMateriale;
    private int quantitaDisponibile;
    private int quantitaMinima;
    private String descrizione;
    private String statoOrdine;
    private String Tipo_di_utente;

    public InventarioMateriale(String nomeMateriale, int quantitaDisponibile, int quantitaMinima, String descrizione, String statoOrdine, String Tipo_di_utente) {
        this.nomeMateriale = nomeMateriale;
        this.quantitaDisponibile = quantitaDisponibile;
        this.quantitaMinima = quantitaMinima;
        this.descrizione = descrizione;
        this.statoOrdine = statoOrdine;
        this.setTipo_di_utente(Tipo_di_utente);
    }

	public String getNomeMateriale() {
		return nomeMateriale;
	}

	public void setNomeMateriale(String nomeMateriale) {
		this.nomeMateriale = nomeMateriale;
	}

	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public int getQuantitaMinima() {
		return quantitaMinima;
	}

	public void setQuantitaMinima(int quantitaMinima) {
		this.quantitaMinima = quantitaMinima;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStatoOrdine() {
		return statoOrdine;
	}

	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}

	public String getTipo_di_utente() {
		return Tipo_di_utente;
	}

	public void setTipo_di_utente(String tipo_di_utente) {
		Tipo_di_utente = tipo_di_utente;
	}

    }