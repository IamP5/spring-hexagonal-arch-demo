package com.example.springhexagonaldemo.application.http.dto.customer;

import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {
    private String name;
    private String email;
    private Address address;
}
