package it.exp75.gestionefatture.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.business.IConstanti;
import it.exp75.gestionefatture.business.Utility;
import it.exp75.gestionefatture.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField txtRagSociale;
	private JTextField txtPartitaIva;
	private JTextField txtCodFiscale;
	private JButton btnSalva;
	private JLabel lblIndirizzo;
	private JTextField txtIndirizzo;
	private JTextField txtCap;
	private JLabel lblCap;
	private JTextField txtCitta;
	private JLabel lblCitta;
	private JTextField txtProvincia;
	private JLabel lblProvincia;
	private JTextField txtNote;
	private JLabel lblNote;

	public static int IDCLIENTE=0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteView frame = new ClienteView();
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
	public ClienteView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRagioneSociale = new JLabel(IConstanti.RAGIONE_SOCIALE);
		lblRagioneSociale.setBounds(31, 110, 113, 14);
		contentPane.add(lblRagioneSociale);
		
		txtRagSociale = new JTextField();
		txtRagSociale.setBounds(156, 107, 406, 20);
		contentPane.add(txtRagSociale);
		txtRagSociale.setColumns(10);
		
		JLabel icnErrorPI = new JLabel("icnLabel");
		icnErrorPI.setIcon(new ImageIcon(ClienteView.class.getResource("../resources/images/s_s_nono.gif")));
		icnErrorPI.setBounds(317, 140, 19, 20);
		icnErrorPI.setVisible(false);
		contentPane.add(icnErrorPI);
		
		txtPartitaIva = new JTextField();
		txtPartitaIva.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				icnErrorPI.setVisible(false);
				if(txtPartitaIva.getText().length() > 0) {
					String resultCheckPI = Utility.ControllaPIVA(txtPartitaIva.getText());
					if(!"".equals(resultCheckPI)) {
						icnErrorPI.setVisible(true);
						JOptionPane.showMessageDialog(null, resultCheckPI);
					}
				}
			}
		});

		txtPartitaIva.setBounds(156, 139, 151, 20);
		contentPane.add(txtPartitaIva);
		txtPartitaIva.setColumns(10);

		JLabel icnErrorCF = new JLabel("icnLabel2");
		icnErrorCF.setIcon(new ImageIcon(ClienteView.class.getResource("../resources/images/s_s_nono.gif")));
		icnErrorCF.setBounds(317, 169, 19, 20);
		icnErrorCF.setVisible(false);
		contentPane.add(icnErrorCF);
		
		txtCodFiscale = new JTextField();
		txtCodFiscale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				icnErrorCF.setVisible(false);
				if(txtCodFiscale.getText().length() > 0) {
					String resultCheckCF = Utility.ControllaCF(txtCodFiscale.getText());
					if(!"".equalsIgnoreCase(resultCheckCF)) {
						icnErrorCF.setVisible(true);
						JOptionPane.showMessageDialog(null, resultCheckCF);
					}
				}
			}
		});

		txtCodFiscale.setBounds(156, 169, 151, 20);
		contentPane.add(txtCodFiscale);
		txtCodFiscale.setColumns(10);
		
		JLabel lblPartitaIva = new JLabel(IConstanti.PARTITA_IVA);
		lblPartitaIva.setBounds(31, 143, 82, 14);
		contentPane.add(lblPartitaIva);
		
		JLabel lblCodiceFiscale = new JLabel(IConstanti.CODICE_FISCALE);
		lblCodiceFiscale.setBounds(31, 172, 113, 14);
		contentPane.add(lblCodiceFiscale);
		
		JButton btnAnnulla = new JButton("");
		btnAnnulla.setToolTipText("Annulla");
		btnAnnulla.setIcon(new ImageIcon(ClienteView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-cancel-16.png")));
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAnnulla.setBounds(67, 11, 33, 33);
		contentPane.add(btnAnnulla);
		
		btnSalva = new JButton("");
		btnSalva.setToolTipText("Salva");
		btnSalva.setIcon(new ImageIcon(ClienteView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-save-16.png")));
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ragSociale = txtRagSociale.getText();
				String partitaIva = txtPartitaIva.getText();
				String codFiscale = txtCodFiscale.getText();
				String indirizzo = txtIndirizzo.getText();
				String cap = txtCap.getText();
				String citta = txtCitta.getText();
				String provincia = txtProvincia.getText();
				String note = txtNote.getText();
				
				String error = "";
				if(Utility.checkRequiredFieldEmpty(ragSociale)) {
					error += "- " + IConstanti.RAGIONE_SOCIALE + "\n";
				}
				
				if(Utility.checkRequiredFieldEmpty(partitaIva)) {
					error += "- " + IConstanti.PARTITA_IVA + "\n";
				} else if(!Utility.ControllaPIVA(partitaIva).equals("")){
					error += "- " + Utility.ControllaPIVA(partitaIva).replace("\n","") + "\n";
				}
				
				if(Utility.checkRequiredFieldEmpty(codFiscale)) {
					error += "- " + IConstanti.CODICE_FISCALE + "\n";
				} else if(!Utility.ControllaCF(codFiscale).equals("")){
					error += "- " + Utility.ControllaCF(codFiscale).replace("\n",	"") + "\n";
				}
				
				if(Utility.checkRequiredFieldEmpty(indirizzo)) {
					error += "- " + IConstanti.INDIRIZZO + "\n";
				}

				if(Utility.checkRequiredFieldEmpty(cap)) {
					error += "- " + IConstanti.CAP + "\n";
				}

				if(Utility.checkRequiredFieldEmpty(citta)) {
					error += "- " + IConstanti.CITTA + "\n";
				}

				if(Utility.checkRequiredFieldEmpty(provincia)) {
					error += "- " + IConstanti.PROVINCIA + "\n";
				}


				if(error.trim().length() > 0 || !error.equals("")) {

					error = "I seguenti compi sono obbligatori:\n" + error;
					JOptionPane.showMessageDialog(null, error);
					
				} else {


					Cliente cl = new Cliente();
					cl.setRagioneSociale(ragSociale);
					cl.setPartitaIva(partitaIva);
					cl.setCodiceFiscale(codFiscale);
					cl.setIndirizzo(indirizzo);
					cl.setCap(cap);
					cl.setCitta(citta);
					cl.setProvincia(provincia);
					cl.setNote(note);

					try {
						int idCliente = 0;
						if(IDCLIENTE > 0) {
							cl.setId(IDCLIENTE);
							idCliente = ClientiBusiness.getInstance().updateCliente(cl);
						} else {
							idCliente = ClientiBusiness.getInstance().salvaCliente(cl);
						}

						if(idCliente > 0) {
							JOptionPane.showMessageDialog(null, "Cliente salvato con successo!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalva.setBounds(30, 11, 33, 33);
		contentPane.add(btnSalva);
		
		lblIndirizzo = new JLabel(IConstanti.INDIRIZZO);
		lblIndirizzo.setBounds(31, 203, 82, 14);
		contentPane.add(lblIndirizzo);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setBounds(156, 200, 406, 20);
		contentPane.add(txtIndirizzo);
		txtIndirizzo.setColumns(10);
		
		txtCap = new JTextField();
		txtCap.setBounds(156, 231, 46, 20);
		contentPane.add(txtCap);
		txtCap.setColumns(10);
		
		lblCap = new JLabel(IConstanti.CAP);
		lblCap.setBounds(31, 234, 46, 14);
		contentPane.add(lblCap);
		
		txtCitta = new JTextField();
		txtCitta.setBounds(156, 262, 238, 20);
		contentPane.add(txtCitta);
		txtCitta.setColumns(10);
		
		lblCitta = new JLabel(IConstanti.CITTA);
		lblCitta.setBounds(31, 265, 60, 14);
		contentPane.add(lblCitta);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(156, 297, 27, 20);
		contentPane.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		lblProvincia = new JLabel(IConstanti.PROVINCIA);
		lblProvincia.setBounds(31, 300, 82, 14);
		contentPane.add(lblProvincia);
		
		txtNote = new JTextField();
		txtNote.setBounds(156, 332, 406, 20);
		contentPane.add(txtNote);
		txtNote.setColumns(10);
		
		lblNote = new JLabel(IConstanti.NOTE);
		lblNote.setBounds(31, 335, 46, 14);
		contentPane.add(lblNote);
		
	}
	
	public void selCliente(Integer id) throws SQLException {
		
		Cliente cliente = ClientiBusiness.getInstance().cliente(id);
		txtRagSociale.setText(cliente.getRagioneSociale());
		txtPartitaIva.setText(cliente.getPartitaIva());
		txtCodFiscale.setText(cliente.getCodiceFiscale());
		txtIndirizzo.setText(cliente.getIndirizzo());
		txtCap.setText(cliente.getCap());
		txtCitta.setText(cliente.getCitta());
		txtProvincia.setText(cliente.getProvincia());
		txtNote.setText(cliente.getNote());
		IDCLIENTE = cliente.getId();
		
	}
}
