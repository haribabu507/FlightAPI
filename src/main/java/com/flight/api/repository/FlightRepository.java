package com.flight.api.repository;

import com.flight.api.entities.FlightData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<FlightData, UUID> {

    /**
     * To retrieve the list of flight with origin and destination
     * @param origin
     * @param destination
     * @return
     */
    List<FlightData> searchAllByOriginAndDestination(String origin, String destination);

}
