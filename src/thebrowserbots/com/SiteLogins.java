/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SiteLogins {
    String[] site_names;
    String[] username_indexes;
    String[] password_indexes;
    JPanel[] sitePanels;
    Integer number_of_panels;
    public SiteLogins(String[] in_site_names, String[] in_username_indexes, String[] in_password_indexes)
    {
    site_names = in_site_names;    
    username_indexes = in_username_indexes;
    password_indexes = in_password_indexes;
    number_of_panels = site_names.length;
    sitePanels = new JPanel[site_names.length];
    
    }
    public void CreateSitePanels()
    {
        int count = 0;
      for (JPanel thisPanel: sitePanels)
      {
          thisPanel = new JPanel();
          JLabel jLabelSiteName = new JLabel(site_names[count]);
          JLabel jLabelSiteUsername = new JLabel("Username:");
          JTextField jTextFieldSiteUsername = new JTextField(40);
          JLabel jLabelSitePassword = new JLabel("Password:");
          JTextField jTextFieldSitePassword = new JTextField(40);
          thisPanel.add(jLabelSiteName);
          thisPanel.add(jLabelSiteUsername);
          thisPanel.add(jTextFieldSiteUsername);
          thisPanel.add(jLabelSitePassword);
          thisPanel.add(jTextFieldSitePassword);
          
          
      }
    }
    
}
