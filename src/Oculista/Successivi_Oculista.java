package Oculista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Persone.Successivi;
import Persone.SuccessivoDAOIMP;

import java.awt.*;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Successivi_Oculista extends JFrame {
    private SuccessivoDAOIMP successivoOculistaDAO = new SuccessivoDAOIMP("successivi_oculista");
    JPanel panelMain = new JPanel(null);
    
    JLabel SuccessiviDenti = new JLabel("SUCCESSIVI");
    JTable table;
    DefaultTableModel tableModel;

    JButton buttonDeleteAll = new JButton("DELETE ALL");
    JButton buttonAdd = new JButton("ADD");
    JButton buttonNewRow = new JButton("ADD RIGHA");
    JButton buttonRemoveSelected = new JButton("REMOVE RIGHA");

    public Successivi_Oculista() {
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

        
        SuccessiviDenti.setBounds(300, 100, 600, 50);
        String[] columnNames = {"COGNOME", "NOME", "CODICE_FISCALE", "PROSSIMA_VISITA", "DATA", "ORA"};
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
        panelMain.add(SuccessiviDenti);

        buttonDeleteAll.setFont(new Font("Arial", Font.BOLD, 15));
        buttonAdd.setFont(new Font("Arial", Font.BOLD, 15));
        buttonNewRow.setFont(new Font("Arial", Font.BOLD, 15));
        buttonRemoveSelected.setFont(new Font("Arial", Font.BOLD, 15));
        SuccessiviDenti.setFont(new Font("ALGERIAN", Font.BOLD, 50));
     
        buttonDeleteAll.setBackground(Color.WHITE);
        buttonAdd.setBackground(Color.WHITE);
        buttonNewRow.setBackground(Color.WHITE);
        buttonRemoveSelected.setBackground(Color.WHITE);
    }

    private void addNewRow() {
        tableModel.addRow(new String[]{"", "", "", "", "", ""});
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
            String prossimaVisita = (String) tableModel.getValueAt(i, 3);
            String data = (String) tableModel.getValueAt(i, 4);
            String ora = (String) tableModel.getValueAt(i, 5);

            Successivi successivo = new Successivi( cognome, nome, codiceFiscale, prossimaVisita, data, ora);
            try {
            	successivoOculistaDAO.addSuccessivo(successivo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
    	Successivi_Oculista frame = new Successivi_Oculista();
        frame.setVisible(true);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
