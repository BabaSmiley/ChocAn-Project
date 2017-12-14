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
import java.sql.SQLException;
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
        try
        {
            inputStream.close();
        } catch(Exception e)
        {
            
        }
        
        try
        {
            outputStream.close();
        } catch(Exception e)
        {
            
        }
        
        try
        {
            client.close();
        } catch(Exception e)
        {
            
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
            //Run login command
            String receivedCommand = (String) inputStream.readObject();
            if (receivedCommand.equals("employeelogin"))
            {
                int response = processEmployeeLoginCommand();
                
                clientPrivileges = response;
                
                outputStream.writeObject(response);
            }
            
            //while clientPrivileges are valid (0 for employee, 1 for manager)
            while(clientPrivileges == 0 || clientPrivileges == 1)
            {
                receivedCommand = (String) inputStream.readObject();
                
                processInput(receivedCommand);
            }
            
            //Close
            close();
        } catch(Exception e)
        {
            close();
        }
    }
    
    /**
     * takes an input string and decides what functionality to do with it,
     * checks clientPrivileges, then executes the function\
     *
     * @param input
     */
    private void processInput(String receivedCommand)
    {
        switch(receivedCommand)
        {
            case "getservicedirectory":
                try
                {
                    processGetAllServicesCommand();
                } catch(Exception e)
                {
                    
                }
                break;
                
            case "insertservice":
                processInsertServiceCommand();
                break;
                
            case "updateservice":
                
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
                
            //If not recognized command, drop the connection
            default:
                clientPrivileges = -1;
        }
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
            return 3;
        }
    }
    
    public ArrayList<Service> processGetAllServicesCommand() throws SQLException
    {
        ArrayList<Service> allServices = DatabaseQueries.getAllServices();
        return allServices;
    }
    
    public int processInsertServiceCommand()
    {
        try
        {
            //get the service to insert
            Service serviceData = (Service) inputStream.readObject();
            
            //Query database and store success boolean
            boolean insertSuccess = DatabaseQueries.insertService(serviceData);
            
            //return 0 if true, 1 if false
            if (insertSuccess)
                return 0;
            else
                return 1;
            
        } catch(Exception e)
        {
            e.printStackTrace();
            return 4;
        }
    }
    
    public int processUpdateServiceCommand()
    {
        return 0;
    }
    
    public ArrayList<Provider> processGetProvidersCommand()
    {
        return null;
    }
    
    public int processInsertProviderCommand()
    {
        return 0;
    }
    
    public int processUpdateProviderCommand()
    {
        return 0;
    }
    
    public ArrayList<Member> processGetMembersCommand()
    {
        return null;
    }
    
    public int processInsertMemberCommand()
    {
        return 0;
    }
    
    public int processUpdateMemberCommand()
    {
        return 0;
    }
    
    public ArrayList<Employee> processGetEmployeesCommand()
    {
        return null;
    }
    
    public int processInsertEmployeeCommand()
    {
        return 0;
    }
    
    public int processUpdateEmployeeCommand()
    {
        return 0;
    }
    
    public int processRequestProviderReportCommand()
    {
        return 0;
    }
    
    public int processRequestMemberReportCommand()
    {
        return 0;
    }
    
    public int processRequestSummaryReportCommand()
    {
        return 0;
    }
}
