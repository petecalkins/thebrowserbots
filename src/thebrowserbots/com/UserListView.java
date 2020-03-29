/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;



public class UserListView {
 JLabel jLabelListTitle;
 JLabel jLabelListDescription;
 JLabel jLabelSearchTerms;
 JLabel jLabelAddSearchTerm;
// JLabel jLabelFileName;
 
 JTextField jTextFieldListTitle;
// JTextField jTextFieldFileName;
 JTextArea jTextAreaListDescription;
 JTextField jTextFieldAddSearchTerm;
 JScrollPane jScrollPaneSearchList;
 JTextArea jTextAreaSearchTermList;
 JFrame jFrameListFrame;
 JPanel jPanelTopNav;
 JPanel jPanelMain;
 JPanel jPanelBottom;
 UserListData ulData;
 String userListTitle;
 
 JButton jButtonEdit;
 JButton jButtonNext;
 JButton jButtonPrev;
 JButton jButtonSelectList;
 JButton jButtonNew;
 JButton jButtonDelete;
 
 JLabel jLabelListIndex;
  JPanel jPanelTopControls;
  JPanel jPanelMiddle;
 
  String displayFilename;
  Integer currentIndex;
 UserListView (UserListData in_uData)
 {
  ulData = in_uData;
 displayFilename = "";
 currentIndex = ulData.ReturnCurrentIndex(ulData.csvFile);
 
 }
 public String GetUserListTitle()
 {
     String ret_val = "";
      ret_val = userListTitle;
     return ret_val;
 }
 public String GetUserListName(int in_index)
 {
     String ret_val = "";
    ret_val = displayFilename;
     return ret_val;
     
 }
 public void setListTitle(String in_title)
 {
     jTextFieldListTitle.setText(in_title);
 }
 public void setListDescription (String in_description)
 {
     jTextAreaListDescription.setText(in_description);    
 }
// public void setjTextFieldFileName(String in_filename)
// {
//   
//    displayFilename = in_filename.substring(0, in_filename.length()-4);
//   
//     jTextFieldFileName.setText(displayFilename);
//     
// }
 
// public void setSearchTermList(ArrayList<String> in_search_terms)
// {
//     String full_list = "";
//     for (String thisTerm: in_search_terms)
//     {
//     full_list+=thisTerm + System.lineSeparator();
//     }
//     jTextAreaSearchTermList.setText(full_list);
//     
// }
// public void setAddSearchTermText(String in_term)
// {
//     jTextFieldAddSearchTerm.setText(in_term);   
// }
// 
 public String getListTitle ()
 {
     String ret_val = "";
     ret_val = jTextFieldListTitle.getText();
     return ret_val;
 }
 public String getListDescription()
 {
     String ret_val = "";
     ret_val = jTextAreaListDescription.getText();
     return ret_val;
     
 }
 public String getSearchTermList()
 {
     String ret_val = "";
     ret_val = jTextAreaSearchTermList.getText();
     return ret_val;
 }
 public String getAddSearchTermText()
 {
     String ret_val = "";
     ret_val = jTextFieldAddSearchTerm.getText();
     return ret_val;
     
 }

 public void setJTextAreaSearchTermList( List<String[]> in_searchterms)
 {
     String all_search_terms ="";
     int count = 0;
     for (String[] thisColumn: in_searchterms)
     {
           if (count==0)
         {
            userListTitle = thisColumn[0];
         }
           else   
    
         {
         all_search_terms+= thisColumn[0] + System.lineSeparator();
         }
    
       
         count++;
     }
    
     jTextAreaSearchTermList.setText(all_search_terms);
     jTextAreaSearchTermList.setEnabled(false);
 }
   public void AddjButtonDeleteActionListener(ActionListener listener)
 {
     jButtonDelete.addActionListener(listener);
 }
  public void AddjButtonNewActionListener(ActionListener listener)
 {
     jButtonNew.addActionListener(listener);
 }
 public void AddjButtonEditActionListener(ActionListener listener)
 {
     jButtonEdit.addActionListener(listener);
 }
 public void AddjButtonSelectListActionListener(ActionListener listener)
 {
     jButtonSelectList.addActionListener(listener);
 }
 public void AddjButtonNextActionListener(ActionListener listener)
 {
     jButtonNext.addActionListener(listener);
 }
 public void AddjButtonPrevActionListener (ActionListener listener)
 {
     jButtonPrev.addActionListener(listener);
 }
 public void Next()
 {
         currentIndex++;
         
     if (currentIndex>ulData.userListFiles.size()-1)
     {
       currentIndex = 0;  
       DefaultListMode();
     }
     else
     {
       BrowseMode();   
     }
 
  
    UpdateDisplay();
 }
 public void Previous()
 {
    currentIndex--;
    if (currentIndex<0)
    {
        currentIndex = ulData.userListFiles.size()-1;
       BrowseMode();
    }
    else
    {
        if (currentIndex==0)
        {
            DefaultListMode();
        }
        else
        {
        BrowseMode();
        }
    }
 
    UpdateDisplay();
 }
 public void SetCurrentIndex(int in_index)
 {
     currentIndex = in_index;
     if (currentIndex<1)
     {
         DefaultListMode();
     }
     else
     {
         BrowseMode();
     }
    
     UpdateDisplay();
 }
 public void UpdateDisplay()
 {
     if (currentIndex>=ulData.userListFiles.size())
     {
        SaveLocalList();
        
     }
   ulData.getsetUserListFiles();

   jLabelListIndex.setText(Integer.toString(currentIndex));
         File currentFile =  ulData.userListFiles.get(currentIndex);
  ulData.setSearchTermsToFile(currentFile.getAbsolutePath());
    setJTextAreaSearchTermList(ulData.getSearchTerms());
 // setjTextFieldFileName(ulData.getFileName());
    setListTitle(userListTitle);
    ulData.setListName(SelectList());
 
 }
 public String SelectList()
 {
     File ret_file = ulData.csvFile;
    if (currentIndex>0)
    {
        ret_file = ulData.userListFiles.get(currentIndex);
    }
    return ret_file.getAbsolutePath();
   
    
 }
 public void EnableSearchTermList(boolean enableit)
 {
     jTextAreaSearchTermList.setEnabled(enableit);
     jTextFieldListTitle.setEnabled(enableit);
     if (enableit)
     {
         jButtonNext.setVisible(false);
         jButtonPrev.setVisible(false);
         jButtonNew.setVisible(false);
         jButtonDelete.setVisible(true);
     }
     else
     {
         jButtonNext.setVisible(true);
         jButtonPrev.setVisible(true);
         jButtonNew.setVisible(true);
         jButtonDelete.setVisible(true);
     }
 }
 public void SaveLocalList()
 {
   //   jButtonSelectList.setText("Saving");
          File userlist_directory = new File(ulData.USERLISTDIR);
        if (userlist_directory.exists()) {
        }
        else
        {
             if (userlist_directory.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
     
     try
     {
    FileWriter writer = new FileWriter(ulData.USERLISTFILENAME + "_" + currentIndex.toString() + ".csv");
     int lines = jTextAreaSearchTermList.getLineCount();
     String thisTitle = "";
     thisTitle = jTextFieldListTitle.getText();
     if (thisTitle.equals("")) { thisTitle = "untitled";}
   writer.write(thisTitle + System.getProperty("line.separator"));
        try{// Traverse the text in the JTextArea line by line
            for(int i = 0; i < lines; i ++){
                int start = jTextAreaSearchTermList.getLineStartOffset(i);
                int end = jTextAreaSearchTermList.getLineEndOffset(i);
                // Implement method processLine
              
                writer.write(jTextAreaSearchTermList.getText(start, end-start));

            }
            writer.close();
        }catch(Exception e){
         System.out.println("Exception when writing lines" + e.toString());
        }
     }
     catch (Exception ex)
     {
       System.out.println("Exception when creating list file:" + ex.toString());  
     }
   ulData.getsetUserListFiles();
   
//   BrowseMode();
       
 }
 public void Delete()
 {
     ulData.Delete(currentIndex);
    if (currentIndex > ulData.userListFiles.size()-1) { currentIndex--; } 
   
    if (currentIndex==0)
    {
     DefaultListMode();
    }
    else
    {
        BrowseMode();
    }
     UpdateDisplay();
 }
 public void BrowseMode()
 {
      EnableSearchTermList(false);
      
      jButtonDelete.setVisible(true);
     jButtonEdit.setVisible(true);
     jButtonEdit.setText("Edit");
     jButtonEdit.setActionCommand("Edit");
    jButtonSelectList.setText("OK");
   
    jButtonSelectList.setActionCommand("Select");
   //  jTextFieldFileName.setText( displayFilename ); 
  setListTitle(userListTitle);
   
 }
 public void EditAsNewMode()
 {
     currentIndex = ulData.userListFiles.size();
        UpdateDisplay();
     EnableSearchTermList(true);
     jButtonEdit.setVisible(false);
    jButtonSelectList.setText("Save");
   
    jButtonSelectList.setActionCommand("Save");
  //   jTextFieldFileName.setText( displayFilename );
   
  

     
 }
 
 public void EditMode()
 {
     EnableSearchTermList(true);
     jButtonEdit.setVisible(false);
    jButtonSelectList.setText("Save");
   
    jButtonSelectList.setActionCommand("Save");
  //   jTextFieldFileName.setText( "");
  // jTextFieldListTitle.setText("");
     
 }
 public void New()
 {
    
  //   jTextFieldFileName.setText( displayFilename );
   
     currentIndex = ulData.userListFiles.size();
  UpdateDisplay();
    EnableSearchTermList(true);
      jTextAreaSearchTermList.setText("");
      jTextFieldListTitle.setText("");
     jButtonEdit.setVisible(false);
    jButtonSelectList.setText("Save");
   
    jButtonSelectList.setActionCommand("Save");
 }
 public void DisplayNewIndex(int in_index)
 {
        String  indexString = Integer.toString(in_index);
     jLabelListIndex.setText(indexString);
 //    currentIndex = in_index;
    
 }
// public void UpdateDisplay(int in_index)
// {
//   if (in_index>ulData.userListFiles.size())
//   {
//       NewMode();
//   }
// }
 public void DefaultListMode()
 {
      jButtonEdit.setVisible(true);
      jButtonEdit.setText("Edit As New");
      jButtonDelete.setVisible(false);
      jButtonEdit.setActionCommand("Edit As New");
       jButtonSelectList.setText("OK");
   
    jButtonSelectList.setActionCommand("Select");
      UpdateDisplay();
 }
 public void initComponents()
 {
      userListTitle = "";
 // jLabelFileName = new JLabel("List filename:");
 // jTextFieldFileName = new JTextField(20);
 // jTextAreaListDescription = new JTextArea(5, 14);
  jFrameListFrame = new JFrame("Search Term List");
  jPanelTopNav = new JPanel();
  jLabelListTitle = new JLabel(ulData.getListName());
  jTextFieldListTitle = new JTextField(20);
//  jLabelListDescription = new JLabel("List Description:");
 // jTextAreaListDescription = new JTextArea(14,20);
 // jLabelAddSearchTerm = new JLabel("Add Search Terms");
  jLabelListTitle = new JLabel("List Title");
  jTextFieldAddSearchTerm = new JTextField(20);
  jTextAreaSearchTermList = new JTextArea(30, 20);
  setJTextAreaSearchTermList(ulData.getSearchTerms());
  jTextFieldListTitle.setText(userListTitle);
  
 // setjTextFieldFileName(ulData.getFileName());
  JPanel jPanelFileName = new JPanel();
  JPanel jPanelTitle = new JPanel();
 // JPanel jPanelDescription = new JPanel();
  
 // jPanelFileName.add(jLabelFileName);
 // jPanelFileName.add(jTextFieldFileName);
  
 // jPanelDescription.add(jLabelListDescription);
 //  jPanelDescription.add(this.jTextAreaListDescription);
  
  jPanelTitle.add(jLabelListTitle);
  jPanelTitle.add(jTextFieldListTitle);
  
  jButtonEdit = new JButton("Edit");
  jButtonNext = new JButton(">");
  jButtonPrev = new JButton("<");
  jButtonNew = new JButton("New");
  jButtonDelete = new JButton("Delete");
  jButtonSelectList = new JButton("OK");
  jButtonSelectList.setActionCommand("Select");

  jLabelListIndex = new JLabel("0");
 
  jPanelTopControls = new JPanel();
  jPanelTopControls.add(jButtonPrev);
  jPanelTopControls.add(jLabelListIndex);
  jPanelTopControls.add(jButtonNext);
  jPanelTopControls.add(jButtonNew);
  
  jPanelTopNav.setLayout(new BoxLayout(jPanelTopNav,BoxLayout.PAGE_AXIS ));
  jPanelTopNav.add(jPanelTopControls);
  jPanelTopNav.add(jPanelFileName);
//  jPanelTopNav.add(jPanelDescription);
  jPanelTopNav.add(jPanelTitle);

  
  
//  jPanelTopNav.add(jLabelListDescription);
//  jPanelTopNav.add(jTextAreaListDescription);
  
 // jPanelTopNav.add(jTextAreaListDescription); 
//  jPanelTopNav.add(jLabelAddSearchTerm);
 // jPanelTopNav.add(jTextFieldAddSearchTerm);
 // String[] columnName = new String[1];
 // columnName[0] = "Search Terms";
 // DefaultTableModel tableModel = new DefaultTableModel(columnName, ulData.a_listSearchTerms.size()); 

//  jTableUserList = new JTable(tableModel);
  jScrollPaneSearchList = new JScrollPane(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
  jScrollPaneSearchList.setViewportView(jTextAreaSearchTermList);

jPanelMain = new JPanel();
  
//  jPanelMain.add(jTextAreaSearchTermList);
  jPanelMiddle = new JPanel();
  jPanelBottom = new JPanel();
  jPanelBottom.add(jButtonEdit);
  jPanelBottom.add(jButtonSelectList);
  jPanelBottom.add(jButtonDelete);
  
   jPanelMiddle.add(jScrollPaneSearchList);
   jPanelMain.add(jPanelTopNav);
   jPanelMain.add(jPanelMiddle);
   jPanelMain.add(jPanelBottom);
 // jFrameListFrame.add(jPanelTopNav);
  jFrameListFrame.add(jPanelMain);
  jFrameListFrame.setLocation(0,0);
  jFrameListFrame.setSize(400,800);
  jFrameListFrame.setVisible(true);
  if (currentIndex>0)
  {
     jLabelListIndex.setText(currentIndex.toString());
      BrowseMode();
  }
  else
  {
      DefaultListMode();
  }
  
 }
}
