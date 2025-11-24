package com.example.aalittle.ToolRental.utils;

import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.model.RentalTerm;

import java.time.LocalDate;

public class RentalTermPayloads {

// ==================== MANDATORY TEST PAYLOADS ====================

    // Test 1: JAKR, 9/3/15, 5 days, 101% discount - INVALID (handled in controller)
    public static final RentalTerm JAKR_DATE_SEPTEMBER_3_15_DAYS_5_DISCOUNT_101 =
            new RentalTerm(ToolCodeEnum.JAKR, LocalDate.of(2015, 9, 3), 5, 101);

    // Test 2: LADW, 7/2/20, 3 days, 10% discount
// Checkout: 7/2/20 (Thu)
// July 4th 2020 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// Ladder: weekday charge YES, weekend charge YES, holiday charge NO
// 7/3 (Fri-weekday, observed holiday) = charged (ladder charges weekdays but NOT holidays - CONFLICT: weekday wins)
// 7/4 (Sat-weekend) = charged
// 7/5 (Sun-weekend) = charged
// Chargeable days: 2 (7/4 and 7/5 only, NOT 7/3 because it's observed holiday)
    public static final RentalTerm LADW_DATE_JULY_2_20_DAYS_3_DISCOUNT_10 =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2020, 7, 2), 3, 10);

    // Test 3: CHNS, 7/2/15, 5 days, 25% discount
// Checkout: 7/2/15 (Thu)
// July 4th 2015 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon), 7/7 (Tue)
// Chainsaw: weekday charge YES, weekend charge NO, holiday charge YES
// 7/3 (Fri-weekday AND observed holiday) = charged (both weekday and holiday = YES)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// 7/7 (Tue-weekday) = charged
// Chargeable days: 3
    public static final RentalTerm CHNS_DATE_JULY_2_15_DAYS_5_DISCOUNT_25 =
            new RentalTerm(ToolCodeEnum.CHNS, LocalDate.of(2015, 7, 2), 5, 25);

    // Test 4: JAKD, 9/3/15, 6 days, 0% discount
// Checkout: 9/3/15 (Thu)
// Labor Day 2015 is 9/7 (Mon) - no observation rule needed
// Rental period: 9/4 (Fri), 9/5 (Sat), 9/6 (Sun), 9/7 (Mon), 9/8 (Tue), 9/9 (Wed)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 9/4 (Fri-weekday) = charged
// 9/5 (Sat-weekend) = NOT charged
// 9/6 (Sun-weekend) = NOT charged
// 9/7 (Mon-Labor Day holiday) = NOT charged
// 9/8 (Tue-weekday) = charged
// 9/9 (Wed-weekday) = charged
// Chargeable days: 3
    public static final RentalTerm JAKD_DATE_SEPTEMBER_3_15_DAYS_6_DISCOUNT_0 =
            new RentalTerm(ToolCodeEnum.JAKD, LocalDate.of(2015, 9, 3), 6, 0);

    // Test 5: JAKR, 7/2/15, 9 days, 0% discount
// Checkout: 7/2/15 (Thu)
// July 4th 2015 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon), 7/7 (Tue), 7/8 (Wed), 7/9 (Thu), 7/10 (Fri), 7/11 (Sat)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 7/3 (Fri-observed holiday) = NOT charged (holiday takes precedence)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// 7/7 (Tue-weekday) = charged
// 7/8 (Wed-weekday) = charged
// 7/9 (Thu-weekday) = charged
// 7/10 (Fri-weekday) = charged
// 7/11 (Sat-weekend) = NOT charged
// Chargeable days: 5
    public static final RentalTerm JAKR_DATE_JULY_2_15_DAYS_9_DISCOUNT_0 =
            new RentalTerm(ToolCodeEnum.JAKR, LocalDate.of(2015, 7, 2), 9, 0);

    // Test 6: JAKR, 7/2/20, 4 days, 50% discount
// Checkout: 7/2/20 (Thu)
// July 4th 2020 is Saturday, observed Friday 7/3
// Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun), 7/6 (Mon)
// Jackhammer: weekday charge YES, weekend charge NO, holiday charge NO
// 7/3 (Fri-observed holiday) = NOT charged (holiday takes precedence)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// 7/6 (Mon-weekday) = charged
// Chargeable days: 1
    public static final RentalTerm JAKR_DATE_JULY_2_20_DAYS_4_DISCOUNT_50 =
            new RentalTerm(ToolCodeEnum.JAKR, LocalDate.of(2020, 7, 2), 4, 50);

// ==================== EDGE CASE TEST PAYLOADS ====================

    // Single day rental - Checkout Monday, charge Tuesday
    public static final RentalTerm LADW_SINGLE_DAY_MONDAY =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2025, 1, 6), 1, 0);

    // 100% discount
    public static final RentalTerm LADW_100_PERCENT_DISCOUNT =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2025, 1, 6), 3, 100);

    // All weekends - Jackhammer checkout Thursday, charge Fri, Sat (NO), Sun (NO) = 1 day
    public static final RentalTerm JAKD_CHECKOUT_THURSDAY_3_DAYS =
            new RentalTerm(ToolCodeEnum.JAKD, LocalDate.of(2025, 1, 2), 3, 0);

    // Long rental period - 30 days
    public static final RentalTerm LADW_30_DAYS =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2025, 1, 1), 30, 15);

// ==================== HOLIDAY SPECIFIC TEST PAYLOADS ====================

    // Independence Day on weekday (Friday 7/4/25) - Chainsaw charged on holiday
// Checkout 7/3 (Thu), Rental period: 7/4 (Fri), 7/5 (Sat)
// July 4th 2025 is Friday - no observation needed
// 7/4 (Fri-holiday) = charged (chainsaw charges holidays)
// 7/5 (Sat-weekend) = NOT charged
// Chargeable days: 1
    public static final RentalTerm CHNS_JULY_4TH_FRIDAY_2025 =
            new RentalTerm(ToolCodeEnum.CHNS, LocalDate.of(2025, 7, 3), 2, 0);

    // Independence Day on weekday (Friday 7/4/25) - Jackhammer NOT charged on holiday
// Checkout 7/3 (Thu), Rental period: 7/4 (Fri), 7/5 (Sat)
// July 4th 2025 is Friday - no observation needed
// 7/4 (Fri-holiday) = NOT charged (jackhammer doesn't charge holidays)
// 7/5 (Sat-weekend) = NOT charged
// Chargeable days: 0
    public static final RentalTerm JAKD_JULY_4TH_FRIDAY_2025 =
            new RentalTerm(ToolCodeEnum.JAKD, LocalDate.of(2025, 7, 3), 2, 0);

    // Independence Day on Saturday (7/4/15), observed Friday (7/3/15) - Chainsaw
// Checkout 7/2 (Thu), Rental period: 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// July 4th 2015 is Saturday, observed Friday 7/3
// 7/3 (Fri-observed holiday) = charged (chainsaw charges holidays)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// Chargeable days: 1
    public static final RentalTerm CHNS_JULY_4TH_SATURDAY_2015 =
            new RentalTerm(ToolCodeEnum.CHNS, LocalDate.of(2015, 7, 2), 3, 0);

    // Independence Day on Sunday (7/4/21), observed Monday (7/5/21) - Chainsaw
// Checkout 7/2 (Fri), Rental period: 7/3 (Sat), 7/4 (Sun), 7/5 (Mon), 7/6 (Tue), 7/7 (Wed)
// July 4th 2021 is Sunday, observed Monday 7/5
// 7/3 (Sat-weekend) = NOT charged
// 7/4 (Sun-weekend) = NOT charged
// 7/5 (Mon-observed holiday) = charged (chainsaw charges holidays)
// 7/6 (Tue-weekday) = charged
// 7/7 (Wed-weekday) = charged
// Chargeable days: 3
    public static final RentalTerm CHNS_JULY_4TH_SUNDAY_2021 =
            new RentalTerm(ToolCodeEnum.CHNS, LocalDate.of(2021, 7, 2), 5, 0);

    // Independence Day on Saturday (7/4/15), observed Friday (7/3/15) - Jackhammer
// Checkout 7/1 (Wed), Rental period: 7/2 (Thu), 7/3 (Fri), 7/4 (Sat), 7/5 (Sun)
// July 4th 2015 is Saturday, observed Friday 7/3
// 7/2 (Thu-weekday) = charged
// 7/3 (Fri-observed holiday) = NOT charged (jackhammer doesn't charge holidays)
// 7/4 (Sat-weekend) = NOT charged
// 7/5 (Sun-weekend) = NOT charged
// Chargeable days: 1
    public static final RentalTerm JAKR_JULY_4TH_SATURDAY_2015 =
            new RentalTerm(ToolCodeEnum.JAKR, LocalDate.of(2015, 7, 1), 4, 0);

    // Independence Day on Sunday (7/4/21), observed Monday (7/5/21) - Jackhammer
// Checkout 7/2 (Fri), Rental period: 7/3 (Sat), 7/4 (Sun), 7/5 (Mon), 7/6 (Tue)
// July 4th 2021 is Sunday, observed Monday 7/5
// 7/3 (Sat-weekend) = NOT charged
// 7/4 (Sun-weekend) = NOT charged
// 7/5 (Mon-observed holiday) = NOT charged (jackhammer doesn't charge holidays)
// 7/6 (Tue-weekday) = charged
// Chargeable days: 1
    public static final RentalTerm JAKR_JULY_4TH_SUNDAY_2021 =
            new RentalTerm(ToolCodeEnum.JAKR, LocalDate.of(2021, 7, 2), 4, 0);

    // Labor Day 2015 (9/7 Monday) - Jackhammer
// Checkout 9/4 (Fri), Rental period: 9/5 (Sat), 9/6 (Sun), 9/7 (Mon), 9/8 (Tue), 9/9 (Wed)
// Labor Day is always first Monday in September, no observation rule
// 9/5 (Sat-weekend) = NOT charged
// 9/6 (Sun-weekend) = NOT charged
// 9/7 (Mon-Labor Day holiday) = NOT charged
// 9/8 (Tue-weekday) = charged
// 9/9 (Wed-weekday) = charged
// Chargeable days: 2
    public static final RentalTerm JAKD_LABOR_DAY_2015 =
            new RentalTerm(ToolCodeEnum.JAKD, LocalDate.of(2015, 9, 4), 5, 0);

// ==================== ROUNDING TEST PAYLOADS ====================

    // Test rounding - 33% of $5.98 = $1.9734, should round to $1.97
// Checkout 1/6 (Mon), Rental period: 1/7 (Tue), 1/8 (Wed)
// 1/7 (Tue-weekday) = charged
// 1/8 (Wed-weekday) = charged
// Chargeable days: 2 at $2.99 = $5.98
    public static final RentalTerm JAKD_ROUNDING_TEST =
            new RentalTerm(ToolCodeEnum.JAKD, LocalDate.of(2025, 1, 6), 2, 33);

// ==================== DATE CALCULATION TEST PAYLOADS ====================

    // Due date calculation - Checkout 1/1, 5 rental days, due date 1/6
    public static final RentalTerm LADW_DUE_DATE_TEST =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2025, 1, 1), 5, 0);

    // Month boundary - Checkout Jan 30, 5 days, due Feb 4
    public static final RentalTerm LADW_MONTH_BOUNDARY =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2025, 1, 30), 5, 0);

    // Year boundary - Checkout Dec 30, 5 days, due Jan 4 next year
    public static final RentalTerm LADW_YEAR_BOUNDARY =
            new RentalTerm(ToolCodeEnum.LADW, LocalDate.of(2024, 12, 30), 5, 0);
}

