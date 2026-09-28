package com.skybook.backend.controller;

import com.skybook.backend.entity.Booking;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TicketController {

    private final BookingRepository bookingRepo;
    private final PdfService pdfService;

    @GetMapping("/{pnr}/pdf")
    public ResponseEntity<byte[]> download(@PathVariable String pnr) throws Exception {

        Booking booking = bookingRepo.findByPnr(pnr).orElseThrow();

        byte[] pdf = pdfService.generateTicket(booking);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ticket-" + pnr + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
