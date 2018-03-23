package it.exp75.gestionefatture.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
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
import java.awt.Dimension;

public class GestioneFatturazioneView {

	private JFrame frame;

	private JTable tblClienti;
	private JTable tblFatture;

	private JPanel pnlHome;
	private JPanel pnlFatture;
	private JPanel pnlClienti;

	private JScrollPane scrollClienti;
	private JScrollPane scrollFatture;

	private JButton btnFatture;
	private JButton btnNewCliente;
	private JButton btnNuovo;
	private JButton btnNuova;
	private JButton btnVedi;
	private JButton btnClienti;
	private JButton btnHeaderHome;
	private JButton btnHeaderFatture;
	private JButton btnHeaderClienti;
	private JToolBar toolBar;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;

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

		Log log = new Log();
		log.setup();

		frame = new JFrame();
		frame.setBounds(100, 100, 725, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		pnlFatture = new JPanel();
		pnlFatture.setName(IConstanti.PANEL_FATTURE);
		pnlFatture.setVisible(false);

		pnlHome = new JPanel();
		pnlHome.setName(IConstanti.PANEL_HOME);
		pnlHome.setBounds(0, 53, 709, 399);
		frame.getContentPane().add(pnlHome);
		pnlHome.setLayout(null);

		btnFatture = new JButton("Fatture");

		btnFatture.setIcon(new ImageIcon(GestioneFatturazioneView.class.getResource("/it/exp75/gestionefatture/resources/images/Documents.png")));
		btnFatture.setBounds(29, 55, 211, 87);
		btnFatture.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnFatture.setBackground(Color.white);
		
		btnFatture.addActionListener(actionFatture);
		
		pnlHome.add(btnFatture);

		btnClienti = new JButton("Clienti");
		btnClienti.setIcon(new ImageIcon(GestioneFatturazioneView.class.getResource("/it/exp75/gestionefatture/resources/images/Users.png")));
		btnClienti.setBounds(250, 55, 211, 87);
		btnClienti.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		btnClienti.setBackground(Color.white);
		
		btnClienti.addActionListener(actionClienti);
		
		pnlHome.add(btnClienti);

		button = new JButton("Clienti");
		button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button.setBackground(Color.WHITE);
		button.setBounds(471, 55, 211, 87);
		pnlHome.add(button);

		button_1 = new JButton("Clienti");
		button_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(29, 153, 211, 87);
		pnlHome.add(button_1);

		button_2 = new JButton("Clienti");
		button_2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(471, 153, 211, 87);
		pnlHome.add(button_2);

		button_3 = new JButton("Clienti");
		button_3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(250, 153, 211, 87);
		pnlHome.add(button_3);

		button_4 = new JButton("Clienti");
		button_4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(29, 251, 211, 87);
		pnlHome.add(button_4);

		button_5 = new JButton("Clienti");
		button_5.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_5.setBackground(Color.WHITE);
		button_5.setBounds(250, 251, 211, 87);
		pnlHome.add(button_5);

		button_6 = new JButton("Clienti");
		button_6.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button_6.setBackground(Color.WHITE);
		button_6.setBounds(471, 251, 211, 87);
		pnlHome.add(button_6);

	
		pnlFatture.setBounds(0, 53, 709, 399);
		frame.getContentPane().add(pnlFatture);
		pnlFatture.setLayout(null);

		scrollFatture = new JScrollPane();
		scrollFatture.setBounds(10, 49, 689, 339);
		pnlFatture.add(scrollFatture);

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
		scrollFatture.setViewportView(tblFatture);

		btnNuova = new JButton("Nuova");
		btnNuova.setBounds(109, 21, 89, 23);
		pnlFatture.add(btnNuova);

		btnVedi = new JButton("Vedi");
		btnVedi.setBounds(208, 21, 89, 23);
		pnlFatture.add(btnVedi);

		btnNuova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FatturaView fv = new FatturaView();
				fv.setVisible(true);
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

		pnlClienti = new JPanel();
		pnlClienti.setName(IConstanti.PANEL_CLIENTI);
		pnlClienti.setVisible(false);
		pnlClienti.setBounds(0, 53, 709, 399);
		frame.getContentPane().add(pnlClienti);
		pnlClienti.setLayout(null);

		scrollClienti = new JScrollPane();
		scrollClienti.setBounds(10, 49, 689, 339);
		pnlClienti.add(scrollClienti);

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
		scrollClienti.setViewportView(tblClienti);

		btnNewCliente = new JButton("Modifica");
		btnNewCliente.setBounds(126, 15, 89, 23);
		pnlClienti.add(btnNewCliente);

		btnNuovo = new JButton("Nuovo");
		btnNuovo.setBounds(228, 15, 89, 23);
		pnlClienti.add(btnNuovo);

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

		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView nwc = new ClienteView();
				nwc.setVisible(true);

			}
		});

		toolBar = new JToolBar();
		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 709, 42);
		frame.getContentPane().add(toolBar);

		btnHeaderHome = new ButtonTB("");
		btnHeaderHome.setSize(new Dimension(32, 32));
		btnHeaderHome.setToolTipText("Home");
		toolBar.add(btnHeaderHome);
		btnHeaderHome.setIcon(new ImageIcon(GestioneFatturazioneView.class.getResource("/it/exp75/gestionefatture/resources/images/Universal Binary.png")));

		btnHeaderFatture = new ButtonTB("");
		btnHeaderFatture.setToolTipText("Fatture");
		toolBar.add(btnHeaderFatture);
		btnHeaderFatture.setIcon(new ImageIcon(GestioneFatturazioneView.class.getResource("/it/exp75/gestionefatture/resources/images/Documents.png")));

		btnHeaderClienti = new ButtonTB("");
		btnHeaderClienti.setToolTipText("Clienti");
		toolBar.add(btnHeaderClienti);
		btnHeaderClienti.setIcon(new ImageIcon(GestioneFatturazioneView.class.getResource("/it/exp75/gestionefatture/resources/images/Users.png")));
		
		btnHeaderClienti.addActionListener(actionClienti);
		btnHeaderFatture.addActionListener(actionFatture);
		btnHeaderHome.addActionListener(actionHome);


	}


	/*
	 * Metodi
	 */

	ActionListener actionFatture = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			setVisiblePanel(IConstanti.PANEL_FATTURE);
			readFatture();
		}
	};
	
	ActionListener actionClienti = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			setVisiblePanel(IConstanti.PANEL_CLIENTI);
			readClienti();
		}
	};
	
	ActionListener actionHome = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			setVisiblePanel(IConstanti.PANEL_HOME);
		}
	};
	
	private void setVisiblePanel(String str) {
		
		for(Component c: frame.getContentPane().getComponents()) {
			if(c instanceof JPanel) {
				String panelName = ((JPanel) c).getName();
				if(panelName != null && panelName.equals(str)){
					c.setVisible(true);
				} else {
					c.setVisible(false);
				}
				
			}
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

