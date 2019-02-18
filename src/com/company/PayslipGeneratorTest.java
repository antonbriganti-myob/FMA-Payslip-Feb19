package com.company;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PayslipGeneratorTest {

    private PayslipGenerator generator;
    private Employee employee;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        TaxBracket t1 = new TaxBracket(0, 18200, 0, 0);
        TaxBracket t2 = new TaxBracket(18201,37000, 0, 0.19);
        TaxBracket t3 = new TaxBracket(37001, 87000, 3572, 0.325);
        TaxBracket t4 = new TaxBracket(87001, 180000, 19822, 0.37);
        TaxBracket t5 = new TaxBracket(180000, Integer.MAX_VALUE, 54232, 0.45);
        ArrayList<TaxBracket> brackets =  new ArrayList<>(Arrays.asList(t1,t2,t3,t4,t5));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay start = MonthDay.parse("1 March", formatter);
        MonthDay end = MonthDay.parse("31 March", formatter);

        PayPeriod payPeriod = new PayPeriod(start, end);

        employee = new Employee("John Jones", 60050, 9, payPeriod);
        generator = new PayslipGenerator(brackets);
    }

    @org.junit.jupiter.api.Test
    void createPayslipCreatesPayslipObject() {
        var result = generator.createPayslip(employee);
        assertSame(result.getClass(), Payslip.class);
    }

    @org.junit.jupiter.api.Test
    void calculateGrossIncomeCorrectlyCalculates() {
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getGrossIncome(), 5004);
    }

    @org.junit.jupiter.api.Test
    void calculateIncomeTaxCorrectlyCalculates() {
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 922);
    }

    @org.junit.jupiter.api.Test
    void calculateNetIncomeCorrectlyCalculates() {
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getNetIncome(), 4082);
    }

    @org.junit.jupiter.api.Test
    void calculateSuperCalculates() {
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getSuperAmount(), 450);
    }

//  income tax tests
    @org.junit.jupiter.api.Test
    void bracket1MinValueTest() {
        employee.setAnnualSalary(0);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket1MaxValueTest() {
        employee.setAnnualSalary(18200);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket1MidValueTest() {
        employee.setAnnualSalary(9100);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket2MinValueTest() {
        employee.setAnnualSalary(18201);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket2MaxValueTest() {
        employee.setAnnualSalary(37000);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 298);
    }

    @org.junit.jupiter.api.Test
    void bracket2MidValueTest() {
        employee.setAnnualSalary(27600);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 149);
    }

    @org.junit.jupiter.api.Test
    void bracket3MinValueTest() {
        employee.setAnnualSalary(37001);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 298);
    }

    @org.junit.jupiter.api.Test
    void bracket3MaxValueTest() {
        employee.setAnnualSalary(87000);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 1652);
    }

    @org.junit.jupiter.api.Test
    void bracket3MidValueTest() {
        employee.setAnnualSalary(62000.5);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 975);
    }

    @org.junit.jupiter.api.Test
    void bracket4MinValueTest() {
        employee.setAnnualSalary(87001);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 1652);
    }

    @org.junit.jupiter.api.Test
    void bracket4MaxValueTest() {
        employee.setAnnualSalary(180000);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 4519);
    }

    @org.junit.jupiter.api.Test
    void bracket4MidValueTest() {
        employee.setAnnualSalary(133500.5);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 3086);
    }

    @org.junit.jupiter.api.Test
    void bracket5ValueTest() {
        employee.setAnnualSalary(180001);
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 4519);
    }

}