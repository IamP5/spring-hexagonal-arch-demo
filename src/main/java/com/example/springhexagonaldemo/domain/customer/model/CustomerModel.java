package com.example.springhexagonaldemo.domain.customer.model;

import com.example.springhexagonaldemo.domain.customer.port.CustomerPort;
import com.example.springhexagonaldemo.domain.customer.valueobject.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CustomerModel implements CustomerPort {
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String name;

    private String email;

    private Address address;

    @Getter(AccessLevel.NONE)
    private Boolean active;

    private BigDecimal rewardPoints;

    public CustomerModel(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.active = false;
        this.rewardPoints = BigDecimal.ZERO;

        this.validate();
    }

    public void changeName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
        this.validate();
    }

    public void changeEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.email = email;
        this.validate();
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void activate() {
        if (this.address == null) {
            throw new IllegalArgumentException("Address is required to activate Customer");
        }

        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public Boolean isActive() {
        return this.active;
    }

    public void incrementRewardPoints(BigDecimal rewardPoints) {
        if (rewardPoints.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot increment negative reward points");
        }
        this.rewardPoints = this.rewardPoints.add(rewardPoints);
    }

    public void decrementRewardPoints(BigDecimal rewardPoints) {
        if (rewardPoints.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot decrement negative reward points");
        }

        if (this.rewardPoints.compareTo(rewardPoints) < 0) {
            throw new IllegalArgumentException("Customer does not have enough reward points");
        }

        this.rewardPoints = this.rewardPoints.subtract(rewardPoints);
    }

    public void validate() {
        if (this.id == null) {
            throw new IllegalArgumentException("Id is required");
        }
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!this.email.contains("@")) {
            throw new IllegalArgumentException("Email is invalid");
        }
        if (this.rewardPoints == null) {
            throw new IllegalArgumentException("Reward points is required");
        }
        if (this.rewardPoints.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Reward points must be greater than 0");
        }
    }
}
