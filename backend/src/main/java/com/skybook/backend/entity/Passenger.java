package com.skybook.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
