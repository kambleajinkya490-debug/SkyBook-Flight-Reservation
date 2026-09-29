package com.skybook.backend.controller;

import com.skybook.backend.entity.Booking;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TicketController {

    private final BookingRepository bookingRepo;
    private final PdfService pdfService;

    @GetMapping("/{pnr}")
    public ResponseEntity<?> getTicket(@PathVariable String pnr) {

        Booking booking = bookingRepo.findByPnr(pnr)
                .orElseThrow();

        Map<String, Object> ticket = new HashMap<>();

        ticket.put("pnr", booking.getPnr());
        ticket.put("passengerName", booking.getPassengerName());
        ticket.put("seatNumber", booking.getSeatNumber());
        ticket.put("bookingStatus", booking.getBookingStatus());

        if (booking.getFlight() != null) {
            ticket.put("flightNumber", booking.getFlight().getFlightNumber());
            ticket.put(
                    "route",
                    booking.getFlight().getSource()
                            + " → "
                            + booking.getFlight().getDestination()
            );
        }

        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{pnr}/pdf")
    public ResponseEntity<byte[]> download(
            @PathVariable String pnr) throws Exception {

        Booking booking = bookingRepo.findByPnr(pnr)
                .orElseThrow();

        byte[] pdf = pdfService.generateTicket(booking);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ticket-" + pnr + ".pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
