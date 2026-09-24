package com.skybook.backend.service;

import com.skybook.backend.dto.SeatResponse;
import com.skybook.backend.entity.Seat;
import com.skybook.backend.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository repo;

    public SeatService(SeatRepository repo){
        this.repo = repo;
    }

    // GET Seats
    public List<SeatResponse> getSeats(Long flightId){

        return repo.findByFlightIdOrderBySeatNumber(flightId)
                .stream()
                .map(seat -> SeatResponse.builder()
                        .id(seat.getId())
                        .seatNumber(seat.getSeatNumber())
                        .booked(seat.getBooked())
                        .build())
                .toList();
    }

    // BOOK Seat  👇 हे नवीन add कर
    public String bookSeat(Long flightId, String seatNumber){

        Seat seat = repo.findByFlightIdAndSeatNumber(
                flightId,
                seatNumber
        ).orElse(null);

        if(seat == null){
            return "Seat Not Found";
        }

        if(seat.getBooked()){
            return "Seat Already Booked";
        }

        seat.setBooked(true);
        repo.save(seat);

        return "Seat Booked Successfully";
    }
}
