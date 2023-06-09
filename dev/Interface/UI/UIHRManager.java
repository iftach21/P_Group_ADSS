package Interface.UI;

import Service.HRManagerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UIHRManager {
    private JPanel panel1;
    static HRManagerService HRManagerService;

    static {
        try {
            HRManagerService = new HRManagerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UIHRManager(){

        JFrame frame = new JFrame("App");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Creating the Employees menu
        JMenu employeesMenu = new JMenu("Employees");

        //all of the drops for it:
        //------------------------------------------------------------------------------------------------------------------------------------------------------------


        //adding new emp:
        JMenuItem addEmployeeItem = new JMenuItem("Add Employee");
        addEmployeeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog();
            }
        });
        employeesMenu.add(addEmployeeItem);


        //add new driver:
        JMenuItem addNewDriverEmployee = new JMenuItem("Add Driver");
        addNewDriverEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add new driver *************************************************
            }
        });
        employeesMenu.add(addNewDriverEmployee);


        //fire employee
        JMenuItem removeEmployeeItem = new JMenuItem("Remove Employee");
        removeEmployeeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add fire employee *************************************************
            }
        });
        employeesMenu.add(removeEmployeeItem);

        //change wage
        JMenuItem changeWageForEmployee = new JMenuItem("Change Wage");
        changeWageForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: change wage *************************************************
            }
        });
        employeesMenu.add(changeWageForEmployee);

        //change contract
        JMenuItem changeContractForEmployee = new JMenuItem("Change Contract");
        changeContractForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: change contract *************************************************
            }
        });
        employeesMenu.add(changeContractForEmployee);


        //update bank Number
        JMenuItem changeBankNumForEmployee = new JMenuItem("Change Bank number");
        changeBankNumForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: change bank number *************************************************
            }
        });
        employeesMenu.add(changeBankNumForEmployee);

        //change personal info
        JMenuItem changePersonalInfoForEmployee = new JMenuItem("Change personal Info");
        changePersonalInfoForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: change personal info *************************************************
            }
        });
        employeesMenu.add(changePersonalInfoForEmployee);


        //add Available for employee
        JMenuItem AddAvailableForEmployee = new JMenuItem("Add when can work");
        AddAvailableForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: add Available for employee *************************************************
            }
        });
        employeesMenu.add(AddAvailableForEmployee);

        //remove Available for employee
        JMenuItem removeAvailableForEmployee = new JMenuItem("Remove when can work");
        removeAvailableForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: remove Available for employee *************************************************
            }
        });
        employeesMenu.add(removeAvailableForEmployee);


        //add prof for employee
        JMenuItem addProfForEmployee = new JMenuItem("Add prof for Employee");
        addProfForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: radd prof for employee *************************************************
            }
        });
        employeesMenu.add(addProfForEmployee);

        //remove prof for employee
        JMenuItem removeProfForEmployee = new JMenuItem("Remove prof for Employee");
        removeProfForEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: remove prof for employee *************************************************
            }
        });
        employeesMenu.add(removeProfForEmployee);




        //-----------------------------------------------------------------------------------------------------------------------------------------------------------


        // Creating the Weekly Shift menu
        JMenu weeklyShiftMenu = new JMenu("Weekly Shift");

        JMenuItem createShiftItem = new JMenuItem("Create Shift");
        createShiftItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Create Shift action performed");
            }
        });

        weeklyShiftMenu.add(createShiftItem);

        JMenuItem editShiftItem = new JMenuItem("Edit Shift");
        editShiftItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Edit Shift action performed");
            }
        });
        weeklyShiftMenu.add(editShiftItem);

        // Adding the menus to the menu bar
        menuBar.add(employeesMenu);
        menuBar.add(weeklyShiftMenu);

        // Setting the menu bar on the frame
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private void showAddEmployeeDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField contractField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField wageField = new JTextField();
        JTextField phoneNumField = new JTextField();
        JTextField personalInfoField = new JTextField();
        JTextField bankNumField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Contract:"));
        panel.add(contractField);
        panel.add(new JLabel("Start Date:"));
        panel.add(startDateField);
        panel.add(new JLabel("Wage:"));
        panel.add(wageField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumField);
        panel.add(new JLabel("Personal Info:"));
        panel.add(personalInfoField);
        panel.add(new JLabel("Bank Number:"));
        panel.add(bankNumField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Get the values from the text fields
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String contract = contractField.getText();
            String startDate = startDateField.getText();
            int wage = Integer.parseInt(wageField.getText());
            int phoneNum = Integer.parseInt(phoneNumField.getText());
            String personalInfo = personalInfoField.getText();
            int bankNum = Integer.parseInt(bankNumField.getText());
        // Call the addEmployee function with the gathered input
        HRManagerService.addemployee(id,name,contract,startDate,wage,phoneNum,personalInfo,bankNum);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UIHRManager::new);
    }
}
