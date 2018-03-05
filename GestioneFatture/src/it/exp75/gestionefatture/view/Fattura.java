package it.exp75.gestionefatture.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.model.Cliente;

public class Fattura {

	private JFrame frame;
	private JTable tblClienti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fattura window = new Fattura();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fattura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 50, 689, 390);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Clienti", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 49, 635, 291);
		panel.add(scrollPane);
		
		tblClienti = new JTable();
		tblClienti.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "Ragione Sociale", "Partita IVA", "Codice Fiscale", "Indirizzo"
			}
		));
		tblClienti.getColumnModel().getColumn(1).setPreferredWidth(246);
		tblClienti.getColumnModel().getColumn(2).setPreferredWidth(108);
		tblClienti.getColumnModel().getColumn(3).setPreferredWidth(115);
		scrollPane.setViewportView(tblClienti);
		
		JButton btnNewButton = new JButton("Leggi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel dtm = (DefaultTableModel) tblClienti.getModel();
				
				while(dtm.getRowCount() > 0) {
					dtm.removeRow(0);
				}
				
				List<Cliente> cliente;
				try {
					cliente = ClientiBusiness.getInstance().listaClienti();
					for(Cliente c: cliente) {
						Vector rowData = new Vector();
						rowData.add(c.getId());
						rowData.add(c.getRagioneSociale());
						rowData.add(c.getPartitaIva());
						rowData.add(c.getCodiceFiscale());
						dtm.addRow(rowData);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(27, 15, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewCliente = new JButton("Modifica");
		btnNewCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCliente nwCl = new NewCliente();
				nwCl.setVisible(true);
			
				int selectedRow = tblClienti.getSelectedRow();
				Object valueAt = tblClienti.getValueAt(selectedRow, 0);
				Integer idValue = Integer.parseInt(valueAt.toString());
//				Integer idValue = new Integer(10);
				try {
					nwCl.selCliente(idValue);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				JOptionPane.showMessageDialog(null, "x:" + idValue.toString());
			}
		});
		btnNewCliente.setBounds(126, 15, 89, 23);
		panel.add(btnNewCliente);
		
		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCliente nwc = new NewCliente();
				nwc.setVisible(true);
				
			}
		});
		btnNuovo.setBounds(228, 15, 89, 23);
		panel.add(btnNuovo);
	}
}
