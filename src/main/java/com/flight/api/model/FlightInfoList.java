package com.flight.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class FlightInfoList {
    List<FlightInfo> flightList;
}
