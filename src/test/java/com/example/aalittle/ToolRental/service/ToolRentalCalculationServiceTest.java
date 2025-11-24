
package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.enums.ToolBrandEnum;
import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.enums.ToolTypeEnum;
import com.example.aalittle.ToolRental.model.RentalAgreement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.aalittle.ToolRental.utils.RentalTermPayloads.*;

@SpringBootTest
public class ToolRentalCalculationServiceTest {

    @Autowired
    private ToolRentalCalculationService toolRentalCalculationService;

// ==================== MANDATORY TESTS ====================

    @Test
    public void test2_LADW_July2_2020_3Days_10Discount_calculatesCorrectly() {
// Test 2: Ladder, 3 rental days, 10% discount
// Checkout: 7/2/20 (Thu)
// July 4th 2020 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// Ladder: weekday charge YES, weekend charge YES, holiday charge NO
// 7/3 (Fri-observed holiday) = NOT charged (holiday rule applies)
// 7/4 (Sat-weekend) = charged
// 7/5 (Sun-weekend) = charged
// Chargeable days: 2
// Due date: 7/5/20
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_DATE_JULY_2_20_DAYS_3_DISCOUNT_10);

        assertNotNull(agreement);
        assertEquals(ToolCodeEnum.LADW, agreement.getToolCode());
        assertEquals(ToolTypeEnum.LADDER, agreement.getToolType());
        assertEquals(ToolBrandEnum.WERNER, agreement.getToolBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
        assertEquals(new BigDecimal("1.99"), agreement.getDailyCharge());
        assertEquals(2, agreement.getChargeableDays());
        assertEquals(new BigDecimal("3.98"), agreement.getSubTotal());
        assertEquals(new BigDecimal("10.0"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.40"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("3.58"), agreement.getDiscountCharge());
    }

    @Test
    public void test3_CHNS_July2_2015_5Days_25Discount_calculatesCorrectly() {
// Test 3: Chainsaw, 5 rental days, 25% discount
// Checkout: 7/2/15 (Thu)
// July 4th 2015 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon), 7/7 (Tue)
// Chainsaw: weekday charge YES, weekend charge NO, holiday charge YES
// 7/3 (Fri-observed holiday) = charged (chainsaw charges holidays)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// 7/7 (Tue-weekday) = charged
// Chargeable days: 3
// Due date: 7/7/15
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                CHNS_DATE_JULY_2_15_DAYS_5_DISCOUNT_25);

        assertNotNull(agreement);
        assertEquals(ToolCodeEnum.CHNS, agreement.getToolCode());
        assertEquals(ToolTypeEnum.CHAINSAW, agreement.getToolType());
        assertEquals(ToolBrandEnum.STIHL, agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate());
        assertEquals(new BigDecimal("1.49"), agreement.getDailyCharge());
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(new BigDecimal("4.47"), agreement.getSubTotal());
        assertEquals(new BigDecimal("25.00"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("3.35"), agreement.getDiscountCharge());
    }

    @Test
    public void test4_JAKD_September3_2015_6Days_0Discount_calculatesCorrectly() {
// Test 4: Jackhammer, 6 rental days, 0% discount
// Checkout: 9/3/15 (Thu)
// Labor Day 2015 is 9/7 (Mon) - no observation needed
// Rental period: 9/4 (Fri), 9/5 (Sat), 9/6 (Sun), 9/7 (Mon), 9/8 (Tue), 9/9 (Wed)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 9/4 (Fri-weekday) = charged
// 9/5 (Sat-weekend) = NOT charged
// 9/6 (Sun-weekend) = NOT charged
// 9/7 (Mon-Labor Day holiday) = NOT charged
// 9/8 (Tue-weekday) = charged
// 9/9 (Wed-weekday) = charged
// Chargeable days: 3
// Due date: 9/9/15
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKD_DATE_SEPTEMBER_3_15_DAYS_6_DISCOUNT_0);

        assertNotNull(agreement);
        assertEquals(ToolCodeEnum.JAKD, agreement.getToolCode());
        assertEquals(ToolTypeEnum.JACKHAMMER, agreement.getToolType());
        assertEquals(ToolBrandEnum.DEWALT, agreement.getToolBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyCharge());
        assertEquals(3, agreement.getChargeableDays());
        assertEquals(new BigDecimal("8.97"), agreement.getSubTotal());
        assertEquals(new BigDecimal("0.0"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("8.97"), agreement.getDiscountCharge());
    }

    @Test
    public void test5_JAKR_July2_2015_9Days_0Discount_calculatesCorrectly() {
// Test 5: Jackhammer, 9 rental days, 0% discount
// Checkout: 7/2/15 (Thu)
// July 4th 2015 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon), 7/7 (Tue), 7/8 (Wed), 7/9 (Thu), 7/10 (Fri), 7/11 (Sat)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 7/3 (Fri-observed holiday) = NOT charged
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// 7/7 (Tue-weekday) = charged
// 7/8 (Wed-weekday) = charged
// 7/9 (Thu-weekday) = charged
// 7/10 (Fri-weekday) = charged
// 7/11 (Sat-weekend) = NOT charged
// Chargeable days: 5
// Due date: 7/11/15
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKR_DATE_JULY_2_15_DAYS_9_DISCOUNT_0);

        assertNotNull(agreement);
        assertEquals(ToolCodeEnum.JAKR, agreement.getToolCode());
        assertEquals(ToolTypeEnum.JACKHAMMER, agreement.getToolType());
        assertEquals(ToolBrandEnum.RIGID, agreement.getToolBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyCharge());
        assertEquals(5, agreement.getChargeableDays());
        assertEquals(new BigDecimal("14.95"), agreement.getSubTotal());
        assertEquals(new BigDecimal("0.0"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("14.95"), agreement.getDiscountCharge());
    }

    @Test
    public void test6_JAKR_July2_2020_4Days_50Discount_calculatesCorrectly() {
// Test 6: Jackhammer, 4 rental days, 50% discount
// Checkout: 7/2/20 (Thu)
// July 4th 2020 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 7/3 (Fri-observed holiday) = NOT charged
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// Chargeable days: 1
// Due date: 7/6/20
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKR_DATE_JULY_2_20_DAYS_4_DISCOUNT_50);

        assertNotNull(agreement);
        assertEquals(ToolCodeEnum.JAKR, agreement.getToolCode());
        assertEquals(ToolTypeEnum.JACKHAMMER, agreement.getToolType());
        assertEquals(ToolBrandEnum.RIGID, agreement.getToolBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
        assertEquals(new BigDecimal("2.99"), agreement.getDailyCharge());
        assertEquals(1, agreement.getChargeableDays());
        assertEquals(new BigDecimal("2.99"), agreement.getSubTotal());
        assertEquals(new BigDecimal("50.0"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("1.50"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("1.49"), agreement.getDiscountCharge());
    }

// ==================== EDGE CASE TESTS ====================

    @Test
    public void testSingleDayRental_calculatesCorrectly() {
// Checkout: 1/6/25 (Mon)
// Rental period: 1/7 (Tue)
// Ladder: weekday YES, weekend YES, holiday NO
// 1/7 (Tue-weekday) = charged
// Chargeable days: 1
// Due date: 1/7
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_SINGLE_DAY_MONDAY);

        assertNotNull(agreement);
        assertEquals(1, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 1, 7), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
        assertEquals(new BigDecimal("1.99"), agreement.getDiscountCharge());
    }

    @Test
    public void test100PercentDiscount_calculatesCorrectly() {
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_100_PERCENT_DISCOUNT);

        assertNotNull(agreement);
        assertEquals(new BigDecimal("100.0"), agreement.getDiscountPercent());
        assertEquals(new BigDecimal("0.00"), agreement.getDiscountCharge());
    }

    @Test
    public void testJackhammerCheckoutThursday_3Days() {
// Checkout: 1/2/25 (Thu)
// Rental period: 1/3 (Fri), 1/4 (Sat), 1/5 (Sun)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 1/3 (Fri-weekday) = charged
// 1/4 (Sat-weekend) = NOT charged
// 1/5 (Sun-weekend) = NOT charged
// Chargeable days: 1
// Due date: 1/5
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKD_CHECKOUT_THURSDAY_3_DAYS);

        assertNotNull(agreement);
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 1, 5), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
    }

    @Test
    public void testLongRentalPeriod_30Days() {
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_30_DAYS);

        assertNotNull(agreement);
        assertEquals(30, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 1, 31), agreement.getDueDate());
        assertTrue(agreement.getChargeableDays() > 0);
        assertTrue(agreement.getChargeableDays() <= 30);
        assertTrue(agreement.getDiscountCharge().compareTo(BigDecimal.ZERO) > 0);
    }

// ==================== HOLIDAY SPECIFIC TESTS ====================

    @Test
    public void testIndependenceDayOnFriday_chainsawCharged() {
// July 4th, 2025 is Friday - no observation needed
// Checkout: 7/3 (Thu)
// Rental period: 7/4 (Fri), 7/5 (Sat)
// Chainsaw: weekday YES, weekend NO, holiday YES
// 7/4 (Fri-holiday) = charged (chainsaw charges holidays)
// 7/5 (Sat-weekend) = NOT charged
// Chargeable days: 1
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                CHNS_JULY_4TH_FRIDAY_2025);

        assertNotNull(agreement);
        assertEquals(2, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 7, 5), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
    }

    @Test
    public void testIndependenceDayOnFriday_jackhammerNotCharged() {
// July 4th, 2025 is Friday - no observation needed
// Checkout: 7/3 (Thu)
// Rental period: 7/4 (Fri), 7/5 (Sat)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 7/4 (Fri-holiday) = NOT charged (jackhammer doesn't charge holidays)
// 7/5 (Sat-weekend) = NOT charged
// Chargeable days: 0
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKD_JULY_4TH_FRIDAY_2025);

        assertNotNull(agreement);
        assertEquals(2, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 7, 5), agreement.getDueDate());
        assertEquals(0, agreement.getChargeableDays());
    }

    @Test
    public void testIndependenceDayOnSaturday_observedFriday_chainsaw() {
// July 4th, 2015 is Saturday, observed on Friday July 3rd
// Checkout: 7/2 (Thu)
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// Chainsaw: weekday YES, weekend NO, holiday YES
// 7/3 (Fri-observed holiday) = charged (chainsaw charges holidays)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// Chargeable days: 1
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                CHNS_JULY_4TH_SATURDAY_2015);

        assertNotNull(agreement);
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 5), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
    }

    @Test
    public void testIndependenceDayOnSaturday_observedFriday_jackhammer() {
// July 4th, 2015 is Saturday, observed on Friday July 3rd
// Checkout: 7/1 (Wed)
// Rental period: 7/2 (Thu), 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 7/2 (Thu-weekday) = charged
// 7/3 (Fri-observed holiday) = NOT charged
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// Chargeable days: 1
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKR_JULY_4TH_SATURDAY_2015);

        assertNotNull(agreement);
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 5), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
    }

    @Test
    public void testIndependenceDayOnSunday_observedMonday_chainsaw() {
// July 4th, 2021 is Sunday, observed on Monday July 5th
// Checkout: 7/2 (Fri)
// Rental period: 7/3 (Sat), 7/4 (Sun), 7/5 (Mon), 7/6 (Tue), 7/7 (Wed)
// Chainsaw: weekday YES, weekend NO, holiday YES
// 7/3 (Sat-weekend) = NOT charged
// 7/4 (Sun-weekend) = NOT charged
// 7/5 (Mon-observed holiday) = charged (chainsaw charges holidays)
// 7/6 (Tue-weekday) = charged
// 7/7 (Wed-weekday) = charged
// Chargeable days: 3
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                CHNS_JULY_4TH_SUNDAY_2021);

        assertNotNull(agreement);
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2021, 7, 7), agreement.getDueDate());
        assertEquals(3, agreement.getChargeableDays());
    }

    @Test
    public void testIndependenceDayOnSunday_observedMonday_jackhammer() {
// July 4th, 2021 is Sunday, observed on Monday July 5th
// Checkout: 7/2 (Fri)
// Rental period: 7/3 (Sat), 7/4 (Sun), 7/5 (Mon), 7/6 (Tue)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 7/3 (Sat-weekend) = NOT charged
// 7/4 (Sun-weekend) = NOT charged
// 7/5 (Mon-observed holiday) = NOT charged
// 7/6 (Tue-weekday) = charged
// Chargeable days: 1
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKR_JULY_4TH_SUNDAY_2021);

        assertNotNull(agreement);
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2021, 7, 6), agreement.getDueDate());
        assertEquals(1, agreement.getChargeableDays());
    }

    @Test
    public void testLaborDayWeekend_jackhammer() {
// Labor Day 2015 is Sept 7 (Monday) - always first Monday, no observation
// Checkout: 9/4 (Fri)
// Rental period: 9/5 (Sat), 9/6 (Sun), 9/7 (Mon), 9/8 (Tue), 9/9 (Wed)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 9/5 (Sat-weekend) = NOT charged
// 9/6 (Sun-weekend) = NOT charged
// 9/7 (Mon-Labor Day holiday) = NOT charged
// 9/8 (Tue-weekday) = charged
// 9/9 (Wed-weekday) = charged
// Chargeable days: 2
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKD_LABOR_DAY_2015);

        assertNotNull(agreement);
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertEquals(2, agreement.getChargeableDays());
    }

// ==================== ROUNDING TESTS ====================

    @Test
    public void testRounding_halfUpCents() {
// 33% of $5.98 = $1.9734, should round to $1.97
// Checkout: 1/6 (Mon)
// Rental period: 1/7 (Tue), 1/8 (Wed)
// Jackhammer: weekday YES, weekend NO, holiday NO
// 1/7 (Tue-weekday) = charged
// 1/8 (Wed-weekday) = charged
// Chargeable days: 2 at $2.99 = $5.98
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                JAKD_ROUNDING_TEST);
        assertNotNull(agreement);
        assertEquals(2, agreement.getRentalDays());
        assertEquals(LocalDate.of(2025, 1, 8), agreement.getDueDate());
        assertEquals(2, agreement.getChargeableDays());
        assertEquals(new BigDecimal("5.98"), agreement.getSubTotal());
        assertEquals(new BigDecimal("1.97"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("4.01"), agreement.getDiscountCharge());
    }

// ==================== DATE CALCULATION TESTS ====================

    @Test
    public void testDueDateCalculation() {
// Checkout: 1/1, rental days: 5, due date: 1/6 (5 days after checkout)
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_DUE_DATE_TEST);
        assertNotNull(agreement);
        assertEquals(LocalDate.of(2025, 1, 1), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2025, 1, 6), agreement.getDueDate());
    }

    @Test
    public void testMonthBoundary() {
// Checkout: Jan 30, 5 rental days, due: Feb 4
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_MONTH_BOUNDARY);
        assertNotNull(agreement);
        assertEquals(LocalDate.of(2025, 1, 30), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2025, 2, 4), agreement.getDueDate());
    }

    @Test
    public void testYearBoundary() {
// Checkout: Dec 30, 5 rental days, due: Jan 4 next year
        RentalAgreement agreement = toolRentalCalculationService.calculateToolRental(
                LADW_YEAR_BOUNDARY);
        assertNotNull(agreement);
        assertEquals(LocalDate.of(2024, 12, 30), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2025, 1, 4), agreement.getDueDate());
    }
}