package DataBase;

import java.sql.SQLException;

import Model.Prenotazione;


public interface PrenotazioneDAO {
	void addPrenotazione(Prenotazione successivo) throws SQLException;
    void eliminaTutti() throws SQLException;

}
