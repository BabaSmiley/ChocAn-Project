/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * This file contains Service which is common between the ChocAnServerApplication
 * and the ChocAnEmployyeTerminal. It will largely be used as interfaces between
 * functions.
 */

package chocanstructs;

public class Service 
{
    public String serviceNumber;
    public String name;
    public double fee;
    public boolean isActive;
    
    public Service()
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
        
        //name must be longer than 0, but less than 26 characters
        if (name.length() > 25 || name.length() == 0)
            return false;
        
        //fee must be greater than or equal to 0, but less than 999.99
        if (fee < 0 || fee > 999.99)
            return false;
        
        return true;
    }
}
