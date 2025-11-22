package com.example.aalittle.ToolRental.controller;

import com.example.aalittle.ToolRental.model.RentalAgreement;
import com.example.aalittle.ToolRental.model.RentalTerm;
import com.example.aalittle.ToolRental.model.TotalRentalAgreement;
import com.example.aalittle.ToolRental.service.ToolRentalCalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Rental Controller Class which contains APIs for renting items
 */
@RestController
public class RentalController {

    /**
     * Entry point for rental items calculation
     * @param a List of  {@link RentalTerm}
     * @return a {@link TotalRentalAgreement}
     */
    @Autowired
    ToolRentalCalculationService toolRentalCalculationService;
    @PostMapping( value = "/api/rentItems/")
    public TotalRentalAgreement rentItems(@RequestBody List<@Valid RentalTerm> rentalTerms){
        System.out.println("Hit API");
       return new TotalRentalAgreement(toolRentalCalculationService.calculateToolRental(rentalTerms));
    }
}
