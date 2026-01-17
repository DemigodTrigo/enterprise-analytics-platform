package com.enterprise.analytics.masterdata.contract;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ChartSeries {

    private String name;
    private String color;
    private Map<String, Number> data;

}
