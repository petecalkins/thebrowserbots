/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thebrowserbots.com;



import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentListener;


public class Login_Register_Dialog extends javax.swing.JFrame {

    /**
     * Creates new form Login_Register_Dialog
     */
    String name;
    String password;
    String confirm_password;
    String email;
    Boolean PWSMATCH = false;
    Boolean EMAILVALID = false;
    String mode ="login";
    Boolean isActive = true;
    public Login_Register_Dialog() {
        super("Login/Register");
        initComponents();
        jButtonRegister.setEnabled(false);
         this.setSize(620,275);
        this.setVisible(true);
       
       LoginMode();
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTextFieldLoginName = new javax.swing.JTextField();
        jLabelLoginName = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonRegister = new javax.swing.JButton();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jTextLabelPWDONOTMATCH = new javax.swing.JLabel();
        jLabelAllRequired = new javax.swing.JLabel();
        jLabelEMAILINVALID = new javax.swing.JLabel();
        jButtonLoginMode = new javax.swing.JButton();
        jButtonRegisterMode = new javax.swing.JButton();
        jButtonRecoverMode = new javax.swing.JButton();
        jButtonRecoverPassword = new javax.swing.JButton();
        jLabelStatusText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BrowserBot Cloud Login/Register");
        setMinimumSize(new java.awt.Dimension(500, 500));

        jTextFieldLoginName.setMinimumSize(new java.awt.Dimension(223, 20));

        jLabelLoginName.setText("Name:");

        jLabelPassword.setText("Password:");

        jButtonLogin.setText("Login");

        jLabelConfirmPassword.setText("Confirm Password:");

        jLabelEmail.setText("Email:");

        jTextFieldEmail.setMinimumSize(new java.awt.Dimension(223, 20));

        jButtonRegister.setText("Register");

        jPasswordFieldPassword.setMinimumSize(new java.awt.Dimension(223, 20));

        jPasswordFieldConfirmPassword.setMinimumSize(new java.awt.Dimension(223, 20));

        jTextLabelPWDONOTMATCH.setText("Confirm value does not match password");

        jLabelAllRequired.setText("*All fields are required for registration");

        jLabelEMAILINVALID.setText("Email Address is not valid");

        jButtonLoginMode.setText("Login");

        jButtonRegisterMode.setText("Register");

        jButtonRecoverMode.setText("Recover Password");

        jButtonRecoverPassword.setText("Recover Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEmail)
                            .addComponent(jLabelConfirmPassword)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelLoginName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLoginName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(jPasswordFieldConfirmPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonLogin)
                                    .addComponent(jTextLabelPWDONOTMATCH)
                                    .addComponent(jLabelEMAILINVALID)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonRegister)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelAllRequired))
                            .addComponent(jButtonRecoverPassword)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonLoginMode, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonRegisterMode, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonRecoverMode, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLoginName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLoginName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogin)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelConfirmPassword)
                    .addComponent(jTextLabelPWDONOTMATCH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEMAILINVALID))
                .addGap(4, 4, 4)
                .addComponent(jButtonRecoverPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegister)
                    .addComponent(jLabelAllRequired))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLoginMode)
                    .addComponent(jButtonRegisterMode)
                    .addComponent(jButtonRecoverMode))
                .addGap(18, 18, 18)
                .addComponent(jLabelStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_Register_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Register_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Register_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Register_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login_Register_Dialog dialog = new Login_Register_Dialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    public void LoginMode()
    {
        this.mode ="login";
      jTextFieldLoginName.setVisible(true);
      jLabelLoginName.setVisible(true);
         jPasswordFieldPassword.setVisible(true);
        jLabelPassword.setVisible(true);
        jPasswordFieldConfirmPassword.setVisible(false);
      jLabelConfirmPassword.setVisible(false);
      jTextFieldEmail.setVisible(false);
      jLabelEmail.setVisible(false);
      jButtonRecoverPassword.setVisible(false);
     jButtonLogin.setVisible(true);
      jButtonRegister.setVisible(false);
        jTextLabelPWDONOTMATCH.setVisible(false);
        jLabelEMAILINVALID.setVisible(false);
        jLabelAllRequired.setVisible(false);
              jButtonLoginMode.setEnabled(false);
        jButtonRegisterMode.setEnabled(true);
        jButtonRecoverMode.setEnabled(true);
        setStatus("");
           this.setSize(620,275);
    }
    public void RegisterMode()
    {
        this.mode = "register";
         jPasswordFieldPassword.setVisible(true);
        jLabelPassword.setVisible(true);
        jTextFieldLoginName.setVisible(true);
      jLabelLoginName.setVisible(true);
     jPasswordFieldConfirmPassword.setVisible(true);
      jLabelConfirmPassword.setVisible(true);
      jTextFieldEmail.setVisible(true);
      jLabelEmail.setVisible(true);
      jButtonRecoverPassword.setVisible(false);
      jButtonLogin.setVisible(false);
      jButtonRegister.setVisible(false);
        jTextLabelPWDONOTMATCH.setVisible(false);
        jLabelEMAILINVALID.setVisible(false);
        jLabelAllRequired.setVisible(true);
        jButtonLoginMode.setEnabled(true);
        jButtonRegisterMode.setEnabled(false);
        jButtonRecoverMode.setEnabled(true);
        
        setStatus("");
       AllRequiredCheck();
             this.setSize(620,275);
    }
    public void RecoverMode()
    {
        this.mode = "recover";
        jPasswordFieldPassword.setVisible(false);
        jLabelPassword.setVisible(false);
      jTextFieldLoginName.setVisible(false);
      jLabelLoginName.setVisible(false);
      jPasswordFieldConfirmPassword.setVisible(false);
      jLabelConfirmPassword.setVisible(false);
      jTextFieldEmail.setVisible(true);
      jLabelEmail.setVisible(true);
      jButtonRecoverPassword.setVisible(true);
      jButtonLogin.setVisible(false);
      jButtonRegister.setVisible(false);
        jTextLabelPWDONOTMATCH.setVisible(false);
        jLabelEMAILINVALID.setVisible(false);
        jLabelAllRequired.setVisible(false);
        jButtonLoginMode.setEnabled(true);
        jButtonRegisterMode.setEnabled(true);  
        jButtonRecoverMode.setEnabled(false);
        setStatus("");
           this.setSize(620,275);
        
    }
    public void setStatus(String status)
    {
        jLabelStatusText.setText(status);
    }
    public void addjTextFieldPasswordActionListener(ActionListener listener)
    {
        jPasswordFieldPassword.addActionListener(listener);
    }
    public void addjButtonRecoverPasswordActionListener(ActionListener listener)
{
    jButtonRecoverPassword.addActionListener(listener);
}
    public void addjButtonLoginModeActionListener(ActionListener listener)
{
    jButtonLoginMode.addActionListener(listener);
}
    public void addjButtonRegisterModeActionListener(ActionListener listener)
{
    jButtonRegisterMode.addActionListener(listener);
}
    public void addjButtonRecoverModeActionListener(ActionListener listener)
{
    jButtonRecoverMode.addActionListener(listener);
}
public void addjTextFieldConfirmPasswordDocListener(DocumentListener doclistener)
{
    jPasswordFieldConfirmPassword.getDocument().addDocumentListener(doclistener);
}
public void addjTextFieldPasswordDocListener(DocumentListener doclistener)
{
    jPasswordFieldPassword.getDocument().addDocumentListener(doclistener);
}

public void addjTextFieldLoginNameDocListener(DocumentListener doclistener)
{
    jTextFieldLoginName.getDocument().addDocumentListener(doclistener);
}
public void addjTextFieldEmailDocListener(DocumentListener doclistener)
{
    jTextFieldEmail.getDocument().addDocumentListener(doclistener);
}
public void addjTextFieldEmailActionListener(ActionListener listener)
{
    jTextFieldEmail.addActionListener(listener);
}

public void addjButtonLoginActionListener(ActionListener listener)
{
    jButtonLogin.addActionListener(listener);
}
public void addjButtonRegisterActionListener(ActionListener listener)
{
    jButtonRegister.addActionListener(listener);
}
public void setLoginName(String name)
{
    jTextFieldLoginName.setText(name);
}
public void setPassword(String password)
{
    jPasswordFieldPassword.setText(password);
    
}
public String getEmail()
    {
        String EmailFrom = jTextFieldEmail.getText();
        return EmailFrom;        
    }
public String getLoginName()
    {
        String LoginName = jTextFieldLoginName.getText();
        return LoginName;        
    }
 public String getPassword ()
    {
        String Password="";
     char[] temp;
     temp = jPasswordFieldPassword.getPassword();
       for (int x = 0; x<temp.length; x++)
     {
         Password = Password + temp[x];
     } 
       
        return Password;
    }
 public String getConfirmPassword ()
    {
        String ConfirmPassword="";
     char[] temp;
     temp = jPasswordFieldConfirmPassword.getPassword();
       for (int x = 0; x<temp.length; x++)
     {
         ConfirmPassword = ConfirmPassword + temp[x];
     } 
       
        return ConfirmPassword;
    }
public void ComparePasswordFields()
{
    if (jPasswordFieldConfirmPassword.isVisible())
    {
    String conf = getConfirmPassword();
    String org = getPassword();
    if (conf == null ? org == null : conf.equals(org))
    {
       jTextLabelPWDONOTMATCH.setVisible(false);
       PWSMATCH = true;
       isActive = true;
    }
    else
    {
        jTextLabelPWDONOTMATCH.setVisible(true);
        PWSMATCH = false;
        isActive = false;
    }
    }
}

public void AllRequiredCheck()
{
   int vals_checked = 0;
    if (PWSMATCH)
    {
     if (!getLoginName().isEmpty())
     {
         vals_checked++;
     }
       if (!getPassword().isEmpty())
     {
         vals_checked++;
     }
       if (!getConfirmPassword().isEmpty())
     {
         vals_checked++;
     }
          if (!getEmail().isEmpty())
     {
         vals_checked++;
     }
     if (vals_checked == 4)
     {
         if (EMAILVALID)
         {
         jButtonRegister.setEnabled(true);
         jButtonRegister.setVisible(true);
         jLabelAllRequired.setVisible(false);
         isActive=true;
         }
     }
     else
     {
         jButtonRegister.setEnabled(false);
         jLabelAllRequired.setVisible(true);
         isActive=false;
     }
      
    }
}

public void ValidateEmailAddress() {
   boolean result = true;
   Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
        Matcher m = p.matcher(getEmail());

        if (m.find())
        {
         result = true;
        }else{
          result = false;
        }

 
  if (result)
  {
      jLabelEMAILINVALID.setVisible(false);
      EMAILVALID=true;
      isActive=true;
  }
  else
  {
      jLabelEMAILINVALID.setVisible(true);
       jButtonRegister.setEnabled(false);
      EMAILVALID=false;
      isActive=false;
  }

}
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonLoginMode;
    private javax.swing.JButton jButtonRecoverMode;
    private javax.swing.JButton jButtonRecoverPassword;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JButton jButtonRegisterMode;
    private javax.swing.JLabel jLabelAllRequired;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelEMAILINVALID;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelLoginName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelStatusText;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLoginName;
    private javax.swing.JLabel jTextLabelPWDONOTMATCH;
    // End of variables declaration                   
}

