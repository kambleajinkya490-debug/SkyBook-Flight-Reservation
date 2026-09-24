package com.skybook.backend.repository;

import com.skybook.backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByFlightIdOrderBySeatNumber(Long flightId);

    Optional<Seat> findByFlightIdAndSeatNumber(
            Long flightId,
            String seatNumber
    );
}
