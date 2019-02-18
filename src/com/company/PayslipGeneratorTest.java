package com.company;

import java.util.ArrayList;
import java.util.Arrays;

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

        employee = new Employee("John Jones", 60050, 9, "01 MARCH - 31 MARCH");
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
        employee = new Employee("John Jones", 0, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket1MaxValueTest() {
        employee = new Employee("John Jones", 18200, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket1MidValueTest() {
        employee = new Employee("John Jones", 9100, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket2MinValueTest() {
        employee = new Employee("John Jones", 18201, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 0);
    }

    @org.junit.jupiter.api.Test
    void bracket2MaxValueTest() {
        employee = new Employee("John Jones", 37000, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 298);
    }

    @org.junit.jupiter.api.Test
    void bracket2MidValueTest() {
        employee = new Employee("John Jones", 27600, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 149);
    }

    @org.junit.jupiter.api.Test
    void bracket3MinValueTest() {
        employee = new Employee("John Jones", 37001, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 298);
    }

    @org.junit.jupiter.api.Test
    void bracket3MaxValueTest() {
        employee = new Employee("John Jones", 87000, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 1652);
    }

    @org.junit.jupiter.api.Test
    void bracket3MidValueTest() {
        employee = new Employee("John Jones", 62000.5, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 975);
    }

    @org.junit.jupiter.api.Test
    void bracket4MinValueTest() {
        employee = new Employee("John Jones", 87001, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 1652);
    }

    @org.junit.jupiter.api.Test
    void bracket4MaxValueTest() {
        employee = new Employee("John Jones", 180000, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 4519);
    }

    @org.junit.jupiter.api.Test
    void bracket4MidValueTest() {
        employee = new Employee("John Jones", 133500.5, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 3086);
    }

    @org.junit.jupiter.api.Test
    void bracket5ValueTest() {
        employee = new Employee("John Jones", 180001, 9, "01 MARCH - 31 MARCH");
        var payslip = generator.createPayslip(employee);
        assertEquals(payslip.getIncomeTax(), 4519);
    }

}