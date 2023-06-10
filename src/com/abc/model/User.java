package com.abc.model;

//importing necessary packages
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * This is an abstract class representing a User
 * 
 * @author LahiruCW
 */

public abstract class User {

    //email validation specified by the RFC 5322 standard
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    
    /**
     * The regular expression for password validation.
     * Ensures that the password contains at least one uppercase letter,
     * one lowercase letter, one digit, one special character from the set !@#$%^&*()_+-=,
     * and is at least 16 characters long.
     */
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=]).{16,}$";

    private String id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    
    /**
     * Constructor for the User class.
     *
     * @param id         The user's ID.
     * @param firstName  The user's first name.
     * @param secondName The user's second name.
     * @param email      The user's email address.
     * @param password   The user's password.
     */
    
    public User(String id, String firstName, String secondName, String email, String password) {
        this.id = id;
        setFirstName(firstName);
        setSecondName(secondName);
        setEmail(email);
        setPassword(password);
    }
    
    public User(String id, String firstName, String password){
        this.id = id;
        setFirstName(firstName);
        setPassword(password);
    }

    public User(String id, String password) {
        this.id = id;
        setPassword( password);
    }

    /**
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user's first name.
     * 
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        
        //first name is necessary
        
        this.firstName = firstName;
    }

    /**
     * @return The user's second name.
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Set the user's second name.
     * 
     * @param secondName The user's second name.
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email address.
     * 
     * @param email The user's email address.
     */
    public void setEmail(String email) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email address!"
                    + " Please provide a valid email address",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     * 
     * @param password The user's password.
     */
    public void setPassword(String password) {
        if (isPasswordValid(password)) {
            this.password = password;
        }else{
            JOptionPane.showMessageDialog(null, """
                                                Password not Strong! Password must contains,
                                                \nat least one uppercase letter
                                                \none lowercase letter
                                                \none digit
                                                \none special character from the set !@#$%^&*()_+-=
                                                \nThe password must also be at least 16 characters long.""",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //seperating the validations //PLEASE DO NOT CHANGE OR MODIFY

    /**
     * Validate the provided email address using the EMAIL_REGEX pattern.
     *
     * @param email The email address to validate.
     * @return true if the email address is valid, false otherwise.
     */
    public boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * Validate the provided password using the PASSWORD_REGEX pattern.
     * 
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public boolean isPasswordValid(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
