package com.example.springhexagonaldemo.application.http.dto.customer;

import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    private String name;
    private String email;
    private BigDecimal rewardPoints;
    private Address address;
}