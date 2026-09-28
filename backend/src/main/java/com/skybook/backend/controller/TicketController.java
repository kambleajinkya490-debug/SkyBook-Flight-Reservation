package com.skybook.backend.controller;

import com.skybook.backend.dto.TicketResponse;
import com.skybook.backend.pdf.PdfService;
import com.skybook.backend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;
    private final PdfService pdfService;

    @GetMapping("/{pnr}")
    public TicketResponse getTicket(@PathVariable String pnr) {
        return service.getTicket(pnr);
    }

    @GetMapping("/{pnr}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String pnr) {

        TicketResponse ticket = service.getTicket(pnr);
        byte[] pdf = pdfService.generate(ticket);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ticket-" + pnr + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
