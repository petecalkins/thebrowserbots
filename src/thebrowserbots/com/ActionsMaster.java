/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;

import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author pcalkins
 */

public class ActionsMaster {
    Set<String> ActionHashMap;
    Set<String> PassFailActionHashMap;
 
    WebDriverWait dummywait;
  
    ActionsMaster()
    {
      ActionHashMap = new HashSet();
      PassFailActionHashMap = new HashSet();
    
  ActionHashMap.add("Back Button");
  ActionHashMap.add("Click at Button Text");
  ActionHashMap.add("Click at HREF");
  ActionHashMap.add("Click at ID");
  ActionHashMap.add("Click at Image SRC");
  ActionHashMap.add("Click at Link Text");
  ActionHashMap.add("Click at Name");
  ActionHashMap.add("Click at XPATH");
  ActionHashMap.add("Close Current Tab or Window");
  ActionHashMap.add("Down Arrow Key");
  ActionHashMap.add("Drag From ID to ID");
  ActionHashMap.add("Drag From XPATH Distance X and Y Pixels");
  ActionHashMap.add("Enter Key");
  ActionHashMap.add("Escape Key");
  ActionHashMap.add("Execute Javascript");
  ActionHashMap.add("Forward Action");
  ActionHashMap.add("Go to URL");
  ActionHashMap.add("Left Arrow Key");
  ActionHashMap.add("Next Tab");
  ActionHashMap.add("Open New Tab");
  ActionHashMap.add("Paste at XPATH");
  ActionHashMap.add("Paste Password at XPATH");
  ActionHashMap.add("Pause");
  ActionHashMap.add("Pause with Continue Button");
  ActionHashMap.add("Right Arrow Key");
  ActionHashMap.add("Right-Click");
  ActionHashMap.add("Set Cookie");
  ActionHashMap.add("Store Link as Variable by XPATH");
  ActionHashMap.add("Store Links as URL List by XPATH");
  ActionHashMap.add("Switch Driver");
  ActionHashMap.add("Switch To Frame");
  ActionHashMap.add("Switch To Tab or Window");
  //legacy support for Type at ID
  ActionHashMap.add("Type at ID");
  ActionHashMap.add("Type at Input ID");
  ActionHashMap.add("Type at Input Name");
  ActionHashMap.add("Type at XPATH");
  ActionHashMap.add("Type Password at ID");
  ActionHashMap.add("Type Password at Input Name");
  ActionHashMap.add("Type Password at XPATH");
  ActionHashMap.add("Up Arrow Key");

 PassFailActionHashMap.add("Yes/No Prompt");
 PassFailActionHashMap.add("Find Text");
 PassFailActionHashMap.add("Find HREF");
 PassFailActionHashMap.add("Find IFrame SRC");
 PassFailActionHashMap.add("Find Image SRC");
 PassFailActionHashMap.add("Find Page Title");
 PassFailActionHashMap.add("Find XPATH");
 PassFailActionHashMap.add("Do NOT Find Text");
 PassFailActionHashMap.add("Do NOT Find HREF");
 PassFailActionHashMap.add("Do NOT Find IFrame SRC");
 PassFailActionHashMap.add("Do NOT Find Image SRC");
 PassFailActionHashMap.add("Do NOT Find Page Title");
 PassFailActionHashMap.add("Do NOT Find XPATH");
  }
    public BMAction CreatePassFailAction (String actionKey)
    {
          BMAction retVal = new YesNoPromptPassFailAction("");
        
        switch (actionKey){
          
            case "Yes/No Prompt":
                  retVal = new YesNoPromptPassFailAction("");
                  break;
            case "Find Text":
                retVal = new FindTextPassFailAction("", false);
                break;
            case "Find HREF":
                retVal = new FindHREFPassFailAction("", false);
                break;
            case "Find IFrame SRC":
                retVal = new FindIFrameSRCPassFailAction("", false);
                break;
            case "Find Image SRC":
                retVal = new FindImageSRCPassFailAction("", false);
                break;
            case "Find Page Title":
                retVal = new FindPAGETITLEPassFailAction("", false);
                break;
            case "Find XPATH":
                retVal = new FindXPATHPassFailAction("", false);
                break;
            case "Do NOT Find Text":
                retVal = new FindTextPassFailAction("", true);
                break;
            case "Do NOT Find HREF":
                retVal = new FindHREFPassFailAction("", true);
                break;
            case "Do NOT Find IFrame SRC":
                retVal = new FindIFrameSRCPassFailAction("", true);
                break;
            case "Do NOT Find Image SRC":
                retVal = new FindImageSRCPassFailAction("", true);
                break;
            case "Do NOT Find Page Title":
                retVal = new FindPAGETITLEPassFailAction("", true);
                break;
            case "Do NOT Find XPATH":
                retVal = new FindXPATHPassFailAction("", true);
                break;
                  default:
                System.out.println("KEY ACTION PASS FAIL NOT FOUND");
                break;
        }
        return retVal;
    }
  
    public BMAction CreateAction(String actionKey)
    {
        BMAction retVal = new BackAction();
        
        switch (actionKey){
          
            case "Back Button":
                   retVal = new BackAction();
                   break;  
            case "Click at Button Text":
                retVal = new ClickAtButtonTextAction("", false, false);
                break;
            case "Click at HREF":
                retVal = new ClickAtHREFAction("", false, false);
                break;
            case "Click at ID":
                retVal = new ClickAtIDAction("", false, false);
                break;
            case "Click at Image SRC":
                retVal = new ClickAtImageSRCAction("", false, false);
                break;
            case "Click at Link Text":
                retVal = new ClickAtLinkTextAction("", false, false);
                break;
                
            case "Click at Name":
                retVal = new ClickAtNameAction("", false, false);
                break;
            case "Click at XPATH":
                retVal = new ClickXPATHAction("", false, false);
                break;
            case "Close Current Tab or Window":
                retVal = new CloseCurrentTabOrWindowAction();
                break;
            case "Down Arrow Key":
                retVal = new DownArrowKeyAction();
                break;
            case "Drag From ID to ID":
                retVal = new DragAndDropAction("", "");
                break;
            case "Drag From XPATH Distance X and Y Pixels":
                retVal = new DragAndDropByAction("", "");
                break;
            case "Enter Key":
                retVal = new EnterKeyAction();
                break;
            case "Escape Key":
                retVal = new EscapeKeyAction();
                break;
            case "Execute Javascript":
                retVal = new ExecuteJavascriptAction("");
                break;
            case "Forward Action":
                retVal = new ForwardAction();
                break;
            case "Go to URL":
                retVal = new GoAction("");
                break;
            case "Left Arrow Key":
                retVal = new LeftArrowKeyAction();
                break;
            case "Next Tab":
                retVal = new NextTabAction();
                break;
            case "Open New Tab":
                retVal = new OpenNewTabAction();
                break;
            case "Paste at XPATH":
                retVal = new PasteAtXPATHAction("", "", false);
                break;
            case "Paste Password at XPATH":
                retVal = new PastePasswordAtXPATHAction("", "", false);
                break;
            case "Pause":
                retVal = new PauseAction("", "");
                break;
            case "Pause with Continue Button":
                retVal = new PauseContinueAction();
                break;
            case "Right Arrow Key":
                retVal = new RightArrowKeyAction();
                break;
            case "Right-Click":
                retVal = new RightClickAction();
                break;
            case "Set Cookie":
                retVal = new SetCookieAction("", "");
                break;
            case "Store Link as Variable by XPATH":
                retVal = new StoreLinkAsVarByXPATHAction("", "");
                break;
            case "Store Links as URL List by XPATH":
                retVal = new StoreLinksAsArrayByXPATHAction("", "");
                break;
            case "Switch Driver":
                retVal = new SwitchDriverAction();
                break;
            case "Switch To Frame":
                retVal = new SwitchToFrameAction("");
                break;
            case "Switch To Tab or Window":
                retVal = new SwitchToTabOrWindowAction("");
                break;
  //legacy support for Type at ID
            case "Type at ID":
                retVal = new TypeAtIDAction("", "", false);
                break;
            case "Type at Input ID":
                retVal = new TypeAtIDAction("", "", false);
                break;
            case "Type at Input Name":
                retVal = new TypeAtInputNameAction("","", false);
                break;
            case "Type at XPATH":
                retVal = new TypeAtXPATHAction("", "", false);
                break;
            case "Type Password at ID":
                retVal = new TypePasswordAtIDAction("", "", false);
                break;
            case "Type Password at Input Name":
                retVal = new TypePasswordAtInputNameAction("", "", false);
                break;
            case "Type Password at XPATH":
                retVal = new TypePasswordAtXPATHAction("", "", false);
                break;
            case "Up Arrow Key":
                retVal = new UpArrowKeyAction();
                break;
            default:
                System.out.println("KEY ACTION NOT FOUND");
                break;
        }
    
      return retVal;  
    }
  
}