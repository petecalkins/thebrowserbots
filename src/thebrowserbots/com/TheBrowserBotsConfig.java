/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;



import com.sun.javafx.PlatformUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;


public class TheBrowserBotsConfig {

      Properties applicationProps;
      FileInputStream input;
     FileWriter writer;
     String CONFIG_FILE_NAME;
     String USERDIR;
     String USERCLOUDDIR;
     String OperatingSystem;
     
      TheBrowserBotsConfig()
      {
          applicationProps = new Properties();
                 String bits = System.getProperty("sun.arch.data.model");
          OperatingSystem = "Other";
          CONFIG_FILE_NAME = "the_browserbots_config.properties";
        USERDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsAppFolder";
        USERCLOUDDIR = System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles";
          if (PlatformUtil.isWindows()){
        OperatingSystem = "Windows64"; 
         boolean is64bit = false;
      if (System.getProperty("os.name").contains("Windows")) {
    is64bit = (System.getenv("ProgramFiles(x86)") != null);
} else {
    is64bit = (System.getProperty("os.arch").contains("64"));
}
         if (!is64bit)
           {
            OperatingSystem = "Windows32";   
           }
      
        }
             if (PlatformUtil.isMac()){
        OperatingSystem = "Mac"; 
        }
              if (PlatformUtil.isLinux()){
        OperatingSystem = "Linux-64"; 
    
           if ("32".equals(bits))
           {
            OperatingSystem = "Linux-32";   
           }
        }     
              File file = new File(USERDIR);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
               File cloudfile = new File(USERCLOUDDIR);
        if (!cloudfile.exists()) {
            if (cloudfile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
             Boolean file_exists = false;
    
       File f = new File(USERDIR+ File.separator + CONFIG_FILE_NAME);
if(f.exists() && !f.isDirectory()) { 
   file_exists = true;
}
if (file_exists == false)
{
    CreateConfigFile();
}

 //     this.applicationProps = new Properties();    
        try (FileInputStream input = new FileInputStream(USERDIR + File.separator + CONFIG_FILE_NAME)) {
             applicationProps.load(input);
             
            
         }
         catch (Exception e)
         {
             System.out.println("error loading config:" + e.toString());
           
             
         }
      }
        public final void CreateConfigFile()
  {
  
      File newconfig = new File(USERDIR + File.separator + CONFIG_FILE_NAME);
      Properties newProps = new Properties();

       newProps.setProperty("main_window_locationY", "0");
      newProps.setProperty("main_window_locationX", "0");
      newProps.setProperty("main_window_sizeWidth", "800");
      newProps.setProperty("main_window_sizeHeight", "600");   
   
      
              try {
  

    
    FileWriter writer = new FileWriter(newconfig);
    newProps.store(writer, "thebrowserbots_settings");
    writer.close();
} 

    catch (Exception e) {
			System.out.println("Exception writing config: " + e);
		}    
      
  }
      public Boolean hasKeyValue(String key)
      {
          Boolean ret_val = false;
          if (applicationProps.containsKey(key))
          {
              ret_val = true;
          }
          return ret_val;
      }
      public String promptForUsername(String sitename)
      {
          String ret_val = "";
          
          return ret_val;
      }
       public String promptForPassword(String sitename)
      {
          String ret_val = "";
          
          return ret_val;
      }
      public String getKeyValue (String key)
      {
          String value = "";
          value = applicationProps.getProperty(key);
          if (value == null) {value = "";}
          return value;
          
      }
      public void setKeyValue (String key, String value)
      {
           applicationProps.setProperty(key, value);
     try {
       writer = new FileWriter(USERDIR + File.separator + CONFIG_FILE_NAME);
    applicationProps.store(writer, "thebrowserbots_settings");
    writer.close();
    } 

    catch (Exception e) {
			System.out.println("Exception writing key: " + key + "value: " +value + "ex: " + e);
		}      
      }
    
    public String ReturnMachineSerialNumber() throws IOException
    {

        
        Process process = null;
        
         String ret_val = "";
      
       
           String sn = null;

		OutputStream os = null;
		InputStream is = null;

		Runtime runtime = Runtime.getRuntime();
      switch (OperatingSystem)
      {
          
          case "Windows":
              process = Runtime.getRuntime().exec(new String[] { "wmic", "bios", "get", "serialnumber" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
      ret_val = serial;
      break;
      
          case "Mac":
          
		
		try {
			process = runtime.exec(new String[] { "/usr/sbin/system_profiler", "SPHardwareDataType" });
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		os = process.getOutputStream();
		is = process.getInputStream();

		try {
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		String marker = "Serial Number";
		try {
			while ((line = br.readLine()) != null) {
				if (line.contains(marker)) {
					sn = line.split(":")[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		if (sn == null) {
			throw new RuntimeException("Cannot find computer SN");
                       
		}

		ret_val = sn;
                break;
          case "Linux":
             line = null;
		marker = "Serial Number:";
		 br = null;

		try {
			br = read("dmidecode -t system");
			while ((line = br.readLine()) != null) {
				if (line.contains(marker)) {
					sn = line.split(marker)[1].trim();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
                
                if (sn==null)
                {
                 line = null;
		 marker = "system.hardware.serial =";
		 br = null;

		try {
			br = read("lshal");
			while ((line = br.readLine()) != null) {
				if (line.contains(marker)) {
					sn = line.split(marker)[1].replaceAll("\\(string\\)|(\\')", "").trim();
					break;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
                }
                if (sn==null){sn="";}
                ret_val = sn;
              break;
              
      }
      
      
      
      
      return ret_val;
      
    }
	private static BufferedReader read(String command) {

		OutputStream os = null;
		InputStream is = null;

		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime.exec(command.split(" "));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		os = process.getOutputStream();
		is = process.getInputStream();

		try {
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new BufferedReader(new InputStreamReader(is));
	}    
}
  
     

