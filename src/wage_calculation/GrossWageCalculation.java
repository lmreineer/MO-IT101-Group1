package wage_calculation;

import data.AttendanceRecords;
import data.initializer.EmployeeDataInitializer;
import data.EmployeeDataManager;
import data.EmployeeInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GrossWageCalculation extends EmployeeDataManager {
  static EmployeeDataInitializer dataInitializer = new EmployeeDataInitializer();
  private static List<EmployeeInfo> info;

  public String calculateWeeklyRate(int employeeNumInput) {
    info = dataInitializer.getEmployeeList();
    EmployeeInfo employee = info.get(employeeNumInput - 1);

    double weeklyRate = employee.getHourlyRate() * 40;

    // Round to two decimal places and apply thousands separator
    String formattedWeeklyRate = String.format("%,.2f", weeklyRate);

    return formattedWeeklyRate;
  }

  public String formatHourlyRate(int employeeNumInput) {
    info = dataInitializer.getEmployeeList();
    EmployeeInfo employee = info.get(employeeNumInput - 1);

    double hourlyRate = employee.getHourlyRate();

    // Round to two decimal places and apply thousands separator
    String formattedHourlyRate = String.format("%,.2f", hourlyRate);

    return formattedHourlyRate;
  }

  public int calculateTotalHoursWorked(int employeeNumInput) {
    int totalHoursWorked = 0;

    List<AttendanceRecords> attendanceRecords = dataInitializer.getAttendanceRecords();

    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    for (AttendanceRecords attendanceRecord : attendanceRecords) {
      if (attendanceRecord.getEmployeeId() == employeeNumInput) {
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
          // Handle the ParseException appropriately
          // For example, log a message or throw a runtime exception
          e.printStackTrace();
        }
      }
    }

    return totalHoursWorked;
  }

  public double calculateGrossWage(int employeeNumInput) {
    info = dataInitializer.getEmployeeList();
    EmployeeInfo employee = info.get(employeeNumInput - 1);

    double hoursWorked = calculateTotalHoursWorked(employeeNumInput);

    double grossWage = employee.getHourlyRate() * hoursWorked;

    return grossWage;
  }

  public String formatGrossWage(int employeeNumInput) {
    double grossWage = calculateGrossWage(employeeNumInput);

    // Round to two decimal places and apply thousands separator
    String formattedGrossWage = String.format("%,.2f", grossWage);

    return formattedGrossWage;
  }
}
