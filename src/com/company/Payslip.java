package com.company;

public class Payslip {
    private Employee employee;
    private long grossIncome;
    private long incomeTax;
    private long netIncome;
    private long superAmount;

    public Payslip(Employee employee, long grossIncome, long incomeTax, long netIncome, long superAmount) {
        this.employee = employee;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.superAmount = superAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(long grossIncome) {
        this.grossIncome = grossIncome;
    }

    public long getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(long incomeTax) {
        this.incomeTax = incomeTax;
    }

    public long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }

    public long getSuperAmount() {
        return superAmount;
    }

    public void setSuperAmount(long superAmount) {
        this.superAmount = superAmount;
    }
}
