package com.academy.mortgage.model.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
    Long personalNumber;
}
