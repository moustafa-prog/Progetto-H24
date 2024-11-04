package Modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Persone.Visiti;

public interface VisitiDAO {
    List<Visiti> getVisitiByDate(LocalDate date) throws SQLException;
    void deleteVisiti(String codiceFiscale) throws SQLException;
}
