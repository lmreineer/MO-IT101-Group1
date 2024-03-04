package data;

public class EmployeeInfo {
  private String lastName[];
  private String firstName[];
  private String birthdate[];
  private String address[];
  private String phoneNumber[];
  private String sssNumber[];
  private String philhealthNumber[];
  private String tinNumber[];
  private String pagIbigNumber[];
  private String status[];
  private String position[];
  private String immediateSupervisor[];
  private String basicSalary[];
  private String riceSubsidy[];
  private String phoneAllowance[];
  private String clothingAllowance[];
  private String grossSemimonthlyRate[];
  private double hourlyRate;

  // Create constructor then provide it with values
  public EmployeeInfo(
      String lastName,
      String firstName,
      String birthdate,
      String address,
      String phoneNumber,
      String sssNumber,
      String philhealthNumber,
      String tinNumber,
      String pagIbigNumber,
      String status,
      String position,
      String immediateSupervisor,
      String basicSalary,
      String riceSubsidy,
      String phoneAllowance,
      String clothingAllowance,
      String grossSemimonthlyRate,
      double hourlyRate) {
    this.lastName = new String[] {lastName};
    this.firstName = new String[] {firstName};
    this.birthdate = new String[] {birthdate};
    this.address = new String[] {address};
    this.phoneNumber = new String[] {phoneNumber};
    this.sssNumber = new String[] {sssNumber};
    this.philhealthNumber = new String[] {philhealthNumber};
    this.tinNumber = new String[] {tinNumber};
    this.pagIbigNumber = new String[] {pagIbigNumber};
    this.status = new String[] {status};
    this.position = new String[] {position};
    this.immediateSupervisor = new String[] {immediateSupervisor};
    this.basicSalary = new String[] {basicSalary};
    this.riceSubsidy = new String[] {riceSubsidy};
    this.phoneAllowance = new String[] {phoneAllowance};
    this.clothingAllowance = new String[] {clothingAllowance};
    this.grossSemimonthlyRate = new String[] {grossSemimonthlyRate};
    this.hourlyRate = hourlyRate;
  }

  public String[] getLastName() {
    return lastName;
  }

  public String[] getFirstName() {
    return firstName;
  }

  public String[] getBirthdate() {
    return birthdate;
  }

  public String[] getAddress() {
    return address;
  }

  public String[] getPhoneNumber() {
    return phoneNumber;
  }

  public String[] getSssNumber() {
    return sssNumber;
  }

  public String[] getPhilhealthNumber() {
    return philhealthNumber;
  }

  public String[] getTinNumber() {
    return tinNumber;
  }

  public String[] getPagIbigNumber() {
    return pagIbigNumber;
  }

  public String[] getStatus() {
    return status;
  }

  public String[] getPosition() {
    return position;
  }

  public String[] getImmediateSupervisor() {
    return immediateSupervisor;
  }

  public String[] getBasicSalary() {
    return basicSalary;
  }

  public String[] getRiceSubsidy() {
    return riceSubsidy;
  }

  public String[] getPhoneAllowance() {
    return phoneAllowance;
  }

  public String[] getClothingAllowance() {
    return clothingAllowance;
  }

  public String[] getGrossSemimonthlyRate() {
    return grossSemimonthlyRate;
  }

  public double getHourlyRate() {
    return hourlyRate;
  }
}
