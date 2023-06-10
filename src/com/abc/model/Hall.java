package com.abc.model;

//import necessary packages
import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * The Hall class represents a hall in a GUI application for managing
 * hall bookings. It stores information about the hall, such as its
 * ID, name, capacity, availability, and a list of bookings.
 * @author LahiruCW
 * @version 1.0
 */
public class Hall implements Serializable {

    private String id;
    private String name;
    private int capacity;
    private boolean availability;
    private File listOfBooking; //this can be replace by an arrayList

    /**
     * Constructs a new Hall object with the specified parameters.
     *
     * @param id           the ID of the hall (cannot be null or empty)
     * @param name         the name of the hall (cannot be null or empty)
     * @param capacity     the capacity of the hall (must be greater than 0)
     * @param availability the initial availability of the hall
     * @param listOfBooking the list of bookings file associated with the hall (cannot be null)
     */
    public Hall(String id, String name, int capacity, boolean availability, File listOfBooking) {

        this.id = id; //id not be changed after constrcuting the object
        setName(name);
        setCapacity(capacity);
        setAvailability(availability);
        setListOfBooking(listOfBooking);
    }
    
    public Hall(String id, String name, int capacity){
        this(id,name,capacity,true, new File("com/abc/resources/halls.ser"));
    }
    
    public Hall(String id){
        this.id = id;
    }

    /**
     * Returns the ID of the hall.
     *
     * @return the hall ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the hall.
     *
     * @return the hall name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the hall
     * 
     * @param name The name of the hall
     */
    public void setName(String name) {
        if (!isNameValid(name)) {
            this.name = name;
        }else {
            JOptionPane.showMessageDialog(null,"Hall name can not be empty!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the capacity of the hall.
     *
     * @return the hall capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the hall
     * 
     * @param capacity The capacity of the hall.
     */
    public void setCapacity(int capacity) {
        if (isCapacityValid(capacity)) {
            this.capacity = capacity;
        }else {
            JOptionPane.showMessageDialog(null,"Capacity can not be negative!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isAvailability() {
        return availability;
    }

    /**
     * Set the availability of that hall
     * Assumption : In here only considered the date. Time is not considered
     * @param availability The availability of the hall of a particular date or continuous period
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public File getListOfBooking() {
        return listOfBooking;
    }

      /**
     * Set the List of bookings for a hall
     * Assumption : In here only considered the date. Time is not considered
     * @param listOfBooking The file containing the all booking ids with associate dates of a hall
     */
    public void setListOfBooking(File listOfBooking) {
        //there must be no any bookings
        //therefore this can be empty
        this.listOfBooking = listOfBooking;
    }

    //seperating the validations //DO NOT CHANGE OR MODIFY
    public boolean isNameValid(String name) {
        
        return name == null || name.trim().isEmpty();
    }

    public boolean isCapacityValid(int capacity) {
        
        return capacity >= 0;
    }
}
