/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that calculates wage based on hours worked.
 *
 * @author Lance
 */
public abstract class WageCalculation {

    private final TimeCalculation timeCalculation;

    // File paths
    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";
    private static final String ATTENDANCE_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_attendance.txt";

    private static final int EMPLOYEE_DATA_LENGTH = 18;

    /**
     * Constructor for WageCalculation.
     *
     */
    public WageCalculation() {
        // Initialize time calculations
        this.timeCalculation = new TimeCalculation();
    }

    /**
     * Calculates and displays wage of an employee.
     *
     * @param employeeNumber Inputted employee number for which wage is
     * calculated
     * @param dateRange Date range inputted
     * @throws IOException If an I/O error happen
     * @throws ParseException If date parsing error happen
     */
    public void showWage(int employeeNumber, DateRange dateRange) throws IOException, ParseException {
        // Read attendance data
        List<String> attendanceDataInList = readAttendanceData();

        // Calculate total hours worked 
        double hoursWorked = calculateTotalHoursWorked(attendanceDataInList, employeeNumber, dateRange);

        // Calculate the assumed hours worked based on the provided date range
        double assumedHoursWorked = calculateAssumedHoursWorked(dateRange);

        // If employee with the inputted employee number is found in the attendance data
        if (hoursWorked > 0) {
            System.out.println(hoursWorked);
            double hourlyRate = getHourlyRate(employeeNumber);
            // Calculate wage based on hours worked from the attendance data
            double wage = calculateWage(hourlyRate, hoursWorked);
            displayWage(employeeNumber, wage);

            // Else employee is not found in attendance data
        } else {
            double hourlyRate = getHourlyRate(employeeNumber);
            // Use assumed hours worked of 9.0 per day to calculate wage
            double wage = calculateWage(hourlyRate, assumedHoursWorked);
            displayWage(employeeNumber, wage);
        }
    }

    /**
     * Calculates the total hours worked by the employee who exists in the
     * attendance data within the inputted date range.
     *
     * @param attendanceDataInList A list containing attendance data
     * @param employeeNumber Inputted employee number for which hours are to be
     * calculated
     * @param dateRange Date range inputted to calculate total hours worked.
     * @return The total hours worked by the employee within the inputted date
     * range.
     * @throws ParseException If date parsing error happen. dateRa
     */
    private double calculateTotalHoursWorked(List<String> attendanceDataInList, int employeeNumber, DateRange dateRange) throws ParseException {
        return timeCalculation.calculateTotalHoursWorked(attendanceDataInList, employeeNumber, dateRange);
    }

    /**
     * Calculates assumed hours worked based on the date range inputted.
     *
     * Gets used when employee number is not found on the attendance data
     *
     * @param dateRange Date range inputted
     * @return Assumed hours worked
     */
    private double calculateAssumedHoursWorked(DateRange dateRange) {
        return timeCalculation.calculateAssumedHoursWorked(dateRange);
    }

    /**
     * Reads attendance data from the data file and returns it as a list of
     * strings.
     *
     * @return A list of attendance data
     * @throws IOException If an I/O error happen
     */
    private List<String> readAttendanceData() throws IOException {
        // Initialize a list
        List<String> attendanceDataInList = new ArrayList<>();

        // Read attendance data from the file
        try (BufferedReader attendanceReader = new BufferedReader(new FileReader(ATTENDANCE_DATA_PATH))) {
            String attendanceLine;
            while ((attendanceLine = attendanceReader.readLine()) != null) {
                // Add each line of attendance data to the list
                attendanceDataInList.add(attendanceLine);
            }
        }

        return attendanceDataInList;
    }

    /**
     * Gets the hourly rate of an employee from the employee data file.
     *
     * @param employeeNumber Inputted employee number
     * @return Hourly rate of the employee with the inputted employee number
     * @throws IOException If an I/O error happen
     */
    protected double getHourlyRate(int employeeNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_DATA_PATH))) {
            String line;
            // Iterate through each line of the employee information data file
            while ((line = reader.readLine()) != null) {
                // Split the attendance data using "|" as delimiter
                String[] employeeData = line.split("\\|");
                // If the data field has the expected length and matches the inputted employee number
                if (employeeData.length >= EMPLOYEE_DATA_LENGTH && Integer.parseInt(employeeData[0]) == employeeNumber) {
                    // Return the hourly rate of the employee
                    return Double.parseDouble(employeeData[EMPLOYEE_DATA_LENGTH]);
                }
            }
        }
        // Throw an exception if the employee with the inputted employee number is not found in the employee database
        throw new RuntimeException("Employee not found in the employee information database");
    }

    /**
     * Calculates wage based on hourly rate and hours worked.
     *
     * @param hourlyRate Hourly rate for the employee
     * @param hoursWorked Hours worked by the employee
     * @return Calculated wage
     */
    protected abstract double calculateWage(double hourlyRate, double hoursWorked);

    /**
     * Displays wage for the employee.
     *
     * @param employeeNumber Employee number
     * @param wage Calculated wage
     */
    protected abstract void displayWage(int employeeNumber, double wage);
}
