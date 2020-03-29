/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Pete
 */
public class UserSiteLoginPanel extends JPanel {
    String siteName;
                    
                JLabel jLabelSiteUsername;
                JTextField jTextFieldSiteUsername;
                JLabel jLabelSitePassword;
                JPasswordField jPasswordFieldSitePassword;
                JPanel jPanelSetUsername;
             
           
                JPanel jPanelSetPassword;
       
               
                
               
                String username;
                String password;
                String username_index;
                String password_index;
             
    public UserSiteLoginPanel(String in_sitename, String in_username, String in_username_index, String in_password, String in_password_index)
    {
        siteName = in_sitename;
        username = in_username;
        password = in_password;
        username_index = in_username_index;
        password_index = in_password_index;
    }
  
    public void CreatePanel()
    {
          jLabelSiteUsername = new JLabel(siteName + " Username:");
          jTextFieldSiteUsername = new JTextField(username, 20);
          jLabelSitePassword = new JLabel(siteName + " Password:");
          jPasswordFieldSitePassword = new JPasswordField(password, 20);
          jPanelSetUsername = new JPanel();
            
          jPanelSetUsername.add(jLabelSiteUsername);
          jPanelSetUsername.add(jTextFieldSiteUsername);
              
           jPanelSetPassword = new JPanel();
           jPanelSetPassword.add(jLabelSitePassword);
           jPanelSetPassword.add(jPasswordFieldSitePassword);
       
                add(jPanelSetUsername);
                add(jPanelSetPassword);
               setSize(600,200);
                setVisible(true);

    }
    public String GetSiteName()
    {
        String ret_val = "";
        ret_val = siteName;
        return ret_val;
    }
    public String GetSiteUsername()
    {
        String ret_val = "";
        ret_val = jTextFieldSiteUsername.getText();
        return ret_val;
    }
    public String GetSitePassword()
    {
        String ret_val = "";
        char[] keys = jPasswordFieldSitePassword.getPassword();
           for (int x = 0; x<keys.length; x++)
     {
         ret_val = ret_val + keys[x];
     }
      
        return ret_val;
        
    }
    public String getUsernameIndex()
    {
      String ret_val = "";
      ret_val = username_index;
      return ret_val;
      
    }
    public String getPasswordIndex()
    {
        String ret_val = "";
        ret_val = password_index;
        return ret_val;
        
    }
}
