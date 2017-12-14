/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanemployeeterminalapplication.EmployeeTerminal;

public class testMain {
    public static void main(String[] args)
    {
        ChocAnEmployeeTerminal myTerminal = new ChocAnEmployeeTerminal();
        System.out.println(myTerminal.login("123", "abc"));
    }
}
