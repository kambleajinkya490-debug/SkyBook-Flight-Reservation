package com.skybook.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private String pnr;
    private String passengerName;
    private String flightNumber;
    private String route;
    private String seat;

    private String bookingStatus;
    private String paymentStatus;

    // NEW
    private String gate;
    private String boardingTime;
    private String terminal;
}
