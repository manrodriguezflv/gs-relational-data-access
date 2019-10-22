package com.livevox;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Customer {
	@JsonProperty
    private long id;
	@JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    // getters & setters omitted for brevity
}

