/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.motorph.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lance1
 */
public class DateRangeTest {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

    @Test
    void isWithinDateRange_ReturnsTrueForDateInsideRange() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));
        assertTrue(dateRange.isWithinDateRange(dateFormat.parse("01/13")));
    }

    @Test
    void isWithinDateRange_ReturnsTrueForStartDate() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));
        assertTrue(dateRange.isWithinDateRange(dateFormat.parse("01/10")));
    }

    @Test
    void isWithinDateRange_ReturnsTrueForEndDate() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));
        assertTrue(dateRange.isWithinDateRange(dateFormat.parse("01/15")));
    }

    @Test
    void isWithinDateRange_ReturnsFalseForDateBeforeRange() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));
        assertFalse(dateRange.isWithinDateRange(dateFormat.parse("01/09")));
    }

    @Test
    void isWithinDateRange_ReturnsFalseForDateAfterRange() throws ParseException {
        DateRange dateRange = new DateRange(dateFormat.parse("01/10"), dateFormat.parse("01/15"));
        assertFalse(dateRange.isWithinDateRange(dateFormat.parse("01/16")));
    }

    @Test
    void createDateRange_ThrowsIllegalArgumentExceptionForInvalidDateSequence() {
        assertThrows(IllegalArgumentException.class, () -> DateRange.createDateRange("01/15", "01/10"));
    }
}
