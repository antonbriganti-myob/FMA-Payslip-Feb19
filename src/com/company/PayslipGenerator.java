package com.company;

import java.util.List;

public class PayslipGenerator {
    private List<TaxBracket> taxBrackets;

    public PayslipGenerator(List<TaxBracket> taxBrackets) {
        this.taxBrackets = taxBrackets;
    }

    public Payslip createPayslip(Employee employee){
        long grossIncome = Math.round(employee.getAnnualSalary()/12);

        long incomeTax = 0;
        for(TaxBracket bracket : taxBrackets){
            if(employee.getAnnualSalary() < bracket.getMaxIncome()){
                incomeTax = Math.round((bracket.getBaseTax() + (employee.getAnnualSalary() - bracket.getMinIncome()) * bracket.getTaxRate())/12);
                break;
            }
        }

        long netIncome = grossIncome - incomeTax;

        long superAmount = Math.round(grossIncome * employee.getSuperRate());

        return new Payslip(employee, grossIncome, incomeTax, netIncome, superAmount);
    }
}
