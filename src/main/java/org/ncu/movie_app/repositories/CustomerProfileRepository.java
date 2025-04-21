package org.ncu.movie_app.repositories;

import org.ncu.movie_app.entities.CustomerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    List<CustomerProfile> findByLocation(String location);
    List<CustomerProfile> findByNameContaining(String keyword);

    @Query("SELECT c FROM CustomerProfile c WHERE c.email LIKE %:domain%")
    List<CustomerProfile> findByEmailDomain(@Param("domain") String domain);

    Page<CustomerProfile> findAll(Pageable pageable);
}
