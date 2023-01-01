package com.example.springhexagonaldemo.domain.customer.service;

import com.example.springhexagonaldemo.domain.customer.factory.CustomerFactory;
import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPersistencePort;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import com.example.springhexagonaldemo.domain.customer.port.CustomerServicePort;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import com.example.springhexagonaldemo.infrastructure.db.adapter.CustomerJpaAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerPersistencePort customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void shouldGetAllCustomers() {
        Address address1 = new Address("Street1", 10, "City1", "State1", "09298777");
        Address address2 = new Address("Street2", 20, "City2", "State2", "09298777");

        CustomerPort customer1 = CustomerFactory.createWithAddress("Name1", "email1@email.com", address1);
        CustomerPort customer2 = CustomerFactory.createWithAddress("Name2", "email2@email.com", address2);

        List<CustomerPort> expectedCustomers = List.of(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<CustomerPort> actualCustomers = customerService.getCustomers();

        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    public void shouldGetCustomerById() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        CustomerPort expectedCustomer = CustomerFactory.createWithAddress("Name", "email@email.com", address);

        when(customerRepository.findById(any(UUID.class))).thenReturn(expectedCustomer);

        CustomerPort actualCustomer = customerService.getCustomerById(UUID.randomUUID());

        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void shouldGetCustomerByEmail() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        CustomerPort expectedCustomer = CustomerFactory.createWithAddress("Name", "email@email.com", address);

        when(customerRepository.findByEmail(anyString())).thenReturn(expectedCustomer);

        CustomerPort actualCustomer = customerService.getCustomerByEmail("email@email.com");

        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void shouldCreateCustomer() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        String customerName = "Name";
        String customerEmail = "email@email.com";
        CustomerPort expectedCustomer = CustomerFactory.createWithAddress(customerName, customerEmail, address);

        try(MockedStatic<CustomerFactory> customerFactoryMockedStatic = mockStatic(CustomerFactory.class)) {
            customerFactoryMockedStatic.when(() -> CustomerFactory.createWithAddress(anyString(), anyString(), any(Address.class)))
               .thenReturn(expectedCustomer);

            when(customerRepository.save(any(CustomerModel.class))).thenReturn(expectedCustomer);

            CustomerPort actualCustomer = customerService.createCustomer(customerName, customerEmail, address);

            customerFactoryMockedStatic.verify(() -> CustomerFactory.createWithAddress(customerName, customerEmail, address));
            assertEquals(expectedCustomer, actualCustomer);
       }
    }

    @Test
    public void shouldActivateCustomer() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        CustomerPort customer = spy(CustomerFactory.createWithAddress("Name", "email@email.com", address));

        when(customerRepository.findById(any(UUID.class))).thenReturn(customer);
        when(customerRepository.save(any(CustomerPort.class))).thenReturn(customer);

        customerService.activateCustomer(UUID.randomUUID());

        verify(customer).activate();
    }

    @Test
    public void shouldDeactivateCustomer() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        CustomerPort customer = spy(CustomerFactory.createWithAddress("Name", "email@email.com", address));

        when(customerRepository.findById(any(UUID.class))).thenReturn(customer);
        when(customerRepository.save(any(CustomerPort.class))).thenReturn(customer);

        customerService.deactivateCustomer(UUID.randomUUID());

        verify(customer).deactivate();
    }

    @Test
    public void shouldChangeCustomerAddress() {
        Address address = new Address("Street", 10, "City", "State", "09298777");
        CustomerPort expectedCustomer = spy(CustomerFactory.createWithAddress("Name", "email@email.com", address));

        when(customerRepository.findById(any(UUID.class))).thenReturn(expectedCustomer);
        when(customerRepository.save(any(CustomerPort.class))).thenReturn(expectedCustomer);

        Address newAddress = new Address("Street", 10, "City", "State", "09298777");

        customerService.changeCustomerAddress(UUID.randomUUID(), newAddress);

        verify(expectedCustomer).changeAddress(newAddress);
    }

}
