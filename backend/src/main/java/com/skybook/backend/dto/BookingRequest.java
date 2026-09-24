package com.skybook.backend.dto;

import lombok.Data;

@Data
public class BookingRequest {

    private Long flightId;

    private String seatNumber;

    private String firstName;

    private String lastName;

    private Integer age;
}
