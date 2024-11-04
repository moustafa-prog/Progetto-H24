package Modelo;

import java.sql.SQLException;

import Persone.Prenotazione;


public interface PrenotazioneDAO {
	void addPrenotazione(Prenotazione successivo) throws SQLException;
    void eliminaTutti() throws SQLException;

}
