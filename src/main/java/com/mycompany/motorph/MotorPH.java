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
 * This class runs the application in a loop for prompts and user input using
 * the console.
 *
 * @author Lance1
 */
public class MotorPH {

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

        // Create an instance of MotorPHApplication
        MotorPHApplication motorPhApplication = new MotorPHApplication();

        // Run the application in a loop
        while (true) {
            motorPhApplication.run(scanner);
        }
    }
}
