package test;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
 
public class MyDialogPanel
{
  private static final Dimension MAIN_SIZE = new Dimension(300, 150);
  private JPanel mainPanel = new JPanel();
  private List<ChangeListener> listenerList = new ArrayList<ChangeListener>();
  private JTextField newTitleField = new JTextField(15);
 
  public MyDialogPanel(String text)
  {
    newTitleField.setText(text);
    newTitleField.getDocument().addDocumentListener(new DocumentListener()
    {
      public void changedUpdate(DocumentEvent e)
      {
        notifyListeners();
      }
 
      public void insertUpdate(DocumentEvent e)
      {
        notifyListeners();
      }
 
      public void removeUpdate(DocumentEvent e)
      {
        notifyListeners();
      }
 
    });
 
    mainPanel.setPreferredSize(MAIN_SIZE);
    mainPanel.add(new JLabel("New Title Text: "));
    mainPanel.add(newTitleField);
  }
 
  public JComponent getMainPanel()
  {
    return mainPanel;
  }
 
  public void addChangeListener(ChangeListener listener)
  {
    listenerList.add(listener);
  }
 
  private void notifyListeners()
  {
    ChangeEvent ce = new ChangeEvent(this);
    for (ChangeListener listener : listenerList)
    {
      listener.stateChanged(ce);
    }
  }
 
  public String getNewTitle()
  {
    return newTitleField.getText();
  }
 
}