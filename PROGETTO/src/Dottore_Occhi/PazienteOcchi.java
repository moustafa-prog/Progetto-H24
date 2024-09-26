package Dottore_Occhi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class PazienteOcchi extends JFrame {
    private static final String INSERT_SQL = "INSERT INTO paziente_occhi(COGNOME, NOME, DATA_DI_NASCITA, CODICE_FISCALE, NOME_DOTTORE) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM paziente_occhi WHERE  CODICE_FISCALE = ?";
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteRow = new JButton("DELETE");
    private JButton buttonAddToDB = new JButton("ADD");
    JLabel PazienteOcchi = new JLabel("*PAZIENTE*");
    public PazienteOcchi() {
        this.setTitle("I PAZIENTE");
        this.setSize(800, 770);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panelMain);
        PazienteOcchi.setFont(new Font("Traditional Arabic", Font.BOLD, 40));

        String[] columnNames = {"COGNOME", "NOME", "DATA_DI_NASCITA", "CODICE_FISCALE", "NOME_DOTTORE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 800, 610);
        
        // Configura i bottoni
        buttonAddRow.setBounds(10, 700, 150, 40);
        buttonDeleteRow.setBounds(650, 700, 150, 40);
        buttonAddToDB.setBounds(325, 700, 150, 40);
        PazienteOcchi.setBounds(0, 30, 600, 50);
        // Aggiungi componenti al pannello
        panelMain.setBackground(Color.decode("#89CFF0"));
        PazienteOcchi.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonAddToDB);
        panelMain.add(PazienteOcchi);
        // Stile dei bottoni
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonDeleteRow);
        setButtonStyle(buttonAddToDB);
        buttonDeleteRow.addActionListener(this::deleteSelectedRows);
        // Carica dati dal database all'avvio
        loadDataFromDatabase();

        // Aggiunge una riga vuota
        buttonAddRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Object[]{"", "", "", "", ""});
            }
        });
    }
        // Elimina le righe selezionate
       
        private void deleteSelectedRows(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 3); // Cambiato l'indice a 4 per CODICE_FISCALE
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
        

        // Salva l'ultima riga nel database
        buttonAddToDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastRowIndex = tableModel.getRowCount() - 1;

                if (lastRowIndex >= 0) {
                    String cognome = (String) tableModel.getValueAt(lastRowIndex, 0);
                    String nome = (String) tableModel.getValueAt(lastRowIndex, 1);
                    String dataDiNascita = (String) tableModel.getValueAt(lastRowIndex, 2);  //ANNI-MESE-GIORNI
                    String codiceFiscale = (String) tableModel.getValueAt(lastRowIndex, 3);
                    String nomeDottore = (String) tableModel.getValueAt(lastRowIndex, 4);

                    // Validazione dei dati
                    if (validateRow(cognome, nome, dataDiNascita, codiceFiscale, nomeDottore)) {
                        saveToDatabase(cognome, nome, dataDiNascita, codiceFiscale, nomeDottore);
                        JOptionPane.showMessageDialog(null, "Record aggiunto con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tutti i campi devono essere compilati.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nessun record da aggiungere", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateRow(String cognome, String nome, String dataDiNascita, String codiceFiscale, String nomeDottore) {
        return !cognome.trim().isEmpty() && !nome.trim().isEmpty() && !dataDiNascita.trim().isEmpty() && !codiceFiscale.trim().isEmpty() && !nomeDottore.trim().isEmpty();
    }

    private void saveToDatabase(String cognome, String nome, String dataDiNascita, String codiceFiscale, String nomeDottore) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, cognome);
            pstmt.setString(2, nome);
            pstmt.setString(3, dataDiNascita);
            pstmt.setString(4, codiceFiscale);
            pstmt.setString(5, nomeDottore);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record aggiunto con successo.");
            } else {
                System.out.println("Impossibile aggiungere il record.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromDatabase() {
        String query = "SELECT COGNOME, NOME, DATA_DI_NASCITA, CODICE_FISCALE, NOME_DOTTORE FROM paziente_occhi";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0);  // Pulizia della tabella

            while (rs.next()) {
                String cognome = rs.getString("COGNOME");
                String nome = rs.getString("NOME");
                String dataDiNascita = rs.getString("DATA_DI_NASCITA");
                String codiceFiscale = rs.getString("CODICE_FISCALE");
                String nomeDottore = rs.getString("NOME_DOTTORE");

                tableModel.addRow(new Object[]{cognome, nome, dataDiNascita, codiceFiscale, nomeDottore});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        JFrame frame = new PazienteOcchi();
        frame.setVisible(true);
    }
    public JPanel getPanelA() {
        return panelMain;
    }
}
