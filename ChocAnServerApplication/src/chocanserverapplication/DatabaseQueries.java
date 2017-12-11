/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * Database Queries
 * 
 * This class will contain all the different static methods used to query the
 * database. All will return a ResultSet for the query.
 */

package chocanserverapplication;

import java.sql.*;
import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;
import chocanstructs.Bill;

public class DatabaseQueries 
{
    private static final String databaseUsername = "root";
    private static final String databasePassword = "";
    
    /**
     * queries database for employee with employeeNumber and password. Returns
     * true if found, false otherwise
     * 
     * @param employeeNumber
     * @param password
     * @return true if found, false if not found
     * @throws SQLException 
     */
    public static boolean employeeLoginQuery(String employeeNumber, String password) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chocan", 
                databaseUsername, databasePassword);
        
        //Create query
        myStmt = myConn.createStatement();
        
        String query = "select employeeNumber, password from employee where "
                + "employeeNumber = " + employeeNumber + " and password = " + 
                password;
        
        //execute query and store result
        myRs = myStmt.executeQuery(query);
        
        boolean returnValue;
        
        //if there is a value returned, save true, otherwise save false
        if (myRs.next())
        {
            returnValue = true;
        }
        else
        {
            returnValue = false;
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the boolean
        return returnValue;
    }
    
    public static boolean createEmployeeQuery(Employee employeeData)
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chocan", 
                databaseUsername, databasePassword);
        
        //Create query
        myStmt = myConn.createStatement();
        
        String query = "select employeeNumber, password from employee where "
                + "employeeNumber = " + employeeNumber + " and password = " + 
                password;
        
        //execute query and store result
        myRs = myStmt.executeQuery(query);
        
        boolean returnValue;
        
        //if there is a value returned, save true, otherwise save false
        if (myRs.next())
        {
            returnValue = true;
        }
        else
        {
            returnValue = false;
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the boolean
        return returnValue;
    }
}
