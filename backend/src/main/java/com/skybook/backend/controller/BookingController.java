package com.skybook.backend.controller;

import com.skybook.backend.dto.BookingRequest;
import com.skybook.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping("/book-seat")
    public String bookSeat(@RequestBody BookingRequest request) {
        return service.bookSeat(request);
    }
}
