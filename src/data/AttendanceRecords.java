package data;

public class AttendanceRecords {
  private String lastName;
  private String firstName;
  private int employeeId;
  private String date;
  private String startTime;
  private String endTime;

  public AttendanceRecords(
      int employeeId,
      String lastName,
      String firstName,
      String date,
      String startTime,
      String endTime) {
    this.employeeId = employeeId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getEndTime() {
    return endTime;
  }
}
