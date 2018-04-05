package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TestClass
{
	JTextField textField;
	JFrame mainFrame;
	TestClass parent;
	
	public TestClass()
	{
		// a bit awkward looking, but this allows passing a reference to the
		// TestClass object to the JDialog constructed inside the anonymous
		// inner class that is the button's action listener
		this.parent = this;
		mainFrame = new JFrame( "Main Frame" );
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainFrame.setLocationRelativeTo( null );
		
		textField = new JTextField( 10 );
		textField.setText( "Data from JDialog" );
		JButton button = new JButton( "Get Data" );

                // an alternative way (preferred by some) to add an
                // action listener to a component. using the anonymous
                // inner class creates new complications, all manageable
		button.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e)
			{
				new BuildDialog( parent );
			}
		} );

		mainFrame.setLayout( new FlowLayout() );
		mainFrame.add( textField );
		mainFrame.add( button );
		
		mainFrame.pack();
		mainFrame.setVisible( true );
	}
	
	public void setData( String data )
	{
		textField.setText( data );
	}

	public static void main( String[] args )
	{
		// begins the swing ap on the Event Dispatch Thread (EDT)
		// i promise all of my Swing examples will be started this way
		SwingUtilities.invokeLater( new Runnable()
		{
			public void run()
			{
				new TestClass();
			}
		} );
		
	} // end method main()
	
} // end class TestClass