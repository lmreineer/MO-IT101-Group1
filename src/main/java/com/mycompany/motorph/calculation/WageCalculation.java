/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that calculates wages based on hours worked.
 * <p>
 * This class reads employee and attendance data from files, calculates total
 * hours worked, late arrival deduction, and wage based on both, and displays
 * the employee's calculated wage
 *
 * @author Lance1
 */
public abstract class WageCalculation {

    private final TimeCalculation timeCalculation;

    // Paths to data files
    private static final String EMPLOYEES_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_information.txt";
    private static final String ATTENDANCE_DATA_PATH = "C:\\Users\\Lance1\\Documents\\MO-IT101-Group1\\src\\main\\resources\\data\\employee_attendance.txt";

    // Expected total number of values per row from the data
    private static final int EXPECTED_ROW_LENGTH = 18;

    /**
     * Constructor for WageCalculation.
     * <p>
     * Initializes an instance of TimeCalculation.
     */
    public WageCalculation() {
        this.timeCalculation = new TimeCalculation();
    }

    /**
     * Calculates and displays wage for an employee.
     *
     * @param employeeNumber Employee number for which wage is calculated
     * @param dateRange Date range for which wage is calculated
     * @throws IOException If an I/O error occurs
     * @throws ParseException If a date parsing error occurs
     */
    public void showWage(int employeeNumber, DateRange dateRange) throws IOException, ParseException {
        // Read attendance data
        List<String> attendanceDataList = readAttendanceData();

        // Calculate total hours worked 
        double hoursWorked = calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        // Get the hourly rate for the employee
        double hourlyRate = getHourlyRate(employeeNumber);

        // Calculate the assumed hours worked based on the provided date range
        double assumedHoursWorked = calculateAssumedHoursWorked(dateRange);

        // Calculate late arrival deduction
        double lateArrivalDeduction = calculateLateArrivalDeduction(attendanceDataList, employeeNumber, dateRange);

        // If employee with the inputted employee number is found in the attendance data
        if (hoursWorked > 0) {
            // Calculate wage based on hours worked from the attendance data
            displayWage(employeeNumber, hourlyRate, hoursWorked, lateArrivalDeduction);

            // Else
        } else {
            // Use assumed hours worked of 9.0 per day to calculate wage
            displayWage(employeeNumber, hourlyRate, assumedHoursWorked, lateArrivalDeduction);
        }
    }

    /**
     * Abstract method to calculate late arrival deduction for an employee.
     *
     * @param attendanceDataList Attendance data
     * @param employeeNumber Employee number
     * @param dateRange Date range
     * @return Late arrival deduction amount
     * @throws ParseException If a date parsing error occurs
     */
    protected abstract double calculateLateArrivalDeduction(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException;

    /**
     * Abstract method to calculate wage based on hourly rate and hours worked.
     *
     * @param hourlyRate Hourly rate
     * @param hoursWorked Hours worked
     * @param lateArrivalDeduction Late arrival deduction for the employee
     * @return Calculated wage
     */
    protected abstract double calculateWage(double hourlyRate, double hoursWorked, double lateArrivalDeduction);

    /**
     * Abstract method to display wage for an employee.
     *
     * @param employeeNumber Employee number
     * @param hourlyRate Hourly rate
     * @param hoursWorked Hours worked
     * @param lateArrivalDeduction Late arrival deduction for the employee
     */
    protected abstract void displayWage(int employeeNumber, double hourlyRate, double hoursWorked, double lateArrivalDeduction);

    /**
     * Gets the hourly rate of an employee from the employee data file.
     *
     * @param employeeNumber Employee number
     * @return Hourly rate of the employee
     * @throws IOException If an I/O error occurs
     */
    private double getHourlyRate(int employeeNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_DATA_PATH))) {
            String line;
            // Iterate through each line of the employee information data file
            while ((line = reader.readLine()) != null) {
                // Split the attendance data using "|" as delimiter
                String[] employeeData = line.split("\\|");
                // If the data field has the expected length and matches the inputted employee number
                if (employeeData.length >= EXPECTED_ROW_LENGTH && Integer.parseInt(employeeData[0]) == employeeNumber) {
                    // Return the hourly rate of the employee
                    return Double.parseDouble(employeeData[EXPECTED_ROW_LENGTH]);
                }
            }
        }

        // Throw an exception if the employee with the inputted employee number is not found in the employee database
        throw new RuntimeException("Employee not found in the employee database");
    }

    /**
     * Calculates the total hours worked by an employee within a inputted date
     * range.
     *
     * @param attendanceDataList Attendance data
     * @param employeeNumber Employee number
     * @param dateRange Date range
     * @return Total hours worked
     * @throws ParseException If a date parsing error occurs
     */
    private double calculateTotalHoursWorked(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        return timeCalculation.calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);
    }

    /**
     * Calculates assumed hours worked based on a date range.
     *
     * @param dateRange Date range
     * @return Assumed hours worked
     */
    private double calculateAssumedHoursWorked(DateRange dateRange) {
        return timeCalculation.calculateAssumedHoursWorked(dateRange);
    }

    /**
     * Reads attendance data from the file and returns it as a list.
     *
     * @return List of attendance data
     * @throws IOException If an I/O error occurs
     */
    private List<String> readAttendanceData() throws IOException {
        // Initialize a list
        List<String> attendanceDataList = new ArrayList<>();

        // Read attendance data from the file
        try (BufferedReader attendanceReader = new BufferedReader(new FileReader(ATTENDANCE_DATA_PATH))) {
            String attendanceLine;
            while ((attendanceLine = attendanceReader.readLine()) != null) {
                // Add each line of attendance data to the list
                attendanceDataList.add(attendanceLine);
            }
        }

        return attendanceDataList;
    }
}
