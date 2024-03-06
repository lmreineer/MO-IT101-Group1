/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.motorph;

import com.mycompany.motorph.data.EmployeeInfo;
import com.mycompany.motorph.data.initializer.EmployeeDataInitializer;
import com.mycompany.motorph.wage_calculation.GrossWageCalculation;
import com.mycompany.motorph.wage_calculation.NetWageCalculation;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lance
 */
public class MotorPH {

    private static final EmployeeDataInitializer dataInitializer = new EmployeeDataInitializer();
    private static List<EmployeeInfo> info;
    private static final GrossWageCalculation grossWage = new GrossWageCalculation();

    static void displayUpperBorder(int menuInput) {
        System.out.println("================================");
        switch (menuInput) {
            case 1 ->
                System.out.println("      Employee Information      ");
            case 2, 3 ->
                System.out.println("            Employee            ");
            case 4 ->
                System.out.println("            Earnings            ");
            case 5 ->
                System.out.println("           Deductions           ");
            case 6 ->
                System.out.println("          Take-Home Pay         ");
            case 0 ->
                System.out.println("           Logged out           ");
        }
        System.out.println("================================");
    }

    static void displayMainMenu() {
        System.out.println("================================");
        System.out.println("    Motor PH Payroll System     ");
        System.out.println("================================");
        System.out.println("|   1:  Search Employee        |");
        System.out.println("|   2:  Calculate Gross Wage   |");
        System.out.println("|   3:  Calculate Net Wage     |");
        System.out.println("|                              |");
        System.out.println("|   0:  Exit Menu              |");
        System.out.println("================================");
    }

    static void clearConsole() {
        for (int i = 0; i < 1000; i++) {
            // Print 999 newlines to clear the console
            System.out.println("\n");
        }
    }

    private static String removeBrackets(String[] attribute) {
        // Use String.join(", ", attribute) to remove brackets
        return String.join(", ", attribute);
    }

    static void showEmployeeInfo(int employeeNumInput) {
        info = dataInitializer.getEmployeeList();
        EmployeeInfo employee = info.get(employeeNumInput - 1);

        System.out.println("Last Name: " + removeBrackets(employee.getLastName()));
        System.out.println("First Name: " + removeBrackets(employee.getFirstName()));
        System.out.println("Birthdate: " + removeBrackets(employee.getBirthdate()));
        System.out.println("Address: " + removeBrackets(employee.getAddress()));
        System.out.println("Phone Number: " + removeBrackets(employee.getPhoneNumber()));
        System.out.println("SSS #: " + removeBrackets(employee.getSssNumber()));
        System.out.println("PhilHealth #: " + removeBrackets(employee.getPhilhealthNumber()));
        System.out.println("TIN #: " + removeBrackets(employee.getTinNumber()));
        System.out.println("Pag-IBIG #: " + removeBrackets(employee.getPagIbigNumber()));
        System.out.println("Status: " + removeBrackets(employee.getStatus()));
        System.out.println("Position: " + removeBrackets(employee.getPosition()));
        System.out.println(
                "Immediate Supervisor: " + removeBrackets(employee.getImmediateSupervisor()));
        System.out.println("Basic Salary: ₱" + removeBrackets(employee.getBasicSalary()));
        System.out.println("Rice Subsidy: ₱" + removeBrackets(employee.getRiceSubsidy()));
        System.out.println("Phone Allowance: ₱" + removeBrackets(employee.getPhoneAllowance()));
        System.out.println(
                "Clothing Allowance: ₱" + removeBrackets(employee.getClothingAllowance()));
        System.out.println(
                "Gross Semi-monthly Rate: ₱" + removeBrackets(employee.getGrossSemimonthlyRate()));
        System.out.println("Hourly Rate: ₱" + employee.getHourlyRate());
    }

    static void showBasicInfo(int employeeNumInput) {
        info = dataInitializer.getEmployeeList();
        EmployeeInfo employee = info.get(employeeNumInput - 1);

        System.out.println("Last Name: " + removeBrackets(employee.getLastName()));
        System.out.println("First Name: " + removeBrackets(employee.getFirstName()));
        System.out.println("Position: " + removeBrackets(employee.getPosition()));
    }

    static void showEmployeeGrossWage(int employeeNumInput) {
        info = dataInitializer.getEmployeeList();
        EmployeeInfo employee = info.get(employeeNumInput - 1);

        showBasicInfo(employeeNumInput);

        // Display "Gross Wage" heading
        displayUpperBorder(4);

        System.out.println("Weekly Rate: ₱" + grossWage.calculateWeeklyRate(employeeNumInput));
        System.out.println("Hourly Rate: ₱" + employee.getHourlyRate());
        System.out.println("Period Start Date: 11/21/2022");
        System.out.println("Period End Date: 11/25/2022");
        System.out.println("Hours Worked: " + grossWage.calculateTotalHoursWorked(employeeNumInput));
        System.out.println("Gross Wage: ₱" + grossWage.calculateGrossWage(employeeNumInput));
    }

    static void showEmployeeNetWage(int employeeNumInput) {
        showBasicInfo(employeeNumInput);

        // Display "Deductions" heading
        displayUpperBorder(5);

        NetWageCalculation netWage = new NetWageCalculation();

        // Format each to desired numeral format
        String sssDeduction
                = netWage.formatDeduction(netWage.calculateSssContribution(employeeNumInput));
        String philHealthDeduction
                = netWage.formatDeduction(netWage.calculatePhilHealthContribution(employeeNumInput));
        String pagIbigDeduction
                = netWage.formatDeduction(netWage.calculatePagIbigContribution(employeeNumInput));
        String withholdingTax
                = netWage.formatDeduction(netWage.calculateWithholdingTax(employeeNumInput));
        String totalDeductions
                = netWage.formatDeduction(netWage.calculateTotalDeductions(employeeNumInput));

        double lateArrivalDeduction = netWage.calculateLateArrivalDeduction(employeeNumInput);

        System.out.println("Social Security System: ₱" + sssDeduction);
        System.out.println("PhilHealth: ₱" + philHealthDeduction);
        System.out.println("Pag-IBIG: ₱" + pagIbigDeduction);
        System.out.println("Withholding Tax: ₱" + withholdingTax);
        System.out.println("Late Arrival Deduction: ₱" + lateArrivalDeduction);

        // Display "Net Wage" heading
        displayUpperBorder(6);

        System.out.println("Gross Wage: ₱" + grossWage.calculateGrossWage(employeeNumInput));
        System.out.println("Total Deductions: ₱" + totalDeductions);
        System.out.println("Net Wage: ₱" + netWage.calculateNetWage(employeeNumInput));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean showMainMenu = true;
        boolean retryInput = true;

        while (showMainMenu) {
            displayMainMenu();

            // Use try to check for input errors
            try {
                System.out.print("Choose your option: ");
                int menuInput = scanner.nextInt();

                if (menuInput >= 1 && menuInput <= 3) {
                    clearConsole();

                    // Display upper border based on menu input
                    displayUpperBorder(menuInput);

                    // Prompt the user for employee ID input
                    do {
                        System.out.print("Employee ID: ");
                        // Subtract by 1 to start array index at 1
                        int employeeNumInput = scanner.nextInt();

                        info = dataInitializer.getEmployeeList();

                        if (employeeNumInput >= 1 && employeeNumInput <= info.size()) {
                            switch (menuInput) {
                                case 1 -> // Show the employee's information based on inputted number
                                    showEmployeeInfo(employeeNumInput);
                                case 2 -> // Show the employee's gross wage based on inputted number
                                    showEmployeeGrossWage(employeeNumInput);
                                case 3 -> // Show the employee's net wage based on inputted number
                                    showEmployeeNetWage(employeeNumInput);
                                default -> {
                                }
                            }

                            // Stop the while loop if employee number is valid
                            retryInput = false;

                            // After the operation, ask if the user wants to return to the main menu
                            System.out.print("\nWould you like to go back to the main menu? (y) or (n)\n");
                        } else {
                            clearConsole();

                            System.out.println("\nInput is invalid. Please try again.\n");

                            displayUpperBorder(menuInput);
                        }

                        // While the input is invalid, retry input
                    } while (retryInput);

                    // While the input is valid
                    while (!retryInput) {
                        String returnToMainMenuInput = scanner.nextLine();

                        // If the user chose to return to the main menu
                        if (returnToMainMenuInput.equalsIgnoreCase("y")) {
                            clearConsole();

                            // Return to the main menu
                            showMainMenu = true;
                            // Stop this while loop
                            retryInput = true;

                            // Else if the user chose not to return to the main menu
                        } else if (returnToMainMenuInput.equalsIgnoreCase("n")) {
                            // End by terminating the console
                            System.exit(0);
                        }
                    }

                    // Else if the user chose to exit the main menu
                } else if (menuInput == 0) {
                    // Do not go back to the main menu
                    showMainMenu = false;

                    clearConsole();

                    displayUpperBorder(menuInput);

                    // Else if the user input is invalid (using integer)
                } else {
                    clearConsole();

                    System.out.println("\nInput is invalid. Please try again.\n");
                }

                // Catch the error if the user input is invalid (using data types other than integer)
            } catch (InputMismatchException e) {
                clearConsole();

                System.out.println("\nInput is invalid. Please try again.\n");

                scanner.next();
            }
        }
    }
}
