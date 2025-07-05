package com.example.payment_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequest {

    @NotNull(message = "courseId must not be null")
    private Long courseId;

}
