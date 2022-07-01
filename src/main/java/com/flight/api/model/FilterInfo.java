package com.flight.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class FilterInfo {
    private Date depatureTime;
    private Date arrivalTime;
    private Integer price;
    private String sortingOrder;
}
