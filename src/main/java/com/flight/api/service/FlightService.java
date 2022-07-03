package com.flight.api.service;

import com.flight.api.entities.FlightData;
import com.flight.api.exception.FlightAPICustomException;
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
     * To get the flight details using origin and destination
     * @param origin
     * @param destination
     * @return
     */
    public List<FlightData> getFlightDetails(String origin, String destination) {
        try {
            return repository.searchAllByOriginAndDestination(origin, destination);
        } catch (Exception e) {
            throw new FlightAPICustomException(e.getMessage());
        }
    }

    /**
     * to get the sorted flight details with given filters
     * @param info
     * @return
     */
    public List<FlightData> getSortedFlightDetails(FilterInfo info) {
        try{
            return sortRepository.sortedFlightInfo(info);
        } catch (Exception e) {
            throw new FlightAPICustomException(e.getMessage());
        }
    }
}
