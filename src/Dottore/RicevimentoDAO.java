package Dottore;

import java.sql.SQLException;
import java.util.List;



public interface RicevimentoDAO {
    void addRicevimento(Ricevimenti ricevimento) throws SQLException;
    void deleteRicevimento(String codiceFiscale) throws SQLException;
    List<Ricevimenti> getRicevimentiByDate(String data) throws SQLException;
  
    List<Ricevimenti> getAllRicevimenti() throws SQLException;
}
