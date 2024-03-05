package data.initializer;

import data.AttendanceRecords;
import data.EmployeeDataManager;
import data.EmployeeInfo;
import data.SssContributionRange;

import java.util.List;

public class EmployeeDataInitializer extends EmployeeDataManager {
  EmployeeInfoInitializer employeeInfo = new EmployeeInfoInitializer();
  AttendanceRecordsInitializer attendanceRecords = new AttendanceRecordsInitializer();
  SssContributionRangeInitializer sssContributionRange = new SssContributionRangeInitializer();

  public EmployeeDataInitializer() {
    List<EmployeeInfo> employees = (employeeInfo.employees);
    List<AttendanceRecords> attendance = (attendanceRecords.attendance);
    List<SssContributionRange> contribution = (sssContributionRange.contribution);

    // Populate
    getEmployeeList().addAll(employees);
    getAttendanceRecords().addAll(attendance);
    getSssContributionRange().addAll(contribution);
  }
}
