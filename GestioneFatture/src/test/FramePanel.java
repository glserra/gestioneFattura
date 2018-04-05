package test;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
/**
 * Creates JPanel for a JFrame Button press launches a JDialog that
 * has ability to change JPanel's label String via observer pattern.
 * Avoids extending JFrame and JDialog or any JComponents that don't need to be
 * extended -- extends by composition, not inheritance.  
 * @author Pete
 */
public class FramePanel
{
  private static final Dimension MAIN_SIZE = new Dimension(600, 450);
  private static final String TITLE_STRING = "Changing Title";
  private JPanel mainPanel = new JPanel(); // main JPanel
  private JDialog dialog; // our dialog
   
  // creates the JPanel that the JDialog holds
  private MyDialogPanel dialogPanel = new MyDialogPanel(TITLE_STRING);
   
  // the JLabel that is changed by the dialog
  private JLabel titleLabel = new JLabel(TITLE_STRING, SwingConstants.CENTER);
 
  public FramePanel(final JFrame frame)
  {
    // add a change listener to the dialog that will grab the 
    // String in the dialog's JTextField when notified via the observer
    // pattern
    dialogPanel.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent arg0)
      {
        changeTitle(dialogPanel.getNewTitle());
      }
    });
    titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 32));
     
    JPanel centerPanel = new JPanel(new GridBagLayout());
    JButton showDialogBtn = new JButton("Show Dialog");
    centerPanel.add(showDialogBtn, new GridBagConstraints());
    showDialogBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (dialog == null)
        {
          createDialog(frame);
        }
        if (!dialog.isVisible())
        {
          dialog.setVisible(true);
        }
      }
    });
     
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(MAIN_SIZE);
    mainPanel.add(titleLabel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
  }
   
  private void createDialog(JFrame frame)
  {
    ModalityType modalityType = ModalityType.MODELESS; // non-modal dialog
    dialog = new JDialog(frame, "Dialog", modalityType);
    dialog.getContentPane().add(dialogPanel.getMainPanel()); // add JPanel to dialog
    dialog.pack();
    dialog.setLocationRelativeTo(null);
  }
 
  public void changeTitle(String title)
  {
    titleLabel.setText(title);
  }
 
  public JComponent getPanel()
  {
    return mainPanel;
  }
 
  // Create JFrame and show it in a thread-safe manner
  private static void createAndShowGUI()
  {
    JFrame frame = new JFrame("ChangingTitleFrame Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new FramePanel(frame).getPanel());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
 
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
  }
}
