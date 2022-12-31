package com.example.springhexagonaldemo.domain.customer.factory;

import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;

public class CustomerFactory {

    public static CustomerModel create(String name, String email) {
       return new CustomerModel(name, email);
    }

    public static CustomerModel createWithAddress(String name, String email, Address address) {
        CustomerModel customer = new CustomerModel(name, email);
        customer.changeAddress(address);

        return customer;
    }
}
