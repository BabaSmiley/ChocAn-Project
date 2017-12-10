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
import java.util.List;

public class ServerThread implements Runnable
{
    private Socket client;
    private int clientPrivileges; //-1 = none, 0 = employee, 1 = manager
    
    /**
     * @param client the socket connected to the client to serve
     */
    public ServerThread(Socket client)
    {
        this.client = client;
        clientPrivileges = -1;
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
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * takes an input string and decides what functionality to do with it, 
     * checks clientPrivileges, then executes the function and returns the
     * result string
     * 
     * @param input
     */
    public String processInput(String input)
    {
        return null;
    }
}
