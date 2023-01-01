package com.example.springhexagonaldemo.application.http.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String zipCode;
}
