/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.io.File;
import javafx.scene.web.WebEngine;


public class TheBrowserBotsPackage {
  File defaultListFile;
  File BrowserMationFile;
  File mapFile;
 
  WebEngine CloudEngine;
  TheBrowserBots mainAppController;

   public static String USERDIR;

  TheBrowserBotsPackage(File browserMationFile_in, File mapFile_in, File listFile_in, TheBrowserBots in_AppController, WebEngine in_CloudEngine)
  {
 
   
      BrowserMationFile = browserMationFile_in;
      if (mapFile_in.getName().contains(".csv"))
      {
      mapFile = mapFile_in;
      }
      if (listFile_in.getName().contains(".csv"))
      {
      defaultListFile = listFile_in;
      }
      
  USERDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles";
  mainAppController = in_AppController;
  CloudEngine = in_CloudEngine;

  }
 
    public void RunBot()
    {
      
    
        
        

   File file = new File(USERDIR);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
      
  
  
  
                  try
        {
            
            
            File browserMatorFileRef = new File (mainAppController.BROWSERMATORFOLDER + "Browsermator.jar");
            if (browserMatorFileRef.exists()==false)
            {
              mainAppController.ExtractBrowserMatorFiles();
            }
            setPermissions(browserMatorFileRef);
             
            if (mapFile!=null)
             {
             
    Runtime.getRuntime().exec("java -jar " + mainAppController.BROWSERMATORFOLDER + "Browsermator.jar" + " runsilent " + BrowserMationFile + " " + mapFile);
 //     Runtime.getRuntime().exec("java -jar " + mainAppController.BROWSERMATORFOLDER + "Browsermator.jar" + " open " + BrowserMationFile + " " + mapFile);
//   mapFile = null;
             }
             else
             {
         Runtime.getRuntime().exec("java -jar " + mainAppController.BROWSERMATORFOLDER + "Browsermator.jar" + " runsilent " + BrowserMationFile);
             
             }
     //     mainAppController.dispose();
     // Platform.runLater(new Runnable() {
     //       @Override
     //       public void run() {

    //        String URLwithLogin = mainAppController.rootURL + "/browse_files.php?app_id="+mainAppController.APPID+ "&version=" + mainAppController.ProgramVersion + "&loginName=" + mainAppController.loginName + "&loginPassword=" + mainAppController.loginPassword;

  //  CloudEngine.load(URLwithLogin);

   //         }
   //     });
        }
                  catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
 //                 try
 //                 {
 //                Thread.sleep(100000);
 //                 }
 //                 catch (Exception ex)
 //                 {
        //              System.out.println (ex.toString());
 //                 }
 //System.exit(0);
 
           
         
  
             }
           public void setPermissions(File thisDriver)
         {
             
        
           if (!thisDriver.canExecute())
           {
       Prompter cantexecuteprompt = new Prompter ("Permissions Error", "The current user does not have permission to run the Browsermator.  We will attempt to set permissions now.  If this fails, you'll need to manually set permission to execute the following file: " + thisDriver.getAbsolutePath(), false,0,0);     
  
            thisDriver.setExecutable (true, true);
           }
           
         }
    }