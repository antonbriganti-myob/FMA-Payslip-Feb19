package company;

import java.time.MonthDay;

public class InputValidator {
    boolean validateAnnualSalary(double annualSalary) {
        if(checkIfValidSalary(annualSalary)){
            return true;
        }
        else{
            System.out.println("Salary must be greater than 0. Please try again.");
            return false;
        }
    }

    private boolean checkIfValidSalary(double annualSalary) {
        return annualSalary > 0;
    }

    boolean validateSuperRate(double superRate) {
        if(checkIfValidSuperRate(superRate)){
            return true;
        }
        else{
            System.out.println("Super rate must be between 0 and 50. Please try again.");
            return false;
        }
    }

    private boolean checkIfValidSuperRate(double superRate) {
        return superRate >= 0 && superRate <= 50;
    }

    boolean validatePayPeriodStartDate(MonthDay date) {
        if(checkIfStartOfMonth(date)){
            return true;
        }
        else{
            System.out.println("Pay period start date must be the start of the month. Please try again");
            return false;
        }
    }

    private boolean checkIfStartOfMonth(MonthDay date) {
        return (date.getDayOfMonth() == 1);
    }

    boolean validatePayPeriodEndDate(MonthDay date) {
        if(checkIfEndOfMonth(date)){
            return true;
        }
        else{
            System.out.println("Pay period end date must be the start of the month. Please try again");
            System.out.println("For the month of " + date.getMonth() + ", the end of the month is " + date.getMonth().minLength());
            return false;
        }
    }

    private boolean checkIfEndOfMonth(MonthDay date) {
        return (date.getDayOfMonth() == date.getMonth().minLength());
    }

    boolean validatePayPeriodDateRange(MonthDay start, MonthDay end) {
        if(checkIfSameMonth(start, end)){
            return true;
        }
        else{
            System.out.println("Pay period start and end dates must be the same month. Please try again");
            return false;
        }
    }

    private boolean checkIfSameMonth(MonthDay start, MonthDay end) {
        return (start.getMonthValue() == end.getMonthValue());
    }
}