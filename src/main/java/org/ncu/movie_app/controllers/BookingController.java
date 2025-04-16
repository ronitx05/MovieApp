package org.ncu.movie_app.controllers;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    public ResponseEntity<String> addBooking (@RequestBody Booking booking) {
        bookingService.insertBooking(booking);
        return ResponseEntity.ok("Booking added");
    }
}