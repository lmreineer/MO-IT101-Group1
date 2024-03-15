/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import com.mycompany.motorph.employee.EmployeeInformation;
import com.mycompany.motorph.calculation.HealthInsurancesDeduction;
import com.mycompany.motorph.model.Employee;
import com.mycompany.motorph.model.DateRange;
import com.mycompany.motorph.data.EmployeeDataReader;
import com.mycompany.motorph.calculation.WithholdingTaxCalculation;
import com.mycompany.motorph.calculation.SSSDeduction;
import com.mycompany.motorph.calculation.WageCalculation;
import com.mycompany.motorph.calculation.NetWageCalculation;
import com.mycompany.motorph.calculation.GrossWageCalculation;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that manages the MotorPH application/payroll system.
 *
 * Displays menus, handles user input, and manages functionalities.
 *
 *
 * @author Lance
 */
public class MotorPHManager {

    private final SSSDeduction sssDeduction;
    private final HealthInsurancesDeduction healthInsuranceDeduction;
    private final WithholdingTaxCalculation withholdingTaxCalculation;

    // Path to the employee data file
    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";

    /**
     * Constructor for MotorPHManager.
     *
     * Initializes dependencies.
     */
    public MotorPHManager() {
        this.sssDeduction = new SSSDeduction();
        this.healthInsuranceDeduction = new HealthInsurancesDeduction();
        this.withholdingTaxCalculation = new WithholdingTaxCalculation(sssDeduction, healthInsuranceDeduction);
    }

    /**
     * Displays the main menu.
     */
    public void printMenu() {
        System.out.println("\n================================");
        System.out.println("    Motor PH Payroll System     ");
        System.out.println("================================");
        System.out.println("|   1:  Search Employee        |");
        System.out.println("|   2:  Calculate Gross Wage   |");
        System.out.println("|   3:  Calculate Net Wage     |");
        System.out.println("|                              |");
        System.out.println("|   0:  Exit Menu              |");
        System.out.println("================================");
        System.out.print("Choose your option: ");
    }

    /**
     * Handles user input.
     *
     * @param scanner Scanner for user input
     * @return Validated user choice
     */
    public int getChoice(Scanner scanner) {
        int choice;

        // Check if the next input is an integer
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            // Discard any remaining input
            scanner.nextLine();

            // Else
        } else {
            // Display error message and prompt again
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Choose your option: ");
            // Discard any remaining input
            scanner.nextLine();

            // Call getChoice recursively to get a valid input
            return getChoice(scanner);
        }

        return choice;
    }

    /**
     * Shows information about an employee.
     *
     * @param scanner Scanner for user input
     */
    public void showEmployeeInformation(Scanner scanner) {
        // Prompt the user for the employee number
        System.out.print("Enter employee number: ");
        int employeeNumber = scanner.nextInt();
        // Discard any remaining input
        scanner.nextLine();

        try {
            // Show information of the employee with the inputted employee number
            new EmployeeInformation().showEmployeeInformation(employeeNumber);
        } catch (IOException | ParseException e) {
            // Catch exception if error occurs and display an error message
            System.err.println("Error: " + e.getMessage());
        }

        // Prompt the user to go back to the main menu
        promptToGoBackToMainMenu(scanner);
    }

    /**
     * Shows either gross or net wage calculation for an employee.
     *
     * @param scanner Scanner for user input
     * @param isGross Indicates whether to calculate gross or net wage
     * @throws ParseException If date parsing error occurs
     * @throws IOException If I/O error occurs
     * @throws InputMismatchException If input mismatch occurs
     */
    public void showWage(Scanner scanner, boolean isGross) throws ParseException, IOException, InputMismatchException {
        // Prompt the user for the employee number
        int employeeNumber = getValidEmployeeNumber(scanner);

        // Prompt the user to enter start and end dates
        System.out.print("Enter start date (mm/dd): ");
        String startDateString = scanner.nextLine();
        System.out.print("Enter end date (mm/dd): ");
        String endDateString = scanner.nextLine();

        // Create a DateRange object based on the inputted start and end date
        DateRange dateRange = DateRange.createDateRange(startDateString, endDateString);

        // Retrieve employee information from the data source
        Employee employeeInfo = getEmployeeInfo(employeeNumber, EMPLOYEES_DATA_PATH);

        // Show type of wage based on the isGross value given (true or false)
        WageCalculation wageCalculation = (isGross)
                ? new GrossWageCalculation(employeeNumber,
                        employeeInfo.getLastName(),
                        employeeInfo.getFirstName(),
                        employeeInfo.getBirthdateAsString())
                : new NetWageCalculation(
                        employeeNumber,
                        employeeInfo.getLastName(),
                        employeeInfo.getFirstName(),
                        employeeInfo.getBirthdateAsString(),
                        sssDeduction,
                        healthInsuranceDeduction,
                        withholdingTaxCalculation);

        try {
            // Show the type of wage and/or other information
            wageCalculation.showWage(employeeNumber, dateRange);
        } catch (RuntimeException e) {
            // Catch runtime exception if an error occurs during wage calculation and display an error message
            System.err.println("Error: " + e.getMessage());
            promptToGoBackToMainMenu(scanner);
        }

        // Prompt the user to go back to the main menu
        promptToGoBackToMainMenu(scanner);
    }

    /**
     * Prompts the user for the employee number and validates it.
     *
     * @param scanner Scanner for user input
     * @return Valid employee number
     */
    private int getValidEmployeeNumber(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter employee number: ");
                int employeeNumber = scanner.nextInt();
                // Discard any remaining input
                scanner.nextLine();

                // Return valid employee number
                return employeeNumber;
            } catch (InputMismatchException e) {
                // Catch the exception if employee number input is invalid
                System.out.println("Invalid input. Please enter a valid integer for the employee number.");
                // Discard any remaining input
                scanner.nextLine();
            }
        }
    }

    /**
     * Prompts/asks the user to go back to the main menu or exit.
     *
     * @param scanner Scanner for user input
     */
    private void promptToGoBackToMainMenu(Scanner scanner) {
        System.out.print("Do you want to go back to the main menu? (y or n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        // If the user wants to exit
        if (!response.equals("y")) {
            // Display logged out message
            System.out.println("Logged out.");
            // Exit the program
            System.exit(0);
        }
    }

    /**
     * Gets employee information from the employee data.
     *
     * @param employeeNumber For getting the information of the employee with
     * the inputted employee number
     * @param filePath File path to the employee data file
     * @return Employee information
     * @throws IOException If I/O error occurs
     * @throws ParseException If date parsing error occurs
     */
    private Employee getEmployeeInfo(int employeeNumber, String filePath) throws IOException, ParseException {
        // Create instance of EmployeeDataReader
        EmployeeDataReader employeeDataReader = new EmployeeDataReader();

        // Get employee information using inputted employee number and specified file path
        return employeeDataReader.getEmployeeInfo(employeeNumber, filePath);
    }
}
