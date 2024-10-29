package Dottore;

public class Servizi {
	 private String nomeDottore;
	    private String prezzo;
	    private String descrizioneMalattia;
	    private String trattamento;
	    private String codiceFiscale;

	    public Servizi(String nomeDottore, String prezzo, String descrizioneMalattia, String trattamento, String codiceFiscale) {
	        this.nomeDottore = nomeDottore;
	        this.prezzo = prezzo;
	        this.descrizioneMalattia = descrizioneMalattia;
	        this.trattamento = trattamento;
	        this.codiceFiscale = codiceFiscale;
	    }

		public String getNomeDottore() {
			return nomeDottore;
		}

		public void setNomeDottore(String nomeDottore) {
			this.nomeDottore = nomeDottore;
		}

		public String getPrezzo() {
			return prezzo;
		}

		public void setPrezzo(String prezzo) {
			this.prezzo = prezzo;
		}

		public String getDescrizioneMalattia() {
			return descrizioneMalattia;
		}

		public void setDescrizioneMalattia(String descrizioneMalattia) {
			this.descrizioneMalattia = descrizioneMalattia;
		}

		public String getTrattamento() {
			return trattamento;
		}

		public void setTrattamento(String trattamento) {
			this.trattamento = trattamento;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
		}
	

}
