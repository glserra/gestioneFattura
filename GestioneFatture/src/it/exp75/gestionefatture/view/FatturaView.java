package it.exp75.gestionefatture.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.exp75.gestionefatture.business.ClientiBusiness;
import it.exp75.gestionefatture.business.FattureBusiness;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.Fattura;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FatturaView extends JFrame {

	private JPanel contentPane;
	private JTable tblPrestazioni;
	private JComboBox<Cliente> cbClienti;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(30, 47, 37, 14);
		contentPane.add(lblCliente);
		
		cbClienti = new JComboBox();
		
		Cliente cl = new Cliente();
		List<Cliente> listaClienti = null;
		
		try {
			listaClienti = ClientiBusiness.getInstance().listaClienti();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Cliente c : listaClienti) {
			cbClienti.addItem(new Cliente(c.getId(),c.getRagioneSociale()));
		}
		
		cbClienti.setBounds(122, 41, 446, 20);
		contentPane.add(cbClienti);
		cbClienti.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JOptionPane.showMessageDialog(null, cbClienti.getSelectedIndex());
			}//((Cliente)cbClienti.getSelectedItem()).getId() +
		});
	}
	
	
	public void selFattura(Integer idFattura) {
		
		Fattura ft = null;
		
		try {
			ft = FattureBusiness.getInstance().fattura(idFattura);
			Cliente cliente = ClientiBusiness.getInstance().cliente(ft.getId_cliente());
			cbClienti.setSelectedItem(cliente.getRagioneSociale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
