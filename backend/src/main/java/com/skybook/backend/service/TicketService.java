package com.skybook.backend.service;

import com.skybook.backend.dto.TicketResponse;
import com.skybook.backend.entity.*;
import com.skybook.backend.repository.*;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final BookingRepository bookingRepo;
    private final PassengerRepository passengerRepo;
    private final PaymentRepository paymentRepo;

    public TicketService(BookingRepository bookingRepo,
                         PassengerRepository passengerRepo,
                         PaymentRepository paymentRepo){
        this.bookingRepo = bookingRepo;
        this.passengerRepo = passengerRepo;
        this.paymentRepo = paymentRepo;
    }

    public TicketResponse getTicket(String pnr){

        Booking booking = bookingRepo.findByPnr(pnr).orElseThrow();

        Passenger passenger = passengerRepo
                .findByBookingId(booking.getId())
                .getFirst();

        Payment payment = paymentRepo
                .findByBookingId(booking.getId())
                .orElseThrow();

return TicketResponse.builder()
        .pnr(booking.getPnr())
        .passengerName(
                passenger.getFirstName() + " " +
                passenger.getLastName()
        )
        .flightNumber(
                booking.getFlight().getFlightNumber()
        )
        .route(
                booking.getFlight().getSource() + " → " +
                booking.getFlight().getDestination()
        )
        .seat(booking.getSeatNumber())
        .bookingStatus(
                booking.getBookingStatus().name()
        )
        .paymentStatus(
                payment.getPaymentStatus().name()
        )

        // NEW
        .gate("A12")
        .boardingTime("08:30 AM")
        .terminal("T1")

        .build();
    }
}
