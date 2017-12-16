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
import java.sql.SQLException;
import java.util.ArrayList;

public class ACMEAccountingFunctionality 
{
    /**
     * method gets a list of all members which have the isUpdated flag set to
     * true in the database and returns them after setting all the isUpdated
     * flags to false after access.
     * 
     * @return list of members which have been updated.
     */
    public static ArrayList<Member> getMemberBillingInformation() throws SQLException
    {
        //Create a new list of Member structs called updatedMembers
        ArrayList<Member> updatedMembers;
        
        //Query the member table for Tuples with IsUpdated set to true
        updatedMembers = DatabaseQueries.getUpdatedMembers();
        
        //return updatedMembers
        return updatedMembers;
    }
    
    /**
     * Takes a list of Members and updates their validity and reason in the the
     * database
     * 
     * @param memberUpdates list of members to update reason and validity of
     */
    public static void updateMemberStatus(ArrayList<Member> memberUpdates)
    {
        //For each element in memberUpdates
        for (Member currentMember: memberUpdates)
        {
            //If the data in the currentMember is valid
            if (currentMember.isValid)
            {
                DatabaseQueries.updateMemberStatus(currentMember);
            }
        }
    }
}
