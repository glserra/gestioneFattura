package it.exp75.gestionefatture.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.IConstanti;
import it.exp75.gestionefatture.business.MisureBusiness;
import it.exp75.gestionefatture.business.PrestazioniBusiness;
import it.exp75.gestionefatture.business.Utility;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.Misure;
import it.exp75.gestionefatture.model.Prestazione;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PrestazioniView extends JFrame {

	private Integer idFattura;
	private JPanel contentPane;
	private JTextField txtSezione;
	private JTextField txtDescr;
	private JTextField txtQuant;
	private JTextField txtImporto;
	private JTextField txtIva;
	private JComboBox cbMisure;
	private Map<Integer, Misure> map = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrestazioniView frame = new PrestazioniView();
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
	public PrestazioniView() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSezione = new JTextField();
		txtSezione.setBounds(10, 110, 414, 20);
		contentPane.add(txtSezione);
		txtSezione.setColumns(10);
		
		txtDescr = new JTextField();
		txtDescr.setBounds(10, 160, 414, 20);
		contentPane.add(txtDescr);
		txtDescr.setColumns(10);
		
		txtQuant = new JTextField();
		txtQuant.setBounds(10, 214, 86, 20);
		contentPane.add(txtQuant);
		txtQuant.setColumns(10);
		
		cbMisure = new JComboBox();
		map = createMap();
		cbMisure = createComboBox(map);
		cbMisure.setBounds(121, 214, 77, 20);
		contentPane.add(cbMisure);
		
		txtImporto = new JTextField();
		txtImporto.setBounds(320, 214, 104, 20);
		contentPane.add(txtImporto);
		txtImporto.setColumns(10);
		
		txtIva = new JTextField();
		txtIva.setBounds(247, 214, 46, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		
		JButton btnSalva = new JButton("");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvaPrestazione();
			}
		});
		btnSalva.setIcon(new ImageIcon(PrestazioniView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-save-30.png")));
		btnSalva.setBounds(7, 11, 40, 40);
		contentPane.add(btnSalva);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(PrestazioniView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-cancel-30.png")));
		btnNewButton.setBounds(54, 11, 40, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblSezione = new JLabel(IConstanti.PRESTAZ_SEZIONE);
		lblSezione.setLabelFor(txtSezione);
		lblSezione.setBounds(10, 95, 62, 14);
		contentPane.add(lblSezione);
		
		JLabel lblDescrizione = new JLabel(IConstanti.PRESTAZ_DESCRIZIONE);
		lblDescrizione.setLabelFor(txtDescr);
		lblDescrizione.setBounds(10, 146, 86, 14);
		contentPane.add(lblDescrizione);
		
		JLabel lblQuantit = new JLabel(IConstanti.PRESTAZ_QUANTITA);
		lblQuantit.setLabelFor(txtQuant);
		lblQuantit.setBounds(10, 199, 62, 14);
		contentPane.add(lblQuantit);
		
		JLabel lblUm = new JLabel(IConstanti.PRESTAZ_UM);
		lblUm.setLabelFor(cbMisure);
		lblUm.setBounds(170, 199, 28, 14);
		contentPane.add(lblUm);
		
		JLabel lblIva = new JLabel(IConstanti.PRESTAZ_IVA);
		lblIva.setLabelFor(txtIva);
		lblIva.setBounds(247, 199, 46, 14);
		contentPane.add(lblIva);
		
		JLabel lblImporto = new JLabel(IConstanti.PRESTAZ_IMPORTO);
		lblImporto.setLabelFor(txtImporto);
		lblImporto.setBounds(320, 199, 64, 14);
		contentPane.add(lblImporto);
	}
	
	private JComboBox createComboBox(final Map<Integer, Misure> map) {
		final JComboBox cbox = new JComboBox();
		for (Integer id : map.keySet()) {
			cbox.addItem(map.get(id));
		}
//		cbox.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Cliente selectedItem = (Cliente) cbox.getSelectedItem();
//				System.out.println(selectedItem.getId() + " " + selectedItem.getRagioneSociale());
//			}
//		});

		return cbox;
	}
	
	private Map<Integer, Misure> createMap() {
		Map<Integer, Misure> map = new HashMap<>();
		
		List<Misure> listaMisure = null;
		
		try {
			listaMisure = MisureBusiness.getInstance().listaMisure();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Misure m : listaMisure) {
			map.put(m.getId(),new Misure(m.getId(),m.getTipo()));
		}
		
		return map;
	}

	private void salvaPrestazione() {
		
		String sezione = txtSezione.getText();
		String descrizione = txtDescr.getText();
		String quantita = txtQuant.getText();
		String iva = txtIva.getText();
		String importo = txtImporto.getText();

		String error = null;
		
		if(Utility.checkRequiredFieldEmpty(descrizione)) {
			error += "- " + IConstanti.PRESTAZ_DESCRIZIONE + "\n";
		}

		if(Utility.checkRequiredFieldEmpty(quantita)) {
			error += "- " + IConstanti.PRESTAZ_QUANTITA + "\n";
		}
		
		if(Utility.checkRequiredFieldEmpty(iva)) {
			error += "- " + IConstanti.PRESTAZ_IVA + "\n";
		}
		
		if(Utility.checkRequiredFieldEmpty(importo)) {
			error += "- " + IConstanti.PRESTAZ_IMPORTO + "\n";
		}
		
		if(error.trim().length() > 0 || !error.equals("")) {

			error = "I seguenti compi sono obbligatori:\n" + error;
			JOptionPane.showMessageDialog(null, error);
			
		} else {
		
			Prestazione prestaz = new Prestazione();

			prestaz.setSezione(sezione);
			prestaz.setDescrizione(descrizione);
			prestaz.setImporto(new Double(importo));
			prestaz.setIva(new Integer(iva));
			prestaz.setQuantita(new Double(quantita));
			prestaz.setId_fattura(this.idFattura);

			Misure selMisura = (Misure) cbMisure.getSelectedItem();
			prestaz.setUnita_misura(selMisura.getId());

			try {
				int intPrestazione = PrestazioniBusiness.getInstance().salvaPrestazione(prestaz);
				if(intPrestazione > 0) {
					JOptionPane.showMessageDialog(null, "Prestazione inserita correttamente!");
					svuotaCampi();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void svuotaCampi() {
		txtSezione.setText("");
		txtDescr.setText("");
		txtQuant.setText("");
		txtIva.setText("");
		txtImporto.setText("");
	}
	
	public Integer getIdFattura() {
		return idFattura;
	}

	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}
}
