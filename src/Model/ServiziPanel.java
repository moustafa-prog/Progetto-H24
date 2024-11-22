 package Model;



public class ServiziPanel {

	    private String prezzo;
	    private String descrizioneMalattia;
	    private String trattamento;
	    private String codiceFiscale;
	    private String Tipo_di_utente;
	    public ServiziPanel( String prezzo, String descrizioneMalattia, String trattamento, String codiceFiscale,String Tipo_di_utente) {
	      
	        this.prezzo = prezzo;
	        this.descrizioneMalattia = descrizioneMalattia;
	        this.trattamento = trattamento;
	        this.codiceFiscale = codiceFiscale;
	        this.Tipo_di_utente = Tipo_di_utente; 
	      
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



		public String getTipo_di_utente() {
			return Tipo_di_utente;
		}



		public void setTipo_di_utente(String tipo_di_utente) {
			Tipo_di_utente = tipo_di_utente;
		}
	

}
