import java.text.DecimalFormat;

public class grossWageCalculation {
  static EmployeeInfo info = new EmployeeInfo();

  public String calculateWeeklyRate(int employeeNumInput) {

    double weeklyRate = info.hourlyRate[employeeNumInput] * 40;

    String formattedWeeklyRate = String.format("%,.2f", weeklyRate);

    return formattedWeeklyRate;
  }

  public String formatHourlyRate(int employeeNumInput) {
    double hourlyRate = info.hourlyRate[employeeNumInput];

    String formattedHourlyRate = String.format("%,.2f", hourlyRate);

    return formattedHourlyRate;
  }

  public int calculateHoursWorked() {
    int hoursWorked = 40;

    return hoursWorked;
  }

  public double calculateGrossWage(int employeeNumInput) {
    int hoursWorked = calculateHoursWorked();

    double grossWage = info.hourlyRate[employeeNumInput] * hoursWorked;

    return grossWage;
  }

  public String formatGrossWage(int employeeNumInput) {
    double grossWage = calculateGrossWage(employeeNumInput);

    // Round to two decimal places and apply thousands separator
    String formattedGrossWage = String.format("%,.2f", grossWage);

    return formattedGrossWage;
  }
}
