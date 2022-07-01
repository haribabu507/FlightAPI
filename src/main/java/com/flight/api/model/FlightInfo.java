package com.flight.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class FlightInfo {
    private UUID id;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date depatureTime;
    private Date arrivalTime;
    private Integer price;
    private String currency;
}
