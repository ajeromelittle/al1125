package com.example.aalittle.ToolRental.model;

import com.example.aalittle.ToolRental.enums.ToolBrandEnum;
import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.enums.ToolTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void printToConsole(){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("MM/dd/yy");
        System.out.println( new StringBuilder()
               .append("Tool code: ").append(toolCode).append("\n")
               .append("Tool type: ").append(toolType).append("\n")
               .append("Tool brand: ").append(toolBrand).append("\n")
               .append("Rental days: ").append(rentalDays).append("\n")
               .append("Checkout date: ").append(checkoutDate.format(formatter)).append("\n")
               .append("Due date: ").append(dueDate.format(formatter)).append("\n")
               .append("Daily rental charge: ").append(NumberFormat.getCurrencyInstance().format(dailyCharge)).append("\n")
               .append("Charge days: ").append(chargeableDays).append("\n")
               .append("Pre-discount charge: ").append(NumberFormat.getCurrencyInstance().format(subTotal)).append("\n")
               .append("Discount percent: ").append(discountPercent.stripTrailingZeros().toPlainString()).append("%").append("\n")
               .append("Discount amount: ").append(NumberFormat.getCurrencyInstance().format(discountAmount)).append("\n")
               .append("Final charge: ").append(NumberFormat.getCurrencyInstance().format(discountCharge)).append("\n").toString());

    }
}
