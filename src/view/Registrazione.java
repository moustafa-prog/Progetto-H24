
package view;


import javax.swing.*;

import Controller.DottoreController;
import Model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
@SuppressWarnings("serial")
public class Registrazione extends JFrame implements ActionListener {

	private DottoreController controller;
	
    
    // Pulsanti e Checkbox
    JButton chiuso = new JButton("CHIUSO");
    JButton conferma = new JButton("CONFERMA");
    JCheckBox showPasswordCheckBox = new JCheckBox("MOSTRA LA PASSWORD");

    // Etichette
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel nomeLabel = new JLabel("NOME");
    JLabel cognomeLabel = new JLabel("COGNOME");
    JLabel codiceFiscaleLabel = new JLabel("CODICE FISCALE");
    JLabel dataNascitaLabel = new JLabel("DATA DI NASCITA");
    JLabel indirizzioLabel = new JLabel("INDIRIZZO");
    JLabel idLabel = new JLabel("ID");

    // Campi di testo
    JTextField emailField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JTextField nomeField = new JTextField(20);
    JTextField cognomeField = new JTextField(20);
    JTextField codiceFiscaleField = new JTextField(20);
    JTextField dataNascitaField = new JTextField(20);
    JTextField indirizzioField = new JTextField(20);
    JTextField idField = new JTextField(20);

    // Radio button
    JRadioButton ortopedicoRadio = new JRadioButton("ORTOPEDICO");
    JRadioButton oculistaRadio = new JRadioButton("OCULISTA");
    JRadioButton dentistaRadio = new JRadioButton("DENTISTA");
    ButtonGroup radioGroup = new ButtonGroup();

    JPanel panel = new JPanel(null);



    public Registrazione() {
    	
        setTitle("REGISTRAZIONE");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Imposta layout e aggiungi componenti
        addComponentsToPanel();
        setComponentStyles();
        addActionListeners();
        registraUtente();
        add(panel);
        
        
    }

    
    public Registrazione(String email2, char[] password2, String nome2, String cognome2, String codiceFiscale2,
			String dataNascita2, String indirizzio2) {
		// TODO Auto-generated constructor stub
	}

	public void chiudiRegistrazione(JFrame frame) {
        frame.dispose();  // Chiude la finestra corrente
        System.out.println("Finestra chiusa.");
    }

	private void addComponentsToPanel() {
        // Layout per posizionamento componenti
        int labelWidth = 150, labelHeight = 30, fieldWidth = 250, fieldHeight = 30;

        panel.add(emailLabel);
        emailLabel.setBounds(50, 30, labelWidth, labelHeight);
        panel.add(emailField);
        emailField.setBounds(200, 30, fieldWidth, fieldHeight);
        
        panel.add(passwordLabel);
        passwordLabel.setBounds(50, 80, labelWidth, labelHeight);
        panel.add(passwordField);
        passwordField.setBounds(200, 80, fieldWidth, fieldHeight);
        
        panel.add(nomeLabel);
        nomeLabel.setBounds(50, 130, labelWidth, labelHeight);
        panel.add(nomeField);
        nomeField.setBounds(200, 130, fieldWidth, fieldHeight);
        
        panel.add(cognomeLabel);
        cognomeLabel.setBounds(50, 180, labelWidth, labelHeight);
        panel.add(cognomeField);
        cognomeField.setBounds(200, 180, fieldWidth, fieldHeight);
        
        panel.add(codiceFiscaleLabel);
        codiceFiscaleLabel.setBounds(50, 230, labelWidth, labelHeight);
        panel.add(codiceFiscaleField);
        codiceFiscaleField.setBounds(200, 230, fieldWidth, fieldHeight);
        
        panel.add(dataNascitaLabel);
        dataNascitaLabel.setBounds(50, 280, labelWidth, labelHeight);
        panel.add(dataNascitaField);
        dataNascitaField.setBounds(200, 280, fieldWidth, fieldHeight);
        
        panel.add(indirizzioLabel);
        indirizzioLabel.setBounds(50, 330, labelWidth, labelHeight);
        panel.add(indirizzioField);
        indirizzioField.setBounds(200, 330, fieldWidth, fieldHeight);
        
        panel.add(showPasswordCheckBox);
        showPasswordCheckBox.setBounds(200, 380, fieldWidth, 30);

        panel.add(ortopedicoRadio);
        ortopedicoRadio.setBounds(50, 430, labelWidth, 30);
        panel.add(oculistaRadio);
        oculistaRadio.setBounds(200, 430, labelWidth, 30);
        panel.add(dentistaRadio);
        dentistaRadio.setBounds(350, 430, labelWidth, 30);
        radioGroup.add(ortopedicoRadio);
        radioGroup.add(oculistaRadio);
        radioGroup.add(dentistaRadio);
        
        panel.add(idLabel);
        idLabel.setBounds(50, 480, labelWidth, labelHeight);
        panel.add(idField);
        idField.setBounds(200, 480, fieldWidth, fieldHeight);
        idLabel.setVisible(false);
        idField.setVisible(false);

        panel.add(chiuso);
        chiuso.setBounds(80, 550, 150, 50);
        panel.add(conferma);
        conferma.setBounds(300, 550, 150, 50);
        
        showPasswordCheckBox.addActionListener(e -> togglePasswordVisibility());
        conferma.addActionListener(this);


        
    }

    private Object togglePasswordVisibility() {
		// TODO Auto-generated method stub
    	if (showPasswordCheckBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
		return null;
	}

	private void setComponentStyles() {
        panel.setBackground(new Color(245, 245, 245));
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        emailLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        nomeLabel.setFont(labelFont);
        cognomeLabel.setFont(labelFont);
        codiceFiscaleLabel.setFont(labelFont);
        dataNascitaLabel.setFont(labelFont);
        indirizzioLabel.setFont(labelFont);
        idLabel.setFont(labelFont);
        
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        emailField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        nomeField.setFont(fieldFont);
        cognomeField.setFont(fieldFont);
        codiceFiscaleField.setFont(fieldFont);
        dataNascitaField.setFont(fieldFont);
        indirizzioField.setFont(fieldFont);
        idField.setFont(fieldFont);
        
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        chiuso.setFont(new Font("Arial", Font.BOLD, 14));
        conferma.setFont(new Font("Arial", Font.BOLD, 14));
        chiuso.setBackground(Color.RED);
        chiuso.setForeground(Color.black);
        conferma.setBackground(Color.GREEN);
        conferma.setForeground(Color.black);
        
        panel.setBackground(Color.blue);
        passwordField.setBackground(Color.WHITE);
        
        Color customColor = new Color(173, 216, 230);
        panel.setBackground(customColor);
        
        chiuso.repaint();
        conferma.repaint();
        
        
        panel.revalidate();
        panel.repaint();

      
        
    }

    private void addActionListeners() {
        showPasswordCheckBox.addActionListener(this);
        chiuso.addActionListener(this);
        conferma.addActionListener(this);
        
        ItemListener userTypeSelectionListener = new UserTypeSelectionListener();
        ortopedicoRadio.addItemListener(userTypeSelectionListener);
        oculistaRadio.addItemListener(userTypeSelectionListener);
        dentistaRadio.addItemListener(userTypeSelectionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPasswordCheckBox) {
            // Mostra o nascondi la password
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        } else if (e.getSource() == chiuso) {
            chiudiRegistrazione(this);
        } else if (e.getSource() == conferma) {
        	if (e.getSource() == conferma) {
        		registraUtente();
            
        }
        }
    }
    

    
    
    
		private void registraUtente() {
            String tipoUtente = ortopedicoRadio.isSelected() ? "ORTOPEDICO" : oculistaRadio.isSelected() ? "OCULISTA" : dentistaRadio.isSelected() ? "DENTISTA" : null;
            
            if (checkRequiredFields()) {
                
                    User user = new User(
                            emailField.getText().trim(),
                            new String(passwordField.getPassword()).trim(),
                            nomeField.getText().trim(),
                            cognomeField.getText().trim(),
                            codiceFiscaleField.getText().trim(),
                            dataNascitaField.getText().trim(),
                            indirizzioField.getText().trim(),
                            tipoUtente,
                            idField.getText().trim()
                    );
                    
                    
             controller.conregUtente(user);       
                    
                    
                    
            } 
            }
		
		
                
                
        
        private boolean checkRequiredFields() {
            if (emailField.getText().isEmpty() || passwordField.getPassword().length == 0 || nomeField.getText().isEmpty() ||
                cognomeField.getText().isEmpty() || codiceFiscaleField.getText().isEmpty() || dataNascitaField.getText().isEmpty() ||
                indirizzioField.getText().isEmpty() || idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Compila tutti i campi obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }

    private class UserTypeSelectionListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // Mostra il campo ID solo se un radio button è selezionato
            idLabel.setVisible(ortopedicoRadio.isSelected() || oculistaRadio.isSelected() || dentistaRadio.isSelected());
            idField.setVisible(ortopedicoRadio.isSelected() || oculistaRadio.isSelected() || dentistaRadio.isSelected());
            panel.revalidate();
            panel.repaint();
        }
    }

    public static void main(String[] args) {
        new Registrazione().setVisible(true);
    }

	public void nome2Check() {
		// TODO Auto-generated method stub
		
	}

	public void Password2Check() {
		// TODO Auto-generated method stub
		
	}

	public void cognome2Check() {
		// TODO Auto-generated method stub
		
	}

	public void email2Check() {
		// TODO Auto-generated method stub
		
	}

	public void codicefiscale2Check() {
		// TODO Auto-generated method stub
		
	}

	public void datanascita2Check() {
		// TODO Auto-generated method stub
		
	}

	public void indirizzio2Check() {
		// TODO Auto-generated method stub
		
	}

	public void checkduplicate() {
		// TODO Auto-generated method stub
		
	}
}


