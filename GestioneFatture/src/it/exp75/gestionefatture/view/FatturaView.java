package it.exp75.gestionefatture.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.business.PrestazioniBusiness;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.Fattura;
import it.exp75.gestionefatture.model.Prestazione;

public class FatturaView extends JFrame {

	private JPanel contentPane;
	private JTable tblPrestazioni;
	private JComboBox cbClienti;
	private Map<Integer, Cliente> map = null;
	
	private static Integer ID_FATTURA;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FatturaView frame = new FatturaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FatturaView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 756, 431);
		contentPane.add(scrollPane);
		
		tblPrestazioni = new JTable();
		tblPrestazioni.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Sezione", "Descrizione", "UM", "IVA", "importo"
			}
		));
		tblPrestazioni.getColumnModel().getColumn(1).setPreferredWidth(324);
		scrollPane.setViewportView(tblPrestazioni);
		
		//carico le prestazioni da fattura
		loadPrestazioni();
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(30, 47, 37, 14);
		contentPane.add(lblCliente);
		
		map = createMap();
		cbClienti = createComboBox(map);

		
//		Cliente cl = new Cliente();
//		List<Cliente> listaClienti = null;
//		
//		try {
//			listaClienti = ClientiBusiness.getInstance().listaClienti();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (Cliente c : listaClienti) {
//			cbClienti.addItem(new Cliente(c.getId(),c.getRagioneSociale()));
//		}
		
		cbClienti.setBounds(122, 41, 446, 20);
		contentPane.add(cbClienti);
//		cbClienti.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent arg0) {
//				JOptionPane.showMessageDialog(null, cbClienti.getSelectedIndex());
//			}//((Cliente)cbClienti.getSelectedItem()).getId() +
//		});
	}
	
	
	public void selFattura(Integer idFattura) {
		
		Fattura ft = null;
		ID_FATTURA = idFattura;
		
		try {
			ft = FattureBusiness.getInstance().fattura(idFattura);
			Cliente cliente = ClientiBusiness.getInstance().cliente(ft.getId_cliente());
			selectedCliente(cliente.getId());
			cbClienti.setEnabled(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Map<Integer, Cliente> createMap() {
		Map<Integer, Cliente> map = new HashMap<>();
		
		List<Cliente> listaClienti = null;
		
		try {
			listaClienti = ClientiBusiness.getInstance().listaClienti();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Cliente c : listaClienti) {
			map.put(c.getId(),new Cliente(c.getId(),c.getRagioneSociale()));
		}
		
		return map;
	}
	
	private JComboBox createComboBox(final Map<Integer, Cliente> map) {
		final JComboBox cbox = new JComboBox();
		for (Integer id : map.keySet()) {
			cbox.addItem(map.get(id));
		}
		cbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente selectedItem = (Cliente) cbox.getSelectedItem();
				System.out.println(selectedItem.getId() + " " + selectedItem.getRagioneSociale());
			}
		});

		return cbox;
	}
	
	private void selectedCliente(Integer idCliente) {
		for (Integer id : map.keySet()) {
			if(map.get(id).getId() == idCliente) {
				cbClienti.setSelectedItem(map.get(id));
			}
		}
	}
	
	private void loadPrestazioni() {
		DefaultTableModel dtm = (DefaultTableModel) tblPrestazioni.getModel();
		
		while(dtm.getRowCount() > 0) {
			dtm.removeRow(0);
		}
		
		List<Prestazione> prestazioni;
		try {
			prestazioni = PrestazioniBusiness.getInstance().listaPrestazioni(ID_FATTURA);
			for(Prestazione p: prestazioni) {
				Vector rowData = new Vector();
				rowData.add(p.getSezione());
				rowData.add(p.getDescrizione());
				rowData.add(p.getUnita_misura());
				rowData.add(p.getIva());
				rowData.add(p.getImporto());
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
