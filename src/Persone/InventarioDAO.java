package Persone;

import java.sql.SQLException;
import java.util.List;

public interface InventarioDAO {
    void addMateriale(InventarioMateriale materiale) throws SQLException;
    void deleteMateriale(String nomeMateriale) throws SQLException;
    List<InventarioMateriale> getAllMateriali() throws SQLException;
    void orderMateriale(String nomeMateriale) throws SQLException;
}
