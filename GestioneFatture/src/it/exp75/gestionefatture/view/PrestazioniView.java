package it.exp75.gestionefatture.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PrestazioniView extends JFrame {

	private JPanel contentPane;
	private JTextField txtSezione;
	private JTextField txtDescr;
	private JTextField txtQuant;
	private JTextField txtImporto;
	private JTextField txtIva;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JComboBox cbMisure = new JComboBox();
		cbMisure.setBounds(170, 214, 28, 20);
		contentPane.add(cbMisure);
		
		txtImporto = new JTextField();
		txtImporto.setBounds(338, 214, 86, 20);
		contentPane.add(txtImporto);
		txtImporto.setColumns(10);
		
		txtIva = new JTextField();
		txtIva.setBounds(271, 214, 46, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		
		JButton btnSalva = new JButton("");
		btnSalva.setIcon(new ImageIcon(PrestazioniView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-save-30.png")));
		btnSalva.setBounds(7, 11, 40, 40);
		contentPane.add(btnSalva);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(PrestazioniView.class.getResource("/it/exp75/gestionefatture/resources/images/icons8-cancel-30.png")));
		btnNewButton.setBounds(54, 11, 40, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblSezione = new JLabel("Sezione:");
		lblSezione.setLabelFor(txtSezione);
		lblSezione.setBounds(10, 95, 46, 14);
		contentPane.add(lblSezione);
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setLabelFor(txtDescr);
		lblDescrizione.setBounds(10, 146, 86, 14);
		contentPane.add(lblDescrizione);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E0:");
		lblQuantit.setLabelFor(txtQuant);
		lblQuantit.setBounds(10, 199, 46, 14);
		contentPane.add(lblQuantit);
		
		JLabel lblUm = new JLabel("UM:");
		lblUm.setLabelFor(cbMisure);
		lblUm.setBounds(170, 199, 28, 14);
		contentPane.add(lblUm);
		
		JLabel lblIva = new JLabel("IVA:");
		lblIva.setLabelFor(txtIva);
		lblIva.setBounds(271, 199, 46, 14);
		contentPane.add(lblIva);
		
		JLabel lblImporto = new JLabel("Importo:");
		lblImporto.setLabelFor(txtImporto);
		lblImporto.setBounds(338, 199, 46, 14);
		contentPane.add(lblImporto);
	}
}
