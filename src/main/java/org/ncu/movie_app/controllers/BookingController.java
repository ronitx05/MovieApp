package org.ncu.movie_app.controllers;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.entities.Seat;
import org.ncu.movie_app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        bookingService.saveBooking(booking);
        return ResponseEntity.ok("Booking created successfully");
    }

    @PostMapping("/with-seat")
    public ResponseEntity<String> createBookingWithSeat(@RequestBody Booking booking, @RequestBody Seat seat) {
        bookingService.createBookingWithSeat(booking, seat);
        return ResponseEntity.ok("Booking with seat created successfully");
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        bookingService.updateBooking(booking);
        return ResponseEntity.ok("Booking updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}