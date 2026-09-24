package com.skybook.backend.controller;

import com.skybook.backend.dto.PaymentRequest;
import com.skybook.backend.dto.PaymentResponse;
import com.skybook.backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service){
        this.service = service;
    }

    @PostMapping
    public PaymentResponse pay(
            @RequestBody PaymentRequest request){

        return service.pay(request);
    }
}
