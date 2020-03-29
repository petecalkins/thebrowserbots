package thebrowserbots.com;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Prompter extends JFrame implements ActionListener 
{
    JButton ContinueButton;
    JButton CancelButton;
    JLabel JLabelJumpTo;
    JComboBox JComboBoxJumpToValue;
    Boolean cancelled;
    Boolean hasCancelButton;
    JScrollPane mainScrollPane;
    
  int JumpToRecord = -1;
    int number_of_records = 0;
     Prompter ( String filename, String messagetodisplay, Boolean in_hascancel, int currentrecord, int number_of_records)
            {
                mainScrollPane = new JScrollPane();
           this.JLabelJumpTo = new JLabel("Skip to #: ");
           
          
           if (number_of_records>0)
           {
               this.JComboBoxJumpToValue = new JComboBox();
           for (int x = 0; x<number_of_records; x++)
           {
            JComboBoxJumpToValue.addItem(x+1);
           }
           JComboBoxJumpToValue.setSelectedItem(currentrecord+1);
           }
           this.number_of_records = number_of_records;
           this.cancelled = false;    
           this.hasCancelButton = in_hascancel;
                ContinueButton = new JButton("OK, I'll be patient.");
                CancelButton = new JButton("Go back to sleep, bot"); 
             
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //        FlowLayout flo = new FlowLayout();
        //        setLayout(flo);
           
             
                 JTextArea messageText = new JTextArea(14, 20);
    messageText.setText(messagetodisplay);
    messageText.setWrapStyleWord(true);
    messageText.setLineWrap(true);
    messageText.setOpaque(false);
    messageText.setEditable(false);
    messageText.setFocusable(false);
    messageText.setBackground(UIManager.getColor("Label.background"));
    messageText.setFont(UIManager.getFont("Label.font"));
    messageText.setBorder(UIManager.getBorder("Label.border"));
                JPanel messagepanel = new JPanel();
                mainScrollPane.setViewportView(messageText);
             //   mainScrollPane.add(messageText);
                messagepanel.add(mainScrollPane);
             
                JPanel buttonpanel = new JPanel();
           //  buttonpanel.setLayout(new FlowLayout());
             //   buttonpanel.add(ContinueButton);
                if (this.hasCancelButton)
                {
               buttonpanel.add(CancelButton);
                }
                else
                {
                    buttonpanel.add(ContinueButton);
                }
                if (this.number_of_records>0)
                {
                    buttonpanel.add(JLabelJumpTo);
                    buttonpanel.add(JComboBoxJumpToValue);
                }
                JPanel WholePrompt = new JPanel();
                WholePrompt.setLayout(new BorderLayout());
                WholePrompt.add(messagepanel, BorderLayout.CENTER);
                WholePrompt.add(buttonpanel, BorderLayout.SOUTH);
                
               
         setSize(400, 200);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - this.getWidth();
       setResizable(false);
        this.setLocation(x, 0);
                this.setTitle(filename);
                add(WholePrompt);
             
                
                setVisible(true);
                ContinueButton.addActionListener(this);
              
             pack();
            }
     
 @Override
     public void actionPerformed(ActionEvent event)
     {
        Object source = event.getSource();
       if (source == ContinueButton)
       {
        ClickedContinue();
        }
   
     }
     public void ClickedContinue()
     {
          if (this.number_of_records>0)
           {
           this.JumpToRecord = JComboBoxJumpToValue.getSelectedIndex();
           }
          this.setVisible(false);
          this.dispose();
     }
      public void addJumpToItemListener(ItemListener listener) {
       JComboBoxJumpToValue.addItemListener(listener);
     
   
      } 
          public void addjButtonContinueActionListener(ActionListener listener)
    {
        ContinueButton.addActionListener(listener);
    }
       public void addCancelButtonActionListener(ActionListener listener) {
       CancelButton.addActionListener(listener);
   }
}
