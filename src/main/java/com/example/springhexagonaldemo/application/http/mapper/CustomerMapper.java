package com.example.springhexagonaldemo.application.http.mapper;

import com.example.springhexagonaldemo.application.http.dto.customer.CustomerRequestDto;
import com.example.springhexagonaldemo.application.http.dto.customer.CustomerResponseDto;
import com.example.springhexagonaldemo.domain.customer.model.CustomerModel;
import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerMapper {

    public static CustomerResponseDto toResponse(CustomerPort customer) {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .rewardPoints(customer.getRewardPoints())
                .address(customer.getAddress())
                .build();
    }
}
