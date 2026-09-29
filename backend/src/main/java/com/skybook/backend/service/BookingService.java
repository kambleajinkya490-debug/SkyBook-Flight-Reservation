package com.skybook.backend.service;

import com.skybook.backend.dto.BookingRequest;
import com.skybook.backend.entity.*;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.repository.FlightRepository;
import com.skybook.backend.repository.SeatRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final FlightRepository flightRepo;
    private final SeatRepository seatRepo;

    @Transactional
    public Booking bookFlight(BookingRequest req) {

        // 1. Find flight
        Flight flight = flightRepo
                .findById(req.getFlightId())
                .orElseThrow(() ->
                        new RuntimeException("Flight not found")
                );

        // 2. Find selected seat
        Seat seat = seatRepo
                .findByFlightIdAndSeatNumber(
                        req.getFlightId(),
                        req.getSeatNumber()
                )
                .orElseThrow(() ->
                        new RuntimeException("Seat not found")
                );

        // 3. Check whether seat is already booked
        if (seat.getBooked()) {
            throw new RuntimeException("Seat already booked");
        }

        // 4. Mark seat as booked
        seat.setBooked(true);
        seatRepo.save(seat);

        // 5. Generate PNR
        String pnr = UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();

        // 6. Create booking
        Booking booking = Booking.builder()
                .pnr(pnr)
                .passengerName(req.getPassengerName())
                .passengerEmail(req.getPassengerEmail())
                .seatNumber(req.getSeatNumber())
                .bookingStatus(BookingStatus.CONFIRMED)
                .flight(flight)
                .build();

        // 7. Save booking
        return bookingRepo.save(booking);
    }
}
