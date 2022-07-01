package com.flight.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        String data = "A101|AMS|DEL|11:00|17:00|850\n" +
                "B101|AMS|BOM|12:00|19:30|750\n" +
                "C101|AMS|BLR|13:00|18:30|800\n" +
                "D101|AMS|MAA|09:00|15:00|600\n" +
                "E101|MAA|BOM|16:00|17:30|100\n" +
                "F101|BOM|DEL|20:30|21:30|80\n" +
                "G101|BOM|DEL|18:00|19:30|100\n" +
                "A201|LHR|DEL|11:30|17:00|600\n" +
                "B201|LHR|BOM|12:35|19:30|750\n" +
                "C201|LHR|BLR|13:45|18:30|800\n" +
                "D201|LHR|MAA|09:00|15:00|600\n" +
                "E201|DEL|BOM|18:45|20:15|100\n" +
                "F201|BOM|DEL|21:15|22:30|80\n" +
                "G01|BOM|DEL|20:20|22:30|100";

        Test t = new Test();
        List<FlightInfo> info = t.getFlight(data);

        Gson mapper = new Gson();
        //mapper.registerModule(new Jdk8Module());
        System.out.println(mapper.toJson(info));
    }

    private List<FlightInfo> getFlight(String data) {
        return data.lines()
                .map(s -> flight(s.split("\\|")))
                .collect(Collectors.toList());
    }

    private FlightInfo flight(String[] arr) {
        System.out.println("@@@@@@"+arr[0]);
        FlightInfo info = new FlightInfo();
        info.setId(UUID.randomUUID());
        info.setFlightNumber(arr[0]);
        info.setOrigin(arr[1]);
        info.setDestination(arr[2]);
        System.out.println(arr[3]);
        System.out.println(new Date());
        info.setDepatureTime(new Date());
        info.setArrivalTime(new Date());
        info.setPrice(Integer.valueOf(arr[5]));
        return info;
    }

}
