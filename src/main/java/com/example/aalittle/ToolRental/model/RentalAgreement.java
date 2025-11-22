package com.example.aalittle.ToolRental.model;

import com.example.aalittle.ToolRental.enums.ToolBrandEnum;
import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.enums.ToolTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data class which holds all fields necessary to create a rental agreement
 */
@Builder
@Data
public class RentalAgreement {

    private ToolCodeEnum toolCode;
    private ToolTypeEnum toolType;
    private ToolBrandEnum toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private int chargeableDays;
    private BigDecimal subTotal;
    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal discountCharge;

}
