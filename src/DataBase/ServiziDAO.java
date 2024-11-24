package DataBase;

import java.sql.SQLException;
import java.util.List;

import Model.ServiziPanel;

public interface ServiziDAO {
	void addServizi(ServiziPanel servizi) throws SQLException;
    void deleteServizi(String codiceFiscale) throws SQLException;
    List<ServiziPanel> searchByCodiceFiscale(String codiceFiscale ) throws SQLException;
  //  List<Servizi> getAllServices() throws SQLException;

	
	}
    
