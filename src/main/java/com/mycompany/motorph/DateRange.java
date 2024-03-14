/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lance
 */
public class DateRange {

    private final Date startDate;
    private final Date endDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    private DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    static DateRange createDateRange(String startDate, String endDate) throws ParseException {
        DATE_FORMAT.setLenient(false);

        try {
            Date start = DATE_FORMAT.parse(startDate);
            Date end = DATE_FORMAT.parse(endDate);

            if (end.before(start)) {
                throw new IllegalArgumentException("Invalid date sequence. End date must be on or after the start date.");
            }
            return new DateRange(start, end);
        } catch (ParseException e) {
            throw new ParseException("Invalid date. Please enter valid dates or dates in in mm/dd/yyyy format.", e.getErrorOffset());
        }
    }

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
