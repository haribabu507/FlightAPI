package com.flight.api.controller;

import com.flight.api.model.FilterInfo;
import com.flight.api.model.FlightInfoList;
import com.flight.api.service.FlightInterface;
import com.flight.api.service.FlightService;
import com.flight.api.util.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {
    FlightController flightController;

    @Mock FlightService flightServiceMock;
    FlightInterface flightInterface = Mappers.getMapper(FlightInterface.class);

    @BeforeEach
    void setup() {
        flightController = new FlightController(flightServiceMock, flightInterface);
    }

    @Test
    @DisplayName("Test to retrieve the FLight details with origin and destination")
    public void getFlightDetailsTest() {

        when(flightServiceMock.getFlightDetails(any(String.class), any(String.class))).thenReturn(TestUtility.prepareListOfFlights());

        ResponseEntity<FlightInfoList> responseEntity = flightController.getFlightDetails("AMS", "DEL");
        assertThat(responseEntity.getBody().getFlightList().size(), is(1));
        assertThat(responseEntity.getBody().getFlightList().get(0).getFlightNumber(), is("A101"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getOrigin(), is("AMS"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getDestination(), is("DEL"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getDepatureTime(), is(Timestamp.valueOf("2022-06-30 11:00:00")));
        assertThat(responseEntity.getBody().getFlightList().get(0).getArrivalTime(), is(Timestamp.valueOf("2022-06-30 17:00:00")));
        assertThat(responseEntity.getBody().getFlightList().get(0).getPrice(), is(850));
        assertThat(responseEntity.getBody().getFlightList().get(0).getCurrency(), is("EURO"));

    }

    @Test
    @DisplayName("Test to retrieve the sorted flight details")
    public void getSortedFlightDetailsTest() {
        FilterInfo info = TestUtility.prepareSearchFilter();
        when(flightServiceMock.getSortedFlightDetails(any(FilterInfo.class))).thenReturn(TestUtility.prepareListOfFlights());

        ResponseEntity<FlightInfoList> responseEntity = flightController.getSortedFlightInfo(info);

        assertThat(responseEntity.getBody().getFlightList().size(), is(1));
        assertThat(responseEntity.getBody().getFlightList().get(0).getFlightNumber(), is("A101"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getOrigin(), is("AMS"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getDestination(), is("DEL"));
        assertThat(responseEntity.getBody().getFlightList().get(0).getDepatureTime(), is(Timestamp.valueOf("2022-06-30 11:00:00")));
        assertThat(responseEntity.getBody().getFlightList().get(0).getArrivalTime(), is(Timestamp.valueOf("2022-06-30 17:00:00")));
        assertThat(responseEntity.getBody().getFlightList().get(0).getPrice(), is(850));
        assertThat(responseEntity.getBody().getFlightList().get(0).getCurrency(), is("EURO"));
    }
}
