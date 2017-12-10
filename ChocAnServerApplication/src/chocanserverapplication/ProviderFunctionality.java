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

public class ProviderFunctionality 
{
    /**
     * takes a memberNumber and checks the validity of the member in the database.
     * 
     * @param memberNumber number of member to check validity of
     * @return 1 if valid, 0-validityReason if invalid
     */
    static String verifyMember(String memberNumber)
    {
        //Check that the member number is in a valid format
            //if the member number is a positive 9 digit number with no other symbols, continue
            //else return “0-Invalid member number format”
        
        //Query the member table on the database for a member entry where the 
        //member number (primary key) is equal to memberNumber. Only parts you 
        //need returned from the query is the memberNumber, the isValid, isActive, and validityReason
        
        //If the query returns one entry and the entry’s isValid and isActive is set to true, return “1”
        
        //If the query returns one entry and the entry’s isValid is set to false, return “0-” + validityReason 
        
        //If the query returns one entry and the entry's isActive is set to false, return "0-Member account deactivated"
        
        //If the query returns no entries (there are no matches), return “0-Member number does not exist”
        
        return null;
    }
    
    /**
     * Takes a providerNumber and password and returns true if the input matches
     * an entry in the database.
     * 
     * @param providerNumber
     * @param providerPassword
     * @return true for correct login, false for incorrect
     */
    static boolean verifyProvider(String providerNumber, String providerPassword)
    {
        //Query the provider table for an entry with the providerNumber and
        //password equal to the providerNumber and providerPassword in the arguments
        //and ensure isActive = true
        
        //if the query returns a result
            //return true
        //else
            //return false
        
        return false;
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
            //Query the database for all entries in the serviceDirectory table that
            //have isActive set to true
            
            //Convert the returned query statement into a list of services
            
            //Create a file under the ServiceDirectory folder called 
            //"ServiceDirectory" + datetime + ".txt" where datetime is the current 
            //timestamp.
            
            //for each service in the list
                //write serviceNumber + "\t" + name + "\t" + "\n" to the file
                
            //close the file
            
        //catch all
            //return false
        
        //return true;
        
        return false;
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
        
        //try
            //run an insertion command on the bill table in the database
            //using all of the data in billCreationData
        //catch all
            //return 1
        
        //return 0
        
        return 0
    }
}
