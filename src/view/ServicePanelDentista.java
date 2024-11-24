package view;

import java.awt.Color;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.DottoreController;

import Model.ServiziPanel;

@SuppressWarnings("serial")
public class ServicePanelDentista extends JFrame {
   
    private DottoreController controller;
    private JPanel PanelA = new JPanel(null);
    private JLabel searchLabel = new JLabel("CODICE FISCALE:");
    private JLabel ServicePanelform = new JLabel("Servizi");
    private JTextField searchField = new JTextField();
    private JButton searchButton = new JButton("Search");
    private JTable Tabel;
    private DefaultTableModel tableModel;
    private JButton Button1 = new JButton("DELETE");
    private JButton Button2 = new JButton("ADD");
    private JButton Button3 = new JButton("ADD RIGHA");



    // Costruttore che accetta l'email come parametro
    public ServicePanelDentista() {
        

        this.setTitle("Service Panel Dentista");
        this.setSize(800, 770);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(PanelA);
        String tipoUtente="Dentista";
		this.controller = new DottoreController(tipoUtente);
        setupUI();
        setupActions();
     
    }

   
    private void setupUI() {
        PanelA.setBounds(0, 0, 811, 920);

        searchLabel.setBounds(400, 55, 140, 20);
        searchField.setBounds(550, 55, 120, 25);
        searchButton.setBounds(680, 55, 100, 25);
        ServicePanelform.setBounds(250, 160, 600, 50);
        Button1.setBounds(0, 600, 130, 35);
        Button2.setBounds(700, 600, 100, 35);
        Button3.setBounds(170, 600, 150, 35);


        String[] columnNames = {"DESCRIZIONE_DELLE_MALATTIA", "TRATTAMENTO", "CODICE_FISCALE", "PREZZO"};
        tableModel = new DefaultTableModel(new String[0][0], columnNames);
        Tabel = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(Tabel);
        scrollPane.setBounds(50, 230, 700, 300);

        PanelA.setBackground(Color.decode("#89CFF0"));
        Button1.setBackground(Color.WHITE);
        Button2.setBackground(Color.WHITE);
        Button3.setBackground(Color.WHITE);
   
        searchButton.setBackground(Color.WHITE);

        ServicePanelform.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        Button1.setFont(new Font("Congenial black", Font.BOLD, 20));
        Button2.setFont(new Font("Congenial black", Font.BOLD, 20));
        Button3.setFont(new Font("Congenial black", Font.BOLD, 20));

        searchLabel.setFont(new Font("Congenial black", Font.BOLD, 15));
        searchButton.setFont(new Font("Congenial black", Font.BOLD, 15));

       
        PanelA.add(searchLabel);
        PanelA.add(searchField);
        PanelA.add(searchButton);
        PanelA.add(ServicePanelform);
        PanelA.add(scrollPane);
        PanelA.add(Button1);
        PanelA.add(Button2);
        PanelA.add(Button3);

    }

   

    private void setupActions() {
        searchButton.addActionListener(e -> {
            String codiceFiscale = searchField.getText();
            if (!codiceFiscale.isEmpty()) {
                loadServicesByCodiceFiscale(codiceFiscale);
            }
        });

        Button2.addActionListener(e -> saveAllRowsToDatabase());
        Button3.addActionListener(e -> tableModel.addRow(new String[]{"", "", "", ""}));
        Button1.addActionListener(e -> deleteSelectedRows());
    }

    private void loadServicesByCodiceFiscale(String codiceFiscale) {
        try {
            List<ServiziPanel> servizio = controller.searchByCodiceFiscale(codiceFiscale);
            tableModel.setRowCount(0);
            for (ServiziPanel servizi : servizio) {
                tableModel.addRow(new String[]{
                    servizi.getDescrizioneMalattia(),
                    servizi.getTrattamento(),
                    servizi.getCodiceFiscale(),
                    servizi.getPrezzo()
                });
            }
        } catch (SQLException ex) {
            showError(ex);
        }
    }

    private void saveAllRowsToDatabase() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String descrizione = (String) tableModel.getValueAt(i, 0);
            String trattamento = (String) tableModel.getValueAt(i, 1);
            String codiceFiscale = (String) tableModel.getValueAt(i, 2);
            String prezzo = (String) tableModel.getValueAt(i, 3);

            System.out.println("Salvataggio del servizio: " + descrizione + ", " + trattamento + ", " + codiceFiscale + ", " + prezzo);

            if (validateFields(prezzo, descrizione, trattamento, codiceFiscale)) {
                
				ServiziPanel servizi = new ServiziPanel(prezzo, descrizione, trattamento, codiceFiscale, "Dentisa" );
                try {
                    controller.addServizi(servizi);
                    JOptionPane.showMessageDialog(this, "Salvato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);;
                } catch (SQLException ex) {
                    System.out.println("Errore durante il salvataggio del servizio: " + ex.getMessage());
                    ex.printStackTrace();
                    break;
                }
            } else {
                System.out.println("Campi non validi per il servizio: " + descrizione + ", " + trattamento + ", " + codiceFiscale + ", " + prezzo);
            }
        }
    }

    private void deleteSelectedRows() {
        int[] selectedRows = Tabel.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int modelRow = Tabel.convertRowIndexToModel(selectedRows[i]);
            String codiceFiscale = (String) tableModel.getValueAt(modelRow, 2);
            if (codiceFiscale != null && !codiceFiscale.trim().isEmpty()) {
                try {
                    System.out.println("Eliminazione del servizio con codice fiscale: " + codiceFiscale); // Debug
                    controller.deleteServizi(codiceFiscale);
                    tableModel.removeRow(modelRow);  // Rimuove la riga dal modello della tabella
                    JOptionPane.showMessageDialog(this, "Riga eliminata con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    showError(ex);
                }
            } else {
                System.out.println("Codice fiscale vuoto o nullo, impossibile eliminare.");
            }
        }
    }

    private void showError(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateFields( String prezzo, String descrizione, String trattamento, String codiceFiscale) {
        return 
               prezzo != null && !prezzo.trim().isEmpty() &&
               descrizione != null && !descrizione.trim().isEmpty() &&
               trattamento != null && !trattamento.trim().isEmpty() &&
               codiceFiscale != null && !codiceFiscale.trim().isEmpty();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ServicePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ServicePanelDentista()); 
        frame.setSize(800, 700);
        frame.setVisible(true);
    }
    public JPanel getPanelA() {
        return PanelA;
    }

	
}
