package com.frendscode.Customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {

}
