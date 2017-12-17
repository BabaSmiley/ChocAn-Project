/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanstructs.Service;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class serviceTable
{
    private DefaultTableModel model;
    private ArrayList<JRadioButton> buttonList;
    private ButtonGroup group;
    private ArrayList<Service> services;
    
    serviceTable(ArrayList<Service> services)
    {
        this.services = services;
        group = new ButtonGroup();
        buttonList = new ArrayList<JRadioButton>();
        
        model = new DefaultTableModel();
        model.addColumn("Select");
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
        JRadioButton newButton = new JRadioButton();
        group.add(newButton);
        buttonList.add(newButton);
        
        Object[] returnObjectArray = {newButton, serviceToAdd.serviceNumber, 
            serviceToAdd.name, serviceToAdd.fee, serviceToAdd.isActive};
        
        return returnObjectArray;
    }
    
    public Service getSelectedService()
    {
        for (int i = 0; i < buttonList.size(); i++)
        {
            if (buttonList.get(i).isSelected())
                return services.get(i);
        }
        
        return null;
    }
    
    public JTable getTable()
    {
        return new JTable(model);
    }
}
