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
    
    public ArrayList<Service> getServiceDirectory()
    {
        return null;
    }
    
    public int insertService(Service serviceData)
    {
        return 0;
    }
    
    public int updateService(Service serviceData)
    {
        return 0;
    }
    
    public ArrayList<Provider> getProviders()
    {
        return null;
    }
    
    public int insertProvider(Provider providerData)
    {
        return 0;
    }
    
    public int updateProvider(Provider providerData)
    {
        return 0;
    }
    
    public ArrayList<Member> getMembers()
    {
        return null;
    }
    
    public int insertMember(Member memberData)
    {
        return 0;
    }
    
    public int updateMember(Member memberData)
    {
        return 0;
    }
    
    public ArrayList<Employee> getEmployees()
    {
        return null;
    }
    
    public int insertEmployee(Employee employeeData)
    {
        return 0;
    }
    
    public int updateEmployee(Employee employeeData)
    {
        return 0;
    }
    
    public int requestProviderReport(String providerNumber, LocalDateTime endDateTime)
    {
        return 0;
    }
    
    public int requestMemberReport(String memberNumber, LocalDateTime endDateTime)
    {
        return 0;
    }
    
    public int requestSummaryReport(LocalDateTime endDateTime)
    {
        return 0;
    }
}
