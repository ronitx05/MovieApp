package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Seat;
import org.ncu.movie_app.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    @Transactional
    public void saveSeat(Seat seat) {
        seatRepository.saveSeat(seat);
    }

    @Override
    public Seat getSeatById(int id) {
        return seatRepository.getSeatById(id);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.getAllSeats();
    }

    @Override
    @Transactional
    public void updateSeat(Seat seat) {
        seatRepository.updateSeat(seat);
    }

    @Override
    @Transactional
    public void deleteSeat(int id) {
        seatRepository.deleteSeat(id);
    }
}