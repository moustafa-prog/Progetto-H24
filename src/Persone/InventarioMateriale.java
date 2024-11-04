package Persone;

public class InventarioMateriale {
    private String nomeMateriale;
    private int quantitaDisponibile;
    private int quantitaMinima;
    private String descrizione;
    private String statoOrdine;

    public InventarioMateriale(String nomeMateriale, int quantitaDisponibile, int quantitaMinima, String descrizione, String statoOrdine) {
        this.nomeMateriale = nomeMateriale;
        this.quantitaDisponibile = quantitaDisponibile;
        this.quantitaMinima = quantitaMinima;
        this.descrizione = descrizione;
        this.statoOrdine = statoOrdine;
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

    }