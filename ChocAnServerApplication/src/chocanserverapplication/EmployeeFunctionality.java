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
 * This class contains methods that check the integrity of data before it reaches
 * the database as either an insertion or deletion. These functions are for use
 * in the employee terminal.
 */

package chocanserverapplication;

import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;
import java.util.List;
import chocanserverapplication.DatabaseQueries;

public class EmployeeFunctionality 
{   
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
        if (employeeCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertEmployee(employeeCreationData);
        
        //if query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
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
        if (employeeCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateEmployee(employeeCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
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
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (providerCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertProvider(providerCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an Provider entry in the database
     * 
     * @param providerCreationData data of provider to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateProvider(Provider providerCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (providerCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateProvider(providerCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
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
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (memberCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertMember(memberCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an member entry in the database
     * 
     * @param memberCreationData data of member to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateMember(Member memberCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (memberCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateMember(memberCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
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
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (serviceCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertService(serviceCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an Service entry in the database
     * 
     * @param serviceCreationData data of service to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateService(Service serviceCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (serviceCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertService(serviceCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
}
