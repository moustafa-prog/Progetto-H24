package DataBase;

import java.sql.SQLException;
import java.util.List;

import Model.Paziente;

public interface PazienteDAO {
    void addPaziente(Paziente paziente) throws SQLException;
    void deletePaziente(String codiceFiscale) throws SQLException;
      List<Paziente> getAllPazienti() throws SQLException;
}
