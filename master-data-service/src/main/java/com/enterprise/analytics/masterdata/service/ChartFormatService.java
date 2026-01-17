package com.enterprise.analytics.masterdata.service;

import org.springframework.stereotype.Service;

@Service
public class ChartFormatService
{
    public String detectFormat(double maxValue){
        if (maxValue >= 1_000_000) return "M";
        if (maxValue >= 1_000) return "K";
        return "RAW";
    }

    public double format(double value, String format) {
        return switch (format) {
            case "M" -> value / 1_000_000;
            case "K" -> value / 1_000;
            default -> value;
        };
    }
}
