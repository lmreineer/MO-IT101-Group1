package data;

public class EmployeeInfo {
  private int totalEmployees = 34;

  // Arrays to store employee data
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

  // Create a class constructor to provide EmployeeInfo object with values
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

  public int getTotalEmployees() {
    return totalEmployees;
  }

  public String[] getLastName() {
    return lastName;
  }

  public void setLastName(String[] lastName) {
    this.lastName = lastName;
  }

  public String[] getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = new String[] {firstName};
  }

  public String[] getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = new String[] {birthdate};
  }

  public String[] getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = new String[] {address};
  }

  public String[] getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = new String[] {phoneNumber};
  }

  public String[] getSssNumber() {
    return sssNumber;
  }

  public void setSssNumber(String sssNumber) {
    this.sssNumber = new String[] {sssNumber};
  }

  public String[] getPhilhealthNumber() {
    return philhealthNumber;
  }

  public void setPhilhealthNumber(String philhealthNumber) {
    this.philhealthNumber = new String[] {philhealthNumber};
  }

  public String[] getTinNumber() {
    return tinNumber;
  }

  public void setTinNumber(String[] tinNumber) {
    this.tinNumber = tinNumber;
  }

  public String[] getPagIbigNumber() {
    return pagIbigNumber;
  }

  public void setPagIbigNumber(String pagIbigNumber) {
    this.pagIbigNumber = new String[] {pagIbigNumber};
  }

  public String[] getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = new String[] {status};
  }

  public String[] getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = new String[] {position};
  }

  public String[] getImmediateSupervisor() {
    return immediateSupervisor;
  }

  public void setImmediateSupervisor(String immediateSupervisor) {
    this.immediateSupervisor = new String[] {immediateSupervisor};
  }

  public String[] getBasicSalary() {
    return basicSalary;
  }

  public void setBasicSalary(String basicSalary) {
    this.basicSalary = new String[] {basicSalary};
  }

  public String[] getRiceSubsidy() {
    return riceSubsidy;
  }

  public void setRiceSubsidy(String riceSubsidy) {
    this.riceSubsidy = new String[] {riceSubsidy};
  }

  public String[] getPhoneAllowance() {
    return phoneAllowance;
  }

  public void setPhoneAllowance(String phoneAllowance) {
    this.phoneAllowance = new String[] {phoneAllowance};
  }

  public String[] getClothingAllowance() {
    return clothingAllowance;
  }

  public void setClothingAllowance(String clothingAllowance) {
    this.clothingAllowance = new String[] {clothingAllowance};
  }

  public String[] getGrossSemimonthlyRate() {
    return grossSemimonthlyRate;
  }

  public void setGrossSemimonthlyRate(String grossSemimonthlyRate) {
    this.grossSemimonthlyRate = new String[] {grossSemimonthlyRate};
  }

  public double getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
}
