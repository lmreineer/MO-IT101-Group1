/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.motorph;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class for the MotorPH payroll system.
 * <p>
 * This class interacts with the user, displays menus, takes user input, and
 * performs corresponding actions based on the input using the console in a
 * loop.
 *
 * @author Lance1
 */
public class MotorPH {

    private final MotorPHManager motorPhManager;

    /**
     * Constructor for MotorPH.
     * <p>
     * Initializes an instance of MotorPHManager.
     */
    public MotorPH() {
        this.motorPhManager = new MotorPHManager();
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     * @throws ParseException If parsing of input fails
     */
    public static void main(String[] args) throws ParseException, IOException {
        // Use Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Run the application in a loop
        while (true) {
            new MotorPH().run(scanner);
        }
    }

    /**
     * Executes the main function of the MotorPH payroll system.
     * <p>
     * Displays menus, takes user input, and performs corresponding actions.
     *
     * @param scanner The scanner object for user input
     * @throws ParseException If a parsing error occurs
     * @throws IOException If an I/O error occurs
     */
    private void run(Scanner scanner) throws ParseException, IOException {
        // Display the menu
        motorPhManager.printMenu();

        // Get user input
        int menuChoice = motorPhManager.getMenuChoice(scanner);

        // Perform action based on user input
        switch (menuChoice) {
            case 1:
                // Show employee information
                motorPhManager.showEmployeeInformation(scanner);
                break;
            case 2:
                // Show gross wage
                motorPhManager.showWage(scanner, true);
                break;
            case 3:
                // Show net wage 
                motorPhManager.showWage(scanner, false);
                break;
            case 0:
                // Exit the program
                System.out.println("Logged out.");
                System.exit(0);
            default:
                System.out.println("\nInvalid option. Please try again.");
                break;
        }
    }
}
