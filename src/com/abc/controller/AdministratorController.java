package com.abc.controller;

import com.abc.database.DatabaseService;
import com.abc.model.Administrator;
import com.abc.view.AdministratorView.administratorForm.AdministratorForm;

import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JLabel;

/**
 * AdministratorController is a subclass of UserController that implements the
 * CRUD operations for an Administrator in the system.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class AdministratorController extends UserController {

    private Administrator model;
    private AdministratorForm view;

    public AdministratorController(DatabaseService databseService) {
        super(databseService);
    }

    public Administrator getModel() {
        return model;
    }

    public void setModel(Administrator model) {
        this.model = model;
    }

    public AdministratorForm getView() {
        return view;
    }

    public void setView(AdministratorForm view) {
        this.view = view;
    }
    
    //set the user ID in the view
    public void setUserIDView(){
        JLabel userIDLabel = view.getUserId();
        userIDLabel.setText(model.getId()+" - "+model.getFirstName());
    }

    /**
     * Inserts the Administrator data into the database.
     */
    @Override
    public void insertData() {

        //defining the SQL query for inserting administrator data
        String sql = "INSERT INTO user (user_id, first_name,second_name,email,password) VALUES (?,?,?,?,?)";
        
        //get the data from the model
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
                System.out.println("Administrator Data inserted successfully!");
            } else {
                System.out.println("Failed to insert administrator data!");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting administrator data!");
        }

    }

    /**
     * Updates the Administrator data in the database.
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
     * Deletes the Administrator data from the database.
     */
    @Override
    public void deleteData() {
        //SQL query for the updating the system data
        String sql = "DELETE FROM user WHERE id = ?";

        //get the administrator data on the administrator model
        String id = model.getId();

        try {
            //call the execute update method to update the database
            int rowsAffected;

            rowsAffected = databseService.executeUpdate(sql, id);

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
     * Searches for the Administrator data in the database using the ID and
     * prints the result.
     */
    @Override
    public void searchData() {

        //SQL query for retrieving administrator data
        String sql = "SELECT * FROM user WHERE user_id = ?";

        //Get the administrator ID from the administrator model
        String id = model.getId();
        String pwd = model.getPassword();

        try (ResultSet rs = databseService.executeSelect(sql, id)) {

            //iterate through the ResultSet object and print the values
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String password = rs.getString("password");

                //updating the Administrator view
                view.getUserId().setText(id + " " + firstName);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Administrator getAdministratorByID(String userID){
        
        //SQL query for retrieving administrator data
        String sql = "SELECT * FROM user WHERE user_id = ?";
        
        try(ResultSet rs = databseService.executeSelect(sql, userID)){
            
            //iterate through the ResultSet object and print the values
            if(rs.next()){
                
                String firstName = rs.getString("first_name");
                String password = rs.getString("password");
                
                //create and return the administrator object
                return new Administrator(userID, firstName, password);
            }
            
        }catch(SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
}
