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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that calculates wage based on hours worked.
 *
 * @author Lance
 */
public abstract class WageCalculation {

    private static final String EMPLOYEES_DATA_PATH = "/home/lance/projects/java/MotorPH/src/main/resources/data/employee_information";
    private static final String ATTENDANCE_DATA_PATH = "/home/lance/projects/java/MotorPH/src/main/resources/data/employee_attendance";
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final int ATTENDANCE_DATA_LENGTH = 6;
    private static final int EMPLOYEE_DATA_LENGTH = 18;

    /**
     * Calculates and displays wage of an employee.
     *
     * @param employeeNumber Inputted employee number for which wage is
     * calculated
     * @param dateRange Date range inputted
     * @param assumedHoursWorked Calculated assumed hours worked if employee's
     * number is not found in the attendance data
     * @throws IOException If an I/O error happen
     * @throws ParseException If date parsing error happen
     */
    public void showWage(int employeeNumber, DateRange dateRange, double assumedHoursWorked) throws IOException, ParseException {
        List<String> attendanceDataList = readAttendanceData();

        double hoursWorked = calculateTotalHoursWorked(attendanceDataList, employeeNumber, dateRange);

        if (hoursWorked > 0) {
            double hourlyRate = getHourlyRate(employeeNumber);
            double wage = calculateWage(hourlyRate, hoursWorked);
            displayWage(employeeNumber, wage);

            // Else, employee number is not found in the attendance data
        } else {
            double hourlyRate = getHourlyRate(employeeNumber);
            // Use the calculated assumed hours worked
            double wage = calculateWage(hourlyRate, assumedHoursWorked);
            displayWage(employeeNumber, wage);
        }
    }

    private List<String> readAttendanceData() throws IOException {
        List<String> attendanceDataList = new ArrayList<>();

        try (BufferedReader attendanceReader = new BufferedReader(new FileReader(ATTENDANCE_DATA_PATH))) {
            String attendanceLine;
            while ((attendanceLine = attendanceReader.readLine()) != null) {
                attendanceDataList.add(attendanceLine);
            }
        }

        return attendanceDataList;
    }

    private double calculateTotalHoursWorked(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalHours = 0.0;

        for (String attendanceLine : attendanceDataList) {
            String[] attendanceData = attendanceLine.split("  ");

            if (attendanceData.length == ATTENDANCE_DATA_LENGTH && Integer.parseInt(attendanceData[0]) == employeeNumber) {
                Date attendanceDate = new SimpleDateFormat("MM/dd/yyyy").parse(attendanceData[3]);

                if (dateRange.isWithinDateRange(attendanceDate)) {
                    totalHours += calculateHoursWorked(attendanceData[4], attendanceData[5]);
                }
            }
        }

        return totalHours;
    }

    protected double getHourlyRate(int employeeNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_DATA_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split("  ");
                if (employeeData.length >= EMPLOYEE_DATA_LENGTH && Integer.parseInt(employeeData[0]) == employeeNumber) {
                    return Double.parseDouble(employeeData[EMPLOYEE_DATA_LENGTH]);
                }
            }
        }
        throw new RuntimeException("Employee not found in the employees database");
    }

    protected double calculateHoursWorked(String timeIn, String timeOut) throws ParseException {
        Date startTime = TIME_FORMAT.parse(timeIn);
        Date endTime = TIME_FORMAT.parse(timeOut);

        long timeDifferenceMillis = endTime.getTime() - startTime.getTime();

        // Convert milliseconds to hours
        return TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis);
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
