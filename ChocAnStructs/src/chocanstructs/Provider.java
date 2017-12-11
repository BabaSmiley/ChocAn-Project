/*
 * CIS-376
 * Fall 2017
 * Team Steiner
 * Project ChocAn
 * Group Members: Brandon Falk, Afeefeh Seblini,
 * Ismaeel Varis, Daniel Vera-Burgos
 * 
 * This file contains Provider which is common between the ChocAnServerApplication
 * and the ChocAnEmployyeTerminal. It will largely be used as interfaces between
 * functions.
 */

package chocanstructs;

public class Provider 
{
    public String providerNumber;
    public String password;
    public String name;
    public String emailAddress;
    public String streetAddress;
    public String city;
    public String state;
    public String zipCode;
    public boolean isActive;
    
    public Provider()
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
        
        //name must be longer than 0, but less than 26 characters
        if (name.length() > 25 || name.length() == 0)
            return false;
        
        //email must be longer than 0, but less than 51 characters. requires @
        if (emailAddress.length() > 25 || emailAddress.length() == 0 || !emailAddress.contains("@"))
            return false;
        
        //street address must be longer than 0, but less than 26 characters
        if (streetAddress.length() > 25 || streetAddress.length() == 0)
            return false;
        
        //city must be longer than 0, but less than 15 characters
        if (city.length() > 14 || city.length() == 0)
            return false;
        
        //zipCode must be 5 characters long
        if (zipCode.length() != 5)
            return false;
        
        //zipCode must be an integer
        try
        {
            Integer.parseInt(zipCode);
        } catch(Exception e)
        {
            return false;
        }
        
        return true;
    }
}
