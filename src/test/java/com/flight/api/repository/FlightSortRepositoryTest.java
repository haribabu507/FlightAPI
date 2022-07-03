package com.flight.api.repository;

import com.flight.api.entities.FlightData;
import com.flight.api.util.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightSortRepositoryTest {

    private FlightSortRepository flightSortRepository;

    @BeforeEach
    void setup() {
        flightSortRepository = new FlightSortRepository(null);
    }

    @Test
    public void getFlightDataTest() {
        List<FlightData> data = flightSortRepository.sortedFlightInfo(TestUtility.prepareSearchFilter());
        assertThat(data.size(), is(2));
    }
}
