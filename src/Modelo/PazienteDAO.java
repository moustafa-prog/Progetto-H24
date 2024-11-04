package Modelo;

import java.sql.SQLException;
import java.util.List;

import Persone.Paziente;

public interface PazienteDAO {
    void addPaziente(Paziente paziente) throws SQLException;
    void deletePaziente(String codiceFiscale) throws SQLException;
      List<Paziente> getAllPazienti() throws SQLException;
}
