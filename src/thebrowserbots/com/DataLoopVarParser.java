/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pete
 */
public class DataLoopVarParser {
 
 Boolean hasDataLoopVar = false;   
 ArrayList<DataLoopVarHelper> DataLoopVars;
 
 DataLoopVarParser(String textfieldvalue)
 {
     DataLoopVars = new ArrayList();
     
   if (!textfieldvalue.contains("[dataloop-field-start]"))
   {
       hasDataLoopVar = false;
   }
   else
   {
   hasDataLoopVar = true;  
  
   int end_fieldcount = 0;

    if (textfieldvalue.contains("[dataloop-field-start]") && textfieldvalue.contains("[dataloop-field-end]"))   
    {
        String[] split_testfield_end = textfieldvalue.split("\\[dataloop\\-field\\-end\\]");
    
      
        end_fieldcount = split_testfield_end.length;
   
      //  DataLoopVars = new DataLoopVarHelper[end_fieldcount];
       
        for (int x=0; x<end_fieldcount; x++)
        {
         DataLoopVarHelper FieldData = new DataLoopVarHelper();
            String thesevalues;
            if (split_testfield_end[x].contains("[dataloop-field-start]"))
            {
           
       int startfield_index = split_testfield_end[x].indexOf("[dataloop-field-start]");
       
            thesevalues = split_testfield_end[x].substring(startfield_index+22);
            if (startfield_index==0)
            {
            String[] data_values = thesevalues.split(",");
            FieldData.varnumber = Integer.parseInt(data_values[0]);
            FieldData.field_column_index = Integer.parseInt(data_values[1]);
            if (data_values.length>2)
            {
            FieldData.field_column_name = data_values[2];
            }
            else
            {
             FieldData.field_column_name = "";   
            }
           
            }
            else
            {
             FieldData.beforevar = split_testfield_end[x].substring(x, startfield_index); 
             thesevalues = split_testfield_end[x].substring(startfield_index+22);
             String[] data_values = thesevalues.split(",");
            FieldData.varnumber = Integer.parseInt(data_values[0]);
            FieldData.field_column_index = Integer.parseInt(data_values[1]);
            if (data_values.length>2)
            {
            FieldData.field_column_name = data_values[2];
            }
            else
            {
             FieldData.field_column_name = "";   
            }
          
            }
            
            }
          else
            {
        
            FieldData.aftervar = split_testfield_end[x];
            
            }
         DataLoopVars.add(FieldData);
        }
    }
    
    else
    {
     
    }
   }
 }
 public String GetFullValueFromURLList(int row_number, List<String> in_list)
 {
   String concat_variable = "";
       for (int y=0; y<DataLoopVars.size(); y++)
      {
       
       DataLoopVarHelper theseDataLoopVars = this.DataLoopVars.get(y);
       concat_variable+= theseDataLoopVars.beforevar;
       if (theseDataLoopVars.field_column_index!=-1)
       {
           //this is wrong...
           if (in_list.size()>0)
          
           {
            concat_variable+= in_list.get(row_number);
            
           }
       }
       concat_variable+=theseDataLoopVars.aftervar;
      }
       return concat_variable;
    
 }
 public String GetFullValueFromFile(int row_number, List<String[]> in_dataset)
 {
     String concat_variable = "";
     
       for (int y=0; y<DataLoopVars.size(); y++)
      {
       
       DataLoopVarHelper theseDataLoopVars = this.DataLoopVars.get(y);
       concat_variable+= theseDataLoopVars.beforevar;
       if (theseDataLoopVars.field_column_index!=-1)
       {
          
           if (in_dataset.size()>0)
          
           {
               String[] thisrow = in_dataset.get(row_number);
               if (thisrow.length>theseDataLoopVars.field_column_index)
               {
            concat_variable+= thisrow[theseDataLoopVars.field_column_index];
               }
            
           }
       }
       concat_variable+=theseDataLoopVars.aftervar;
      }
       return concat_variable;
 }
}
