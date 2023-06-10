package com.abc.model;

//importing necessary packages
import java.time.LocalDate;
import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Represents a booking made by a customer.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class Booking implements Serializable {

    private final String bookingId;
    private File listOfHalls;
    private LocalDate startDate;
    private LocalDate endDate;
    private final Customer customer;
    private LocalDate[] listOfDates;

    /**
     * Creates a new booking object with the given parameters.
     *
     * @param bookingId the ID of the booking. This is a randomly generated by
     * the system
     * @param listOfHalls the file containing a list of all Halls
     * @param startDate the start date of the booking
     * @param endDate the end date of the booking
     * @param customer the customer who made the booking. Customer is the
     * current user.
     */
    public Booking(String bookingId, File listOfHalls, LocalDate startDate, LocalDate endDate, Customer customer) {

        //booking Id will randomly generated and no need to be change after setting
        //this is the primary key of the booking table
        this.bookingId = bookingId;

        //In here customer is the current logged in user.
        //it must not be changed after setting his or her booking by himself or herself
        this.customer = customer;

        setListOfHalls(listOfHalls);
        setStartDate(startDate);
        setEndDate(endDate);

    }

    public LocalDate[] getListOfDates() {
        return listOfDates;
    }

    public void setListOfDates(LocalDate[] listOfDates) {
        this.listOfDates = listOfDates;
    }
    
    /**
     * Returns the ID of the booking.
     *
     * @return the booking ID
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Returns the file containing a list of all bookings.
     *
     * @return the file containing a list of all bookings
     */
    public File getListOfHalls() {
        return listOfHalls;
    }

    /**
     * Sets the file containing a list of all bookings.
     *
     * @param listOfHalls the file containing a list of all Halls
     */
    public void setListOfHalls(File listOfHalls) {
        this.listOfHalls = listOfHalls;
    }

    /**
     * Returns the start date of the booking.
     *
     * @return the start date of the booking
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the booking.
     *
     * @param startDate the start date of the booking
     */
    public void setStartDate(LocalDate startDate) {
        if (!isStartDateValid(startDate)) {
            this.startDate = startDate;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Date!"
                    + " Start Date is necessary and can not be a date after the end date", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Returns the end date of the booking.
     *
     * @return the end date of the booking
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the booking.
     *
     * @param endDate the end date of the booking
     */
    public void setEndDate(LocalDate endDate) {
        if (!isEndDateValid(endDate)) {
            this.endDate = endDate;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Date!"
                    + " End Date is necessary and can not be a date before the Start date", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the customer who made the booking.
     *
     * @return the customer who made the booking
     */
    public Customer getCustomer() {
        return customer;
    }

    //seperating the validations //DO NOT MODIFY THE CODE
    
    /**
     * use to check the validity of the start date.
     *
     * @param startDate the start date to check
     * @return true if the start date is null or after the end date, false otherwise
     */
    public boolean isStartDateValid(LocalDate startDate) {
        return startDate == null || startDate.isAfter(this.endDate);
    }

    /**
     * use to check the validity of the end date.
     *
     * @param endDate the end date to check
     * @return true if the end date is null or before the start date, false otherwise
     */
    public boolean isEndDateValid(LocalDate endDate) {
        return endDate == null || endDate.isBefore(this.startDate);
    }
}
