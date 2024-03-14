/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * A class that executes the main function of the MotorPH payroll system.
 *
 * @author lance
 */
public class MotorPHApplication {

    private final MotorPHManager motorPhManager;

    /**
     * Constructor for MotorPHApplication.
     */
    public MotorPHApplication() {
        // Initialize instance of MotorPHManager
        this.motorPhManager = new MotorPHManager();
    }

    /**
     * Executes the main function of the MotorPH payroll system.
     *
     * Displays menus, takes user input then performs corresponding actions
     *
     * @param scanner The scanner object for user input
     * @throws ParseException If a parsing error happen
     * @throws IOException If an I/O error happen
     */
    public void run(Scanner scanner) throws ParseException, IOException {
        // Display menu
        motorPhManager.printMenu();

        // Get user input
        int choice = motorPhManager.getChoice(scanner);

        // Perform action based on user choice
        switch (choice) {
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
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}
