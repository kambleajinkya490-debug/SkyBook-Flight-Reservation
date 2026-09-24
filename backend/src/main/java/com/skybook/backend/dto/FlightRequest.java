package com.skybook.backend.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {

    private String flightNumber;
    private String airline;
    private String source;
    private String destination;

    private LocalDate flightDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private Double price;

    private Integer totalSeats;
}
