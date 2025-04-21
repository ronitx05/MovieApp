package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.CustomerProfile;
import org.ncu.movie_app.repositories.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProfileService {
    @Autowired
    private CustomerProfileRepository repository;

    public CustomerProfile saveCustomer(CustomerProfile customer) {
        return repository.save(customer);
    }

    public List<CustomerProfile> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<CustomerProfile> getCustomerById(Long id) {
        return repository.findById(id);
    }

    public CustomerProfile updateCustomer(Long id, CustomerProfile updatedCustomer) {
        return repository.findById(id).map(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setLocation(updatedCustomer.getLocation());
            return repository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with ID " + id));
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    public List<CustomerProfile> findByLocation(String location) {
        return repository.findByLocation(location);
    }

    public List<CustomerProfile> findByFullNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }

    public Page<CustomerProfile> getPaginatedCustomers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<CustomerProfile> findByEmailDomain(String domain) {
        return repository.findByEmailDomain(domain);
    }

}