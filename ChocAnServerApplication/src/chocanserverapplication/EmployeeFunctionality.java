/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanserverapplication;

/**
 *
 * @author danie
 */
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
}
