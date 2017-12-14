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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerThread implements Runnable
{
    private Socket client;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    
    private int clientPrivileges; //-1 = none, 0 = employee, 1 = manager
    
    /**
     * @param client the socket connected to the client to serve
     */
    public ServerThread(Socket client)
    {
        try
        {
            this.client = client;
            clientPrivileges = -1;
            
            inputStream = new ObjectInputStream(client.getInputStream());
            outputStream = new ObjectOutputStream(client.getOutputStream());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * First checks the client's credentials (login) and stores what their
     * privileges are.
     *
     * Then run the main loop.
     *
     * Should take input, figure out what to do with it, do it, send a response
     * to the client, then repeat.
     */
    public void run()
    {
        try
        {
            String receivedCommand = (String) inputStream.readObject();
            if (receivedCommand.equals("employeelogin"))
            {
                int response = processEmployeeLoginCommand();
                clientPrivileges = response;
                
                outputStream.writeObject(response);
            }
            
            while(clientPrivileges == 0 || clientPrivileges == 1)
            {
                
            }
            
            client.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * takes an input string and decides what functionality to do with it, 
     * checks clientPrivileges, then executes the function and returns the
     * result string
     * 
     * @param input
     */
    private String processInput(String input)
    {
        return null;
    }
    
    private int processEmployeeLoginCommand()
    {
        try
        {   
            String employeeNumber = (String) inputStream.readObject();
            String password = (String) inputStream.readObject();
            return DatabaseQueries.employeeLogin(employeeNumber, password);
        } catch(Exception e)
        {
            e.printStackTrace();
            return 4;
        }
    }
}
