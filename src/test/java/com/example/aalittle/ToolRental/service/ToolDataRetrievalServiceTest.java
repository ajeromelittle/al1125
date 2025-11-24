package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToolDataRetrievalServiceTest {

    @Autowired
    private ToolDataRetrievalService toolDataRetrievalService;

    @Test
    public void testRetrieveToolDataWithNullCode_returnsNull() {
        assertEquals(null, toolDataRetrievalService.retrieveToolData(null));
    }

    @Test
    public void testRetrieveToolDataWithValidCode_returnsData() {
        assertNotNull(toolDataRetrievalService.retrieveToolData(ToolCodeEnum.JAKD));
    }
}
