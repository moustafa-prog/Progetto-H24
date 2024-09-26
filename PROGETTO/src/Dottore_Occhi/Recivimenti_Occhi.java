package Dottore_Occhi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Recivimenti_Occhi extends JFrame {
    private static final String SELECT_APPOINTMENTS_SQL = "SELECT COGNOME, NOME, DATA, ORA, CODICE_FISCALE, PROSSIMA_VISITA FROM successivi_occhi WHERE DATA = ?";
    private static final String INSERT_SQL = "INSERT INTO recivementi_occhi (COGNOME, NOME, DATA, ORA, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM recivementi_occhi WHERE  CODICE_FISCALE = ?";
    JTable table;
    DefaultTableModel tableModel;
    JPanel panelMain = new JPanel(null); 
    JButton buttonAddRow = new JButton("ADD RIGHA");
    JButton buttonDeleteRow = new JButton("DELETE");
    JButton buttonSave = new JButton("SAVE");
    JButton buttonLoadFutureAppointments = new JButton("NOUVA DATI");
    JLabel RecivimentiOcchi = new JLabel("*RECIVIMENTI *");
    public Recivimenti_Occhi() {
        this.setTitle("Ricevimenti Occhi");
        this.setSize(800, 800);  // Modificato per garantire spazio
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panelMain);

        String[] columnNames = {"COGNOME", "NOME", "DATA", "ORA", "CODICE_FISCALE", "PROSSIMA_VISITA"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 80, 800, 600);  // Regolato per dare più spazio

        buttonAddRow.setBounds(0, 715, 150, 30);
        buttonDeleteRow.setBounds(170, 715, 150, 30);
        buttonSave.setBounds(420, 715, 150, 30);
        buttonLoadFutureAppointments.setBounds(580, 715, 170, 30);  // Posizionato il pulsante in modo visibile
        RecivimentiOcchi.setBounds(0, 30, 600, 50);
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonDeleteRow);
        setButtonStyle(buttonSave);
        setButtonStyle(buttonLoadFutureAppointments);

        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonSave);
        panelMain.add(buttonLoadFutureAppointments);
        panelMain.add(RecivimentiOcchi);

        // Collegamento dei listener ai pulsanti
        buttonAddRow.addActionListener(this::addEmptyRow);
        buttonDeleteRow.addActionListener(this::deleteSelectedRows);
        buttonSave.addActionListener(this::saveLastRow);
        buttonLoadFutureAppointments.addActionListener(this::loadFutureAppointments);
        RecivimentiOcchi.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        // Carica gli appuntamenti del giorno corrente all'avvio
        loadDailyAppointments();
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
            String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 4); // Cambiato l'indice a 4 per CODICE_FISCALE
            deleteFromDatabase(codiceFiscale);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteFromDatabase(String codiceFiscale) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setString(1, codiceFiscale);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Materiale eliminato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Errore nell'eliminazione del materiale.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
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

        // Salva l'ultima riga nel database
        saveToDatabase(cognome, nome, data, ora, codiceFiscale);

        JOptionPane.showMessageDialog(this, "Ultima riga salvata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
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

    private void saveToDatabase(String cognome, String nome, String data, String ora, String codiceFiscale) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, cognome);
            pstmt.setString(2, nome);
            pstmt.setString(3, data);
            pstmt.setString(4, ora);
            pstmt.setString(5, codiceFiscale);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record aggiunto con successo.");
            } else {
                System.out.println("Impossibile aggiungere il record.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDailyAppointments() {
        String currentDay = LocalDate.now().toString();  // Ottieni la data corrente come stringa

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(SELECT_APPOINTMENTS_SQL)) {

            pstmt.setString(1, currentDay);

            try (ResultSet rs = pstmt.executeQuery()) {
                tableModel.setRowCount(0); // Pulisce la tabella prima di inserire i dati

                while (rs.next()) {
                    String cognome = rs.getString("COGNOME");
                    String nome = rs.getString("NOME");
                    String data = rs.getString("DATA");
                    String ora = rs.getString("ORA");
                    String codiceFiscale = rs.getString("CODICE_FISCALE");
                    String prossimaVisita = rs.getString("PROSSIMA_VISITA");

                    tableModel.addRow(new Object[]{cognome, nome, data, ora, codiceFiscale, prossimaVisita});
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento degli appuntamenti giornalieri: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadDataFromDatabase() {
        String query = "SELECT  COGNOME, NOME, DATA, ORA, CODICE_FISCALE  FROM recivementi_occhi";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0);  // Pulizia della tabella

            while (rs.next()) {
                String cognome = rs.getString("COGNOME");
                String nome = rs.getString("NOME");
                String dataDiNascita = rs.getString("DATA");
                String codiceFiscale = rs.getString("ORA");
                String nomeDottore = rs.getString("CODICE_FISCALE");

                tableModel.addRow(new Object[]{cognome, nome, dataDiNascita, codiceFiscale, nomeDottore});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadFutureAppointments(ActionEvent e) {
        loadDailyAppointments();  // Carica automaticamente gli appuntamenti del giorno corrente
    }

    public static void main(String[] args) {
        Recivimenti_Occhi frame = new Recivimenti_Occhi();
        frame.setVisible(true);
    }

	public JPanel getPanelA() {
		
		return panelMain;
	}
}

