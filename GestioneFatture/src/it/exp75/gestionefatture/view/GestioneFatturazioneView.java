package it.exp75.gestionefatture.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.business.PagamentiBusiness;
import it.exp75.gestionefatture.business.Utility;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.Fattura;

public class GestioneFatturazioneView {

	private JFrame frame;
	private JTable tblClienti;
	private JTable tblFatture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneFatturazioneView window = new GestioneFatturazioneView();
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
	public GestioneFatturazioneView() {
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
		
		JPanel pnlClienti = new JPanel();
		tabbedPane.addTab("Clienti", null, pnlClienti, null);
		pnlClienti.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 49, 635, 291);
		pnlClienti.add(scrollPane);
		
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
		pnlClienti.add(btnNewButton);
		
		JButton btnNewCliente = new JButton("Modifica");
		btnNewCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView nwCl = new ClienteView();
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
		pnlClienti.add(btnNewCliente);
		
		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView nwc = new ClienteView();
				nwc.setVisible(true);
				
			}
		});
		btnNuovo.setBounds(228, 15, 89, 23);
		pnlClienti.add(btnNuovo);
		
		JPanel pnlFatture = new JPanel();
		tabbedPane.addTab("fatture", null, pnlFatture, null);
		pnlFatture.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 55, 664, 296);
		pnlFatture.add(scrollPane_1);
		
		tblFatture = new JTable();
		tblFatture.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "Numero", "Ragione Sociale", "Data", "Pagamento"
			}
		));
		tblFatture.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblFatture.getColumnModel().getColumn(1).setPreferredWidth(63);
		tblFatture.getColumnModel().getColumn(2).setPreferredWidth(266);
		tblFatture.getColumnModel().getColumn(3).setPreferredWidth(104);
		tblFatture.getColumnModel().getColumn(4).setPreferredWidth(165);
		scrollPane_1.setViewportView(tblFatture);
		
		JButton btnLeggiFatture = new JButton("Leggi");
		btnLeggiFatture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel dtm = (DefaultTableModel) tblFatture.getModel();

				while(dtm.getRowCount() > 0) {
					dtm.removeRow(0);
				}

				Cliente cl = null;
				
				List<Fattura> fattura;
				try {
					fattura = FattureBusiness.getInstance().listaFatture();
					for(Fattura f: fattura) {
						Vector rowData = new Vector();
						
						rowData.add(f.getId_fattura());
						rowData.add(f.getNum_fattura());
						
						cl = ClientiBusiness.getInstance().cliente(f.getId_cliente());
						rowData.add(cl.getRagioneSociale());
						
						rowData.add(Utility.formatDateToString((f.getData_fattura())));
						
						String pagamento = PagamentiBusiness.getInstance().pagamento(f.getPagamento());
						rowData.add(pagamento);
						
						dtm.addRow(rowData);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnLeggiFatture.setBounds(10, 21, 89, 23);
		pnlFatture.add(btnLeggiFatture);
		
		JButton btnNuova = new JButton("Nuova");
		btnNuova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FatturaView fv = new FatturaView();
				fv.setVisible(true);
			}
		});
		btnNuova.setBounds(109, 21, 89, 23);
		pnlFatture.add(btnNuova);
		
		JButton btnVedi = new JButton("Vedi");
		btnVedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FatturaView fv = new FatturaView();
				fv.setVisible(true);
				
				int selectedRow = tblFatture.getSelectedRow();
				Object valueAt = tblFatture.getValueAt(selectedRow, 0);
				int idFattura = Integer.parseInt(valueAt.toString());
				
				fv.selFattura(idFattura);
				
			}
		});
		btnVedi.setBounds(208, 21, 89, 23);
		pnlFatture.add(btnVedi);
	}
}
