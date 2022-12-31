package com.example.springhexagonaldemo.infrastructure.db.mapper;

import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import com.example.springhexagonaldemo.infrastructure.db.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerPort customer) {
        return CustomerEntity.builder()
                .id(customer.getId().toString())
                .name(customer.getName())
                .email(customer.getEmail())
                .active(customer.isActive())
                .rewardPoints(customer.getRewardPoints())
                .street(customer.getAddress().getStreet())
                .number(customer.getAddress().getNumber())
                .city(customer.getAddress().getCity())
                .state(customer.getAddress().getState())
                .zipCode(customer.getAddress().getZipCode())
                .build();
    }

    public static CustomerPort toModel (CustomerEntity customer) {
        Address address = new Address(customer.getStreet(), customer.getNumber(), customer.getCity(), customer.getState(), customer.getZipCode());
        return new CustomerModel(
                UUID.fromString(customer.getId()),
                customer.getName(),
                customer.getEmail(),
                address,
                customer.getActive(),
                customer.getRewardPoints()
        );
    }
}
