/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a date range.
 */
public class DateRange {

    private final Date startDate;
    private final Date endDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Constructs a DateRange object with the inputted start and end dates.
     *
     * @param startDate Inputted start date
     * @param endDate Inputted end date
     */
    private DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a DateRange object with the inputted start and end date strings
     * in mm/dd/yyyy format.
     *
     * @param startDate Inputted start date
     * @param endDate Inputted end date
     * @return DateRange object
     * @throws ParseException If parsing error happen
     */
    public static DateRange createDateRange(String startDate, String endDate) throws ParseException {
        // Strictly match pattern
        DATE_FORMAT.setLenient(false);

        try {
            // Parse the start and end dates
            Date start = DATE_FORMAT.parse(startDate);
            Date end = DATE_FORMAT.parse(endDate);

            // If the end date is before the start date
            if (end.before(start)) {
                // Throw an exception and display an error message
                throw new IllegalArgumentException("Invalid date sequence. End date must be on or after the start date.");
            }
            // Create a DateRange object with the parsed start and end dates
            return new DateRange(start, end);
        } catch (ParseException e) {
            // Catch parsing exception and prompt the user again
            throw new ParseException("Invalid date. Please enter valid dates or dates in mm/dd/yyyy format.", e.getErrorOffset());
        }
    }

    /**
     * Checks if the inputted date is within the date range.
     *
     * @param date The date to check
     * @return true if the date is within the date range, otherwise false
     */
    public boolean isWithinDateRange(Date date) {
        return !date.before(startDate) && !date.after(endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
