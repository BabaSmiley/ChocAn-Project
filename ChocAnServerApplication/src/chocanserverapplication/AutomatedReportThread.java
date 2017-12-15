/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanserverapplication;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class AutomatedReportThread implements Runnable
{
    LocalDateTime nextFriday;
    
    public AutomatedReportThread()
    {
        LocalDateTime dateTime = LocalDateTime.now();
        nextFriday = dateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }
    
    public void run() 
    {
        while (true)
        {
            if (LocalDateTime.now().isAfter(nextFriday))
            {
                LocalDateTime dateTime = LocalDateTime.now();
                nextFriday = dateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                
                createAllReports();
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
    
    private void createAllReports()
    {
        
    }
    
    private void createSummaryReport()
    {
        
    }
    
    private void createEFTReport(String providerName, String providerNumber, Double fee)
    {
        
    }
}
