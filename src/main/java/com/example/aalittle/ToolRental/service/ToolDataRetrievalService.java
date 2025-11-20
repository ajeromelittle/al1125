package com.example.aalittle.ToolRental.service;

import com.example.aalittle.ToolRental.enums.ToolBrandEnum;
import com.example.aalittle.ToolRental.enums.ToolCodeEnum;
import com.example.aalittle.ToolRental.enums.ToolTypeEnum;
import com.example.aalittle.ToolRental.model.ToolData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ToolDataRetrievalService {

    private Map<ToolCodeEnum, ToolData> toolCodeMap = new HashMap<>() {{
        put(ToolCodeEnum.CHNS, new ToolData(ToolTypeEnum.CHAINSAW,ToolBrandEnum.STIHL, BigDecimal.valueOf(1.49),
                true, false, true));
        put(ToolCodeEnum.JAKD, new ToolData(ToolTypeEnum.JACKHAMMER, ToolBrandEnum.DEWALT,
                BigDecimal.valueOf(2.99), true, false, false));
        put(ToolCodeEnum.JAKR, new ToolData(ToolTypeEnum.JACKHAMMER, ToolBrandEnum.RIGID,
                BigDecimal.valueOf(2.99), true, false, false));
        put(ToolCodeEnum.LADW, new ToolData(ToolTypeEnum.LADDER, ToolBrandEnum.WERNER,
                BigDecimal.valueOf(1.99), true, true, false));
    }};


    public ToolData retrieveToolData(final ToolCodeEnum toolCodeEnum){
        return toolCodeMap.get(toolCodeEnum);
    }
}
