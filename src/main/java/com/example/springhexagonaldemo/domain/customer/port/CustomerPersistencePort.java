package com.example.springhexagonaldemo.domain.customer.port;

import java.util.UUID;

public interface CustomerPersistencePort {
    CustomerPort save(CustomerPort customer);
    CustomerPort findById(UUID customerId);
    CustomerPort findByEmail(String email);
    CustomerPort findAll();
}
