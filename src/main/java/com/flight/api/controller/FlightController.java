package com.flight.api.controller;

import com.flight.api.entities.FlightData;
import com.flight.api.model.FilterInfo;
import com.flight.api.model.FlightInfo;
import com.flight.api.model.FlightInfoList;
import com.flight.api.service.FlightInterface;
import com.flight.api.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FlightController {

    private final FlightService flightService;
    private final FlightInterface flightInterface;

    public FlightController(FlightService flightService, FlightInterface flightInterface) {
        this.flightService = flightService;
        this.flightInterface = flightInterface;
    }

    @PostMapping(path = "/flightApi/addFlight", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<FlightData>> addNewFlight(@RequestBody FlightInfoList data) throws Exception {
        return ResponseEntity.ok(flightService.addFlight(flightInterface.addFlights(data.getFlightList())));
    }

    @GetMapping(path = "/flightApi/getFlightInfo")
    public ResponseEntity<FlightInfoList> getFlightDetails(@RequestParam(name = "origin", required = false) String origin,
                                             @RequestParam(name = "destination", required = false) String destination) throws Exception {

        FlightInfoList flightInfoList = new FlightInfoList();
        flightInfoList.setFlightList(flightInterface.getFlightInfo(flightService.getFlightDetails(origin, destination)));
        return ResponseEntity.ok(flightInfoList);
    }

    @PostMapping(path = "/flightApi/getSortedFlightInfo")
    public ResponseEntity<FlightInfoList> getSortedFlightInfo(@RequestBody FilterInfo info) throws Exception {
        FlightInfoList flightInfoList = new FlightInfoList();
        flightInfoList.setFlightList(flightInterface.getFlightInfo(flightService.getSortedFlightDetails(info)));
        return ResponseEntity.ok(flightInfoList);
    }
}
