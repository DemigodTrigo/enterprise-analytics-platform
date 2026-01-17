package com.enterprise.analytics.masterdata.contract;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnalyticsFilterCriteria {


    private LocalDate fromDate;
    private LocalDate toDate;

    private String product;
    private String status;
    private String country;

    private String channel;
    private String customerType;
}
