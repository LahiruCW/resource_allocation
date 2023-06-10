package com.abc.view.LoginView.components;

//import graphics packages
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

//import action packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JPanel;

//import packages for logic handling

/**
 * This class represents a login and registration panel using Swing components.
 * It contains two panels: one for user login and one for user registration. The
 * user can switch between these panels by clicking on the corresponding
 * buttons.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class LoginAndRegister extends javax.swing.JLayeredPane {

    //constants
    private static final String ICON_PATH = "/com/abc/view/LoginView/icon/companyLogo.png";
    private static final Color MAIN_COLOR = new Color(7, 164, 121);
    private static final Dimension PANEL_SIZE = new Dimension(500, 600);

    //declaring components for the login panel
    //graphic changes are initialzed in the initLogin() method
    private final JLabel label = new JLabel("Log In");
    private final JLabel infoTxt = new JLabel("Please enter your details.");
    private final JLabel txtSign = new JLabel("Don't you have an account? ");
    private final JButton signBtn = new JButton("Sign up");
    private final RoundedTextField txtUserId = new RoundedTextField();
    private final RoundedPasswordField txtPassword = new RoundedPasswordField();
    private final JButton forgetBtn = new JButton("Forgot Password");
    private Button signIn = new Button();

    //declaring components for the register panel
    //graphic changes are initialized in the initRegister() method
    private final JLabel createAccount = new JLabel("Create Account");
    private final JLabel alreadyMemberLabel = new JLabel("Already a member? ");
    private final JButton loginBtn = new JButton("Log In");
    private final RoundedTextField txtFirstName = new RoundedTextField();
    private final RoundedTextField txtSecondName = new RoundedTextField();
    private final RoundedTextField txtEmail = new RoundedTextField();
    private final RoundedPasswordField txtCreatePassword = new RoundedPasswordField();
    private Button signUp = new Button();

    /**
     * Constructor for the LoginAndRegister class. Initializes the login and
     * register panels.
     */
    public LoginAndRegister() {
        initComponents();

        initializeLoginPanel();
        loginPanel.setVisible(true);
        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(PANEL_SIZE);

        initializeRegisterPanel();
        RegisterPanel.setOpaque(false);

    }

    /**
     * Initializes the login panel with components and layout.
     */
    private void initializeLoginPanel() {

        // Set up the login panel with components and layout
        loginPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]15[]5[]10[]10[]10[]10[]10[]10[]push"));

        ImageIcon logoIcon = new ImageIcon(getClass().getResource(ICON_PATH));
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
        var logoLabel = new JLabel(scaledLogoIcon);
        loginPanel.add(logoLabel);

        this.label.setFont(new Font("sansserif", 1, 30));
        this.label.setForeground(MAIN_COLOR);
        loginPanel.add(this.label);

        this.infoTxt.setFont(new Font("sansserif", 0, 10));
        this.infoTxt.setForeground(MAIN_COLOR);
        loginPanel.add(this.infoTxt);

        this.txtSign.setFont(new Font("sansserif", 1, 12));
        this.txtSign.setForeground(MAIN_COLOR);
        loginPanel.add(this.txtSign, "split 2");

        this.signBtn.setForeground(new Color(123, 177, 248));
        this.signBtn.setFont(new Font("sansserif", 1, 12));
        this.signBtn.setContentAreaFilled(false);
        this.signBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(this.signBtn);

        this.txtUserId.setHint("User Id *");
        this.txtUserId.setPrefixIcon(new ImageIcon(getClass().getResource("/com/abc/view/LoginView/icon/portrait.png")));
        loginPanel.add(this.txtUserId, "wmin 60%, width 60%:60%:60%");

        this.txtPassword.setHint("Password *");
        this.txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/com/abc/view/LoginView/icon/key.png")));
        loginPanel.add(this.txtPassword, "wmin 60%, width 60%:60%:60%");

        this.forgetBtn.setForeground(new Color(100, 100, 100));
        this.forgetBtn.setFont(new Font("sansserif", 1, 12));
        this.forgetBtn.setContentAreaFilled(false);
        this.forgetBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(this.forgetBtn, "w 40%, h 40");

        this.signIn.setBackground(MAIN_COLOR);
        this.signIn.setForeground(new Color(250, 250, 250));
        this.signIn.setText("Log In");
        loginPanel.add(this.signIn, "w 40%, h 40");

        // Add an action listener to the signBtn
        addLoginButtonListener();
    }

    /**
     * Initializes the register panel with components and layout.
     */
    private void initializeRegisterPanel() {

        // Set up the register panel with components and layout
        RegisterPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]15[]5[]25[]10[]10[]10[]35[]push"));

        ImageIcon logoIcon = new ImageIcon(getClass().getResource(ICON_PATH));
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
        var logoLabel = new JLabel(scaledLogoIcon);
        RegisterPanel.add(logoLabel);

        this.createAccount.setFont(new Font("sansserif", 1, 30));
        this.createAccount.setForeground(MAIN_COLOR);
        RegisterPanel.add(this.createAccount);

        this.alreadyMemberLabel.setFont(new Font("sansserif", 1, 12));
        this.alreadyMemberLabel.setForeground(MAIN_COLOR);
        RegisterPanel.add(this.alreadyMemberLabel, "split 2");

        this.loginBtn.setForeground(new Color(123, 177, 248));
        this.loginBtn.setFont(new Font("sansserif", 1, 12));
        this.loginBtn.setContentAreaFilled(false);
        this.loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        RegisterPanel.add(this.loginBtn); // This label will be placed in the same cell as txt1

        this.txtFirstName.setHint("First Name *");
        RegisterPanel.add(this.txtFirstName, "wmin 60%, width 60%:60%:60%");

        this.txtSecondName.setHint("Second Name *");
        RegisterPanel.add(this.txtSecondName, "wmin 60%, width 60%:60%:60%");

        this.txtEmail.setHint("Email *");
        RegisterPanel.add(txtEmail, "wmin 60%, width 60%:60%:60%");

        txtCreatePassword.setHint("Password *");
        RegisterPanel.add(txtCreatePassword, "wmin 60%, width 60%:60%:60%");

        signUp.setBackground(MAIN_COLOR);
        signUp.setForeground(new Color(250, 250, 250));
        signUp.setText("Sign Up");
        RegisterPanel.add(signUp, "w 40%, h 40");
        
        // Add an action listener to the loginBtn
        addRegisterButtonListener();
    }

    /**
     * Adds an action listener to the signBtn that switches to the register panel.
     */
    private void addLoginButtonListener() {
        signBtn.addActionListener((ActionEvent e) -> {
            switchToRegisterPanel();
        });
    }

    /**
     * Adds an action listener to the loginBtn that switches to the login panel.
     */
    private void addRegisterButtonListener() {
        loginBtn.addActionListener((ActionEvent e) -> {
            switchToLoginPanel();
        });
    }
    
    //action listener for the signUp button
    public void addSignUpButtonListener(ActionListener listener){
        signUp.addActionListener(listener);
    }
    
    //action listener for the signIn button
    public void addSignInButtonListener(ActionListener listener){
        signIn.addActionListener(listener);
    }

    /**
     * Switches the view to the login panel.
     */
    private void switchToLoginPanel() {
        loginPanel.setVisible(true);
        loginPanel.setPreferredSize(PANEL_SIZE);
        RegisterPanel.setVisible(false);
    }

    /**
     * Switches the view to the register panel.
     */
    private void switchToRegisterPanel() {
        loginPanel.setVisible(false);
        RegisterPanel.setVisible(true);
        RegisterPanel.setPreferredSize(PANEL_SIZE);
    }

    public Button getSignIn() {
        return signIn;
    }
    
    public Button getSignUp() {
        return signUp;
    }

    public void setSignUp(Button signUp) {
        this.signUp = signUp;
    }
    
    /**
     * Returns the user Id
     *
     * @return String user id entered by the user
     */
    public String getUserId() {
        return txtUserId.getText();
    }

    /**
     * Returns the password in the login form
     *
     * @return String password in the login form
     */
    public String getPassword() {
        return String.valueOf(txtPassword.getPassword());
    }

    /**
     * Returns the first name
     *
     * @return String first name entered by the user
     */
    public String getFirstName() {
        return txtFirstName.getText();
    }

    /**
     * Returns the second name
     *
     * @return String second name entered by the user
     */
    public String getSecondName() {
        return txtSecondName.getText();
    }

    /**
     * Returns the email address
     *
     * @return String email entered by the user
     */
    public String getEmail() {
        return txtEmail.getText();
    }

    /**
     * Returns the user password entered in the sign up form
     *
     * @return String new password
     */
    public String getNewPassword() {
        return String.valueOf(txtCreatePassword.getPassword());
    }

    /**
     * Returns the login panel.
     *
     * @return the login panel
     */
    public JPanel getLoginPanel() {
        return loginPanel;
    }
    
    /**
     * Returns the register panel
     *
     * @return the register panel
     */
    public JPanel getRegisterPanel(){
        return RegisterPanel;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        RegisterPanel = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(loginPanel, "card2");

        RegisterPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RegisterPanelLayout = new javax.swing.GroupLayout(RegisterPanel);
        RegisterPanel.setLayout(RegisterPanelLayout);
        RegisterPanelLayout.setHorizontalGroup(
            RegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        RegisterPanelLayout.setVerticalGroup(
            RegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(RegisterPanel, "card3");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RegisterPanel;
    private javax.swing.JPanel loginPanel;
    // End of variables declaration//GEN-END:variables
}
