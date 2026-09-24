package com.skybook.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

