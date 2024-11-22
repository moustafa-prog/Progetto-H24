package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  
import java.sql.Statement;



public class DataBase {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "20012001");
   
            String  SQL = "CREATE TABLE  Persone (" +
                                    "Email VARCHAR(50) NOT NULL, " +
                                    "Password VARCHAR(25) NOT NULL, " +
                                    "Nome VARCHAR(20) NOT NULL, " +
                                    "Cognome VARCHAR(20) NOT NULL, " +
                                    "Codice_fiscale VARCHAR(30) NOT NULL, " +
                                    "Data_nascita DATE NOT NULL, " +
                                    "Indirizzo VARCHAR(30) NOT NULL)"+
                                    "ID VARCHAR(6)NOT NULL";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        

            String insertSQL = "INSERT INTO Dottore (Email, Password, Nome, Cognome, Codice_Fiscale, Data_Nascita, Indirizzo, ID, Tipo_utente) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(insertSQL);
         
            pstmt.setString(1, "");
            pstmt.setString(2,"");
            pstmt.setString(3, "");
            pstmt.setString(4, "");
            pstmt.setString(5,"");
            pstmt.setString(6,"");
            pstmt.setString(7, "");
            pstmt.setString(8, "");
            pstmt.setString(9, "");
            pstmt.executeUpdate();

            System.out.println("Successo!");

            pstmt.close();
            stmt.close();
            con.close();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	
	}
