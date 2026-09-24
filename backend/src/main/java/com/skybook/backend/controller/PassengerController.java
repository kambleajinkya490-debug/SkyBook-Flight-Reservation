package com.skybook.backend.controller;

import com.skybook.backend.dto.PassengerRequest;
import com.skybook.backend.service.PassengerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service){
        this.service = service;
    }

    @PostMapping
    public String addPassenger(
            @RequestBody PassengerRequest request){

        return service.addPassenger(request);
    }
}
