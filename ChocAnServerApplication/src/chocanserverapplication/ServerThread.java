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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                try 
                {
                    inputStream.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
                
                try 
                {
                    outputStream.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
                
                try 
                {
                    client.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
        
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
            //login functionality!
            String receivedCommand = (String) inputStream.readObject();
            if (receivedCommand.equals("employeelogin"))
                processEmployeeLoginCommand();
            
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
            case "getallservices":
            {
                try {
                    processGetAllServicesCommand();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            break;
            
            case "insertservice":
            {
                try {
                    processInsertServiceCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "updateservice":
            {
                try {
                    processUpdateServiceCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "getallproviders":
            {
                try {
                    processGetAllProvidersCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "insertprovider":
            {
                try {
                    processInsertProviderCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "updateprovider":
            {
                try {
                    processUpdateProviderCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "getallmembers":
            {
                try {
                    processGetAllMembersCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "insertmember":
            {
                try {
                    processInsertMemberCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "updatemember":
            {
                try {
                    processUpdateMemberCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "getallemployees":
            {
                try {
                    processGetAllEmployeesCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "insertemployee":
            {
                try {
                    processInsertEmployeeCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "updateemployee":
            {
                try {
                    processUpdateEmployeeCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "requestproviderreport":
            {
                try {
                    processRequestProviderReportCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "requestmemberreport":
            {
                try {
                    processRequestMemberReportCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            case "requestsummaryreport":
            {
                try {
                    processRequestSummaryReportCommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            
            //If not recognized command, drop the connection
            default:
                clientPrivileges = -1;
        }
    }
    
    private void processEmployeeLoginCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            String employeeNumber = (String) inputStream.readObject();
            String password = (String) inputStream.readObject();
            returnValue = DatabaseQueries.employeeLogin(employeeNumber, password);
        } catch(Exception e)
        {
            returnValue = 3;
        }
        
        clientPrivileges = returnValue;
        
        outputStream.writeObject(returnValue);
    }
    
    public void processGetAllServicesCommand() throws SQLException, IOException
    {
        ArrayList<Service> allServices = DatabaseQueries.getAllServices();
        
        outputStream.writeObject(allServices);
    }
    
    public void processInsertServiceCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the service to insert
            Service serviceData = (Service) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.createService(serviceData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processUpdateServiceCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the service to insert
            Service serviceData = (Service) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.updateService(serviceData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processGetAllProvidersCommand() throws SQLException, IOException
    {
        ArrayList<Provider> allProviders = DatabaseQueries.getAllProviders();
        
        outputStream.writeObject(allProviders);
    }
    
    public void processInsertProviderCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the provider to insert
            Provider providerData = (Provider) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.createProvider(providerData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processUpdateProviderCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the provider to insert
            Provider providerData = (Provider) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.updateProvider(providerData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processGetAllMembersCommand() throws SQLException, IOException
    {
        ArrayList<Member> allMembers = DatabaseQueries.getAllMembers();
        
        outputStream.writeObject(allMembers);
    }
    
    public void processInsertMemberCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the member to insert
            Member memberData = (Member) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.createMember(memberData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processUpdateMemberCommand() throws IOException
    {
        int returnValue;
        
        try
        {
            //get the member to insert
            Member memberData = (Member) inputStream.readObject();
            
            //Check value, query database, return value (EmployeeFunctionality function)
            returnValue = EmployeeFunctionality.updateMember(memberData);
        } catch(Exception e)
        {
            e.printStackTrace();
            returnValue = 4;
        }
        
        outputStream.writeObject(returnValue);
    }
    
    public void processGetAllEmployeesCommand() throws SQLException, IOException
    {
        if (clientPrivileges == 1)
        {
            ArrayList<Employee> allEmployees = DatabaseQueries.getAllEmployees();
            
            outputStream.writeObject(allEmployees);
        }
        else
            clientPrivileges = -1;
    }
    
    public void processInsertEmployeeCommand() throws IOException
    {
        if (clientPrivileges == 1)
        {
            int returnValue;
            
            try
            {
                //get the employee to insert
                Employee employeeData = (Employee) inputStream.readObject();
                
                //Check value, query database, return value (EmployeeFunctionality function)
                returnValue = EmployeeFunctionality.createEmployee(employeeData);
            } catch(Exception e)
            {
                e.printStackTrace();
                returnValue = 4;
            }
            
            outputStream.writeObject(returnValue);
        }
        else
            clientPrivileges = -1;
    }
    
    public void processUpdateEmployeeCommand() throws IOException
    {
        if (clientPrivileges == 1)
        {
            int returnValue;
            
            try
            {
                //get the employee to insert
                Employee employeeData = (Employee) inputStream.readObject();
                
                //Check value, query database, return value (EmployeeFunctionality function)
                returnValue = EmployeeFunctionality.updateEmployee(employeeData);
            } catch(Exception e)
            {
                e.printStackTrace();
                returnValue = 4;
            }
            
            outputStream.writeObject(returnValue);
        }
        else
            clientPrivileges = -1;
    }
    
    public void processRequestProviderReportCommand() throws IOException, ClassNotFoundException, SQLException
    {
        if (clientPrivileges == 1)
        {
            String providerNumber = (String) inputStream.readObject();
            LocalDateTime endTime = (LocalDateTime) inputStream.readObject();
            
            int returnValue = EmployeeFunctionality.createProviderReport(providerNumber, endTime);
            
            outputStream.writeObject(returnValue);
        }
        else
            clientPrivileges = -1;
    }
    
    public void processRequestMemberReportCommand() throws IOException, ClassNotFoundException, SQLException
    {
        if (clientPrivileges == 1)
        {
            String memberNumber = (String) inputStream.readObject();
            LocalDateTime endTime = (LocalDateTime) inputStream.readObject();
            
            int returnValue = EmployeeFunctionality.createMemberReport(memberNumber, endTime);
            
            outputStream.writeObject(returnValue);
        }
        else
            clientPrivileges = -1;
    }
    
    public void processRequestSummaryReportCommand() throws IOException, ClassNotFoundException, SQLException
    {
        if (clientPrivileges == 1)
        {
            LocalDateTime endTime = (LocalDateTime) inputStream.readObject();
            
            int returnValue = EmployeeFunctionality.createSummaryReport(endTime);
            
            outputStream.writeObject(returnValue);
        }
        else
            clientPrivileges = -1;
    }
}
