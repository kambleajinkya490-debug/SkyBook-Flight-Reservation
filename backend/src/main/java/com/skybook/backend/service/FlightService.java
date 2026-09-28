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

    public FlightService(FlightRepository repo, SeatRepository seatRepo) {
        this.repo = repo;
        this.seatRepo = seatRepo;
    }

    // ADD FLIGHT
    public Flight addFlight(FlightRequest req) {

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

        // Create seats automatically
        for (int i = 1; i <= req.getTotalSeats(); i++) {
            Seat seat = Seat.builder()
                    .seatNumber("A" + i)
                    .booked(false)
                    .flight(saved)
                    .build();

            seatRepo.save(seat);
        }

        return saved;
    }

    // GET ALL
    public List<Flight> getAllFlights() {
        return repo.findAll();
    }

    // SEARCH
    public List<Flight> search(String source, String destination) {
        return repo.findBySourceAndDestination(source, destination);
    }

    // UPDATE
    public Flight updateFlight(Long id, Flight updated) {

        Flight flight = repo.findById(id).orElseThrow();

        flight.setFlightNumber(updated.getFlightNumber());
        flight.setAirline(updated.getAirline());
        flight.setSource(updated.getSource());
        flight.setDestination(updated.getDestination());
        flight.setFlightDate(updated.getFlightDate());
        flight.setDepartureTime(updated.getDepartureTime());
        flight.setArrivalTime(updated.getArrivalTime());
        flight.setPrice(updated.getPrice());
        flight.setTotalSeats(updated.getTotalSeats());

        return repo.save(flight);
    }

    // DELETE
    public void deleteFlight(Long id) {
        repo.deleteById(id);
    }
}
