package com.abc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author LahiruCW
 */
public class DatabaseConnectionIT {
    
    public DatabaseConnectionIT() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class DatabaseConnection.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        DatabaseConnection expResult = null;
        DatabaseConnection result = DatabaseConnection.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class DatabaseConnection.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DatabaseConnection instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
