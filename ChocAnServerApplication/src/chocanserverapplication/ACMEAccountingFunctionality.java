/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * ACME Accounting Functionality
 * 
 * This class will contain all the different static methods required to serve
 * the ACME Accounting third party software.
 */

package chocanserverapplication;

import chocanstructs.Member; 
import java.util.List;

public class ACMEAccountingFunctionality 
{
    /**
     * method gets a list of all members which have the isUpdated flag set to
     * true in the database and returns them after setting all the isUpdated
     * flags to false after access.
     * 
     * @return list of members which have been updated.
     */
    public static List<Member> getMemberBillingInformation()
    {
        //Create a new list of Member structs called updatedMembers
        
        //Query the member table for Tuples with IsUpdated set to true
        
        //For each tuple returned from the query
        
        //Create a local member struct instance and populate it with the 
        //information from the tuple
        
        //Add the newly created member struct instance to updatedMembers
        
        //return updatedMembers

        return null;
    }
    
    /**
     * Takes a list of Members and updates their validity and reason in the the
     * database
     * 
     * @param memberUpdates list of members to update reason and validity of
     */
    public static void updateMemberStatus(List<Member> memberUpdates)
    {
        //For each element in memberUpdates
            //Query the member table for a tuple with the matching memberNumber 
            //in the element
            
            //if the query returned a single result
            
            //update the member table entry using the memberNumber, isValid and
            //validityReason in the element
    }
    
    
}
