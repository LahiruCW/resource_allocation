package com.abc.controller;

//handing database side
import com.abc.database.DatabaseService;
import java.sql.SQLException;
import java.sql.ResultSet;

//model and view
import com.abc.model.Hall;
import com.abc.view.AdministratorView.administratorForm.AdministratorForm;
import com.abc.view.AdministratorView.components.Table;
import com.abc.view.CustomerView.customerForm.CustomerForm;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LahiruCW
 */
public class HallController {

    private Hall model;
    private AdministratorForm view;
    private CustomerForm customerView;
    private final DatabaseService dbms;

    public HallController(DatabaseService dbms) {

        this.dbms = dbms; //only one time setting the object
    }

    public Hall getModel() {
        return model;
    }

    public void setModel(Hall model) {
        this.model = model;
    }

    public AdministratorForm getView() {
        return view;
    }

    public void setView(AdministratorForm view) {
        this.view = view;
    }

    public CustomerForm getCustomerView() {
        return customerView;
    }

    public void setCustomerView(CustomerForm customerView) {
        this.customerView = customerView;
    }

    //get the hall name and hall capacity from the user
    public String getHallName() {
        return view.getHallName();
    }

    public String getHallCapacity() {
        return view.getHallCapacity();
    }

    //update the model with the followings
    public void updateModelName() {
        model.setName(getHallName());
    }

    public void updateModelCapacity() {
        model.setCapacity(Integer.parseInt(getHallCapacity()));
    }

    //writing the sql queries for the code
    public void insertData() {

        //defining the sql query for inserting the hall details
        String sql = "INSERT INTO hall (hall_id, hall_name, capacity) VALUES(?,?,?)";

        //get the data from the model
        String hallID = model.getId();
        String hallName = model.getName();
        int capacity = model.getCapacity();

        try {

            //call the executeUpdate() method to insert the hall data
            //to the database
            int rowsAffected;

            rowsAffected = dbms.executeUpdate(sql, hallID, hallName, capacity);

            //Check if the data was inserted successfully
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Hall Successfully Added. Generated Hall ID: " + hallID, "Adding Hall", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Hall Successfully Disrupt!", "Adding Hall", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Adding Hall", JOptionPane.ERROR_MESSAGE);
        }

    }

    //retrive all hall details and update the customer form
    public void updateHallTable(Table table) {

        //SQL Query to get the hall data
        String sqlQuery = "SELECT * FROM hall";

        //Get the hall details from the database
        try (ResultSet rs = dbms.executeSelect(sqlQuery)) {

            //iterate through the ResultSet object and update the table
            while (rs.next()) {

                String id = rs.getString("hall_id");
                String hallName = rs.getString("hall_name");
                int hallCapacity = rs.getInt("capacity");

                if (!rowExists(table, id)) {
                    table.addRow(new Object[]{id, hallName, hallCapacity});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Hall Table Update Unsuccessfull!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void retrieveNumberOfRows() {

        //SQL Query
        String sqlQuery = "SELECT COUNT(*) FROM ";
    }

    public boolean rowExists(Table table, String hallId) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).equals(hallId)) {
                return true;
            }
        }
        return false;
    }

}
