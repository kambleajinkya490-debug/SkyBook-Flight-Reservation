package com.skybook.backend.repository;

import com.skybook.backend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository
        extends JpaRepository<Passenger, Long> {

    List<Passenger> findByBookingId(Long bookingId);

}
