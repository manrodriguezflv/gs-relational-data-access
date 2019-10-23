package com.livevox.customer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class CustomerRequest {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    public CustomerRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "CustomerRequest[firstName='%s', lastName='%s']",
                firstName, lastName);
    }
}
