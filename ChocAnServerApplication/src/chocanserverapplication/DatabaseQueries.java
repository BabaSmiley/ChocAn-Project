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
import java.util.ArrayList;

public class DatabaseQueries 
{
    private static final String connectionString = "jdbc:mysql://localhost:3306/chocan";
    private static final String databaseUsername = "root";
    private static final String databasePassword = "";
    
    public static ArrayList<Service> getServicesForMember(String memberNumber, Date endDate)
    {
        return null;
    }
    
    public static ArrayList<Service> getServicesForProvider(String providerNumber, Date endDate)
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
    
    /**
     * returns a list of all members who have the flag isUpdated set to true
     * 
     * @return ArrayList of Members who have update flag set to trues
     * @throws SQLException 
     */
    public static ArrayList<Member> getUpdatedMembers() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select * from member where isUpdated = true";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Member>
        ArrayList<Member> memberList = new ArrayList<Member>();
        
        while(myRs.next())
        {
            Member newMember = new Member();
            newMember.memberNumber = myRs.getString("memberNumber");
            newMember.name = myRs.getString("name");
            newMember.emailAddress = myRs.getString("emailAddress");
            newMember.streetAddress = myRs.getString("streetAddress");
            newMember.city = myRs.getString("city");
            newMember.state = myRs.getString("state");
            newMember.zipCode = myRs.getString("zipCode");
            newMember.isValid = myRs.getBoolean("isValid");
            newMember.validityReason = myRs.getString("validityReason");
            newMember.isActive = myRs.getBoolean("isActive");
            
            memberList.add(newMember);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the memberList
        return memberList;
    }
    
    /**
     * sets the isUpdated flag of all members in the database to false
     * 
     * @return true if the statement completed without issue
     */
    public static boolean setAllMemberUpdatedFalse()
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            Statement myStmt = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Create sql statement
            myStmt = myConn.createStatement();
            
            String statement = "update member set isUpdated = true where isUpdated = false";
            
            //execute statement
            myStmt.executeUpdate(statement);
            
            //cleanup connection
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return true;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    /**
     * for each member in the list of members input, update the corresponding 
     * entry in the member table of the database to match the isValid and
     * validityReason of.
     * 
     * @param membersToUpdate list of members to update the isValid and
     * validityReason of.
     * @return 
     */
    public static boolean updateMemberStatuses(ArrayList<Member> membersToUpdate)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //prepare sql statement
            String statement = "update member set isValid = ? , "
                    + "validityReason = ? where memberNumber = ?";
            myStmt = myConn.prepareStatement(statement);
            
            //update all members in list
            for (Member currentMember : membersToUpdate)
            {
                //Fill statement variables
                myStmt.setBoolean(1, currentMember.isValid);
                myStmt.setString(2, currentMember.validityReason);
                myStmt.setString(3, currentMember.memberNumber);
                
                //execute statement
                myStmt.executeUpdate();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return true;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    /**
     * Checks the database to see if the member is valid. Returns "0" if valid,
     * "1"+validityReason if invalid. Inactive counts as invalid.
     * 
     * @param memberNumber
     * @returnReturns "0" if valid, "1"+validityReason if invalid. Inactive 
     * counts as invalid
     * @throws SQLException 
     */
    public static String verifyMember(String memberNumber) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        String statement = "select isValid, isActive, validityReason from member"
                + " where memberNumber = ?";
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, memberNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //extract output
        boolean memberFound = false;
        boolean isActive = false;
        boolean isValid = false;
        String validityReason = null;
        if (myRs.next())
        {
            memberFound = true;
            isActive = myRs.getBoolean("isActive");
            isValid = myRs.getBoolean("isValid");
            validityReason = myRs.getString("validityReason");
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //determine output
        String output;
        if (memberFound)
        {
            if (isActive)
            {
                if (isValid)
                {
                    output = "0";
                }
                else
                {
                    output = "1" + validityReason;
                }
            }
            else
            {
                output = "1Member Is Inactive";
            }
        }
        else
        {
            output = "1Member Does Not Exist";
        }
        
        //return the string
        return output;
    }
    
    /**
     * checks to see if the providerNumber and password combo matches an entry in
     * the database. Returns true if it does, false if it doesn't. Also returns
     * false if the database can't be queried for some reason.
     * 
     * @param providerNumber
     * @param password
     * @return true means verified, false means not verified or database down
     */
    public static boolean verifyProvider(String providerNumber, String password)
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
            String statement = "select memberNumber from provider"
                    + " where providerNumber = ? and password = ? and isActive = true";
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, providerNumber);
            myStmt.setString(2, password);
            
            //execute statement and store result
            myRs = myStmt.executeQuery(statement);
            
            //determine if a match was found
            boolean providerFound = false;
            if (myRs.next())
            {
                providerFound = true;
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return providerFound;
        } catch(Exception e)
        {
            return false;
        }
    }
    
    /**
     * gets and returns a list of services in the servicedirectory database
     * with isActive set to true
     * 
     * @return list of services in database with isActive set to true
     * @throws SQLException 
     */
    public static ArrayList<Service> getActiveServices() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select serviceNumber, name, fee "
                + "from servicedirectory where isActive = true";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Service>
        ArrayList<Service> serviceList = new ArrayList<Service>();
        
        while(myRs.next())
        {
            Service newService = new Service();
            newService.serviceNumber = myRs.getString("serviceNumber");
            newService.name = myRs.getString("name");
            newService.fee = myRs.getDouble("fee");
            newService.isActive = true;
            
            serviceList.add(newService);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the serviceList
        return serviceList;
    }
    
    /**
     * gets and returns a list of all services in the servicedirectory table in 
     * the database
     * 
     * @return list of all services in database
     * @throws SQLException 
     */
    public static ArrayList<Service> getAllServices() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select serviceNumber, name, fee, isActive"
                + " from servicedirectory";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Service>
        ArrayList<Service> serviceList = new ArrayList<Service>();
        
        while(myRs.next())
        {
            Service newService = new Service();
            newService.serviceNumber = myRs.getString("serviceNumber");
            newService.name = myRs.getString("name");
            newService.fee = myRs.getDouble("fee");
            newService.isActive = myRs.getBoolean("isActive");
            
            serviceList.add(newService);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the serviceList
        return serviceList;
    }
    
    /**
     * Gets and returns a list of all employees from the employee table in the
     * database
     * 
     * @return list of employees
     * @throws SQLException 
     */
    public static ArrayList<Employee> getAllEmployees() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select * from employee";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Employee>
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        
        while(myRs.next())
        {
            Employee newEmployee = new Employee();
            newEmployee.employeeNumber = myRs.getString("employeeNumber");
            newEmployee.password = myRs.getString("password");
            newEmployee.name = myRs.getString("name");
            newEmployee.emailAddress = myRs.getString("emailAddress");
            newEmployee.streetAddress = myRs.getString("streetAddress");
            newEmployee.city = myRs.getString("city");
            newEmployee.state = myRs.getString("state");
            newEmployee.zipCode = myRs.getString("zipCode");
            newEmployee.isActive = myRs.getBoolean("isActive");
            
            employeeList.add(newEmployee);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the employeeList
        return employeeList;
    }
    
    /**
     * Gets and returns a list of all members from the member table in the
     * database
     * 
     * @return list of members
     * @throws SQLException 
     */
    public static ArrayList<Member> getAllMembers() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select * from member";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Member>
        ArrayList<Member> memberList = new ArrayList<Member>();
        
        while(myRs.next())
        {
            Member newMember = new Member();
            newMember.memberNumber = myRs.getString("employeeNumber");
            newMember.name = myRs.getString("name");
            newMember.emailAddress = myRs.getString("emailAddress");
            newMember.streetAddress = myRs.getString("streetAddress");
            newMember.city = myRs.getString("city");
            newMember.state = myRs.getString("state");
            newMember.zipCode = myRs.getString("zipCode");
            newMember.isValid = myRs.getBoolean("isValid");
            newMember.validityReason = myRs.getString("validityReason");
            newMember.isActive = myRs.getBoolean("isActive");
            
            memberList.add(newMember);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the memberList
        return memberList;
    }
    
    /**
     * Gets and returns a list of all providers from the provider table in the
     * database
     * 
     * @return list of providers
     * @throws SQLException 
     */
    public static ArrayList<Provider> getAllProviders() throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        myStmt = myConn.createStatement();
        
        String statement = "select * from provider";
        
        //execute statement and store result
        myRs = myStmt.executeQuery(statement);
        
        //Convert result set to List<Provider>
        ArrayList<Provider> providerList = new ArrayList<Provider>();
        
        while(myRs.next())
        {
            Provider newProvider = new Provider();
            newProvider.providerNumber = myRs.getString("providerNumber");
            newProvider.password = myRs.getString("password");
            newProvider.name = myRs.getString("name");
            newProvider.emailAddress = myRs.getString("emailAddress");
            newProvider.streetAddress = myRs.getString("streetAddress");
            newProvider.city = myRs.getString("city");
            newProvider.state = myRs.getString("state");
            newProvider.zipCode = myRs.getString("zipCode");
            newProvider.isActive = myRs.getBoolean("isActive");
            
            providerList.add(newProvider);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the providerList
        return providerList;
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
    
    /**
     * Inserts new Provider into the provider table if the providerNumber isn't
     * already taken
     * 
     * @param providerData provider to be inserted into the database
     * @return true if insert succeeded
     */
    public static boolean insertProvider(Provider providerData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that providerNumber isn't taken.
            //Create sql statement
            String statement = "select providerNumber from provider where "
                    + "providerNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, providerData.providerNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = true;
            
            //if there is a value returned, providerNumber is taken, return false
            if (myRs.next())
            {
                returnValue = false;
            }
            
            //if providerNumber isn't taken, insert the new provider
            if (returnValue)
            {
                //Create sql statement
                statement = "insert into employee values( ? , ? , ? , ? , ? , ? , ? "
                        + ", ? , ? )";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, providerData.providerNumber);
                myStmt.setString(2, providerData.password);
                myStmt.setString(3, providerData.name);
                myStmt.setString(4, providerData.emailAddress);
                myStmt.setString(5, providerData.streetAddress);
                myStmt.setString(6, providerData.city);
                myStmt.setString(7, providerData.state);
                myStmt.setString(8, providerData.zipCode);
                myStmt.setBoolean(9, providerData.isActive);
                
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