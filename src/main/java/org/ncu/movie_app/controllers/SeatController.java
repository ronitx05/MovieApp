package org.ncu.movie_app.controllers;

import org.ncu.movie_app.entities.Seat;
import org.ncu.movie_app.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<String> createSeat(@RequestBody Seat seat) {
        seatService.saveSeat(seat);
        return ResponseEntity.ok("Seat created successfully");
    }

    @GetMapping("/{id}")
    public Seat getSeat(@PathVariable int id) {
        return seatService.getSeatById(id);
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeat(@PathVariable int id, @RequestBody Seat seat) {
        seat.setSeatId(id);
        seatService.updateSeat(seat);
        return ResponseEntity.ok("Seat updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable int id) {
        seatService.deleteSeat(id);
        return ResponseEntity.ok("Seat deleted successfully");
    }
}