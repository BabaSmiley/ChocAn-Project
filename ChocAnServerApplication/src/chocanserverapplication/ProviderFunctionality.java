/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * Provider Functionality
 * 
 * This class will contain all the different static methods required to serve a
 * provider terminal client on the server side.
 */

package chocanserverapplication;

import chocanstructs.Bill;
import chocanstructs.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProviderFunctionality 
{
    /**
     * takes a memberNumber and checks the validity of the member in the database.
     * 
     * @param memberNumber number of member to check validity of
     * @return 0 if valid, 1-VALIDITYREASON if invalid
     */
    public static String verifyMember(String memberNumber)
    {
        //Check that the member number is in a valid format
            //if the member number is a positive 9 digit number with no other symbols, continue
            //else return “1-Invalid member number format”
        int memberNumberValue;
        try
        {
            memberNumberValue = Integer.parseInt(memberNumber);
        } catch(Exception e)
        {
            return "1-Invalid Member Number Format";
        }
        if (memberNumber.length() != 9 || memberNumberValue <= 0)
            return "1-Invalid Member Number Format";
        
        //Query the member table on the database for a member entry where the 
        //member number (primary key) is equal to memberNumber. Only parts you 
        //need returned from the query is the memberNumber, the isValid, isActive, and validityReason
        String returnValue;
        try
        {
            returnValue = DatabaseQueries.verifyMember(memberNumber);
        } catch(Exception e)
        {
            return "1-Service Down";
        }
        
        return returnValue;
    }
    
    /**
     * Takes a providerNumber and password and returns true if the input matches
     * an entry in the database.
     * 
     * @param providerNumber
     * @param providerPassword
     * @return true for correct login, false for incorrect
     */
    public static boolean verifyProvider(String providerNumber, String providerPassword)
    {
        //Query the provider table for an entry with the providerNumber and
        //password equal to the providerNumber and providerPassword in the arguments
        //and ensure isActive = true
        
        //if the query returns a result
            //return true
        //else
            //return false
        boolean returnValue = DatabaseQueries.verifyProvider(providerNumber, providerPassword);
        
        return returnValue;
    }
    
    /**
     * creates a text file in the ServiceDirectory folder containing each service
     * on independent lines. To be sent as email attachment.
     * 
     * @return true if succeeded, false if failed to create file
     */
    public static boolean requestServiceDirectory()
    {   
        //try
        try
        {
            //Query the database for all entries in the serviceDirectory table that
            //have isActive set to true
            //Convert the returned query statement into a list of services
            ArrayList<Service> activeServices = DatabaseQueries.getActiveServices();
            
            //Create a file under the ServiceDirectory folder called 
            //"ServiceDirectory" + datetime + ".txt" where datetime is the current 
            //timestamp.
            String fileName = "\\Service Directory\\ServiceDirectory" + LocalDateTime.now().hashCode() + ".txt";
            fileName = fileName.replaceAll(":", " ");
            System.out.println(fileName);
        
            File newFile = new File(fileName);
            newFile.getParentFile().mkdirs();
        
            FileWriter fileWriter = new FileWriter(newFile);
        
            BufferedWriter outputStream = new BufferedWriter(fileWriter);
            
            //for each service in the list
            for (Service currentService: activeServices)
            {
                //write serviceNumber + "\t" + name + "\t" + "\n" to the file
                outputStream.write(currentService.serviceNumber + "\t" + currentService.name +
                    "\t" + currentService.fee + "\n");
            }
                
            //close the file
            outputStream.close();
            fileWriter.close();
            
        } catch(Exception e)
        {
        //catch all
            //return false
            return false;
        }
        //return true;
        
        return true;
    }
    
    /**
     * takes an bill struct in as input, validates the data in the struct,
     * adds the struct to the bill table of the database, returns successful
     * or not
     * 
     * @param billCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createBill(Bill billCreationData)
    {
        //call the verifyData function from the Bill struct on Bill data
        //if verifyData returns false, return 4
        if (!billCreationData.verifyData())
            return 4;
        
        boolean returnValue = DatabaseQueries.insertBill(billCreationData);
        
        if (returnValue)
            return 0;
        else
            return 1;
    }
}
