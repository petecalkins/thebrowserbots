/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import java.io.FileFilter;

import java.util.List;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.WildcardFileFilter;


public class UserListData {
 String listName;
 String browsermatorFileName;
 List<String[]> a_listSearchTerms;
 File csvFile;
 CSVReader CSVFileReader;
 String USERLISTDIR;
 ArrayList<File> userListFiles;
 String listDescription;
 String username;
 String USERLISTFILENAME;
 String browsermatorFileNameNoExt;
 File defaultFile;
 
 UserListData (String in_username, String in_browsermatorFileName, File in_defaultFile, File currentList)
 {
     // hardcode description til I figure a workaround
     listDescription = "";
     username = in_username;
     USERLISTDIR =  System.getProperty("user.home") + File.separator + "TheBrowserBotsCloudFiles" + File.separator + "UserLists" + File.separator;
     defaultFile = in_defaultFile;
     csvFile = currentList;
   
     browsermatorFileName = in_browsermatorFileName;
     browsermatorFileNameNoExt = browsermatorFileName.substring(0, browsermatorFileName.length()-14);
     userListFiles = new ArrayList<>();
     
      USERLISTFILENAME = USERLISTDIR + browsermatorFileNameNoExt + "_" + username;
     
      
 }
 public int ReturnCurrentIndex(File in_File)
 {
     Integer ret_val = 0;
           File userlist_directory = new File(USERLISTDIR);
        if (userlist_directory.exists()) {
            File[] listOfFiles = new File[0];
           FileFilter csvFilter = new WildcardFileFilter(browsermatorFileNameNoExt+"*.csv", IOCase.INSENSITIVE);
           listOfFiles = userlist_directory.listFiles(csvFilter);
    
        int loopindex = 1;
            for (File thisFile: listOfFiles)
          {
            if (thisFile.equals(in_File))
            {
                ret_val = loopindex;
            }
            loopindex++;
          }
        }  
     return ret_val;
     
 }
 public void getsetUserListFiles()
 {
  
            File userlist_directory = new File(USERLISTDIR);
        if (userlist_directory.exists()) {
            File[] listOfFiles = new File[0];
           FileFilter csvFilter = new WildcardFileFilter(browsermatorFileNameNoExt+"*.csv", IOCase.INSENSITIVE);
           listOfFiles = userlist_directory.listFiles(csvFilter);
        userListFiles.clear();
     userListFiles.add(defaultFile);
            for (File thisFile: listOfFiles)
          {
             userListFiles.add(thisFile);
          }
        }
        
        
 }
 public void Delete(int in_index)
 {
     int number_index = in_index;
     int delete_index = in_index-1;
     
     if (delete_index>-1)
     {
      File userlist_directory = new File(USERLISTDIR);
        if (userlist_directory.exists()) {
            File[] listOfFiles = new File[0];
           FileFilter csvFilter = new WildcardFileFilter(browsermatorFileNameNoExt+"*.csv", IOCase.INSENSITIVE);
           listOfFiles = userlist_directory.listFiles(csvFilter);
           
        if (listOfFiles.length>0)
        {
            listOfFiles[delete_index].delete();
            if (listOfFiles.length>0)
            {
                int currentIndexCount = 1;
                for (File thisFile: listOfFiles)
                {
                    
                    int thisIndex = GetFileIndex(thisFile);
                    if (thisIndex>number_index)
                    {
                    String stringIndex = Integer.toString(currentIndexCount-1);
                    String renameTo = USERLISTDIR + browsermatorFileNameNoExt + "_" + username + "_" + stringIndex + ".csv";
                    File renameFile = new File(renameTo);
                    thisFile.renameTo(renameFile);
                    }
                    currentIndexCount++;
                }
                getsetUserListFiles();
             }
            } 
         }
     }
 }
 
 public int GetFileIndex(File in_File)
 {
     int ret_val = 0;
     String thisFilename = in_File.getName();
     String[] parts = thisFilename.split(browsermatorFileNameNoExt + "_" + username + "_");
     String retIndex = parts[1].substring(0, parts[1].length()-4);
    
     ret_val = Integer.parseInt(retIndex);
     return ret_val;
 }
 public void setListName(String in_listname)
 {
     listName = in_listname;
 }
 public String getFileName()
 {
     String ret_val = "";
     ret_val = csvFile.getName();
     return ret_val;
 }
 public void setListDescription (String in_listdescription)
 {
     listDescription = in_listdescription;
 }
 public void setSearchTerms(List<String[]> in_searchterms)
 {
     
     a_listSearchTerms = in_searchterms;
 }
 public void setSearchTermsToFile(String pathToFile)
 {
            File checkPath = new File(pathToFile);
      if (checkPath.exists())
      {
          csvFile = checkPath;
      }
      
   List<String[]> thisList = CreateArrayListFromFile(csvFile.getAbsolutePath());
 //    a_listSearchTerms.clear();
    a_listSearchTerms = thisList;
   

 }
   public List<String[]> CreateArrayListFromFile(String DataFile)
    {
      List<String[]> return_array = new ArrayList<>();
      
    
             File checkPath = new File(DataFile);
      if (checkPath.exists())
      {
  
     
      try
     {
      CSVFileReader = new CSVReader(new FileReader(DataFile), ',', '"', '\0');
             return_array = CSVFileReader.readAll();   
     }
     catch(Exception ex)
     {
         System.out.println("Exception reading csv file: 122 procedure" + ex.toString());
       
     }
      try
      {
             CSVFileReader.close();
      }
      catch (Exception ex)
      {
         System.out.println("Exception closing CSVFileReader: " + ex.toString());
          
      }
      }
       return return_array;
    }   
 public String getListName()
 {
     String ret_val = "";
     ret_val = listName;
     return ret_val;   
 }
 public String getListDescription()
 {
     String ret_val = "";
     ret_val = listDescription;
     return ret_val;   
 }
 public List<String[]> getSearchTerms()
 {
     List<String[]> ret_val;
     ret_val = a_listSearchTerms;
     
     return ret_val;
 }
 
 
}
