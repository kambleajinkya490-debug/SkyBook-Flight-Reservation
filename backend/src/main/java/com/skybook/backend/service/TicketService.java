package com.skybook.backend.service;

import com.skybook.backend.dto.TicketResponse;
import com.skybook.backend.entity.Booking;
import com.skybook.backend.entity.Passenger;
import com.skybook.backend.entity.Payment;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.repository.PassengerRepository;
import com.skybook.backend.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final BookingRepository bookingRepo;
    private final PassengerRepository passengerRepo;
    private final PaymentRepository paymentRepo;

    public TicketResponse getTicket(String pnr) {

        Booking booking = bookingRepo.findByPnr(pnr).orElseThrow();
        Passenger passenger = passengerRepo.findByBookingId(booking.getId()).get(0);
        Payment payment = paymentRepo.findByBookingId(booking.getId()).orElseThrow();

        return TicketResponse.builder()
                .pnr(booking.getPnr())
                .passengerName(passenger.getFirstName() + " " + passenger.getLastName())
                .flightNumber(booking.getFlight().getFlightNumber())
                .route(booking.getFlight().getSource() + " → " + booking.getFlight().getDestination())
                .seat(booking.getSeatNumber())
                .gate("A12")
                .terminal("T1")
                .boardingTime("08:30 AM")
                .bookingStatus(booking.getBookingStatus().name())
                .paymentStatus(payment.getPaymentStatus().name())
                .build();
    }
}
