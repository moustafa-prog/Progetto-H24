/*
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


@SuppressWarnings("serial")
public class Registrazione extends JFrame implements ActionListener {


	private RegistrazioneDAO registrazioneDAO = new REGDAOimp(); // Usa l'implementazione del DAO
    JButton Chiuso = new JButton("CHIUSO");
    JButton Conferma = new JButton("CONFERMA");
    JLabel Email = new JLabel("EMAIL");
    JLabel Password = new JLabel("PASSWORD");
    JLabel Nome = new JLabel("NOME");
    JLabel Cognome = new JLabel("COGNOME");
    JLabel CodiceFiscale = new JLabel("CODICE FISCALE");
    JLabel DataNascita = new JLabel("DATA DI NASCITA");
    JLabel Indirizzio = new JLabel("INDIRIZZIO");
    JLabel Registirform = new JLabel("NUOVO UTENTE");
    JCheckBox show = new JCheckBox("MOSTRA LA PASSWORD");
    JRadioButton Radio1 = new JRadioButton("ORTOPEDICO");
    JRadioButton Radio2 = new JRadioButton("OCULISTA");
    JRadioButton Radio3 = new JRadioButton("DENTISTA");
    JTextField email = new JTextField();
    JPasswordField pass = new JPasswordField();
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

        // Aggiungi componenti al pannello
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

        // Imposta le dimensioni dei componenti
        panel.setBounds(0, 0, 1000, 1000);
        Registirform.setBounds(330, 200, 600, 70);
        Email.setBounds(200, 320, 150, 30);
        email.setBounds(300, 320, 400, 30);
        Password.setBounds(135, 360, 200, 30);
        pass.setBounds(300, 360, 400, 30);
        Nome.setBounds(200, 400, 150, 30);
        nome.setBounds(300, 400, 400, 30);
        Cognome.setBounds(150, 440, 150, 30);
        cognome.setBounds(300, 440, 400, 30);
        CodiceFiscale.setBounds(60, 480, 300, 30);
        codicefiscale.setBounds(300, 480, 400, 30);
        DataNascita.setBounds(50, 520, 300, 30);
        datanascita.setBounds(300, 520, 400, 30);
        Indirizzio.setBounds(120, 560, 300, 30);
        indirizzio.setBounds(300, 560, 400, 30);
        Chiuso.setBounds(50, 800, 150, 50);
        Conferma.setBounds(760, 800, 190, 50);
        show.setBounds(750, 370, 180, 20);
        Radio1.setBounds(200, 280, 170, 20);
        Radio2.setBounds(750, 280, 130, 20);
        Radio3.setBounds(550, 280, 130, 20);
        ID.setBounds(190, 600, 150, 30);
        idField.setBounds(300, 600, 400, 30);

        // Configura colori e font
        panel.setBackground(Color.decode("#89CFF0"));
        Conferma.setBackground(Color.LIGHT_GRAY);
        Chiuso.setBackground(Color.LIGHT_GRAY);
        show.setBackground(Color.decode("#89CFF0"));
        Radio1.setBackground(Color.decode("#89CFF0"));
        Radio2.setBackground(Color.decode("#89CFF0"));
        Radio3.setBackground(Color.decode("#89CFF0"));
        Conferma.setFont(new Font("Congenial black", Font.BOLD, 20));
        Chiuso.setFont(new Font("Congenial black", Font.BOLD, 20));
        Registirform.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        Email.setFont(new Font("BODONI MT", Font.BOLD, 30));
        Password.setFont(new Font("BODONI MT", Font.BOLD, 30));
        Nome.setFont(new Font("BODONI MT", Font.BOLD, 30));
        Cognome.setFont(new Font("BODONI MT", Font.BOLD, 30));
        CodiceFiscale.setFont(new Font("BODONI MT", Font.BOLD, 30));
        DataNascita.setFont(new Font("BODONI MT", Font.BOLD, 30));
        Indirizzio.setFont(new Font("BODONI MT", Font.BOLD, 30));
        ID.setFont(new Font("BODONI MT", Font.BOLD, 30));

        // Aggiungi listener
        show.addActionListener(this);
        Chiuso.addActionListener(this);
        Conferma.addActionListener(this);
        Radio1.addActionListener(this);
        Radio2.addActionListener(this);
        Radio3.addActionListener(this);

        // Imposta visibilità iniziale
        ID.setVisible(false);
        idField.setVisible(false);
        radioGroup.add(Radio1);
        radioGroup.add(Radio2);
        radioGroup.add(Radio3);
    }

    public Registrazione(String email2, char[] password2, String nome2, String cognome2, String codiceFiscale2,
			String dataNascita2, String indirizzio2) {
		// TODO Auto-generated constructor stub
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
                show.setText("MOSTRA LA PASSWORD");
            }
        } else if (e.getSource() == Chiuso) {
            int c = JOptionPane.showConfirmDialog(null, "VUOI CHIUDERE LA REGISTRAZIONE?", "SCELTA", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                this.dispose(); // Chiude solo la finestra di registrazione
            }
        } else if (e.getSource() == Conferma) {
            if (!Radio1.isSelected() && !Radio2.isSelected() && !Radio3.isSelected()) {
                JOptionPane.showMessageDialog(this, "DEVI SELEZIONARE UN'OPZIONE", "ERRORE", JOptionPane.ERROR_MESSAGE);
            } else {
                registerUser();
            }
        } else if (e.getSource() == Radio1 || e.getSource() == Radio2 || e.getSource() == Radio3) {
            ID.setVisible(true);
            idField.setVisible(true);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void registerUser() {
        String emailText = email.getText().trim();
        String passwordText = new String(pass.getPassword()).trim();
        String nomeText = nome.getText().trim();
        String cognomeText = cognome.getText().trim();
        String codiceFiscaleText = codicefiscale.getText().trim();
        String dataNascitaText = datanascita.getText().trim();
        String indirizzioText = indirizzio.getText().trim();
        String idSpecializzazione = idField.getText().trim();

        String tipoUtente = null;
        if (Radio1.isSelected()) {
            tipoUtente = "ORTOPEDICO";
        } else if (Radio2.isSelected()) {
            tipoUtente = "OCULISTA";
        } else if (Radio3.isSelected()) {
            tipoUtente = "DENTISTA";
        }

        // Controllo campi obbligatori
        if (emailText.isEmpty() || passwordText.isEmpty() || nomeText.isEmpty() || cognomeText.isEmpty() ||
            codiceFiscaleText.isEmpty() || dataNascitaText.isEmpty() || indirizzioText.isEmpty() || idSpecializzazione.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compila tutti i campi obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = new User(emailText, passwordText, nomeText, cognomeText, codiceFiscaleText, dataNascitaText, indirizzioText, tipoUtente, idSpecializzazione);
            registrazioneDAO.registraUtente(user);
            JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo!");
        } catch (Exception ex) {
            ex.printStackTrace();  // Stampa l'errore SQL
            JOptionPane.showMessageDialog(this, "Errore durante la registrazione: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }

    
    
 }
*/
package Dottore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Registrazione extends JFrame implements ActionListener {

    private RegistrazioneDAO registrazioneDAO = new REGDAOimp(); // Usa l'implementazione del DAO
    JButton chiuso = new JButton("CHIUSO");
    JButton conferma = new JButton("CONFERMA");
    
    // Definisci i componenti dell'interfaccia
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel nomeLabel = new JLabel("NOME");
    JLabel cognomeLabel = new JLabel("COGNOME");
    JLabel codiceFiscaleLabel = new JLabel("CODICE FISCALE");
    JLabel dataNascitaLabel = new JLabel("DATA DI NASCITA");
    JLabel indirizzioLabel = new JLabel("INDIRIZZIO");
    JLabel registrazioneFormLabel = new JLabel("NUOVO UTENTE");
    
    JCheckBox showPasswordCheckBox = new JCheckBox("MOSTRA LA PASSWORD");
    
    JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    JRadioButton dentistaRadio = new JRadioButton("DENTISTA");
    
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField nomeField = new JTextField();
    JTextField cognomeField = new JTextField();
    JTextField codiceFiscaleField = new JTextField();
    JTextField dataNascitaField = new JTextField();
    JTextField indirizzioField = new JTextField();
    
    JPanel panel = new JPanel(null);
    ButtonGroup radioGroup = new ButtonGroup();
    
    JLabel idLabel = new JLabel("ID");
    JTextField idField = new JTextField();

    public Registrazione() {
        setTitle("REGISTRAZIONE");
        setLayout(null);
        setResizable(false);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(panel);

        // Aggiungi componenti al pannello
        addComponentsToPanel();

        // Configura colori e font
        setComponentStyles();

        // Aggiungi listener
        addActionListeners();

        // Imposta visibilità iniziale
        idLabel.setVisible(false);
        idField.setVisible(false);
    }

    public Registrazione(String email2, char[] password2, String nome2, String cognome2, String codiceFiscale2,
			String dataNascita2, String indirizzio2) {
		// TODO Auto-generated constructor stub
	}

	private void addComponentsToPanel() {
        panel.add(registrazioneFormLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(nomeLabel);
        panel.add(cognomeLabel);
        panel.add(codiceFiscaleLabel);
        panel.add(dataNascitaLabel);
        panel.add(indirizzioLabel);
        panel.add(emailField);
        panel.add(passwordField);
        panel.add(nomeField);
        panel.add(cognomeField);
        panel.add(codiceFiscaleField);
        panel.add(dataNascitaField);
        panel.add(indirizzioField);
        panel.add(conferma);
        panel.add(chiuso);
        panel.add(showPasswordCheckBox);
        panel.add(ortopedicoRadio);
        panel.add(oculistaRadio);
        panel.add(dentistaRadio);
        panel.add(idLabel);
        panel.add(idField);
        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
    }

    private void setComponentStyles() {
        // Dimensioni componenti
        panel.setBounds(0, 0, 1000, 1000);
        registrazioneFormLabel.setBounds(330, 200, 600, 70);
        emailLabel.setBounds(200, 320, 150, 30);
        emailField.setBounds(300, 320, 400, 30);
        passwordLabel.setBounds(135, 360, 200, 30);
        passwordField.setBounds(300, 360, 400, 30);
        nomeLabel.setBounds(200, 400, 150, 30);
        nomeField.setBounds(300, 400, 400, 30);
        cognomeLabel.setBounds(150, 440, 150, 30);
        cognomeField.setBounds(300, 440, 400, 30);
        codiceFiscaleLabel.setBounds(60, 480, 300, 30);
        codiceFiscaleField.setBounds(300, 480, 400, 30);
        dataNascitaLabel.setBounds(50, 520, 300, 30);
        dataNascitaField.setBounds(300, 520, 400, 30);
        indirizzioLabel.setBounds(120, 560, 300, 30);
        indirizzioField.setBounds(300, 560, 400, 30);
        chiuso.setBounds(50, 800, 150, 50);
        conferma.setBounds(760, 800, 190, 50);
        showPasswordCheckBox.setBounds(750, 370, 180, 20);
        ortopedicoRadio.setBounds(200, 280, 170, 20);
        oculistaRadio.setBounds(750, 280, 130, 20);
        dentistaRadio.setBounds(550, 280, 130, 20);
        idLabel.setBounds(190, 600, 150, 30);
        idField.setBounds(300, 600, 400, 30);

        // Colori e font
        panel.setBackground(Color.decode("#89CFF0"));
        conferma.setBackground(Color.LIGHT_GRAY);
        chiuso.setBackground(Color.LIGHT_GRAY);
        showPasswordCheckBox.setBackground(Color.decode("#89CFF0"));
        ortopedicoRadio.setBackground(Color.decode("#89CFF0"));
        oculistaRadio.setBackground(Color.decode("#89CFF0"));
        dentistaRadio.setBackground(Color.decode("#89CFF0"));
        conferma.setFont(new Font("Congenial black", Font.BOLD, 20));
        chiuso.setFont(new Font("Congenial black", Font.BOLD, 20));
        registrazioneFormLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        emailLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        passwordLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        nomeLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        cognomeLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        codiceFiscaleLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        dataNascitaLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        indirizzioLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        idLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
    }

    private void addActionListeners() {
        showPasswordCheckBox.addActionListener(this);
        chiuso.addActionListener(this);
        conferma.addActionListener(this);
        ortopedicoRadio.addActionListener(this);
        oculistaRadio.addActionListener(this);
        dentistaRadio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPasswordCheckBox) {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar('\0');
                showPasswordCheckBox.setText("NASCONDI LA PASSWORD");
            } else {
                passwordField.setEchoChar('*');
                showPasswordCheckBox.setText("MOSTRA LA PASSWORD");
            }
        } else if (e.getSource() == chiuso) {
            int confirm = JOptionPane.showConfirmDialog(null, "VUOI CHIUDERE LA REGISTRAZIONE?", "SCELTA", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } else if (e.getSource() == conferma) {
            if (!ortopedicoRadio.isSelected() && !oculistaRadio.isSelected() && !dentistaRadio.isSelected()) {
                JOptionPane.showMessageDialog(this, "DEVI SELEZIONARE UN'OPZIONE", "ERRORE", JOptionPane.ERROR_MESSAGE);
            } else {
                registerUser();
            }
        } else if (e.getSource() == ortopedicoRadio || e.getSource() == oculistaRadio || e.getSource() == dentistaRadio) {
            idLabel.setVisible(true);
            idField.setVisible(true);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void registerUser() {
        String emailText = emailField.getText().trim();
        String passwordText = new String(passwordField.getPassword()).trim();
        String nomeText = nomeField.getText().trim();
        String cognomeText = cognomeField.getText().trim();
        String codiceFiscaleText = codiceFiscaleField.getText().trim();
        String dataNascitaText = dataNascitaField.getText().trim();
        String indirizzioText = indirizzioField.getText().trim();
        String idSpecializzazione = idField.getText().trim();

        String tipoUtente = null;
        if (ortopedicoRadio.isSelected()) {
            tipoUtente = "ORTOPEDICO";
        } else if (oculistaRadio.isSelected()) {
            tipoUtente = "OCULISTA";
        } else if (dentistaRadio.isSelected()) {
            tipoUtente = "DENTISTA";
        }

        // Controllo campi obbligatori
        if (emailText.isEmpty() || passwordText.isEmpty() || nomeText.isEmpty() || cognomeText.isEmpty() ||
            codiceFiscaleText.isEmpty() || dataNascitaText.isEmpty() || indirizzioText.isEmpty() || idSpecializzazione.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compila tutti i campi obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = new User(emailText, passwordText, nomeText, cognomeText, codiceFiscaleText, dataNascitaText, indirizzioText, tipoUtente, idSpecializzazione);
            registrazioneDAO.registraUtente(user);
            JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante la registrazione: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Registrazione().setVisible(true);
    }


	 void indirizzio2Check() {
		// TODO Auto-generated method stub
		
	}

	public void checkduplicate() {
		// TODO Auto-generated method stub
		
	}

	public void datanascita2Check() {
		// TODO Auto-generated method stub
		
	}

	public void codicefiscale2Check() {
		// TODO Auto-generated method stub
		
	}

	public void cognome2Check() {
		// TODO Auto-generated method stub
		
	}

	public void Password2Check() {
		// TODO Auto-generated method stub
		
	}

	public void email2Check() {
		// TODO Auto-generated method stub
		
	}

	public void nome2Check() {
		// TODO Auto-generated method stub
		
	}

}