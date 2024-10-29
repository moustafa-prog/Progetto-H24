package Dottore_Occhi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dottore.Ricevimenti;
import Dottore.RicevimentoDAOImpl;

@SuppressWarnings("serial")
	public class RicevimentiOculista extends JFrame {
	RicevimentoDAOImpl ricevimentoDentiDAO = new RicevimentoDAOImpl("occhi");
     private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteRow = new JButton("ELIMINA");
    private JButton buttonSave = new JButton("SALVA");
    private JButton buttonLoadAppointments = new JButton("APPUNTAMENTI");
    JLabel RecivimentiOculista = new JLabel("*RECIVIMENTI *");
    public RicevimentiOculista() {
        this.setTitle("Ricevimenti Denti");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panelMain);

        String[] columnNames = {"COGNOME", "NOME", "DATA", "ORA", "CODICE_FISCALE", "PROSSIMA_VISITA"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 80, 800, 600);

        buttonAddRow.setBounds(0, 715, 150, 30);
        buttonDeleteRow.setBounds(170, 715, 150, 30);
        buttonSave.setBounds(350, 715, 150, 30);
        buttonLoadAppointments.setBounds(550, 715, 200, 30);
        RecivimentiOculista.setBounds(0, 30, 600, 50);
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonDeleteRow);
        setButtonStyle(buttonSave);
        setButtonStyle(buttonLoadAppointments);

        panelMain.setBackground(Color.decode("#89CFF0"));
        RecivimentiOculista.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonSave);
        panelMain.add(buttonLoadAppointments);
        panelMain.add(RecivimentiOculista);
        buttonAddRow.addActionListener(this::addEmptyRow);
        buttonDeleteRow.addActionListener(this::deleteSelectedRows);
        buttonSave.addActionListener(this::saveLastRow);
        buttonLoadAppointments.addActionListener(this::loadDailyAppointments); 

       // loadDailyAppointments();
        loadDataFromDatabase();
    }


	private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    private void addEmptyRow(ActionEvent e) {
        tableModel.addRow(new Object[]{"", "", "", "", "", ""});
    }

    private void deleteSelectedRows(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 4); // L'indice per CODICE_FISCALE
            try {
                ricevimentoDentiDAO.deleteRicevimento(codiceFiscale);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Riga eliminata con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della riga.", "Errore", JOptionPane.ERROR_MESSAGE);
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

        // Ottieni l'ultima riga
        int lastRowIndex = rowCount - 1;

        String cognome = (String) tableModel.getValueAt(lastRowIndex, 0);
        String nome = (String) tableModel.getValueAt(lastRowIndex, 1);
        String data = (String) tableModel.getValueAt(lastRowIndex, 2);
        String ora = (String) tableModel.getValueAt(lastRowIndex, 3);
        String codiceFiscale = (String) tableModel.getValueAt(lastRowIndex, 4);

        // Verifica che tutti i campi siano compilati
        if (!validateRowData(cognome, nome, data, ora, codiceFiscale)) {
            return;
        }

        // Salva l'ultima riga nel database tramite DAO
        Ricevimenti ricevimento = new Ricevimenti(cognome, nome, data, ora, codiceFiscale);
        try {
            ricevimentoDentiDAO.addRicevimento(ricevimento);
            JOptionPane.showMessageDialog(this, "Ultima riga salvata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il salvataggio della riga.", "Errore", JOptionPane.ERROR_MESSAGE);
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


	private void loadDailyAppointments(ActionEvent e) {
        String currentDay = LocalDate.now().toString();  // Ottieni la data corrente come stringa

        try {
            List<Ricevimenti> ricevimenti = ricevimentoDentiDAO.getRicevimentiByDate(currentDay);
            tableModel.setRowCount(0); // Pulisce la tabella prima di inserire i dati

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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento degli appuntamenti giornalieri.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromDatabase() {
        try {
            List<Ricevimenti> ricevimenti = ricevimentoDentiDAO.getAllRicevimenti();
            tableModel.setRowCount(0); // Pulisce la tabella

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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
    	RicevimentiOculista frame = new RicevimentiOculista();
        frame.setVisible(true);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
