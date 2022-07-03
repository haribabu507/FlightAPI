package com.flight.api.service;

import com.flight.api.entities.FlightData;
import com.flight.api.repository.FlightRepository;
import com.flight.api.repository.FlightSortRepository;
import com.flight.api.util.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightServiceTest {
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;

    @Autowired EntityManager entityManager;

    FlightSortRepository flightSortRepository = new FlightSortRepository(entityManager);

    @BeforeEach
    void setup() {
        flightService = new FlightService(flightRepository, flightSortRepository);
    }

    @Test
    @DisplayName("Retrieving the flight details using mock repository")
    public void getFlightDetailsTest() {
        List<FlightData> data = flightService.getFlightDetails("AMS", "DEL");
        assertThat(data.size(), is(1));
        assertThat(data.get(0).getFlightNumber(), is("A101"));
    }
}
