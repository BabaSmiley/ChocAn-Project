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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class DatabaseQueries 
{
    private static final String connectionString = "jdbc:mysql://localhost:3306/chocan?autoReconnect=true&useSSL=false";
    private static final String databaseUsername = "root";
    private static final String databasePassword = "";
    
    /**
     * gets a list of bills from the database that contain memberNumber and were
     * billed between endDate and endDate - 7 days
     * 
     * @param memberNumber
     * @param endDate
     * @return list of bills
     * @throws SQLException 
     */
    public static ArrayList<Bill> getServicesForMember(String memberNumber, LocalDateTime endDate) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Format endDateTime and derive startDateTime
        String formattedEndDateTime = "'" + endDate.getYear() + "-" + 
                String.format("%02d", endDate.getMonthValue()) + "-" + 
                String.format("%02d", endDate.getDayOfMonth()) + " " + 
                String.format("%02d", endDate.getHour()) + ":" +
                String.format("%02d", endDate.getMinute()) + ":" + 
                String.format("%02d", endDate.getSecond()) + "'";
        
        LocalDateTime startDate = endDate.plusDays(-7);
        
        String formattedStartDateTime = "'" + startDate.getYear() + "-" + 
                String.format("%02d", startDate.getMonthValue()) + "-" + 
                String.format("%02d", startDate.getDayOfMonth()) + " " + 
                String.format("%02d", startDate.getHour()) + ":" +
                String.format("%02d", startDate.getMinute()) + ":" + 
                String.format("%02d", startDate.getSecond()) + "'";
        
        //Create sql statement
        String statement = "select * from bill where "
                + "memberNumber = ? and (dateTimeBilled between " + 
                formattedStartDateTime + " and " + formattedEndDateTime + ")";
        
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, memberNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into list of bills
        ArrayList<Bill> billList = new ArrayList<Bill>();
        
        while(myRs.next())
        {
            Bill newBill = new Bill();
            
            newBill.providerNumber = myRs.getString("providerNumber");
            newBill.memberNumber = myRs.getString("memberNumber");
            newBill.serviceNumber = myRs.getString("serviceNumber");
            newBill.dateTimeBilled = myRs.getTimestamp("dateTimeBilled").toLocalDateTime();
            newBill.dateOfService = myRs.getTimestamp("dateOfService").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            newBill.comments = myRs.getString("comments");
            
            billList.add(newBill);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the billList
        return billList;
    }
    
    /**
     * gets a list of bills from the database that contain providerNumber and were
     * billed between endDate and endDate - 7 days
     * 
     * @param providerNumber
     * @param endDate
     * @return list of bills
     * @throws SQLException 
     */
    public static ArrayList<Bill> getServicesForProvider(String providerNumber, LocalDateTime endDate) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Format endDateTime and derive startDateTime
        String formattedEndDateTime = "'" + endDate.getYear() + "-" + 
                String.format("%02d", endDate.getMonthValue()) + "-" + 
                String.format("%02d", endDate.getDayOfMonth()) + " " + 
                String.format("%02d", endDate.getHour()) + ":" +
                String.format("%02d", endDate.getMinute()) + ":" + 
                String.format("%02d", endDate.getSecond()) + "'";
        
        LocalDateTime startDate = endDate.plusDays(-7);
        
        String formattedStartDateTime = "'" + startDate.getYear() + "-" + 
                String.format("%02d", startDate.getMonthValue()) + "-" + 
                String.format("%02d", startDate.getDayOfMonth()) + " " + 
                String.format("%02d", startDate.getHour()) + ":" +
                String.format("%02d", startDate.getMinute()) + ":" + 
                String.format("%02d", startDate.getSecond()) + "'";
        
        //Create sql statement
        String statement = "select * from bill where "
                + "providerNumber = ? and (dateTimeBilled between " + 
                formattedStartDateTime + " and " + formattedEndDateTime + ")";
        
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, providerNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into list of bills
        ArrayList<Bill> billList = new ArrayList<Bill>();
        
        while(myRs.next())
        {
            Bill newBill = new Bill();
            
            newBill.providerNumber = myRs.getString("providerNumber");
            newBill.memberNumber = myRs.getString("memberNumber");
            newBill.serviceNumber = myRs.getString("serviceNumber");
            newBill.dateTimeBilled = myRs.getDate("dateTimeBilled").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            newBill.dateOfService = myRs.getDate("dateOfService").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            newBill.comments = myRs.getString("comments");
            
            billList.add(newBill);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the billList
        return billList;
    }
    
    /**
     * gets a list of bills from the database that were billed between endDate
     * and endDate - 7 days.
     * 
     * @param endDate
     * @return list of bills
     * @throws SQLException 
     */
    public static ArrayList<Bill> getServicesBeforeDate(LocalDateTime endDate) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Format endDateTime and derive startDateTime
        String formattedEndDateTime = "'" + endDate.getYear() + "-" + 
                String.format("%02d", endDate.getMonthValue()) + "-" + 
                String.format("%02d", endDate.getDayOfMonth()) + " " + 
                String.format("%02d", endDate.getHour()) + ":" +
                String.format("%02d", endDate.getMinute()) + ":" + 
                String.format("%02d", endDate.getSecond()) + "'";
        
        LocalDateTime startDate = endDate.plusDays(-7);
        
        String formattedStartDateTime = "'" + startDate.getYear() + "-" + 
                String.format("%02d", startDate.getMonthValue()) + "-" + 
                String.format("%02d", startDate.getDayOfMonth()) + " " + 
                String.format("%02d", startDate.getHour()) + ":" +
                String.format("%02d", startDate.getMinute()) + ":" + 
                String.format("%02d", startDate.getSecond()) + "'";
        
        //Create sql statement
        String statement = "select * from bill where "
                + "(dateTimeBilled between " + formattedStartDateTime + 
                " and " + formattedEndDateTime + ")" + 
                "order by providerNumber asc";
        
        myStmt = myConn.prepareStatement(statement);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into list of bills
        ArrayList<Bill> billList = new ArrayList<Bill>();
        
        while(myRs.next())
        {
            Bill newBill = new Bill();
            
            newBill.providerNumber = myRs.getString("providerNumber");
            newBill.memberNumber = myRs.getString("memberNumber");
            newBill.serviceNumber = myRs.getString("serviceNumber");
            newBill.dateTimeBilled = myRs.getDate("dateTimeBilled").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            newBill.dateOfService = myRs.getDate("dateOfService").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            newBill.comments = myRs.getString("comments");
            
            billList.add(newBill);
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the billList
        return billList;
    }
    
    /**
     * gets the service associated with serviceNumber from the database
     * 
     * @param serviceNumber
     * @return a service
     * @throws SQLException 
     */
    public static Service getService(String serviceNumber) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        String statement = "select * from servicedirectory where "
                + "serviceNumber = ?";
        
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, serviceNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into service
        Service requestedService = new Service();
        
        if(myRs.next())
        {
            requestedService.serviceNumber = myRs.getString("serviceNumber");
            requestedService.name = myRs.getString("name");
            requestedService.fee = myRs.getDouble("fee");
            requestedService.isActive = myRs.getBoolean("isActive");
        }
        else
        {
            requestedService.serviceNumber = "ERROR: SERVICE NOT FOUND";
            requestedService.name = "ERROR: SERVICE NOT FOUND";
            requestedService.fee = 0;
            requestedService.isActive = false;
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the service
        return requestedService;
    }
    
    /**
     * gets the member associated with memberNumber in the database
     * 
     * @param memberNumber
     * @return a member
     * @throws SQLException 
     */
    public static Member getMember(String memberNumber) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        String statement = "select * from member where "
                + "memberNumber = ?";
        
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, memberNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into member
        Member requestedMember = new Member();
        
        if(myRs.next())
        {
            requestedMember.memberNumber = myRs.getString("memberNumber");
            requestedMember.name = myRs.getString("name");
            requestedMember.emailAddress = myRs.getString("emailAddress");
            requestedMember.streetAddress = myRs.getString("streetAddress");
            requestedMember.city = myRs.getString("city");
            requestedMember.state = myRs.getString("state");
            requestedMember.zipCode = myRs.getString("zipCode");
            requestedMember.isValid = myRs.getBoolean("isValid");
            requestedMember.validityReason = myRs.getString("validityReason");
            requestedMember.isActive = myRs.getBoolean("isActive");
        }
        else
        {
            requestedMember.memberNumber = "ERROR: MEMBER NOT FOUND";
            requestedMember.name = "ERROR: MEMBER NOT FOUND";
            requestedMember.emailAddress = "ERROR: MEMBER NOT FOUND";
            requestedMember.streetAddress = "ERROR: MEMBER NOT FOUND";
            requestedMember.city = "ERROR: MEMBER NOT FOUND";
            requestedMember.state = "ERROR: MEMBER NOT FOUND";
            requestedMember.zipCode = "ERROR: MEMBER NOT FOUND";
            requestedMember.isValid = false;
            requestedMember.validityReason = "ERROR: MEMBER NOT FOUND";
            requestedMember.isActive = false;
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the service
        return requestedMember;
    }
    
    /**
     * gets the provider associated with providerNumber in the database
     * 
     * @param providerNumber
     * @return a provider
     * @throws SQLException 
     */
    public static Provider getProvider(String providerNumber) throws SQLException
    {
        //setup connection to database
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection(connectionString,
                databaseUsername, databasePassword);
        
        //Create sql statement
        String statement = "select * from provider where "
                + "providerNumber = ?";
        
        myStmt = myConn.prepareStatement(statement);
        myStmt.setString(1, providerNumber);
        
        //execute statement and store result
        myRs = myStmt.executeQuery();
        
        //Convert Result Set into provider
        Provider requestedProvider = new Provider();
        
        if(myRs.next())
        {
            requestedProvider.providerNumber = myRs.getString("providerNumber");
            requestedProvider.name = myRs.getString("name");
            requestedProvider.emailAddress = myRs.getString("emailAddress");
            requestedProvider.streetAddress = myRs.getString("streetAddress");
            requestedProvider.city = myRs.getString("city");
            requestedProvider.state = myRs.getString("state");
            requestedProvider.zipCode = myRs.getString("zipCode");
            requestedProvider.isActive = myRs.getBoolean("isActive");
        }
        else
        {
            requestedProvider.providerNumber = "ERROR: MEMBER NOT FOUND";
            requestedProvider.name = "ERROR: MEMBER NOT FOUND";
            requestedProvider.emailAddress = "ERROR: MEMBER NOT FOUND";
            requestedProvider.streetAddress = "ERROR: MEMBER NOT FOUND";
            requestedProvider.city = "ERROR: MEMBER NOT FOUND";
            requestedProvider.state = "ERROR: MEMBER NOT FOUND";
            requestedProvider.zipCode = "ERROR: MEMBER NOT FOUND";
            requestedProvider.isActive = false;
        }
        
        //cleanup connection
        myRs.close();
        myStmt.close();
        myConn.close();
        
        //return the service
        return requestedProvider;
    }
    
    /**
     * queries database for employee with employeeNumber and password.
     * 
     * @param employeeNumber
     * @param password
     * @return 0 if employee login, 1 if manager login, 2 if bad login, 3 if database down.
     */
    public static int employeeLogin(String employeeNumber, String password)
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
            String statement = "select isManager, password from employee where "
                    + "employeeNumber = ? and password = ? and isActive = true";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, employeeNumber);
            myStmt.setString(2, password);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            int returnValue;
            
            //if there is a value returned, save true, otherwise save false
            if (myRs.next())
            {
                if (myRs.getBoolean("isManager"))
                    returnValue = 1;
                else
                    returnValue = 0;
            }
            else
            {
                returnValue = 2;
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return returnValue;
        } catch(Exception e)
        {
            return 3;
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
            newEmployee.isManager = myRs.getBoolean("isManager");
            
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
    public static boolean insertEmployee(Employee employeeData)
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
                        + ", ? , ? , ?)";
                
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
                myStmt.setBoolean(10, employeeData.isManager);
                
                //execute query
                myStmt.executeUpdate();
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
    public static boolean updateEmployee(Employee employeeData)
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
                        + "isManager = ? "
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
                myStmt.setBoolean(9, employeeData.isManager);
                myStmt.setString(10, employeeData.employeeNumber);
                
                //execute query
                myStmt.executeUpdate();
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
                statement = "insert into provider values( ? , ? , ? , ? , ? , ? , ? "
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
                
                //execute query
                myStmt.executeUpdate();
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
     * Updates provider entry in the database if it exists.
     * 
     * @param providerData provider to be updated
     * @return true if update successful
     */
    public static boolean updateProvider(Provider providerData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that providerNumber exists in the table
            //Create sql statement
            String statement = "select providerNumber from provider where "
                    + "providerNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, providerData.providerNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = false;
            
            //if there is a value returned, providerNumber does exist in table
            if (myRs.next())
            {
                returnValue = true;
            }
            
            //if providerNumber exists in table, update the provider
            if (returnValue)
            {
                //Create sql statement
                statement = "update provider "
                        + "set password = ? , "
                        + "name = ? , "
                        + "emailAddress = ? , "
                        + "streetAddress = ? , "
                        + "city = ? , "
                        + "state = ? , "
                        + "zipCode = ? , "
                        + "isActive = ? "
                        + "where providerNumber = ?";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, providerData.password);
                myStmt.setString(2, providerData.name);
                myStmt.setString(3, providerData.emailAddress);
                myStmt.setString(4, providerData.streetAddress);
                myStmt.setString(5, providerData.city);
                myStmt.setString(6, providerData.state);
                myStmt.setString(7, providerData.zipCode);
                myStmt.setBoolean(8, providerData.isActive);
                myStmt.setString(9, providerData.providerNumber);
                
                //execute query
                myStmt.executeUpdate();
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
     * Inserts new Member into the member table if the memberNumber isn't taken
     * 
     * @param memberData member to be inserted into the database
     * @return true if the insertion succeeds.
     */
    public static boolean insertMember(Member memberData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that memberNumber isn't taken.
            //Create sql statement
            String statement = "select memberNumber from member where "
                    + "memberNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, memberData.memberNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = true;
            
            //if there is a value returned, providerNumber is taken, return false
            if (myRs.next())
            {
                returnValue = false;
            }
            
            //if memberNumber isn't taken, insert the new member
            if (returnValue)
            {
                //Create sql statement
                statement = "insert into member values( ? , ? , ? , ? , ? , ? , ? "
                        + ", ? , ? , ? , ?)";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, memberData.memberNumber);
                myStmt.setString(2, memberData.name);
                myStmt.setString(3, memberData.emailAddress);
                myStmt.setString(4, memberData.streetAddress);
                myStmt.setString(5, memberData.city);
                myStmt.setString(6, memberData.state);
                myStmt.setString(7, memberData.zipCode);
                myStmt.setBoolean(8, true);
                myStmt.setBoolean(9, memberData.isValid);
                myStmt.setString(10, memberData.validityReason);
                myStmt.setBoolean(11, memberData.isActive);
                
                //execute query
                myStmt.executeUpdate();
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
     * Updates member entry in the database if it exists
     * 
     * @param memberData member to be updated
     * @return true if the update was successful
     */
    public static boolean updateMember(Member memberData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that memberNumber exists in the table
            //Create sql statement
            String statement = "select memberNumber from member where "
                    + "memberNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, memberData.memberNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = false;
            
            //if there is a value returned, memberNumber does exist in table
            if (myRs.next())
            {
                returnValue = true;
            }
            
            //if memberNumber exists in table, update the member
            if (returnValue)
            {
                //Create sql statement
                statement = "update member "
                        + "set name = ? , "
                        + "emailAddress = ? , "
                        + "streetAddress = ? , "
                        + "city = ? , "
                        + "state = ? , "
                        + "zipCode = ? , "
                        + "isUpdated = ? , "
                        + "isValid = ? , "
                        + "validityReason = ? , "
                        + "isActive = ? "
                        + "where memberNumber = ?";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, memberData.name);
                myStmt.setString(2, memberData.emailAddress);
                myStmt.setString(3, memberData.streetAddress);
                myStmt.setString(4, memberData.city);
                myStmt.setString(5, memberData.state);
                myStmt.setString(6, memberData.zipCode);
                myStmt.setBoolean(7, true);
                myStmt.setBoolean(8, memberData.isValid);
                myStmt.setString(9, memberData.validityReason);
                myStmt.setBoolean(10, memberData.isActive);
                myStmt.setString(11, memberData.memberNumber);
                
                //execute query
                myStmt.executeUpdate();
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
     * Inserts new service into the servicedirectory table if the serviceNumber
     * isn't taken
     * 
     * @param serviceData service to be inserted
     * @return true if the insertion succeeded
     */
    public static boolean insertService(Service serviceData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that serviceNumber isn't taken.
            //Create sql statement
            String statement = "select serviceNumber from servicedirectory where "
                    + "serviceNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, serviceData.serviceNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = true;
            
            //if there is a value returned, serviceNumber is taken, return false
            if (myRs.next())
            {
                returnValue = false;
            }
            
            //if serviceNumber isn't taken, insert the new service
            if (returnValue)
            {
                //Create sql statement
                statement = "insert into servicedirectory values( ? , ? , ? , ?)";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, serviceData.serviceNumber);
                myStmt.setString(2, serviceData.name);
                myStmt.setDouble(3, serviceData.fee);
                myStmt.setBoolean(4, serviceData.isActive);
                
                //execute query
                myStmt.executeUpdate();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return returnValue;
        } catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Updates service entry in the database if it exists
     * 
     * @param serviceData service to be updated
     * @return true if the update was successful
     */
    public static boolean updateService(Service serviceData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that serviceNumber exists in the table
            //Create sql statement
            String statement = "select serviceNumber from servicedirectory where "
                    + "serviceNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, serviceData.serviceNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            boolean returnValue = false;
            
            //if there is a value returned, serviceNumber does exist in table
            if (myRs.next())
            {
                returnValue = true;
            }
            
            //if serviceNumber exists in table, update the service
            if (returnValue)
            {
                //Create sql statement
                statement = "update servicedirectory "
                        + "set name = ? , "
                        + "fee = ? , "
                        + "isActive = ? "
                        + "where serviceNumber = ?";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, serviceData.name);
                myStmt.setDouble(2, serviceData.fee);
                myStmt.setBoolean(3, serviceData.isActive);
                myStmt.setString(4, serviceData.serviceNumber);
                
                //execute query
                myStmt.executeUpdate();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
            
            //return the boolean
            return returnValue;
        } catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Inserts new bill into the bill table if the bill is unique and if providerNumber,
     * memberNumber, and serviceNumber all have entries in their respective tables
     * 
     * @param billData bill to be inserted into the database
     * @return true if the insert is successful
     */
    public static boolean insertBill(Bill billData)
    {
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            boolean returnValue = true;
            //Check that ProviderNumber exists in provider
            //Create sql statement
            String statement = "select providerNumber from provider where "
                    + "providerNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, billData.providerNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            //if there isn't a value returned, providerNumber doesn't exist. Fail insert
            if(!myRs.next())
            {
                returnValue = false;
            }
            
            //Check that MemberNumber exists in member
            //Create sql statement
            statement = "select memberNumber from member where "
                    + "memberNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, billData.memberNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            //if there isn't a value returned, memberNumber doesn't exist. Fail insert
            if(!myRs.next())
            {
                returnValue = false;
            }
            
            //Check that ServiceNumber exists in servicedirectory
            //Create sql statement
            statement = "select serviceNumber from servicedirectory where "
                    + "serviceNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, billData.serviceNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            //if there isn't a value returned, serviceNumber doesn't exist. Fail insert
            if(!myRs.next())
            {
                returnValue = false;
            }
            
            //Check that bill is unique
            //Format dateTimeBilled
            String formattedDateTimeBilled = "'" + billData.dateTimeBilled.getYear() + "-" + 
                String.format("%02d", billData.dateTimeBilled.getMonthValue()) + "-" + 
                String.format("%02d", billData.dateTimeBilled.getDayOfMonth()) + " " + 
                String.format("%02d", billData.dateTimeBilled.getHour()) + ":" +
                String.format("%02d", billData.dateTimeBilled.getMinute()) + ":" + 
                String.format("%02d", billData.dateTimeBilled.getSecond()) + "'";
            
            //Create sql statement
            statement = "select serviceNumber from bill where "
                    + "providerNumber = ? and memberNumber = ? and "
                    + "serviceNumber = ? and dateTimeBilled = "
                    + formattedDateTimeBilled;
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, billData.providerNumber);
            myStmt.setString(2, billData.memberNumber);
            myStmt.setString(3, billData.serviceNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            //if there is a value returned, date isn't unqiue, return false
            if (myRs.next())
            {
                returnValue = false;
            }
            
            //if bill is unique, insert the new bill
            if (returnValue)
            {
                //Format dateOfService
                String formattedDateOfService = "'" + billData.dateOfService.getYear() + "-" + 
                String.format("%02d", billData.dateOfService.getMonthValue()) + "-" + 
                String.format("%02d", billData.dateOfService.getDayOfMonth()) + "'";
                
                //Create sql statement
                statement = "insert into bill values( ? , ? , ? , " + 
                        formattedDateTimeBilled + " , " + formattedDateOfService + 
                        " , ? )";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setString(1, billData.providerNumber);
                myStmt.setString(2, billData.memberNumber);
                myStmt.setString(3, billData.serviceNumber);
                myStmt.setString(4, billData.comments);
                
                //execute query
                myStmt.executeUpdate();
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

    public static boolean updateMemberStatus(Member memberData) 
    {
        boolean returnValue = false;
        
        try
        {
            //setup connection to database
            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;
            
            myConn = DriverManager.getConnection(connectionString,
                    databaseUsername, databasePassword);
            
            //Check that memberNumber exists in the table
            //Create sql statement
            String statement = "select memberNumber from member where "
                    + "memberNumber = ?";
            
            myStmt = myConn.prepareStatement(statement);
            myStmt.setString(1, memberData.memberNumber);
            
            //execute query and store result
            myRs = myStmt.executeQuery();
            
            //if there is a value returned, memberNumber does exist in table
            if (myRs.next())
            {
                returnValue = true;
            }
            
            //if memberNumber exists in table, update the member
            if (returnValue)
            {
                //Create sql statement
                statement = "update member "
                        + "isUpdated = false , "
                        + "validityReason = ? , "
                        + "isActive = ? , "
                        + "where memberNumber = ?";
                
                myStmt = myConn.prepareStatement(statement);
                myStmt.setBoolean(1, memberData.isValid);
                myStmt.setString(2, memberData.validityReason);
                myStmt.setString(3, memberData.memberNumber);
                
                //execute query
                myStmt.executeUpdate();
            }
            
            //cleanup connection
            myRs.close();
            myStmt.close();
            myConn.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //return the boolean
        return returnValue;
    }
}