package com.abc.database;

//importing necessary libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * DatabaseConnection class provides a single instance of a database connection
 * This ensures that only one database connection is maintained throughout the
 * application.
 *
 * <p>
 * Usage:
 * <pre>
 * {@code
 * Connection connection = DatabaseConnection.getInstance().getConnection();}
 * </pre>
 * </p>
 *
 * @author LahiruCW
 * @version 1.0
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    //the database url
    private String url = "jdbc:mysql://localhost:3306/abc_company";

    //the username for the database
    private String username = "root";

    //the password for the database
    //private String password = "";

    /**
     * Private constructor that initializes the database connection. This
     * constructor is private to ensure that no other instances can be created.
     *
     * @throws SQLException if a database access error occurs.
     */
    
    private DatabaseConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username,null);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Database"
                    + "Connection Creation Failed: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the single database connection instance If the instance does not
     * exist or the connection is closed, a new instance is created.
     *
     * @return The single database connection instance
     * @throws SQLException if the database access error occurs
     */
    public static DatabaseConnection getInstance() throws SQLException {
        
        if(instance == null){
            instance = new DatabaseConnection();
        }else if(instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        
        return instance;
    }
    
    /**
     * Returns the Connection object for the database.
     * @return The Connection object for the database.
     */
    public Connection getConnection(){
        
        return connection;
    }
}
