package company;


import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class InputParser {

    private Scanner scanner = new Scanner(System.in);
    private InputValidator validator = new InputValidator();

    String getUserName() {
        String userName;

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        userName = firstName + " " + lastName;

        return userName;
    }

    double getAnnualSalary() {
        double annualSalary = 0;
        boolean validSalaryEntered = false;

        System.out.println("Enter your annual salary: ");

        while(!validSalaryEntered){
            try{
                String input = scanner.nextLine();
                annualSalary = Double.valueOf(input);
                validSalaryEntered = validator.validateAnnualSalary(annualSalary);

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter salary again: ");
            }
        }

        return annualSalary;
    }

    double getSuperRate() {
        double superRate = 0;
        boolean validSuperEntered = false;

        while(!validSuperEntered){
            System.out.println("Enter your annual super rate: ");
            try{
                String input = scanner.nextLine();
                superRate = Double.valueOf(input);
                validSuperEntered = validator.validateSuperRate(superRate);

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input detected. Please try again.");
            }
        }

        return superRate;
    }


    MonthDay getPayPeriodStartDate() {
        MonthDay date = MonthDay.now();
        boolean validStartDateEntered = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);

        while(!validStartDateEntered){
            System.out.println("Enter your payment start date: ");
            try{
                String input = scanner.nextLine();
                date = MonthDay.parse(input, formatter);
                validStartDateEntered = validator.validatePayPeriodStartDate(date);

            }
            catch (Exception e){
                System.out.println("Invalid input detected. Ensure that date is given in format of 'Day Month' (i.e. 1 March)");
                System.out.println("Please try again.");
            }
        }

        return date;
    }


    MonthDay getPayPeriodEndDate() {
        MonthDay date = MonthDay.now();
        boolean validEndDateEntered = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);


        while(!validEndDateEntered){
            System.out.println("Enter your payment end date: ");
            try{
                String input = scanner.nextLine();
                date = MonthDay.parse(input, formatter);
                validEndDateEntered = validator.validatePayPeriodEndDate(date);

            }
            catch (Exception e){
                System.out.println("Invalid input detected. Ensure that date is given in format of 'Day Month' (i.e. 1 March)");
                System.out.println("Please try again.");
            }
        }

        return date;
    }

    PayPeriod getPayPeriod(){
        MonthDay start = MonthDay.now();
        MonthDay end = MonthDay.now();
        boolean validPayPeriodEntered = false;

        while(!validPayPeriodEntered){
            start = getPayPeriodStartDate();
            end = getPayPeriodEndDate();
            validPayPeriodEntered = validator.validatePayPeriodDateRange(start, end);
        }

        return new PayPeriod(start, end);

    }







}
