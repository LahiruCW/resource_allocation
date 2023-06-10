package com.abc;

import com.abc.controller.AdministratorController;
import com.abc.model.Customer;
import java.sql.Connection;
import com.abc.controller.CustomerController;
import com.abc.controller.HallController;
import com.abc.database.DatabaseConnection;
import com.abc.database.DatabaseService;
import com.abc.model.Administrator;
import com.abc.model.Hall;
import com.abc.view.AdministratorView.administratorForm.AdministratorForm;
import com.abc.view.CustomerView.customerForm.CustomerForm;
import com.abc.view.LoginView.loginForm.LoginForm;
import com.abc.view.LoginView.components.Button;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import javax.swing.*;
import com.abc.model.IdGenerator;
import com.abc.view.AdministratorView.components.Table;
import java.awt.event.ActionEvent;

/**
 *
 * @author LahiruCW
 */
public class Main {

    //create a ID generator object to make random ID
    //These are SYSTEM GENERATED IDS
    protected static IdGenerator idGen = new IdGenerator();

    public static void main(String[] args) throws SQLException {

        //get the database connection object
        Connection connection = DatabaseConnection.getInstance().getConnection();

        //get the database services object to handle the database operations
        DatabaseService dbms = new DatabaseService(connection);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);

            //Create an existing form object called adminView
            AdministratorForm adminView = new AdministratorForm();

            //Create an existing form object called customerView
            CustomerForm customerView = new CustomerForm();

            //action listener for the sign up button
            Button signUpButton = loginForm.getSignUpButton();
            signUpButton.addActionListener(e -> Main.createAccount(loginForm.getLoginAndRegister1(), dbms));

            //action listener for the sign in button
            Button signInButton = loginForm.getSignInButton();
            signInButton.addActionListener(e -> {
                Main.LogIn(loginForm.getLoginAndRegister1(), dbms, adminView, customerView);

                if (customerView.isVisible() || adminView.isVisible()) {
                    loginForm.dispose();
                }
            });

            //action listener for the add halls button
            Button addHallsButton = adminView.getAddButton();
            addHallsButton.addActionListener(e -> {
                Main.addHalls(adminView, dbms);
            });
            
            //calling the update hall method
            setupTableUpdater(dbms, customerView);

        });
    }

    public static void createAccount(com.abc.view.LoginView.components.LoginAndRegister login, DatabaseService dbms) {

        String firstName = login.getFirstName();
        String secondName = login.getSecondName();
        String email = login.getEmail();
        String password = login.getNewPassword();
        String userId = idGen.generateCustomerID();

        Customer customer = new Customer(userId, firstName, secondName, email, password);
        CustomerController customerController = new CustomerController(dbms);
        customerController.setModel(customer);
        customerController.insertData();
    }

    public static void LogIn(com.abc.view.LoginView.components.LoginAndRegister login, DatabaseService dbms, AdministratorForm adminView, CustomerForm customerView) {

        //defining the local variables
        AdministratorController adminController;
        CustomerController customerController;
        Administrator admin;
        Customer customer;

        String userID = login.getUserId();
        String password = login.getPassword();

        if (userID.startsWith("AD")) {

            adminController = new AdministratorController(dbms);
            admin = adminController.getAdministratorByID(userID);

            if (admin != null && admin.getPassword().equals(password)) {
                adminView.setVisible(true);

                //current object who logged in to the system
                adminController.setModel(admin);
                adminController.setView(adminView);

                //set the user ID coloumn
                adminController.setUserIDView();

            } else {

                JOptionPane.showMessageDialog(null,
                        "Password not matched. Please Enter the correct password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (userID.startsWith("GU")) {

            customerController = new CustomerController(dbms);
            customer = customerController.getCustomerByID(userID);

            if (customer != null && customer.getPassword().equals(password)) {

                //current object who logged in to the system
                customerController.setModel(customer);
                customerController.setView(customerView);

                //set the user ID coloumn
                customerController.setUserIDView();

                //display the customer view
                customerView.setVisible(true);

            } else {

                System.out.println(customer.getPassword());
                System.out.println(password);
                JOptionPane.showMessageDialog(null,
                        "Password not matched. Please Enter the correct password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(null,
                    "Incorrect User ID! Please enter the correct Id",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void addHalls(AdministratorForm adminForm, DatabaseService dbms) {

        //get the data from the view
        String hallName = adminForm.getHallName();
        String hallCapacity = adminForm.getHallCapacity();

        //generate a hall id
        String hallID = idGen.generateHallID();

        //create a hall object
        Hall hall = new Hall(hallID, hallName, Integer.parseInt(hallCapacity));

        //create a hall controller
        HallController hallController = new HallController(dbms);

        //set the hall model and hall view
        hallController.setModel(hall);
        hallController.setView(adminForm);

        //execute the insert method
        hallController.insertData();

    }

    public static void setupTableUpdater(DatabaseService dbms, CustomerForm view) {
        int updateInterval = 5000; // 5 seconds

        //create a hall controller
        HallController hallCtrl = new HallController(dbms);

        Timer timer = new Timer(updateInterval, (ActionEvent e) -> {
            //get the table from the customer view
            Table table = view.getHallTable();
            hallCtrl.updateHallTable(table);
        });
        timer.start();
    }

}
