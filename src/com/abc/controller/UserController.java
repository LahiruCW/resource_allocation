package com.abc.controller;

//import the necessary packages
import com.abc.model.User;
import com.abc.database.DatabaseService;

/**
 *
 * @author LahiruCW
 */
public abstract class UserController {
    
    //accept a DatabaseService object to handle the database operations
    final DatabaseService databseService;

    public UserController(DatabaseService databseService) {
        this.databseService = databseService;
    }
    
    //insert data into the database
    //for that statement must be created in the child class
    public abstract void insertData();
    
    //update data into the database
    //for that statement must be created in the child class
    public abstract void updateData();
    
    //delete data in the databse
    //statement must be created in the chil class
    public abstract void deleteData();
    
    //search data in the database
    //statement must be created in the child class
    public abstract void searchData();
    
}
