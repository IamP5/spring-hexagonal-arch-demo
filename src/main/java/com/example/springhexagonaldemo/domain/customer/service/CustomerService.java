package com.example.springhexagonaldemo.domain.customer.service;

import com.example.springhexagonaldemo.domain.customer.factory.CustomerFactory;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPersistencePort;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import com.example.springhexagonaldemo.domain.customer.port.CustomerServicePort;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServicePort {

    private final CustomerPersistencePort customerPersistence;

    @Override
    public List<CustomerPort> getCustomers() {
        return customerPersistence.findAll();
    }

    @Override
    public CustomerPort getCustomerById(UUID customerId) {
        return customerPersistence.findById(customerId);
    }

    @Override
    public CustomerPort getCustomerByEmail(String email) {
        return customerPersistence.findByEmail(email);
    }

    @Override
    public CustomerPort createCustomer(String name, String email, Address address) {
        CustomerPort customer = CustomerFactory.createWithAddress(name, email, address);

        return customerPersistence.save(customer);
    }

    @Override
    public CustomerPort activateCustomer(UUID customerId) {
        CustomerPort customer = customerPersistence.findById(customerId);
        customer.activate();

        return customerPersistence.save(customer);
    }

    @Override
    public CustomerPort deactivateCustomer(UUID customerId) {
        CustomerPort customer = customerPersistence.findById(customerId);
        customer.deactivate();

        return customerPersistence.save(customer);
    }

    @Override
    public CustomerPort changeCustomerAddress(UUID customerId, Address address) {
        CustomerPort customer = customerPersistence.findById(customerId);
        customer.changeAddress(address);

        return customerPersistence.save(customer);
    }
}
