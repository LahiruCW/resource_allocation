package com.abc.model;

import java.io.Serializable;
import javax.swing.JOptionPane;
import java.io.*;

/**
 * Customer class that extends User and implements Serializable.
 * Represents a customer with a list of bookings.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class Customer extends User implements Serializable {

    private File listOfBookings;

    private final String FILE_PATH = "/com/abc/resources/bookings.ser";

   /**
     * Constructor to create customers with zero bookings.
     * Bookings can be added later.
     *
     * @param id         customer ID
     * @param firstName  customer first name
     * @param secondName customer second name
     * @param email      customer email
     * @param password   customer password
     */
    public Customer(String id, String firstName, String secondName, String email, String password) {
        super(id, firstName, secondName, email, password);
        this.listOfBookings = new File(FILE_PATH);
    }
    
    public Customer(String id, String firstName, String password) {
        super(id, firstName, password);
    }

    /**
     * Getter for the list of bookings.
     *
     * @return File containing the list of bookings
     */
    public File getListOfBookings() {
        return listOfBookings;
    }

    /**
     * Setter for the list of bookings.
     * Validates the input file and shows an error message if the file is not valid.
     *
     * @param listOfBookings File containing the list of bookings
     */
    public void setListOfBookings(File listOfBookings) {
        if (isListValid(listOfBookings)) {
            this.listOfBookings = listOfBookings;
        }else{
            JOptionPane.showMessageDialog(null,
                    "File Not Found!", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Seperate the validations //DO NOT MODIFY THE CODE
    
    /**
     * Validates the list of bookings file.
     * Checks if the file exists and is a valid file.
     *
     * @param listOfBookings File containing the list of bookings
     * @return true if the file is valid, false otherwise
     */
    boolean isListValid(File listOfBookings) {
        return listOfBookings.exists() || listOfBookings.isFile();
    }

}
