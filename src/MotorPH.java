import data.EmployeeInfo;
import wageCalculation.grossWageCalculation;
import wageCalculation.netWageCalculation;

import java.util.Scanner;

public class MotorPH {
  static EmployeeInfo info = new EmployeeInfo();

  static void displayUpperBorder(int menuInput) {
    System.out.println("================================");
    switch (menuInput) {
      case 1:
        System.out.println("      Employee Information      ");
        break;
      case 2:
      case 3:
        System.out.println("            Employee            ");
        break;
      case 4:
        System.out.println("            Net Wage            ");
        break;
      case 5:
        System.out.println("           Deductions           ");
        break;
      case 0:
        System.out.println("           Logged out           ");
        break;
    }
    System.out.println("================================");
  }

  static void displayMenu() {
    System.out.println("================================");
    System.out.println("    Motor PH Payroll System     ");
    System.out.println("================================");
    System.out.println("|   1:  Search Employee        |");
    System.out.println("|   2:  Calculate Gross Wage   |");
    System.out.println("|   2:  Calculate Net Wage     |");
    System.out.println("|                              |");
    System.out.println("|   0:  Exit Menu              |");
    System.out.println("================================");
  }

  static void clearConsole() {
    for (int i = 0; i < 1000; i++) {
      System.out.println("\n");
    }
  }

  static void showEmployeeInfo(int employeeNumInput) {
    System.out.println("Last Name: " + info.lastName[employeeNumInput]);
    System.out.println("First Name: " + info.firstName[employeeNumInput]);
    System.out.println("Birthdate: " + info.birthdate[employeeNumInput]);
    System.out.println("Address: " + info.address[employeeNumInput]);
    System.out.println("Phone Number: " + info.phoneNumber[employeeNumInput]);
    System.out.println("SSS #: " + info.sssNumber[employeeNumInput]);
    System.out.println("PhilHealth #: " + info.philhealthNumber[employeeNumInput]);
    System.out.println("TIN #: " + info.tinNumber[employeeNumInput]);
    System.out.println("Pag-IBIG #: " + info.pagIbigNumber[employeeNumInput]);
    System.out.println("Status: " + info.status[employeeNumInput]);
    System.out.println("Position: " + info.position[employeeNumInput]);
    System.out.println("Immediate Supervisor: " + info.immediateSupervisor[employeeNumInput]);
    System.out.println("Basic Salary: ₱" + info.basicSalary[employeeNumInput]);
    System.out.println("Rice Subsidy: ₱" + info.riceSubsidy[employeeNumInput]);
    System.out.println("Phone Allowance: ₱" + info.phoneAllowance[employeeNumInput]);
    System.out.println("Clothing Allowance: ₱" + info.clothingAllowance[employeeNumInput]);
    System.out.println("Gross Semi-monthly Rate: ₱" + info.grossSemimonthlyRate[employeeNumInput]);
    System.out.println("Hourly Rate: ₱" + info.hourlyRate[employeeNumInput]);
  }

  static void showEmployeeGrossWage(int employeeNumInput) {
    System.out.println("Last Name: " + info.lastName[employeeNumInput]);
    System.out.println("First Name: " + info.firstName[employeeNumInput]);
    System.out.println("Position: " + info.position[employeeNumInput]);

    // Display "Gross Wage" heading
    displayUpperBorder(4);

    grossWageCalculation grossWage = new grossWageCalculation();

    System.out.println("Weekly Rate: ₱" + grossWage.calculateWeeklyRate(employeeNumInput));
    System.out.println("Hourly Rate: ₱" + grossWage.formatHourlyRate(employeeNumInput));
    System.out.println("Hours Worked: " + grossWage.calculateHoursWorked());
    System.out.println("Gross Wage: ₱" + grossWage.formatGrossWage(employeeNumInput));
  }

  static void showEmployeeNetWage(int employeeNumInput) {
    System.out.println("Last Name: " + info.lastName[employeeNumInput]);
    System.out.println("First Name: " + info.firstName[employeeNumInput]);
    System.out.println("Position: " + info.position[employeeNumInput]);

    // Display "Net Wage" heading
    displayUpperBorder(5);

    netWageCalculation netWage = new netWageCalculation();

    String sssDeduction = netWage.calculateSssContribution(employeeNumInput);
    String philHealthDeduction = netWage.calculatePhilHealthContribution(employeeNumInput);
    String pagIbigDeduction = netWage.calculatePhilHealthContribution(employeeNumInput);
    String withholdingTax = netWage.calculateWithholdingTax(employeeNumInput);

    System.out.println("Social Security System: ₱" + sssDeduction);
    System.out.println("PhilHealth: ₱" + philHealthDeduction);
    System.out.println("Pag-IBIG: ₱" + pagIbigDeduction);
    System.out.println("Withholding Tax: ₱" + withholdingTax);
    System.out.println("Net Wage: ₱" + netWage.calculateNetWage(employeeNumInput));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    displayMenu();

    System.out.print("Choose your option: ");
    int menuInput = scanner.nextInt();

    if (menuInput >= 1 && menuInput <= 3) {
      clearConsole();

      // Display upper border based on menu input
      displayUpperBorder(menuInput);

      System.out.print("Employee ID: ");
      // Start the array at 1
      int employeeNumInput = scanner.nextInt() - 1;

      EmployeeInfo info = new EmployeeInfo();

      if (employeeNumInput <= info.totalEmployees) {
        if (menuInput == 1) {
          // Show the employee's information based on inputted number
          showEmployeeInfo(employeeNumInput);
        } else if (menuInput == 2) {
          // Show the employee's gross wage based on inputted number
          showEmployeeGrossWage(employeeNumInput);
        } else if (menuInput == 3) {
          //           Show the employee's net wage based on inputted number
          showEmployeeNetWage(employeeNumInput);
        }
      } else {
        System.out.println("\n" + "Employee not found");
      }
    } else if (menuInput == 0) {
      clearConsole();

      displayUpperBorder(menuInput);
    } else {
      System.out.println("\n" + "Invalid input");
    }
  }
}
