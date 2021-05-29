/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;


public class TheBrowserBots  extends JFrame {
public static String USERDIR;
String rootURL;
String loginName;
String loginPassword;
Integer user_id;
String CONFIG_FILE_NAME;
TheBrowserBotsFileCloud TheBrowserBotsAppFrame;
String OS;
String USERCLOUDDIR;
TheBrowserBotsConfig TheBrowserBotsConfig;
 public final String ProgramVersion = "2.0.018";
 String APPID;
 String BROWSERMATORFOLDER;
 ArrayList<String> theseErrors = new ArrayList();
   static final int buffer = 512;
public TheBrowserBots()
  {
   
     APPID = "2";   
 TheBrowserBotsConfig = new TheBrowserBotsConfig();
      CONFIG_FILE_NAME = "the_browserbots_config.properties";
        USERDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsAppFolder";
       BROWSERMATORFOLDER = System.getProperty("user.home") + File.separator + "BrowsermatorAppFolder" + File.separator;
      OS = TheBrowserBotsConfig.OperatingSystem;
        USERCLOUDDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles";
 // rootURL = "https://www.TheBrowserBots.com"; 
  rootURL = "https://www.thebrowserbots.com";
// rootURL = "http://localhost";

   TheBrowserBotsAppFrame = new TheBrowserBotsFileCloud(this);
          String versionstored = TheBrowserBotsConfig.getKeyValue("version_main");
    if (versionstored==null) {versionstored = "";}
    
      
    if (!versionstored.equals(this.ProgramVersion))
    {
   ExtractBrowserMatorFiles();
    }
  }
  
    public static void main(String[] args) {
 


         TheBrowserBots app = new TheBrowserBots(); 
           app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   
   

    
           app.OpenBrowserBotsAppFrame();
  
  
                
        }
    
 
 public void OpenBrowserBotsAppFrame()
{

   
  TheBrowserBotsAppFrame.initializeJFXPanel();
  

            try
{
  
     FileInputStream input = new FileInputStream(USERDIR + File.separator + CONFIG_FILE_NAME);
    
     TheBrowserBotsAppFrame.setWindowProps(input);
}
        catch(Exception ex)
        {
          System.out.println("Exception loading config: " + ex.toString() + USERDIR +File.separator + CONFIG_FILE_NAME);  
        }
    TheBrowserBotsAppFrame.cloudFrame.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent windowEvent) {
    
    TheBrowserBotsAppFrame.cloudFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    File newconfig = new File(USERDIR + File.separator + CONFIG_FILE_NAME);
     Properties newProps = TheBrowserBotsConfig.applicationProps;
         Boolean file_exists = false;
       
    try
{
  
     FileInputStream input = new FileInputStream(USERDIR + File.separator + CONFIG_FILE_NAME);
   newProps.load(input);
    
   
      
      newProps.setProperty("main_window_locationY", Integer.toString(windowEvent.getWindow().getY()));
      newProps.setProperty("main_window_locationX", Integer.toString(windowEvent.getWindow().getX()));
      newProps.setProperty("main_window_sizeWidth", Integer.toString(windowEvent.getWindow().getWidth()));
      newProps.setProperty("main_window_sizeHeight", Integer.toString(windowEvent.getWindow().getHeight()));
    
    
    FileWriter writer = new FileWriter(USERDIR + File.separator + CONFIG_FILE_NAME);
    newProps.store(writer, "thebrowserbots_settings");
    writer.close();
 
    
} 

    catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
      System.exit(0);
    }
       
    });
            
}
public void saveProps()
{
 // probably don't need   
}
       public void LoadNameAndPassword()
  {
   
     //     Properties applicationProps = new Properties();
     

this.loginName = TheBrowserBotsConfig.getKeyValue("loginName");
String temppassword = TheBrowserBotsConfig.getKeyValue("loginPassword");

 if (temppassword==null || "".equals(temppassword))
   {
    this.loginName = "";
    this.loginPassword = "";
   }
   else
       
   {
       
       try
    {
     String machineSN = TheBrowserBotsConfig.ReturnMachineSerialNumber();
     this.loginPassword = Protector.decryptLocal(temppassword, machineSN);
    }
    catch (Exception ex)
    {
      System.out.println("error decrypting login pw: " + ex.toString());
       
    }

   
   }

   
   
        
  }

   public void SaveNameAndPassword(String in_loginName, String in_Password)
  {
    
    
      this.loginName = in_loginName;
      this.loginPassword = in_Password;

    
 
  String encPassword = "";
  try
      {
           String machineID = TheBrowserBotsConfig.ReturnMachineSerialNumber();
       encPassword = Protector.encryptLocal(in_Password, machineID);
      }
      catch (Exception ex)
      {
          System.out.println("error encrypting login pw: " + ex.toString());
          
      }
  TheBrowserBotsConfig.setKeyValue("loginPassword", encPassword);
  TheBrowserBotsConfig.setKeyValue("loginName", in_loginName);
  
 
  }  
   
    public void LookUpUser(String name, String password)
 {
   String outHTML = "";
     UserParamHash userData = new UserParamHash(name, "", password);
     SendReceiveData thisSession = new SendReceiveData(rootURL + "/get_user_id.php", userData);
  Boolean errorcheck = false;
          try
           {
      outHTML = thisSession.SendParams();           
           }
   catch (Exception ex)
   {
       errorcheck = true;
       System.out.println ("Exception getting user_id : " + ex.toString());
       
   }
   if (outHTML.isEmpty() || "failed".equals(outHTML))
   {
       if (errorcheck)
       {
           this.user_id = -1;
       }
       else
       {
        this.user_id = 0;
       }
       
 }
   else
   {
       try
       {
      this.user_id = Integer.parseInt(outHTML);
       }
       catch (Exception ex)
       {
           System.out.println("Exception parsing user_id int:" + ex.toString());
           this.user_id = -1;
       }
               
   }
 }
     public String RecoverPassword (String email)
 {
     
     UserParamHash userData = new UserParamHash("", email, "");
      SendReceiveData thisSession = new SendReceiveData(rootURL + "/recover.php", userData);
      try
     {
     String outHTML = thisSession.SendParams();
      if ("Success".equals(outHTML))
      {
        return "An email with your password has been sent.";
       
      }
      else
      {
          if (outHTML.contains("failed to send"))
          {
  
          return "Connection to mail server has failed.  Please try again later.";    
          }
          
          else
          {
        return "There is no account registered to " + email;
          }
      }
     
      
     }
     catch (Exception ex)
     {
         
         System.out.println("Exception recovering password: " + ex.toString());
         if (ex.toString().contains("Connection refused"))
         {
             return "Unable to connect to TheBrowserBots.com";
         }
         else
         {
         return "Unable to recover password.";
         }
     }
   
 }
 public String RegisterUser (Login_Register_Dialog loginDialog, String loginName, String Email, String Password)
 {
    
     
     UserParamHash userData = new UserParamHash(loginName, Email, Password);
     SendReceiveData thisSession = new SendReceiveData(rootURL + "/register.php", userData);
  try
  {
     String outHTML = thisSession.SendParams();
      if ("Success".equals(outHTML))
      {
        
       return "Success";
       
      }
      else
      {
        return "The username and/or email address you have chosen already exists.";
      }
  }
  catch (Exception ex)
  {
      System.out.println ("Exception sending params register: " + ex.toString());
  }
     
     
return "Unable to connect thebrowserbots.com.";
  
 }
  public void ExtractBrowserMatorFiles()
 {
    
  
        ProgressFrame waitprompt = new ProgressFrame("Extracting Browsermator");
       waitprompt.initNoButtons("Extracting Browsermator...");
 WriteResource ("", "Browsermator.jar");
 WriteResource ("lib", "byte-buddy-1.8.3.jar");    
 WriteResource ("lib", "selenium-server-standalone-3.141.59.jar");    
 WriteResource ("lib", "commons-codec-1.10.jar");    
 WriteResource ("lib", "commons-exec-1.3.jar");    
 WriteResource ("lib", "commons-io-2.5.jar");    
 WriteResource ("lib", "commons-logging-1.2.jar");    
 WriteResource ("lib", "gson-2.8.4.jar");    
 WriteResource ("lib", "guava-25.0-jre.jar");    
// WriteResource ("lib", "htmlunit-2.27.jar");    
// WriteResource ("lib", "htmlunit-core-js-2.27.jar");    
 WriteResource ("lib", "htmlunit-driver-2.33.3-jar-with-dependencies.jar");    
 WriteResource ("lib", "httpclient-4.5.5.jar");    
 WriteResource ("lib", "httpcore-4.4.9.jar");    
 WriteResource ("lib", "okhttp-3.10.0.jar");    
 WriteResource ("lib", "okio-1.14.1.jar"); 
 WriteResource ("lib", "opencsv-3.7.jar");    
 WriteResource ("lib", "poi-3.14-20160307.jar");    
 WriteResource ("lib", "poi-ooxml-3.14-20160307.jar");    
 WriteResource ("lib", "poi-ooxml-schemas-3.14-20160307.jar");    
 WriteResource ("lib", "serializer-2.7.2.jar");    
 WriteResource ("", "browsermator_license.txt"); 
 WriteResource ("", "chromedriver_license.txt"); 
 WriteResource ("", "geckodriver_license.txt"); 
 WriteResource ("", "Joe Walnes-base64encoder-bsd-license.txt"); 
 WriteResource ("", "selenium-apache2-license.txt"); 
 WriteResource ("", "commons_license.txt"); 


  if (theseErrors.size()>0)
  {
      String allErrors = "";
      for (String thisError: theseErrors)
      {
          allErrors+=thisError;
      }
      Prompter errorsPrompt = new Prompter ("Error extracting files.", "The following errors occurred while extracting files: " + allErrors, false,0,0);

  }
 else
  {
    TheBrowserBotsConfig.setKeyValue("version_main", this.ProgramVersion);
  }
 waitprompt.mainFrame.dispose();
    
 }

  public void WriteResource (String dirname, String filename)
 {
       File driver_directory = new File(BROWSERMATORFOLDER + dirname);
        if (!driver_directory.exists()) {
            if (driver_directory.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        InputStream inStream;
        if ("".equals(dirname))
        {
     inStream = TheBrowserBots.class.getResourceAsStream("/thebrowserbots/com/Resources/" + filename);
          
        }
        else
        {
     inStream = TheBrowserBots.class.getResourceAsStream("/thebrowserbots/com/Resources/" + dirname + "/" + filename);
        }
         File newFile;
        if ("".equals(dirname))
        {
         newFile = new File(BROWSERMATORFOLDER  + filename);     
        }
        else
        {
     newFile = new File(BROWSERMATORFOLDER + dirname + File.separator + filename); 
        }
          Path BMAppPath = newFile.toPath();

try
{
   if (inStream != null) {
 
                Files.copy(inStream, BMAppPath,StandardCopyOption.REPLACE_EXISTING);
                inStream.close();
                setPermissions(newFile);
            }
   else
   {
   
    
   }
   
}
catch (Exception ex)
{
    theseErrors.add(ex.toString());
}  

 }
   public void setPermissions(File thisFile)
         {
             
          
           if (!thisFile.canExecute())
           {
            thisFile.setExecutable (true, true);
           }
           
         }
}
