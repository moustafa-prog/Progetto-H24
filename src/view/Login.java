
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import Controller.DottoreController;

@SuppressWarnings("serial")
public class Login extends JFrame {

    // Controller
    private DottoreController controller;

    // Componenti dell'interfaccia
    private JButton loginButton = new JButton("ACCESSO");
    private JButton registrazioneButton = new JButton("REGISTRAZIONE");
    private JLabel emailLabel = new JLabel("E-MAIL");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JCheckBox showPasswordCheckbox = new JCheckBox("MOSTRA LA PASSWORD");
    private JLabel loginFormLabel = new JLabel("BENVENUTO");

    private JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    private JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    private JRadioButton dentistaRadio = new JRadioButton("DENTISTA");

    private JTextField idSpecializzazioneField = new JTextField(); // Campo ID specializzazione
    private JLabel idSpecializzazioneLabel = new JLabel("ID");

    private ButtonGroup radioGroup = new ButtonGroup();
    private JPanel panel = new JPanel(null);

    public Login() {
        // Impostazioni base della finestra
        this.setTitle("ACCESSO");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.controller = new DottoreController();
        // Layout e posizionamento componenti
        layoutComponents();

        // Imposta visibilità iniziale
        idSpecializzazioneField.setVisible(false);
        idSpecializzazioneLabel.setVisible(false);

        // Aggiungi listener
        addRadioButtonListeners();
        openRegistration();
        togglePasswordVisibility();
        handleLogin();
    }

    private void layoutComponents() {
        panel.setBounds(0, 0, 1000, 1000);
        panel.setBackground(Color.decode("#89CFF0"));

        loginFormLabel.setBounds(350, 200, 600, 100);
        loginFormLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));

        emailLabel.setBounds(180, 450, 120, 35);
        emailLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        emailField.setBounds(300, 450, 400, 35);

        passwordLabel.setBounds(135, 500, 180, 35);
        passwordLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
        passwordField.setBounds(300, 500, 400, 35);

        showPasswordCheckbox.setBounds(750, 500, 170, 20);
        showPasswordCheckbox.setBackground(Color.decode("#89CFF0"));

        idSpecializzazioneLabel.setBounds(210, 580, 120, 35);
        idSpecializzazioneLabel.setFont(new Font("BODONI MT", Font.BOLD, 30));
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
        panel.add(passwordField);
        panel.add(showPasswordCheckbox);
        panel.add(loginButton);
        panel.add(registrazioneButton);
        panel.add(ortopedicoRadio);
        panel.add(oculistaRadio);
        panel.add(dentistaRadio);
        panel.add(idSpecializzazioneField);
        panel.add(idSpecializzazioneLabel);

        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
    }

    public void handleLogin() {
        loginButton.addActionListener(e -> {
            String email = getEmail();
            String password = getPassword();
            String Specializzazione = getSpecialization();
            String idSpecializzazione = getSpecializationId();
            System.out.println("DEBUG - Tipo di utente selezionato: " + Specializzazione);
            if (email.isEmpty() || password.isEmpty() || Specializzazione == null || idSpecializzazione.isEmpty()) {
                showMessage("Compila tutti i campi!");
                return;
            }
            this.controller = new DottoreController(Specializzazione);
            // Inizializza il controller con il tipo di utente selezionato
            this.controller = new DottoreController(Specializzazione);

            try {
                boolean loginRiuscito = controller.converificred(email, password, Specializzazione, idSpecializzazione);

                if (loginRiuscito) {
                    String nomeDottore = controller.congetnomedott(email);
                    showMessage("Login effettuato con successo, Benvenuto " + nomeDottore);

                    controller.conHomepage(Specializzazione, nomeDottore);
                    this.dispose();
                } else {
                    showMessage("Credenziali non valide");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage("Errore durante il login: " + ex.getMessage());
            }
        });
    }

    public void togglePasswordVisibility() {
        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
    }

    private void openRegistration() {
        registrazioneButton.addActionListener(e -> new Registrazione().setVisible(true));
    }

    private void addRadioButtonListeners() {
        ActionListener radioButtonListener = e -> {
            idSpecializzazioneField.setVisible(true);
            idSpecializzazioneLabel.setVisible(true);
        };

        ortopedicoRadio.addActionListener(radioButtonListener);
        oculistaRadio.addActionListener(radioButtonListener);
        dentistaRadio.addActionListener(radioButtonListener);
    }

    // Getter per i dati inseriti dall'utente
    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public String getSpecialization() {
        if (ortopedicoRadio.isSelected()) return "ORTOPEDICO";
        if (oculistaRadio.isSelected()) return "OCULISTA";
        if (dentistaRadio.isSelected()) return "DENTISTA";
        showMessage("Seleziona una specializzazione!");
        return null;
    }

    public String getSpecializationId() {
        return idSpecializzazioneField.getText().trim();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    

    public static void main(String[] args) {
        new Login().setVisible(true);
    }


	public void mostraLogin() {
		// TODO Auto-generated method stub
		 new Login().setVisible(true);
	}
}
