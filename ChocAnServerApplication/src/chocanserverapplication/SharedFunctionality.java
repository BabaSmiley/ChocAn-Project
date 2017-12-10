/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * Share Functionality
 * 
 * This class will contain all the different static methods shared to serve a
 * multiple difference clients on the server side.
 */

package chocanserverapplication;

import chocanstructs.Service;
import java.util.List;

public class SharedFunctionality 
{   
    /**
     * returns all of the active entries in the serviceDirectory table in the
     * database in the form of a list of Service structs.
     * 
     * Shared between Employee and Provider terminals.
     * 
     * @return a list of all the services still active in the serviceDirectory
     */
    public static List<Service> getServiceDirectory()
    {
        //Query the database for all entries in the serviceDirectory table that
        //have isActive set to true
        
        //Convert the returned query statement into a list of services
        
        //return the list of services
        
        return null;
    }
}
