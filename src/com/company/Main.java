package com.company;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    	InputParser inputParser = new InputParser();

		String fullName = inputParser.getUserName();
		double annualSalary = inputParser.getAnnualSalary();
		double superRate = inputParser.getSuperRate();
		MonthDay startDate = inputParser.getPayPeriodStartDay();
		MonthDay endDate = inputParser.getPayPeriodEndDay();


		long grossIncome = Math.round(annualSalary/12);

		TaxBracket t1 = new TaxBracket(0, 18200, 0, 0);
		TaxBracket t2 = new TaxBracket(18201,37000, 0, 0.19);
		TaxBracket t3 = new TaxBracket(37001, 87000, 3572, 0.325);
		TaxBracket t4 = new TaxBracket(87001, 180000, 19822, 0.37);
		TaxBracket t5 = new TaxBracket(180000, Integer.MAX_VALUE, 54232, 0.45);
		List<TaxBracket> taxBrackets = new ArrayList<>(Arrays.asList(t1,t2,t3,t4,t5));


		long incomeTax = 0;
		for(TaxBracket bracket : taxBrackets){
			if(annualSalary < bracket.getMaxIncome()){
				incomeTax = Math.round((bracket.getBaseTax() + (annualSalary - bracket.getMinIncome()) * bracket.getTaxRate())/12);
				break;
			}
		}

		long netIncome = grossIncome - incomeTax;

		long superAmount = Math.round(grossIncome * superRate);


		System.out.println("Name: " + fullName);
		System.out.println("Pay Period: " + startDate.getDayOfMonth() + " " + startDate.getMonth() + " - " + endDate.getDayOfMonth() + " " + endDate.getMonth();
		System.out.println("Gross Income: " + grossIncome);
		System.out.println("Income Tax: " + incomeTax);
		System.out.println("Net Income: " + netIncome);
		System.out.println("Super: " + superAmount);

    }

}


