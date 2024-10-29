package Dottore;

import java.sql.SQLException;


public interface SuccessiviDAO {
	void addSuccessivo(Successivi successivo) throws SQLException;
    void eliminaTutti() throws SQLException;
}
