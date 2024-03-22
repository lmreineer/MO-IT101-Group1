/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.model;

import com.mycompany.motorph.util.CurrencyUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an employee.
 * <p>
 * Contains employee information and methods to access and display it. This
 * class also provides methods for formatting and displaying employee
 * information.
 *
 * @author Lance1
 */
public class Employee {

    private int employeeNumber;
    private String lastName;
    private String firstName;
    private Date birthdate;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philHealthNumber;
    private String tinNumber;
    private String pagIbigNumber;
    private String status;
    private String position;
    private String immediateSupervisor;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemimonthlyRate;
    private double hourlyRate;

    // Getters and setters
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    public void setPagIbigNumber(String pagIbigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public void setImmediateSupervisor(String immediateSupervisor) {
        this.immediateSupervisor = immediateSupervisor;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public double getGrossSemimonthlyRate() {
        return grossSemimonthlyRate;
    }

    public void setGrossSemimonthlyRate(double grossSemimonthlyRate) {
        this.grossSemimonthlyRate = grossSemimonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Gets the birthdate of the employee formatted as a string.
     *
     * @return The birthdate as a string in "mm/dd/yyyy" format
     */
    public String getBirthdateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(birthdate);
    }

    /**
     * Displays employee information.
     */
    public void displayEmployeeInformation() {
        System.out.println("================================");
        System.out.println("Employee #: " + employeeNumber);
        System.out.println("Last Name: " + lastName);
        System.out.println("First Name: " + firstName);
        System.out.println("Birthdate: " + getBirthdateAsString());
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("SSS #: " + sssNumber);
        System.out.println("PhilHealth #: " + philHealthNumber);
        System.out.println("TIN #: " + tinNumber);
        System.out.println("Pag-IBIG #: " + pagIbigNumber);
        System.out.println("Status: " + status);
        System.out.println("Position: " + position);
        System.out.println("Immediate Supervisor: " + immediateSupervisor);
        System.out.println("Basic Salary: PHP " + CurrencyUtil.formatCurrency(basicSalary));
        System.out.println("Rice Subsidy: PHP " + CurrencyUtil.formatCurrency(riceSubsidy));
        System.out.println("Phone Allowance: PHP " + CurrencyUtil.formatCurrency(phoneAllowance));
        System.out.println("Clothing Allowance: PHP " + CurrencyUtil.formatCurrency(clothingAllowance));
        System.out.println("Gross Semi-monthly Rate: PHP " + CurrencyUtil.formatCurrency(grossSemimonthlyRate));
        System.out.println("Hourly Rate: PHP " + CurrencyUtil.formatCurrency(hourlyRate));
        System.out.println("================================");
    }
}
