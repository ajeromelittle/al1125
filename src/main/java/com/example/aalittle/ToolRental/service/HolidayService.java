package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.utils.HolidayUtils;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES;

@Service
public class HolidayService {

    public Set<Holiday> findHolidaysInRange(final LocalDate startDate, final LocalDate endDate){
        ///Assumption that hardware store is US only
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(UNITED_STATES));

        Set<Holiday> holidays = holidayManager.getHolidays(startDate, endDate);
        return holidays.stream().filter( holiday ->
                !HolidayUtils.HOLIDAY_SET.contains(holiday.getDescription()
                        .toLowerCase())).collect(Collectors.toSet());

    }
}
