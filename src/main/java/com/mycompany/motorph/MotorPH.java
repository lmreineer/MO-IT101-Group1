/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.motorph;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Main class for the MotorPH payroll system.
 *
 * This class runs the app in a loop for the prompts and user input using
 * console.
 *
 * @author Lance
 */
public class MotorPH {

    public static void main(String[] args) throws ParseException, IOException {
        // Use Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create instance of MotorPHApplication
        MotorPHApplication motorPhApplication = new MotorPHApplication();

        // Loop to keep the prompts running
        while (true) {
            motorPhApplication.run(scanner);
        }
    }
}
