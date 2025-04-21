package org.ncu.movie_app.controllers;
import org.ncu.movie_app.entities.CustomerProfile;
import org.ncu.movie_app.services.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;

    @PostMapping
    public ResponseEntity<CustomerProfile> createCustomer(@RequestBody CustomerProfile customer) {
        return ResponseEntity.ok(service.saveCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerProfile>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfile> getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerProfile> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerProfile updatedCustomer) {
        try {
            CustomerProfile customer = service.updateCustomer(id, updatedCustomer);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<CustomerProfile>> getCustomersByLocation(@PathVariable String location) {
        return ResponseEntity.ok(service.findByLocation(location));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerProfile>> searchCustomersByName(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(service.findByFullNameContaining(keyword));
    }

    @GetMapping("/paginated")
    public Page<CustomerProfile> getPaginatedCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "fullName") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return service.getPaginatedCustomers(pageable);
    }

    @GetMapping("/email-domain")
    public ResponseEntity<List<CustomerProfile>> getCustomersByEmailDomain(@RequestParam("domain") String domain) {
        return ResponseEntity.ok(service.findByEmailDomain(domain));
    }
}