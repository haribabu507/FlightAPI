package com.flight.api.service;

import com.flight.api.entities.FlightData;
import com.flight.api.model.FilterInfo;
import com.flight.api.repository.FlightRepository;
import com.flight.api.repository.FlightSortRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FlightService {

    private final FlightRepository repository;
    private final FlightSortRepository sortRepository;

    public FlightService(FlightRepository repository, FlightSortRepository sortRepository) {
        this.repository = repository;
        this.sortRepository = sortRepository;
    }

    /**
     *
     * @param data
     * @return
     */
    public List<FlightData> addFlight(List<FlightData> data) {
        return repository.saveAll(data);
    }

    /**
     * To get the flight details using origin and destination
     * @param origin
     * @param destination
     * @return
     */
    public List<FlightData> getFlightDetails(String origin, String destination) {
        return repository.searchAllByOriginOrDestination(origin, destination);
    }

    /**
     * to get the sorted flight details with given filters
     * @param info
     * @return
     */
    public List<FlightData> getSortedFlightDetails(FilterInfo info) {
        return sortRepository.sortedFlightInfo(info);
    }
}
