package com.abc.model;

/**
 * <h1>Administrator</h1>
 * The Administrator class represents an administrator user in the system.
 * It extends the User class and inherits its properties and methods.
 * <p>
 * Administrators have additional privileges and can perform specific actions
 * that regular users cannot.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class Administrator extends User {
    
    
    /**
     * Constructor for the Administrator class.
     * 
     * @param id         The unique identifier for the administrator.
     * @param firstName  The first name of the administrator.
     * @param secondName The second name of the administrator.
     * @param email      The email address of the administrator.
     * @param password   The password for the administrator's account.
     */
    public Administrator(String id,
            String firstName, 
            String secondName, 
            String email, 
            String password) {
        super(id, firstName, secondName, email, password);
    }
    
    public Administrator(String id, String password){
        super(id,password);
    }
    
    public Administrator(String id, String firstName, String password){
        super(id,firstName,password); 
    }
}
