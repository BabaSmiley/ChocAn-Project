/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * ChocAn Server Application
 * 
 * This application is the server side of the ChocAn System. It will interface 
 * with the ChocAn database which should be running on the same machine. It will
 * accept connections from ChocAn Employee Terminals, giving them access to
 * database functionality. It will also have an interface for Provider Terminals
 * and ACME Accounting Services, however these applications wont be developed in
 * this project and networking services will not be provided for them as that is
 * the purpose of a third party.
 * 
 * This particular file is the start point for the server application. It will
 * accept incoming TCP connections and then give them to a new ServerThread 
 * instance which will then be given its' own thread to serve them.
 */

package chocanserverapplication;

import chocanserverapplication.ServerThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChocAnServerApplication 
{ 
    public static final int welcomeSocketPortNumber = 3305;
    private static ServerSocket welcomeSocket;
    
    /**
     * Add a shutdownhook to die gracefully with the VM
     * Setup welcomeSocket and put it into it's infinite loop.
     */
    public static void main(String[] args) 
    {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                System.out.println("Shutting down...");
                try 
                {
                    welcomeSocket.close();
                } catch (IOException e) 
                {
                    System.out.println("welcomeSocket already closed");
                }
            }
        });
        
        setupWelcomeSocket();
        welcomeLoop();
    }
    
    /**
     * Sets up welcomeSocket with welcomeSocketPortNumber. Prints error if it
     * throws an exception and exits the program.
     * prints to console the port it listens on.
     */
    private static void setupWelcomeSocket()
    {
        try 
        {
            welcomeSocket = new ServerSocket(welcomeSocketPortNumber);
            System.out.println("Welcome Socket listening on " +
                    welcomeSocketPortNumber);
        } catch (IOException e) 
        {
            System.out.println("Failed to setup welcomeSocket on port " +
                    welcomeSocketPortNumber);
            System.exit(-1);
        }
    }
    
    /**
     * Runs on an infinite loop.
     * Prints to console when starting and whenever a connection is accepted.
     */
    private static void welcomeLoop()
    {
        System.out.println("Welcome socket now listening...");
        while(true)
        {
            ServerThread newClientService;
            Socket clientSocket;
            try
            {
                clientSocket = welcomeSocket.accept();
                System.out.println("New client connected at " + 
                        clientSocket.getInetAddress() + ":" + 
                        clientSocket.getPort());
                
                new Thread(new ServerThread(clientSocket)).start();
            } catch (IOException e) 
            {
                System.out.println("welcomeSocket failed to accept on port " + 
                        welcomeSocketPortNumber);
                System.exit(-1);
            }
        }
    }
}
