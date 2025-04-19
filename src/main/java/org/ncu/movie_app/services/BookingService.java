package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.entities.Seat;
import java.util.List;

public interface BookingService {
    void saveBooking(Booking booking);
    Booking getBookingById(int id);
    List<Booking> getAllBookings();
    void updateBooking(Booking booking);
    void deleteBooking(int id);
    void createBookingWithSeat(Booking booking, Seat seat);
}