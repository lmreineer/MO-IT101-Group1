/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.wage_calculation;

import com.mycompany.motorph.data.AttendanceRecords;
import com.mycompany.motorph.data.EmployeeDataManager;
import com.mycompany.motorph.data.EmployeeInfo;
import com.mycompany.motorph.data.initializer.EmployeeDataInitializer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author lance
 */
public class GrossWageCalculation extends EmployeeDataManager {

    private static final EmployeeDataInitializer dataInitializer = new EmployeeDataInitializer();
    private static List<EmployeeInfo> info;

    public String calculateWeeklyRate(int employeeNumInput) {
        info = dataInitializer.getEmployeeList();
        EmployeeInfo employee = info.get(employeeNumInput - 1);

        double weeklyRate = employee.getHourlyRate() * 40;

        // Round to two decimal places and apply thousands separator
        String formattedWeeklyRate = String.format("%,.2f", weeklyRate);

        return formattedWeeklyRate;
    }

    public int calculateTotalHoursWorked(int employeeNumInput) {
        int totalHoursWorked = 0;

        List<AttendanceRecords> attendanceRecords = dataInitializer.getAttendanceRecords();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        boolean employeeFound = false;

        for (AttendanceRecords attendanceRecord : attendanceRecords) {
            if (attendanceRecord.getEmployeeId() == employeeNumInput) {
                employeeFound = true;

                try {
                    Date startDate = format.parse(attendanceRecord.getStartTime());
                    Date endDate = format.parse(attendanceRecord.getEndTime());

                    Calendar startCalendar = new GregorianCalendar();
                    startCalendar.setTime(startDate);

                    Calendar endCalendar = new GregorianCalendar();
                    endCalendar.setTime(endDate);

                    int diffMillis = (int) (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis());
                    totalHoursWorked += diffMillis / (60 * 60 * 1000);

                } catch (ParseException e) {
                    // Handle exception
                    e.printStackTrace();
                }
            }
        }

        // If employee number not found, set totalHoursWorked to 40
        if (!employeeFound) {
            totalHoursWorked = 40;
        }

        return totalHoursWorked;
    }

    public String calculateGrossWage(int employeeNumInput) {
        info = dataInitializer.getEmployeeList();
        EmployeeInfo employee = info.get(employeeNumInput - 1);

        int hoursWorked = calculateTotalHoursWorked(employeeNumInput);

        double grossWage = employee.getHourlyRate() * hoursWorked;

        return formatTotal(grossWage);
    }

    public String formatTotal(double total) {
        // Round to two decimal places and apply thousands separator
        return String.format("%,.2f", total);
    }
}
