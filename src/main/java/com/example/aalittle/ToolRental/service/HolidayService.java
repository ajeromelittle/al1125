package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.utils.HolidayUtils;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES;

/**
 * Service responsible providing information on Holidays
 */
@Service
public class HolidayService {
    /**
     * Finds all holidays in a given data range
     * @param startDate the start of the date range to find holidays (inclusive)
     * @param endDate the end of the date range to find holidays (inclusive)
     * @return a {@link Set} of {@link Holiday}
     */
    public Set<Holiday> findHolidaysInRange(final LocalDate startDate, final LocalDate endDate){
        ///Assumption that hardware store is US only
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(UNITED_STATES));

        Set<Holiday> holidays = holidayManager.getHolidays(startDate, endDate);
        return holidays.stream().filter( holiday ->
                HolidayUtils.HOLIDAY_SET.contains(holiday.getDescription()
                        .toLowerCase())).collect(Collectors.toSet());

    }
}
