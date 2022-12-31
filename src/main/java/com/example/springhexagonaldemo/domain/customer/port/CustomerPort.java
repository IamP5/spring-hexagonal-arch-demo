package com.example.springhexagonaldemo.domain.customer.port;

import com.example.springhexagonaldemo.domain.customer.valueobject.Address;

import java.math.BigDecimal;
import java.util.UUID;

public interface CustomerPort {
    UUID getId();
    String getName();
    String getEmail();
    Address getAddress();
    Boolean isActive();
    BigDecimal getRewardPoints();

    void changeName(String name) throws IllegalArgumentException;
    void changeEmail(String email) throws IllegalArgumentException;
    void changeAddress(Address address);
    void activate() throws IllegalArgumentException;
    void deactivate();
    void incrementRewardPoints(BigDecimal rewardPoints) throws IllegalArgumentException;
    void decrementRewardPoints(BigDecimal rewardPoints) throws IllegalArgumentException;
    void validate() throws IllegalArgumentException;
}
