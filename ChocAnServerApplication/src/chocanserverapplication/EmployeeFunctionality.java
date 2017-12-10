/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * Employee Functionality
 * 
 * This class will contain all the different static methods required to serve
 * a Employee Terminal Client
 */

package chocanserverapplication;

import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;

public class EmployeeFunctionality 
{
    /**
     * Method used to verify a given username and password against the member
     * table in the database.
     * 
     * @param username memberID of an employee
     * @param password password of an employee
     * @return 0 if credentials match an employee, 1 if they match a manager, else 2
     */
    public static int verifyEmployeeLogin(String username, String password)
    {
        //query the employee table in the database for a tuple with an 
        //employeeID that matches the username and a password that matches the 
        //password.
        
        //if the query returns a result and isManager is true, return 1
        
        //if the query returns a result and isManager is false, return 0
        
        //if the query returns no results, return 2
        
        return 0;
    }
    
    /**
     * takes an employee struct in as input, validates the data in the struct,
     * adds the struct to the Employee table of the database, returns successful
     * or not
     * 
     * @param employeeCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createEmployee(Employee employeeCreationData)
    {
        //call the verifyData function from the employee struct on employee data
        //if verifyData returns false, return 4
        
        //try
            //run an insertion command on the employee table in the database
            //using all of the data in employeeData
        //catch all
            //return 1
        
        //return 0
        
        return 0;
    }
    
    /**
     * Updates an Employee entry in the database
     * 
     * @param employeeCreationData data of employee to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateEmployee(Employee employeeCreationData)
    {
        //call the verifyData function from employeeCreationData
        
        //if verifyData returns false, return 4
        
        //try
            //run an update common on the employee table in the database using
            //all of the data in employeeData
        //catch all
            //return 1
        //return 0
        
        return 0;
    }
    
    /**
     * takes a provider struct in as input, validates the data in the struct,
     * adds the struct to the provider table of the database, returns successful
     * or not
     * 
     * @param providerCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createProvider(Provider providerCreationData)
    {
        //call the verifyData function from the provider struct on provider data
        //if verifyData returns false, return 4
        
        //try
            //run an insertion command on the provider table in the database
            //using all of the data in providerCreationData
        //catch all
            //return 1
        
        //return 0
        
        return 0;
    }
    
    /**
     * Updates an Provider entry in the database
     * 
     * @param providerCreationData data of provider to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateProvider(Provider providerCreationData)
    {
        //call the verifyData function from providerCreationData
        
        //if verifyData returns false, return 4
        
        //try
            //run an update common on the provider table in the database using
            //all of the data in providerCreationData
        //catch all
            //return 1
        //return 0
        
        return 0;
    }
    
    /**
     * takes an member struct in as input, validates the data in the struct,
     * adds the struct to the member table of the database, returns successful
     * or not
     * 
     * @param memberCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createMember(Member memberCreationData)
    {
        //call the verifyData function from the member struct on member data
        //if verifyData returns false, return 4
        
        //try
            //run an insertion command on the member table in the database
            //using all of the data in memberCreationData
        //catch all
            //return 1
        
        //return 0
        
        return 0;
    }
    
    /**
     * Updates an member entry in the database
     * 
     * @param memberCreationData data of member to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateMember(Member memberCreationData)
    {
        //call the verifyData function from memberCreationData
        
        //if verifyData returns false, return 4
        
        //try
            //run an update common on the member table in the database using
            //all of the data in memberCreationData
        //catch all
            //return 1
        //return 0
        
        return 0;
    }
    
    /**
     * takes an service struct in as input, validates the data in the struct,
     * adds the struct to the servicedirectory table of the database, returns successful
     * or not
     * 
     * @param serviceCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createService(Service serviceCreationData)
    {
        //call the verifyData function from the service struct on service data
        //if verifyData returns false, return 4
        
        //try
            //run an insertion command on the service table in the database
            //using all of the data in serviceCreationData
        //catch all
            //return 1
        
        //return 0
        
        return 0;
    }
    
    /**
     * Updates an Service entry in the database
     * 
     * @param serviceCreationData data of service to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateService(Service serviceCreationData)
    {
        //call the verifyData function from serviceCreationData
        
        //if verifyData returns false, return 4
        
        //try
            //run an update common on the service table in the database using
            //all of the data in serviceCreationData
        //catch all
            //return 1
        //return 0
        
        return 0;
    }
}
