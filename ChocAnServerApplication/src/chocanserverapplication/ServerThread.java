/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * ServerThread Class
 * 
 * This class is part of the ChocAn Server Application.
 * 
 * Each instance of this class will be assigned a unique tcp client connection
 * and then run on their own thread. They will be responsible for serving their
 * client up until the client disconnects.
 */

package chocanserverapplication;

import java.net.Socket;

public class ServerThread implements Runnable
{
    private Socket client;
    
    /**
     * @param client the socket connected to the client to serve
     */
    public ServerThread(Socket client)
    {
        this.client = client;
    }

    /**
     * The main loop. This is run when the class is given its' own thread.
     * 
     * Should take input, figure out what to do with it, do it, repeat.
     */
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void processInput()
    {
        
    }
    
    /**
     * Method used to verify a given username and password against the member
     * table in the database.
     * 
     * @param username memberID of an employee
     * @param password password of an employee
     * @return 0 if credentials match an employee, 1 if they match a manager, else 2
     */
    public int verifyEmployeeLogin(String username, String password)
    {
        //query the employee table in the database for a tuple with an 
        //employeeID that matches the username and a password that matches the 
        //password.
        
        //if the query returns a result and isManager is true, return 1
        
        //if the query returns a result and isManager is false, return 0
        
        //if the query returns no results, return 2
        
        return 0;
    }
    
    public 
}
