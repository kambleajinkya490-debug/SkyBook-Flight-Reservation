package com.skybook.backend.controller;

import com.skybook.backend.entity.Flight;
import com.skybook.backend.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightRepository repo;

    public FlightController(FlightRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public List<Flight> getAllFlights(){
        return repo.findAll();
    }
}
