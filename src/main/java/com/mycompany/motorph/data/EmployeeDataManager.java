/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lance
 */
public abstract class EmployeeDataManager {

    private List<EmployeeInfo> employeeInfo;
    private List<AttendanceRecords> attendanceRecords;
    private List<SssContributionRange> sssContributionRange;

    public EmployeeDataManager() {
        employeeInfo = new ArrayList<>();
        attendanceRecords = new ArrayList<>();
        sssContributionRange = new ArrayList<>();
    }

    public List<EmployeeInfo> getEmployeeList() {
        return employeeInfo;
    }

    public List<AttendanceRecords> getAttendanceRecords() {
        return attendanceRecords;
    }

    public List<SssContributionRange> getSssContributionRange() {
        return sssContributionRange;
    }
}
