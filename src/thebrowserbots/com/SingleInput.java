/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SingleInput {
    String[] input_names;
    String[] single_input_indexes;
    JPanel[] sitePanels;
    Integer number_of_panels;
    public SingleInput(String[] in_input_names, String[] in_single_input_indexes)
    {
    input_names = in_input_names;    
   
    single_input_indexes = in_single_input_indexes;
    number_of_panels = input_names.length;
    sitePanels = new JPanel[input_names.length];
    
    }
    public void CreateSingleInputPanels()
    {
        int count = 0;
      for (JPanel thisPanel: sitePanels)
      {
          thisPanel = new JPanel();
          JLabel jLabelSiteName = new JLabel(input_names[count]);
        
          JLabel jLabelSingleInput = new JLabel("Text to Send:");
          JTextField jTextFieldSingleInput = new JTextField(40);
          thisPanel.add(jLabelSiteName);
          thisPanel.add(jLabelSingleInput);
          thisPanel.add(jTextFieldSingleInput);     
          
      }
    }
    
}
