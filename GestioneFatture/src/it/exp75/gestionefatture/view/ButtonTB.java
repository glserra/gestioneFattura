package it.exp75.gestionefatture.view;

import javax.swing.JButton;
import javax.swing.border.Border;

public class ButtonTB extends JButton{

	public ButtonTB(String txt) {
		setText(txt);
		setBorderPainted(false);
	}
	public ButtonTB() {
		setBorderPainted(false);
	}


}
