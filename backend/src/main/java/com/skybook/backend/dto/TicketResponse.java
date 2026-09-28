package com.skybook.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponse {

    private String pnr;
    private String passengerName;
    private String flightNumber;
    private String route;
    private String seat;

    private String gate;
    private String terminal;
    private String boardingTime;

    private String bookingStatus;
    private String paymentStatus;
}
