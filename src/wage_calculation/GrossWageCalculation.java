package wage_calculation;

import data.EmployeeDataInitializer;
import data.EmployeeInfo;

import java.util.List;

public class GrossWageCalculation {
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

  public int calculateHoursWorked() {
    int hoursWorked = 40;

    return hoursWorked;
  }

  public double calculateGrossWage(int employeeNumInput) {
    info = dataInitializer.getEmployeeList();
    EmployeeInfo employee = info.get(employeeNumInput - 1);

    int hoursWorked = calculateHoursWorked();

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
