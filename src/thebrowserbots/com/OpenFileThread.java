/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pcalkins
 */
public class OpenFileThread extends SwingWorker<String, Integer>{
File file;

SeleniumTestToolData STAppData;
ArrayList<SeleniumTestToolData> MDIDataClasses;
MainAppFrame mainAppFrame;
STAppController mainAppController;
int calling_MDI_Index;
boolean isFlatten;
boolean RunIt;
boolean fromCloud=false;
boolean hasGUI = true;
List<String[]> theseMapEntries;
boolean hasMap = false;
public OpenFileThread(STAppController in_mainAppController, File in_file, ArrayList<SeleniumTestToolData> in_MDIDataClasses,List<String[]> mapEntries )
{
  hasGUI = false;
   mainAppController = in_mainAppController;
   MDIDataClasses = in_MDIDataClasses;
   RunIt = true;
   file = in_file;
   hasMap = true;
  theseMapEntries = mapEntries;
  
}

public OpenFileThread(STAppController in_mainAppController, File in_file, ArrayList<SeleniumTestToolData> in_MDIDataClasses)
{
   hasGUI = false;
   mainAppController = in_mainAppController;
   MDIDataClasses = in_MDIDataClasses;
   RunIt = true;
   file = in_file;
 
}
 
    public OpenFileThread(STAppController in_mainAppController, File file, ArrayList<SeleniumTestToolData> MDIDataClasses, int calling_MDI_Index, boolean isFlatten, boolean RunIt)
{
  this.isFlatten = isFlatten;
 
  this.mainAppController = in_mainAppController;
  this.file = file;
  this.MDIDataClasses = MDIDataClasses;
  this.calling_MDI_Index = calling_MDI_Index;
  this.RunIt = RunIt;
}
   public OpenFileThread(STAppController in_mainAppController, File file, ArrayList<SeleniumTestToolData> MDIDataClasses, int calling_MDI_Index, boolean isFlatten, boolean RunIt, boolean fromCloud, List<String[]> mapEntries)
{
    this.fromCloud = fromCloud;
  this.isFlatten = isFlatten;

   this.mainAppController = in_mainAppController;
  this.file = file;

  this.MDIDataClasses = MDIDataClasses;
  this.calling_MDI_Index = calling_MDI_Index;
  this.RunIt = RunIt;
    hasMap = true;
  theseMapEntries = mapEntries;
}
      public OpenFileThread(STAppController in_mainAppController, File file, ArrayList<SeleniumTestToolData> MDIDataClasses, boolean isFlatten, boolean RunIt, boolean fromCloud)
{
    this.fromCloud = fromCloud;
  this.isFlatten = isFlatten;
   this.mainAppController = in_mainAppController;
  this.file = file;
 
  this.MDIDataClasses = MDIDataClasses;
   this.RunIt = RunIt;
}
@Override 
public String doInBackground()
 {

  
    try {
        int MDI_CLASS_INDEX;
           MDI_CLASS_INDEX = OpenFile();
       
       }
       catch (IOException | ClassNotFoundException ex) {
          System.out.println("error opening file: " + ex.toString());
       } 
    
 return "done";
 }
@Override
 protected void done()
 {
  
   
   int last_index = mainAppController.MDIDataClasses.size()-1;
   if (last_index<0)
   {
       return;
   }
   else
   {
       
 
       if (RunIt)
  {
      if (hasGUI)
      {
      if (hasMap)
    {
  
    if (last_index>-1) {  
        mainAppController.ApplyMap(theseMapEntries,  MDIDataClasses.get(last_index));
    }
    }
 
    if (last_index>-1) {    mainAppController.RunActions( MDIDataClasses.get(last_index));
    }
      }
    else
    {
         if (last_index>-1)
  {
            if (hasMap)
    {
        mainAppController.ApplyMap(theseMapEntries, MDIDataClasses.get(last_index));
    }
        mainAppController.RunActions(MDIDataClasses.get(last_index));
    } 
    }
  } 
 }
     if (mainAppController.deletemap)
     {
         
       File deleteFile = new File(mainAppController.mapPath);
       if (deleteFile.exists())
       {
  //      Prompter thisprompt = new Prompter(deleteFile.getAbsolutePath(), deleteFile.getAbsolutePath(), true, 0, 0);
       deleteFile.delete();
       }
       else
       {
  //           Prompter thisprompt = new Prompter(deleteFile.getAbsolutePath(), deleteFile.getAbsolutePath(), true, 0, 0);   
       }
       }
 }
 @Override
 protected void process ( List <Integer> bugindex)
 {
     
 }
    public int OpenFile () throws FileNotFoundException, IOException, ClassNotFoundException
    {
   
  
          String full_filename="";
if(!file.exists()) { 
 return -10;
 
}
else
{
  
    if (this.fromCloud)
    {
       
        }
    else
    {
 full_filename = file.getAbsoluteFile().toString();
    }

int MDI_Index = -1;


              Boolean PromptForSameFileName = false;
    
     int alreadyopen_index = -1;
     int thisfile_index = 0;
  
     if (PromptForSameFileName==false)
    {


Document doc=null;
try
{

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
DocumentBuilder builder = factory.newDocumentBuilder();
String file_path = file.getAbsolutePath();

doc = builder.parse(file_path);

 
}
catch (ParserConfigurationException | SAXException | IOException e)
{
    System.out.println("DocumentBuilder error:" + e.toString());
   
}
    
finally 
{
 
   BuildNewWindow(doc, full_filename);
   STAppData.setFilenames(full_filename);
 

  if (this.fromCloud)
  {
      
  }
  else
  {
    
  }
  


   MDIDataClasses.add(STAppData);
   MDI_Index =  MDIDataClasses.size()-1;

}

 return MDI_Index;
    }
   else
     {
      
//  mainApp.DisplayWindow(alreadyopen_index);
 //   JOptionPane.showMessageDialog (null, filealreadyopen + " is already open", "File is open", JOptionPane.INFORMATION_MESSAGE);
                               
 
 return -1;
     }
}

    }
  
  public void BuildNewWindow(Document doc, String full_filename)
  {
   ArrayList<Procedure> newBugArray = new ArrayList<>();
  // ArrayList<ProcedureView> newBugViewArray = new ArrayList<>();
   NamedNodeMap NewAttributes = doc.getElementsByTagName("BrowserMatorWindow").item(0).getAttributes(); 
     STAppData = new SeleniumTestToolData(newBugArray);
     
    
   String filename_read = NewAttributes.getNamedItem("Filename").getNodeValue();

   



  STAppData.setFilenames(full_filename);
 
  NodeList FileSettingsNode = doc.getElementsByTagName("FileSettings");
 
  String thisSettingsNodeName;
  String thisSettingsNodeValue;
  String stShowReport="false";
  String stEmailReport = "false";
  String stEmailReportFail = "false";
  String stIncludeScreenshots = "false";
  String stExitAfter = "false";
  String SMTPHostname = "";
  String EmailLoginName = "";
  String stPromptToClose = "false";
  String TargetBrowser = "Firefox";
  String PromptBehavior = "Dismiss";
  String WaitForLoad = "Yes";
  String WaitTime = "3";
  String Sessions = "1";
  String OSType = "Windows32";
  String EmailPassword = "";
  String unepassword = "";
  String EmailTo = "";
  String EmailFrom = "";
  String EmailSubject = "";
   String stUniqueList = "false";
  String EcTimeout = "";
 if (FileSettingsNode!=null)
 {
try
{
    NodeList SettingsNodes = FileSettingsNode.item(0).getChildNodes();


    for (int k = 0; k<SettingsNodes.getLength(); k++)
    {
   thisSettingsNodeName = SettingsNodes.item(k).getNodeName();
   thisSettingsNodeValue = SettingsNodes.item(k).getTextContent();
    switch(thisSettingsNodeName)
    {
        case "ShowReport":
            stShowReport = thisSettingsNodeValue;
           Boolean ShowReport = false;
   if (stShowReport.equals("true"))
   {
       ShowReport = true;
   }
 
   STAppData.setShowReport(ShowReport);
            break;
            
        case "IncludeScreenshots":
           stIncludeScreenshots = thisSettingsNodeValue;
   Boolean IncludeScreenshots = false;  
    if (stIncludeScreenshots.equals("true"))
   {
       IncludeScreenshots = true;
   }
    
       STAppData.setIncludeScreenshots(IncludeScreenshots);
            break;
            
                
        case "EmailReport":
   stEmailReport = thisSettingsNodeValue;
   Boolean EmailReport = false;
    if (stEmailReport.equals("true"))
   {
       EmailReport = true;
   }
   
       STAppData.setEmailReport(EmailReport);
            break;
      
        case "EmailReportFail":
   stEmailReportFail = thisSettingsNodeValue;
   Boolean EmailReportFail = false;
    if (stEmailReportFail.equals("true"))
   {
       EmailReportFail = true;
   }
  
       STAppData.setEmailReportFail(EmailReportFail);
            break;
         
        case "ExitAfter":
   stExitAfter = thisSettingsNodeValue;
   Boolean ExitAfter = false;
    if (stExitAfter.equals("true"))
   {
       ExitAfter = true;
   }
       STAppData.setExitAfter(ExitAfter);
  
            break;        
       
        case "SMTPHostname":
 SMTPHostname = thisSettingsNodeValue;
    
       STAppData.setSMTPHostname(SMTPHostname);
            break;  

        case "EmailLoginName":
 EmailLoginName = thisSettingsNodeValue;
    
      STAppData.setEmailLoginName(EmailLoginName);
            break;  
        
        case "PromptToClose":
 stPromptToClose = thisSettingsNodeValue;
   Boolean PromptToClose = false;
    if (stPromptToClose.equals("true"))
   {
       PromptToClose = true;
   }
    
         STAppData.setPromptToClose(PromptToClose);
            break; 
      case "UniqueList":
 stUniqueList = thisSettingsNodeValue;
   Boolean UniqueList = false;
    if (stUniqueList.equals("true"))
   {
      UniqueList = true;
   }
    
        STAppData.setUniqueList(UniqueList);
     
            break; 
         case "UniqueFileOption":
  
        STAppData.setUniqueFileOption(thisSettingsNodeValue);
     
            break;          
            
          
        case "TargetBrowser":
 TargetBrowser = thisSettingsNodeValue;
    
      STAppData.setTargetBrowser(TargetBrowser);
   
            break; 
       case "PromptBehavior":
     PromptBehavior = thisSettingsNodeValue;   
      STAppData.setPromptBehavior(PromptBehavior);
                    break; 
       case "WaitForLoad":
    WaitForLoad = thisSettingsNodeValue;   
      STAppData.setWaitForLoad(WaitForLoad);
            break; 
            
           case "EcTimeout":
 EcTimeout = thisSettingsNodeValue;
 int intEcTimeout = Integer.parseInt(EcTimeout);
    
      STAppData.setEcTimeout(intEcTimeout);
            break;    
            
       case "WaitTime":
 WaitTime = thisSettingsNodeValue;
 int intWaitTime = Integer.parseInt(WaitTime);
    
      STAppData.setWaitTime(intWaitTime);
            break;  
  
            
        case "Sessions":
 Sessions = thisSettingsNodeValue;
 int intSessions = Integer.parseInt(Sessions);
  
      STAppData.setSessions(intSessions);
            break;               
       case "OSType":
 OSType = thisSettingsNodeValue;
     
       STAppData.setOSType(OSType);
    
            break;   
     
       case "EmailPassword":
 EmailPassword = thisSettingsNodeValue;
  try
   {
          String machineID="Peanuts";
  //     machineID = this.mainAppController.appConfig.ReturnMachineSerialNumber();
   unepassword = Protector.decryptLocal(EmailPassword, machineID);
   }
   catch (GeneralSecurityException | IOException e)
           {
   //            System.out.println("decrypt error" + e.toString());
           }
  
      STAppData.setEmailPassword(unepassword);
            break;  
      
       case "EmailTo":
 EmailTo = thisSettingsNodeValue;
    
       STAppData.setEmailTo(EmailTo);
            break;    
      
       case "EmailFrom":
 EmailFrom = thisSettingsNodeValue;
   
      STAppData.setEmailFrom(EmailFrom);
            break;   
       
       case "EmailSubject":
 EmailSubject = thisSettingsNodeValue;
     
       STAppData.setSubject(EmailSubject);
            
    }

    }

    }
catch (Exception e)
        {
           System.out.println ("exception reading file settings: " + e.toString());
          
        }
 }
   if (hasGUI)
 {
  //   STAppFrame.UpdateDisplay();
 }
   
try
{
   NodeList ProcedureList = doc.getElementsByTagName("Procedure");

   boolean hasDataloop = false;
   boolean hasURLList = false;

for (int i = 0; i < ProcedureList.getLength(); ++i)
{
    
   Procedure newbug = new Procedure();
 
   
    Element Procedure = (Element) ProcedureList.item(i);
    String ProcType = "Procedure";
    if (Procedure.hasAttribute("Type"))
    {
    ProcType = Procedure.getAttribute("Type");
    }
    String DataLoopSource = "urllist";
    if (Procedure.hasAttribute("DataLoopSource"))
    {
     DataLoopSource = Procedure.getAttribute("DataLoopSource");
    }
    if ("Dataloop".equals(ProcType))
    {
        
        hasDataloop = true;
        String DataFile = "";
    
        if (Procedure.hasAttribute("DataLoopFile"))
                {
                    DataFile = Procedure.getAttribute("DataLoopFile");
            //legacy support...
                    if (DataFile.contains(File.separator))
                    {
                        DataLoopSource = "file";
                    }
                }
        //failsafe
            if (DataLoopSource.equals("")||DataLoopSource.equals("none"))
        {
            DataLoopSource = "file";
        }
        if ("file".equals(DataLoopSource))
        {
        File DataFile_file = new File(DataFile);
   
   STAppData.AddNewDataLoopFile(DataFile_file);

    int last_added_bug_index = STAppData.BugArray.size()-1;
     newbug = STAppData.BugArray.get(last_added_bug_index);

  

    
 
     
        }
       if ("urllist".equals(DataLoopSource))
        {
         hasURLList = true;
          STAppData.AddNewDataLoopURLList(DataFile);
           int last_added_bug_index = STAppData.BugArray.size()-1;
           newbug = STAppData.BugArray.get(last_added_bug_index);
    
  
  
   
 
  
  
   
    
 
        }
      if (Procedure.hasAttribute("Random"))
  {
  String stRand = Procedure.getAttribute("Random");
    Boolean Rand = false;
    if (stRand.equals("true"))
    {
        Rand = true;
    }
   
    STAppData.BugArray.get(i).setRandom(Rand);
   
  }
    if (Procedure.hasAttribute("Limit"))
  {
    int limit = Integer.parseInt(Procedure.getAttribute("Limit"));
    STAppData.BugArray.get(i).setLimit(limit);
   
  }
    }
    else
    {
     STAppData.AddNewBug(); 
     int last_added_bug_index = STAppData.BugArray.size()-1;
      newbug = STAppData.BugArray.get(last_added_bug_index);
   
       
      
 
    }
    String BugTitle = "";
    if (Procedure.hasAttribute("Title"))
    {
        BugTitle = Procedure.getAttribute("Title");
    }
    STAppData.BugArray.get(i).setBugTitle(BugTitle);
    
    String BugURL = "";
    if (Procedure.hasAttribute("URL"))
    {
        BugURL = Procedure.getAttribute("URL");
    }
    STAppData.BugArray.get(i).setBugURL(BugURL);
    
    // needed?
   // String stPass = Procedure.getAttribute("Pass");
  //  Boolean Pass = false;
  //  if (stPass.equals("true"))
  //  {
  //      Pass = true;
  //  }
  //  STAppData.BugArray.get(i).setPass(Pass);

    NodeList ActionsList = Procedure.getElementsByTagName("Action");
   
    for (int j = 0; j <ActionsList.getLength(); j++)
    {
   Element Action = (Element) ActionsList.item(j);
   NodeList ActionNodes = Action.getChildNodes();
   String thisActionNodeName = "none";
   String thisActionNodeValue = "none";
   
   String Variable1 = "";
   String Variable2 = "";
   String LOCKED = "false";
   String BoolVal1 = "false";
   String BoolVal2 = "false";
    String TimeOfTest;
    String ActionType = "none";
    String ActionIndex;
    String ActionPass;
    String Password = "";
    
    
   Boolean RealBoolVal1 = false;
   Boolean RealBoolVal2 = false;
   Boolean boolLOCKED = false;
    for (int k = 0; k<ActionNodes.getLength(); k++)
    {
   thisActionNodeName = ActionNodes.item(k).getNodeName();
   thisActionNodeValue = ActionNodes.item(k).getTextContent();
   
    switch(thisActionNodeName)
    {
        case "Pass":
            ActionPass = thisActionNodeValue;
            break;
        case "ActionIndex":
            ActionIndex = thisActionNodeValue;
            break;
        case "Type":
            ActionType = thisActionNodeValue;
            break;
        case "Variable1":
            Variable1 = thisActionNodeValue;
            break;
        case "Variable2":
            Variable2 = thisActionNodeValue;
            break;
        case "BoolVal1":
            BoolVal1 = thisActionNodeValue;
            if (BoolVal1.equals("true"))
                    {
                    RealBoolVal1 = true;
                    }
           break;
            case "BoolVal2":
            BoolVal2 = thisActionNodeValue;
            if (BoolVal2.equals("true"))
                    {
                    RealBoolVal2 = true;
                    }
           break;
        case "LOCKED":
            LOCKED = thisActionNodeValue;
             if (LOCKED.equals("true"))
                    {
                    boolLOCKED = true;
                    }
            break;
       

        case "TimeOfTest":
            TimeOfTest = thisActionNodeValue;
            break;
    }  
                
    } 
    
 
   if (ActionType.contains("Password"))
   {
       try
       {
       Password = Protector.decrypt(Variable2);
       Variable2 = Password;
       }
       catch (Exception e)
       {
     //   System.out.println("Load/decrypt error: " + e.toString());
       }
   }
  
   
   
     
           if (mainAppController.NewActionsMaster.ActionHashMap.contains(ActionType))
           {
               BMAction thisActionToAdd = (BMAction) mainAppController.NewActionsMaster.CreateAction(ActionType);
               thisActionToAdd.SetVars(Variable1, Variable2, Password, RealBoolVal1, RealBoolVal2, boolLOCKED);
               STAppData.AddActionToArray(thisActionToAdd, newbug);
              
           }      
 
     if (mainAppController.NewActionsMaster.PassFailActionHashMap.contains(ActionType))
             {
               BMAction thisActionToAdd = (BMAction) mainAppController.NewActionsMaster.CreatePassFailAction(ActionType);
            
               thisActionToAdd.SetVars(Variable1, Variable2, Password, RealBoolVal1, RealBoolVal2, boolLOCKED);
             
               STAppData.AddActionToArray(thisActionToAdd, newbug);
               
             }
      
   
 
 
  

        }   

    }

    if (hasDataloop)
 {
   
 
 } 
    }
catch (Exception e)
        {
            System.out.println("Exception parsing procedure node" + e.toString());
          e.printStackTrace();
        }
 
    
  }
}
