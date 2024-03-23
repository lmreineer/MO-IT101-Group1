/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that calculates SSS deductions based on gross wage.
 *
 * @author Lance1
 */
public class SSSDeduction {

    // Path to the SSS deductions data file
    private static final String SSS_DEDUCTIONS_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\sss_deduction.txt";

    private static final double MIN_COMPENSATION_RANGE = 3250.00;
    private static final double MAX_COMPENSATION_RANGE = 24750.00;
    private static final double MIN_DEDUCTION = 135.00;
    private static final double MAX_DEDUCTION = 1125.00;
    private static final double SSS_DEDUCTION_DATA_LENGTH = 3;

    private final List<double[]> sssCompensationRanges = new ArrayList<>();
    private final List<Double> sssDeductions = new ArrayList<>();

    /**
     * Constructor for SSSDeduction.
     * <p>
     * Reads SSS deductions data from the data file.
     */
    public SSSDeduction() {
        readSSSDeductions();
    }

    /**
     * Calculates SSS deduction.
     *
     * @param grossWage Gross wage
     * @return SSS deduction amount
     */
    double calculateSssDeduction(double grossWage) {
        // If gross wage is below the lower limit
        if (grossWage < MIN_COMPENSATION_RANGE) {
            return MIN_DEDUCTION;
        } // Else if gross wage is above the upper limit
        else if (grossWage > MAX_COMPENSATION_RANGE) {
            return MAX_DEDUCTION;
        } else {
            // Loop through the SSS compensation ranges and deductions
            for (int i = 0; i < sssCompensationRanges.size(); i++) {
                double[] range = sssCompensationRanges.get(i);
                double sssDeduction = sssDeductions.get(i);

                // If gross wage falls within the specific compensation range based on the gross wage
                if (grossWage >= range[0] && grossWage <= range[1]) {
                    // Return the deduction aligned within that specific compensation range
                    return sssDeduction;
                }
            }
        }

        // Default value if no matching range is found
        return 0.0;
    }

    /**
     * Reads SSS deductions data from the data file and populates.
     */
    private void readSSSDeductions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SSS_DEDUCTIONS_PATH))) {
            String line;

            // Read each line from the data file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // If the line has the normal length
                if (parts.length == SSS_DEDUCTION_DATA_LENGTH) {
                    double lowerRange = Double.parseDouble(parts[0]);
                    double upperRange = Double.parseDouble(parts[1]);
                    double sssDeduction = Double.parseDouble(parts[2]);

                    // Add the parsed values to their own lists
                    sssCompensationRanges.add(new double[]{lowerRange, upperRange});
                    sssDeductions.add(sssDeduction);

                    // Else
                } else {
                    // Print error message for invalid length
                    System.err.println("Formatting error in sss_deduction file.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Catch exceptions and print error message
            System.err.println("Error reading SSS Deductions file: " + e.getMessage());
        }
    }
}
