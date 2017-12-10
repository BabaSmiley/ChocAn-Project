/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    //public static void updateMemberStatus(List<ChocAnStructs.Member> memberUpdates)
    {
        
    }
}
