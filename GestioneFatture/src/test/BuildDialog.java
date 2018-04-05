package test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class BuildDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JPanel bPanel;
	private JLabel label1;
	private JTextField input1;
	private JButton button1;

        // an instance variable to the parent class
	private TestClass parent;

	private String num1;

	// constructor BuildDialog() takes an argument that is a reference to the
	// parent class so that the parent's getters and setters can be used
	public BuildDialog( TestClass parent )
	{
		super();
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.parent = parent;
		
		bPanel = new JPanel();
		label1 = new JLabel( "Input A: " );
		input1 = new JTextField( "0", 3 );
		button1 = new JButton( "Add" );

		num1 = "0";

		bPanel.add( label1 );
		bPanel.add( input1 );
		bPanel.add( button1 );

		button1.addActionListener( this );

		add( bPanel );
		setSize( new Dimension( 200, 200 ) );
		setVisible( true );
		
	} // end construction BuildDialog()

	// method actionPerformed() determines the source of the event and if the
	// source was button1, obtains the value from the text field and passes
	// it to the parent using the parent's method setData()
	public void actionPerformed( ActionEvent e ) 
	{
		Object object = e.getSource();

		if( object.equals( button1 ) )
		{
			num1 = input1.getText();
			parent.setData( num1 );

			// i removed the dispose so that multiple demonstrations could be
			// shown. it works the same with it uncommented, but just once
			// dispose();
		}

	} // end method actionPerformed()

	// method getNum1() passes num1 to callers
	public String getNum1()
	{
		return num1;		
	}

} // end class BuildDialog
