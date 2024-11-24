package view;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DataBase.InventarioDAOImp;
import Model.InventarioMateriale;

@SuppressWarnings("serial")
public class InventarioMaterialiOrtopedico extends JFrame {
    private InventarioDAOImp inventarioDAO = new InventarioDAOImp("Ortopedico");

    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteMaterial = new JButton("ELIMINA");
    private JButton buttonOrderMaterial = new JButton("ORDINA");
    private JButton buttonSave = new JButton("SALVA");  // Pulsante di salvataggio
    JLabel InventarioMaterialiDenti = new JLabel("*Inventario Materiali*");

    public InventarioMaterialiOrtopedico() {
        setTitle("Inventario Materiali Medici");
        setSize(800, 770);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(panelMain);

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
        buttonSave.setBounds(500, 700, 150, 40);  // Posizione del pulsante SALVA
        InventarioMaterialiDenti.setBounds(0, 30, 600, 50);

        // Aggiunta componenti al pannello principale
        panelMain.setBackground(Color.decode("#89CFF0"));
        InventarioMaterialiDenti.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteMaterial);
        panelMain.add(buttonOrderMaterial);
        panelMain.add(buttonSave);  // Aggiunge il pulsante SALVA
        panelMain.add(InventarioMaterialiDenti);

        // Stile dei bottoni
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonOrderMaterial);
        setButtonStyle(buttonDeleteMaterial);
        setButtonStyle(buttonSave);  // Stile del pulsante SALVA

        // Carica dati dal database all'avvio
        loadDataFromDatabase();

        // Aggiunge una nuova riga
        buttonAddRow.addActionListener(e -> tableModel.addRow(new Object[]{"", "", "", "", ""}));

        // Elimina il materiale selezionato
        buttonDeleteMaterial.addActionListener(e -> deleteSelectedRow());

        // Ordina il materiale selezionato
        buttonOrderMaterial.addActionListener(e -> orderMaterial());

        // Salva l'ultima riga nel database
        buttonSave.addActionListener(e -> saveToDatabase());
    }

    private void loadDataFromDatabase() {
        try {
            List<InventarioMateriale> materiali = inventarioDAO.getAllMateriali();
            tableModel.setRowCount(0);
            for (InventarioMateriale materiale : materiali) {
                tableModel.addRow(new Object[]{
                    materiale.getNomeMateriale(),
                    materiale.getQuantitaDisponibile(),
                    materiale.getQuantitaMinima(),
                    materiale.getDescrizione(),
                    materiale.getStatoOrdine()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nomeMateriale = (String) tableModel.getValueAt(selectedRow, 0);
            try {
                inventarioDAO.deleteMateriale(nomeMateriale);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Materiale eliminato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void orderMaterial() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nomeMateriale = (String) tableModel.getValueAt(selectedRow, 0);
            try {
                inventarioDAO.orderMateriale(nomeMateriale);
                tableModel.setValueAt("Da ordinare", selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Ordine creato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveToDatabase() {
        int lastRow = tableModel.getRowCount() - 1;
        if (lastRow < 0) {
            JOptionPane.showMessageDialog(this, "Non ci sono righe da salvare.", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nomeMateriale = (String) tableModel.getValueAt(lastRow, 0);
        int quantitaDisponibile = Integer.parseInt((String) tableModel.getValueAt(lastRow, 1));
        int quantitaMinima = Integer.parseInt((String) tableModel.getValueAt(lastRow, 2));
        String descrizione = (String) tableModel.getValueAt(lastRow, 3);
        String statoOrdine = (String) tableModel.getValueAt(lastRow, 4);

        String Tipo_di_utente = null;
		InventarioMateriale materiale = new InventarioMateriale(nomeMateriale, quantitaDisponibile, quantitaMinima, descrizione, statoOrdine, Tipo_di_utente );
        try {
            inventarioDAO.addMateriale(materiale);
            JOptionPane.showMessageDialog(this, "Materiale salvato con successo nel database.", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il salvataggio nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        JFrame frame = new InventarioMaterialiOrtopedico();
        frame.setVisible(true);
    }

    public JPanel getPanelA() {
        return panelMain;
    }
}
