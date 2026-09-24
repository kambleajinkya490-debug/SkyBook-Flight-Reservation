package com.skybook.backend.service;

import com.skybook.backend.dto.PaymentRequest;
import com.skybook.backend.dto.PaymentResponse;
import com.skybook.backend.entity.*;
import com.skybook.backend.repository.*;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final BookingRepository bookingRepo;

    public PaymentService(PaymentRepository paymentRepo,
                          BookingRepository bookingRepo){
        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
    }

    public PaymentResponse pay(PaymentRequest request){

        Booking booking = bookingRepo.findById(
                request.getBookingId()
        ).orElseThrow();

        String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random=new SecureRandom();

        StringBuilder id=new StringBuilder("PAY");

        for(int i=0;i<8;i++){
            id.append(chars.charAt(
                    random.nextInt(chars.length())
            ));
        }

        Payment payment = Payment.builder()
                .paymentId(id.toString())
                .amount(request.getAmount())
                .paymentStatus(PaymentStatus.SUCCESS)
                .booking(booking)
                .build();

        paymentRepo.save(payment);

        return PaymentResponse.builder()
                .paymentId(id.toString())
                .amount(request.getAmount())
                .status("SUCCESS")
                .build();
    }
}
