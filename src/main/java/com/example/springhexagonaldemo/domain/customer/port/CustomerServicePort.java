package com.example.springhexagonaldemo.domain.customer.port;

import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;

import java.util.List;
import java.util.UUID;

public interface CustomerServicePort {
    List<CustomerPort> getCustomers();
    CustomerPort getCustomerById(UUID customerId);
    CustomerPort getCustomerByEmail(String email);
    CustomerPort createCustomer(String name, String email, Address address);
    CustomerPort activateCustomer(UUID customerId);
    CustomerPort deactivateCustomer(UUID customerId);
    CustomerPort changeCustomerAddress(UUID customerId, Address address);
}
