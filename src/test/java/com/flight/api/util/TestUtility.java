package com.flight.api.util;

import com.flight.api.entities.FlightData;
import com.flight.api.model.FilterInfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtility {

    public static FlightData prepareFlightData() {
        FlightData flightData = new FlightData();
        flightData.setId(UUID.randomUUID());
        flightData.setFlightNumber("A101");
        flightData.setOrigin("AMS");
        flightData.setDestination("DEL");
        flightData.setDepatureTime(Timestamp.valueOf("2022-06-30 11:00:00"));
        flightData.setArrivalTime(Timestamp.valueOf("2022-06-30 17:00:00"));
        flightData.setPrice(850);
        flightData.setCurrency("EURO");
        return flightData;
    }

    public static List<FlightData> prepareListOfFlights() {
        List<FlightData> listOfData = new ArrayList<>();
        listOfData.add(prepareFlightData());

        return listOfData;
    }

    public static FilterInfo prepareSearchFilter() {
        FilterInfo info = new FilterInfo();
        info.setDepatureTime(Timestamp.valueOf("2022-06-30 11:00:00"));
        info.setArrivalTime(Timestamp.valueOf("2022-06-30 17:00:00"));
        info.setSortingOrder("asc");

        return info;
    }
}
