package Dottore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Registrazione extends JFrame implements ActionListener{
	JButton Chiuso = new JButton("CHIUSO");
    JButton Conferma= new JButton("CONFERMA");
            JLabel Email = new JLabel("EMAIL");
    		  JLabel Password = new JLabel("PASSWORD");
    		  	JLabel Nome = new JLabel("NOME");
				  JLabel Cognome  = new JLabel("COGNOME");
				  	JLabel CodiceFiscale = new JLabel("CODICE FISCALE");
				  	 JLabel DataNascita = new JLabel("DATA DI NASCITA");
				      JLabel Indirizzio = new JLabel("INDIRIZZIO");
				       JLabel Registirform = new JLabel("NOUVO UTENTE");
				        JCheckBox show = new JCheckBox("MOSTRA IL PASSWORD");
				        JRadioButton Radio1= new JRadioButton(" DOTTORE STOMACO");
				        JRadioButton Radio2= new JRadioButton(" DOTTORE OCCHI");
				        JRadioButton Radio3= new JRadioButton(" DOTTORE DENTI");
				        						//TEXT FILED EMAIL
	JTextField email = new JTextField();	
		JPasswordField pass = new JPasswordField();	//TEXT FILED PASSWORD
			JTextField nome = new JTextField();								 
	     		JTextField cognome = new JTextField();	
	     			JTextField codicefiscale = new JTextField();	
	     				JTextField datanascita = new JTextField();	
	     					JTextField indirizzio = new JTextField();	
	     						JPanel panel = new JPanel(null); 
	     						 ButtonGroup radioGroup = new ButtonGroup();
	     					   JLabel ID = new JLabel("ID");
	     					  JTextField idField = new JTextField();
	public Registrazione() {
			this.setTitle("REGISTRAZIONE");
	        this.setLayout(null);
	        this.setResizable(false);
	        this.setSize(1000, 1000);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.add(panel);
	        panel.add(Registirform);
	        	panel.add(Email);
	        		panel.add(Password);
	        			panel.add(Nome);
	        				panel.add(Cognome);
	        					panel.add(CodiceFiscale);
	        						panel.add(DataNascita);
	        							panel.add(Indirizzio);
	        								panel.add(email);
	        									panel.add(pass);
	        										panel.add(nome);
	        											panel.add(cognome);
	        												panel.add(codicefiscale);
	        													panel.add(datanascita);
	        														panel.add(indirizzio);
	        															panel.add(Conferma);
	        																panel.add(Chiuso);
	        																 	panel.add(show);
	        																 		panel.add(Radio1);
	        																 			panel.add(Radio2);
	        																 				panel.add(Radio3);
	        																 				panel.add(ID);
	        																 				panel.add(idField);
	        																 				// DIMENSIONI PER TESTI E BORDI	      
	        panel.setBounds(0, 0, 1000, 1000);
	        Registirform.setBounds(330, 200, 600, 70);
	        Email.setBounds(200, 320, 150, 100);
	        email.setBounds(300, 350, 400, 40);
	        Password.setBounds(135, 400, 200, 40);
	        pass.setBounds(300, 400, 400, 40);
	        Nome.setBounds(200, 420, 150, 100);
	        nome.setBounds(300, 450, 400, 40);
	        Cognome.setBounds(150, 470, 150, 100);
	        cognome.setBounds(300, 500, 400, 40);
	        CodiceFiscale.setBounds(60,520,300,100 );
	        codicefiscale.setBounds(300,550,400,40);
	        DataNascita.setBounds(50, 570, 300, 100);
	        datanascita.setBounds(300,600,400,40); 
	        Indirizzio.setBounds(120, 620, 300, 100);
	        indirizzio.setBounds(300, 650, 400, 40);
	        Chiuso.setBounds(50, 800, 150, 50);
	        Conferma.setBounds(760, 800, 190, 50);
	        show.setBounds(750, 410, 180, 20);
	        Radio1.setBounds(200, 290, 170, 20);
	        Radio2.setBounds(750, 290, 130, 20);
	        Radio3.setBounds(550, 290, 130, 20);
	        ID.setBounds(190, 670, 150, 100);
	        idField.setBounds(300, 700, 400, 40);
	     
	        panel.setBackground(Color.decode("#89CFF0"));
	        Conferma.setBackground(Color.LIGHT_GRAY);
	        Chiuso.setBackground(Color.LIGHT_GRAY);
	        show.setBackground(Color.decode("#89CFF0"));
	        Radio1.setBackground(Color.decode("#89CFF0"));
	        Radio2.setBackground(Color.decode("#89CFF0"));
	        Radio3.setBackground(Color.decode("#89CFF0"));
	        Conferma.setFont(new Font("Congenial black",Font.BOLD,20));
	        Chiuso.setFont(new Font("Congenial black",Font.BOLD,20));
	        Registirform.setFont(new Font("ALGERIAN",Font.BOLD,50));
	        Email.setFont(new Font("BODONI MT",Font.BOLD,30));
	        Password.setFont(new Font("BODONI MT",Font.BOLD,30));
	        Nome.setFont(new Font("BODONI MT",Font.BOLD,30));
	        Cognome.setFont(new Font("BODONI MT",Font.BOLD,30));
	        CodiceFiscale.setFont(new Font("BODONI MT",Font.BOLD,30));
	        DataNascita.setFont(new Font("BODONI MT",Font.BOLD,30));
	        Indirizzio.setFont(new Font("BODONI MT",Font.BOLD,30));
	        ID.setFont(new Font("BODONI MT",Font.BOLD,30));

	        show.addActionListener(this);
	        Chiuso.addActionListener(this);
	        Conferma.addActionListener(this);
	        Radio1.addActionListener(this);
	        Radio2.addActionListener(this);
	        Radio3.addActionListener(this);

	       	ID.setVisible(false);
	       	idField.setVisible(false);
	        radioGroup.add(Radio1);
	        radioGroup.add(Radio2);
	        radioGroup.add(Radio3);
	 
	}

	public static void main(String[] args) {
		 new Registrazione().setVisible(true);

	}
	 @Override  
	 public void actionPerformed(ActionEvent e) {
		    if (e.getSource() == show) {
		        if (show.isSelected()) {
		            pass.setEchoChar('\0');
		            show.setText("NASCONDI LA PASSWORD");
		        } else {
		            pass.setEchoChar('*');
		            show.setText("MOSTRARE LA PASSWORD");
		        }
		    } else if (e.getSource() == Chiuso) {
		        int c = JOptionPane.showConfirmDialog(null, "VOUI CHIUDERE L'REGISTRAZIONE", "SCELTA", JOptionPane.YES_NO_OPTION);
		        if (c == 0) {
		            System.exit(EXIT_ON_CLOSE);
		        }
		    } else if (e.getSource() == Conferma) {
		         if (!Radio1.isSelected() && !Radio2.isSelected() && !Radio3.isSelected()) {
		            JOptionPane.showMessageDialog(this, "DEVI SELEZIONARE UN'OPZIONE TRA DOTTORE E PAZIENTE", "ERRORE", JOptionPane.ERROR_MESSAGE);
		        } else {
		            registerUser();
		        }
		    } else if (e.getSource() == Radio1) {
		        ID.setVisible(true);
		     	idField.setVisible(true);
		    } else if (e.getSource() == Radio2) {
		        ID.setVisible(true);
		     	idField.setVisible(true);
		    } else if (e.getSource() == Radio3) {
		     	idField.setVisible(true);
		        ID.setVisible(true);
		    }

		    panel.revalidate();
		    panel.repaint();
		}
	 
	

	 private void registerUser() {
		    String emailText = email.getText(); // Usa getText() per ottenere il valore reale
		    String passwordText = new String(pass.getPassword());
		    String nomeText = nome.getText();
		    String cognomeText = cognome.getText();
		    String codiceFiscaleText = codicefiscale.getText();
		    String dataNascitaText = datanascita.getText(); // Format: ANNO-MESE-GIORNO
		    String indirizzioText = indirizzio.getText();
		    String idSpecializzazione = idField.getText();
		 
		    String tipoUtente = null;
		    String insertSQL = null;
		
		    if (Radio1.isSelected()) {
		        tipoUtente = "DOTTORE STOMACO";
		        insertSQL = "INSERT INTO user (email, password, Nome, Cognome, Codice_fiscale, Data_di_Nascita, Indirizzio, Tipo_di_utente, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        idSpecializzazione = idField.getText();
		    } else if (Radio2.isSelected()) {
		        tipoUtente = "DOTTORE OCCHI";
		        insertSQL = "INSERT INTO user (email, password, Nome, Cognome, Codice_fiscale, Data_di_Nascita, Indirizzio, Tipo_di_utente, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        idSpecializzazione = idField.getText();
		    } else if (Radio3.isSelected()) {
		        tipoUtente = "DOTTORE DENTI";
		        insertSQL = "INSERT INTO user (email, password, Nome, Cognome, Codice_fiscale, Data_di_Nascita, Indirizzio, Tipo_di_utente, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        idSpecializzazione = idField.getText();
		    }

		    if (emailText.isEmpty() || passwordText.isEmpty() || nomeText.isEmpty() || cognomeText.isEmpty() ||
		        codiceFiscaleText.isEmpty() || dataNascitaText.isEmpty() || indirizzioText.isEmpty() || idSpecializzazione.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Compila tutti i campi obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    if (tipoUtente != null && insertSQL != null) {
		        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
		             PreparedStatement pstmt = con.prepareStatement(insertSQL)) {

		            pstmt.setString(1, emailText);
		            pstmt.setString(2, passwordText);
		            pstmt.setString(3, nomeText);
		            pstmt.setString(4, cognomeText);
		            pstmt.setString(5, codiceFiscaleText);
		            pstmt.setString(6, dataNascitaText);
		            pstmt.setString(7, indirizzioText);
		            pstmt.setString(8, tipoUtente);
		            pstmt.setString(9, idSpecializzazione);

		            pstmt.executeUpdate();

		            JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo:)");

		        } catch (SQLException e) {
		            e.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Errore durante la registrazione!", "Errore", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Errore: Tipo di utente non definito.", "Errore", JOptionPane.ERROR_MESSAGE);
		    }
		}
}