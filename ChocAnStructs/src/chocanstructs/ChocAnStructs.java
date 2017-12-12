/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanstructs;

import java.time.LocalDateTime;

public class ChocAnStructs 
{
    public static void main(String[] args) 
    {
        LocalDateTime dateTime = LocalDateTime.now();
        
        System.out.println(dateTime);
        System.out.println(dateTime.getYear() + "-" + 
                String.format("%02d", dateTime.getMonthValue()) + "-" + 
                String.format("%02d", dateTime.getDayOfMonth()) + " " + 
                String.format("%02d", dateTime.getHour()) + ":" +
                String.format("%02d", dateTime.getMinute()) + ":" + 
                String.format("%02d", dateTime.getSecond()));
    }
}
