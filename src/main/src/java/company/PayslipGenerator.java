package company;

import java.util.List;

public class PayslipGenerator {
    private List<TaxBracket> taxBrackets;

    public PayslipGenerator(List<TaxBracket> taxBrackets) {
        this.taxBrackets = taxBrackets;
    }

    public Payslip createPayslip(Employee employee){
        long grossIncome = calculateGrossIncome(employee.getAnnualSalary());

        long incomeTax = calculateIncomeTax(employee.getAnnualSalary());

        long netIncome = calculateNetIncome(grossIncome, incomeTax);

        long superAmount = calculateSuperAmount(grossIncome, employee.getSuperRate());

        return new Payslip(employee, grossIncome, incomeTax, netIncome, superAmount);
    }

    private long calculateGrossIncome(double annualSalary) {
        return Math.round(annualSalary/12);
    }

    private long calculateIncomeTax(double annualSalary) {
        long incomeTax = 0;
        for(TaxBracket bracket : taxBrackets){
            if(annualSalary < bracket.getMaxIncome()){
                incomeTax = Math.round((bracket.getBaseTax() + (annualSalary - bracket.getMinIncome()) * bracket.getTaxRate())/12);
                break;
            }
        }
        return incomeTax;
    }

    private long calculateNetIncome(long grossIncome, long incomeTax) {
        return grossIncome - incomeTax;
    }

    private long calculateSuperAmount(long grossIncome, double superRate) {
        return Math.round(grossIncome * (superRate/100));
    }


}
