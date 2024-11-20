package DataBase;

import java.sql.SQLException;
import java.util.List;

import dominio.Paziente;

public interface PazienteDAO {
    void addPaziente(Paziente paziente) throws SQLException;
    void deletePaziente(String codiceFiscale) throws SQLException;
      List<Paziente> getAllPazienti() throws SQLException;
}
