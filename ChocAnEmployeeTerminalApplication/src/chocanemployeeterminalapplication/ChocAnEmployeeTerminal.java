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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;
import chocanstructs.Bill;
import java.io.IOException;
import java.time.LocalDateTime;

public class ChocAnEmployeeTerminal
{
    private static final String connectionAddress = "127.0.0.1";
    private static final int connectionPort = 3305;
    
    private Socket connectionSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    
    ChocAnEmployeeTerminal()
    {
        
    }
    
    private boolean connect()
    {
        try
        {
            connectionSocket = new Socket(connectionAddress, connectionPort);
            outputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            inputStream = new ObjectInputStream(connectionSocket.getInputStream());
        } catch(Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    public void disconnect()
    {
        try
        {
            connectionSocket.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        try
        {
            outputStream.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        try
        {
            inputStream.close();
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
     * @return 0 for successful login as Employee, 1 for successful
     * login as Manager, 2 for failed login, and 3 for database connection failed,
     * 4 for connection failed
     */
    public int login(String employeeNumber, String password)
    {
        try
        {
            //Connect to the server, return 4 if failed
            boolean connectionSuccess = connect();
            if (!connectionSuccess)
                return 4;
            
            //send login command followed by the arguments as strings
            //employeelogin
            String commandString = "employeelogin";
            outputStream.writeObject(commandString);
            
            //employeeNumber
            outputStream.writeObject(employeeNumber);
            
            //password
            outputStream.writeObject(password);
            
            //get response from server - an int.
            int serverResponse = (int) inputStream.readObject();
            
            //return whatever the server responded with
            return serverResponse;
        } catch(Exception e)
        {
            e.printStackTrace();
            return 4;
        }
    }
    
    public ArrayList<Service> getAllServices() throws IOException, ClassNotFoundException
    {
        String command = "getallservices";
        
        outputStream.writeObject(command);
        
        ArrayList<Service> allServices = (ArrayList<Service>) inputStream.readObject();
        
        return allServices;
    }
    
    public int insertService(Service serviceData) throws IOException, ClassNotFoundException
    {
        if (!serviceData.verifyData())
            return 4;
        
        String command = "insertservice";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(serviceData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int updateService(Service serviceData) throws IOException, ClassNotFoundException
    {
        if (!serviceData.verifyData())
            return 4;
        
        String command = "updateservice";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(serviceData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public ArrayList<Provider> getAllProviders() throws IOException, ClassNotFoundException
    {
        String command = "getallproviders";
        
        outputStream.writeObject(command);
        
        ArrayList<Provider> allProviders = (ArrayList<Provider>) inputStream.readObject();
        
        return allProviders;
    }
    
    public int insertProvider(Provider providerData) throws IOException, ClassNotFoundException
    {
        if (!providerData.verifyData())
            return 4;
        
        String command = "insertprovider";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(providerData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int updateProvider(Provider providerData) throws IOException, ClassNotFoundException
    {
        if (!providerData.verifyData())
            return 4;
        
        String command = "updateprovider";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(providerData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public ArrayList<Member> getAllMembers() throws IOException, ClassNotFoundException
    {
        String command = "getallmembers";
        
        outputStream.writeObject(command);
        
        ArrayList<Member> allMembers = (ArrayList<Member>) inputStream.readObject();
        
        return allMembers;
    }
    
    public int insertMember(Member memberData) throws IOException, ClassNotFoundException
    {
        if (!memberData.verifyData())
            return 4;
        
        String command = "insertmember";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(memberData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int updateMember(Member memberData) throws IOException, ClassNotFoundException
    {
        if (!memberData.verifyData())
            return 4;
        
        String command = "updatemember";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(memberData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public ArrayList<Employee> getAllEmployees() throws IOException, ClassNotFoundException
    {
        String command = "getallemployees";
        
        outputStream.writeObject(command);
        
        ArrayList<Employee> allEmployees = (ArrayList<Employee>) inputStream.readObject();
        
        return allEmployees;
    }
    
    public int insertEmployee(Employee employeeData) throws IOException, ClassNotFoundException
    {
        if (!employeeData.verifyData())
            return 4;
        
        String command = "insertemployee";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(employeeData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int updateEmployee(Employee employeeData) throws IOException, ClassNotFoundException
    {
        if (!employeeData.verifyData())
            return 4;
        
        String command = "updateemployee";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(employeeData);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int requestProviderReport(String providerNumber, LocalDateTime endDateTime) throws IOException, ClassNotFoundException
    {
        String command = "requestproviderreport";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(providerNumber);
        
        outputStream.writeObject(endDateTime);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int requestMemberReport(String memberNumber, LocalDateTime endDateTime) throws IOException, ClassNotFoundException
    {
        String command = "requestmemberreport";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(memberNumber);
        
        outputStream.writeObject(endDateTime);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
    
    public int requestSummaryReport(LocalDateTime endDateTime) throws IOException, ClassNotFoundException
    {
        String command = "requestsummaryreport";
        
        outputStream.writeObject(command);
        
        outputStream.writeObject(endDateTime);
        
        int returnValue = (int) inputStream.readObject();
        
        return returnValue;
    }
}
