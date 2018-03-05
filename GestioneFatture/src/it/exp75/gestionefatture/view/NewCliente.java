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

public class NewCliente extends JFrame {

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
	private JLabel lblCitt;
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
					NewCliente frame = new NewCliente();
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
	public NewCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRagioneSociale = new JLabel(IConstanti.RAGIONE_SOCIALE);
		lblRagioneSociale.setBounds(34, 34, 82, 14);
		contentPane.add(lblRagioneSociale);
		
		txtRagSociale = new JTextField();
		txtRagSociale.setBounds(126, 31, 439, 20);
		contentPane.add(txtRagSociale);
		txtRagSociale.setColumns(10);
		
		txtPartitaIva = new JTextField();
		txtPartitaIva.setBounds(126, 62, 151, 20);
		contentPane.add(txtPartitaIva);
		txtPartitaIva.setColumns(10);

		JLabel icnErrorCF = new JLabel("icnLabel");
		icnErrorCF.setIcon(new ImageIcon(NewCliente.class.getResource("../resources/images/s_s_nono.gif")));
		icnErrorCF.setBounds(287, 93, 19, 20);
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

		txtCodFiscale.setBounds(126, 93, 151, 20);
		contentPane.add(txtCodFiscale);
		txtCodFiscale.setColumns(10);
		
		JLabel lblPartitaIva = new JLabel(IConstanti.PARTITA_IVA);
		lblPartitaIva.setBounds(34, 67, 82, 14);
		contentPane.add(lblPartitaIva);
		
		JLabel lblCodiceFiscale = new JLabel(IConstanti.CODICE_FISCALE);
		lblCodiceFiscale.setBounds(34, 96, 82, 14);
		contentPane.add(lblCodiceFiscale);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setIcon(new ImageIcon(NewCliente.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAnnulla.setBounds(484, 371, 103, 23);
		contentPane.add(btnAnnulla);
		
		btnSalva = new JButton("Salva");
		btnSalva.setIcon(new ImageIcon(NewCliente.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
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
				}
				
				if(Utility.checkRequiredFieldEmpty(codFiscale)) {
					error += "- " + IConstanti.CODICE_FISCALE + "\n";
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

					error = "I segunti compi sono obbligatori:\n" + error;
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
		btnSalva.setBounds(368, 371, 106, 23);
		contentPane.add(btnSalva);
		
		lblIndirizzo = new JLabel(IConstanti.INDIRIZZO);
		lblIndirizzo.setBounds(34, 127, 46, 14);
		contentPane.add(lblIndirizzo);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setBounds(126, 124, 439, 20);
		contentPane.add(txtIndirizzo);
		txtIndirizzo.setColumns(10);
		
		txtCap = new JTextField();
		txtCap.setBounds(126, 155, 46, 20);
		contentPane.add(txtCap);
		txtCap.setColumns(10);
		
		lblCap = new JLabel(IConstanti.CAP);
		lblCap.setBounds(34, 158, 46, 14);
		contentPane.add(lblCap);
		
		txtCitta = new JTextField();
		txtCitta.setBounds(126, 186, 238, 20);
		contentPane.add(txtCitta);
		txtCitta.setColumns(10);
		
		lblCitt = new JLabel(IConstanti.CITTA);
		lblCitt.setBounds(34, 189, 46, 14);
		contentPane.add(lblCitt);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(126, 221, 27, 20);
		contentPane.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		lblProvincia = new JLabel(IConstanti.PROVINCIA);
		lblProvincia.setBounds(34, 224, 46, 14);
		contentPane.add(lblProvincia);
		
		txtNote = new JTextField();
		txtNote.setBounds(126, 256, 439, 20);
		contentPane.add(txtNote);
		txtNote.setColumns(10);
		
		lblNote = new JLabel(IConstanti.NOTE);
		lblNote.setBounds(34, 259, 46, 14);
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
