package com.skybook.backend.controller;

import com.skybook.backend.dto.TicketResponse;
import com.skybook.backend.pdf.PdfService;
import com.skybook.backend.service.TicketService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;
    private final PdfService pdfService;

    public TicketController(TicketService service,
                            PdfService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    // JSON Ticket API
    @GetMapping("/{pnr}")
    public TicketResponse ticket(@PathVariable String pnr) {
        return service.getTicket(pnr);
    }

    // PDF Download API
    @GetMapping("/{pnr}/pdf")
    public ResponseEntity<byte[]> pdf(@PathVariable String pnr) {

        TicketResponse ticket = service.getTicket(pnr);

        byte[] pdf = pdfService.generate(ticket);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=SkyBook-Ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
