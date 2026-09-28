package com.skybook.backend.controller;

import com.skybook.backend.dto.FlightRequest;
import com.skybook.backend.entity.Flight;
import com.skybook.backend.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public Flight addFlight(@RequestBody FlightRequest request) {
        return flightService.addFlight(request);
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination
    ) {
        return flightService.search(source, destination);
    }
@PutMapping("/{id}")
public Flight update(@PathVariable Long id, @RequestBody Flight flight){
    return flightService.updateFlight(id, flight);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id){
    flightService.deleteFlight(id);
}
}
