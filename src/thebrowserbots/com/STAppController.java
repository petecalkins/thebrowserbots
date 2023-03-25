package thebrowserbots.com;

import com.opencsv.CSVReader;


import java.beans.PropertyVetoException;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public final class STAppController  {

public final String UNIQUE_LOG_DIR;
private int CurrentMDIWindowIndex;
public final String ProgramVersion = "4.3.053";
public final String lastWebDriverUpdate = "08072022";
public boolean DriverUpdateFail = false;
public String loginName;
public String loginPassword;
String PTPUSERCLOUDDIR;
String BMUSERCLOUDDIR;
String WEBDRIVERSDIR;
String BrowsermatorAppFolder; 
String PTPAPPFOLDER;
  Boolean SHOWGUI = true;
  Boolean updateIT = false;
  public int user_id;
//  String rootURL = "http://localhost";
 String rootURL = "https://www.browsermator.com";
 
       ArrayList<SeleniumTestToolData> MDIDataClasses = new ArrayList();
 MainAppFrame mainAppFrame;
String BMPATH;
Boolean EXITAFTER = false;
String PTPCLOUDDIRUSERLIST;
Boolean deletemap = false;
String mapPath = "";
BrowserMatorConfig appConfig;
ActionsMaster NewActionsMaster;
  public STAppController(String[] args) throws PropertyVetoException {
 
      NewActionsMaster = new ActionsMaster();
      BrowsermatorAppFolder =   System.getProperty("user.home")+File.separator+"BrowsermatorAppFolder"+File.separator;
      PTPUSERCLOUDDIR = System.getProperty("user.home") + File.separator + "PTPCloudFiles" + File.separator;
     PTPCLOUDDIRUSERLIST = System.getProperty("user.home") + File.separator + "PTPCloudFiles" + File.separator + "UserLists" + File.separator;
       BMUSERCLOUDDIR = System.getProperty("user.home") + File.separator + "BrowserMatorCloudFiles" + File.separator;
PTPAPPFOLDER = System.getProperty("user.home") + File.separator + "PTPAppFolder" + File.separator;
UNIQUE_LOG_DIR = BrowsermatorAppFolder + "BrowsermatorUniqueLogFolder" + File.separator;
   File file = new File(BrowsermatorAppFolder);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
   
      
     file = new File(UNIQUE_LOG_DIR);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
           WEBDRIVERSDIR = BrowsermatorAppFolder + "Webdrivers" + File.separator;
       File web_dir_file = new File(WEBDRIVERSDIR);
        if (!web_dir_file.exists()) {
            if (web_dir_file.mkdir()) {
                updateIT = true;
                System.out.println("Directory is created!");
            } else {
             
                System.out.println("Failed to create directory!");
            }
        }
          appConfig = new BrowserMatorConfig();
      ExtractWebDrivers();
 
   
  
    if (args.length>0) { 
   
        CheckArgs(args);}

  
          
 
 
  }
 
  public void ApplyMap(List<String[]> mapEntries, SeleniumTestToolData STAppData)
  {
      for(String[] ROW: mapEntries)
      {
          int bugindex = -1;
          int actionindex = -1;
          int fieldNumber = -1;
          String newValue = "";
        if (ROW.length>3)
        {
          if (ROW[0].contains("-"))
          {
 String[] parts = ROW[0].split("-");
bugindex = Integer.parseInt(parts[0])-1;
actionindex = Integer.parseInt(parts[1])-1;
          }
          else
          {
              try
              {
              bugindex = Integer.parseInt(ROW[0])-1;
          
              }
              catch (Exception ex)
              {
                System.out.println (ex.toString());  bugindex = -1;
              }
          }
       String commandText = ROW[1];
       try
       {
      fieldNumber = Integer.parseInt(ROW[2]);
       }
       catch (Exception ex)
       {
           fieldNumber = 0;
       }
      newValue = ROW[3];
       
       switch(commandText)
            {
           case "changedownloaddir":         
 
 appConfig.setKeyValue("downloaddir", newValue);
               break;
               
           case "silentmode":
               STAppData.setSilentMode(true);
               break;
               
              case "deleteme":
               deletemap = true;
               break;
               
           case "changeOS":
        
                STAppData.setOSType(newValue);
                
               break;
           case "changebrowser":
             
                STAppData.setTargetBrowser(newValue);
               
               break;
               
           
             case "disable":
                 if (actionindex>-1)
                 {
             
                STAppData.BugArray.get(bugindex).ActionsList.get(actionindex).Locked=true;
                 }
                 else
                 {
                 
                     STAppData.BugArray.get(bugindex).setLocked(true);
                 }
                 break;
             case "enable":
                 if (actionindex>-1)
                 {
                
                STAppData.BugArray.get(bugindex).ActionsList.get(actionindex).Locked=false;
                 }
                 else
                 {
                
                     STAppData.BugArray.get(bugindex).setLocked(false);   
                 }
                 break;
             case "changefield":
                 if (fieldNumber==1)
                 {
                STAppData.BugArray.get(bugindex).ActionsList.get(actionindex).Variable1 = newValue;
                 }
                 if (fieldNumber==2)
                 {
                STAppData.BugArray.get(bugindex).ActionsList.get(actionindex).Variable2 = newValue;
                 }
                 if (fieldNumber==3)
                 {
                     try
                     {
                     newValue = Protector.decrypt(newValue);
                     }
                     catch (Exception ex)
                     {
                         
                     }
                 STAppData.BugArray.get(bugindex).ActionsList.get(actionindex).Variable2 = newValue;    
                 }
                 break;
             case "setdatafile":
                    if (newValue.contains("%PTPUSERLIST%"))
      {
         newValue = newValue.replace("%PTPUSERLIST%", PTPCLOUDDIRUSERLIST);
      }
                   if (newValue.contains("%PTPCLOUDDIR%"))
      {
         newValue = newValue.replace("%PTPCLOUDDIR%", PTPUSERCLOUDDIR);
      }
      if (newValue.contains("%BMUSERCLOUDDIR%"))
      {
          newValue = newValue.replace("%BMUSERCLOUDDIR%", BMUSERCLOUDDIR);
      }
              File checkfile = new File(newValue);
              if (checkfile.exists())
              {
                  try
                  {
                  newValue = checkfile.getCanonicalPath();
                  }
                  catch (Exception ex)
                  {
                      System.out.println(ex.toString());
                  }
                  }
              STAppData.BugArray.get(bugindex).setDataFile(newValue);
           
           
                  break;
             case "setstoredvar":
        
               if (newValue.contains(":::"))
               {
               String[] valueNames = newValue.split(":::");
               
                   
               for (String value: valueNames)
               {
                 STAppData.SetSentStoredVariableName(newValue);
              
               }
               }
               else
               {
                   STAppData.SetSentStoredVariableName(newValue);
             
               }
             break;
                 
                     
       }
         
        }
      }
      
  
  }

  public void OpenFile(File file, boolean RunIt, String mapFile)
  {
      mapPath = mapFile;
      List<String[]> mapEntries;
            try {
                 CSVReader Reader = new CSVReader(new FileReader(mapFile), ',', '"', '\0');
              mapEntries = Reader.readAll();   
              Reader.close();
                     }
                     catch(Exception e)
                             {
                          mapEntries = new ArrayList<String[]>();       
                             }
       
 
       
      OpenFileThread OPENREF = new OpenFileThread(this, file, MDIDataClasses, mapEntries);
  OPENREF.execute();       
   

    
  }
     public void OpenFile (File file, boolean RunIt) 
    {
        
   
       
      OpenFileThread OPENREF = new OpenFileThread(this, file, MDIDataClasses);
  OPENREF.execute();       
    }
 
      public void OpenFile (File file, boolean RunIt, boolean fromCloud) 
    {
        
  
  OpenFileThread OPENREF = new OpenFileThread(this, file, MDIDataClasses, false, RunIt, fromCloud);
  OPENREF.execute();
 

    }
     
  
  

  public void CheckArgs(String[] args)
  {
      Boolean hasMap = false;
     String mapfile = "";
     if (args.length>2)
     {
   
       mapfile = args[2];
      File file_to_open = new File(args[1]);
     
   
   
    
  
  
       SHOWGUI=false;
       EXITAFTER = true;
   
    OpenFile(file_to_open, true, mapfile);
 
 
     }
   
        
  }
   public void UpdateWindowName (int MDI_CLASS_INDEX, String old_name)
   {
      String update_name = MDIDataClasses.get(MDI_CLASS_INDEX).filename;
   
      
   }
  
   
   

  
  public void LoadFirefoxPath()
  {
          Properties applicationProps = new Properties();
  
try
{
         try (FileInputStream input = new FileInputStream(BrowsermatorAppFolder + "browsermator_config.properties")) {
             applicationProps.load(input);
         }
         catch (Exception e)
         {
             System.out.println("Error config file: " + e);
           
             
         }
}
catch (Exception e) {
			System.out.println("Exception loading firefox path: " + e);
                        
		} 
        
  }




 
   
   public void UpdateStoredVarPulldowns (SeleniumTestToolData STAppData, int BugIndex)
   {
   for (BMAction A: STAppData.BugArray.get(BugIndex).ActionsList)
   {
       if ("Store Link as Variable by XPATH".equals(A.Type))
       {
           STAppData.VarHashMap.remove(A.Variable2);
       }
       if ("Store Links as URL List by XPATH".equals(A.Type))
       {
           STAppData.VarLists.remove(A.Variable2);
       }
   }
  
   }
    
       
    

   
  public void RunActions (SeleniumTestToolData STAppData)
  {
           int sessions = 1;
           if (EXITAFTER)
           {
               STAppData.setExitAfter(true);
           }
         if (STAppData.MultiSession)
         {
          sessions = STAppData.getSessions();
         }
         
          String tbrowser = STAppData.getTargetBrowser();
      if ("Firefox/IE/Chrome".equals(tbrowser))
      {
 for (int x=0; x<sessions; x++)
 {
    
     STAppData.setTargetBrowser("Firefox");
       RunAllTests REFSYNCH = new RunAllTests(STAppData);
    REFSYNCH.execute();   
    STAppData.setTargetBrowser("Chrome");
       RunAllTests REFSYNCH2 = new RunAllTests(STAppData);
    REFSYNCH2.execute();  
    STAppData.setTargetBrowser("Internet Explorer-32");
      RunAllTests REFSYNCH3 = new RunAllTests(STAppData);
    REFSYNCH3.execute();  
    STAppData.setTargetBrowser("Firefox/IE/Chrome");
 }
      }
      else
      {
     for (int x=0; x<sessions; x++)
 {
    RunAllTests REFSYNCH = new RunAllTests(STAppData);
    REFSYNCH.execute();      
 }       
  }
  }
 


 public void ExtractWebDrivers()
 {
    
     
    String versionstored = appConfig.getKeyValue("version");
    String lastWebDriverUpdateStored = appConfig.getKeyValue("lastWebDriverUpdate");
    if (versionstored==null) {versionstored = "";}
    if (lastWebDriverUpdateStored==null) {lastWebDriverUpdateStored="";}
       if (!lastWebDriverUpdateStored.equals(this.lastWebDriverUpdate))
       {
           updateIT = true;
       }
  
    if (updateIT)
    {
       ProgressFrame waitprompt = new ProgressFrame("Extracting webdrivers");
       waitprompt.initNoButtons("Extracting Webdrivers...");
       
         //  Prompter waitprompt = new Prompter ("Extracting webdrivers", "We've detected that Selenium automation webdrivers need to be extracted or updated. This could take a minute or two.", false,0,0);
 WriteResource ("chromedriver_linux32", "chromedriver");
 WriteResource ("chromedriver_linux64", "chromedriver");    
 WriteResource ("chromedriver_mac64", "chromedriver");
 WriteResource ("chromedriver_win32", "chromedriver.exe");
// WriteResource ("chromedriver_win32", "chromedriver-winxp.exe");
// WriteResource ("edgedriver", "MicrosoftWebDriver.exe");
 WriteResource ("geckodriver-linux32", "geckodriver");
 WriteResource ("geckodriver-linux64", "geckodriver");
 WriteResource ("geckodriver-osx", "geckodriver");
  WriteResource ("geckodriver-win32", "geckodriver.exe");
   WriteResource ("geckodriver-win64", "geckodriver.exe");
    WriteResource ("iedriverserver_win32", "IEDriverServer.exe");
     WriteResource ("iedriverserver_win64", "IEDriverServer.exe");
      File driver_directory = new File(WEBDRIVERSDIR + "edgedriver");
        if (!driver_directory.exists()) {
            if (driver_directory.mkdir()) {
              
            } 
        }
   if (DriverUpdateFail==false) { appConfig.setKeyValue("lastWebDriverUpdate", this.lastWebDriverUpdate);}
  waitprompt.mainFrame.dispose();
    }

  
 }
 public void WriteResource (String dirname, String filename)
 {
       File driver_directory = new File(WEBDRIVERSDIR + dirname);
        if (!driver_directory.exists()) {
            if (driver_directory.mkdir()) {
              
            } else {
             
                DriverUpdateFail = true;
            }
        }
      InputStream inStream = STAppController.class.getResourceAsStream("/thebrowserbots/com/Resources/" + dirname + "/" + filename);
   
      File newFile = new File(WEBDRIVERSDIR + dirname + File.separator + filename); 
          Path BMAppPath = newFile.toPath();

try
{
   if (inStream != null) {
 
                Files.copy(inStream, BMAppPath,StandardCopyOption.REPLACE_EXISTING);
                inStream.close();
            }
   else
   {
   DriverUpdateFail = true;
    
   }
   
}
catch (Exception ex)
{
    Prompter fallbackprompt2 = new Prompter ("Error writing driver", "Could not write driver: " + BMAppPath + " Error: " + ex.toString(), false,0,0);  
   DriverUpdateFail = true;
 
}  
        
 }
 
      
}

