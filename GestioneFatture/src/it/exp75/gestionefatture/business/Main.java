package it.exp75.gestionefatture.business;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/*from w  ww. j ava2s  .  c om*/
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Main {
	public Main() {
		Map<Integer, Cliente> map = createMap();
		JComboBox cbox = createComboBox(map);

		JFrame frame = new JFrame();
		frame.add(cbox);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private Map<Integer, Cliente> createMap() {
		Map<Integer, Cliente> map = new HashMap<>();
		Cliente s1 = new Cliente(23, "M");
		Cliente s2 = new Cliente(6, "L");
		Cliente s3 = new Cliente(3, "C");
		Cliente s4 = new Cliente(8, "K");
		Cliente s5 = new Cliente(21, "T");

		map.put(s1.getId(), s1);
		map.put(s2.getId(), s2);
		map.put(s3.getId(), s3);
		map.put(s4.getId(), s4);
		map.put(s5.getId(), s5);

		return map;
	}

	private JComboBox createComboBox(final Map<Integer, Cliente> map) {
		final JComboBox cbox = new JComboBox();
		for (Integer id : map.keySet()) {
			cbox.addItem(id);
		}

		cbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = (Integer) cbox.getSelectedItem();
				System.out.println(map.get(id));
			}
		});

		return cbox;
	}

	public static void main(String[] args) {

		new Main();

	}
}

class Cliente {

	String name;
	Integer id;

	public Cliente(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Name: " + name + " - Stud ID: " + id;
	}
}
