package DataBase;

import java.sql.SQLException;
import java.util.List;

public interface OrarioDottoreDAO {
    void addOrarioDottore(String cognome, String nome, String email, String numeroDiTelefono, String orario) throws SQLException;
    void deleteOrarioDottore(String email) throws SQLException;
    List<String[]> getAllOrariDottore() throws SQLException;
}
