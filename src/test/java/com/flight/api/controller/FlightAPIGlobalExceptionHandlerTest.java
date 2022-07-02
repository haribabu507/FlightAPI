package com.flight.api.controller;

import com.flight.api.exception.FlightAPICustomException;
import com.flight.api.service.FlightInterface;
import com.flight.api.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FlightAPIGlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @MockBean
    FlightService flightService;
    @MockBean
    FlightInterface flightInterface;

    FlightController controller;

    @BeforeEach
    void setup() {
        controller = new FlightController(flightService, flightInterface);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new FlightAPIGlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Test RestGolbalAdvice annotation")
    public void exceptionHandlerTest() throws Exception {
        when(controller.getFlightDetails("AMS", "DEL")).thenThrow(new FlightAPICustomException("Failed to get Flight details"));
        mockMvc.perform(MockMvcRequestBuilders.get("/flightApi/getFlightInfo"))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
}
