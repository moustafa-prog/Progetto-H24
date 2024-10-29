package Dottore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;  
import java.sql.Statement;



public class DataBase {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
   
            String  SQL = "CREATE TABLE  USER (" +
                                    "username VARCHAR(50) NOT NULL, " +
                                    "password VARCHAR(25) NOT NULL, " +
                                    "nome VARCHAR(20) NOT NULL, " +
                                    "cognome VARCHAR(20) NOT NULL, " +
                                    "codice_fiscale VARCHAR(30) NOT NULL, " +
                                    "data_nascita DATE NOT NULL, " +
                                    "indirizzo VARCHAR(30) NOT NULL)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
        

            String insertSQL = "INSERT INTO USER (username, password, nome, cognome, codice_fiscale, data_nascita, indirizzo) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(insertSQL);
         
            pstmt.setString(1, "");
            pstmt.setString(2,"");
            pstmt.setString(3, "");
            pstmt.setString(4, "");
            pstmt.setString(5,"");
            pstmt.setString(6,"");
            pstmt.setString(7, "");
            
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
