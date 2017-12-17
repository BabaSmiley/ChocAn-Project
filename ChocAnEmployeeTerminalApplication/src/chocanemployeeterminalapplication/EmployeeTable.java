/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanstructs.Employee;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class EmployeeTable 
{
    private DefaultTableModel model;
    
    EmployeeTable(ArrayList<Employee> employees)
    {   
        model = new DefaultTableModel();
        model.addColumn("Employee Number");
        model.addColumn("Password");
        model.addColumn("Name");
        model.addColumn("Email Address");
        model.addColumn("Sreet Address");
        model.addColumn("City");
        model.addColumn("State");
        model.addColumn("Zip Code");
        model.addColumn("Is Active");
        model.addColumn("Is Manager");
        
        for (Employee currentEmployee: employees)
        {
            Object[] newRow = createElement(currentEmployee);
            model.addRow(newRow);
        }
    }
    
    private Object[] createElement(Employee employeeToAdd)
    {
        Object[] returnObjectArray = {employeeToAdd.employeeNumber, employeeToAdd.password,
            employeeToAdd.name, employeeToAdd.emailAddress, employeeToAdd.streetAddress,
            employeeToAdd.city, employeeToAdd.state, employeeToAdd.zipCode, 
            employeeToAdd.isActive, employeeToAdd.isManager};
        
        return returnObjectArray;
    }
    
    public DefaultTableModel getModel()
    {
        return model;
    }
}
