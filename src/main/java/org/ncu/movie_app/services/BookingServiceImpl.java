package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Booking;
import org.ncu.movie_app.entities.Seat;
import org.ncu.movie_app.repositories.BookingRepository;
import org.ncu.movie_app.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    @Transactional
    public void saveBooking(Booking booking) {
        bookingRepository.saveBooking(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
    @Transactional
    public void updateBooking(Booking booking) {
        bookingRepository.updateBooking(booking);
    }

    @Override
    @Transactional
    public void deleteBooking(int id) {
        bookingRepository.deleteBooking(id);
    }

    @Override
    @Transactional
    public void createBookingWithSeat(Booking booking, Seat seat) {
        // Save seat first
        seatRepository.saveSeat(seat);
        // Associate seat with booking
        booking.setSeat(seat);
        seat.setBooking(booking);
        // Save booking
        bookingRepository.saveBooking(booking);
    }
}