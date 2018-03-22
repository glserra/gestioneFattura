package it.exp75.gestionefatture.view;

import java.awt.Color;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.business.IConstanti;
import it.exp75.gestionefatture.business.PagamentiBusiness;
import it.exp75.gestionefatture.business.Utility;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.Fattura;
import it.exp75.gestionefatture.resources.Log;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.JMenuBar;

public class GestioneFatturazioneView_old {

	private JFrame frame;
	
	private JTable tblClienti;
	private JTable tblFatture;

	private JTabbedPane tabbedPane;
	
	private JPanel pnlHome;
	private JPanel pnlFatture;
	private JPanel pnlClienti;
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	private JButton btnFatture;
	private JButton btnNewCliente;
	private JButton btnNuovo;
	private JButton btnNuova;
	private JButton btnVedi;
	private JButton btnClienti;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneFatturazioneView_old window = new GestioneFatturazioneView_old();
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
	public GestioneFatturazioneView_old() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Log log = new Log();
		log.setup();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 50, 689, 390);
		frame.getContentPane().add(tabbedPane);
		
		pnlHome = new JPanel();
		tabbedPane.addTab("New tab", null, pnlHome, null);
		pnlHome.setLayout(null);
		
		btnFatture = new JButton("Fatture");
		
		btnFatture.setIcon(new ImageIcon(GestioneFatturazioneView_old.class.getResource("/it/exp75/gestionefatture/resources/images/Documents.png")));
		btnFatture.setBounds(32, 55, 211, 87);
		btnFatture.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnFatture.setBackground(Color.white);
		pnlHome.add(btnFatture);
		
		btnClienti = new JButton("Clienti");
		btnClienti.setIcon(new ImageIcon(GestioneFatturazioneView_old.class.getResource("/it/exp75/gestionefatture/resources/images/Users.png")));
		btnClienti.setBounds(253, 55, 211, 87);
		btnClienti.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnClienti.setBackground(Color.white);
		pnlHome.add(btnClienti);
		
		pnlClienti = new JPanel();
		tabbedPane.addTab("Clienti", null, pnlClienti, null);
		pnlClienti.setLayout(null);
		
		scrollPane = new JScrollPane();
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
		
		btnNewCliente = new JButton("Modifica");
		btnNewCliente.setBounds(126, 15, 89, 23);
		pnlClienti.add(btnNewCliente);
		
		btnNuovo = new JButton("Nuovo");
		btnNuovo.setBounds(228, 15, 89, 23);
		pnlClienti.add(btnNuovo);
		
		pnlFatture = new JPanel();
		tabbedPane.addTab("fatture", null, pnlFatture, null);
		pnlFatture.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
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
		tblFatture.getColumnModel().getColumn(0).setWidth(0);
		tblFatture.getColumnModel().getColumn(0).setMinWidth(0);
		tblFatture.getColumnModel().getColumn(0).setMaxWidth(0); 
		tblFatture.getColumnModel().getColumn(1).setPreferredWidth(63);
		tblFatture.getColumnModel().getColumn(2).setPreferredWidth(266);
		tblFatture.getColumnModel().getColumn(3).setPreferredWidth(104);
		tblFatture.getColumnModel().getColumn(4).setPreferredWidth(165);
		scrollPane_1.setViewportView(tblFatture);
		
		btnNuova = new JButton("Nuova");
		btnNuova.setBounds(109, 21, 89, 23);
		pnlFatture.add(btnNuova);
		
		btnVedi = new JButton("Vedi");
		btnVedi.setBounds(208, 21, 89, 23);
		pnlFatture.add(btnVedi);

		
		/*
		 * Gestione Action
		 * 
		 */
		
		
		
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				selMetodoPerTab(index);
			}
		};
		tabbedPane.addChangeListener(changeListener);


		btnClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});

		btnFatture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
			}
		});

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

		btnNuova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FatturaView fv = new FatturaView();
				fv.setVisible(true);
			}
		});

		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView nwc = new ClienteView();
				nwc.setVisible(true);

			}
		});

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


		
	}
	
	
	/*
	 * Metodi
	 */
	
	private void selMetodoPerTab(int indexTabSel) {
		
		switch (indexTabSel) {
		case 1:
			readClienti();
			break;

		case 2:
			readFatture();
			break;

		default:
			break;
		} 
			
	}
	
	private void readFatture() {
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
				
				rowData.add(Utility.formatDateToString(f.getData_fattura(), "dd/MM/yyyy"));
				
				String pagamento = PagamentiBusiness.getInstance().pagamento(f.getPagamento());
				rowData.add(pagamento);
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			showMessageErroreRecuperoDati(IConstanti.LISTA_FATTURE, e);
			e.printStackTrace();
		}
	}
	
	private void readClienti() {
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
			showMessageErroreRecuperoDati(IConstanti.LISTA_CLIENTI, e);
			e.printStackTrace();
		}
	}
	
	
	private void showMessageErroreRecuperoDati(String txt, Exception e) {
		JOptionPane.showMessageDialog(null, IConstanti.ERRORE_RECUPERO_DATI + txt);
		Log.getLogger().info(txt + ": " + e);
	}
}
