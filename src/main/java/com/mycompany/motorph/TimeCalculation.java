/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Lance1
 */
public class TimeCalculation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private static final int ATTENDANCE_DATA_LENGTH = 6;

    /**
     * Calculates the number of days within the date range inputted.
     *
     * @param dateRange Date range inputted
     * @return Number of days
     */
    private long calculateNumberOfDays(DateRange dateRange) {
        long startMillis = dateRange.getStartDate().getTime();
        long endMillis = dateRange.getEndDate().getTime();

        // Ensure start date is before or equal to end date
        if (startMillis > endMillis) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        // Calculate the difference/duration between the start and end dates in milliseconds
        long durationInMillis = endMillis - startMillis;

        // Convert the difference/duration in milliseconds to days and add 1 to include the end date
        return TimeUnit.MILLISECONDS.toDays(durationInMillis) + 1;
    }

    /**
     * Calculates the number of hours the employee has worked from time in to
     * time out.
     *
     * @param timeIn The hour the employee clocked in
     * @param timeOut The hour the employee clocked out
     * @return The number of hours worked from time in to time out.
     */
    private double calculateHoursWorked(Date timeIn, Date timeOut) {
        // Calculate the difference/duration in time in milliseconds
        long timeDifferenceMillis = timeOut.getTime() - timeIn.getTime();

        // Convert the time difference/duration to hours
        return TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis);
    }

    protected double calculateTotalHoursWorked(List<String> attendanceDataInList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalHoursWorked = 0.0;

        // Iterate through each line of attendance data
        for (String attendanceLine : attendanceDataInList) {
            // Split the line in attendance data using "|" as delimiter
            String[] attendanceData = attendanceLine.split("\\|");

            // If the line has the expected length of total values and matches the employee number
            if (attendanceData.length == ATTENDANCE_DATA_LENGTH && Integer.parseInt(attendanceData[0]) == employeeNumber) {
                // Parse attendance date, time in, and time out from the data
                Date attendanceDate = DATE_FORMAT.parse(attendanceData[3]);
                Date attendanceTimeIn = TIME_FORMAT.parse(attendanceData[4]);
                Date attendanceTimeOut = TIME_FORMAT.parse(attendanceData[5]);

                // If the attendance date is within the inputted date range
                if (dateRange.isWithinDateRange(attendanceDate)) {
                    // Calculate hours worked
                    totalHoursWorked += calculateHoursWorked(attendanceTimeIn, attendanceTimeOut);
                }
            }
        }

        return totalHoursWorked;
    }

    /**
     * Calculates assumed hours worked based on the date range inputted.
     *
     * Gets used when employee number is not found on the attendance data
     *
     * @param dateRange Date range inputted
     * @return Assumed hours worked
     */
    protected double calculateAssumedHoursWorked(DateRange dateRange) {
        // Calculate number of days within the date range inputted
        long numberOfDays = calculateNumberOfDays(dateRange);

        // Assume 9 hours per day
        return 9.0 * numberOfDays;
    }

}
