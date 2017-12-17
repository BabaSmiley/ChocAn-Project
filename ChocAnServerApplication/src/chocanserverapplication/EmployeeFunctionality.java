/*
* CIS-376
* Fall 2017
* Team Steiner
* Project ChocAn
* Group Members: Brandon Falk, Afeefeh Seblini,
* Ismaeel Varis, Daniel Vera-Burgos
*
* Employee Functionality
*
* This class contains methods that check the integrity of data before it reaches
* the database as either an insertion or deletion. These functions are for use
* in the employee terminal.
*/

package chocanserverapplication;

import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;
import chocanstructs.Bill;
import java.util.List;
import chocanserverapplication.DatabaseQueries;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EmployeeFunctionality
{
    /**
     * takes an employee struct in as input, validates the data in the struct,
     * adds the struct to the Employee table of the database, returns successful
     * or not
     *
     * @param employeeCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createEmployee(Employee employeeCreationData)
    {
        //call the verifyData function from the employee struct on employee data
        //if verifyData returns false, return 4
        if (!employeeCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertEmployee(employeeCreationData);
        
        //if query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an Employee entry in the database
     *
     * @param employeeCreationData data of employee to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateEmployee(Employee employeeCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!employeeCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateEmployee(employeeCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * takes a provider struct in as input, validates the data in the struct,
     * adds the struct to the provider table of the database, returns successful
     * or not
     *
     * @param providerCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createProvider(Provider providerCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!providerCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertProvider(providerCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an Provider entry in the database
     *
     * @param providerCreationData data of provider to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateProvider(Provider providerCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!providerCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateProvider(providerCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * takes an member struct in as input, validates the data in the struct,
     * adds the struct to the member table of the database, returns successful
     * or not
     *
     * @param memberCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createMember(Member memberCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!memberCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertMember(memberCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an member entry in the database
     *
     * @param memberCreationData data of member to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateMember(Member memberCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!memberCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean updateSuccess = DatabaseQueries.updateMember(memberCreationData);
        
        //if the query was successful, return 0, else return 1
        if (updateSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * takes an service struct in as input, validates the data in the struct,
     * adds the struct to the servicedirectory table of the database, returns successful
     * or not
     *
     * @param serviceCreationData struct containing all the data for the entry
     * @return 0 - success, 1 - insertion failed, 4 - invalid data
     */
    public static int createService(Service serviceCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!serviceCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.insertService(serviceCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Updates an Service entry in the database
     *
     * @param serviceCreationData data of service to update
     * @return 0 - success, 1 - query failed, 4 - invalid input
     */
    public static int updateService(Service serviceCreationData)
    {
        //call the verifyData function from employeeCreationData
        //if verifyData returns false, return 4
        if (!serviceCreationData.verifyData())
            return 4;
        
        //get the query result
        boolean insertSuccess = DatabaseQueries.updateService(serviceCreationData);
        
        //if the query was successful, return 0, else return 1
        if (insertSuccess)
            return 0;
        else
            return 1;
    }
    
    /**
     * Creates a providerReport file in the Provider Report directory. Only
     * includes bills within a week prior to the report endTime
     * 
     * @param providerNumber
     * @param endTime
     * @return 0 - successful, 1 - no bills, 2 - provider doesn't exist
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static int createProviderReport(String providerNumber, LocalDateTime endTime) throws SQLException, FileNotFoundException, IOException
    {
        //Query the provider table in the database for a provider with the
        //provider number equal to providerNumber from the arguments of the method.
        Provider providerData = DatabaseQueries.getProvider(providerNumber);
        
        //if no provider is returned, return 1.
        if (providerData.name.equals(null))
            return 2;
        
        //Query the bills table in the database for bills with a dateBilled that
        //occurred on or within the 7 days prior to billPeriodEndDateTime with
        //the providerNumber
        ArrayList<Bill> billList = DatabaseQueries.getServicesForProvider(providerNumber, endTime);
        
        //if no bills are returned, return 1.
        if (billList.size() == 0)
            return 1;
        
        //create a new file in the provider report directory using the
        //string - providerNumber + the date from billPeriodEndDateTime + the
        //time from billPeriodEndDateTime + “ProviderReport” - as the filename
        //and the file extension being .txt
        File providerReportFile = new File("\\Provider Reports\\" + providerNumber
                + endTime + LocalDateTime.now() + ".txt");
        FileWriter outputStream = new FileWriter(providerReportFile);
        
        //write the following data from the provider table query on individual
        //lines of the new file
        //Provider name
        outputStream.write(providerData.name + "\n");
        //Provider number
        outputStream.write(providerData.providerNumber + "\n");
        //Provider street address
        outputStream.write(providerData.streetAddress + "\n");
        //Provider city
        outputStream.write(providerData.city + "\n");
        //Provider state
        outputStream.write(providerData.state + "\n");
        //Provider ZIP code
        outputStream.write(providerData.zipCode + "\n");
        
        //Create a double called feeSum and an int called numConsultations. Set both to 0.
        double feeSum = 0;
        int numConsultations = 0;
        
        //write a blank line to the file
        outputStream.write("\n");
        
        //For each bill from the bill table query
        for (Bill currentBill : billList)
        {
            //increment numConsultations
            numConsultations++;
            
            //create a Member called currentMember and a string called serviceFee
            Member currentMember;
            String serviceFee;
            
            //Query the member table for the memberData with the member number
            //equal to that of the member number listed in the bill.
            currentMember = DatabaseQueries.getMember(currentBill.memberNumber);
            
            //if not found, save “memberDataNotFound” to memberName
            if (currentMember.name.equals(null))
                currentMember.name = "memberDataNotFound";
            
            //Query the Service Directory table for the fee of the service code
            //found in the bill.
            Service currentService = DatabaseQueries.getService(currentBill.serviceNumber);
            serviceFee = Double.toString(currentService.fee);
            feeSum += currentService.fee;
            
            //add the serviceFee to feeSum
            feeSum += currentService.fee;
            
            //if not found, save “feeNotFound” to serviceFee
            if (currentService.name.equals(null))
                currentService.name = "feeNotFound";
            
            //write the following to individual lines in the file
            //Date of service (MM-DD-YYYY)
            outputStream.write("\t" + currentBill.dateOfService.toString() + "\n");
            //Date and time of billing (MM-DD-YYY HH:MM:SS)
            outputStream.write("\t" + currentBill.dateTimeBilled.toString() + "\n");
            //memberName
            outputStream.write("\t" + currentMember.name + "\n");
            //memberNumber
            outputStream.write("\t" + currentBill.memberNumber + "\n");
            //Service Code
            outputStream.write("\t" + currentBill.serviceNumber + "\n");
            //serviceFee
            outputStream.write("\t" + serviceFee + "\n");
            //a blank line
            outputStream.write("\n");
        }
        
        //Write numConsultations to the file on its’ own line
        outputStream.write(numConsultations + "\n");
        
        //If feeSum >= 10000
        //write 99999.99 to the file on its’ own line
        //else
        //write feeSum to the file on its’ own line.
        if (feeSum >= 10000)
            outputStream.write("99999.99\n");
        else
            outputStream.write(feeSum + "\n");
        
        //close the file
        outputStream.close();
        
        return 0;
    }
    
    public static int createMemberReport(String memberNumber, LocalDateTime endTime) throws SQLException, IOException
    {
        //Query the member table in the database for a member with the member
        //number equal to memberNumber from the arguments of the method.
        Member memberData = DatabaseQueries.getMember(memberNumber);
        
        //if no member is returned, return 2.
        if (memberData.name.equals(null))
            return 2;
        
        //Query the bills table in the database for bills with a dateBilled that
        //occurred on or within the 7 days prior to billPeriodEndDateTime with
        //the memberNumber
        ArrayList<Bill> billList = DatabaseQueries.getServicesForMember(memberNumber, endTime);
        
        //if no bills are returned, return 1.
        if (billList.size() == 0)
            return 1;
        
        //create a new file in the member report directory using the
        //string - memberNumber + the date from billPeriodEndDateTime + the time
        //from billPeriodEndDateTime + “MemberReport” - as the filename and the
        //file extension being .txt
        //memberReportFile.createNewFile();
        String fileName = "\\Member Reports\\" + memberNumber + endTime + LocalDateTime.now().hashCode() + ".txt";
        fileName = fileName.replaceAll(":", " ");
        System.out.println(fileName);
        
        FileWriter fileWriter = new FileWriter(fileName);
        
        BufferedWriter outputStream = new BufferedWriter(fileWriter);
        
        //write the following data from the member table query on individual
        //lines of the new file
        //Member name
        outputStream.write(memberData.name + "\n");
        //Member number
        outputStream.write(memberData.memberNumber + "\n");
        //Member street address
        outputStream.write(memberData.streetAddress + "\n");
        //Member city
        outputStream.write(memberData.city + "\n");
        //Member state
        outputStream.write(memberData.state + "\n");
        //Member ZIP code
        outputStream.write(memberData.zipCode + "\n");
        
        //For each bill from the bill table query
        for (Bill currentBill : billList)
        {
            //write a blank line to the file
            outputStream.write("\n");
            
            //create a Provider called currentProvider
            Provider currentProvider;
            
            //Query the provider table for the name of a provider with the
            //provider number equal to that of the provider number listed in the bill.
            currentProvider = DatabaseQueries.getProvider(currentBill.providerNumber);
            
            //if not found, save “providerDataNotFound” to providerName
            if (currentProvider.name.equals(null))
                currentProvider.name = "providerDataNotFound";
            
            //Query the Service Directory table for the name of the service code
            //found in the bill.
            //if found, save service name to serviceName
            //if not found, save “ServiceNotFound” to serviceName
            Service currentService = DatabaseQueries.getService(currentBill.serviceNumber);
            if (currentService.name.equals(null))
                currentService.name = "ServiceNotFound";
            
            //write the following to individual lines in the file
            //Date of service (MM-DD-YYYY)
            outputStream.write("\t" + currentBill.dateOfService.toString() + "\n");
            //providerName
            outputStream.write("\t" + currentProvider.name + "\n");
            //serviceName
            outputStream.write("\t" + currentService.name + "\n");
        }
        
        //close the file
        outputStream.close();
        fileWriter.close();
        
        return 0;
    }
    
    public static int createSummaryReport(LocalDateTime endTime) throws IOException, SQLException
    {
        //Create an integer called totalProviders and set it to 0
        int totalProviders = 0;
        
        //Create an integer called totalConsultations and set it to 0
        int totalConsultations = 0;
        
        //Create a double called totalFee and set it to 0
        double totalFee = 0;
        
        //Create a new file in the Summary Report directory using the string - the
        //date from billPeriodEndDate + date and time from now + “SummaryReport”
        // - as the filename and the file extension being .txt
        File summaryReportFile = new File("\\Summary Reports\\" + endTime + 
                LocalDateTime.now() + ".txt");
        FileWriter outputStream = new FileWriter(summaryReportFile);
        
        //Query the bills table for bills added in the 7 days before
        //billPeriodEndDateTime. Order the query first by providerNumber.
        ArrayList<Bill> billList = DatabaseQueries.getServicesBeforeDate(endTime);
        
        //if the query returned no results, return 1
        if (billList.size() == 0)
            return 1;
        
        //Create an string called currentProviderNumber and set it to the first
        //bill in the table returned from the query’s providerNumber
        String currentProviderNumber = billList.get(0).providerNumber;
        
        //Increment totalProviders by 1
        totalProviders++;
        
        //Create an integer called currentProviderConsultations and set it to 0
        int currentProviderConsultations = 0;
        
        //Create a double called currentProviderTotalFee and set it to 0
        double currentProviderTotalFee = 0;
        
        //For each bill in the table returned from the query
        for (Bill currentBill: billList)
        {
            //if the bill’s providerNumber does not equal the currentProviderNumber
            if (!currentBill.providerNumber.equals(currentProviderNumber))
            {
                //write currentProviderNumber to it’s own line in the file
                outputStream.write(currentProviderNumber + "\n");
                //write currentProviderConsultations to it’s own line in the file
                outputStream.write(currentProviderConsultations + "\n");
                //write currentProviderTotalFee to it’s own line in the file
                outputStream.write(currentProviderTotalFee + "\n");
                //write a blank line to the file
                outputStream.write("\n");
                
                //set the currentProviderNumber to the bill’s providerNumber
                currentProviderNumber = currentBill.providerNumber;
                
                //increment totalProviders by 1
                totalProviders++;
                
                //set currentProviderConsultations to 0
                currentProviderConsultations = 0;
                
                //set currentProviderTotalFee to 0
                currentProviderTotalFee = 0;
            }
            
            //increment currentProviderConsultations and totalConsultations by 1
            currentProviderConsultations++;
            
            //query to service directory table for the fee of the service who’s
            //serviceCode equals the serviceCode in the bill
            double currentFee = DatabaseQueries.getService(currentBill.serviceNumber).fee;
            
            //add the result of the previous query to currentProviderTotalFee and to totalFee
            currentProviderTotalFee += currentFee;
            totalFee += currentFee;
        }
        //write currentProviderNumber to it’s own line in the file
        outputStream.write(currentProviderNumber + "\n");
        //write currentProviderConsultations to it’s own line in the file
        outputStream.write(currentProviderConsultations + "\n");
        //write currentProviderTotalFee to it’s own line in the file
        outputStream.write(currentProviderTotalFee + "\n");
        //write a blank line to the file
        outputStream.write("\n");
        //write totalProviders to it’s own line in the file
        outputStream.write(totalProviders + "\n");
        //write totalConsultations to it’s own line in the file
        outputStream.write(totalConsultations + "\n");
        //write totalFee to it’s own line in the file
        outputStream.write(Double.toString(totalFee));
        
        //close the file
        outputStream.close();
        
        return 0;
    }
    
    public static int createSummaryAndEFTReports(LocalDateTime endTime) throws IOException, SQLException
    {
        //Create an integer called totalProviders and set it to 0
        int totalProviders = 0;
        
        //Create an integer called totalConsultations and set it to 0
        int totalConsultations = 0;
        
        //Create a double called totalFee and set it to 0
        double totalFee = 0;
        
        //Create a new file in the Summary Report directory using the string - the
        //date from billPeriodEndDate + date and time from now + “SummaryReport”
        // - as the filename and the file extension being .txt
        File summaryReportFile = new File("\\Summary Reports\\" + endTime + 
                LocalDateTime.now() + ".txt");
        FileWriter outputStream = new FileWriter(summaryReportFile);
        
        //Query the bills table for bills added in the 7 days before
        //billPeriodEndDateTime. Order the query first by providerNumber.
        ArrayList<Bill> billList = DatabaseQueries.getServicesBeforeDate(endTime);
        
        //if the query returned no results, return 1
        if (billList.size() == 0)
            return 1;
        
        //Create an string called currentProviderNumber and set it to the first
        //bill in the table returned from the query’s providerNumber
        String currentProviderNumber = billList.get(0).providerNumber;
        
        //Increment totalProviders by 1
        totalProviders++;
        
        //Create an integer called currentProviderConsultations and set it to 0
        int currentProviderConsultations = 0;
        
        //Create a double called currentProviderTotalFee and set it to 0
        double currentProviderTotalFee = 0;
        
        //For each bill in the table returned from the query
        for (Bill currentBill: billList)
        {
            //if the bill’s providerNumber does not equal the currentProviderNumber
            if (!currentBill.providerNumber.equals(currentProviderNumber))
            {
                //write currentProviderNumber to it’s own line in the file
                outputStream.write(currentProviderNumber + "\n");
                //write currentProviderConsultations to it’s own line in the file
                outputStream.write(currentProviderConsultations + "\n");
                //write currentProviderTotalFee to it’s own line in the file
                outputStream.write(currentProviderTotalFee + "\n");
                //write a blank line to the file
                outputStream.write("\n");
                
                //Get providerName and create EFT file
                Provider currentProviderInfo = DatabaseQueries.getProvider(currentProviderNumber);
                createEFTReport(currentProviderInfo.name, currentProviderNumber, currentProviderTotalFee, endTime);
                
                //set the currentProviderNumber to the bill’s providerNumber
                currentProviderNumber = currentBill.providerNumber;
                
                //increment totalProviders by 1
                totalProviders++;
                
                //set currentProviderConsultations to 0
                currentProviderConsultations = 0;
                
                //set currentProviderTotalFee to 0
                currentProviderTotalFee = 0;
            }
            
            //increment currentProviderConsultations and totalConsultations by 1
            currentProviderConsultations++;
            
            //query to service directory table for the fee of the service who’s
            //serviceCode equals the serviceCode in the bill
            double currentFee = DatabaseQueries.getService(currentBill.serviceNumber).fee;
            
            //add the result of the previous query to currentProviderTotalFee and to totalFee
            currentProviderTotalFee += currentFee;
            totalFee += currentFee;
        }
        //write currentProviderNumber to it’s own line in the file
        outputStream.write(currentProviderNumber + "\n");
        //write currentProviderConsultations to it’s own line in the file
        outputStream.write(currentProviderConsultations + "\n");
        //write currentProviderTotalFee to it’s own line in the file
        outputStream.write(currentProviderTotalFee + "\n");
        //write a blank line to the file
        outputStream.write("\n");
        
        //Get providerName and create EFT file
        Provider currentProviderInfo = DatabaseQueries.getProvider(currentProviderNumber);
        createEFTReport(currentProviderInfo.name, currentProviderNumber, currentProviderTotalFee, endTime);
                
        //write totalProviders to it’s own line in the file
        outputStream.write(totalProviders + "\n");
        //write totalConsultations to it’s own line in the file
        outputStream.write(totalConsultations + "\n");
        //write totalFee to it’s own line in the file
        outputStream.write(Double.toString(totalFee));
        
        //close the file
        outputStream.close();
        
        return 0;
    }
    
    private static void createEFTReport(String providerName, String providerNumber, Double amountToBeTransferred, LocalDateTime endTime) throws IOException
    {
        File EFTReportFile = new File("\\EFT Reports\\" + endTime + 
                LocalDateTime.now() + ".txt");
        FileWriter outputStream = new FileWriter(EFTReportFile);
        
        outputStream.write(providerName + "\n");
        outputStream.write(providerNumber + "\n");
        outputStream.write(Double.toString(amountToBeTransferred));
        
        outputStream.close();
    }
}
