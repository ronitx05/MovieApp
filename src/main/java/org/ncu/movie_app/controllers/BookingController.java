package org.ncu.movie_app.controllers;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.entities.Movie;
import org.ncu.movie_app.entities.Seat;
import org.ncu.movie_app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/with-seat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBookingWithSeat(@RequestBody Map<String, Object> request) {
        Map<String, Object> bookingMap = (Map<String, Object>) request.get("booking");
        Map<String, Object> seatMap = (Map<String, Object>) request.get("seat");
        Map<String, Object> movieMap = (Map<String, Object>) bookingMap.get("movie");

        Movie movie = new Movie();
        movie.setMovieId(Integer.parseInt(movieMap.get("movieId").toString()));

        Seat seat = new Seat();
        seat.setSeatNumber(seatMap.get("seatNumber").toString());
        seat.setSeatType(seatMap.get("seatType").toString());

        Booking booking = new Booking();
        booking.setBookingTime(LocalDateTime.parse(bookingMap.get("bookingTime").toString()));
        booking.setTotalAmount(new BigDecimal(bookingMap.get("totalAmount").toString()));
        booking.setMovie(movie);

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