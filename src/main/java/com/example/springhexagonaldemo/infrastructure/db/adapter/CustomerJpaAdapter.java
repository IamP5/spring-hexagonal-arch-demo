package com.example.springhexagonaldemo.infrastructure.db.adapter;

import com.example.springhexagonaldemo.domain.customer.port.CustomerPersistencePort;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import com.example.springhexagonaldemo.infrastructure.db.entity.CustomerEntity;
import com.example.springhexagonaldemo.infrastructure.db.mapper.CustomerMapper;
import com.example.springhexagonaldemo.infrastructure.db.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerJpaAdapter implements CustomerPersistencePort {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerPort save(CustomerPort customer) {
        CustomerEntity customerSaved = customerRepository.save(CustomerMapper.toEntity(customer));

        return CustomerMapper.toModel(customerSaved);
    }

    @Override
    public CustomerPort findById(UUID customerId) {
        CustomerEntity customer = customerRepository.findById(customerId.toString())
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        return CustomerMapper.toModel(customer);
    }

    @Override
    public CustomerPort findByEmail(String email) {
        CustomerEntity customer = customerRepository.findByEmail(email)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        return CustomerMapper.toModel(customer);
    }

    @Override
    public List<CustomerPort> findAll() {
        return customerRepository.findAll().stream()
            .map(CustomerMapper::toModel)
            .toList();
    }
}
