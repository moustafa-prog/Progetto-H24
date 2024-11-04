package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import view.HomePageDentista;
import view.HomePageOculista;
import view.HomePageOrtopedico;
import view.Login;
import view.Registrazione;
import Modelo.UserDAO;

public class LoginController {

    private Login view;
    @SuppressWarnings("rawtypes")
	private UserDAO userDAO;

    public LoginController(Login view, UserDAO<?> userDAO) {
        this.view = view;
        this.userDAO = userDAO;

        // Assegna i listener dal controller
        this.view.setLoginListener(new LoginButtonListener());
        this.view.setShowPasswordListener(new ShowPasswordListener());
        this.view.setRegistrationListener(e -> openRegistration());
        view.ortopedicoRadio.addActionListener(e -> view.setSpecializationIdVisibility(true));
        view.oculistaRadio.addActionListener(e -> view.setSpecializationIdVisibility(true));
        view.dentistaRadio.addActionListener(e -> view.setSpecializationIdVisibility(true));

    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmail();
            String password = view.getPassword();
            String specialization = view.getSpecialization();
            String specializationId = view.getSpecializationId();

            if (email.isEmpty() || password.isEmpty() || specialization.isEmpty() || specializationId.isEmpty()) {
                view.showMessage("Compila tutti i campi!");
                return;
            }

            boolean loginRiuscito = userDAO.verificaCredenziali(email, password, specialization, specializationId);

            if (loginRiuscito) {
                String nomeDottore = userDAO.getNomeDottore(email);
                view.showMessage("Login effettuato con successo, Benvenuto " + nomeDottore);
                
                // Apri la pagina successiva basata sulla specializzazione
                switch (specialization) {
                    case "ORTOPEDICO":
                    	
                        new HomePageOrtopedico(nomeDottore).setVisible(true);
                        break;
                    case "OCULISTA":
                        new HomePageOculista(nomeDottore).setVisible(true);
                        break;
                    case "DENTISTA":
                        new HomePageDentista(nomeDottore).setVisible(true);
                        break;
                }
                view.dispose();
            } else {
                view.showMessage("Credenziali non valide");
            }
        }
    }

    private class ShowPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getShowPasswordCheckbox().isSelected()) {
                view.getPasswordField().setEchoChar('\0');
                view.getShowPasswordCheckbox().setText("NASCONDI LA PASSWORD");
            } else {
                view.getPasswordField().setEchoChar('*');
                view.getShowPasswordCheckbox().setText("MOSTRA LA PASSWORD");
            }
        }
    }

    private void openRegistration() {
        // Implementazione apertura finestra di registrazione
        new Registrazione().setVisible(true);
    }
}
