package com.example.aalittle.ToolRental.model;

import com.example.aalittle.ToolRental.enums.ToolBrandEnum;
import com.example.aalittle.ToolRental.enums.ToolTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ToolData {

     private ToolTypeEnum toolType;
     private ToolBrandEnum toolBrand;
     private BigDecimal dailyCharge;
     private boolean weekdayCharge;
     private boolean weekendCharge;
     private boolean holidayCharge;

     public ToolData(final ToolTypeEnum toolType, final ToolBrandEnum toolBrand, final BigDecimal dailyCharge,
             boolean weekdayCharge, final boolean weekendCharge, final boolean holidayCharge ){
         this.toolType = toolType;
         this.toolBrand = toolBrand;
         this.dailyCharge = dailyCharge;
         this.weekdayCharge = weekdayCharge;
         this.weekendCharge = weekendCharge;
         this.holidayCharge = holidayCharge;

     }
}
