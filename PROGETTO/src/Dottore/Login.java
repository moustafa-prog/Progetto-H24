package Dottore;

import java.awt.Color;
import java.awt.Font;

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

import Dottore_Denti.InterfacciaPrincipaleDenti;
import Dottore_Occhi.InterfacciaPrincipaleOcchi;
import Dottore_Stomaco.InterfacciaPrincipaleStomaco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
    JButton login = new JButton("ACCESSO");
    JButton Registrazione = new JButton("REGISTRAZIONE");
    JLabel l_name = new JLabel("E-MAIL");
    JLabel l_pass = new JLabel("PASSWORD");
    JTextField txt_name = new JTextField();
    JPasswordField pass = new JPasswordField();
    JCheckBox show = new JCheckBox("MOSTRA IL PASSWORD");
    JLabel loginform = new JLabel("BENVENUTO");
    JPanel panel = new JPanel(null); 
    JRadioButton Radio1= new JRadioButton("SONO DOTTORE STOMACO");
    JRadioButton Radio2= new JRadioButton("SONO DOTTORE OCCHI");
    JRadioButton Radio3= new JRadioButton("SONO DOTTORE DENTI");
    JTextField iD_StomacoField = new JTextField();
    JTextField iD_OcchiField = new JTextField();
    JTextField iD_DentiField = new JTextField();
    ButtonGroup radioGroup = new ButtonGroup();
    public Login() {
        this.setTitle("ACCESSO");
        this.setLayout(null);
       this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panel);
        this.setResizable(false);
        
        panel.setBounds(0, 0, 1000, 1000);
        loginform.setBounds(350, 200, 600, 100);
        l_name.setBounds(180, 450, 120, 35);
        txt_name.setBounds(300, 450, 400, 35);
        l_pass.setBounds(135, 500, 180, 35);
        pass.setBounds(300, 500, 400, 35);
        show.setBounds(750, 500, 150, 20);
        login.setBounds(550, 650, 150, 25);
        Registrazione.setBounds(300, 650, 200, 25);
        Radio1.setBounds(200,550,200,25);
       Radio2.setBounds(500,550,200,25);
       Radio3.setBounds(750,550,200,25);
        // AGGUINGE IL COLORE 
        panel.setBackground(Color.decode("#89CFF0"));
        login.setBackground(Color.LIGHT_GRAY);
        Registrazione.setBackground(Color.LIGHT_GRAY);
        show.setBackground(Color.decode("#89CFF0"));
        Radio1.setBackground(Color.decode("#89CFF0"));
        Radio2.setBackground(Color.decode("#89CFF0"));
        Radio3.setBackground(Color.decode("#89CFF0"));
        // CAMBIAMENTO LA FONTE
        loginform.setFont(new Font("ALGERIAN",Font.BOLD,50));
        l_name.setFont(new Font("BODONI MT",Font.BOLD,30));
        l_pass.setFont(new Font("BODONI MT",Font.BOLD,30));
        login.setFont(new Font("Congenial black",Font.BOLD,20));
        Registrazione.setFont(new Font("Congenial black",Font.BOLD,20));
        show.setFont(new Font("Congenial black",Font.BOLD,10));

        // PER MOSTRA TUTTO NEL NOSRTA INTERFACCIA
        panel.add(loginform);
        panel.add(l_name);
        panel.add(l_pass);
        panel.add(txt_name);
        panel.add(pass);
        panel.add(login);
        panel.add(Registrazione);
        panel.add(show);
        panel.add(Radio1);
        panel.add(Radio2);
        panel.add(Radio3);
        panel.add(iD_StomacoField);
        panel.add(iD_OcchiField);
        panel.add(iD_DentiField);
        radioGroup.add(Radio1);
        radioGroup.add(Radio2);
        radioGroup.add(Radio3);
       
        
        show.addActionListener(this);
        Registrazione.addActionListener(this);
        login.addActionListener(this);
        Radio1.addActionListener(this);
        Radio2.addActionListener(this);
        Radio3.addActionListener(this);
        iD_StomacoField.setVisible(false);
        iD_OcchiField.setVisible(false);
        iD_DentiField.setVisible(false);
        Registrazione.setVisible(true);
    }
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
    
    @Override                // L'ACTION LISTENER DELL'INTERFACCIA LOGIN
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()== show){
           if(show.isSelected()){
               pass.setEchoChar('\0');
               show.setText("NASCONDI LA PASSWORD");
           }else{
                      pass.setEchoChar('*');
                      show.setText("MOSTRARE LA PASSWORD");
                       }
        }else if (e.getSource() == Registrazione) {
            new Registrazione().setVisible(true);
    	}else 
    		if(e.getSource()== login) {
    			 
    		checkEmail();
    
		} 
    		else if (e.getSource() == Radio1) {
                iD_StomacoField.setVisible(true);
                iD_OcchiField.setVisible(false);
                iD_DentiField.setVisible(false);
           
                panel.add(iD_StomacoField);
                iD_StomacoField.setBounds(300, 580, 400, 35);
                panel.revalidate();
                panel.repaint();
            } else if (e.getSource() == Radio2) {
            	iD_OcchiField.setVisible(true);
            	iD_StomacoField.setVisible(false);
            	  iD_DentiField.setVisible(false);
          
                panel.add(iD_OcchiField);
                iD_OcchiField.setBounds(300, 580, 400, 35);
                panel.revalidate();
                panel.repaint();
              
        }else if (e.getSource() == Radio3) {
        	iD_OcchiField.setVisible(false);
        	iD_StomacoField.setVisible(false);
        	iD_DentiField.setVisible(true);
         
            panel.add(iD_DentiField);
            iD_DentiField.setBounds(300, 580, 400, 35);
            panel.revalidate();
            panel.repaint();
        }
    }
    @SuppressWarnings("unused")
	public void checkEmail() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Progetto", "root", "Moustafa2001");
            String username = txt_name.getText();
            String password = new String(pass.getPassword());
            String sql = null;
            PreparedStatement pst = null;

            // Verifica il tipo di utente e prepara la query SQL appropriata
            if (Radio1.isSelected()) {
                String id = iD_StomacoField.getText();
                sql = "SELECT * FROM user WHERE email = ? AND password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
            } else if (Radio2.isSelected()) {
                String id = iD_OcchiField.getText();
                sql = "SELECT * FROM user WHERE email = ? AND password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
            } else if (Radio3.isSelected()) {
                String id = iD_DentiField.getText();
                sql = "SELECT * FROM user WHERE email = ? AND password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
            }

            // Esegui la query e controlla se i dati inseriti sono validi
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // Login riuscito, ora verifica il tipo di utente e l'ID
               String tipoUtente = rs.getString("Tipo_di_utente");
                boolean Tipo_di_utente = false;

                if (Radio1.isSelected() && tipoUtente.equals("DOTTORE STOMACO")) {
                    String id = rs.getString("ID");
                    if (id.equals(iD_StomacoField.getText())) {
                    	Tipo_di_utente = true;
                    	  new InterfacciaPrincipaleStomaco().setVisible(true);     
                    	  }
                } else if (Radio2.isSelected() && tipoUtente.equals("DOTTORE OCCHI")) {
                    String id = rs.getString("ID");
                    if (id.equals(iD_OcchiField.getText())) {
                    	Tipo_di_utente = true;
                    	new InterfacciaPrincipaleOcchi().setVisible(true);    
                    }
                } else if (Radio3.isSelected() && tipoUtente.equals("DOTTORE DENTI")) {
                    String id = rs.getString("ID");
                    if (id.equals(iD_DentiField.getText())) {
                    	Tipo_di_utente = true;
                    	new InterfacciaPrincipaleDenti().setVisible(true);  
                    }
                }

                if (Tipo_di_utente) {
                    // Login riuscito, apri l'interfaccia principale
                    JOptionPane.showMessageDialog(null, "Login effettuato con successo!");
              
                } else {
                    // Le credenziali sono corrette, ma il tipo di utente o l'ID non corrispondono
                    JOptionPane.showMessageDialog(null, "Credenziali corrette, ma tipo di utente o ID non corrispondono", "Errore", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                // Credenziali non valide
                JOptionPane.showMessageDialog(null, "Credenziali non valide", "Errore", JOptionPane.ERROR_MESSAGE);
            }

            // Chiudi le risorse
            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il login: " + ex.getMessage());
        }
    }
}