/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanemployeeterminalapplication.EmployeeTerminal;
import chocanstructs.Employee;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testMain {
    public static void main(String[] args)
    {
        /**
        ChocAnEmployeeTerminal myTerminal = new ChocAnEmployeeTerminal();
        System.out.println(myTerminal.login("146964984", "password"));
        
        Employee newEmployee = new Employee();
        newEmployee.city = "Novi";
        newEmployee.emailAddress = "Billy@bob.com";
        newEmployee.employeeNumber = "867530800";
        newEmployee.isActive = true;
        newEmployee.name = "Billy Bob Joe";
        newEmployee.password = "PratchettTheHatchet9";
        newEmployee.state = "MI";
        newEmployee.streetAddress = "12345 Novi Rd";
        newEmployee.zipCode = "87654";
        newEmployee.isManager = true;
        
        try {
            System.out.println(myTerminal.updateEmployee(newEmployee));
            
            ArrayList<Employee> allEmployees = myTerminal.getAllEmployees();
            
            for (Employee currentEmployee: allEmployees)
            {
                System.out.println(currentEmployee.name + "\t" + currentEmployee.emailAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        LocalDate date = LocalDate.now();
        date = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDateTime dateTime = date.atStartOfDay();
        System.out.println(dateTime);
        
        
    }
}
