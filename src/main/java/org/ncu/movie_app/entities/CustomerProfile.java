package org.ncu.movie_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_profile")
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CPId")
    private int CustomerProfileId;

   private String CustomerProfileName;
}
