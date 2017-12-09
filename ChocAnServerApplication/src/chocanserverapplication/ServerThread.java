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
    ServerThread(Socket client)
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
}
