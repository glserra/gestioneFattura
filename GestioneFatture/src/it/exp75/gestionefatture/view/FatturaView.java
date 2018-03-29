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
		));
		tblPrestazioni.getColumnModel().getColumn(1).setPreferredWidth(324);
		scrollPane.setViewportView(tblPrestazioni);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(42, 15, 73, 14);
		contentPane.add(lblCliente);
		
		map = createMap();
		cbClienti = createComboBox(map);
		
		cbClienti.setBounds(134, 12, 446, 20);
		contentPane.add(cbClienti);
		
		JLabel lblImponibile = new JLabel("Imponibile");
		lblImponibile.setBounds(560, 498, 73, 14);
		contentPane.add(lblImponibile);
		
		txtImponibile = new JTextField();
		txtImponibile.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImponibile.setEditable(false);
		txtImponibile.setBounds(646, 495, 86, 20);
		contentPane.add(txtImponibile);
		txtImponibile.setColumns(10);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(560, 528, 73, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIva.setEditable(false);
		txtIva.setColumns(10);
		txtIva.setBounds(646, 525, 86, 20);
		contentPane.add(txtIva);
		
		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setBounds(560, 560, 73, 14);
		contentPane.add(lblTotale);
		
		txtTotale = new JTextField();
		txtTotale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotale.setEditable(false);
		txtTotale.setColumns(10);
		txtTotale.setBounds(646, 557, 86, 20);
		contentPane.add(txtTotale);
		
		txtNote = new JTextField();
		txtNote.setEditable(false);
		txtNote.setBounds(22, 557, 352, 20);
		contentPane.add(txtNote);
		txtNote.setColumns(10);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(22, 528, 46, 14);
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
				
				df.setImponibile(txtImponibile.getText());
				df.setTotIva(txtIva.getText());
				df.setTotFattura(txtTotale.getText());
				
				CreaPdf.creaFatturaPdf(df);
			}
		});
		btnPrintPdf.setIcon(new ImageIcon(FatturaView.class.getResource("/it/exp75/gestionefatture/resources/images/s_x__pdf.gif")));
		btnPrintPdf.setBounds(634, 11, 119, 23);
		contentPane.add(btnPrintPdf);
		
		txtRagSociale = new JTextField();
		txtRagSociale.setBounds(135, 55, 292, 20);
		contentPane.add(txtRagSociale);
		txtRagSociale.setColumns(10);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setBounds(134, 86, 293, 20);
		contentPane.add(txtIndirizzo);
		txtIndirizzo.setColumns(10);
		
		txtCap = new JTextField();
		txtCap.setBounds(133, 118, 86, 20);
		contentPane.add(txtCap);
		txtCap.setColumns(10);
		
		txtCitta = new JTextField();
		txtCitta.setBounds(240, 118, 119, 20);
		contentPane.add(txtCitta);
		txtCitta.setColumns(10);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(386, 117, 86, 20);
		contentPane.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		txtCF = new JTextField();
		txtCF.setBounds(134, 149, 145, 20);
		contentPane.add(txtCF);
		txtCF.setColumns(10);
		
		txtPIVA = new JTextField();
		txtPIVA.setText("");
		txtPIVA.setBounds(341, 149, 86, 20);
		contentPane.add(txtPIVA);
		txtPIVA.setColumns(10);
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
			txtCF.setText(cliente.getCodiceFiscale());
			txtPIVA.setText(cliente.getPartitaIva());
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
