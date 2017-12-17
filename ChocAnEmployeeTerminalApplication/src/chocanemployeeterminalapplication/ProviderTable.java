/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanstructs.Provider;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class ProviderTable 
{
    private DefaultTableModel model;
    
    ProviderTable(ArrayList<Provider> providers)
    {   
        model = new DefaultTableModel();
        model.addColumn("Provider Number");
        model.addColumn("Password");
        model.addColumn("Name");
        model.addColumn("Email Address");
        model.addColumn("Sreet Address");
        model.addColumn("City");
        model.addColumn("State");
        model.addColumn("Zip Code");
        model.addColumn("Is Active");
        
        for (Provider currentProvider: providers)
        {
            Object[] newRow = createElement(currentProvider);
            model.addRow(newRow);
        }
    }
    
    private Object[] createElement(Provider providerToAdd)
    {
        Object[] returnObjectArray = {providerToAdd.providerNumber, providerToAdd.password,
            providerToAdd.name, providerToAdd.emailAddress, providerToAdd.streetAddress,
            providerToAdd.city, providerToAdd.state, providerToAdd.zipCode, providerToAdd.isActive};
        
        return returnObjectArray;
    }
    
    public DefaultTableModel getModel()
    {
        return model;
    }
}
