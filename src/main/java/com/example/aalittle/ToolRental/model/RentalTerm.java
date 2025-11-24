package com.example.aalittle.ToolRental.model;

import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data class which holds all fields needed to rent an item
 */
@Data
@AllArgsConstructor
public class RentalTerm {

    @NotNull(message = "Please enter a valid tool code enum")
    private ToolCodeEnum toolCode;

    @NotNull
    @JsonFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDate;

    @NotNull(message = "Rental Days Must Be Entered")
    @Min(value = 1, message = "Rental day count must be 1 or more")
    private Integer rentalDayCount;

    @Min(value = 0, message = "Discount Percent must be 0 or more")
    @Max(value = 100, message = "Discount Percent must be 100 or under")
    private int discountPercent;

}
