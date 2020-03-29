/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pete
 */
public class UserSiteLoginFrame extends JFrame{
  
  
    UserSiteLoginPanel[] UserSiteLoginPanels;
     JPanel jPanelButtons; 
                JButton jButtonOK;
      //          JButton jButtonCancel;
            JPanel jPanelMain;
   public UserSiteLoginFrame(UserSiteLoginPanel[] in_user_site_login_panels)
  {
     
                jButtonOK = new JButton("Use Login(s)");
         //       jButtonCancel = new JButton("Close");
                jPanelButtons = new JPanel();
             
      UserSiteLoginPanels = in_user_site_login_panels;
    
  }
   public void InitializeFrame()
   {
     jPanelMain = new JPanel();
     jPanelMain.setLayout(new BoxLayout(jPanelMain, BoxLayout.PAGE_AXIS));
       for (UserSiteLoginPanel thisPanel: UserSiteLoginPanels)
       {
  //      thisPanel.CreatePanel();

          jPanelMain.add(thisPanel);
       }
      
                jPanelButtons.add(jButtonOK);
             //      jPanelButtons.add(jButtonCancel);  
                   jPanelButtons.setSize(600,200);
                    jPanelMain.add(jPanelButtons);
                      add(jPanelMain);
        setTitle("Site Logins");
        pack();
      setVisible(true);
      
       
   }
       public void addjButtonOKActionListener(ActionListener listener)
    {
        jButtonOK.addActionListener(listener);
    }
    
}
