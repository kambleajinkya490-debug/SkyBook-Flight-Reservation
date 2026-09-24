package com.skybook.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {

    private Long bookingId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
}
