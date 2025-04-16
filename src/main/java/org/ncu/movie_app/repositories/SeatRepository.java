package org.ncu.movie_app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.ncu.movie_app.entities.Seat;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class SeatRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveSeat(Seat seat) {
        entityManager.persist(seat);
    }

    public Seat getSeatById(int id) {
        return entityManager.find(Seat.class, id);
    }

    public List<Seat> getAllSeats() {
        return entityManager.createQuery("SELECT s FROM Seat s", Seat.class).getResultList();
    }

    public void updateSeat(Seat seat) {
        entityManager.merge(seat);
    }

    public void deleteSeat(int id) {
        Seat seat = entityManager.find(Seat.class, id);
        if (seat != null) {
            entityManager.remove(seat);
        }
    }
}