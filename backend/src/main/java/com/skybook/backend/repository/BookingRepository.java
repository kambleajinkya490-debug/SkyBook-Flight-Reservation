package com.skybook.backend.repository;

import com.skybook.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository
        extends JpaRepository<Booking,Long>{

    Optional<Booking> findByPnr(String pnr);

}
