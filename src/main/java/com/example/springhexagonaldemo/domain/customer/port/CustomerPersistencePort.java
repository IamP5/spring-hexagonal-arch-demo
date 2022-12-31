package com.example.springhexagonaldemo.domain.customer.port;

import java.util.List;
import java.util.UUID;

public interface CustomerPersistencePort {
    CustomerPort save(CustomerPort customer);
    CustomerPort findById(UUID customerId);
    CustomerPort findByEmail(String email);
    List<CustomerPort> findAll();
}
