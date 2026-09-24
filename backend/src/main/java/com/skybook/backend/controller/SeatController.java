package com.skybook.backend.controller;

import com.skybook.backend.dto.SeatResponse;
import com.skybook.backend.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService service;

    @GetMapping("/{flightId}/seats")
    public List<SeatResponse> getSeats(@PathVariable Long flightId) {
        return service.getSeats(flightId);
    }
}
