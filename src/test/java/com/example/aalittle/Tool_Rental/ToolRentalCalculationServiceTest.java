package com.example.aalittle.Tool_Rental;

import com.example.aalittle.ToolRental.service.HolidayService;
import com.example.aalittle.ToolRental.service.ToolRentalCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToolRentalCalculationServiceTest {



    private ToolRentalCalculationService toolRentalCalculationService;
    @BeforeEach
    public void setup(){
        toolRentalCalculationService = new ToolRentalCalculationService();
    }

    @Test
    public void setToolRentalCalculationServiceWithEmptyList_returnsEmpty() {
        assertTrue(toolRentalCalculationService.calculateToolRental(new ArrayList<>()).isEmpty());
    }
}
