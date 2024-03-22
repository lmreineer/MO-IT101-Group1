/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a date range.
 *
 * @author Lance1
 */
public class DateRange {

    private final Date startDate;
    private final Date endDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd");

    /**
     * Constructor for DateRange.
     *
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     */
    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * Checks if the inputted date falls within the date range.
     *
     * @param date The date to check
     * @return true if the date is within the range, false otherwise
     */
    public boolean isWithinDateRange(Date date) {
        return !date.before(startDate) && !date.after(endDate);
    }

    /**
     * Creates a DateRange object with the inputted start and end date strings
     * in "mm/dd" format.
     *
     * @param startDateString The start date string
     * @param endDateString The end date string
     * @return A DateRange object representing the specified date range
     * @throws ParseException If parsing error occurs
     */
    public static DateRange createDateRange(String startDateString, String endDateString) throws ParseException {
        try {
            // Set leniency to false to strictly match the pattern
            DATE_FORMAT.setLenient(false);

            // Parse the start and end dates
            Date start = DATE_FORMAT.parse(startDateString);
            Date end = DATE_FORMAT.parse(endDateString);

            // If the end date is before the start date
            if (end.before(start)) {
                // Throw IllegalArgumentException with an error message
                throw new IllegalArgumentException("Invalid date sequence. End date must be on or after the start date.");
            }

            // Create a DateRange object with the parsed start and end dates
            return new DateRange(start, end);
        } catch (ParseException e) {
            // Throw ParseException with an error message
            throw new ParseException("Invalid date. Dates must be valid or in mm/dd format.", e.getErrorOffset());
        }
    }
}
