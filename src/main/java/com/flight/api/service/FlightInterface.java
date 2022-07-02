package com.flight.api.service;

import com.flight.api.entities.FlightData;
import com.flight.api.model.FlightInfo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * This interface is being used to auto generate the mapping from entity to model using MapStruct
 */
@Mapper(componentModel = "spring")
public interface FlightInterface {
    List<FlightInfo> getFlightInfo(List<FlightData> listFlightData);
}
