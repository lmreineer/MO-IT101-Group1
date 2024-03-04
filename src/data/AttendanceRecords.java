package data;

public class AttendanceRecords {
  private int employeeId;
  private String date;
  private String startTime;
  private String endTime;

  public AttendanceRecords(int employeeId, String date, String startTime, String endTime) {
    this.employeeId = employeeId;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getDate() {
    return date;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getEndTime() {
    return endTime;
  }
}
