package com.skybook.backend.service;

import com.skybook.backend.dto.FlightRequest;
import com.skybook.backend.entity.Flight;
import com.skybook.backend.entity.Seat;
import com.skybook.backend.repository.FlightRepository;
import com.skybook.backend.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repo;
    private final SeatRepository seatRepo;

    public FlightService(FlightRepository repo,
                         SeatRepository seatRepo) {
        this.repo = repo;
        this.seatRepo = seatRepo;
    }

    public Flight addFlight(FlightRequest req){

        Flight flight = Flight.builder()
                .flightNumber(req.getFlightNumber())
                .airline(req.getAirline())
                .source(req.getSource())
                .destination(req.getDestination())
                .flightDate(req.getFlightDate())
                .departureTime(req.getDepartureTime())
                .arrivalTime(req.getArrivalTime())
                .price(req.getPrice())
                .totalSeats(req.getTotalSeats())
                .build();

        Flight saved = repo.save(flight);

        String[] rows = {"A","B","C","D","E","F"};

        for(int n=1; n<=30; n++){
            for(String row: rows){
                Seat seat = Seat.builder()
                        .seatNumber(row+n)
                        .booked(false)
                        .flight(saved)
                        .build();

                seatRepo.save(seat);
            }
        }

        return saved;
    }

    public List<Flight> getAllFlights(){
        return repo.findAll();
    }

    public List<Flight> search(String source,
                               String destination,
                               LocalDate date){
        return repo.findBySourceAndDestinationAndFlightDate(
                source,destination,date);
    }
}
