package Dottore_Denti;

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

import Dottore.InventarioDAOImp;
import Dottore.InventarioMateriale;

@SuppressWarnings("serial")
public class InventarioMaterialiDentista extends JFrame {
	 private InventarioDAOImp inventarioDAO = new InventarioDAOImp("denti");
	 
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteMaterial = new JButton("ELIMINA");
    private JButton buttonOrderMaterial = new JButton("ORDINA");
    JLabel InventarioMaterialiDenti = new JLabel("*Inventario Materiali*");
    public InventarioMaterialiDentista() {
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
        InventarioMaterialiDenti.setBounds(0, 30, 600, 50);
        // Aggiunta componenti al pannello principale
        panelMain.setBackground(Color.decode("#89CFF0"));
        InventarioMaterialiDenti.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteMaterial);
        panelMain.add(buttonOrderMaterial);
        panelMain.add(InventarioMaterialiDenti);
        // Stile dei bottoni
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonOrderMaterial);
        setButtonStyle(buttonDeleteMaterial);

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

 
    // Configura lo stile dei bottoni
    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        JFrame frame = new InventarioMaterialiDentista();
        frame.setVisible(true);
    }


    public JPanel getPanelA() {
        return panelMain;
    }
}
