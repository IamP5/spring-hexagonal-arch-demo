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

    void changeName(String name);
    void changeEmail(String email);
    void changeAddress(Address address);
    void activate();
    void deactivate();
    void incrementRewardPoints(BigDecimal rewardPoints);
    void decrementRewardPoints(BigDecimal rewardPoints);
    void validate();
}
