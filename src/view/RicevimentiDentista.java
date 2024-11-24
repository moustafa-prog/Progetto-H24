package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.DottoreController;
import Model.Ricevimenti;

@SuppressWarnings("serial")
public class RicevimentiDentista extends JFrame {
    private DottoreController controller;

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteRow = new JButton("ELIMINA");
    private JButton buttonSave = new JButton("SALVA");

    JLabel RecivimentiDenti = new JLabel("*RECIVIMENTI *");

    public RicevimentiDentista(String tipoUtente) {
        this.setTitle("Ricevimenti Dentista");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panelMain);

        // Inizializza il controller con il tipo di utente
        this.controller = new DottoreController(tipoUtente);

        String[] columnNames = {"COGNOME", "NOME", "DATA", "ORA", "CODICE_FISCALE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 80, 800, 600);

        buttonAddRow.setBounds(0, 715, 150, 30);
        buttonDeleteRow.setBounds(170, 715, 150, 30);
        buttonSave.setBounds(350, 715, 150, 30);

        RecivimentiDenti.setBounds(0, 30, 600, 50);
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonDeleteRow);
        setButtonStyle(buttonSave);

        panelMain.setBackground(Color.decode("#89CFF0"));
        RecivimentiDenti.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonSave);

        panelMain.add(RecivimentiDenti);
        buttonAddRow.addActionListener(this::addEmptyRow);
        buttonDeleteRow.addActionListener(this::deleteSelectedRows);
        buttonSave.addActionListener(this::saveLastRow);

        loadDataFromDatabase();
    }

    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    private void addEmptyRow(ActionEvent e) {
        tableModel.addRow(new Object[]{"", "", "", "", ""});
    }

    private void deleteSelectedRows(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 4);

            try {
                controller.deleteRicevimento(codiceFiscale); // Chiama il controller per eliminare
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Riga eliminata con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della riga: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveLastRow(ActionEvent e) {
        int rowCount = tableModel.getRowCount();

        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Non ci sono righe da salvare.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int lastRowIndex = rowCount - 1;

        String cognome = (String) tableModel.getValueAt(lastRowIndex, 0);
        String nome = (String) tableModel.getValueAt(lastRowIndex, 1);
        String data = (String) tableModel.getValueAt(lastRowIndex, 2);
        String ora = (String) tableModel.getValueAt(lastRowIndex, 3);
        String codiceFiscale = (String) tableModel.getValueAt(lastRowIndex, 4);

        if (!validateRowData(cognome, nome, data, ora, codiceFiscale)) {
            return;
        }

        try {
            Ricevimenti ricevimento = new Ricevimenti(cognome, nome, data, ora, codiceFiscale, "DENTISTA");
            controller.addRicevimento(ricevimento); // Chiama il controller per aggiungere
            JOptionPane.showMessageDialog(this, "Ultima riga salvata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Errore durante il salvataggio della riga: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRowData(String cognome, String nome, String data, String ora, String codiceFiscale) {
        if (cognome == null || cognome.trim().isEmpty() ||
            nome == null || nome.trim().isEmpty() ||
            data == null || data.trim().isEmpty() ||
            ora == null || ora.trim().isEmpty() ||
            codiceFiscale == null || codiceFiscale.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Tutti i campi devono essere compilati per ogni riga!", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void loadDataFromDatabase() {
        try {
            List<Ricevimenti> ricevimenti = controller.getAllRicevimenti(); // Chiama il controller per ottenere i dati
            tableModel.setRowCount(0);

            for (Ricevimenti ricevimento : ricevimenti) {
                tableModel.addRow(new Object[]{
                    ricevimento.getCognome(),
                    ricevimento.getNome(),
                    ricevimento.getData(),
                    ricevimento.getOra(),
                    ricevimento.getCodiceFiscale()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Errore SQL: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        String tipoUtente = "DENTISTA"; 
        RicevimentiDentista frame = new RicevimentiDentista(tipoUtente);
        frame.setVisible(true);
    }

	public JPanel getPanelMain() {
		// TODO Auto-generated method stub
		return panelMain;
	}
}
