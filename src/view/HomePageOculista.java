
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.ImagePanel;


@SuppressWarnings("serial")
public class HomePageOculista extends JFrame implements ActionListener {
	 
    JMenuBar Bar1  = new JMenuBar();
    JMenuItem Edit1 =  new JMenuItem("RICEVIMENTI");
    JMenu Edit = new JMenu("EDIT");
    JMenu File = new JMenu("FILE");
    JMenu Opizione = new JMenu("OPIZIONE");
    JMenu Aiutarsi = new JMenu("AIUTO");
    JMenu Account = new JMenu("ACCOUNTS");
    JLabel Dottore =new JLabel("DOTTORE");
    JLabel DR =new JLabel("DR:");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(); 
    ImagePanel panel3;  // Cambiato in ImagePanel
   JTextField label1 =new JTextField(); 
   
    JButton num1 = new JButton("RICEVIMENTI");	
    JButton num2 = new JButton("PRENOTAZIONE");		
    JButton num3 = new JButton("SERVIZI");			
    JButton num4 = new JButton("ASSISTENZA");		
    JButton num5 = new JButton(" ORARIO");			
    JButton num6 = new JButton("I PAZIENTI");		
    JButton num7= new JButton("USCITA");	
    JButton num8 = new JButton("VISITI");	
    public HomePageOculista(String NomeDottore, String specializzazione) {
        this.setTitle("UTENTE");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1006, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(Bar1);
        this.add(panel1);
        this.add(panel2);
       panel3 = new ImagePanel("C:\\Users\\peschiera721\\OneDrive\\Immagini\\Catture di schermata\\Screenshot 2024-08-08 142007.png");
        this.add(panel3);  
        
        label1.setColumns(15); // Aumenta la larghezza per mostrare correttamente il nome
        label1.setEditable(false); 


        // Aggiungi ActionListener ai bottoni
        num1.addActionListener(this);
        num2.addActionListener(this);
        num3.addActionListener(this);
        num4.addActionListener(this);
        num5.addActionListener(this);
        num6.addActionListener(this);
        num7.addActionListener(this);
        num8.addActionListener(this);
        // Dimensioni dei pannelli e bar
        Bar1.setBounds(0,0,1000,20);
        panel1.setBounds(0, 20,1000,75);
        panel2.setBounds(800, 30,200,900);
        panel3.setBounds(0, 95,800,900);
        panel3.setLayout(new BorderLayout());
     
        // Colore di background
        panel1.setBackground(Color.WHITE);
     	panel2.setBackground(Color.decode("#89CFF0"));
     			num1.setBackground(Color.WHITE);
     				num2.setBackground(Color.WHITE);
     					num3.setBackground(Color.WHITE);
     						num4.setBackground(Color.WHITE); 
     								num5.setBackground(Color.WHITE);
     									num6.setBackground(Color.WHITE);
     										num7.setBackground(Color.WHITE);
     										label1.setBackground(Color.WHITE);
     										 num8.setBackground(Color.WHITE);
        
        // Dimensioni di font
        Dottore.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        num1.setFont(new Font("Congenial black", Font.BOLD, 19));
        num2.setFont(new Font("Congenial black", Font.BOLD, 18));
        num3.setFont(new Font("Congenial black", Font.BOLD, 20));
        num4.setFont(new Font("Congenial black", Font.BOLD, 20));
     
        num5.setFont(new Font("Congenial black", Font.BOLD, 20));
        num6.setFont(new Font("Congenial black", Font.BOLD, 20));
        num7.setFont(new Font("Congenial black", Font.BOLD, 20));
        label1.setFont(new Font("Congenial black", Font.BOLD, 20));
        DR.setFont(new Font("ALGERIAN", Font.BOLD, 20));
        num8.setFont(new Font("Congenial black", Font.BOLD, 20));
        // Aggiungi i componenti al menu e ai pannelli
        Edit.add(Edit1);
        Bar1.add(Edit);
        Bar1.add(File);
        Bar1.add(Opizione);
        Bar1.add(Aiutarsi);
        
        panel1.setLayout(null);
        panel1.add(Dottore);
        panel1.add(label1);
        panel1.add(label1);
        panel1.add(Dottore);
        panel1.add(DR);
      
        Dottore.setBounds(350, 25, 500, 45);
        label1.setBounds(800, 25, 150, 30);
        DR.setBounds(750, 25, 50, 30);
        label1.setEditable(false); 
        label1.setText(NomeDottore);
     
        // Imposta layout e aggiungi i bottoni
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 60, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 1;
        panel2.add(num8, gbc);
        gbc.gridy = 2;
        panel2.add(num1, gbc);
        gbc.gridy = 3;
        panel2.add(num2, gbc);
        gbc.gridy = 4;
        panel2.add(num3, gbc);
        gbc.gridy = 5;
        panel2.add(num4, gbc);
        gbc.gridy = 6;
        panel2.add(num5, gbc);
        gbc.gridy = 7;
        panel2.add(num6, gbc);
        gbc.gridy = 8;
        panel2.add(num7, gbc);
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == num1) {
            RicevimentiOculista ricivimenti = new RicevimentiOculista();
            panel3.removeAll();
            panel3.revalidate();
            panel3.repaint();
            panel3.add(ricivimenti.getPanelMain(), BorderLayout.CENTER);
        } else if (e.getSource() == num2) {
            Prenotazione_Oculista prenotazione_Oculista = new Prenotazione_Oculista();
            panel3.removeAll();
            panel3.revalidate();
            panel3.repaint();
            panel3.add(prenotazione_Oculista.getPanelMain(), BorderLayout.CENTER);
        } else if (e.getSource() == num3) {
            ServicePanelOculista servicePanelOculista = new ServicePanelOculista();
            panel3.removeAll();
            panel3.revalidate();
            panel3.repaint();
            panel3.add(servicePanelOculista.getPanelA(), BorderLayout.CENTER);
        } else if (e.getSource() == num4) {
        	InventarioMaterialiOculista i_dottore = new InventarioMaterialiOculista();
            panel3.removeAll();
            panel3.revalidate();
            panel3.repaint();
            panel3.add(i_dottore.getPanelA(), BorderLayout.CENTER);
            }
        else if (e.getSource() == num5) {
        	Orario_Dottore_Oculista Occhi = new Orario_Dottore_Oculista();
            panel3.removeAll();
            panel3.revalidate();
            panel3.repaint();
            panel3.add(Occhi.getPanelA(), BorderLayout.CENTER);
        }else if (e.getSource() == num6) {
                PazienteOculista pazienteOculista = new PazienteOculista();
                panel3.removeAll();
                panel3.revalidate();
                panel3.repaint();
                panel3.add(pazienteOculista.getPanelA(), BorderLayout.CENTER);
            }else if(e.getSource() == num7) {
                int c = JOptionPane.showConfirmDialog(null, "VOUI CHIUDERE L'APP", "SCELTA",JOptionPane.YES_NO_OPTION);
                if(c == 0) {
                    System.exit(0);
                }
            }else if (e.getSource() == num8) {
              	 VisitiOculista visiti = new VisitiOculista();
                 panel3.removeAll();
                 panel3.revalidate();
                 panel3.repaint();
                 panel3.add(visiti.getPanelA(), BorderLayout.CENTER);
       }
          
        }
    public JPanel getpanel3() {
        return panel3;
    }

    public static void main(String[] args) {
    	 String NomeDottore = "";
    	 String specializzazione = "";
        new HomePageOculista(NomeDottore, specializzazione).setVisible(true);
    }
    }

