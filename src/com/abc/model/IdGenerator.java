package com.abc.model;

import java.util.Random;

/**
 * This class provides an implementation of a customer ID generator. The ID is
 * generated using a combination of a fixed prefix 'GU' and 5 random numbers
 * between 0 and 9.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class IdGenerator {

    /**
     * Generates a unique ID for a customer using a combination of 'GU' and 5
     * random numbers between 0 and 9.
     *
     * @return a unique ID for a customer
     */
    public String generateCustomerID() {
        StringBuilder id;
        id = new StringBuilder("GU");
        Random random = new Random();

        for (int i = 0; i < 5; i++) {

            //Generate a random number between 0 and 9
            int randomNumber = random.nextInt(10);
            id.append(randomNumber);
        }

        String generatedID = id.toString();
        return generatedID;
    }

    public String generateHallID() {

        StringBuilder id;
        id = new StringBuilder("ABC");
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            
            //Generate random number between 0 and 9
            int randomNumber = random.nextInt(10);
            id.append(randomNumber);
        }
        
        String generatedId = id.toString();
        return generatedId;
    }
    
        /**
     * Generates a unique booking ID.
     *
     * @return The generated booking ID.
     */
    public String generateBookingId() {

        StringBuilder id;
        id = new StringBuilder("BK");
        Random random = new Random();

        for (int i = 0; i < 7; i++) {

            //Generate a random number between 0 and 9
            int randomNumber = random.nextInt(10);
            id.append(randomNumber);
        }

        String generatedID = id.toString();
        return generatedID;
    }
}
