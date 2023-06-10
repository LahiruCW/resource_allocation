package com.abc.controller;

//custom database operation package
import com.abc.database.DatabaseService;

//import model and view packages
import com.abc.model.Customer;
import com.abc.view.CustomerView.customerForm.CustomerForm;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * CustomerController class extends UserController and handles CRUD operations
 * for the Customer model.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class CustomerController extends UserController {

    private Customer model;
    private CustomerForm view;

    public Customer getModel() {
        return model;
    }

    public void setModel(Customer model) {
        this.model = model;
    }

    public CustomerForm getView() {
        return view;
    }

    public void setView(CustomerForm view) {
        this.view = view;
    }

    /**
     * Constructs a new CustomerController with the given DatabaseService and
     * User model.
     *
     * @param databseService the DatabaseService instance
     * @param model the User model instance
     */
    public CustomerController(DatabaseService databseService) {
        super(databseService);
    }

    /**
     * Inserts customer data into the database.
     */
    @Override
    public void insertData() {
        //defining the SQL query for inserting administrator data
        String sql = "INSERT INTO user (user_id, first_name,second_name,email,password) VALUES (?,?,?,?,?)";

        //getting the user data from the model
        String id = model.getId();
        String firstName = model.getFirstName();
        String secondName = model.getSecondName();
        String email = model.getEmail();
        String password = model.getPassword();

        try {

            //call the executeUpdate() method to insert the administrator data into
            //the databse
            int rowsAffected;

            rowsAffected = databseService.executeUpdate(sql, id, firstName, secondName, email, password);

            //Check if the data was inserted successfully
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,
                        "User added successfully!",
                        "User Add",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "User adding unsuccessfull!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "User adding unsuccessfull!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates customer data in the database
     */
    @Override
    public void updateData() {
        //SQL query for the updating the system data
        String sql = "UPDATE user SET email=?, password = ?, first_name = ?, second_name = ? WHERE id = ?";

        //get the administrator data on the administrator model
        String id = model.getId();
        String firstName = model.getFirstName();
        String secondName = model.getSecondName();
        String email = model.getEmail();
        String password = model.getPassword();

        try {
            //call the execute update method to update the database
            int rowsAffected;

            rowsAffected = databseService.executeUpdate(sql, id, firstName, secondName, email, password);

            //Check if the data was inserted successfully
            if (rowsAffected > 0) {
                System.out.println("Administrator data updated succesfully!");
            } else {
                System.out.println("Failed to update the administrator data!");
            }
        } catch (SQLException e) {

            System.out.println("Error, While updating the administrator data!");

        }
    }

    /**
     * Deletes customer data from the database.
     */
    @Override
    public void deleteData() {

        //SQL query for the deletion of the record
        String sql = "DELETE FROM user WHERE id = ?";

        //get the customer data on the customer model
        String id = model.getId();

        try {

            //call the execute update method to update the database
            int rowsAffected;

            rowsAffected = databseService.executeUpdate(sql, id);

            //check if the data was inserted successfully
            if (rowsAffected > 0) {
                System.out.println("Customer Data Updated Successfully!");
            } else {
                System.out.println("Failed to update the customer data!");
            }
        } catch (SQLException e) {
            System.out.println("Error, while updating the customer data!");
        }
    }

    /**
     * Searches for customer data in the database using the customer ID.
     */
    @Override
    public void searchData() {

        //SQL query for retrieving customer data
        String sql = "SELECT * FROM user WHERE id = ?";

        //get the customer ID from the customer model
        String id = model.getId();

        try (ResultSet rs = databseService.executeSelect(sql, id)) {

            //iterate through the ResultSet object and print the values
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                // Print the retrived data
                System.out.println("First Name: " + firstName);
                System.out.println("Second Name: " + secondName);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
            }
        } catch (SQLException ex) {

            System.out.println("Error while retrieving customer data!");
        }
    }

    public Customer getCustomerByID(String userID) {
        
        String pass;

        //SQL query for retrieving administrator data
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try (ResultSet rs = databseService.executeSelect(sql, userID)) {

            //iterate through the ResultSet object and print the values
            if (rs.next()) {

                String firstName = rs.getString("first_name");
                String password = rs.getString("password");
                
                //create and return the administrator object
                return new Customer(userID, firstName, password);
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return null;
    }

    //set the user ID in the view
    public void setUserIDView() {
        JLabel userIDLabel = view.getUserId();
        userIDLabel.setText(model.getId() + " - " + model.getFirstName());
    }
}
