/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author pcalkins
 */
public class PauseActionView extends ActionView {
  PauseActionView()
   {
 
    
    this.JLabelVariable1 = new JLabel("Minutes to Pause:");
    this.JLabelVariable2 = new JLabel("Seconds to Pause:");
  
    this.JPanelAction.add(this.JLabelVariable1);
      this.JPanelAction.add(this.JTextFieldVariable1); 
      this.JPanelAction.add(this.JLabelVariable2);
      this.JPanelAction.add(this.JTextFieldVariable2);
       this.JPanelAction.add(this.JButtonOK);
  this.JPanelAction.add(this.JButtonDelete);
  theseActionSettings.add(new ActionSettings(JLabelVariable1, 2, 1, 0.0, GridBagConstraints.WEST));
    theseActionSettings.add(new ActionSettings(JTextFieldVariable1, 3, 2, 0.5, GridBagConstraints.WEST));
      theseActionSettings.add(new ActionSettings(JLabelVariable2, 5, 1, 0.0, GridBagConstraints.WEST));
        theseActionSettings.add(new ActionSettings(JTextFieldVariable2, 6, 2, 0.5, GridBagConstraints.WEST));
  theseActionSettings.add(new ActionSettings(JButtonOK, 8, 1, 0.0, GridBagConstraints.WEST));
   theseActionSettings.add(new ActionSettings(JButtonDelete, 9, 1, 0.0, GridBagConstraints.WEST));
  
   }
 @Override  
public void AddListeners(BMAction action, SeleniumTestTool STAppFrame, SeleniumTestToolData STAppData, Procedure newbug, ProcedureView newbugview)
   {
 AddDraggers(action, STAppFrame, STAppData, newbug, newbugview);

                        addJButtonDeleteActionActionListener((ActionEvent evt) -> {
                              STAppFrame.saveState();
                            STAppFrame.DeleteActionView(newbugview, action.index);
                          STAppData.DeleteAction(newbug, action.index);
// why was this here?                      
//        STAppFrame.updateStoredURLListIndexes(newbugview);
                    
   });
   


     addJTextFieldVariable1DocListener(
             new DocumentListener()
           {
@Override
       public void changedUpdate(DocumentEvent documentEvent) {
 STAppFrame.saveState();
           action.setVariable1(JTextFieldVariable1.getText());
      
      }
@Override
      public void insertUpdate(DocumentEvent documentEvent) {
      action.setVariable1(JTextFieldVariable1.getText());
      }
@Override
      public void removeUpdate(DocumentEvent documentEvent) {
 STAppFrame.saveState();
          action.setVariable1(JTextFieldVariable1.getText());
      }
      }
                 );

     addJButtonOKActionActionListener((ActionEvent evt) -> {
             STAppFrame.saveState();
         String ACommand = evt.getActionCommand();
         
         if (ACommand.equals("Update"))
         {
             
             UpdateActionView();
             action.Locked= true;
             
         }
         if (ACommand.equals("Edit"))
         {
             EditActionView();
             action.Locked= false;
             
         } });        
   addJTextFieldVariable2DocListener(
             new DocumentListener()
           {

       public void changedUpdate(DocumentEvent documentEvent) {
      action.setVariable2(JTextFieldVariable2.getText());
      }

      public void insertUpdate(DocumentEvent documentEvent) {
      action.setVariable2(JTextFieldVariable2.getText());
      }
      public void removeUpdate(DocumentEvent documentEvent) {
     action.setVariable2(JTextFieldVariable2.getText());
      }
      }
                 );
   } 
 
        
}
