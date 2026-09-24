package com.skybook.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {

    private Long id;
    private String seatNumber;
    private Boolean booked;
}
