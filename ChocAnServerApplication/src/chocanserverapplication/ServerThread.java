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

import chocanstructs.Employee;
import chocanstructs.Member;
import chocanstructs.Provider;
import chocanstructs.Service;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    
    private void close()
    {
        inputStream.close();
        outputStream.close();
        client.close();
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
                receivedCommand = (String) inputStream.readObject();
                
                switch(receivedCommand)
                {
                    case "getservicedirectory":
                        
                        break;
                        
                    case "insertservice":
                        
                        break;
                        
                    case "insertservice":
                        
                        break;
                        
                    case "getproviders":
                        
                        break;
                        
                    case "insertprovider":
                        
                        break;
                        
                    case "updateprovider":
                        
                        break;
                        
                    case "getmembers":
                        
                        break;
                        
                    case "insertmember":
                        
                        break;
                        
                    case "updatemember":
                        
                        break;
                        
                    case "getemployee":
                        
                        break;
                        
                    case "insertemployee":
                        
                        break;
                        
                    case "updateemployee":
                        
                        break;
                        
                    case "requestproviderreport":
                        
                        break;
                        
                    case "requestmemberreport":
                        
                        break;
                        
                    case "requestsummaryreport":
                        
                        break;
                        
                    default:
                        clientPrivileges = -1;
                }
            }
            
            close();
        } catch(Exception e)
        {
            close();
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
    
    public ArrayList<Service> processGetServiceDirectoryCommand()
    {
        return null;
    }
    
    public int processInsertServiceCommand(Service serviceData)
    {
        return 0;
    }
    
    public int processUpdateServerCommand(Service serviceData)
    {
        return 0;
    }
    
    public ArrayList<Provider> processGetProvidersCommand()
    {
        return null;
    }
    
    public int processInsertProviderCommand(Provider providerData)
    {
        return 0;
    }
    
    public int processUpdateProviderCommand(Provider providerData)
    {
        return 0;
    }
    
    public ArrayList<Member> processGetMembersCommand()
    {
        return null;
    }
    
    public int processInsertMemberCommand(Member memberData)
    {
        return 0;
    }
    
    public int processUpdateMemberCommand(Member memberData)
    {
        return 0;
    }
    
    public ArrayList<Employee> processGetEmployeesCommand()
    {
        return null;
    }
    
    public int processInsertEmployeeCommand(Employee employeeData)
    {
        return 0;
    }
    
    public int processUpdateEmployeeCommand(Employee employeeData)
    {
        return 0;
    }
    
    public int processRequestProviderReportCommand(String providerNumber, LocalDateTime endDateTime)
    {
        return 0;
    }
    
    public int processRequestMemberReportCommand(String memberNumber, LocalDateTime endDateTime)
    {
        return 0;
    }
    
    public int processRequestSummaryReportCommand(LocalDateTime endDateTime)
    {
        return 0;
    }
}
