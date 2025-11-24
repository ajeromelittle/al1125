package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.model.RentalAgreement;
import com.example.aalittle.ToolRental.model.RentalTerm;
import com.example.aalittle.ToolRental.model.ToolData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import de.focus_shift.jollyday.core.Holiday;

/**
 * Service responsible for calculating tool rental information
 */
@Service
public class ToolRentalCalculationService {

    @Autowired
    private ToolDataRetrievalService toolDataRetrievalService;

    @Autowired
    private HolidayService holidayService;

    /**
     * Calculates the price for renting a tool(s)
     * @param rentalTerm a {@link RentalTerm}
     * @return a {@link List} of {@link RentalAgreement}
     */
    public RentalAgreement calculateToolRental(final RentalTerm rentalTerm){
        /// Notes for future improvement
        /// IF we have the SAME rental terms (e.g. 2 tools for the same dates) then we only need to do calculation once
        /// RATHER than calling the fairly heavy holidayService.findHolidaysInRange(rentalStartDate,rentalEndDate)
        /// we could get once and store in map?
        ///Add logging vs basic system.println
        /// Does not account for rental terms more than `~100 years`
            LocalDate rentalStartDate = rentalTerm.getCheckoutDate();
            LocalDate rentalEndDate = rentalStartDate.plusDays(rentalTerm.getRentalDayCount());

            //Retrieve Tool Data
            ToolData toolData = toolDataRetrievalService.retrieveToolData(rentalTerm.getToolCode());

            //First Day is not included in rental terms
            //Last day is exclusive in datesUntil documentation
            Set<LocalDate> allRentalDays = rentalStartDate.plusDays(1).datesUntil
                    (rentalEndDate.plusDays(1)).collect(Collectors.toSet());
            //Remove all holidays from set if applicable
            Set<LocalDate> chargeableDays = new HashSet<>(allRentalDays);
            if(!toolData.isHolidayCharge()){
                Set<Holiday> holidaysInRange = holidayService.findHolidaysInRange(rentalStartDate,rentalEndDate);
                for(Holiday holiday : holidaysInRange){
                    chargeableDays.remove(holiday.getDate());
                }
            }
            //Remove all weekends from set if applicable
            if(!toolData.isWeekendCharge()) {
                chargeableDays = chargeableDays.stream().filter(localDate -> localDate.getDayOfWeek() != DayOfWeek.SATURDAY
                        && localDate.getDayOfWeek() != DayOfWeek.SUNDAY).collect(Collectors.toSet());
            }

            return calculateRentalAgreement(toolData, allRentalDays.size(),
                    rentalStartDate, rentalEndDate,
                    chargeableDays.size(), rentalTerm);

    }

    private RentalAgreement calculateRentalAgreement(final ToolData toolData, final int allRentalDays,
                                                      final LocalDate rentalStartDate, final LocalDate rentalEndDate,
                                                      final int chargeableDays, final RentalTerm rentalTerm) {

        BigDecimal subTotal = toolData.getDailyCharge().multiply(BigDecimal.valueOf(chargeableDays))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAsDecimal = BigDecimal.valueOf(((double) rentalTerm.getDiscountPercent() / (double)100 ));
        BigDecimal discountAsValue = discountAsDecimal.multiply(subTotal).setScale(2, RoundingMode.HALF_UP);

        BigDecimal discountedTotal = subTotal.subtract(discountAsValue);
        RentalAgreement rentalAgreement =  RentalAgreement.builder()
                .toolCode(rentalTerm.getToolCode())
                .toolType(toolData.getToolType())
                .toolBrand(toolData.getToolBrand())
                .rentalDays(allRentalDays)
                .checkoutDate(rentalStartDate)
                .dueDate(rentalEndDate)
                .chargeableDays(chargeableDays)
                .dailyCharge(toolData.getDailyCharge())
                .subTotal(subTotal)
                .discountPercent(discountAsDecimal.multiply(BigDecimal.valueOf(100)))
                .discountAmount(discountAsValue)
                .discountCharge(discountedTotal).build();
        rentalAgreement.printToConsole();
        return rentalAgreement;
    }
}
