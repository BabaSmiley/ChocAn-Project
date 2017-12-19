/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanserverapplication;

import chocanstructs.Member;
import chocanstructs.Provider;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class AutomatedReportThread implements Runnable
{
    LocalDateTime nextSaturday;
    
    public AutomatedReportThread()
    {
        LocalDate dateOfSaturday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        nextSaturday = dateOfSaturday.atStartOfDay();
        System.out.println("Next Auto Report: " + nextSaturday);
    }
    
    public void run()
    {
        while (true)
        {
            if (LocalDateTime.now().isAfter(nextSaturday))
            {
                System.out.println("Starting auto reports...");
                try
                {
                    createAllReports();
                } catch(Exception e)
                {
                    e.printStackTrace();
                }
                
                LocalDate dateOfSaturday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                nextSaturday = dateOfSaturday.atStartOfDay();
            }
            else
            {
                try {
                    Thread.sleep(30000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void createAllReports() throws SQLException, IOException
    {
        ArrayList<Provider> allProviders = DatabaseQueries.getAllProviders();
        for (Provider currentProvider: allProviders)
        {
            EmployeeFunctionality.createProviderReport(currentProvider.providerNumber, nextSaturday);
        }
        
        ArrayList<Member> allMembers = DatabaseQueries.getAllMembers();
        for (Member currentMember: allMembers)
        {
            EmployeeFunctionality.createMemberReport(currentMember.memberNumber, nextSaturday);
        }
        
        EmployeeFunctionality.createSummaryAndEFTReports(nextSaturday);
    }
}
