package Dottore_Denti;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dottore.Servizi;
import Dottore.ServiziDAOimp;

@SuppressWarnings({ "serial", "unused" })
public class ServicePanelDentista extends JFrame {
	private ServiziDAOimp serviziDAO = new ServiziDAOimp("denti");
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

    public ServicePanelDentista() {
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

        String[] columnNames = {"DESCRIZIONE_DELLE_MALATTIA", "TRATTAMENTO", "CODICE_FISCALE", "PREZZO"};
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
        searchButton.addActionListener(e -> {
            String codiceFiscale = searchField.getText();
            if (!codiceFiscale.isEmpty()) {
                loadServicesByCodiceFiscale(codiceFiscale);
            }
        });

        Button2.addActionListener(e -> saveAllRowsToDatabase());
        Button3.addActionListener(e -> tableModel.addRow(new String[]{"", "", "", ""}));
        Button4.addActionListener(e -> deleteSelectedRows());
    }

    private void loadServicesByCodiceFiscale(String codiceFiscale) {
        try {
            List<Servizi> servizio = serviziDAO.searchByCodiceFiscale(codiceFiscale);
            tableModel.setRowCount(0);
            for (Servizi servizi : servizio) {
                tableModel.addRow(new String[]{
                    servizi.getDescrizioneMalattia(),
                    servizi.getTrattamento(),
                    servizi.getCodiceFiscale(),
                    servizi.getPrezzo()
                });
            }
        } catch (SQLException ex) {
            showError(ex);
        }
    }

    private void saveAllRowsToDatabase() {
        String nomeDottore = label1.getText();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String descrizione = (String) tableModel.getValueAt(i, 0);
            String trattamento = (String) tableModel.getValueAt(i, 1);
            String codiceFiscale = (String) tableModel.getValueAt(i, 2);
            String prezzo = (String) tableModel.getValueAt(i, 3);
            if (validateFields(nomeDottore, prezzo, descrizione, trattamento, codiceFiscale)) {
                Servizi servizi = new Servizi(nomeDottore, prezzo, descrizione, trattamento, codiceFiscale);
                try {
                    serviziDAO.addServizi(servizi);
                } catch (SQLException ex) {
                    showError(ex);
                    break;
                }
            }
        }
    }

    private void deleteSelectedRows() {
        int[] selectedRows = Tabel.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            String codiceFiscale = (String) tableModel.getValueAt(selectedRows[i], 2);
            try {
                serviziDAO.deleteServizi(codiceFiscale);
                tableModel.removeRow(selectedRows[i]);
            } catch (SQLException ex) {
                showError(ex);
            }
        }
    }

    private void showError(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateFields(String nomeDottore, String prezzo, String descrizione, String trattamento, String codiceFiscale) {
        return nomeDottore != null && !nomeDottore.trim().isEmpty() &&
               prezzo != null && !prezzo.trim().isEmpty() &&
               descrizione != null && !descrizione.trim().isEmpty() &&
               trattamento != null && !trattamento.trim().isEmpty() &&
               codiceFiscale != null && !codiceFiscale.trim().isEmpty();
    }

  

    public static void main(String[] args) {
        JFrame frame = new JFrame("ServicePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ServicePanelDentista());
        frame.setSize(800, 700);
        frame.setVisible(true);
    }
    public JPanel getPanelA() {
        return PanelA;
    }
}
