package com.example.aalittle.Tool_Rental;

import com.example.aalittle.ToolRental.service.HolidayService;
import de.focus_shift.jollyday.core.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static com.example.aalittle.ToolRental.utils.HolidayUtils.HOLIDAY_SET;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HolidayServiceTest {

    private HolidayService holidayService;

    @BeforeEach
    public void setup(){
        holidayService = new HolidayService();
    }

    @Test
    public void testFindHolidaysInRangeWithNullDates_throwsException() {
        assertThrows( NullPointerException.class,() -> holidayService.findHolidaysInRange(null,null));
    }

    @Test
    public void testFindHolidaysInRangeWithStartAfterEnd_returnsEmpty() {
        LocalDate startDate = LocalDate.of(2026,7,1);
        LocalDate endDate = LocalDate.of(2025,7,30);
        Set<Holiday> holidayList = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidayList.isEmpty());
    }

    @Test
    public void testHolidayServiceWithIndependenceDayDates_returnsHoliday() {
        LocalDate startDate = LocalDate.of(2025,7,1);
        LocalDate endDate = LocalDate.of(2025,7,30);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.stream()
                .anyMatch(holiday -> HOLIDAY_SET.contains(holiday.getDescription().toLowerCase())));
    }

    @Test
    public void testHolidayServiceWithIndependenceDayOnSaturday_returnsHolidayOnFriday() {
        LocalDate startDate = LocalDate.of(2020,7,2);
        LocalDate endDate = LocalDate.of(2025,7,5);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.stream()
                .anyMatch(holiday -> holiday.getDate().equals(LocalDate.of(2020,7,3))));
    }

    @Test
    public void testHolidayServiceWithIndependenceDayOnSunday_returnsHolidayOnMonday() {
        LocalDate startDate = LocalDate.of(2021,7,2);
        LocalDate endDate = LocalDate.of(2021,7,5);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.stream()
                .anyMatch(holiday -> holiday.getDate().equals(LocalDate.of(2021,7,5))));
    }

    @Test
    public void testHolidayServiceWithLabourDayDates_returnsHoliday() {
        LocalDate startDate = LocalDate.of(2025,9,1);
        LocalDate endDate = LocalDate.of(2025,9,30);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.stream()
                .anyMatch(holiday -> HOLIDAY_SET.contains(holiday.getDescription().toLowerCase())));
    }

    @Test
    public void testHolidayServiceWithSameHolidayDates_returnsHoliday() {
        LocalDate startDate = LocalDate.of(2025,7,4);
        LocalDate endDate = LocalDate.of(2025,7,4);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.stream()
                .anyMatch(holiday -> HOLIDAY_SET.contains(holiday.getDescription().toLowerCase())));
    }

    @Test
    public void testHolidayServiceWithNoHolidayDates_returnsEmpty() {
        LocalDate startDate = LocalDate.of(2025,8,1);
        LocalDate endDate = LocalDate.of(2025,8,30);
        Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(startDate,endDate);
        assertTrue(holidaysInRange.isEmpty());
    }
}
