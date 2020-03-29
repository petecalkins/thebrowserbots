/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;
import java.awt.Cursor;
import java.io.File;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javax.swing.SwingWorker;

public class SendFileThread extends SwingWorker<String, Integer>{
 Upload_File_Dialog thisUpDiag;
 File sendfile;
 String name;
 String password;
 String file_id;
 String filename;
 String description;
 String isPrivate;
 File clean_file;
 TheBrowserBots mainApp;
// String rootURL = "http://localhost";
// String rootURL = "https://www.thebrowserbots.com";
  String rootURL = "https://www.thebrowserbots.com";
  String USERCLOUDDIR;
WebEngine cloudEngine;
String version;
String app_id;
Boolean isList;

    SendFileThread(String in_version, String in_app_id, WebEngine in_cloudREF, File in_sendfile, String in_name, String in_password, String in_fileid, String in_filename, Boolean in_isList)
  {

 USERCLOUDDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles";
 this.thisUpDiag = null;
 this.sendfile = in_sendfile;
 this.name = in_name;
 this.filename = in_filename;
 this.password = in_password; 
  this.file_id = in_fileid;
this.description = "";
 this.isPrivate = "";
 this.cloudEngine = in_cloudREF;
    version = in_version;
   app_id = in_app_id;   
 isList = in_isList;
  }
    
    SendFileThread(TheBrowserBots mainApp, Upload_File_Dialog in_thisUpDiag, File in_sendfile, String in_name, String in_password)
  {
 this.file_id = "";
 this.thisUpDiag = in_thisUpDiag;
 this.sendfile = in_sendfile;
 this.name = in_name;
 this.password = in_password;
  this.filename = thisUpDiag.getFilename();
this.description = thisUpDiag.getDescription();
 this.isPrivate = thisUpDiag.getPrivate();
 this.mainApp = mainApp;
  }
@Override 
public String doInBackground()
 {
    
         String ret_val = "none";
     if (thisUpDiag!=null)
     {
     thisUpDiag.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     thisUpDiag.setUpButtonText("Uploading...");
   
     }
     else
     {
         
     }
     UserParamHash userData = new UserParamHash(name, "", password);
     SendReceiveData thisSession = new SendReceiveData(rootURL + "/get_user_id.php", userData);
   
     try
     {
     String outHTML = thisSession.SendParams();
     if (outHTML.isEmpty()==false)
     {
         int check_int =0;
      try
      {
          check_int = Integer.parseInt(outHTML);
          
      }
      catch (Exception ex)
              {
             check_int = -1;     
              }
     if (check_int>0)
      {
        if (thisUpDiag!=null)
        {
       outHTML = thisSession.UploadFile(filename, description, sendfile, isPrivate);
    thisUpDiag.setUpButtonText("Upload File");
        }
        else
        {
            clean_file = thisSession.UpdateFile(version, app_id, file_id, sendfile, isList);
            if (clean_file.exists())
            {
                outHTML = "Success";
            }
        }
       if ("Success".equals(outHTML))
       {
         
           if (thisUpDiag!=null)
           {
           thisUpDiag.dispose();
           thisUpDiag.hideWarningMessage();
               File thisFile = new File(USERCLOUDDIR+ File.separator + filename);
        Files.copy(sendfile.toPath(), thisFile.toPath(), REPLACE_EXISTING);
         
       //  sendfile.deleteOnExit();
           mainApp.OpenBrowserBotsAppFrame();
           }
           else
           {
            File thisFile = new File(USERCLOUDDIR+ File.separator +filename);
        Files.copy(clean_file.toPath(), thisFile.toPath(), REPLACE_EXISTING);
         
    //     clean_file.deleteOnExit();
           }
       }
       if ("dupe file".equals(outHTML))
       {
           if (thisUpDiag!=null)
           {
           thisUpDiag.setshowWarningMessage("A file with this name already exists.  Please use a unique filename.");
           }
           
           }
       if ("error uploading file".equals(outHTML))
       {
           if (thisUpDiag!=null)
           {
            thisUpDiag.setshowWarningMessage("An error occurred while uploading the file.  Please try again.");
           }
       }
        if ("file invalid".equals(outHTML))
       {
           if (thisUpDiag!=null)
           {
            thisUpDiag.setshowWarningMessage("The file you have chosen is invalid.");
           }
       }
      }
     else
     {
         if (thisUpDiag!=null)
           {
         thisUpDiag.setshowWarningMessage("You are not logged in."); 
         thisUpDiag.setUpButtonText("Upload File");
           }
     }
     
     }
     else
     {
         if (thisUpDiag!=null)
           {
         thisUpDiag.setshowWarningMessage("You are not logged in.");
         thisUpDiag.setUpButtonText("Upload File");
           }
     }
      
     }
     catch (Exception ex)
     {
         System.out.println("Exception uploading file: " + ex.toString());
     }
      
    return ret_val;
     
 }
 @Override
 protected void process ( List <Integer> bugindex)
 {
     
 }
 @Override
 protected void done()
 {
    if (thisUpDiag!=null)
    {
     thisUpDiag.setCursor(Cursor.getDefaultCursor());
   
    }
    else
    {
// move file to local ptpcloud folder
       String toURL = rootURL + "/browse_files.php?app_id=" + app_id + "&version=" +version + "&loginName=" + name + "&loginPassword=" + password;
  
      
          Platform.runLater(new Runnable() {
            @Override
            public void run() {

              cloudEngine.load(toURL);

            }
        });
    }

     
 }

}
