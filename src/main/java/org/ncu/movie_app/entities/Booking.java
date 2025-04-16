package org.ncu.movie_app.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int bookingId;

    @Column(name="booking_date", nullable=false)
    Date bookingDate;

    @Column(name = "booking_price", nullable=false)
    int bookingPrice;

    @Column(name = "customer_name", nullable=false, length=20)
    String customerName;

    public Booking() {}

    public Booking(String customerName, int bookingPrice, Date bookingDate, int bookingId) {
        this.customerName = customerName;
        this.bookingPrice = bookingPrice;
        this.bookingDate = bookingDate;
        this.bookingId = bookingId;
    }

    // Getters and Setters (omitted for brevity)
    // toString() method (omitted)
}