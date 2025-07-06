package com.example.payment_service.dto;

import com.example.payment_service.model.SubscriptionStatusEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSubscriptionRequest {


      @NotNull(message = "status is required")
    private SubscriptionStatusEnum status;
}
