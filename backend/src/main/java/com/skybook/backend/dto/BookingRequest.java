package com.skybook.backend.dto;

import lombok.Data;

@Data
public class BookingRequest {

    private Long flightId;
    private String passengerName;
    private String passengerEmail;
    private String seatNumber;
}
