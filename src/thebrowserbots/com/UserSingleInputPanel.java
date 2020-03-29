/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pete
 */
public class UserSingleInputPanel extends JPanel {
    String inputName;
                    
                JLabel jLabelInputValue;
                JTextField jTextFieldInputValue;
              
                JPanel jPanelSetInputValue;
             
       
               
                
               
                String input_value;
              
                String input_index;
             
             
    public UserSingleInputPanel(String in_inputname, String in_InputValue, String in_input_index)
    {
        inputName = in_inputname;
        input_value = in_InputValue;
      
        input_index = in_input_index;
      
    }
  
    public void CreatePanel()
    {
          JLabel jLabelInputValue;
                JTextField jTextFieldInputValue;
              
                JPanel jPanelSetInputValue;
             
          jLabelInputValue = new JLabel(inputName + " Value:");
          jTextFieldInputValue = new JTextField(input_value, 20);
          
          jPanelSetInputValue = new JPanel();
            
          jPanelSetInputValue.add(jLabelInputValue);
          jPanelSetInputValue.add(jTextFieldInputValue);
              
         
                add(jPanelSetInputValue);
              
               setSize(600,100);
                setVisible(true);

    }
    public String GetInputName()
    {
        String ret_val = "";
        ret_val = inputName;
        return ret_val;
    }
    public String GetInputValue()
    {
        String ret_val = "";
        ret_val = jTextFieldInputValue.getText();
        return ret_val;
    }
   
    public String GetInputIndex()
    {
      String ret_val = "";
      ret_val = input_index;
      return ret_val;
      
    }
  
}
