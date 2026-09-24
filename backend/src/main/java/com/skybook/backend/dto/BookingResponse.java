package com.skybook.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private String pnr;
    private String seatNumber;
    private String status;
}
