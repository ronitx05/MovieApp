package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void insertBooking(Booking booking) {
        bookingRepository.createBooking(booking);
    }

    @Override
    public Booking updateBooking(int id, Booking booking) {
        // Implement logic (e.g., fetch existing booking, update fields, save)
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Booking deleteBookingById(int id) {
        // Implement deletion logic
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Booking getBookingById(int id) {
        // Implement fetch logic
        throw new UnsupportedOperationException("Not yet implemented");
    }
}