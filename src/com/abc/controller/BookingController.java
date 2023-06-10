package com.abc.controller;

import com.abc.database.DatabaseService;
import com.abc.model.Booking;
import com.abc.view.CustomerView.customerForm.CustomerForm;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * This class provides methods to handle booking data, such as inserting,
 * deleting, and searching. It uses the Booking model and DatabaseService to
 * interact with the database.
 *
 * @author LahiruCW
 * @version 1.0
 *
 */
public class BookingController {

    private Booking model;
    private CustomerForm view;
    
    private final DatabaseService databaseService;

    /**
     * Constructor that initializes the BookingController with a Booking model
     * and DatabaseService.
     *
     * @param databaseService The DatabaseService instance for database
     * operations.
     */
    public BookingController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Booking getModel() {
        return model;
    }

    public void setModel(Booking model) {
        this.model = model;
    }

    public CustomerForm getView() {
        return view;
    }

    public void setView(CustomerForm view) {
        this.view = view;
    }
}
