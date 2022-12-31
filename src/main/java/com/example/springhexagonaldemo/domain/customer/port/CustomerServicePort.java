package com.example.springhexagonaldemo.domain.customer.port;

import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;

import java.util.UUID;

public interface CustomerServicePort {
    CustomerPort createCustomer(String name, String email, Address address);
    CustomerPort activateCustomer(UUID customerId);
    CustomerPort deactivateCustomer(UUID customerId);
    CustomerPort changeCustomerAddress(UUID customerId, Address address);
}
