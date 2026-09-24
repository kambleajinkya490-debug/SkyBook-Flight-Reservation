package com.skybook.backend.service;

import com.skybook.backend.dto.PassengerRequest;
import com.skybook.backend.entity.Booking;
import com.skybook.backend.entity.Passenger;
import com.skybook.backend.repository.BookingRepository;
import com.skybook.backend.repository.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepo;
    private final BookingRepository bookingRepo;

    public PassengerService(PassengerRepository passengerRepo,
                            BookingRepository bookingRepo) {
        this.passengerRepo = passengerRepo;
        this.bookingRepo = bookingRepo;
    }

    public String addPassenger(PassengerRequest request){

        Booking booking = bookingRepo.findById(request.getBookingId())
                .orElseThrow();

        Passenger passenger = Passenger.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .gender(request.getGender())
                .booking(booking)
                .build();

        passengerRepo.save(passenger);

        return "Passenger Added Successfully";
    }
}
