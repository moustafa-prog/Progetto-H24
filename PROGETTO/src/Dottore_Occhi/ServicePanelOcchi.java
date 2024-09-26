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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial", "unused" })
public class ServicePanelOcchi extends JFrame {
    private static final String SEARCH_SQL = "SELECT NOME_DOTTORE, PREZZO, NOME_DELLA_MALATTIA, TRATTAMENTO FROM servicepanel_occhiWHERE CODICE_FISCALE = ?";
    private static final String INSERT_SQL = "INSERT INTO servicepanel_occhi (NOME_DOTTORE, PREZZO, NOME_DELLA_MALATTIA, TRATTAMENTO, CODICE_FISCALE) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM servicepanel_occhi WHERE CODICE_FISCALE = ?";
    
    JPanel PanelA = new JPanel(null);
    JLabel Label = new JLabel("NOME_DOTTORE");
    JLabel searchLabel = new JLabel("CODICE FISCALE:");
    JLabel ServicePanelform = new JLabel("Servizi");
    JTextField label1 = new JTextField();
    JTextField searchField = new JTextField();
    JButton searchButton = new JButton("Search");
    JTable Tabel;
    DefaultTableModel tableModel;
    JButton Button1 = new JButton("DELETE");
    JButton Button2 = new JButton("ADD");
    JButton Button3 = new JButton("ADD RIGHA");
    JButton Button4 = new JButton("REMOVE RIGHA");
    
	private int lastAddedRow = -1;

    public ServicePanelOcchi() {
        this.setTitle("ServicePanel");
        this.setSize(800, 770);
        this.add(PanelA);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setupUI();
        setupActions();
    }

    private void setupUI() {
        PanelA.setBounds(0, 0, 811, 920);

        Label.setBounds(0, 50, 190, 35);
        label1.setBounds(140, 55, 120, 25);
        searchLabel.setBounds(400, 55, 140, 20);
        searchField.setBounds(550, 55, 120, 25);
        searchButton.setBounds(680, 55, 100, 25);
        ServicePanelform.setBounds(250, 160, 600, 50);
        Button1.setBounds(0, 600, 130, 35);
        Button2.setBounds(700, 600, 100, 35);
        Button3.setBounds(170, 600, 150, 35);
        Button4.setBounds(450, 600, 200, 35);

        String[] columnNames = {"NOME_DELLA_MALATTIA", "TRATTAMENTO", "CODICE_FISCALE", "PREZZO"};
        tableModel = new DefaultTableModel(new String[0][0], columnNames);
        Tabel = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(Tabel);
        scrollPane.setBounds(50, 230, 700, 300);

        PanelA.setBackground(Color.decode("#89CFF0"));
        Button1.setBackground(Color.WHITE);
        Button2.setBackground(Color.WHITE);
        Button3.setBackground(Color.WHITE);
        Button4.setBackground(Color.WHITE);
        searchButton.setBackground(Color.WHITE);

        ServicePanelform.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        Button1.setFont(new Font("Congenial black", Font.BOLD, 20));
        Button2.setFont(new Font("Congenial black", Font.BOLD, 20));
        Button3.setFont(new Font("Congenial black", Font.BOLD, 20));
        Button4.setFont(new Font("Congenial black", Font.BOLD, 20));
        Label.setFont(new Font("Congenial black", Font.BOLD, 15));
        searchLabel.setFont(new Font("Congenial black", Font.BOLD, 15));
        searchButton.setFont(new Font("Congenial black", Font.BOLD, 15));

        PanelA.add(Label);
        PanelA.add(label1);
        PanelA.add(searchLabel);
        PanelA.add(searchField);
        PanelA.add(searchButton);
        PanelA.add(ServicePanelform);
        PanelA.add(scrollPane);
        PanelA.add(Button1);
        PanelA.add(Button2);
        PanelA.add(Button3);
        PanelA.add(Button4);
    }

    private void setupActions() {
        // ActionListener per il pulsante "Search"
        searchButton.addActionListener(e -> {
            String codiceFiscale = searchField.getText();
            if (!codiceFiscale.isEmpty()) {
                searchByCodiceFiscale(codiceFiscale);
            } else {
                JOptionPane.showMessageDialog(null, "Inserisci un codice fiscale", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener per il pulsante "DELETE"
        Button1.addActionListener(e -> {
            int selectedRow = Tabel.getSelectedRow();
            if (selectedRow != -1) {
                String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 2);
                deleteFromDatabase(codiceFiscale);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Riga eliminata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Seleziona una riga da eliminare", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener per il pulsante "ADD"
        Button2.addActionListener(e -> {
            if (label1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "NECESSARIO COMPLETARE I DATI", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nomeDottore = label1.getText();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String nomeMalattia = (String) tableModel.getValueAt(i, 0);
                String trattamento = (String) tableModel.getValueAt(i, 1);
                String codiceFiscale = (String) tableModel.getValueAt(i, 2);
                String prezzo = (String) tableModel.getValueAt(i, 3);
                if (validateRowData(nomeDottore, prezzo, nomeMalattia, trattamento, codiceFiscale)) {
                    // Save each row to the database
                    saveToDatabase(nomeDottore, prezzo, nomeMalattia, trattamento, codiceFiscale);
                } else {
                    return; // Exit if validation fails for any row
                }
            }
            JOptionPane.showMessageDialog(null, "Tutti i dati sono stati salvati con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        });
        

        // ActionListener per il pulsante "+"
        Button3.addActionListener(e -> tableModel.addRow(new String[]{"", "", "", ""}));

        // ActionListener per il pulsante "Button5"
        Button4.addActionListener(e -> {
            int[] selectedRows = Tabel.getSelectedRows();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                tableModel.removeRow(selectedRows[i]);
            }
        });
    }

    private void searchByCodiceFiscale(String codiceFiscale) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SEARCH_SQL)) {
            pstmt.setString(1, codiceFiscale);
            try (ResultSet rs = pstmt.executeQuery()) {
                tableModel.setRowCount(0);
                while (rs.next()) {
                    String nomeDottore = rs.getString("NOME_DOTTORE");
                    String prezzo = rs.getString("PREZZO");
                    String nomeMalattia = rs.getString("NOME_DELLA_MALATTIA");
                    String trattamento = rs.getString("TRATTAMENTO");
                    tableModel.addRow(new String[]{nomeMalattia, trattamento, codiceFiscale, prezzo});
                }
            }
        } catch (SQLException ex) {
            handleSQLException(ex);
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

        String nomeDottore = (String) tableModel.getValueAt(lastRowIndex, 0);
        String prezzo = (String) tableModel.getValueAt(lastRowIndex, 1);
        String nomeMalattia = (String) tableModel.getValueAt(lastRowIndex, 2);
        String trattamento = (String) tableModel.getValueAt(lastRowIndex, 3);
        String codiceFiscale = (String) tableModel.getValueAt(lastRowIndex, 4);

        // Verifica che tutti i campi siano compilati
        if (!validateRowData(nomeDottore, prezzo, nomeMalattia, trattamento, codiceFiscale)) {
            return;
        }

        // Salva l'ultima riga nel database
        saveToDatabase(nomeDottore, prezzo, nomeMalattia, trattamento, codiceFiscale);

        JOptionPane.showMessageDialog(this, "Ultima riga salvata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }private boolean validateRowData(String nomeDottore, String prezzo, String nomeMalattia, String trattamento, String codiceFiscale) {
        if (nomeDottore == null || nomeDottore.trim().isEmpty() ||
        		prezzo == null || prezzo.trim().isEmpty() ||
        		nomeMalattia == null || nomeMalattia.trim().isEmpty() ||
        		trattamento == null || trattamento.trim().isEmpty() ||
                codiceFiscale == null || codiceFiscale.trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Tutti i campi devono essere compilati per ogni riga!", "Errore", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }

    private void saveToDatabase(String nomeDottore, String prezzo, String nomeMalattia, String trattamento, String codiceFiscale) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
            pstmt.setString(1, nomeDottore);
            pstmt.setString(2, prezzo);
            pstmt.setString(3, nomeMalattia);
            pstmt.setString(4, trattamento);
            pstmt.setString(5, codiceFiscale);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            handleSQLException(ex);
        }
    }

    private void deleteFromDatabase(String codiceFiscale) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setString(1, codiceFiscale);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            handleSQLException(ex);
        }
    }

    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
    }

    private void handleSQLException(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ServicePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ServicePanelOcchi());
        frame.setSize(800, 700);
        frame.setVisible(true);
    }
    public JPanel getPanelA() {
        return PanelA;
    }
}
