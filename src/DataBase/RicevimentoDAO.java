package DataBase;

import java.sql.SQLException;
import java.util.List;

import Model.Ricevimenti;
import Model.Visiti;




public interface RicevimentoDAO {
    void addRicevimento(Visiti visita) throws SQLException;
    void deleteRicevimento(String codiceFiscale) throws SQLException;
  
  
    List<Ricevimenti> getAllRicevimenti() throws SQLException;
}
