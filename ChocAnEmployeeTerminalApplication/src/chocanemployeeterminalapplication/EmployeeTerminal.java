/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanemployeeterminalapplication;
import java.util.ArrayList;
import chocanstructs.Employee;
import chocanstructs.Provider;
import chocanstructs.Member;
import chocanstructs.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Afeefeh
 */
public class EmployeeTerminal extends javax.swing.JFrame {

    private int clientPrivileges;
    private ChocAnEmployeeTerminal employeeTerminal;
    private int currentSDCommand;
    private int currentPCommand;
    private int currentMCommand;
    private int currentECommand;
    
    /**
     * Creates new form EmployeeTerminal
     */
    private EmployeeTerminal() {
        clientPrivileges = -1;
        employeeTerminal = new ChocAnEmployeeTerminal();
        initComponents();
        updateTabs();
        currentSDCommand = -1;
        currentPCommand = -1;
        currentMCommand = -1;
        currentECommand = -1;
    }
    
    private void updateServiceDirectoryTable()
    {
        try
        {
            ArrayList<Service> allServices = employeeTerminal.getAllServices();
            
            ServiceTable newTable = new ServiceTable(allServices);
            
            tableSD.setModel(newTable.getModel());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            employeeTerminal.disconnect();
            clientPrivileges = -1;
            updateTabs();
        }
    }
    
    private void setServiceFieldsEditable(Boolean editable)
    {
        serviceCodeInputSD.setEditable(editable);
        nameInputSD.setEditable(editable);
        feeInputSD.setEditable(editable);
        isActiveCheckboxSD.setEnabled(editable);
        submitButtonSD.setEnabled(editable);
    }
    
    private void clearDataSD()
    {
        serviceCodeInputSD.setText("");
        nameInputSD.setText("");
        feeInputSD.setText("");
        isActiveCheckboxSD.setSelected(false);
    }
    
    private void updateProviderTable()
    {
        try
        {
            ArrayList<Provider> allProviders = employeeTerminal.getAllProviders();
            
            ProviderTable newTable = new ProviderTable(allProviders);
            
            tableP.setModel(newTable.getModel());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            employeeTerminal.disconnect();
            clientPrivileges = -1;
            updateTabs();
        }
    }
    
    private void setProviderFieldsEditable(Boolean editable)
    {
        providerNumberInputP.setEditable(editable);
        passwordInputP.setEditable(editable);
        nameInputP.setEditable(editable);
        emailAddressInputP.setEditable(editable);
        addressInputP.setEditable(editable);
        cityInputP.setEditable(editable);
        stateInputP.setEditable(editable);
        zipInputP.setEditable(editable);
        isActiveCheckBoxP.setEnabled(editable);
        submitInfoP.setEnabled(editable);
    }
    
    private void clearDataP()
    {
        providerNumberInputP.setText("");
        passwordInputP.setText("");
        nameInputP.setText("");
        emailAddressInputP.setText("");
        addressInputP.setText("");
        cityInputP.setText("");
        stateInputP.setText("");
        zipInputP.setText("");
        isActiveCheckBoxP.setSelected(false);
    }
    
    private void updateMemberTable()
    {
        try
        {
            ArrayList<Member> allMembers = employeeTerminal.getAllMembers();
            
            MemberTable newTable = new MemberTable(allMembers);
            
            tableM.setModel(newTable.getModel());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            employeeTerminal.disconnect();
            clientPrivileges = -1;
            updateTabs();
        }
    }
    
    private void setMemberFieldsEditable(Boolean editable)
    {
        memberNumberInputM.setEditable(editable);
        nameInputM.setEditable(editable);
        emailAddressInputM.setEditable(editable);
        addressInputM.setEditable(editable);
        cityInputM.setEditable(editable);
        stateInputM.setEditable(editable);
        zipInputM.setEditable(editable);
        isValidCheckBoxM.setEnabled(editable);
        validityReasonInputM.setEditable(editable);
        isActiveCheckBoxM.setEnabled(editable);
        submitInfoM.setEnabled(editable);
    }
    
    private void clearDataM()
    {
        memberNumberInputM.setText("");
        nameInputM.setText("");
        emailAddressInputM.setText("");
        addressInputM.setText("");
        cityInputM.setText("");
        stateInputM.setText("");
        zipInputM.setText("");
        isValidCheckBoxM.setSelected(false);
        validityReasonInputM.setText("");
        isActiveCheckBoxM.setSelected(false);
    }
    
    private void updateEmployeeTable()
    {
        try
        {
            ArrayList<Employee> allEmployees = employeeTerminal.getAllEmployees();
            
            EmployeeTable newTable = new EmployeeTable(allEmployees);
            
            tableE.setModel(newTable.getModel());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            employeeTerminal.disconnect();
            clientPrivileges = -1;
            updateTabs();
        }
    }
    
    private void setEmployeeFieldsEditable(Boolean editable)
    {
        employeeNumberInputE.setEditable(editable);
        passwordInputE.setEditable(editable);
        nameInputE.setEditable(editable);
        emailAddressInputE.setEditable(editable);
        addressInputE.setEditable(editable);
        cityInputE.setEditable(editable);
        stateInputE.setEditable(editable);
        zipInputE.setEditable(editable);
        isActiveCheckBoxE.setEnabled(editable);
        isManagerCheckBoxE.setEnabled(editable);
        submitInfoE.setEnabled(editable);
    }
    
    private void clearDataE()
    {
        employeeNumberInputE.setText("");
        passwordInputE.setText("");
        nameInputE.setText("");
        emailAddressInputE.setText("");
        addressInputE.setText("");
        cityInputE.setText("");
        stateInputE.setText("");
        zipInputE.setText("");
        isActiveCheckBoxE.setSelected(false);
        isManagerCheckBoxE.setSelected(false);
    }
    
    private void updateTabs(){ 
         if(clientPrivileges == 0){
            mainPanel.add(serviceDirectoryPanel);
            mainPanel.setTitleAt(1, "Service Directory");
            mainPanel.add(providersPanel);
            mainPanel.setTitleAt(2, "Providers");
            mainPanel.add(membersPanel);
            mainPanel.setTitleAt(3, "Members");
            mainPanel.add(employeesPanel);
            mainPanel.remove(loginPanel);
            logoutButton.setVisible(true);
            welcomeLabel.setVisible(true); 
            
            updateServiceDirectoryTable();
            updateProviderTable();
            updateMemberTable();
         }
         else if(clientPrivileges == 1){
            mainPanel.add(serviceDirectoryPanel);
            mainPanel.setTitleAt(1, "Service Directory");
            mainPanel.add(providersPanel);
            mainPanel.setTitleAt(2, "Providers");
            mainPanel.add(membersPanel);
            mainPanel.setTitleAt(3, "Members");
            mainPanel.add(employeesPanel);
            mainPanel.setTitleAt(4, "Employees");
            mainPanel.add(requestReportPanel);
            mainPanel.setTitleAt(5, "Reports");
            mainPanel.remove(loginPanel);
            logoutButton.setVisible(true);
            welcomeLabel.setVisible(true); 

            updateServiceDirectoryTable();
            updateProviderTable();
            updateMemberTable();
            updateEmployeeTable();
         }
         else{
            mainPanel.remove(serviceDirectoryPanel);
            mainPanel.remove(providersPanel);
            mainPanel.remove(membersPanel);
            mainPanel.remove(employeesPanel);
            mainPanel.remove(requestReportPanel);
            mainPanel.add(loginPanel);
            mainPanel.setTitleAt(0, "Login");
            logoutButton.setVisible(false);
            welcomeLabel.setVisible(false);
         }
    }
    
    private void displayAlert(String message, String titleBar)
    {
        JOptionPane.showMessageDialog(null, message, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JTabbedPane();
        loginPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        instructionsLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        providersPanel = new javax.swing.JPanel();
        scrollPaneP = new javax.swing.JScrollPane();
        tableP = new javax.swing.JTable();
        providersLabel = new javax.swing.JLabel();
        refreshButtonP = new javax.swing.JButton();
        createButtonP = new javax.swing.JButton();
        editButtonP = new javax.swing.JButton();
        formPanelP = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        providerNumberInputP = new javax.swing.JTextField();
        nameInputP = new javax.swing.JTextField();
        addressInputP = new javax.swing.JTextField();
        cityInputP = new javax.swing.JTextField();
        zipInputP = new javax.swing.JTextField();
        stateInputP = new javax.swing.JTextField();
        submitInfoP = new javax.swing.JButton();
        passwordInputP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        emailAddressInputP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        isActiveCheckBoxP = new javax.swing.JCheckBox();
        providerInfoLabel = new javax.swing.JLabel();
        membersPanel = new javax.swing.JPanel();
        scrollPanelM = new javax.swing.JScrollPane();
        tableM = new javax.swing.JTable();
        membersLabel = new javax.swing.JLabel();
        refreshButtonM = new javax.swing.JButton();
        createButtonM = new javax.swing.JButton();
        editButtonM = new javax.swing.JButton();
        formPanelM = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        memberNumberInputM = new javax.swing.JTextField();
        nameInputM = new javax.swing.JTextField();
        addressInputM = new javax.swing.JTextField();
        cityInputM = new javax.swing.JTextField();
        zipInputM = new javax.swing.JTextField();
        stateInputM = new javax.swing.JTextField();
        submitInfoM = new javax.swing.JButton();
        emailAddressInputM = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        isValidCheckBoxM = new javax.swing.JCheckBox();
        isActiveCheckBoxM = new javax.swing.JCheckBox();
        validityReasonInputM = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        memberInfoLabel = new javax.swing.JLabel();
        requestReportPanel = new javax.swing.JPanel();
        requestReportButton = new javax.swing.JButton();
        dateScrollPane = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        requestReportInstructions = new javax.swing.JLabel();
        numberInputR = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        datePickerR = new org.jdesktop.swingx.JXDatePicker();
        employeesPanel = new javax.swing.JPanel();
        employeesLabel = new javax.swing.JLabel();
        scrollPanelE = new javax.swing.JScrollPane();
        tableE = new javax.swing.JTable();
        refreshButtonE = new javax.swing.JButton();
        createButtonE = new javax.swing.JButton();
        editButtonE = new javax.swing.JButton();
        formPanelE = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        employeeNumberInputE = new javax.swing.JTextField();
        nameInputE = new javax.swing.JTextField();
        addressInputE = new javax.swing.JTextField();
        cityInputE = new javax.swing.JTextField();
        zipInputE = new javax.swing.JTextField();
        stateInputE = new javax.swing.JTextField();
        submitInfoE = new javax.swing.JButton();
        passwordInputE = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        emailAddressInputE = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        isActiveCheckBoxE = new javax.swing.JCheckBox();
        isManagerCheckBoxE = new javax.swing.JCheckBox();
        employeeInfoLabel = new javax.swing.JLabel();
        serviceDirectoryPanel = new javax.swing.JPanel();
        refreshButtonSD = new javax.swing.JButton();
        createButtonSD = new javax.swing.JButton();
        editButtonSD = new javax.swing.JButton();
        servicesLabel = new javax.swing.JLabel();
        formPanelSD = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        serviceCodeInputSD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameInputSD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        feeInputSD = new javax.swing.JTextField();
        submitButtonSD = new javax.swing.JButton();
        isActiveCheckboxSD = new javax.swing.JCheckBox();
        serviceInfoLabel = new javax.swing.JLabel();
        scrollPanelSD = new javax.swing.JScrollPane();
        tableSD = new javax.swing.JTable();
        titlePanel = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        chocAnLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chocoholics Anonymous");
        setBackground(new java.awt.Color(230, 242, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(236, 245, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        mainPanel.setName(""); // NOI18N

        loginPanel.setBackground(new java.awt.Color(237, 245, 255));

        loginButton.setBackground(new java.awt.Color(255, 255, 255));
        loginButton.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        loginButton.setText("Login");
        loginButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(198, 218, 243), null));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        instructionsLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        instructionsLabel.setText("Enter information to continue:");

        nameLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        nameLabel.setText("ID");

        nameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        passwordLabel.setText("Password");

        passwordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordInputActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel7.setText("Welcome To Chocoholics Anpnymous!");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(instructionsLabel)
                .addGap(323, 323, 323))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameLabel)
                            .addComponent(passwordLabel))
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(427, 427, 427)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel7)))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(instructionsLabel)
                .addGap(55, 55, 55)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(45, 45, 45)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );

        mainPanel.addTab("Log in", loginPanel);

        providersPanel.setBackground(new java.awt.Color(237, 245, 255));

        scrollPaneP.setBackground(new java.awt.Color(243, 237, 237));
        scrollPaneP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPaneP.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneP.setViewportView(tableP);

        providersLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        providersLabel.setText("Providers:");

        refreshButtonP.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        refreshButtonP.setText("Refresh");
        refreshButtonP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshButtonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonPActionPerformed(evt);
            }
        });

        createButtonP.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        createButtonP.setText("Create");
        createButtonP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createButtonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonPActionPerformed(evt);
            }
        });

        editButtonP.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        editButtonP.setText("Edit");
        editButtonP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editButtonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonPActionPerformed(evt);
            }
        });

        formPanelP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Provider Number:");

        jLabel5.setText("Name:");

        jLabel6.setText("Street Address: ");

        jLabel8.setText("City:");

        jLabel9.setText("State:");

        jLabel10.setText("ZIP Code:");

        providerNumberInputP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                providerNumberInputPActionPerformed(evt);
            }
        });

        submitInfoP.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        submitInfoP.setText("SUBMIT");
        submitInfoP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitInfoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitInfoPActionPerformed(evt);
            }
        });

        jLabel11.setText("Password:");

        jLabel18.setText("emailAddress:");

        isActiveCheckBoxP.setText("Is Active");

        javax.swing.GroupLayout formPanelPLayout = new javax.swing.GroupLayout(formPanelP);
        formPanelP.setLayout(formPanelPLayout);
        formPanelPLayout.setHorizontalGroup(
            formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelPLayout.createSequentialGroup()
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(nameInputP, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(providerNumberInputP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(passwordInputP, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailAddressInputP, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(71, 71, 71)
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addressInputP, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formPanelPLayout.createSequentialGroup()
                                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(stateInputP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(zipInputP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(isActiveCheckBoxP))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(formPanelPLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(295, 295, 295)
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cityInputP, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitInfoP, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        formPanelPLayout.setVerticalGroup(
            formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelPLayout.createSequentialGroup()
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(providerNumberInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelPLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passwordInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailAddressInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isActiveCheckBoxP))
                        .addGap(55, 55, 55))
                    .addGroup(formPanelPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cityInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(submitInfoP)))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stateInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelPLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(zipInputP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(107, 107, 107))))
        );

        providerInfoLabel.setText("Provider Information:");

        javax.swing.GroupLayout providersPanelLayout = new javax.swing.GroupLayout(providersPanel);
        providersPanel.setLayout(providersPanelLayout);
        providersPanelLayout.setHorizontalGroup(
            providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(providersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(providersLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(providerInfoLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, providersPanelLayout.createSequentialGroup()
                        .addComponent(scrollPaneP, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createButtonP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshButtonP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editButtonP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(formPanelP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        providersPanelLayout.setVerticalGroup(
            providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, providersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(providersLabel)
                .addGap(0, 0, 0)
                .addGroup(providersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(providersPanelLayout.createSequentialGroup()
                        .addComponent(refreshButtonP)
                        .addGap(33, 33, 33)
                        .addComponent(createButtonP)
                        .addGap(33, 33, 33)
                        .addComponent(editButtonP))
                    .addComponent(scrollPaneP, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(providerInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanelP, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        providersLabel.getAccessibleContext().setAccessibleName("providersLabel ");

        mainPanel.addTab("Providers ", providersPanel);

        membersPanel.setBackground(new java.awt.Color(237, 245, 255));

        scrollPanelM.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPanelM.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelM.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N

        tableM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPanelM.setViewportView(tableM);

        membersLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        membersLabel.setText("Members:");

        refreshButtonM.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        refreshButtonM.setText("Refresh");
        refreshButtonM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshButtonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonMActionPerformed(evt);
            }
        });

        createButtonM.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        createButtonM.setText("Create");
        createButtonM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createButtonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonMActionPerformed(evt);
            }
        });

        editButtonM.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        editButtonM.setText("Edit");
        editButtonM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editButtonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonMActionPerformed(evt);
            }
        });

        formPanelM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Member Number:");

        jLabel13.setText("Name:");

        jLabel14.setText("Street Address: ");

        jLabel15.setText("City:");

        jLabel16.setText("State:");

        jLabel17.setText("ZIP Code:");

        memberNumberInputM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberNumberInputMActionPerformed(evt);
            }
        });

        submitInfoM.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        submitInfoM.setText("SUBMIT");
        submitInfoM.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitInfoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitInfoMActionPerformed(evt);
            }
        });

        jLabel25.setText("Email Address:");

        isValidCheckBoxM.setText("Is Valid");

        isActiveCheckBoxM.setText("Is Active");

        validityReasonInputM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validityReasonInputMActionPerformed(evt);
            }
        });

        jLabel26.setText("Validity Reason:");

        javax.swing.GroupLayout formPanelMLayout = new javax.swing.GroupLayout(formPanelM);
        formPanelM.setLayout(formPanelMLayout);
        formPanelMLayout.setHorizontalGroup(
            formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(memberNumberInputM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addComponent(nameInputM, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(emailAddressInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(addressInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(31, 31, 31)
                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelMLayout.createSequentialGroup()
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(stateInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17)
                            .addComponent(zipInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitInfoM, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelMLayout.createSequentialGroup()
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cityInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isActiveCheckBoxM)
                                    .addComponent(isValidCheckBoxM))
                                .addGap(15, 15, 15)
                                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(validityReasonInputM))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formPanelMLayout.setVerticalGroup(
            formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelMLayout.createSequentialGroup()
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberNumberInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cityInputM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stateInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(formPanelMLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zipInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submitInfoM))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelMLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(isActiveCheckBoxM)
                        .addGap(37, 37, 37)
                        .addComponent(isValidCheckBoxM))
                    .addGroup(formPanelMLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailAddressInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(formPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(validityReasonInputM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelMLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addressInputM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        memberInfoLabel.setText("Member Information:");

        javax.swing.GroupLayout membersPanelLayout = new javax.swing.GroupLayout(membersPanel);
        membersPanel.setLayout(membersPanelLayout);
        membersPanelLayout.setHorizontalGroup(
            membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(membersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(formPanelM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(membersLabel)
                        .addGroup(membersPanelLayout.createSequentialGroup()
                            .addComponent(scrollPanelM, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(refreshButtonM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(createButtonM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(editButtonM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(memberInfoLabel)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        membersPanelLayout.setVerticalGroup(
            membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(membersLabel)
                .addGap(0, 0, 0)
                .addGroup(membersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, membersPanelLayout.createSequentialGroup()
                        .addComponent(refreshButtonM)
                        .addGap(33, 33, 33)
                        .addComponent(createButtonM)
                        .addGap(33, 33, 33)
                        .addComponent(editButtonM))
                    .addComponent(scrollPanelM, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memberInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanelM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        mainPanel.addTab("Members", membersPanel);

        requestReportPanel.setBackground(new java.awt.Color(237, 245, 255));

        requestReportButton.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        requestReportButton.setText("Request Report");
        requestReportButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        requestReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestReportButtonActionPerformed(evt);
            }
        });

        jList1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Member Report", "Provider Report", "Summary Report" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setToolTipText("");
        dateScrollPane.setViewportView(jList1);

        requestReportInstructions.setText("Choose a date to request a report for:");

        jLabel29.setText("Member/Provider Number:");

        javax.swing.GroupLayout requestReportPanelLayout = new javax.swing.GroupLayout(requestReportPanel);
        requestReportPanel.setLayout(requestReportPanelLayout);
        requestReportPanelLayout.setHorizontalGroup(
            requestReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestReportPanelLayout.createSequentialGroup()
                .addGroup(requestReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestReportPanelLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addGroup(requestReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(requestReportPanelLayout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addGroup(requestReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(requestReportPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(numberInputR, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel29)))
                    .addGroup(requestReportPanelLayout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(requestReportInstructions))
                    .addGroup(requestReportPanelLayout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(datePickerR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(556, Short.MAX_VALUE))
        );
        requestReportPanelLayout.setVerticalGroup(
            requestReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, requestReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(requestReportInstructions)
                .addGap(18, 18, 18)
                .addComponent(datePickerR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(dateScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberInputR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(requestReportButton)
                .addContainerGap(448, Short.MAX_VALUE))
        );

        mainPanel.addTab("Reports", requestReportPanel);

        employeesPanel.setBackground(new java.awt.Color(237, 245, 255));

        employeesLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        employeesLabel.setText("Employees:");

        scrollPanelE.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPanelE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelE.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N

        tableE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPanelE.setViewportView(tableE);

        refreshButtonE.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        refreshButtonE.setText("Refresh");
        refreshButtonE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshButtonE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonEActionPerformed(evt);
            }
        });

        createButtonE.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        createButtonE.setText("Create");
        createButtonE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createButtonE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonEActionPerformed(evt);
            }
        });

        editButtonE.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        editButtonE.setText("Edit");
        editButtonE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editButtonE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonEActionPerformed(evt);
            }
        });

        formPanelE.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("Employee Number:");

        jLabel20.setText("Name:");

        jLabel21.setText("Street Address: ");

        jLabel22.setText("City:");

        jLabel23.setText("State:");

        jLabel24.setText("ZIP Code:");

        employeeNumberInputE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeNumberInputEActionPerformed(evt);
            }
        });

        submitInfoE.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        submitInfoE.setText("SUBMIT");
        submitInfoE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitInfoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitInfoEActionPerformed(evt);
            }
        });

        jLabel27.setText("Password:");

        jLabel28.setText("Email Address:");

        isActiveCheckBoxE.setText("Is Active");

        isManagerCheckBoxE.setText("Is Manager");

        javax.swing.GroupLayout formPanelELayout = new javax.swing.GroupLayout(formPanelE);
        formPanelE.setLayout(formPanelELayout);
        formPanelELayout.setHorizontalGroup(
            formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20)
                        .addComponent(jLabel19)
                        .addComponent(passwordInputE, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addComponent(nameInputE)
                        .addComponent(employeeNumberInputE))
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(emailAddressInputE, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(formPanelELayout.createSequentialGroup()
                            .addComponent(cityInputE)
                            .addGap(182, 182, 182)
                            .addComponent(submitInfoE, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(formPanelELayout.createSequentialGroup()
                            .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(formPanelELayout.createSequentialGroup()
                                    .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23)
                                        .addComponent(stateInputE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(59, 59, 59)
                                    .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24)
                                        .addComponent(zipInputE, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isManagerCheckBoxE)))
                                .addComponent(jLabel22))
                            .addGap(411, 411, 411)))
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(addressInputE, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isActiveCheckBoxE))
                        .addGap(186, 186, 186))))
        );
        formPanelELayout.setVerticalGroup(
            formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employeeNumberInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelELayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cityInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(submitInfoE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelELayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zipInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailAddressInputE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isActiveCheckBoxE)
                    .addComponent(isManagerCheckBoxE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        employeeInfoLabel.setText("Employee Information:");

        javax.swing.GroupLayout employeesPanelLayout = new javax.swing.GroupLayout(employeesPanel);
        employeesPanel.setLayout(employeesPanelLayout);
        employeesPanelLayout.setHorizontalGroup(
            employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employeesPanelLayout.createSequentialGroup()
                        .addComponent(employeesLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(employeesPanelLayout.createSequentialGroup()
                        .addGroup(employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(employeesPanelLayout.createSequentialGroup()
                                .addComponent(scrollPanelE, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(createButtonE, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editButtonE, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(refreshButtonE, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(employeeInfoLabel)
                            .addComponent(formPanelE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 163, Short.MAX_VALUE))))
        );
        employeesPanelLayout.setVerticalGroup(
            employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeesLabel)
                .addGap(0, 0, 0)
                .addGroup(employeesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelE, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeesPanelLayout.createSequentialGroup()
                        .addComponent(refreshButtonE)
                        .addGap(33, 33, 33)
                        .addComponent(createButtonE)
                        .addGap(33, 33, 33)
                        .addComponent(editButtonE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        mainPanel.addTab("Employees", employeesPanel);

        serviceDirectoryPanel.setBackground(new java.awt.Color(236, 245, 255));
        serviceDirectoryPanel.setName("Service Directory "); // NOI18N

        refreshButtonSD.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        refreshButtonSD.setText("Refresh");
        refreshButtonSD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        refreshButtonSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSDActionPerformed(evt);
            }
        });

        createButtonSD.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        createButtonSD.setText("Create");
        createButtonSD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createButtonSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonSDActionPerformed(evt);
            }
        });

        editButtonSD.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        editButtonSD.setText("Edit");
        editButtonSD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editButtonSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonSDActionPerformed(evt);
            }
        });

        servicesLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        servicesLabel.setText("Services:");

        formPanelSD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Service Number:");

        jLabel2.setText("Service Name:");

        nameInputSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameInputSDActionPerformed(evt);
            }
        });

        jLabel3.setText("Service Fee:");

        submitButtonSD.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        submitButtonSD.setText("SUBMIT");
        submitButtonSD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitButtonSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonSDActionPerformed(evt);
            }
        });

        isActiveCheckboxSD.setText("Is Actvice");

        javax.swing.GroupLayout formPanelSDLayout = new javax.swing.GroupLayout(formPanelSD);
        formPanelSD.setLayout(formPanelSDLayout);
        formPanelSDLayout.setHorizontalGroup(
            formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelSDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelSDLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(serviceCodeInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelSDLayout.createSequentialGroup()
                        .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13)
                        .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelSDLayout.createSequentialGroup()
                                .addComponent(nameInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(isActiveCheckboxSD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(submitButtonSD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(feeInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        formPanelSDLayout.setVerticalGroup(
            formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelSDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(serviceCodeInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(submitButtonSD)
                    .addComponent(isActiveCheckboxSD))
                .addGap(25, 25, 25)
                .addGroup(formPanelSDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(feeInputSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        serviceInfoLabel.setText("Service Information:");

        scrollPanelSD.setBackground(new java.awt.Color(242, 245, 247));
        scrollPanelSD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPanelSD.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelSD.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        scrollPanelSD.setOpaque(false);

        tableSD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPanelSD.setViewportView(tableSD);

        javax.swing.GroupLayout serviceDirectoryPanelLayout = new javax.swing.GroupLayout(serviceDirectoryPanel);
        serviceDirectoryPanel.setLayout(serviceDirectoryPanelLayout);
        serviceDirectoryPanelLayout.setHorizontalGroup(
            serviceDirectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serviceDirectoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serviceDirectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(servicesLabel)
                    .addGroup(serviceDirectoryPanelLayout.createSequentialGroup()
                        .addComponent(scrollPanelSD, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(serviceDirectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createButtonSD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshButtonSD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editButtonSD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(serviceInfoLabel)
                    .addComponent(formPanelSD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        serviceDirectoryPanelLayout.setVerticalGroup(
            serviceDirectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serviceDirectoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(servicesLabel)
                .addGap(1, 1, 1)
                .addGroup(serviceDirectoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(serviceDirectoryPanelLayout.createSequentialGroup()
                        .addComponent(refreshButtonSD)
                        .addGap(33, 33, 33)
                        .addComponent(createButtonSD)
                        .addGap(32, 32, 32)
                        .addComponent(editButtonSD))
                    .addComponent(scrollPanelSD, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(serviceInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanelSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        servicesLabel.getAccessibleContext().setAccessibleName("Services ");

        mainPanel.addTab("Service Directory ", serviceDirectoryPanel);

        titlePanel.setBackground(new java.awt.Color(236, 245, 255));

        logoutButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutButton.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        logoutButton.setText("Log out");
        logoutButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        welcomeLabel.setText("Welcome, ");

        chocAnLabel.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        chocAnLabel.setText("Chocoholics Anonymous");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chocAnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addComponent(chocAnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonSDActionPerformed
        setServiceFieldsEditable(true);
        
        clearDataSD();
        
        currentSDCommand = 0;
    }//GEN-LAST:event_createButtonSDActionPerformed

    private void refreshButtonSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSDActionPerformed
        updateServiceDirectoryTable();
    }//GEN-LAST:event_refreshButtonSDActionPerformed

    private void refreshButtonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonPActionPerformed
        updateProviderTable();
    }//GEN-LAST:event_refreshButtonPActionPerformed

    private void createButtonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonPActionPerformed
        setProviderFieldsEditable(true);
        
        clearDataP();
        
        currentPCommand = 0;
    }//GEN-LAST:event_createButtonPActionPerformed

    private void refreshButtonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonMActionPerformed
        updateMemberTable();
    }//GEN-LAST:event_refreshButtonMActionPerformed

    private void createButtonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonMActionPerformed
        setMemberFieldsEditable(true);
        
        clearDataM();
        
        currentMCommand = 0;
    }//GEN-LAST:event_createButtonMActionPerformed

    private void refreshButtonEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonEActionPerformed
        updateEmployeeTable();
    }//GEN-LAST:event_refreshButtonEActionPerformed

    private void createButtonEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonEActionPerformed
        setEmployeeFieldsEditable(true);
        
        clearDataE();
        
        currentECommand = 0;
    }//GEN-LAST:event_createButtonEActionPerformed

    private void submitButtonSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonSDActionPerformed
        Service service = new Service();
        service.serviceNumber = serviceCodeInputSD.getText();
        service.name = nameInputSD.getText();
        boolean doubleConverted = false;
        try
        {
            service.fee = Double.parseDouble(feeInputSD.getText());
            doubleConverted = true;
        } catch (Exception e)
        {
            doubleConverted = false;
        }
        service.isActive = isActiveCheckboxSD.isSelected();
        
        if (service.verifyData() && doubleConverted)
        {
            int returnValue = -1;
            
            try
            {
                if (currentSDCommand == 0)
                {
                    returnValue = employeeTerminal.insertService(service);
                }
                else if (currentSDCommand == 1)
                {
                    returnValue = employeeTerminal.updateService(service);
                }
                
                if (returnValue == 0)
                {
                    displayAlert("Entry Successfully Inserted/Edited", "Submit Service Alert");
                    setServiceFieldsEditable(false);
                    currentSDCommand = -1;
                    clearDataSD();
                }
                else if (returnValue == 1)
                {
                    displayAlert("Connection to database failed at server", "Submit Service Alert");
                }
                else if (returnValue == 4)
                {
                    displayAlert("Invalid Data", "Submit Service Alert");
                }
                
            } catch(Exception e)
            {
                displayAlert("Connection to server failed", "Submit Service Alert");
            }
            
        } else
        {
            displayAlert("Invalid Input", "Submit Service Alert");
        }
    }//GEN-LAST:event_submitButtonSDActionPerformed

    private void nameInputSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputSDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputSDActionPerformed

    private void providerNumberInputPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_providerNumberInputPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_providerNumberInputPActionPerformed

    private void memberNumberInputMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberNumberInputMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memberNumberInputMActionPerformed

    private void editButtonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonMActionPerformed
        setMemberFieldsEditable(true);
        
        int row = tableM.getSelectedRow();
        memberNumberInputM.setText((String) tableM.getValueAt(row, 0));
        memberNumberInputM.setEditable(false);
        nameInputM.setText((String) tableM.getValueAt(row, 1));
        emailAddressInputM.setText((String) tableM.getValueAt(row, 2));
        addressInputM.setText((String) tableM.getValueAt(row, 3));
        cityInputM.setText((String) tableM.getValueAt(row, 4));
        stateInputM.setText((String) tableM.getValueAt(row, 5));
        zipInputM.setText((String) tableM.getValueAt(row, 6));
        isValidCheckBoxM.setSelected((Boolean) tableM.getValueAt(row, 7));
        validityReasonInputM.setText((String) tableM.getValueAt(row, 8));
        isActiveCheckBoxM.setSelected((Boolean) tableM.getValueAt(row, 9));
        
        currentMCommand = 1;
    }//GEN-LAST:event_editButtonMActionPerformed

    private void employeeNumberInputEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeNumberInputEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeNumberInputEActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String employeeNumber = nameInput.getText();
        String password = passwordInput.getText();
        
        clientPrivileges = employeeTerminal.login(employeeNumber, password);
        
        if (clientPrivileges == 0 || clientPrivileges == 1)
        {
            updateTabs();
            nameInput.setText(null);
            passwordInput.setText(null);
            
            setServiceFieldsEditable(false);
            setProviderFieldsEditable(false);
            setMemberFieldsEditable(false);
            setEmployeeFieldsEditable(false);
        }
        else if (clientPrivileges == 2)
        {
            displayAlert("Invalid login credentials", "Login Error");
        }
        else if (clientPrivileges == 3)
        {
            displayAlert("Database down", "Login Error");
        }
        else
        {
            displayAlert("Cannot connect to server", "Login Error");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void nameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameInputActionPerformed

    private void passwordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordInputActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        employeeTerminal.disconnect();
        
        clientPrivileges = -1;
        
        clearDataSD();
        clearDataP();
        clearDataM();
        clearDataE();
        
        updateTabs();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void editButtonSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonSDActionPerformed
        setServiceFieldsEditable(true);
        
        int entry = tableSD.getSelectedRow();
        serviceCodeInputSD.setText((String) tableSD.getValueAt(entry, 0));
        serviceCodeInputSD.setEditable(false);
        nameInputSD.setText((String) tableSD.getValueAt(entry, 1));
        feeInputSD.setText(Double.toString((Double) tableSD.getValueAt(entry, 2)));
        isActiveCheckboxSD.setSelected((Boolean) tableSD.getValueAt(entry, 3));
        
        currentSDCommand = 1;
    }//GEN-LAST:event_editButtonSDActionPerformed

    private void validityReasonInputMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validityReasonInputMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_validityReasonInputMActionPerformed

    private void editButtonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonPActionPerformed
        setProviderFieldsEditable(true);
        
        int row = tableP.getSelectedRow();
        providerNumberInputP.setText((String) tableP.getValueAt(row, 0));
        providerNumberInputP.setEditable(false);
        passwordInputP.setText((String) tableP.getValueAt(row, 1));
        nameInputP.setText((String) tableP.getValueAt(row, 2));
        emailAddressInputP.setText((String) tableP.getValueAt(row, 3));
        addressInputP.setText((String) tableP.getValueAt(row, 4));
        cityInputP.setText((String) tableP.getValueAt(row, 5));
        stateInputP.setText((String) tableP.getValueAt(row, 6));
        zipInputP.setText((String) tableP.getValueAt(row, 7));
        isActiveCheckBoxP.setSelected((Boolean) tableP.getValueAt(row, 8));
        
        currentPCommand = 1;
    }//GEN-LAST:event_editButtonPActionPerformed

    private void submitInfoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitInfoPActionPerformed
        Provider provider = new Provider();
        provider.providerNumber = providerNumberInputP.getText();
        provider.password = passwordInputP.getText();
        provider.name = nameInputP.getText();
        provider.emailAddress = emailAddressInputP.getText();
        provider.streetAddress = addressInputP.getText();
        provider.city = cityInputP.getText();
        provider.state = stateInputP.getText();
        provider.zipCode = zipInputP.getText();
        provider.isActive = isActiveCheckBoxP.isSelected();
        
        if (provider.verifyData())
        {
            int returnValue = -1;
            
            try
            {
                if (currentPCommand == 0)
                {
                    returnValue = employeeTerminal.insertProvider(provider);
                }
                else if (currentPCommand == 1)
                {
                    returnValue = employeeTerminal.updateProvider(provider);
                }
                
                if (returnValue == 0)
                {
                    displayAlert("Entry Successfully Inserted/Edited", "Submit Provider Alert");
                    setProviderFieldsEditable(false);
                    currentPCommand = -1;
                    clearDataP();
                }
                else if (returnValue == 1)
                {
                    displayAlert("Connection to database failed at server", "Submit Provider Alert");
                }
                else if (returnValue == 4)
                {
                    displayAlert("Invalid Data", "Submit Provider Alert");
                }
                
            } catch(Exception e)
            {
                displayAlert("Connection to server failed", "Submit Provider Alert");
            }
            
        } else
        {
            displayAlert("Invalid Input", "Submit Provider Alert");
        }
    }//GEN-LAST:event_submitInfoPActionPerformed

    private void submitInfoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitInfoMActionPerformed
        Member member = new Member();
        member.memberNumber = memberNumberInputM.getText();
        member.name = nameInputM.getText();
        member.emailAddress = emailAddressInputM.getText();
        member.streetAddress = addressInputM.getText();
        member.city = cityInputM.getText();
        member.state = stateInputM.getText();
        member.zipCode = zipInputM.getText();
        member.isValid = isValidCheckBoxM.isSelected();
        member.validityReason = validityReasonInputM.getText();
        member.isActive = isActiveCheckBoxM.isSelected();
        
        if (member.verifyData())
        {
            int returnValue = -1;
            
            try
            {
                if (currentMCommand == 0)
                {
                    returnValue = employeeTerminal.insertMember(member);
                }
                else if (currentMCommand == 1)
                {
                    returnValue = employeeTerminal.updateMember(member);
                }
                
                if (returnValue == 0)
                {
                    displayAlert("Entry Successfully Inserted/Edited", "Submit Member Alert");
                    setMemberFieldsEditable(false);
                    currentMCommand = -1;
                    clearDataM();
                }
                else if (returnValue == 1)
                {
                    displayAlert("Connection to database failed at server", "Submit Member Alert");
                }
                else if (returnValue == 4)
                {
                    displayAlert("Invalid Data", "Submit Member Alert");
                }
                
            } catch(Exception e)
            {
                displayAlert("Connection to server failed", "Submit Member Alert");
            }
            
        } else
        {
            displayAlert("Invalid Input", "Submit Member Alert");
        }
    }//GEN-LAST:event_submitInfoMActionPerformed

    private void editButtonEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonEActionPerformed
        setEmployeeFieldsEditable(true);
        
        int row = tableE.getSelectedRow();
        employeeNumberInputE.setText((String) tableE.getValueAt(row, 0));
        employeeNumberInputE.setEditable(false);
        passwordInputE.setText((String) tableE.getValueAt(row, 1));
        nameInputE.setText((String) tableE.getValueAt(row, 2));
        emailAddressInputE.setText((String) tableE.getValueAt(row, 3));
        addressInputE.setText((String) tableE.getValueAt(row, 4));
        cityInputE.setText((String) tableE.getValueAt(row, 5));
        stateInputE.setText((String) tableE.getValueAt(row, 6));
        zipInputE.setText((String) tableE.getValueAt(row, 7));
        isActiveCheckBoxE.setSelected((Boolean) tableE.getValueAt(row, 8));
        isManagerCheckBoxE.setSelected((Boolean) tableE.getValueAt(row, 9));
        
        currentECommand = 1;
    }//GEN-LAST:event_editButtonEActionPerformed

    private void submitInfoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitInfoEActionPerformed
        Employee employee = new Employee();
        employee.employeeNumber = employeeNumberInputE.getText();
        employee.password = passwordInputE.getText();
        employee.name = nameInputE.getText();
        employee.emailAddress = emailAddressInputE.getText();
        employee.streetAddress = addressInputE.getText();
        employee.city = cityInputE.getText();
        employee.state = stateInputE.getText();
        employee.zipCode = zipInputE.getText();
        employee.isActive = isActiveCheckBoxE.isSelected();
        employee.isManager = isManagerCheckBoxE.isSelected();
        
        if (employee.verifyData())
        {
            int returnValue = -1;
            
            try
            {
                if (currentECommand == 0)
                {
                    returnValue = employeeTerminal.insertEmployee(employee);
                }
                else if (currentECommand == 1)
                {
                    returnValue = employeeTerminal.updateEmployee(employee);
                }
                
                if (returnValue == 0)
                {
                    displayAlert("Entry Successfully Inserted/Edited", "Submit Employee Alert");
                    setEmployeeFieldsEditable(false);
                    currentECommand = -1;
                    clearDataE();
                }
                else if (returnValue == 1)
                {
                    displayAlert("Connection to database failed at server", "Submit Employee Alert");
                }
                else if (returnValue == 4)
                {
                    displayAlert("Invalid Data", "Submit Employee Alert");
                }
                
            } catch(Exception e)
            {
                displayAlert("Connection to server failed", "Submit Employee Alert");
            }
            
        } else
        {
            displayAlert("Invalid Input", "Submit Employee Alert");
        }
    }//GEN-LAST:event_submitInfoEActionPerformed

    private void requestReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestReportButtonActionPerformed
        LocalDateTime endDateTime = LocalDateTime.ofInstant(datePickerR.getDate().toInstant(), ZoneId.systemDefault());
        
        String number = numberInputR.getText();
        
        int returnValue;
        
        if (jList1.getSelectedValue() == "Member Report")
        {
            try {
                returnValue = employeeTerminal.requestMemberReport(number, endDateTime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (jList1.getSelectedValue() == "Provider Report")
        {
            try {
                returnValue = employeeTerminal.requestProviderReport(number, endDateTime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (jList1.getSelectedValue() == "Summary Report")
        {
            try {
                returnValue = employeeTerminal.requestSummaryReport(endDateTime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_requestReportButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeTerminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
         
            public void run() {
                new EmployeeTerminal().setVisible(true);
                           
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressInputE;
    private javax.swing.JTextField addressInputM;
    private javax.swing.JTextField addressInputP;
    private javax.swing.JLabel chocAnLabel;
    private javax.swing.JTextField cityInputE;
    private javax.swing.JTextField cityInputM;
    private javax.swing.JTextField cityInputP;
    private javax.swing.JButton createButtonE;
    private javax.swing.JButton createButtonM;
    private javax.swing.JButton createButtonP;
    private javax.swing.JButton createButtonSD;
    private org.jdesktop.swingx.JXDatePicker datePickerR;
    private javax.swing.JScrollPane dateScrollPane;
    private javax.swing.JButton editButtonE;
    private javax.swing.JButton editButtonM;
    private javax.swing.JButton editButtonP;
    private javax.swing.JButton editButtonSD;
    private javax.swing.JTextField emailAddressInputE;
    private javax.swing.JTextField emailAddressInputM;
    private javax.swing.JTextField emailAddressInputP;
    private javax.swing.JLabel employeeInfoLabel;
    private javax.swing.JTextField employeeNumberInputE;
    private javax.swing.JLabel employeesLabel;
    private javax.swing.JPanel employeesPanel;
    private javax.swing.JTextField feeInputSD;
    private javax.swing.JPanel formPanelE;
    private javax.swing.JPanel formPanelM;
    private javax.swing.JPanel formPanelP;
    private javax.swing.JPanel formPanelSD;
    private javax.swing.JLabel instructionsLabel;
    private javax.swing.JCheckBox isActiveCheckBoxE;
    private javax.swing.JCheckBox isActiveCheckBoxM;
    private javax.swing.JCheckBox isActiveCheckBoxP;
    private javax.swing.JCheckBox isActiveCheckboxSD;
    private javax.swing.JCheckBox isManagerCheckBoxE;
    private javax.swing.JCheckBox isValidCheckBoxM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTabbedPane mainPanel;
    private javax.swing.JLabel memberInfoLabel;
    private javax.swing.JTextField memberNumberInputM;
    private javax.swing.JLabel membersLabel;
    private javax.swing.JPanel membersPanel;
    private javax.swing.JTextField nameInput;
    private javax.swing.JTextField nameInputE;
    private javax.swing.JTextField nameInputM;
    private javax.swing.JTextField nameInputP;
    private javax.swing.JTextField nameInputSD;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField numberInputR;
    private javax.swing.JTextField passwordInput;
    private javax.swing.JTextField passwordInputE;
    private javax.swing.JTextField passwordInputP;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel providerInfoLabel;
    private javax.swing.JTextField providerNumberInputP;
    private javax.swing.JLabel providersLabel;
    private javax.swing.JPanel providersPanel;
    private javax.swing.JButton refreshButtonE;
    private javax.swing.JButton refreshButtonM;
    private javax.swing.JButton refreshButtonP;
    private javax.swing.JButton refreshButtonSD;
    private javax.swing.JButton requestReportButton;
    private javax.swing.JLabel requestReportInstructions;
    private javax.swing.JPanel requestReportPanel;
    private javax.swing.JScrollPane scrollPaneP;
    private javax.swing.JScrollPane scrollPanelE;
    private javax.swing.JScrollPane scrollPanelM;
    private javax.swing.JScrollPane scrollPanelSD;
    private javax.swing.JTextField serviceCodeInputSD;
    private javax.swing.JPanel serviceDirectoryPanel;
    private javax.swing.JLabel serviceInfoLabel;
    private javax.swing.JLabel servicesLabel;
    private javax.swing.JTextField stateInputE;
    private javax.swing.JTextField stateInputM;
    private javax.swing.JTextField stateInputP;
    private javax.swing.JButton submitButtonSD;
    private javax.swing.JButton submitInfoE;
    private javax.swing.JButton submitInfoM;
    private javax.swing.JButton submitInfoP;
    private javax.swing.JTable tableE;
    private javax.swing.JTable tableM;
    private javax.swing.JTable tableP;
    private javax.swing.JTable tableSD;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField validityReasonInputM;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JTextField zipInputE;
    private javax.swing.JTextField zipInputM;
    private javax.swing.JTextField zipInputP;
    // End of variables declaration//GEN-END:variables

   
}
