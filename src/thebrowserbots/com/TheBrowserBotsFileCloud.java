/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;


import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import netscape.javascript.JSObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JComboBox;


public class TheBrowserBotsFileCloud {
 
    String CONFIG_FILE_NAME;
    String USERDIR;
 WebView browser2;
 WebEngine webEngine;
JFXPanel fxPanel; 
 JPanel mainPanel;
 JFrame cloudFrame;
 String HTML_TO_SEND="";
 TheBrowserBots mainAppController;
// MainAppFrame mainAppFrame;
JLabel loggedinLabelText; 
JLabel loginLabelText;
//JLabel loginLabelName;
//JLabel loginLabelPassword;
//JTextField loginFieldName;
//JPasswordField loginFieldPassword;
// String rootURL = "http://localhost";

 String rootURL;
    JPanel bottomPanel;
JLabel LoadingLabel;

JButton loginButton = new JButton("Login"); 
JButton jButtonRefresh = new JButton("Refresh");
String USERCLOUDDIR;
String THEBROWSERBOTSCLOUDFILEPROPERTIES;
JComboBox jComboBoxTargetBrowser;
String targetBrowser;
List<String> mapFileList;
File MapFile;
JLabel jLabelTargetBrowser;
Properties cloudProps;
private JavaApp javaAppBridge;


     
      public TheBrowserBotsFileCloud(TheBrowserBots in_mainAppController)
      {
       mapFileList = new ArrayList<>();
       MapFile = new File(System.getProperty("user.home") + File.separator +"blank");
           cloudFrame = new JFrame("The BrowserBots");
        cloudProps = new Properties();
      
    //    rootURL = "https://www.thebrowserbots.com";
   rootURL = "https://www.thebrowserbots.com";
  //  rootURL = "http://localhost";
  CONFIG_FILE_NAME = "the_browserbots_config.properties";
  THEBROWSERBOTSCLOUDFILEPROPERTIES = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles"  + File.separator +  "TheBrowserBotsCloudFiles.properties";
  USERCLOUDDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles";
        USERDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsAppFolder";
 
     Platform.setImplicitExit(false);
     mainAppController = in_mainAppController;
     
    mainAppController.LoadNameAndPassword();
  
 }
      public void initializeJFXPanel()
      {
         fxPanel = new JFXPanel();
     
           targetBrowser = getSavedTargetBrowser();
              bottomPanel = new JPanel();
 LoadingLabel = new JLabel("Loading.  Please wait...");
    jLabelTargetBrowser = new JLabel ("Set Target Browser:");
 jComboBoxTargetBrowser = new JComboBox(new javax.swing.DefaultComboBoxModel(new String[] { "Chrome", "Firefox", "Internet Explorer-32", "Internet Explorer-64", "Silent Mode (HTMLUnit)", "Firefox/IE/Chrome" }));
 setjComboBoxTargetBrowser(targetBrowser);
 jComboBoxTargetBrowser.setSelectedItem(targetBrowser);
 
  
     

     
        
bottomPanel.add(LoadingLabel);
bottomPanel.add(jLabelTargetBrowser);
bottomPanel.add(jComboBoxTargetBrowser);


        if ("".equals(mainAppController.loginName))
     {
 ShowHTMLWindow(rootURL + "/browse_files.php?app_id="+mainAppController.APPID+"&version="+mainAppController.ProgramVersion);
     }
     else
     {
         String URLwithLogin = rootURL + "/browse_files.php?app_id="+mainAppController.APPID+ "&loginName=" + mainAppController.loginName + "&loginPassword=" + mainAppController.loginPassword + "&version=" + mainAppController.ProgramVersion;
         
         ShowHTMLWindow(URLwithLogin);
          
     }


    
   LoadingLabel.setVisible(false);     
      }
          public File BrowseForDownloadDir()
  {
           final JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fc.setDialogTitle("Select Download Directory");
       fc.setCurrentDirectory(new File(System.getProperty("user.home")));
       fc.setAcceptAllFileFilterUsed(false);
      int returnVal = fc.showOpenDialog(mainAppController);
         File chosenDir = fc.getSelectedFile();
  
   
     return chosenDir; 
       
  }
       public String getSavedTargetBrowser()
  {
      String ret_val = "Chrome";
       if (mainAppController.TheBrowserBotsConfig.hasKeyValue("target_browser"))
           {
              ret_val = mainAppController.TheBrowserBotsConfig.getKeyValue("target_browser");
           }
       return ret_val;
  }

  public void setSavedTargetBrowser(String in_targetBrowser)
  {
      mainAppController.TheBrowserBotsConfig.setKeyValue("target_browser", in_targetBrowser);
  }
 public void Refresh()
 {
     jButtonRefresh.setVisible(false);
     ShowFileCloudWindow();
  
 }
  public void ShowFileCloudWindow()
  {
         if ("".equals(mainAppController.loginName))
     {
 ShowHTMLWindow(rootURL + "/browse_files.php?app_id="+mainAppController.APPID+"&version="+mainAppController.ProgramVersion);
     }
     else
     {
         String URLwithLogin = rootURL + "/browse_files.php?app_id="+mainAppController.APPID+ "&loginName=" + mainAppController.loginName + "&loginPassword=" + mainAppController.loginPassword + "&version=" + mainAppController.ProgramVersion;
         
         ShowHTMLWindow(URLwithLogin);
          
     }
  }
  public void UpdateWebView (String url)
  {
           try
      {
        
      URLConnection connection = new URL(url).openConnection();
connection.setDoOutput(true);
  BufferedReader in = new BufferedReader(new InputStreamReader(
                                   ((HttpURLConnection) connection).getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
          HTML_TO_SEND += inputLine;
        in.close();
      
      }
      catch(Exception ex)
      {
          HTML_TO_SEND = "Unable to connect to TheBrowserBots.com.";
   //        webviewREF.getEngine().loadContent(HTML_TO_SEND);
      webEngine.loadContent(HTML_TO_SEND);
          System.out.println("Exception browsing cloud: " + ex.toString());
            LoadingLabel.setVisible(false);
          
         
      }
   if (HTML_TO_SEND=="Unable to connect to TheBrowserBots.com.")
{
 //   webviewREF.getEngine().loadContent(HTML_TO_SEND);
    webEngine.loadContent(HTML_TO_SEND);
    LoadingLabel.setVisible(false);
    jButtonRefresh.setVisible(true);
}
else
{

     
                webEngine.load(url);

  
}
  }
  public class Debug {
    public void print(final Object text) {
        System.err.println(text);
    }
}
       public void ShowHTMLWindow(String url)
  {
 
      try
      {
         
      URLConnection connection = new URL(url).openConnection();
connection.setDoOutput(true);
  BufferedReader in = new BufferedReader(new InputStreamReader(
                                   ((HttpURLConnection) connection).getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
          HTML_TO_SEND += inputLine;
        in.close();
      
      }
      catch(Exception ex)
      {
        HTML_TO_SEND = "Unable to connect to TheBrowserBots.com.";
  
        System.out.println("Exception browsing cloud: " + ex.toString());
          LoadingLabel.setVisible(false);
      }
    

 
 
   Platform.runLater(new Runnable() {
            @Override
            public void run() {
             
     browser2 = new WebView();
       javaAppBridge = new JavaApp(browser2.getEngine());   
webEngine = javaAppBridge.CloudEngine;
 
 webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<State>() {
    @Override
      public void changed(ObservableValue<? extends State> observableValue, State oldState, State newState) {
        if (newState == State.SUCCEEDED) {
          
                      JSObject win
                                = (JSObject) webEngine.executeScript("window");
                        win.setMember("app", javaAppBridge);  
                 
                }
                  
                }
            });


if (HTML_TO_SEND=="Unable to connect to TheBrowserBots.com.")
{
    webEngine.loadContent(HTML_TO_SEND);
    
}
else
{
 //  UpdateWebView(url);
  webEngine.load(url);
}
Scene scene = new Scene(browser2);

        fxPanel.setScene(scene);
   LoadingLabel.setVisible(false);
  

   
  webEngine.locationProperty().addListener(new ChangeListener<String>() {
  @Override public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
   
      if (newLoc.contains("serve_datafile"))
      {

  String[] filename_parts = newLoc.split("\\?");
  String[] queryvalues = filename_parts[1].split("&");
  String[] filename_value = queryvalues[0].split("=");
  String[] file_idvalue = queryvalues[1].split("=");
  String[] filedate_value = queryvalues[2].split("=");
  String file_id = file_idvalue[1];
  String filename = filename_value[1];
  String file_date = filedate_value[1];

  try
  {
filename = URLDecoder.decode(filename_value[1], "UTF-8");
  }
  catch (Exception ex)
  {
     System.out.println("Exception when decoding HTML: " +ex.toString());
  }
 
  String[] filenameparts = filename.split("\\.");
    int lastbit_index = filenameparts.length-1;
  String ext = filenameparts[lastbit_index];
  filename = filenameparts[0];
  String extension = ".csv";
  if ("browsermation".equals(ext))
  {
 extension = ".browsermation";
  }
  File thisFile = new File(USERCLOUDDIR+ File.separator +filename+extension);
                    if (thisFile.exists())
             {
                  Boolean checkdate = false;
                 if ("list_id".equals(file_idvalue[0]))
                 {
                     String listkey = "listid"+file_id;
             checkdate = CheckIfFileIsNew(file_date, listkey);
                 }
                 else
                 {
               checkdate = CheckIfFileIsNew(file_date, file_id);      
                 }
             if (checkdate)
             {
            
  
    
 //   mainAppController.RunPeteyRun(filename);
  if ("list_id".equals(file_idvalue[0]))
  {
  String list_key = "listid"+file_id;
  UpdateCloudConfig(list_key, file_date);
  }
  else
  {
         try {
          
         
    org.apache.commons.io.FileUtils.copyURLToFile(new URL(newLoc), new File(USERCLOUDDIR+ File.separator +filename+extension));
  UpdateCloudConfig(file_id, file_date);
    //   cloudFrame.dispose();
} catch (Exception x) { System.out.println ("Exception downloading file" + x.toString()); }
  
             }
  }
       }
             else
             {
       
         try{
   org.apache.commons.io.FileUtils.copyURLToFile(new URL(newLoc), new File(USERCLOUDDIR+ File.separator +filename+extension));
   UpdateCloudConfig(file_id, file_date);
         }
          catch (Exception x) { System.out.println ("Exception downloading file" + x.toString()); }
  
             
              
    
 //   mainAppController.RunPeteyRun(filename);
 //  Platform.exit();
  //   cloudFrame.dispose();

      }
      }
  }
});          

            }
   });   
JPanel topPanel = new JPanel();
FlowLayout newFlow = new FlowLayout();
topPanel.setLayout(newFlow);
// mainApp.LoadNameAndPassword();
mainAppController.LookUpUser(mainAppController.loginName, mainAppController.loginPassword);
 
if (mainAppController.user_id>0)
{
 loginLabelText = new JLabel("Welcome " + mainAppController.loginName + " you are currently logged in. ");   
 loginButton = new JButton("Switch user"); 
addloginButtonActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
LoginDialogLauncher();
  
        }
      }
    );
  topPanel.add(loginLabelText);
  topPanel.add(loginButton);
}
else
{
loginLabelText = new JLabel("You are not logged in. Login/Register to view high level access files: ");
//JLabel loginLabelName = new JLabel ("Name:");
//JLabel loginLabelPassword = new JLabel ("Password:");
//JTextField loginFieldName = new JTextField("", 10);
//JPasswordField loginFieldPassword = new JPasswordField("",10);
 topPanel.add(loginLabelText);
loginButton = new JButton("Login"); 
addloginButtonActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
LoginDialogLauncher();
  
        }
      }
    );
        
addjButtonRefreshActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
Refresh();
  
        }
      }
    );

jButtonRefresh.setVisible(true);
// topPanel.add(loginLabelName);
// topPanel.add(loginFieldName);
// topPanel.add(loginLabelPassword);
// topPanel.add(loginFieldPassword);
 topPanel.add(loginButton);
 topPanel.add(jButtonRefresh);
}

 
 
            fxPanel.setVisible(true);
fxPanel.setSize(800,800);

mainPanel = new JPanel(new BorderLayout());

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(fxPanel, BorderLayout.CENTER);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    mainPanel.setVisible(true);
 
  
    cloudFrame.getContentPane().removeAll();
    cloudFrame.add(mainPanel);
    cloudFrame.setSize(800, 800);
    cloudFrame.setVisible(true);   
  }  
    
    
    public void UpdateCloudConfig(String file_id, String file_date)
    {
   
      Properties applicationProps = new Properties();
      
      try
{
    Boolean file_exists = false;
    
    File f = new File(THEBROWSERBOTSCLOUDFILEPROPERTIES);
if(f.exists() && !f.isDirectory()) { 
   file_exists = true;
}
if (file_exists == false)
{
    CreateCloudConfigFile();
}
      FileInputStream input = new FileInputStream(THEBROWSERBOTSCLOUDFILEPROPERTIES);
  
applicationProps.load(input);
input.close();
}
      catch (Exception ex)
      {
         System.out.println ("exception loading cloud files config: " + ex.toString()); 
      }
      applicationProps.setProperty(file_id, file_date);     
   
           try {
       FileWriter writer = new FileWriter(THEBROWSERBOTSCLOUDFILEPROPERTIES);
    applicationProps.store(writer, "TheBrowserBots_cloud_files");
    writer.close();
         
  
   
} 

    catch (Exception e) {
			System.out.println("Exception writing login details: " + e);
		}      
  }  
 
     public final void CreateCloudConfigFile()
  {
   
      File newconfig = new File(THEBROWSERBOTSCLOUDFILEPROPERTIES);
    

              try {
  
    FileWriter writer = new FileWriter(newconfig);
   cloudProps.store(writer, "TheBrowserBots_cloud_files");
    writer.close();
} 

    catch (Exception e) {
			System.out.println("Exception writing cloud file config: " + e);
		}    
      
  }  
 
   public Boolean CheckIfFileIsNew( String file_date, String file_id)
   {
       Boolean ret_val = false;
        
      Properties applicationProps = new Properties();
      String stored_file_date = "";
       try
{
    Boolean file_exists = false;
    
    File f = new File(THEBROWSERBOTSCLOUDFILEPROPERTIES);
if(f.exists() && !f.isDirectory()) { 
   file_exists = true;
}
if (file_exists == false)
{
    CreateCloudConfigFile();
}
      FileInputStream input = new FileInputStream(THEBROWSERBOTSCLOUDFILEPROPERTIES);
  
applicationProps.load(input);
input.close();
}
      catch (Exception ex)
      {
         System.out.println ("exception loading cloud files config: " + ex.toString()); 
      }  
       
       if (applicationProps.containsKey(file_id))
       {
           stored_file_date = applicationProps.getProperty(file_id);
       }
       if (!file_date.equals(stored_file_date))
       {
           ret_val = true;
       }
       return ret_val;
       
   }
    public class JavaApp {
    final WebEngine CloudEngine;
    Integer number_of_addons;
    Integer browsermator_file_id;
    Integer map_file_id;
    Integer default_list_id;
    String browsermator_file_name;
    String map_file_name;
    String default_list_name;
    String chosen_list_name;
    Integer browsermator_file_date;
    Integer map_file_date;
    Integer default_list_date;
    Integer[] addon_ids;
    String[] addon_filenames;
    Integer[] addon_filedates;
    
    Integer number_of_site_logins;
    String[] site_login_names;
    String[] site_login_usernames;
    String[] site_login_passwords;
    String[] site_login_username_indexes;
    String[] site_login_password_indexes;
    UserSiteLoginFrame UserSiteLoginFrame;
    UserSiteLoginPanel[] UserSiteLoginPanels;
    
    UserSingleInputPanel[] UserSingleInputPanels;
    UserSingleInputFrame UserSingleInputFrame;
    
    SiteLogins theseSiteLogins;
    String[] sitename_list;  
    String[] single_input_indexes;
    SingleInput theseSingleInputs;
    String[] singleinputnames_list;
          String[] single_input_index_list;
          String[] username_index_list;
          String[] password_index_list;
          String lastChosenList;
        JavaApp(WebEngine in_cloudREF)
        {
        CloudEngine = in_cloudREF;
        number_of_site_logins = -1;
        number_of_addons = -1;
        browsermator_file_id = -1;
        map_file_id = -1;
        default_list_id = -1;
        browsermator_file_name = "";
        map_file_name = "";
        default_list_name = "";
        chosen_list_name = "";
        browsermator_file_date = -1;
        map_file_date = -1;
        default_list_date = -1;
     //   addon_ids = new Integer[];
    //    addon_filenames = new String[0];
    //    addon_filedates = new Integer[0];
        sitename_list = new String[0];
        singleinputnames_list = new String[0];
     
        }
         public void openinsystembrowser(String in_url)
         {
             if (!"".equals(in_url))
             {
                   if (Desktop.isDesktopSupported()) {
                 try
                 {
                 URI thisURI = new URI(in_url);
    Desktop.getDesktop().browse(thisURI);
      System.exit(0);
                 }
                 catch (Exception ex)
                 {
                     System.out.println("Exception opening system browser link: " + ex.toString());
                 }
                   }
             }
         }
         public void launchuserlistwindow(String in_list_id, String in_default_list_name, String browsermator_file_name)
         {
              File currentList=null;
              default_list_id = Integer.parseInt(in_list_id);
              File defaultList = new File(USERCLOUDDIR+ File.separator +in_default_list_name);
                  if (!defaultList.exists())
              {
                   String url = rootURL + "/serve_datafile.php?filename=" + defaultList.getName() + "&list_id=" + default_list_id + "&date_added="+ default_list_date;
               CloudEngine.load(url);         
              } 
                     if (mainAppController.TheBrowserBotsConfig.hasKeyValue(browsermator_file_name+ "-lastChosenList"))
               {
            chosen_list_name =  mainAppController.TheBrowserBotsConfig.getKeyValue(browsermator_file_name+"-lastChosenList");
               }
          
               
             if (!chosen_list_name.equals(""))
             {
              currentList =  new File(chosen_list_name);
             }
             else
             {
              currentList = new File(USERCLOUDDIR+ File.separator +in_default_list_name);
             }
          
         //   File currentList = new File(in_default_list_name);
       //  System.out.println(browsermator_file_name);
             UserListData uListData = new UserListData(mainAppController.loginName, browsermator_file_name,defaultList, currentList);
             uListData.setSearchTermsToFile(currentList.getAbsolutePath());
             uListData.setListDescription("Placeholder");
             uListData.getsetUserListFiles();
             UserListView uListView = new UserListView(uListData);
             uListView.initComponents();
            uListView.AddjButtonNewActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
             uListView.New();
     
  
        }
             });
            uListView.AddjButtonDeleteActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
             uListView.Delete();
     
  
        }
             });
             uListView.AddjButtonEditActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
        String editCommand = evt.getActionCommand();
           switch (editCommand)
           {
               case "Edit As New":
                  uListView.EditAsNewMode();
                  break;
               case "Edit":
                   uListView.EditMode();
                   break;
           }
           
     
  
        }
             });
                uListView.AddjButtonNextActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
 
        uListView.Next();
  
        }
             });
                        uListView.AddjButtonPrevActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
 
        uListView.Previous();
  
        }
             });
               uListView.AddjButtonSelectListActionListener(new ActionListener()
             {
                       public void actionPerformed(ActionEvent evt)
        { 
         String actionText = evt.getActionCommand();
         switch (actionText)
         {
             case "Select":
              chosen_list_name  =   uListView.SelectList();
                mainAppController.TheBrowserBotsConfig.setKeyValue(browsermator_file_name+"-lastChosenList", chosen_list_name);
              uListView.jFrameListFrame.dispose();
              
                 break;
             case "Save":
                 uListView.SaveLocalList();
//                  chosen_list_name  =   uListView.SelectList();

//              uListView.jFrameListFrame.dispose();
                 uListView.BrowseMode();
                 break;
         }
             
    
  
        }
             });
          uListView.UpdateDisplay();
         }
           public void sendlogindata(String site_names,String username_indexes, String password_indexes)
        {
          
         if (site_names.contains(","))
         {
         sitename_list = site_names.split(",");   
         }
         else if (!site_names.equals(""))
         {
            sitename_list = new String[1];
            
             sitename_list[0] = site_names;
         }
         else
         {
             sitename_list = new String[0];
         }
         if (username_indexes.contains(","))
         {
         username_index_list = username_indexes.split(",");
         }
         else if (!username_indexes.equals(""))
         {
             username_index_list = new String[1];
             username_index_list[0] = username_indexes;
         }
             
         if (password_indexes.contains(","))
         {
            
         password_index_list = password_indexes.split(",");
         }
         else if (!password_indexes.equals(""))
         {
               password_index_list = new String[1];
             password_index_list[0] = password_indexes;
         }
         if (sitename_list.length>0)
         {
          theseSiteLogins = new SiteLogins(sitename_list, username_index_list, password_index_list);
         
          Integer site_count =  sitename_list.length;
        
          theseSiteLogins.CreateSitePanels();
          site_login_names = new String[site_count];
          site_login_username_indexes = new String[site_count];
          site_login_password_indexes = new String[site_count];
          site_login_usernames = new String[site_count];
          site_login_passwords = new String[site_count];
      
         
          UserSiteLoginPanels = new UserSiteLoginPanel[site_count];
         
         }
          
        }
         public void sendlogindata2(String site_names, String single_inputnames, String username_indexes, String password_indexes)
        {
            if (single_inputnames.contains(","))
            {
                singleinputnames_list = single_inputnames.split(","); 
            }
         if (site_names.contains(","))
         {
         sitename_list = site_names.split(",");   
         }
         if (username_indexes.contains(","))
         {
         username_index_list = username_indexes.split(",");
         }
         if (password_indexes.contains(","))
         {
         password_index_list = password_indexes.split(",");
         }
         if (singleinputnames_list.length>0)
         {
             theseSingleInputs = new SingleInput(singleinputnames_list, single_input_index_list);
             Integer input_count = singleinputnames_list.length;
             
             theseSingleInputs.CreateSingleInputPanels();
             singleinputnames_list = new String[input_count];
             single_input_index_list = new String[input_count];
            
             UserSingleInputPanels = new UserSingleInputPanel[input_count];
             
         }
         if (sitename_list.length>0)
         {
          theseSiteLogins = new SiteLogins(sitename_list, username_index_list, password_index_list);
         
          Integer site_count =  sitename_list.length;
        
          theseSiteLogins.CreateSitePanels();
          site_login_names = new String[site_count];
          site_login_username_indexes = new String[site_count];
          site_login_password_indexes = new String[site_count];
          site_login_usernames = new String[site_count];
          site_login_passwords = new String[site_count];
      
         
          UserSiteLoginPanels = new UserSiteLoginPanel[site_count];
         
         }
          
        }
      
        public void setnumberofaddons(String in_numberof)
        {
          
             number_of_addons = Integer.parseInt(in_numberof);  
        }
    
        public void setpackageids(String in_browsermatorid, String in_mapfileid, String in_defaultlistid, String in_addon_ids)
        {
        
            browsermator_file_id = Integer.parseInt(in_browsermatorid);
            map_file_id = Integer.parseInt(in_mapfileid);
            default_list_id = Integer.parseInt(in_defaultlistid);
            addon_ids = new Integer[number_of_addons];
           
            String[] addon_id_list = in_addon_ids.split(":");
            Integer x = 0;
            for (String id_to_add: addon_id_list)
            {
                addon_ids[x] = Integer.parseInt(id_to_add);
                        x++;
                     
            }
            
            
        }
        public void setpackagefilenames(String in_browsermatorfilename, String in_mapfilename, String in_defaultlistfilename, String in_addonnames)
        {
         
            browsermator_file_name = in_browsermatorfilename;
            map_file_name = in_mapfilename;
            default_list_name = in_defaultlistfilename;
            addon_filenames = new String[number_of_addons];
              String[] addon_file_list = in_addonnames.split(":");
            Integer x = 0;
            for (String name_to_add: addon_file_list)
            {
         
                addon_filenames[x] = name_to_add;
                x++;
            }
            
        }
        public void setpackagefiledates(String in_browsermatorfiledate, String in_mapfiledate, String in_defaultlistfiledate, String in_addonfiledates)
        {
        
            browsermator_file_date = Integer.parseInt(in_browsermatorfiledate);
            map_file_date = Integer.parseInt(in_mapfiledate);
            default_list_date = Integer.parseInt(in_defaultlistfiledate);
            addon_filedates = new Integer[number_of_addons];
            
            
            String[] addon_filedate_list = new String[number_of_addons];
          
              addon_filedate_list = in_addonfiledates.split(":");
            
              
            Integer x = 0;
            for (String filedate_to_add: addon_filedate_list)
            {
                if (!"".equals(filedate_to_add))
                {
                addon_filedates[x] = Integer.parseInt(filedate_to_add);
                }
                x++;
            }
            openpackage(CloudEngine);
        }
        public void openpackage(WebEngine CloudEngine)
        {
     
         int counter = 0;
       
                  if (sitename_list.length>0)
                  {
          for (String sitename: theseSiteLogins.site_names)
          {
        
            String username = GetSiteLoginUsername(sitename);
            String password = GetSiteLoginPassword(sitename);
            site_login_names[counter] = sitename;
              site_login_username_indexes[counter]= username_index_list[counter];
              site_login_password_indexes[counter] = password_index_list[counter];
           
           
           
            String username_index = username_index_list[counter];
            String password_index = password_index_list[counter];
              UserSiteLoginPanel thisUserSiteLoginPanel = new UserSiteLoginPanel(sitename, username, username_index, password, password_index);
                thisUserSiteLoginPanel.CreatePanel();
             UserSiteLoginPanels[counter] = thisUserSiteLoginPanel;
            
          
           
            counter++;
          }
       
                  }
            String url = rootURL + "/serve_datafile.php?filename=" + browsermator_file_name + "&file_id=" + browsermator_file_id + "&date_added="+ browsermator_file_date;
                      CloudEngine.load(url);
                      if (map_file_id>0)
                      {
             url = rootURL + "/serve_datafile.php?filename=" + map_file_name + "&file_id=" + map_file_id + "&date_added="+map_file_date;
              CloudEngine.load(url);
                      }
               if (default_list_id>0)
               {
               url = rootURL + "/serve_datafile.php?filename=" + default_list_name + "&list_id=" + default_list_id + "&date_added="+ default_list_date;
               CloudEngine.load(url);              
               }
           Integer counter2 = 0;
           for (Integer id: addon_ids)
           {
                 if (id>0)
                 {
               String filename = addon_filenames[counter2];
             
                   
               String id_value = id.toString();
             
              String date_added = addon_filedates[counter2].toString();
              
          url = rootURL + "/serve_datafile.php?filename=" + filename + "&file_id=" + id_value + "&date_added="+date_added;
                      CloudEngine.load(url);
                 }
                       counter2++;
           }

   //      Platform.exit();
     //     cloudFrame.dispose();
   
     File browserMationFile = new File(USERCLOUDDIR + File.separator +browsermator_file_name);
  
        MapFile = new File(USERCLOUDDIR + File.separator + map_file_name);
      File listFile = null;
        if (chosen_list_name.equals(""))
      {
   listFile = new File(USERCLOUDDIR + File.separator + default_list_name);
  
      }
        else
        {
          
            
             
            listFile = new File(chosen_list_name);
        }
     if (map_file_name.contains(".csv"))
     {
    AddOSTypeToMapFile(MapFile);
     if (!default_list_name.equals("none"))
     {
     if (!chosen_list_name.equals(""))
     {
       File newFile = new File(chosen_list_name);
            
        
    ReplaceDefaultListNamesInMapFile(MapFile, default_list_name, newFile);
     }
     }
     }
   //      if (singleinputnames_list.length>0)
   //               {
  //             if (UserSingleInputPanels.length>0)
   //       {
  
 //     }
    
 //         String URLwithLogin = rootURL + "/browse_files.php?app_id="+APPID+ "&loginName=" + mainAppController.loginName + "&loginPassword=" + mainAppController.loginPassword;

 //       CloudEngine.load(URLwithLogin);
 
           
               
           if (sitename_list.length>0)
                  {
               if (UserSiteLoginPanels.length>0)
          {
          UserSiteLoginFrame thisFrame = new UserSiteLoginFrame(UserSiteLoginPanels);
      thisFrame.InitializeFrame();

          thisFrame.addjButtonOKActionListener(  new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
           
        //    File tempMapFile = MapFile;
              String[] sitenames = new String[UserSiteLoginPanels.length];
               String[] usernames = new String[UserSiteLoginPanels.length];
                String[] username_indexes = new String[UserSiteLoginPanels.length];
                 String[] passwords = new String[UserSiteLoginPanels.length];
                  String[] password_indexes = new String[UserSiteLoginPanels.length];
            int panel_counter = 0;      
        for (UserSiteLoginPanel thisPanel: UserSiteLoginPanels)
        {
            sitenames[panel_counter] = thisPanel.GetSiteName();
            usernames[panel_counter] = thisPanel.GetSiteUsername();
            String thisUsernameKeyString = thisPanel.GetSiteName() +"-username";
            passwords[panel_counter] = thisPanel.GetSitePassword();
            String thisPasswordKeyString = thisPanel.GetSiteName()+"-password";
            mainAppController.TheBrowserBotsConfig.setKeyValue(thisUsernameKeyString, thisPanel.GetSiteUsername());
         
            try
            {
            String machineID = mainAppController.TheBrowserBotsConfig.ReturnMachineSerialNumber();
             
             String savePass = Protector.encryptLocal(thisPanel.GetSitePassword(), machineID);
              mainAppController.TheBrowserBotsConfig.setKeyValue(thisPasswordKeyString, savePass);
            }
            catch (Exception ex)
            {
                System.out.println ("Exception getting machine ID");
            }
      
            username_indexes[panel_counter]= thisPanel.getUsernameIndex();
            password_indexes[panel_counter] = thisPanel.getPasswordIndex();
     
           
     
      panel_counter++;
           
        }
           AddLoginToMapFile(sitenames, usernames, username_indexes, passwords, password_indexes);
    
     //                  PeteysPackage Boner = new PeteysPackage(browserMationFile, MapFile, listFile, mainAppController, CloudEngine);
  
 //    Boner.RunPeteyRun();

 thisFrame.dispose();
        }
      }
    );
          while(thisFrame.isVisible() == true){
        try
        {
Thread.sleep(200);

        }
        catch (InterruptedException e)
                {
                    System.out.println("pause exception: " + e.toString());
                    
                }
          }
     //     thisFrame.dispose();

  
 //         String URLwithLogin = rootURL + "/browse_files.php?app_id="+APPID+ "&loginName=" + mainAppController.loginName + "&loginPassword=" + mainAppController.loginPassword;

 //       CloudEngine.load(URLwithLogin);
          }
        
                  }
           else
           {
       
        
           }
    //           MapFile.deleteOnExit();
                            TheBrowserBotsPackage Boter = new TheBrowserBotsPackage(browserMationFile, MapFile, listFile, mainAppController, CloudEngine);
  
                   
     Boter.RunBot();
 

             
    
     
        }
      
        public String updateFile(String button_id, String in_filename, String in_fileid)
        {
            
            //to do... switch case based on button_id... need more than fileid?
            Boolean isList = false;
            if (button_id.equals("updateDefaultListLink"))
            {
             isList = true;   
            }
            String ret_val = "cancelled";
          File newfile = BrowseForFile();
 


 
    if (newfile!=null && !newfile.getName().contains(" "))
 {
 
 
SendFileThread UpdateSendFileREF = new SendFileThread(mainAppController.ProgramVersion, mainAppController.APPID, CloudEngine, newfile, mainAppController.loginName, mainAppController.loginPassword, in_fileid, in_filename, isList);
 UpdateSendFileREF.execute();  
 ret_val = "filechosen";

 }    
            
            return ret_val;
            
        }
 
}
      public void AddLoginToMapFile(String[] sitenames, String[] usernames, String[] username_indexes, String[] passwords, String[] password_indexes)
        {
          
             String tempFileName = "temp_map";
       
 
         try {
                    FileChannel src = new FileInputStream(MapFile).getChannel();
                    
    File temp_map_file = File.createTempFile(tempFileName, ".csv");

    FileChannel dest = new FileOutputStream(temp_map_file).getChannel();
 
  dest.transferFrom(src, 0, src.size());
  src.close();
  dest.close();
  MapFile.delete();
   BufferedWriter writer = new BufferedWriter(new FileWriter(temp_map_file, true)); 
   int site_counter = 0;
     for (String sitename: sitenames)
     {
      
        writer.append( username_indexes[site_counter] + ",changefield,2," + usernames[site_counter] + System.getProperty("line.separator"));
                    writer.append(password_indexes[site_counter] + ",changefield,2," + passwords[site_counter] + System.getProperty("line.separator"));
                
                    site_counter++;
     }
//         writer.append("0,deleteme,0,0"+System.getProperty("line.separator"));   
     writer.close();
                     MapFile = temp_map_file;
        //             temp_map_file.deleteOnExit();
    } 
    catch (Exception ex)
    {
        System.out.println ("Exception writing temp file for map: " + ex.toString());
     
 
    }
          //  File thisMapFile = new File(USERCLOUDDIR + File.separator + map_file_name);
       
       
        }
 
        public String GetSingleInputValue(String input_name)
        {
               String ret_val = "";
            String inputvalue_key = input_name+"-inputvalue";
           if (mainAppController.TheBrowserBotsConfig.hasKeyValue(inputvalue_key))
           {
              ret_val = mainAppController.TheBrowserBotsConfig.getKeyValue(inputvalue_key);
           }
           else
           {
               ret_val = "";
           }
           return ret_val;
        }
        public String GetSiteLoginUsername(String sitename)
        {
            String ret_val = "";
            String username_key = sitename+"-username";
           if (mainAppController.TheBrowserBotsConfig.hasKeyValue(username_key))
           {
              ret_val = mainAppController.TheBrowserBotsConfig.getKeyValue(username_key);
           }
           else
           {
               ret_val = "";
           }
           return ret_val;
        }
              public String GetSiteLoginPassword(String sitename)
        {
            String ret_val = "";
            String password_key = sitename+"-password";
           if (mainAppController.TheBrowserBotsConfig.hasKeyValue(password_key))
           {
             
             String temp_ret_val = mainAppController.TheBrowserBotsConfig.getKeyValue(password_key);
             try
             {
                 String machineID = mainAppController.TheBrowserBotsConfig.ReturnMachineSerialNumber();
                 ret_val = Protector.decryptLocal(temp_ret_val, machineID);
             }
             catch (Exception ex)
             {
                 
             }
           }
           else
           {
               ret_val = "";
           }
           return ret_val;
        }
    public void addjButtonRefreshActionListener(ActionListener listener)
    {
        jButtonRefresh.addActionListener(listener);
    }
           public void addloginButtonActionListener(ActionListener listener)
{
    loginButton.addActionListener(listener);
}
    
    public void LoginDialogLauncher()
       {
        //   cloudFrame.dispose();
            Login_Register_Dialog loginDialog = new Login_Register_Dialog();
     loginDialog.setLoginName(mainAppController.loginName);
  loginDialog.setPassword(mainAppController.loginPassword);
     loginDialog.addjTextFieldConfirmPasswordDocListener(
             new DocumentListener()
           {
@Override
       public void changedUpdate(DocumentEvent documentEvent) {
       loginDialog.ComparePasswordFields();
       loginDialog.AllRequiredCheck();
      }
@Override
      public void insertUpdate(DocumentEvent documentEvent) {
      loginDialog.ComparePasswordFields();
      loginDialog.AllRequiredCheck();
      }
@Override
      public void removeUpdate(DocumentEvent documentEvent) {
     loginDialog.ComparePasswordFields();
     loginDialog.AllRequiredCheck();
      }
      }
                 );
 loginDialog.addjTextFieldPasswordDocListener(
             new DocumentListener()
           {
@Override
       public void changedUpdate(DocumentEvent documentEvent) {
       loginDialog.ComparePasswordFields();
       loginDialog.AllRequiredCheck();

      }
@Override
      public void insertUpdate(DocumentEvent documentEvent) {
      loginDialog.ComparePasswordFields();
      loginDialog.AllRequiredCheck();
      }
@Override
      public void removeUpdate(DocumentEvent documentEvent) {
     loginDialog.ComparePasswordFields();
     loginDialog.AllRequiredCheck();
      }
      }
                 );
    
     loginDialog.addjTextFieldLoginNameDocListener(
             new DocumentListener()
           {
@Override
       public void changedUpdate(DocumentEvent documentEvent) {
      
       loginDialog.AllRequiredCheck();

      }
@Override
      public void insertUpdate(DocumentEvent documentEvent) {
    
      loginDialog.AllRequiredCheck();
      }
@Override
      public void removeUpdate(DocumentEvent documentEvent) {
    
     loginDialog.AllRequiredCheck();
      }
      }
                 );
     loginDialog.addjTextFieldEmailDocListener(
             new DocumentListener()
           {
@Override
       public void changedUpdate(DocumentEvent documentEvent) {
      
       loginDialog.AllRequiredCheck();
       loginDialog.ValidateEmailAddress();
      }
@Override
      public void insertUpdate(DocumentEvent documentEvent) {
    
      loginDialog.AllRequiredCheck();
      loginDialog.ValidateEmailAddress();
      }
@Override
      public void removeUpdate(DocumentEvent documentEvent) {
    
     loginDialog.AllRequiredCheck();
     loginDialog.ValidateEmailAddress();
      }
      }
                 );
      loginDialog.addjButtonLoginModeActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
        loginDialog.LoginMode();
 
  
        }
      }
    );
   
       loginDialog.addjButtonRegisterModeActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
        loginDialog.RegisterMode();
 
  
        }
      }
    );
        loginDialog.addjButtonRecoverModeActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
        loginDialog.RecoverMode();
 
  
        }
      }
    );
          loginDialog.addjTextFieldEmailActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e){

                        //statements!!!

                }});
     loginDialog.addjButtonLoginActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 loginDialog.setStatus("");
       mainAppController.LookUpUser(loginDialog.getLoginName(), loginDialog.getPassword());
  if (mainAppController.user_id >0 )
   {
       

                           mainAppController.SaveNameAndPassword(loginDialog.getLoginName(), loginDialog.getPassword());
                  //   UploadFile(STAppFrame);
             
                     loginDialog.dispose();
                     
                   ShowFileCloudWindow();
              
                
    loginDialog.setStatus("");
   }
  else
  {
      loginDialog.setStatus("Login has failed.");
  }
  
        }
      }
    );
        loginDialog.addjTextFieldPasswordActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
       if ("login".equals(loginDialog.mode) && loginDialog.isActive )
       {
    loginDialog.setStatus("");
       mainAppController.LookUpUser(loginDialog.getLoginName(), loginDialog.getPassword());
  if (mainAppController.user_id >0 )
   {
       

                           mainAppController.SaveNameAndPassword(loginDialog.getLoginName(), loginDialog.getPassword());
                  //   UploadFile(STAppFrame);
             
                     loginDialog.dispose();
                     
                   ShowFileCloudWindow();
              
                
    loginDialog.setStatus("");
   }
  else
  {
      loginDialog.setStatus("Login has failed.");
  }
       }
  
        }
      }
    );
        loginDialog.addjTextFieldEmailActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
       if ("recover".equals(loginDialog.mode)&& loginDialog.isActive)
       {
           loginDialog.setStatus("");
     String statustext =  mainAppController.RecoverPassword(loginDialog.getEmail());
     loginDialog.setStatus (statustext);
     
       }
       if ("register".equals(loginDialog.mode)&& loginDialog.isActive)
       {
           loginDialog.setStatus("");
      String statustext =  mainAppController.RegisterUser(loginDialog, loginDialog.getLoginName(), loginDialog.getEmail(), loginDialog.getPassword());
      loginDialog.setStatus(statustext);
       }
    
    
  
        }
      }
    );
        
     loginDialog.addjButtonRegisterActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 loginDialog.setStatus("");
       String statustext =  mainAppController.RegisterUser(loginDialog, loginDialog.getLoginName(), loginDialog.getEmail(), loginDialog.getPassword());
        loginDialog.setStatus(statustext);
  
        }
      }
    );
     loginDialog.addjButtonRecoverPasswordActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt)
        { 
 
       String status =   mainAppController.RecoverPassword(loginDialog.getEmail());
         loginDialog.setStatus(status);
  
        }
      }
    );
       }   
  public File BrowseForFile()
  {
       

        final JFileChooser fc = new JFileChooser();
      
// fc.setMultiSelectionEnabled(false);

 FileNameExtensionFilter filefilter = new FileNameExtensionFilter("Browsermator or CSV file (*.browsermation, *.csv)","browsermation", "csv");


      String lastused_open_dir = mainAppController.TheBrowserBotsConfig.getKeyValue("lastused_open_dir");
      if (lastused_open_dir!=null)
      {
      fc.setCurrentDirectory(new File(lastused_open_dir));
      }
    fc.setFileFilter(filefilter);
fc.setPreferredSize(new Dimension(800,600));
int returnVal = fc.showOpenDialog(mainAppController);
       File chosenDir = fc.getCurrentDirectory();
 mainAppController.TheBrowserBotsConfig.setKeyValue ("lastused_open_dir", chosenDir.getAbsolutePath());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();   
  

  
     return file; 
    }

           
            else
            {
               
                return null;
            }
        
            
  }
   public void setWindowProps(FileInputStream input)
          {
              try
              {
         
           mainAppController.TheBrowserBotsConfig.applicationProps.load(input);
              }
              catch (Exception ex)
              {
                  System.out.println ("Exception loading config : " + ex.toString() );
              }
                int winLocY =  Integer.parseInt( mainAppController.TheBrowserBotsConfig.applicationProps.getProperty("main_window_locationY", "0"));
   int winLocX =   Integer.parseInt( mainAppController.TheBrowserBotsConfig.applicationProps.getProperty("main_window_locationX", "0"));
   int winWidth =  Integer.parseInt( mainAppController.TheBrowserBotsConfig.applicationProps.getProperty("main_window_sizeWidth", "1200"));
   int winHeight = Integer.parseInt( mainAppController.TheBrowserBotsConfig.applicationProps.getProperty("main_window_sizeHeight", "802"));
      java.awt.Point startPosition = new java.awt.Point(winLocX, winLocY);
            if (isLocationInScreenBounds(startPosition) )
        {

    cloudFrame.setLocation(startPosition);
   cloudFrame.setSize(winWidth, winHeight);
        }
       
      else
      {
  int Width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
int Height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
cloudFrame.setSize(Width-300,Height-300);
   cloudFrame.setLocation(0,0);
  cloudFrame.setExtendedState( cloudFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
      }
          }
    public static boolean isLocationInScreenBounds(Point location) 
    {
      
      // Check if the location is in the bounds of one of the graphics devices.
    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
    Rectangle graphicsConfigurationBounds = new Rectangle();
    
    // Iterate over the graphics devices.
    for (int j = 0; j < graphicsDevices.length; j++) {
      
      // Get the bounds of the device.
      GraphicsDevice graphicsDevice = graphicsDevices[j];
      graphicsConfigurationBounds.setRect(graphicsDevice.getDefaultConfiguration().getBounds());
      
        // Is the location in this bounds?
      graphicsConfigurationBounds.setRect(graphicsConfigurationBounds.x, graphicsConfigurationBounds.y,
          graphicsConfigurationBounds.width, graphicsConfigurationBounds.height);
      if (graphicsConfigurationBounds.contains(location.x, location.y)) {
        
        // The location is in this screengraphics.
        return true;
        
      }
      
    }
    
    // We could not find a device that contains the given point.
    return false;
    
    }
   public Boolean CheckIfFileIsUnique(File fileToCheck)
   {
       
       return false;
   }
     public String getjComboBoxTargetBrowser()
  {
      String ret_val = "Chrome 49";
      ret_val = jComboBoxTargetBrowser.getSelectedItem().toString();
      return ret_val;
  }
  public void setjComboBoxTargetBrowser(String in_targetBrowser)
  {
     jComboBoxTargetBrowser.setSelectedItem(in_targetBrowser);
  }
   public void ReplaceDefaultListNamesInMapFile(File thisMapFile, String defaultListName, File chosenListFile)
   {
          if (!defaultListName.equals(chosenListFile.getName()))  
           {
           try
             {
        
             BufferedReader reader = new BufferedReader(new FileReader(thisMapFile));
             String line = "", oldtext = "";
             while((line = reader.readLine()) != null)
                 {
                 oldtext += line +  System.lineSeparator();
             }
             reader.close();
             // replace a word in a file
             //String newtext = oldtext.replaceAll("drink", "Love");
            
             //To replace a line in a file
             String replacement =  "%PTPUSERLIST%" + chosenListFile.getName();
             String newtext = oldtext.replaceAll("%PTPCLOUDDIR%" + defaultListName, replacement);
            
             FileWriter writer = new FileWriter(thisMapFile);
             writer.write(newtext);writer.close();
         }
         catch (Exception ioe)
             {
            System.out.println ("Exception while reading mapfile for replace: " + ioe.toString());
         }

           }
      
   
     }
   
      public void AddOSTypeToMapFile(File thisMapFile)
     {
         String new_targetBrowser;
         new_targetBrowser = getjComboBoxTargetBrowser();
         setSavedTargetBrowser(new_targetBrowser);
           mapFileList.clear();
         try
         {
  BufferedReader file = new BufferedReader(new FileReader(thisMapFile));
     String line;
   Boolean skipline = false;
        while ((line = file.readLine()) != null) {
             if (line.contains("0,silentmode,0,"))
           {
          skipline =false;
           }
           if (line.contains("0,changeOS,0,"))
           {
          skipline = true;
           }
           if (line.contains("0,changeOS,0," + mainAppController.OS))
           {
           skipline = true;
           }
             if (line.contains("0,changebrowser"))
           {
            skipline = true;
           }
             if (!skipline)
             {
             mapFileList.add(line);
             }
             skipline = false;
        }
      
   //    mapFileList.add("0,setpeteymode,0," + mainAppController.OS);
        mapFileList.add("0,changeOS,0," + mainAppController.OS);
        mapFileList.add("0,changebrowser,0,"+new_targetBrowser);
      
        }
         catch (Exception ex)
         {
             System.out.println ("Exception while reading mapfile: " + ex.toString());
                      }
       writeMapFile(thisMapFile, mapFileList);
   //   thisMapFile.deleteOnExit();
 //     MapFile.deleteOnExit();
   
     }
     public void writeMapFile(File in_mapfile, List<String> in_mapfilelist)
     {
        String tempFileName = in_mapfile.getName();
    File ret_file = in_mapfile;
         try
         {
      File newTempFile =  File.createTempFile(tempFileName, ".csv");
        
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(newTempFile, true)); 
          
             for (String thisentry: in_mapfilelist)
             {
             
         writer2.append(thisentry + System.getProperty("line.separator") );
               
             }
                writer2.append("0,deleteme,0,0"+System.getProperty("line.separator"));   
          writer2.close();
       ret_file = newTempFile;
   
         }
         catch (Exception ex)
         {
             System.out.println("Exception writing mapfile: " + ex.toString());
         }
    //   ret_file.deleteOnExit();
       MapFile = ret_file;
    //  newTempFile.renameTo(in_mapfile);
     }          
     public void CloseBot()
     {
// Refresh();

  System.exit(0);
  
     }
}
