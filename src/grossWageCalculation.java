import java.text.DecimalFormat;

public class grossWageCalculation {

  public String calculateGrossWage(int employeeNumInput) {
    EmployeeInfo info = new EmployeeInfo();

    DecimalFormat decimal = new DecimalFormat("0.00");

    // 160 because: 8 x 5 is 40 and 40 x 4 = 160
    double grossWage = info.hourlyRate[employeeNumInput] * 160;
    String formattedGrossWage = decimal.format(grossWage);

    return formattedGrossWage;
  }
}
