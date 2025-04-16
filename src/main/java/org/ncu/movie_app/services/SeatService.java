package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Seat;
import java.util.List;

public interface SeatService {
    void saveSeat(Seat seat);
    Seat getSeatById(int id);
    List<Seat> getAllSeats();
    void updateSeat(Seat seat);
    void deleteSeat(int id);
}