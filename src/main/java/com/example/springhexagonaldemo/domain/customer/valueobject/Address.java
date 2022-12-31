package com.example.springhexagonaldemo.domain.customer.valueobject;

import lombok.Getter;

@Getter
public class Address {
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String zipCode;


    public Address(String street, Integer number, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;

        this.validate();
    }

    public void validate() {
        if (this.street == null || this.street.isEmpty()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (this.city == null || this.city.isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (this.state == null || this.state.isEmpty()) {
            throw new IllegalArgumentException("State is required");
        }
        if (this.zipCode == null || this.zipCode.isEmpty()) {
            throw new IllegalArgumentException("Zip code is required");
        }
        if (this.zipCode.length() != 8) {
            throw new IllegalArgumentException("Zip code must be 8 digits");
        }
        if (this.number == null) {
            throw new IllegalArgumentException("Number is required");
        }
        if (this.number < 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }
    }

    public String toString() {
        return this.street + ", " + this.number + ", " + this.city + ", " + this.state + ", " + this.zipCode;
    }
}
