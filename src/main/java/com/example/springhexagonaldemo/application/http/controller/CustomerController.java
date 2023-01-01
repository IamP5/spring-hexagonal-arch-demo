package com.example.springhexagonaldemo.application.http.controller;

import com.example.springhexagonaldemo.application.http.dto.customer.AddressDto;
import com.example.springhexagonaldemo.application.http.dto.customer.CustomerRequestDto;
import com.example.springhexagonaldemo.application.http.dto.customer.CustomerResponseDto;
import com.example.springhexagonaldemo.application.http.mapper.CustomerMapper;
import com.example.springhexagonaldemo.domain.customer.port.CustomerServicePort;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServicePort customerService;

    @GetMapping
    public List<CustomerResponseDto> getCustomers() {
        return customerService.getCustomers().stream()
            .map(CustomerMapper::toResponse)
            .toList();
    }

    @GetMapping("/id/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable String customerId) {
        return CustomerMapper.toResponse(customerService.getCustomerById(UUID.fromString(customerId)));
    }

    @GetMapping("/email/{email}")
    public CustomerResponseDto getCustomerByEmail(@PathVariable String email) {
        return CustomerMapper.toResponse(customerService.getCustomerByEmail(email));
    }

    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto request) {
        return CustomerMapper
            .toResponse(customerService.createCustomer(request.getName(), request.getEmail(), request.getAddress()));
    }

    @PostMapping("/{id}/activate")
    public CustomerResponseDto activateCustomer(@PathVariable String id) {
        return CustomerMapper.toResponse(customerService.activateCustomer(UUID.fromString(id)));
    }

    @PostMapping("/{id}/deactivate")
    public CustomerResponseDto deactivateCustomer(@PathVariable String id) {
        return CustomerMapper.toResponse(customerService.deactivateCustomer(UUID.fromString(id)));
    }

    @PostMapping("/{id}/address")
    public CustomerResponseDto changeCustomerAddress(@PathVariable String id, @RequestBody AddressDto request) {
        Address address = new Address(request.getStreet(), request.getNumber(), request.getCity(), request.getState(), request.getZipCode());

        return CustomerMapper.toResponse(customerService.changeCustomerAddress(UUID.fromString(id), address));
    }
}
