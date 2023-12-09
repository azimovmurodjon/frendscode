package com.frendscode.Customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {

}
