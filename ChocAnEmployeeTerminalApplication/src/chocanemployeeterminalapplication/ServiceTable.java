/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanstructs.Service;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ServiceTable
{
    private DefaultTableModel model;
    
    ServiceTable(ArrayList<Service> services)
    {   
        model = new DefaultTableModel();
        model.addColumn("Service Number");
        model.addColumn("Name");
        model.addColumn("Fee");
        model.addColumn("Is Active");
        
        for (Service currentService: services)
        {
            Object[] newRow = createElement(currentService);
            model.addRow(newRow);
        }
    }
    
    private Object[] createElement(Service serviceToAdd)
    {
        Object[] returnObjectArray = {serviceToAdd.serviceNumber, 
            serviceToAdd.name, serviceToAdd.fee, serviceToAdd.isActive};
        
        return returnObjectArray;
    }
    
    public DefaultTableModel getModel()
    {
        return model;
    }
}
