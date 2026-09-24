package com.skybook.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pnr;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
