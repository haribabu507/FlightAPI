package com.flight.api.controller;

import com.flight.api.exception.FlightAPICustomException;
import com.flight.api.model.FilterInfo;
import com.flight.api.model.FlightInfoList;
import com.flight.api.service.FlightInterface;
import com.flight.api.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FlightController {

    private final FlightService flightService;
    private final FlightInterface flightInterface;

    public FlightController(FlightService flightService, FlightInterface flightInterface) {
        this.flightService = flightService;
        this.flightInterface = flightInterface;
    }

    @GetMapping(path = "/flightApi/getFlightInfo")
    public ResponseEntity<FlightInfoList> getFlightDetails(@RequestParam(name = "origin") String origin,
                                             @RequestParam(name = "destination") String destination) {

        if (origin == null || origin.isBlank()) {
            throw new FlightAPICustomException("Origin is required");
        }

        if (destination == null || destination.isBlank()) {
            throw new FlightAPICustomException("Destination is required");
        }
        FlightInfoList flightInfoList = new FlightInfoList();
        flightInfoList.setFlightList(flightInterface.getFlightInfo(flightService.getFlightDetails(origin, destination)));
        return ResponseEntity.ok(flightInfoList);
    }

    @PostMapping(path = "/flightApi/getSortedFlightInfo")
    public ResponseEntity<FlightInfoList> getSortedFlightInfo(@RequestBody FilterInfo info) {
        FlightInfoList flightInfoList = new FlightInfoList();
        flightInfoList.setFlightList(flightInterface.getFlightInfo(flightService.getSortedFlightDetails(info)));
        return ResponseEntity.ok(flightInfoList);
    }
}
