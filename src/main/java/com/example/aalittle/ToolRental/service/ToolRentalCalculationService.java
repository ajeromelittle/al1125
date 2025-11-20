package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.model.RentalAgreement;
import com.example.aalittle.ToolRental.model.RentalTerm;
import com.example.aalittle.ToolRental.model.ToolData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import de.focus_shift.jollyday.core.Holiday;

@Service
public class ToolRentalCalculationService {

    @Autowired
    private ToolDataRetrievalService toolDataRetrievalService;

    @Autowired
    private HolidayService holidayService;

    public List<RentalAgreement> calculateToolRental(final List<RentalTerm> rentalTermsList){

        /// Notes for future improvement
        /// IF we have the SAME rental terms (e.g. 2 tools for the same dates) then we only need to do calculation once
        /// RATHER than calling the fairly heavy holidayService.findHolidaysInRange(rentalStartDate,rentalEndDate)
        /// we could get once and store in map?
        /// Iterations through the rentalTerms List could be done async
        ///(especially if the necessary operations ever get more data heavy)
        return rentalTermsList.stream().map( rentalTerm -> {
            /// CALCULATE ALL rental dates as a set
            LocalDate rentalStartDate = rentalTerm.getCheckoutDate();
            LocalDate rentalEndDate = rentalStartDate.plusDays(rentalTerm.getRentalDayCount());

            //Retrieve Tool Data
            ToolData toolData = toolDataRetrievalService.retrieveToolData(rentalTerm.getToolCodeEnum());
            Set<LocalDate> allRentalDays = rentalStartDate.datesUntil
                    (rentalEndDate).collect(Collectors.toSet());

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

            //What is left will be our subtotal
            BigDecimal subTotal = toolData.getDailyCharge().multiply(BigDecimal.valueOf(chargeableDays.size()));
            BigDecimal discountAsPercent = BigDecimal.valueOf(rentalTerm.getDiscountPercent() / 100 );
            BigDecimal discountAsValue = discountAsPercent.multiply(subTotal);
            BigDecimal discountedTotal = subTotal.subtract(discountAsValue);

            return RentalAgreement.builder()
                    .toolCode(rentalTerm.getToolCodeEnum())
                    .toolType(toolData.getToolType())
                    .toolBrand(toolData.getToolBrand())
                    .rentalDays(allRentalDays.size())
                    .checkoutDate(rentalStartDate)
                    .dueDate(rentalEndDate)
                    .chargeableDays(chargeableDays.size())
                    .subTotal(subTotal)
                    .discountPercent(discountAsPercent)
                    .discountAmount(discountAsValue)
                    .discountCharge(discountedTotal).build();
        } ).toList();
    }
}
