package view;

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

import Controller.DottoreController;

import Model.Visiti;

@SuppressWarnings("serial")
public class VisitiDentista extends JFrame {
    private JPanel PanelA = new JPanel(null);
    private JLabel VisitiLabel = new JLabel("*VISITI*");
    private JTable Tabel;
    private DefaultTableModel tableModel;
    private JButton Button1 = new JButton("DELETE");
    private DottoreController controller; // Dichiarazione del tipo come interfaccia

    public VisitiDentista() {
        this.setTitle("VISITI");
        this.setSize(800, 770);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(PanelA);

        String tipoUtente="DENTISTA";

        this.controller = new DottoreController(tipoUtente);

        setupUI();
        loadVisitiForToday(); // Carica gli appuntamenti per la data odierna
    }

    private void setupUI() {
        PanelA.setBounds(0, 0, 811, 920);
        Button1.setBounds(0, 600, 130, 35);
        String[] columnNames = {"NOME", "COGNOME", "CODICE_FISCALE", "DATA", "ORA"};
        tableModel = new DefaultTableModel(new String[0][0], columnNames);
        Tabel = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(Tabel);
        scrollPane.setBounds(50, 230, 700, 300);
        VisitiLabel.setBounds(250, 160, 600, 50);

        PanelA.setBackground(Color.decode("#89CFF0"));
        Button1.setBackground(Color.WHITE);
        VisitiLabel.setFont(new Font("ALGERIAN", Font.BOLD, 50));
        Button1.setFont(new Font("Congenial black", Font.BOLD, 20));
        PanelA.add(scrollPane);
        PanelA.add(Button1);
        PanelA.add(VisitiLabel);
        Button1.addActionListener(this::deleteVisiti);
    }

    private void loadVisitiForToday() {
        LocalDate today = LocalDate.now(); // Ottieni la data odierna
        try {
            // Chiama il metodo getVisitiByDate sull'istanza visitiDAO
            List<Visiti> visitiList = controller.getVisitiByDate(today);
            tableModel.setRowCount(0); // Pulisce la tabella

            for (Visiti visiti : visitiList) {
                tableModel.addRow(new Object[]{
                    visiti.getNome(),
                    visiti.getCognome(),
                    visiti.getCodiceFiscale(),
                    visiti.getData(),
                    visiti.getOra()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il caricamento dei visitatori di oggi.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteVisiti(ActionEvent e) {
        int selectedRow = Tabel.getSelectedRow();
        if (selectedRow != -1) {
            String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 2); // Indice per CODICE_FISCALE
            try {
                // Chiama il metodo deleteVisiti sull'istanza visitiDAO
                controller.deleteVisiti(codiceFiscale);
                tableModel.removeRow(selectedRow); // Rimuovi la riga dalla tabella
                JOptionPane.showMessageDialog(this, "Riga eliminata con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della riga.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        VisitiDentista visiti = new VisitiDentista();
        visiti.setVisible(true);
    }

    public JPanel getPanelA() {
        return PanelA;
    }
}
