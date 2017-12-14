/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * ChocAnEmployeeTerminal
 * 
 * This class will contain all the different static methods and variables required
 * to run the employee terminal functionality. It will need to be able to connect
 * to the ChocAn Server Application over tcp and be able to send and receive 
 * commands.
 */

package chocanemployeeterminalapplication;

import java.net.Socket;

public class ChocAnEmployeeTerminal
{
    private static final String connectionAddress = "127.0.0.1";
    private static final int connectionPort = 3305;
    
    private Socket connectionSocket;
    
    private boolean connect()
    {
        try
        {
            connectionSocket = new Socket(connectionAddress, connectionPort);
        } catch(Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    private void disconnect()
    {
        try
        {
            connectionSocket.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * login will have two arguments, both strings. The first is
     * called username, and the second is called password. The function will
     * return an integer - 0 for successful login as Employee, 1 for successful
     * login as Manager, 2 for failed login, and 3 for connection failed.
     * 
     * @param employeeNumber
     * @param password
     * @return 
     */
    public int login(String employeeNumber, String password)
    {
        //Connect to the server
        boolean connectionSuccess = connect();
        if (!connectionSuccess)
            return 3;
        
        //create a string in the following format: “loginRequest\t” + username + “\t” + password
        
        //call the connect method giving it the string
        
        //return whatever the connect method returns

        return -1;
    }
}
