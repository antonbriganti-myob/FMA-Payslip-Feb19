package com.company;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class InputParser {

    private Scanner scanner = new Scanner(System.in);

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
               if(checkIfValidSalary(annualSalary)){
                   validSalaryEntered = true;
               }
               else{
                   System.out.println("Salary must be greater than 0. Please try again.");
               }

           }
           catch (NumberFormatException e){
               System.out.println("Invalid input, please enter salary again: ");
            }
        }

        return annualSalary;
    }

    private boolean checkIfValidSalary(double annualSalary) {
        return annualSalary > 0;
    }

    double getSuperRate() {
        double superRate = 0;
        boolean validSuperEntered = false;


        while(!validSuperEntered){
            System.out.println("Enter your annual super rate: ");
            try{
                String input = scanner.nextLine();
                superRate = Double.valueOf(input);
                if(checkIfValidSuperRate(superRate)){
                    validSuperEntered = true;
                }
                else{
                    System.out.println("Super rate must be between 0 and 50. Please try again.");
                }

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input detected. Please try again.");
            }
        }

        return superRate;
    }


    MonthDay getPayPeriodStartDay() {
        MonthDay date = MonthDay.now();
        boolean validStartDateEntered = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);

        while(!validStartDateEntered){
            System.out.println("Enter your payment start date: ");
            try{
                String input = scanner.nextLine();
                date = MonthDay.parse(input, formatter);
                if(checkIfStartOfMonth(date)){
                    validStartDateEntered = true;
                }
                else{
                    System.out.println("Pay period start date must be the start of the month. Please try again");
                }

            }
            catch (Exception e){
                System.out.println("Invalid input detected. Ensure that date is given in format of 'Day Month' (i.e. 1 March)");
                System.out.println("Please try again.");
            }
        }

        return date;
    }

    MonthDay getPayPeriodEndDay() {
        MonthDay date = MonthDay.now();
        boolean validStartDateEntered = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);

        while(!validStartDateEntered){
            System.out.println("Enter your payment end date: ");
            try{
                String input = scanner.nextLine();
                date = MonthDay.parse(input, formatter);
                if(checkIfEndOfMonth(date)){
                    validStartDateEntered = true;
                }
                else{
                    System.out.println("Pay period end date must be the start of the month. Please try again");
                    System.out.println("For the month of " + date.getMonth() + ", the end of the month is " + date.getMonth().minLength());
                }

            }
            catch (Exception e){
                System.out.println("Invalid input detected. Ensure that date is given in format of 'Day Month' (i.e. 1 March)");
                System.out.println("Please try again.");
            }
        }

        return date;
    }

    private boolean checkIfStartOfMonth(MonthDay date) {
        return (date.getDayOfMonth() == 1);
    }

    private boolean checkIfEndOfMonth(MonthDay date) {
        return (date.getDayOfMonth() == date.getMonth().minLength());
    }

    private boolean checkIfValidSuperRate(double superRate) {
        return superRate >= 0 && superRate <= 50;
    }



}
