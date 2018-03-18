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
import it.exp75.gestionefatture.business.IntestazioneBusiness;
import it.exp75.gestionefatture.business.PrestazioniBusiness;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.DatiStampaFattura;
import it.exp75.gestionefatture.model.Fattura;
import it.exp75.gestionefatture.model.Intestazione;
import it.exp75.gestionefatture.model.Misure;
import it.exp75.gestionefatture.model.Pagamento;
import it.exp75.gestionefatture.model.Prestazione;
import it.exp75.gestionefatture.pdf.CreaPdf;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class FatturaView extends JFrame {

	private JPanel contentPane;
	private JTable tblPrestazioni;
	private JComboBox cbClienti;
	private Map<Integer, Cliente> map = null;
	
	private static Integer ID_FATTURA;
	private JTextField txtImponibile;
	private JTextField txtIva;
	private JTextField txtTotale;
	private JTextField txtNote;
	private Fattura ft;
	private Cliente cliente;
	private List<Prestazione> prestazioni;
	private Pagamento pagamento;
	private List<Misure> misure;
	private Intestazione intest;
	
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
		setBounds(100, 100, 792, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 756, 286);
		contentPane.add(scrollPane);
		
		tblPrestazioni = new JTable();
		tblPrestazioni.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Sezione", "Descrizione", "Q", "UM", "IVA", "importo"
			}
		));
		tblPrestazioni.getColumnModel().getColumn(1).setPreferredWidth(324);
		scrollPane.setViewportView(tblPrestazioni);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(81, 51, 73, 14);
		contentPane.add(lblCliente);
		
		map = createMap();
		cbClienti = createComboBox(map);
		
		cbClienti.setBounds(164, 48, 446, 20);
		contentPane.add(cbClienti);
		
		JLabel lblImponibile = new JLabel("Imponibile");
		lblImponibile.setBounds(548, 437, 73, 14);
		contentPane.add(lblImponibile);
		
		txtImponibile = new JTextField();
		txtImponibile.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImponibile.setEditable(false);
		txtImponibile.setBounds(634, 434, 86, 20);
		contentPane.add(txtImponibile);
		txtImponibile.setColumns(10);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(548, 467, 73, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIva.setEditable(false);
		txtIva.setColumns(10);
		txtIva.setBounds(634, 464, 86, 20);
		contentPane.add(txtIva);
		
		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setBounds(548, 499, 73, 14);
		contentPane.add(lblTotale);
		
		txtTotale = new JTextField();
		txtTotale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotale.setEditable(false);
		txtTotale.setColumns(10);
		txtTotale.setBounds(634, 496, 86, 20);
		contentPane.add(txtTotale);
		
		txtNote = new JTextField();
		txtNote.setEditable(false);
		txtNote.setBounds(10, 496, 352, 20);
		contentPane.add(txtNote);
		txtNote.setColumns(10);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(10, 467, 46, 14);
		contentPane.add(lblNote);
		
		JButton btnPrintPdf = new JButton("Stampa PDF");
		btnPrintPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	            try {
					intest = IntestazioneBusiness.getInstance().getIntestazione();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DatiStampaFattura df = new DatiStampaFattura(intest, cliente, ft, prestazioni, pagamento, misure);
				CreaPdf.creaFatturaPdf(df);
			}
		});
		btnPrintPdf.setIcon(new ImageIcon(FatturaView.class.getResource("/it/exp75/gestionefatture/resources/images/s_x__pdf.gif")));
		btnPrintPdf.setBounds(634, 11, 119, 23);
		contentPane.add(btnPrintPdf);
//		cbClienti.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent arg0) {
//				JOptionPane.showMessageDialog(null, cbClienti.getSelectedIndex());
//			}//((Cliente)cbClienti.getSelectedItem()).getId() +
//		});
	}
	
	
	public void selFattura(Integer idFattura) {
		
		ID_FATTURA = idFattura;
		
		try {
			ft = FattureBusiness.getInstance().fattura(idFattura);
			cliente = ClientiBusiness.getInstance().cliente(ft.getId_cliente());
			selectedCliente(cliente.getId());
			cbClienti.setEnabled(false);
			
			txtNote.setText(ft.getNote());
			//carico le prestazioni da fattura
			loadPrestazioni();
			
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
		
		Double imponibile = 0.0;
		Double iva = 0.0;
		Double totale = 0.0;

		try {
			prestazioni = PrestazioniBusiness.getInstance().listaPrestazioni(ID_FATTURA);
			for(Prestazione p: prestazioni) {
				Vector rowData = new Vector();
				rowData.add(p.getSezione());
				rowData.add(p.getDescrizione());
				rowData.add(p.getQuantita());
				rowData.add(p.getUnita_misura());
				rowData.add(p.getIva());
				rowData.add(p.getImporto());
				dtm.addRow(rowData);
				
				imponibile += p.getQuantita()*p.getImporto();
				iva += p.getImporto()*((p.getIva().doubleValue())/100);
				totale += p.getImporto()*(1+(p.getIva()/100));
				
			}
			
			txtImponibile.setText(imponibile.toString());
			txtIva.setText(iva.toString());
			txtTotale.setText(totale.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
