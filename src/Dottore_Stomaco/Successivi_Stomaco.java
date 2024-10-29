package Dottore_Stomaco;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Successivi_Stomaco extends JFrame {
    private static final String INSERT_SQL = "INSERT INTO successivi_stomaco(NOME_DOTTORE, COGNOME, NOME, CODICE_FISCALE, PROSSIMA_VISITA, DATA, ORA) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    JPanel panelMain = new JPanel(null);
    JLabel labelNomeDottore = new JLabel("NOME_DOTTORE:");
    JTextField textNomeDottore = new JTextField();
    JLabel SuccessiviDenti = new JLabel("SUCCESSIVI");
    JTable table;
    DefaultTableModel tableModel;
    
    JButton buttonDeleteAll = new JButton("DELETE ALL");
    JButton buttonAdd = new JButton("ADD");
    JButton buttonNewRow = new JButton("ADD RIGHA");
    JButton buttonRemoveSelected = new JButton("REMOVE RIGHA");
    
    public Successivi_Stomaco() {
        this.setTitle("Successivi Stomaco");
        this.setSize(800, 800);  // Altezza maggiore per includere pulsanti sotto la tabella
        this.add(panelMain);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Imposta il layout dei componenti
        setupLayout();
        
        // Aggiungi una nuova riga
        buttonNewRow.addActionListener(e -> addNewRow());

        // Elimina le righe selezionate
        buttonRemoveSelected.addActionListener(e -> removeSelectedRows());

        // Cancella tutto
        buttonDeleteAll.addActionListener(e -> clearAll());

        // Aggiungi dati al database
        buttonAdd.addActionListener(e -> addToDatabase());
    }

    private void setupLayout() {
        panelMain.setBounds(0, 0, 800, 800);

        labelNomeDottore.setBounds(20, 40, 150, 30);
        textNomeDottore.setBounds(150, 40, 150, 30);
        SuccessiviDenti.setBounds(300, 100, 600, 50);
        String[] columnNames = {"COGNOME", "NOME", "CODICE_FISCALE", "PROSSIMA_VISITA", "DATA", "ORA"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 170, 750, 500);  // Tabella centrata sopra i pulsanti

        // Pulsanti posizionati sotto la tabella
        buttonNewRow.setBounds(0, 680, 150, 30);
        buttonRemoveSelected.setBounds(170, 680, 150, 30);
        buttonDeleteAll.setBounds(480, 680, 150, 30);
        buttonAdd.setBounds(650, 680, 150, 30);

        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(labelNomeDottore);
        panelMain.add(textNomeDottore);
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
        labelNomeDottore.setFont(new Font("Arial", Font.BOLD, 15));
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
        textNomeDottore.setText("");
    }

    private void addToDatabase() {
        String nomeDottore = textNomeDottore.getText().trim();
        if (nomeDottore.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Il campo NOME_DOTTORE è obbligatorio.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(null, "Non ci sono righe da aggiungere.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < rowCount; i++) {
            String cognome = (String) tableModel.getValueAt(i, 0);
            String nome = (String) tableModel.getValueAt(i, 1);
            String codiceFiscale = (String) tableModel.getValueAt(i, 2);
            String prossimaVisita = (String) tableModel.getValueAt(i, 3);
            String data = (String) tableModel.getValueAt(i, 4);
            String ora = (String) tableModel.getValueAt(i, 5);

            if (cognome.isEmpty() || nome.isEmpty() || codiceFiscale.isEmpty() || prossimaVisita.isEmpty() || data.isEmpty() || ora.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completa tutti i campi della riga " + (i + 1), "Errore", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            saveToDatabase(nomeDottore, cognome, nome, codiceFiscale, prossimaVisita, data, ora);
        }

        JOptionPane.showMessageDialog(null, "Dati salvati con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToDatabase(String nomeDottore, String cognome, String nome, String codiceFiscale, String prossimaVisita, String data, String ora) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, nomeDottore);
            pstmt.setString(2, cognome);
            pstmt.setString(3, nome);
            pstmt.setString(4, codiceFiscale);
            pstmt.setString(5, prossimaVisita);
            pstmt.setString(6, data);
            pstmt.setString(7, ora);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Record aggiunto con successo.");
            } else {
                System.out.println("Errore durante l'inserimento del record.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante l'inserimento dei dati: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Successivi_Stomaco frame = new Successivi_Stomaco();
        frame.setVisible(true);
    }

    public JPanel getPanelA() {
        return panelMain;
    }
}
