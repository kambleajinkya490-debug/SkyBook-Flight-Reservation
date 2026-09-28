package com.skybook.backend.controller;

import com.skybook.backend.dto.BookingRequest;
import com.skybook.backend.entity.Booking;
import com.skybook.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request){
        return bookingService.bookFlight(request);
    }
}
