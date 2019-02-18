package com.company;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Employee employee = createEmployee();

        List<TaxBracket> taxBrackets = getTaxBrackets();

        PayslipGenerator payslipGenerator = new PayslipGenerator(taxBrackets);
        Payslip payslip = payslipGenerator.createPayslip(employee);


        System.out.println("Name: " + employee.getFullName());
        System.out.println("Pay Period: " + employee.getPayPeriod());
        System.out.println("Gross Income: " + payslip.getGrossIncome());
        System.out.println("Income Tax: " + payslip.getIncomeTax());
        System.out.println("Net Income: " + payslip.getNetIncome());
        System.out.println("Super: " + payslip.getSuperAmount());

    }

    private static List<TaxBracket> getTaxBrackets() {
        TaxBracket t1 = new TaxBracket(0, 18200, 0, 0);
        TaxBracket t2 = new TaxBracket(18201,37000, 0, 0.19);
        TaxBracket t3 = new TaxBracket(37001, 87000, 3572, 0.325);
        TaxBracket t4 = new TaxBracket(87001, 180000, 19822, 0.37);
        TaxBracket t5 = new TaxBracket(180000, Integer.MAX_VALUE, 54232, 0.45);
        return new ArrayList<>(Arrays.asList(t1,t2,t3,t4,t5));

    }

    private static Employee createEmployee() {
        InputParser inputParser = new InputParser();

        String fullName = inputParser.getUserName();
        double annualSalary = inputParser.getAnnualSalary();
        double superRate = inputParser.getSuperRate();
        MonthDay startDate = inputParser.getPayPeriodStartDate();
        MonthDay endDate = inputParser.getPayPeriodEndDate();
        String payPeriod = startDate.getDayOfMonth() + " " + startDate.getMonth() + " - " + endDate.getDayOfMonth() + " " + endDate.getMonth();


        return new Employee(fullName, annualSalary, superRate, payPeriod);
    }

}


