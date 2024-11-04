/*
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//import Modelo.UserDAO;
//import Modelo.UserDAOimp;


@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	//private UserDAO<Login> userDAO = new UserDAOimp();

    // Componenti dell'interfaccia
    JButton loginButton = new JButton("ACCESSO");
    JButton registrazioneButton = new JButton("REGISTRAZIONE");
    JLabel emailLabel = new JLabel("E-MAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPasswordCheckbox = new JCheckBox("MOSTRA LA PASSWORD");
    JLabel loginFormLabel = new JLabel("BENVENUTO");
    
    JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    JRadioButton dentistaRadio = new JRadioButton("DENTISTA");
    
    JTextField idSpecializzazioneField = new JTextField(); // Campo ID specializzazione
    JLabel IdSpecializzazioneField = new JLabel("ID");
    
    ButtonGroup radioGroup = new ButtonGroup();
    JPanel panel = new JPanel(null);

    public Login() {
    	
        // Impostazioni base della finestra
        this.setTitle("ACCESSO");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);

        // Layout e posizionamento componenti
        layoutComponents();

        // Aggiungi listener
     //   addActionListeners();

        // Imposta visibilità iniziale
        idSpecializzazioneField.setVisible(false);
        IdSpecializzazioneField.setVisible(false);
    }
    

   
    private void layoutComponents() {
        // Configurazione layout e componenti
        panel.setBounds(0, 0, 1000, 1000);
        panel.setBackground(Color.decode("#89CFF0"));

        // Label e campi di testo
        loginFormLabel.setBounds(350, 200, 600, 100);
        loginFormLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));

        emailLabel.setBounds(180, 450, 120, 35);
        emailLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));

        emailField.setBounds(300, 450, 400, 35);

        passwordLabel.setBounds(135, 500, 180, 35);
        passwordLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));

        IdSpecializzazioneField.setBounds(210, 580, 120, 35);
        IdSpecializzazioneField.setFont(new Font("BODONI MT", Font.BOLD, 30));

        passwordField.setBounds(300, 500, 400, 35);
        showPasswordCheckbox.setBounds(750, 500, 170, 20);
        showPasswordCheckbox.setBackground(Color.decode("#89CFF0"));
        // Pulsanti
        loginButton.setBounds(550, 650, 150, 25);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("Congenial black", Font.BOLD, 20));

        registrazioneButton.setBounds(300, 650, 200, 25);
        registrazioneButton.setBackground(Color.LIGHT_GRAY);
        registrazioneButton.setFont(new Font("Congenial black", Font.BOLD, 20));

        // RadioButton per specializzazioni
        ortopedicoRadio.setBounds(200, 550, 200, 25);
        ortopedicoRadio.setBackground(Color.decode("#89CFF0"));

        oculistaRadio.setBounds(500, 550, 200, 25);
        oculistaRadio.setBackground(Color.decode("#89CFF0"));

        dentistaRadio.setBounds(750, 550, 200, 25);
        dentistaRadio.setBackground(Color.decode("#89CFF0"));

        // Campo ID per la specializzazione
        idSpecializzazioneField.setBounds(300, 580, 400, 35);

        // Aggiunta componenti al pannello
        panel.add(loginFormLabel);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(showPasswordCheckbox);
        panel.add(loginButton);
        panel.add(registrazioneButton);
        panel.add(ortopedicoRadio);
        panel.add(oculistaRadio);
        panel.add(dentistaRadio);
        panel.add(idSpecializzazioneField);
        panel.add(IdSpecializzazioneField);
        // Aggiungi i RadioButton al gruppo
        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
    }

    private void addActionListeners() {
        // Assegna i listener per gli eventi
        showPasswordCheckbox.addActionListener(this);
        registrazioneButton.addActionListener(this);
        loginButton.addActionListener(this);
        ortopedicoRadio.addActionListener(this);
        oculistaRadio.addActionListener(this);
        dentistaRadio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPasswordCheckbox) {
            // Mostra o nasconde la password
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar('\0');
                showPasswordCheckbox.setText("NASCONDI LA PASSWORD");
            } else {
                passwordField.setEchoChar('*');
                showPasswordCheckbox.setText("MOSTRA LA PASSWORD");
            }
        } else if (e.getSource() == registrazioneButton) {
            // Apri la finestra di registrazione
            new Registrazione().setVisible(true);
        } else if (e.getSource() == loginButton) {
            // Verifica le credenziali
            checkEmail();
        } else if (e.getSource() == ortopedicoRadio || e.getSource() == oculistaRadio || e.getSource() == dentistaRadio) {
            // Mostra il campo ID corrispondente alla specializzazione selezionata
            idSpecializzazioneField.setVisible(true);
            IdSpecializzazioneField.setVisible(true);

        }
    }

    private void checkEmail() {
        String username = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String tipoUtente = "";
        String idUtente = idSpecializzazioneField.getText().trim();
        System.out.println("Email inserita per il login: " + username);
        if (ortopedicoRadio.isSelected()) {
            tipoUtente = "ORTOPEDICO";
        } else if (oculistaRadio.isSelected()) {
            tipoUtente = "OCULISTA";
            
        } else if (dentistaRadio.isSelected()) {
            tipoUtente = "DENTISTA";
        }

        // Verifica che i campi non siano vuoti
        if (username.isEmpty() || password.isEmpty() || tipoUtente.isEmpty() || idUtente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compila tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica credenziali con il DAO
        boolean loginRiuscito = userDAO.verificaCredenziali(username, password, tipoUtente, idUtente);

        if (loginRiuscito) {
        	 String NomeDottore = userDAO.getNomeDottore(username);
            JOptionPane.showMessageDialog(this, "Login effettuato con successo!");
         
            if (ortopedicoRadio.isSelected()) {
                new HomePageOrtopedico(NomeDottore).setVisible(true);
            } else if (oculistaRadio.isSelected()) {
                new HomePageOculista(NomeDottore).setVisible(true);
                
            } else if (dentistaRadio.isSelected()) {
                new HomePageDentista(NomeDottore).setVisible(true);
            }

            this.dispose(); // Chiudi la finestra di login
        } else {
            JOptionPane.showMessageDialog(this, "Credenziali non valide", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
  

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

}
  */  
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

    // Componenti dell'interfaccia
    JButton loginButton = new JButton("ACCESSO");
    JButton registrazioneButton = new JButton("REGISTRAZIONE");
    JLabel emailLabel = new JLabel("E-MAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPasswordCheckbox = new JCheckBox("MOSTRA LA PASSWORD");
    JLabel loginFormLabel = new JLabel("BENVENUTO");

    public JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    public JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    public JRadioButton dentistaRadio = new JRadioButton("DENTISTA");

    JTextField idSpecializzazioneField = new JTextField(); // Campo ID specializzazione
    JLabel IdSpecializzazioneField = new JLabel("ID");

    ButtonGroup radioGroup = new ButtonGroup();
    JPanel panel = new JPanel(null);

    public Login() {
        // Impostazioni base della finestra
        this.setTitle("ACCESSO");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);

        // Layout e posizionamento componenti
        layoutComponents();

        // Imposta visibilità iniziale
        idSpecializzazioneField.setVisible(false);
        IdSpecializzazioneField.setVisible(false);
    }

    private void layoutComponents() {
        // Configurazione layout e componenti
        panel.setBounds(0, 0, 1000, 1000);
        panel.setBackground(Color.decode("#89CFF0"));

        loginFormLabel.setBounds(350, 200, 600, 100);
        loginFormLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));

        emailLabel.setBounds(180, 450, 120, 35);
        emailLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        emailField.setBounds(300, 450, 400, 35);

        passwordLabel.setBounds(135, 500, 180, 35);
        passwordLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        getPasswordField().setBounds(300, 500, 400, 35);
        getShowPasswordCheckbox().setBounds(750, 500, 170, 20);
        getShowPasswordCheckbox().setBackground(Color.decode("#89CFF0"));

        IdSpecializzazioneField.setBounds(210, 580, 120, 35);
        IdSpecializzazioneField.setFont(new Font("BODONI MT", Font.BOLD, 30));
        idSpecializzazioneField.setBounds(300, 580, 400, 35);

        loginButton.setBounds(550, 650, 150, 25);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setFont(new Font("Congenial black", Font.BOLD, 20));
        
        registrazioneButton.setBounds(300, 650, 200, 25);
        registrazioneButton.setBackground(Color.LIGHT_GRAY);
        registrazioneButton.setFont(new Font("Congenial black", Font.BOLD, 20));

        ortopedicoRadio.setBounds(200, 550, 200, 25);
        ortopedicoRadio.setBackground(Color.decode("#89CFF0"));
        oculistaRadio.setBounds(500, 550, 200, 25);
        oculistaRadio.setBackground(Color.decode("#89CFF0"));
        dentistaRadio.setBounds(750, 550, 200, 25);
        dentistaRadio.setBackground(Color.decode("#89CFF0"));

        panel.add(loginFormLabel);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(getPasswordField());
        panel.add(getShowPasswordCheckbox());
        panel.add(loginButton);
        panel.add(registrazioneButton);
        panel.add(ortopedicoRadio);
        panel.add(oculistaRadio);
        panel.add(dentistaRadio);
        panel.add(idSpecializzazioneField);
        panel.add(IdSpecializzazioneField);

        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
    }

    // Getter per i dati inseriti dall'utente
    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(getPasswordField().getPassword()).trim();
    }

    public String getSpecialization() {
        if (ortopedicoRadio.isSelected()) return "ORTOPEDICO";
        if (oculistaRadio.isSelected()) return "OCULISTA";
        if (dentistaRadio.isSelected()) return "DENTISTA";
        return "";
    }

    public String getSpecializationId() {
        return idSpecializzazioneField.getText().trim();
    }
    public void setSpecializationIdVisibility(boolean visible) {
        idSpecializzazioneField.setVisible(visible);
        IdSpecializzazioneField.setVisible(visible);
    }


    // Metodi per assegnare listener dal controller
    public void setLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setShowPasswordListener(ActionListener listener) {
        getShowPasswordCheckbox().addActionListener(listener);
    }

    public void setRegistrationListener(ActionListener listener) {
        registrazioneButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

	public JCheckBox getShowPasswordCheckbox() {
		return showPasswordCheckbox;
	}

	public void setShowPasswordCheckbox(JCheckBox showPasswordCheckbox) {
		this.showPasswordCheckbox = showPasswordCheckbox;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
}
