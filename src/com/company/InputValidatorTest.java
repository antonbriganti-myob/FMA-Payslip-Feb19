package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    @Test
    void validateAnnualSalaryReturnsTrueOnValidSalary() {
        assertTrue(validator.validateAnnualSalary(60000));
    }

    @Test
    void validateAnnualSalaryReturnsTrueOnNegativeValue() {
        assertFalse(validator.validateAnnualSalary(-1));
    }

    @Test
    void validateSuperRateReturnsTrueOnLowerLimit() {
        assertTrue(validator.validateSuperRate(0));
    }

    @Test
    void validateSuperRateReturnsTrueOnUpperLimit() {
        assertTrue(validator.validateSuperRate(50));
    }

    @Test
    void validateSuperRateReturnsTrueOnMiddleLimit() {
        assertTrue(validator.validateSuperRate(25));
    }

    @Test
    void validateSuperRateReturnsFalseOnNegativeValue() {
        assertFalse(validator.validateSuperRate(-1));
    }

    @Test
    void validateSuperRateReturnsFalseOnValueAboveUpperLimit() {
        assertFalse(validator.validateSuperRate(51));
    }


    @Test
    void validatePayPeriodStartReturnsTrueOnStartDateOnValidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay date = MonthDay.parse("1 March", formatter);
        assertTrue(validator.validatePayPeriodEndDate(date));
    }

    @Test
    void validatePayPeriodStatReturnsTrueOnStartDateOnInvalidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay date = MonthDay.parse("21 March", formatter);
        assertTrue(validator.validatePayPeriodEndDate(date));
    }

    @Test
    void validatePayPeriodEndReturnsTrueOnStartDateOnValidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay date = MonthDay.parse("31 March", formatter);
        assertTrue(validator.validatePayPeriodEndDate(date));
    }

    @Test
    void validatePayPeriodEndReturnsTrueOnStartDateOnInvalidDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay date = MonthDay.parse("3 March", formatter);
        assertTrue(validator.validatePayPeriodEndDate(date));
    }

    //write tests about making sure they're same date
    @Test
    void validatePayPeriodDateRangeReturnsTrueOnSameMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay start = MonthDay.parse("1 March", formatter);
        MonthDay end = MonthDay.parse("31 March", formatter);
        assertTrue(validator.validatePayPeriodDateRange(start, end));
    }

    @Test
    void validatePayPeriodDateRangeReturnsInvalidOnDifferentMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH);
        MonthDay start = MonthDay.parse("1 March", formatter);
        MonthDay end = MonthDay.parse("30 April", formatter);
        assertFalse(validator.validatePayPeriodDateRange(start, end));
    }
}