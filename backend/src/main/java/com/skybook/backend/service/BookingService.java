package com.skybook.backend.service;

import com.skybook.backend.dto.BookingRequest;
import com.skybook.backend.entity.*;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final FlightRepository flightRepo;

    public Booking bookFlight(BookingRequest req) {

        Flight flight = flightRepo.findById(req.getFlightId()).orElseThrow();

        String pnr = UUID.randomUUID()
                .toString()
                .substring(0,6)
                .toUpperCase();

        Booking booking = Booking.builder()
                .pnr(pnr)
                .passengerName(req.getPassengerName())
                .passengerEmail(req.getPassengerEmail())
                .seatNumber(req.getSeatNumber())
                .bookingStatus(BookingStatus.CONFIRMED)
                .flight(flight)
                .build();

        return bookingRepo.save(booking);
    }
}
