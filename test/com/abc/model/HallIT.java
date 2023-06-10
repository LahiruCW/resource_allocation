package com.abc.model;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the Hall class. This class contains test methods for each
 * method in the Hall class to ensure proper functionality and validation.
 *
 * @author LahiruCW
 * @see com.abc.model.Hall
 * @version 1.0
 */
public class HallIT {

    /**
     * Test the Hall constructor to ensure proper initialization of fields.
     */
    @Test
    public void testConstructor() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        assertEquals("1", hall.getId());
        assertEquals("Hall 1", hall.getName());
        assertEquals(100, hall.getCapacity());
        assertTrue(hall.isAvailability());
        assertNull(hall.getListOfBooking());
    }

    /**
     * Test the setName method to ensure proper handling of valid and invalid names.
     */
    @Test
    public void testSetName() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        hall.setName("New Hall Name");
        assertEquals("New Hall Name", hall.getName());

        hall.setName("");
        assertEquals("New Hall Name", hall.getName());
    }

    /**
     * Test the setCapacity method to ensure proper handling of valid and invalid capacities.
     */
    @Test
    public void testSetCapacity() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        hall.setCapacity(200);
        assertEquals(200, hall.getCapacity());

        hall.setCapacity(-1);
        assertEquals(200, hall.getCapacity());
    }

    /**
     * Test the setAvailability method to ensure proper functionality.
     */
    @Test
    public void testSetAvailability() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        hall.setAvailability(false);
        assertFalse(hall.isAvailability());
    }

    /**
     * Test the setListOfBooking method to ensure proper functionality.
     */
    @Test
    public void testSetListOfBooking() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        File listOfBooking = new File("booking.txt");
        hall.setListOfBooking(listOfBooking);
        assertEquals(listOfBooking, hall.getListOfBooking());
    }

     /**
     * Test the isNameValid method to ensure proper validation of hall names.
     */
    @Test
    public void testIsNameValid() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        assertTrue(hall.isNameValid(""));
        assertTrue(hall.isNameValid(null));
        assertFalse(hall.isNameValid("Valid Name"));
    }

     /**
     * Test the isCapacityValid method to ensure proper validation of hall capacities.
     */
    @Test
    public void testIsCapacityValid() {
        Hall hall = new Hall("1", "Hall 1", 100, true, null);
        assertFalse(hall.isCapacityValid(-1));
        assertTrue(hall.isCapacityValid(0));
        assertTrue(hall.isCapacityValid(100));
    }
}
