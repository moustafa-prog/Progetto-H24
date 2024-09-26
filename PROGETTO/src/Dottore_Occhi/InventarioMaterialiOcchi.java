package Dottore_Occhi;

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
public class InventarioMaterialiOcchi extends JFrame {

    private static final String INSERT_SQL = "INSERT INTO inventario_materiali_occhi (NOME_MATERIALE, QUANTITA_DISPONIBILE, QUANTITA_MINIMA, DESCRIZIONE, STATO_ORDINE) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_SQL = "DELETE FROM inventario_materiali_occhi WHERE NOME_MATERIALE = ?";
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteMaterial = new JButton("ELIMINA");
    private JButton buttonOrderMaterial = new JButton("ORDINA");
    JLabel InventarioMaterialiOcchi = new JLabel("*InventarioMateriali*");
    public InventarioMaterialiOcchi() {
        setTitle("Inventario Materiali Medici");
        setSize(800, 770);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(panelMain);
        InventarioMaterialiOcchi.setFont(new Font("Traditional Arabic", Font.BOLD, 40));

        // Definizione delle colonne della tabella
        String[] columnNames = {"NOME MATERIALE", "QUANTITA DISPONIBILE", "QUANTITA MINIMA", "DESCRIZIONE", "STATO ORDINE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 800, 610);

        // Configurazione dei bottoni
        buttonAddRow.setBounds(10, 700, 150, 40);
        buttonDeleteMaterial.setBounds(650, 700, 150, 40);
        buttonOrderMaterial.setBounds(325, 700, 150, 40);
        InventarioMaterialiOcchi.setBounds(0, 30, 600, 50);

        // Aggiunta componenti al pannello principale
        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteMaterial);
        panelMain.add(buttonOrderMaterial);

        // Stile dei bottoni
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonOrderMaterial);
        setButtonStyle(buttonDeleteMaterial);
        panelMain.add(InventarioMaterialiOcchi);

        // Carica dati dal database all'avvio
        loadDataFromDatabase();

        // Aggiunge una nuova riga
        buttonAddRow.addActionListener(e -> tableModel.addRow(new Object[]{"", "", "", "", ""}));

        // Elimina il materiale selezionato
        buttonDeleteMaterial.addActionListener(e -> deleteSelectedRow());

        // Ordina il materiale selezionato
        buttonOrderMaterial.addActionListener(e -> orderMaterial());
    }

    // Elimina il materiale selezionato dalla tabella e dal database
    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nomeMateriale = (String) tableModel.getValueAt(selectedRow, 0);
            deleteFromDatabase(nomeMateriale);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Ordina il materiale selezionato
    private void orderMaterial() {
        int selectedRow = table.getSelectedRow(); // Ottieni la riga selezionata
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da ordinare.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Recupera i dati dalla riga selezionata
        String nomeMateriale = (String) tableModel.getValueAt(selectedRow, 0);
        String quantitaDisponibile = (String) tableModel.getValueAt(selectedRow, 1);
        String quantitaMinima = (String) tableModel.getValueAt(selectedRow, 2);
        String descrizione = (String) tableModel.getValueAt(selectedRow, 3);
        String statoOrdine = (String) tableModel.getValueAt(selectedRow, 4);

        // Verifica se il materiale è già da ordinare
        if ("Da ordinare".equals(statoOrdine)) {
            JOptionPane.showMessageDialog(this, "Il materiale è già da ordinare.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aggiorna lo stato dell'ordine a "Da ordinare" nella tabella
        tableModel.setValueAt("Da ordinare", selectedRow, 4);

        // Chiama il metodo per salvare nel database
        saveToDatabase(nomeMateriale, quantitaDisponibile, quantitaMinima, descrizione, "Da ordinare");

        tableModel.setValueAt("Da ordinare", selectedRow, 4);
        JOptionPane.showMessageDialog(this, "Ordine creato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToDatabase(String nomeMateriale, String quantitaDisponibile, String quantitaMinima, String descrizione, String statoOrdine) {
        System.out.println("Tentativo di salvataggio: " + nomeMateriale + ", " + quantitaDisponibile + ", " + quantitaMinima + ", " + descrizione + ", " + statoOrdine);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setString(1, nomeMateriale);
            pstmt.setString(2, quantitaDisponibile);
            pstmt.setString(3, quantitaMinima);
            pstmt.setString(4, descrizione);
            pstmt.setString(5, statoOrdine);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Materiale aggiunto con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, "Impossibile aggiungere il materiale.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina un materiale dal database
    private void deleteFromDatabase(String nomeMateriale) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setString(1, nomeMateriale);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Materiale eliminato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carica i dati dal database
    private void loadDataFromDatabase() {
        String query = "SELECT NOME_MATERIALE, QUANTITA_DISPONIBILE, QUANTITA_MINIMA, DESCRIZIONE, STATO_ORDINE FROM inventario_materiali_occhi";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progetto", "root", "Moustafa2001");
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0);

            while (rs.next()) {
                String nomeMateriale = rs.getString("NOME_MATERIALE");
                String quantitaDisponibile = rs.getString("QUANTITA_DISPONIBILE");
                String quantitaMinima = rs.getString("QUANTITA_MINIMA");
                String descrizione = rs.getString("DESCRIZIONE");
                String statoOrdine = rs.getString("STATO_ORDINE");

                tableModel.addRow(new Object[]{nomeMateriale, quantitaDisponibile, quantitaMinima, descrizione, statoOrdine});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Configura lo stile dei bottoni
    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        JFrame frame = new InventarioMaterialiOcchi();
        frame.setVisible(true);
    }


    public JPanel getPanelA() {
        return panelMain;
    }
}
