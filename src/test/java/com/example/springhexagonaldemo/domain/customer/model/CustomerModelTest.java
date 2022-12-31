package com.example.springhexagonaldemo.domain.customer.model;

import com.example.springhexagonaldemo.domain.customer.factory.CustomerFactory;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

public class CustomerModelTest {

    @Test
    public void shouldCreateCustomer() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        Assertions.assertEquals("John Doe", customer.getName());
        Assertions.assertEquals("johndoe@email.com", customer.getEmail());
        Assertions.assertEquals(BigDecimal.ZERO, customer.getRewardPoints());
        Assertions.assertFalse(customer.isActive());
    }

    @Test
    public void shouldChangeAddress() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        customer.changeAddress(address);

        Assertions.assertEquals("street", customer.getAddress().getStreet());
        Assertions.assertEquals(10, customer.getAddress().getNumber());
        Assertions.assertEquals("city", customer.getAddress().getCity());
        Assertions.assertEquals("state", customer.getAddress().getState());
        Assertions.assertEquals("09121000", customer.getAddress().getZipCode());
        Assertions.assertEquals("street, 10, city, state, 09121000", customer.getAddress().toString());
    }

    @Test
    public void shouldThrowErrorWhenCreatingCustomerWithEmptyName() {
        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CustomerModel("", "johndoe@email.com");
        });

        Assertions.assertEquals("Name is required", error.getMessage());
    }

    @Test
    public void shouldThrowErrorWhenCreatingCustomerWithEmptyEmail() {
        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CustomerModel("John Doe", "");
        });

        Assertions.assertEquals("Email is required", error.getMessage());
    }

    @Test
    public void shouldThrowErrorWhenCreatingCustomerWithInvalidEmail() {
        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CustomerModel("John Doe", "johndoeemail.com");
        });

        Assertions.assertEquals("Email is invalid", error.getMessage());
    }

    @Test
    public void shouldChangeName() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        customer.changeName("John Doe Jr");

        Assertions.assertEquals("John Doe Jr", customer.getName());
    }

    @Test
    public void shouldThrowErrorWhenChangingNameToEmpty() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.changeName("");
        });

        Assertions.assertEquals("Name cannot be empty", error.getMessage());
    }

    @Test
    public void shouldChangeEmail() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        customer.changeEmail("johndoejr@email.com");

        Assertions.assertEquals("johndoejr@email.com", customer.getEmail());
    }

    @Test
    public void shouldThrowErrorWhenChangingEmailToEmpty() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.changeEmail("");
        });

        Assertions.assertEquals("Email cannot be empty", error.getMessage());
    }

    @Test void shouldThrowErrorWhenChangingEmailToInvalid() {
        CustomerModel customer = new CustomerModel("John Doe", "johndoe@email.com");

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.changeEmail("jondoeemail.com");
        });

        Assertions.assertEquals("Invalid email", error.getMessage());
    }

    @Test
    public void shouldActivateCustomer() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.activate();

        Assertions.assertTrue(customer.isActive());
    }

    @Test
    public void shouldDeactivateCustomer() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.activate();
        Assertions.assertTrue(customer.isActive());

        customer.deactivate();
        Assertions.assertFalse(customer.isActive());
    }

    @Test
    public void shouldThrowErrorWhenActivatingCustomerWithouthAddress() {
        CustomerModel customer = CustomerFactory.create("John Doe", "johndoe@email.com");

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, customer::activate);

        Assertions.assertEquals("Address is required to activate Customer", error.getMessage());
    }

    @Test
    public void shouldIncrementCustomerRewardPoints() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.incrementRewardPoints(BigDecimal.valueOf(10));
        Assertions.assertEquals(BigDecimal.valueOf(10), customer.getRewardPoints());

        customer.incrementRewardPoints(BigDecimal.valueOf(7.59));
        Assertions.assertEquals(BigDecimal.valueOf(17.59), customer.getRewardPoints());
    }

    @Test
    public void shouldThrowErrorWhenIncrementingCustomerRewardPointsWithNegativeValue() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.incrementRewardPoints(BigDecimal.valueOf(-10));
        });

        Assertions.assertEquals("Cannot increment negative reward points", error.getMessage());
    }

    @Test
    public void shouldDecrementCustomerRewardPoints() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.incrementRewardPoints(BigDecimal.valueOf(10));
        Assertions.assertEquals(BigDecimal.valueOf(10), customer.getRewardPoints());

        customer.decrementRewardPoints(BigDecimal.valueOf(7.59));
        Assertions.assertEquals(BigDecimal.valueOf(2.41), customer.getRewardPoints());
    }

    @Test
    public void shouldThrowErrorWhenDecrementingCustomerRewardPointsWithNegativeValue() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.incrementRewardPoints(BigDecimal.valueOf(10));
        Assertions.assertEquals(BigDecimal.valueOf(10), customer.getRewardPoints());

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.decrementRewardPoints(BigDecimal.valueOf(-10));
        });

        Assertions.assertEquals("Cannot decrement negative reward points", error.getMessage());
    }

    @Test
    public void shouldThrowErrorWhenDecrementingCustomerRewardPointsWithMoreThanCurrentPoints() {
        Address address = new Address("street", 10, "city", "state", "09121000");
        CustomerModel customer = CustomerFactory.createWithAddress("John Doe", "johndoe@email.com", address);

        customer.incrementRewardPoints(BigDecimal.valueOf(10));
        Assertions.assertEquals(BigDecimal.valueOf(10), customer.getRewardPoints());

        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customer.decrementRewardPoints(BigDecimal.valueOf(21));
        });

        Assertions.assertEquals("Customer does not have enough reward points", error.getMessage());
    }
}
