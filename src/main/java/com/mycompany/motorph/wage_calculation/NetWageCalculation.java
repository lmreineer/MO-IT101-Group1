/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.wage_calculation;

import com.mycompany.motorph.data.AttendanceRecords;
import com.mycompany.motorph.data.SssContributionRange;
import com.mycompany.motorph.data.initializer.EmployeeDataInitializer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lance
 */
public class NetWageCalculation {

    private static final GrossWageCalculation grossWage = new GrossWageCalculation();
    private static final EmployeeDataInitializer dataInitializer = new EmployeeDataInitializer();

    public double calculateSssContribution(int employeeNumInput) {
        double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

        double sssDeduction = 0;

        // Loop through the compensation ranges and apply deduction based on contribution
        for (SssContributionRange contribution : dataInitializer.getSssContributionRange()) {
            String[] asd = contribution.getRange().split("-");
            double leftValue = Double.parseDouble(asd[0]);
            double rightValue = Double.parseDouble(asd[1]);

            if (doubledGrossWage > leftValue && doubledGrossWage <= rightValue) {
                sssDeduction = contribution.getContribution();
                // Exit the loop if the matching range is found
                break;
            } else if (doubledGrossWage <= leftValue) {
                sssDeduction = 135.00;
            } else if (doubledGrossWage > rightValue) {
                sssDeduction = 1125.00;
            }
        }

        return sssDeduction;
    }

    public double calculatePhilHealthContribution(int employeeNumInput) {
        double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

        double monthlyPremium = 0;
        double philHealthDeduction = 0;

        // If grossWage is less than the minimum basic salary
        if (doubledGrossWage < 10000) {
            // Deduct a minimum contribution of 300
            monthlyPremium = 300;
            // Take 50% from the monthlyPremium as employee share
            philHealthDeduction = (50 * monthlyPremium) / 100;

            // Else if grossWage is greater than the maximum basic salary
        } else if (doubledGrossWage > 60000) {
            // Deduct a maximum contribution of 1800
            monthlyPremium = 1800;
            // Take 50% from the monthlyPremium as employee share
            philHealthDeduction = (50 * monthlyPremium) / 100;

            // Else if it falls between the range of 10000.01 - 59999.99
        } else {
            // Deduct 3% from the total grossWage
            monthlyPremium = (3 * doubledGrossWage) / 100;
            // Take 50% from the monthlyPremium as employee share
            philHealthDeduction = (50 * monthlyPremium) / 100;
        }

        return philHealthDeduction;
    }

    public double calculatePagIbigContribution(int employeeNumInput) {
        double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

        double pagIbigDeduction = 0;

        // If grossWage is at least 1000 to 1500
        if (doubledGrossWage >= 1000 && doubledGrossWage <= 1500) {
            // Take 3% from the grossWage
            pagIbigDeduction = (3 * doubledGrossWage) / 100;

            // Else if grossWage is greater than 1500
        } else if (doubledGrossWage > 1500) {
            // Take 4% from the grossWage
            double totalContribution = (4 * doubledGrossWage) / 100;
            // If totalContribution is greater than 100, don't increase, else, continue calculation
            pagIbigDeduction = totalContribution > 100 ? 100 : totalContribution;
        }

        return pagIbigDeduction;
    }

    public double calculateMonthlyContributions(int employeeNumInput) {
        double sssContribution = calculateSssContribution(employeeNumInput);
        double philHealthContribution = calculatePhilHealthContribution(employeeNumInput);
        double pagIbigContribution = calculatePagIbigContribution(employeeNumInput);

        // Calculate total deductions before calculating withholding tax
        double monthlyContributions = sssContribution + philHealthContribution + pagIbigContribution;

        return monthlyContributions;
    }

    public double calculateWithholdingTax(int employeeNumInput) {
        double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

        double excessValue = 0;
        double withholdingTax = 0;

        double monthlyContributions = calculateMonthlyContributions(employeeNumInput);

        double taxableIncome = doubledGrossWage - monthlyContributions;

        if (taxableIncome <= 20832) {
            withholdingTax = 0;
        } else if (taxableIncome > 20832 && taxableIncome < 33333) {
            // Subtract taxable income with the excess value
            excessValue = taxableIncome - 20833;
            // Take 20% of the calculated amount
            withholdingTax = (20 * excessValue) / 100;
        } else if (taxableIncome >= 33333 && taxableIncome < 66667) {
            // Subtract taxable income with the excess value
            excessValue = taxableIncome - 33333;
            // Take 25% of the calculated amount plus 2500
            withholdingTax = ((25 * excessValue) / 100) + 2500;
        } else if (taxableIncome >= 66667 && taxableIncome < 166667) {
            // Subtract taxable income with the excess value
            excessValue = taxableIncome - 66667;
            // Take 30% of the calculated amount plus 666667
            withholdingTax = ((30 * excessValue) / 100) + 10833;
        } else if (taxableIncome >= 166667 && taxableIncome < 666667) {
            // Subtract taxable income with the excess value
            excessValue = taxableIncome - 166667;
            // Take 32% of the calculated amount plus 40833.33
            withholdingTax = ((32 * excessValue) / 100) + 40833.33;
        } else if (taxableIncome >= 666667) {
            // Subtract taxable income with the excess value
            excessValue = taxableIncome - 666667;
            // Take 32% of the calculated amount plus 200833.33
            withholdingTax = ((32 * excessValue) / 100) + 200833.33;
        }

        return withholdingTax;
    }

    public double calculateLateArrivalDeduction(int employeeNumInput) {
        double lateArrivalDeduction = 0;

        List<AttendanceRecords> attendanceRecords = dataInitializer.getAttendanceRecords();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        boolean employeeFound = false;

        for (AttendanceRecords attendanceRecord : attendanceRecords) {
            if (attendanceRecord.getEmployeeId() == employeeNumInput) {
                employeeFound = true;

                try {
                    Date startTime = format.parse(attendanceRecord.getStartTime());

                    // Calculate the minutes of lateness
                    int minutesLate
                            = (int) ((startTime.getTime() - format.parse("8:10").getTime()) / (60 * 1000));

                    // Deduct for every minute of lateness, with a maximum of 100
                    lateArrivalDeduction = Math.min(minutesLate, 100);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        // If employee number not found, set lateArrivalDeduction to 0
        if (!employeeFound || lateArrivalDeduction < 0) {
            lateArrivalDeduction = 0;
        }

        return lateArrivalDeduction;
    }

    public double calculateTotalDeductions(int employeeNumInput) {
        double monthlyContributions = calculateMonthlyContributions(employeeNumInput);
        double withholdingTax = calculateWithholdingTax(employeeNumInput);
        double lateArrivalDeduction = calculateLateArrivalDeduction(employeeNumInput);

        double totalDeductions = monthlyContributions + withholdingTax + lateArrivalDeduction;

        return totalDeductions;
    }

    public String calculateNetWage(int employeeNumInput) {
        double doubledGrossWage = grossWage.calculateGrossWage(employeeNumInput);

        double totalDeductions = calculateTotalDeductions(employeeNumInput);

        double netWage = doubledGrossWage - totalDeductions;

        // Round to two decimal places and apply thousands separator
        String formattedNetWage = String.format("%,.2f", netWage);

        return formattedNetWage;
    }

    public String formatDeduction(double deduction) {
        return String.format("%,.2f", deduction);
    }
}