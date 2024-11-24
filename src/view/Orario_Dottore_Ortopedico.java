package view;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.OrarioDottoreDAOimp;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class Orario_Dottore_Ortopedico extends JFrame {
    private OrarioDottoreDAOimp orarioDottoreDAO = new OrarioDottoreDAOimp("Ortopedico");
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain;
    private JButton buttonAddRow, buttonDeleteRow, buttonSave;
    JLabel labelOrarioDottoreDenti = new JLabel("*ORARIO*");

    public Orario_Dottore_Ortopedico() {
        setTitle("Orario Dottore Denti");
        setSize(800, 770);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panelMain = new JPanel(null);
        add(panelMain);

        String[] columnNames = {"COGNOME", "NOME", "EMAIL", "NUMERO DI TELEFONO", "ORARIO"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 800, 610);
        labelOrarioDottoreDenti.setFont(new Font("Traditional Arabic", Font.BOLD, 40));

        buttonAddRow = createButton("ADD RIGA", 10, 700, 150, 40);
        buttonDeleteRow = createButton("DELETE", 650, 700, 150, 40);
        buttonSave = createButton("SAVE", 325, 700, 150, 40);
        labelOrarioDottoreDenti.setBounds(0, 30, 600, 50);

        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonSave);
        panelMain.add(labelOrarioDottoreDenti);

        loadDataFromDatabase();

        buttonAddRow.addActionListener(e -> tableModel.addRow(new Object[]{"", "", "", "", ""}));
        buttonDeleteRow.addActionListener(e -> deleteSelectedRows());
        buttonSave.addActionListener(e -> saveLastRowToDatabase());
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        return button;
    }

    private void saveLastRowToDatabase() {
        int lastRowIndex = tableModel.getRowCount() - 1;

        if (lastRowIndex >= 0) {
            String cognome = (String) tableModel.getValueAt(lastRowIndex, 0);
            String nome = (String) tableModel.getValueAt(lastRowIndex, 1);
            String email = (String) tableModel.getValueAt(lastRowIndex, 2);
            String numeroDiTelefono = (String) tableModel.getValueAt(lastRowIndex, 3);
            String orario = (String) tableModel.getValueAt(lastRowIndex, 4);

            if (!validateRow(cognome, nome, email, numeroDiTelefono, orario)) {
                JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                orarioDottoreDAO.addOrarioDottore(cognome, nome, email, numeroDiTelefono, orario);
                JOptionPane.showMessageDialog(null, "Record aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Errore durante l'inserimento dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nessuna riga da salvare!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRow(String cognome, String nome, String email, String numeroDiTelefono, String orario) {
        return !cognome.trim().isEmpty() && !nome.trim().isEmpty() && !email.trim().isEmpty() &&
                !numeroDiTelefono.trim().isEmpty() && !orario.trim().isEmpty();
    }

    private void loadDataFromDatabase() {
        try {
            List<String[]> orari = orarioDottoreDAO.getAllOrariDottore();
            tableModel.setRowCount(0);

            for (String[] row : orari) {
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il recupero dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRows() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Nessuna riga selezionata per l'eliminazione.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            String email = (String) tableModel.getValueAt(selectedRows[i], 2);
            try {
                orarioDottoreDAO.deleteOrarioDottore(email);
                tableModel.removeRow(selectedRows[i]);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new Orario_Dottore_Ortopedico();
        frame.setVisible(true);
    }

    public JPanel getPanelA() {
        return panelMain;
    }
}
