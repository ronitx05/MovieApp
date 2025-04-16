package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Booking;

public interface BookingService {
    void insertBooking(Booking booking);
    Booking updateBooking(int id, Booking booking);
    Booking deleteBookingById(int id);
    Booking getBookingById(int id);
}
