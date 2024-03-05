/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.data.initializer;

import com.mycompany.motorph.data.EmployeeInfo;
import com.mycompany.motorph.data.AttendanceRecords;
import com.mycompany.motorph.data.SssContributionRange;
import com.mycompany.motorph.data.EmployeeDataManager;
import java.util.List;

/**
 *
 * @author lance
 */
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
