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
import java.util.List;

public class DatabaseQueries 
{
    private static final String connectionString = "jdbc:mysql://localhost:3306/chocan";
    private static final String databaseUsername = "root";
    private static final String databasePassword = "";
    
    public static List<Service> getServicesForMember(String memberNumber, Date endDate)
    {
        return null;
    }
    
    public static List<Service> getServicesForProvider(String providerNumber, Date endDate)
    {
        return null;
    }
    
    /**
     * queries database for employee with employeeNumber and password. Returns
     * true if found, false otherwise
     * 
     * @param employeeNumber
     * @param password
     * @return true if found, false if not found
     */
    public static boolean employeeLoginQuery(String employeeNumber, String password)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Create sql statement
            String statement = "select employeeNumber, password from employee where "
                    + "employeeNumber = ? and password = ? and isActive = true";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, employeeNumber);
            myStmt.setString(2, password);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
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
        } catch(Exception e)
        {
            return false;
        }
    }
    
    public static List<Member> getUpdatedMembers()
    {
        return null;
    }
    
    public static boolean updateMemberStatuses(List<Member> membersToUpdate)
    {
        return false;
    }
    
    public static boolean verifyMember(String memberNumber)
    {
        return false;
    }
    
    public static boolean verifyProvider(String providerNumber, String password)
    {
        return false;
    }
    
    public static List<Service> getActiveServices()
    {
        return null;
    }
    
    public static List<Service> getAllServices()
    {
        return null;
    }
    
    public static List<Employee> getAllEmployees()
    {
        return null;
    }
    
    public static List<Member> getAllMembers()
    {
        return null;
    }
    
    public static List<Provider> getAllProviders()
    {
        return null;
    }
    
    /**
     * Inserts new employee into the employee table if it the employeeNumber
     * isn't already taken.
     * 
     * @param employeeData employee struct containing data for insertion
     * @return true if insertion success, else false
     */
    public static boolean insertEmployeeQuery(Employee employeeData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that employeeNumber isn't taken.
            //Create sql statement
            String statement = "select employeeNumber from employee where "
                    + "employeeNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, employeeData.employeeNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = true;
            
            //if there is a value returned, employeeNumbe is taken, return false
            if (myRs.next())
            {
                returnValue = false;
            }
            
            //if employeeNumber isn't taken, insert the new employee
            if (returnValue)
            {
                //Create sql statement
                statement = "insert into employee values( ? , ? , ? , ? , ? , ? , ? "
                        + ", ? , ? )";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, employeeData.employeeNumber);
                myStmt.setString(2, employeeData.password);
                myStmt.setString(3, employeeData.name);
                myStmt.setString(4, employeeData.emailAddress);
                myStmt.setString(5, employeeData.streetAddress);
                myStmt.setString(6, employeeData.city);
                myStmt.setString(7, employeeData.state);
                myStmt.setString(8, employeeData.zipCode);
                myStmt.setBoolean(9, employeeData.isActive);
                
                //execute query and store result
                myRs = myStmt.executeQuery();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return returnValue;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    /**
     * Updates employee entry in the database if it exists. Returns true if it
     * succeeds in the update, false if it doesn't
     * 
     * @param employeeData employee struct containing data for insertion
     * @return true if update success, else false
     * @throws SQLException 
     */
    public static boolean updateEmployeeQuery(Employee employeeData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that employeeNumber exists in the table
            //Create sql statement
            String statement = "select employeeNumber from employee where "
                    + "employeeNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, employeeData.employeeNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = false;
            
            //if there is a value returned, employeeNumber does exist in table
            if (myRs.next())
            {
                returnValue = true;
            }
            
            //if employeeNumber exists in table, update the employee
            if (returnValue)
            {
                //Create sql statement
                statement = "update employee "
                        + "set password = ? , "
                        + "name = ? , "
                        + "emailAddress = ? , "
                        + "streetAddress = ? , "
                        + "city = ? , "
                        + "state = ? , "
                        + "zipCode = ? , "
                        + "isActive = ? , "
                        + "where employeeNumber = ?";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, employeeData.password);
                myStmt.setString(2, employeeData.name);
                myStmt.setString(3, employeeData.emailAddress);
                myStmt.setString(4, employeeData.streetAddress);
                myStmt.setString(5, employeeData.city);
                myStmt.setString(6, employeeData.state);
                myStmt.setString(7, employeeData.zipCode);
                myStmt.setBoolean(8, employeeData.isActive);
                myStmt.setString(9, employeeData.employeeNumber);
                
                //execute query and store result
                myRs = myStmt.executeQuery();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return returnValue;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean insertProvider(Provider providerData)
    {
        return false;
    }
    
    public static boolean updateProvider(Provider providerDate)
    {
        return false;
    }
    
    public static boolean insertMember(Member memberData)
    {
        return false;
    }
    
    public static boolean updateMember(Member memberData)
    {
        return false;
    }
    
    public static boolean insertService(Service serviceData)
    {
        return false;
    }
    
    public static boolean updateService(Service serviceData)
    {
        return false;
    }
    
    public static boolean insertBill(Bill billData)
    {
        return false;
    }
}