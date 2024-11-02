package Persone;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VisitiDAO {
    List<Visiti> getVisitiByDate(LocalDate date) throws SQLException;
    void deleteVisiti(String codiceFiscale) throws SQLException;
}
