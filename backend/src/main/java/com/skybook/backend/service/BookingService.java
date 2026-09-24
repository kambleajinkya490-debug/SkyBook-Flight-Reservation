package com.skybook.backend.service;

import com.skybook.backend.dto.BookingRequest;
import com.skybook.backend.entity.*;
import com.skybook.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final FlightRepository flightRepo;
    private final UserRepository userRepo;
    private final BookingRepository bookingRepo;
    private final SeatRepository seatRepo;
    private final PaymentRepository paymentRepo;
    private final PassengerRepository passengerRepo;

    public String bookSeat(BookingRequest request) {

        Flight flight = flightRepo.findById(request.getFlightId())
                .orElseThrow();

        User user = userRepo.findById(1L)
                .orElseThrow();

        Seat seat = seatRepo
                .findByFlightIdAndSeatNumber(
                        request.getFlightId(),
                        request.getSeatNumber())
                .orElseThrow();

        if (seat.getBooked()) {
            return "Seat Already Booked";
        }

        seat.setBooked(true);
        seatRepo.save(seat);

        String pnr = "SKB" +
                UUID.randomUUID()
                        .toString()
                        .substring(0,6)
                        .toUpperCase();

        Booking booking = Booking.builder()
                .pnr(pnr)
                .user(user)
                .flight(flight)
                .seatNumber(request.getSeatNumber())
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        bookingRepo.save(booking);

        Passenger passenger = Passenger.builder()
                .booking(booking)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();

        passengerRepo.save(passenger);

        Payment payment = Payment.builder()
                .booking(booking)
                .paymentId("PAY" + System.currentTimeMillis())
                .amount(flight.getPrice())
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();

        paymentRepo.save(payment);

        return pnr;
    }
}
