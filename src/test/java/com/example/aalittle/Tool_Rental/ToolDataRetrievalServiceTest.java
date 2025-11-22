package com.example.aalittle.Tool_Rental;

import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.service.ToolDataRetrievalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolDataRetrievalServiceTest {

    private ToolDataRetrievalService toolDataRetrievalService;

    @BeforeEach
    public void setup() {
        toolDataRetrievalService = new ToolDataRetrievalService();
    }

    @Test
    public void testRetrieveToolDataWithNullCode_returnsNull() {
        assertEquals(null, toolDataRetrievalService.retrieveToolData(null));
    }

    @Test
    public void testRetrieveToolDataWithValidCode_returnsData() {
        assertNotNull(toolDataRetrievalService.retrieveToolData(ToolCodeEnum.JAKD));
    }
}
