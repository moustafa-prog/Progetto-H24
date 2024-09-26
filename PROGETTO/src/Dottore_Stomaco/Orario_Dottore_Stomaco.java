package Dottore_Stomaco;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Orario_Dottore_Stomaco extends JFrame {
    private static final String INSERT_SQL = "INSERT INTO orario_dottore_stomaco(COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM orario_dottore_stomaco WHERE EMAIL = ?";
    private static final String SELECT_SQL = "SELECT COGNOME, NOME, EMAIL, NUMERO_DI_TELEFONO, ORARIO FROM orario_dottore_stomaco";

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain;
    private JButton buttonAddRow, buttonDeleteRow, buttonSave;
    JLabel Orario_Dottore_Stomaco = new JLabel("*ORARIO*");

    public Orario_Dottore_Stomaco() {
        setTitle("I Paziente Denti");
        setSize(800, 770);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panelMain = new JPanel(null);
        add(panelMain);

        // Impostazione delle colonne della tabella
        String[] columnNames = {"COGNOME", "NOME", "EMAIL", "NUMERO DI TELEFONO", "ORARIO"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 800, 610);
        Orario_Dottore_Stomaco.setFont(new Font("Traditional Arabic", Font.BOLD, 40));

        // Configura i bottoni
        buttonAddRow = createButton("ADD RIGA", 10, 700, 150, 40);
        buttonDeleteRow = createButton("DELETE", 650, 700, 150, 40);
        buttonSave = createButton("ADD", 325, 700, 150, 40);
        Orario_Dottore_Stomaco.setBounds(0, 30, 600, 50);

        // Aggiungi componenti al pannello principale
        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonSave);
        panelMain.add(Orario_Dottore_Stomaco);

        // Carica i dati dal database
        loadDataFromDatabase();

        // Event listener per aggiungere una riga vuota
        buttonAddRow.addActionListener(e -> tableModel.addRow(new Object[]{"", "", "", "", ""}));

        // Event listener per eliminare righe selezionate
        buttonDeleteRow.addActionListener(e -> deleteSelectedRows());

        // Event listener per salvare i dati nel database
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

            // Validazione
            if (!validateRow(cognome, nome, email, numeroDiTelefono, orario)) {
                JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Salva i dati nel database
            saveToDatabase(cognome, nome, email, numeroDiTelefono, orario);
        } else {
            JOptionPane.showMessageDialog(null, "Nessuna riga da salvare!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRow(String cognome, String nome, String email, String numeroDiTelefono, String orario) {
        return !cognome.trim().isEmpty() && !nome.trim().isEmpty() && !email.trim().isEmpty() &&
                !numeroDiTelefono.trim().isEmpty() && !orario.trim().isEmpty();
    }

    private void saveToDatabase(String cognome, String nome, String email, String numeroDiTelefono, String orario) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, cognome);
            pstmt.setString(2, nome);
            pstmt.setString(3, email);
            pstmt.setString(4, numeroDiTelefono);
            pstmt.setString(5, orario);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Record aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase(); // Ricarica i dati aggiornati
            } else {
                JOptionPane.showMessageDialog(null, "Errore nell'inserimento del record.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0); // Pulizia della tabella prima di aggiungere i nuovi dati

            while (rs.next()) {
                String cognome = rs.getString("COGNOME");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                String numeroDiTelefono = rs.getString("NUMERO_DI_TELEFONO");
                String orario = rs.getString("ORARIO");

                tableModel.addRow(new String[]{cognome, nome, email, numeroDiTelefono, orario});
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
            deleteFromDatabase(email);
            tableModel.removeRow(selectedRows[i]);
        }
    }

    private void deleteFromDatabase(String email) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setString(1, email);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Record eliminato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Errore nell'eliminazione del record.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new Orario_Dottore_Stomaco();
        frame.setVisible(true);
    }

public JPanel getPanelA() {
     return panelMain;
 }
}


