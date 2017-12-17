/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;

import chocanstructs.Member;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class MemberTable 
{
    private DefaultTableModel model;
    
    MemberTable(ArrayList<Member> members)
    {   
        model = new DefaultTableModel();
        model.addColumn("Member Number");
        model.addColumn("Name");
        model.addColumn("Email Address");
        model.addColumn("Sreet Address");
        model.addColumn("City");
        model.addColumn("State");
        model.addColumn("Zip Code");
        model.addColumn("Is Valid");
        model.addColumn("Validity Reason");
        model.addColumn("Is Active");
        
        for (Member currentMember: members)
        {
            Object[] newRow = createElement(currentMember);
            model.addRow(newRow);
        }
    }
    
    private Object[] createElement(Member memberToAdd)
    {
        Object[] returnObjectArray = {memberToAdd.memberNumber, memberToAdd.name, 
            memberToAdd.emailAddress, memberToAdd.streetAddress, memberToAdd.city, 
            memberToAdd.state, memberToAdd.zipCode, memberToAdd.isValid, 
            memberToAdd.validityReason, memberToAdd.isActive};
        
        return returnObjectArray;
    }
    
    public DefaultTableModel getModel()
    {
        return model;
    }
}
