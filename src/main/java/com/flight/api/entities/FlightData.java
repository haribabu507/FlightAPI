package com.flight.api.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class FlightData {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(unique = true)
    private String flightNumber;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column
    private Timestamp arrivalTime;
    @Column
    private Timestamp depatureTime;
    @Column
    private Integer price;
    @Column
    private String currency;

}
