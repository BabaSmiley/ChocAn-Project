/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * This file contains Bill which is common between the ChocAnServerApplication
 * and the ChocAnEmployyeTerminal. It will largely be used as interfaces between
 * functions.
 */

package chocanstructs;

import java.util.Date;

public class Bill 
{
    public String providerNumber;
    public String memberNumber;
    public String serviceNumber;
    public Date dateOfService;
    public String comments;
    
    public Bill()
    {
        
    }
    
    /**
     * checks all variables for data integrity in the struct and returns true if
     * everything is within expected domains, and false if something isn't.
     * 
     * @return true if the data is valid, false if it isn't
     */
    public boolean verifyData()
    {
        //providerNumber must be 9 characters long
        if (providerNumber.length() != 9)
            return false;
        
        //providerNumber must be an integer
        try
        {
            int value = Integer.parseInt(providerNumber);
            
            //providerNumber must be positive
            if (value < 0)
                return false;
        } catch(Exception e)
        {
            return false;
        }
        
        //memberNumber must be 9 characters long
        if (memberNumber.length() != 9)
            return false;
        
        //memberNumber must be an integer
        try
        {
            int value = Integer.parseInt(memberNumber);
            
            //memberNumber must be positive
            if (value < 0)
                return false;
        } catch(Exception e)
        {
            return false;
        }
        
        //serviceNumber must be 6 characters long
        if (serviceNumber.length() != 6)
            return false;
        
        //serviceNumber must be an integer
        try
        {
            int value = Integer.parseInt(serviceNumber);
            
            //serviceNumber must be positive
            if (value < 0)
                return false;
        } catch(Exception e)
        {
            return false;
        }
        
        //dateOfService may not be null
        if (dateOfService == null)
            return false;
        
        //comments must be less than 101 characters
        if (comments != null && comments.length() > 100)
            return false;
        
        return true;
    }
}