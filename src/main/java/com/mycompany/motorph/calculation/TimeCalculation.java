/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.calculation;

import com.mycompany.motorph.model.DateRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A class for calculating employee working hours.
 *
 * @author Lance1
 */
public class TimeCalculation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private static final int ATTENDANCE_DATA_EXPECTED_LENGTH = 6;

    /**
     * Calculates the total hours worked by the employee, late arrival deduction
     * if applicable, within the inputted date range.
     *
     * @param attendanceDataList A list containing attendance data
     * @param employeeNumber Employee number for which hours are to be
     * calculated
     * @param dateRange Date range within which to calculate total hours worked
     * @return A pair containing the total hours worked and late arrival
     * deduction amount
     * @throws ParseException If there is an error parsing dates
     */
    double calculateTotalHoursWorked(List<String> attendanceDataList, int employeeNumber, DateRange dateRange) throws ParseException {
        double totalHoursWorked = 0.0;

        // Iterate through each line of attendance data
        for (String attendanceLine : attendanceDataList) {
            // Split the line into attendance data using "|" as delimiter
            String[] attendanceData = attendanceLine.split("\\|");

            // If the line matches the expected format and employee number
            if (attendanceData.length == ATTENDANCE_DATA_EXPECTED_LENGTH && Integer.parseInt(attendanceData[0]) == employeeNumber) {
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
     * Calculates assumed hours worked based on the inputted date range.
     *
     * @param dateRange Date range inputted
     * @return Assumed hours worked
     */
    double calculateAssumedHoursWorked(DateRange dateRange) {
        // Calculate the number of days within the date range
        long numberOfDays = calculateNumberOfDays(dateRange);

        // Assume 9 hours per day
        return 9.0 * numberOfDays;
    }

    /**
     * Calculates the number of days within the inputted date range.
     *
     * @param dateRange The date range
     * @return The number of days
     */
    private long calculateNumberOfDays(DateRange dateRange) {
        long startMillis = dateRange.getStartDate().getTime();
        long endMillis = dateRange.getEndDate().getTime();

        // If start date is before or equal to end date
        if (startMillis > endMillis) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        // Calculate the difference in milliseconds
        long durationInMillis = endMillis - startMillis;

        // Convert milliseconds to days and add 1 to include the end date
        return TimeUnit.MILLISECONDS.toDays(durationInMillis) + 1;
    }

    /**
     * Calculates the number of hours worked between the specified time in and
     * time out.
     *
     * @param timeIn The time the employee clocked in
     * @param timeOut The time the employee clocked out
     * @return The number of hours worked
     */
    private double calculateHoursWorked(Date timeIn, Date timeOut) {
        // Calculate the difference in milliseconds
        long timeDifferenceMillis = timeOut.getTime() - timeIn.getTime();

        // Convert milliseconds to hours
        return TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis);
    }
}
