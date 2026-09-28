package com.skybook.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pnr;

    private String passengerName;

    private String passengerEmail;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
