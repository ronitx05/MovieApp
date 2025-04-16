package org.ncu.movie_app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.ncu.movie_app.entities.Booking;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class BookingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveBooking(Booking booking) {
        entityManager.persist(booking);
    }

    public Booking getBookingById(int id) {
        return entityManager.find(Booking.class, id);
    }

    public List<Booking> getAllBookings() {
        return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }

    public void updateBooking(Booking booking) {
        entityManager.merge(booking);
    }

    public void deleteBooking(int id) {
        Booking booking = entityManager.find(Booking.class, id);
        if (booking != null) {
            entityManager.remove(booking);
        }
    }
}