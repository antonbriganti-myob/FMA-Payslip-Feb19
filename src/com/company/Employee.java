package com.company;

public class Employee {
    private String fullName;
    private double annualSalary;
    private double superRate;
    private String payPeriod;

    public Employee(String fullName, double annualSalary, double superRate, String payPeriod) {
        this.fullName = fullName;
        this.annualSalary = annualSalary;
        this.superRate = superRate;
        this.payPeriod = payPeriod;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getSuperRate() {
        return superRate;
    }

    public void setSuperRate(double superRate) {
        this.superRate = superRate;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }
}
