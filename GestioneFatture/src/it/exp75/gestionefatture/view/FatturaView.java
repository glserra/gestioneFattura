package it.exp75.gestionefatture.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
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
import it.exp75.gestionefatture.business.IConstanti;
import it.exp75.gestionefatture.business.IntestazioneBusiness;
import it.exp75.gestionefatture.business.PrestazioniBusiness;
import it.exp75.gestionefatture.business.Utility;
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
	private JTextField txtRagSociale;
	private JTextField txtIndirizzo;
	private JTextField txtCap;
	private JTextField txtCitta;
	private JTextField txtProvincia;
	private JTextField txtCF;
	private JTextField txtPIVA;
	
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
		scrollPane.setBounds(10, 184, 756, 285);
		contentPane.add(scrollPane);
		
		tblPrestazioni = new JTable();
		tblPrestazioni.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Sezione", "Descrizione", "Q", "UM", "IVA", "importo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblPrestazioni.getColumnModel().getColumn(1).setPreferredWidth(324);
		scrollPane.setViewportView(tblPrestazioni);
		
//		map = createMap();
//		cbClienti = createComboBox(map);
//		cbClienti.setBounds(134, 12, 446, 20);
//		contentPane.add(cbClienti);
		
		JLabel lblImponibile = new JLabel("Imponibile");
		lblImponibile.setBounds(535, 498, 73, 14);
		contentPane.add(lblImponibile);
		
		txtImponibile = new JTextField();
		txtImponibile.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImponibile.setEditable(false);
		txtImponibile.setBounds(618, 495, 114, 20);
		contentPane.add(txtImponibile);
		txtImponibile.setColumns(10);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(535, 528, 73, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIva.setEditable(false);
		txtIva.setColumns(10);
		txtIva.setBounds(618, 525, 114, 20);
		contentPane.add(txtIva);
		
		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotale.setBounds(535, 555, 73, 22);
		contentPane.add(lblTotale);
		
		txtTotale = new JTextField();
		txtTotale.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotale.setEditable(false);
		txtTotale.setColumns(10);
		txtTotale.setBounds(618, 557, 114, 20);
		contentPane.add(txtTotale);
		
		txtNote = new JTextField();
		txtNote.setEditable(false);
		txtNote.setBounds(22, 557, 352, 20);
		contentPane.add(txtNote);
		txtNote.setColumns(10);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(22, 528, 46, 14);
		contentPane.add(lblNote);
		
		JButton btnPrintPdf = new JButton("");
		btnPrintPdf.setToolTipText("Crea Documento PDF");
		btnPrintPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	            try {
					intest = IntestazioneBusiness.getInstance().getIntestazione();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
				DatiStampaFattura df = new DatiStampaFattura(intest, cliente, ft, prestazioni, pagamento, misure);
				
				df.setImponibile(txtImponibile.getText());
				df.setTotIva(txtIva.getText());
				df.setTotFattura(txtTotale.getText());
				
				CreaPdf.creaFatturaPdf(df);
			}
		});
		btnPrintPdf.setIcon(new ImageIcon(FatturaView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-export-pdf-30.png")));
		btnPrintPdf.setBounds(62, 11, 46, 46);
		contentPane.add(btnPrintPdf);
		
		txtRagSociale = new JTextField();
		txtRagSociale.setBounds(272, 58, 494, 20);
		contentPane.add(txtRagSociale);
		txtRagSociale.setColumns(10);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setBounds(272, 89, 494, 20);
		contentPane.add(txtIndirizzo);
		txtIndirizzo.setColumns(10);
		
		txtCap = new JTextField();
		txtCap.setBounds(272, 120, 57, 20);
		contentPane.add(txtCap);
		txtCap.setColumns(10);
		
		txtCitta = new JTextField();
		txtCitta.setBounds(383, 121, 275, 20);
		contentPane.add(txtCitta);
		txtCitta.setColumns(10);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(742, 120, 24, 20);
		contentPane.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		txtCF = new JTextField();
		txtCF.setBounds(272, 151, 145, 20);
		contentPane.add(txtCF);
		txtCF.setColumns(10);
		
		txtPIVA = new JTextField();
		txtPIVA.setText("");
		txtPIVA.setBounds(612, 151, 154, 20);
		contentPane.add(txtPIVA);
		txtPIVA.setColumns(10);
		
		JLabel lblRagioneSociale = new JLabel("Ragione Sociale:");
		lblRagioneSociale.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRagioneSociale.setBounds(164, 61, 98, 14);
		contentPane.add(lblRagioneSociale);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIndirizzo.setLabelFor(txtIndirizzo);
		lblIndirizzo.setBounds(189, 92, 73, 14);
		contentPane.add(lblIndirizzo);
		
		JLabel lblCap = new JLabel("CAP:");
		lblCap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCap.setLabelFor(txtCap);
		lblCap.setBounds(216, 124, 46, 14);
		contentPane.add(lblCap);
		
		JLabel lblCitta = new JLabel("Citt\u00E0:");
		lblCitta.setLabelFor(txtCitta);
		lblCitta.setLabelFor(txtCitta);
		lblCitta.setBounds(345, 124, 39, 14);
		contentPane.add(lblCitta);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setLabelFor(txtProvincia);
		lblProvincia.setLabelFor(txtProvincia);
		lblProvincia.setBounds(675, 123, 57, 14);
		contentPane.add(lblProvincia);
		
		JLabel lblCf = new JLabel("CF:");
		lblCf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCf.setLabelFor(txtCF);
		lblCf.setBounds(226, 159, 36, 14);
		contentPane.add(lblCf);
		
		JLabel lblPIva = new JLabel("P. IVA:");
		lblPIva.setLabelFor(txtPIVA);
		lblPIva.setBounds(569, 154, 39, 14);
		contentPane.add(lblPIva);
		
		JButton btnModSalva = new JButton("");
		btnModSalva.setIcon(new ImageIcon(FatturaView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-edit-file-30.png")));
		btnModSalva.setBounds(10, 11, 46, 46);
		contentPane.add(btnModSalva);
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
			
			txtRagSociale.setText(cliente.getRagioneSociale());
			txtIndirizzo.setText(cliente.getIndirizzo());
			txtCitta.setText(cliente.getCitta());
			txtCap.setText(cliente.getCap());
			txtProvincia.setText(cliente.getProvincia());
			txtCF.setText(cliente.getCodiceFiscale());
			txtPIVA.setText(cliente.getPartitaIva());
//			selectedCliente(cliente.getId());
//			cbClienti.setEnabled(false);
//			
			setReadonlyCliente(false);
			
			txtNote.setText(ft.getNote());
			//carico le prestazioni da fattura
			loadPrestazioni();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setReadonlyCliente(boolean visible) {
		txtRagSociale.setEditable(visible);
		txtIndirizzo.setEditable(visible);
		txtCitta.setEditable(visible);
		txtCap.setEditable(visible);
		txtProvincia.setEditable(visible);
		txtCF.setEditable(visible);
		txtPIVA.setEditable(visible);
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
				BigDecimal importo = Utility.roundDecimal(p.getImporto(),IConstanti.ROUND_VALUE);
				rowData.add(Utility.euroFormat(importo));
				dtm.addRow(rowData);
				
				imponibile += p.getQuantita()*p.getImporto();
				iva += p.getImporto()*((p.getIva().doubleValue())/100);
				totale += p.getImporto()*(1+(p.getIva()/100));
				
			}
			
			BigDecimal imponibileRound = Utility.roundDecimal(imponibile,IConstanti.ROUND_VALUE);
			BigDecimal ivaRound = Utility.roundDecimal(iva,IConstanti.ROUND_VALUE);
			txtImponibile.setText(Utility.euroFormat(imponibileRound));
			txtIva.setText(Utility.euroFormat(ivaRound));
			txtTotale.setText(Utility.euroFormat(imponibileRound.add(ivaRound)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
