package com.example.aalittle.ToolRental.model;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TotalRentalAgreement {

    List<RentalAgreement> rentalAgreementList;

    public TotalRentalAgreement(List<RentalAgreement> rentalAgreementList){
        this.rentalAgreementList = rentalAgreementList;
    }


    public String toString(){
        return rentalAgreementList.stream()
                .map(RentalAgreement::toString).collect(Collectors.joining(", "));

    }
}
