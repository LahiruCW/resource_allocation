package com.abc.database;

//import necessary packages
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * DatabaseService class provides methods for executing SQL queries on a given
 * database connection.
 *
 * @author LahiruCW
 * @version 1.0
 */
public class DatabaseService {
    
    private final Connection connection;

    /**
     * Creates a new DatabaseService instance with the specified connection.
     *
     * @param connection The {@code Connection} object to be used for database operations.
     */
    public DatabaseService(Connection connection) {
        
        //connection object not changed after set
        this.connection = connection;
    }
    
    /**
     * Executes a SELECT query with the specified SQL and parameters.
     *
     * @param sql The SQL query string.
     * @param params The parameters to be used in the query.
     * @return A {@code ResultSet} containing the results of the query.
     * @throws SQLException If an error occurs during the execution of the query.
     */
    public ResultSet executeSelect(String sql, Object... params) throws SQLException{
        PreparedStatement prepairedStatement = connection.prepareStatement(sql);
        
        for(int i = 0; i < params.length; i++){
            prepairedStatement.setObject(i+1, params[i]);
        }
        
        return prepairedStatement.executeQuery();
    }
    
    /**
     * Executes an UPDATE query with the specified SQL and parameters.
     *
     * @param sql The SQL query string.
     * @param params The parameters to be used in the query.
     * @return The number of rows affected by the query.
     * @throws SQLException If an error occurs during the execution of the query.
     */
    public int executeUpdate(String sql, Object...params) throws SQLException{
        
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            for(int i=0;i<params.length;i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            
            return preparedStatement.executeUpdate();
        }
    }
}
