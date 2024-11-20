package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.PrenotazioneDAOIMP;
import dominio.Prenotazione;

import java.awt.*;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Prenotazione_Oculista extends JFrame {
    private PrenotazioneDAOIMP PrenotazioneOculistaDAO = new PrenotazioneDAOIMP("Oculista");
    JPanel panelMain = new JPanel(null);
    
    JLabel PrenotazioneOculista = new JLabel("*PRENOTAZIONE*");
    JTable table;
    DefaultTableModel tableModel;

    JButton buttonDeleteAll = new JButton("DELETE ALL");
    JButton buttonAdd = new JButton("ADD");
    JButton buttonNewRow = new JButton("ADD RIGHA");
    JButton buttonRemoveSelected = new JButton("REMOVE RIGHA");

    public Prenotazione_Oculista() {
        this.setTitle("Successivi Denti");
        this.setSize(800, 800);
        this.add(panelMain);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setupLayout();
        buttonNewRow.addActionListener(e -> addNewRow());
        buttonRemoveSelected.addActionListener(e -> removeSelectedRows());
        buttonDeleteAll.addActionListener(e -> clearAll());
        buttonAdd.addActionListener(e -> addToDatabase());
    }

    private void setupLayout() {
        panelMain.setBounds(0, 0, 800, 800);

        
        PrenotazioneOculista.setBounds(200, 100, 600, 50);
        String[] columnNames = {"COGNOME", "NOME", "CODICE_FISCALE", "DATA", "ORA"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 170, 750, 500);

        // Posizionamento dei pulsanti
        buttonNewRow.setBounds(0, 680, 150, 30);
        buttonRemoveSelected.setBounds(170, 680, 150, 30);
        buttonDeleteAll.setBounds(480, 680, 150, 30);
        buttonAdd.setBounds(650, 680, 150, 30);

        panelMain.setBackground(Color.decode("#89CFF0"));
      
        panelMain.add(scrollPane);
        panelMain.add(buttonNewRow);
        panelMain.add(buttonRemoveSelected);
        panelMain.add(buttonDeleteAll);
        panelMain.add(buttonAdd);
        panelMain.add(PrenotazioneOculista);

        buttonDeleteAll.setFont(new Font("Arial", Font.BOLD, 15));
        buttonAdd.setFont(new Font("Arial", Font.BOLD, 15));
        buttonNewRow.setFont(new Font("Arial", Font.BOLD, 15));
        buttonRemoveSelected.setFont(new Font("Arial", Font.BOLD, 15));
        PrenotazioneOculista.setFont(new Font("ALGERIAN", Font.BOLD, 50));
     
        buttonDeleteAll.setBackground(Color.WHITE);
        buttonAdd.setBackground(Color.WHITE);
        buttonNewRow.setBackground(Color.WHITE);
        buttonRemoveSelected.setBackground(Color.WHITE);
    }

    private void addNewRow() {
        tableModel.addRow(new String[]{"", "", "", "", ""});
    }

    private void removeSelectedRows() {
        int[] selectedRows = table.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            tableModel.removeRow(selectedRows[i]);
        }
    }

    private void clearAll() {
        tableModel.setRowCount(0);
    
    }

    private void addToDatabase() {
      

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String cognome = (String) tableModel.getValueAt(i, 0);
            String nome = (String) tableModel.getValueAt(i, 1);
            String codiceFiscale = (String) tableModel.getValueAt(i, 2);
          
            String data = (String) tableModel.getValueAt(i, 3);
            String ora = (String) tableModel.getValueAt(i, 4);

            String Tipo_di_utente= null;
			Prenotazione prenotazione = new Prenotazione( cognome, nome, codiceFiscale, data, ora, Tipo_di_utente);
            try {
            	PrenotazioneOculistaDAO.addPrenotazione(prenotazione);
            	  JOptionPane.showMessageDialog(this, "Ultima riga salvata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
    	Prenotazione_Oculista frame = new Prenotazione_Oculista();
        frame.setVisible(true);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}