package com.example.springhexagonaldemo.domain.customer.factory;

import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CustomerFactoryTest {

    @Test
    public void shouldCreateCustomer() {
        CustomerModel customer = CustomerFactory.create("John Doe", "johndoe@email.com");

        Assertions.assertEquals("John Doe", customer.getName());
        Assertions.assertEquals("johndoe@email.com", customer.getEmail());
        Assertions.assertEquals(BigDecimal.ZERO, customer.getRewardPoints());
        Assertions.assertFalse(customer.isActive());
    }

    @Test
    public void shouldCreateCustomerWithAddress() {
        Address address = new Address("street", 10,"city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        Assertions.assertEquals("John Doe", customer.getName());
        Assertions.assertEquals("johndoe@email.com", customer.getEmail());
        Assertions.assertEquals(BigDecimal.ZERO, customer.getRewardPoints());
        Assertions.assertFalse(customer.isActive());
        Assertions.assertEquals("street, 10, city, state, 09121000", customer.getAddress().toString());
    }
}
