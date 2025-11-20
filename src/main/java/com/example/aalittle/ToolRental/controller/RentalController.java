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

@RestController
public class RentalController {

    @Autowired
    ToolRentalCalculationService toolRentalCalculationService;
    @PostMapping( value = "/api/rentItems/")
    public TotalRentalAgreement rentItems(@Valid @RequestBody List<RentalTerm> rentalTerms){
       return new TotalRentalAgreement(toolRentalCalculationService.calculateToolRental(rentalTerms));
    }
}
