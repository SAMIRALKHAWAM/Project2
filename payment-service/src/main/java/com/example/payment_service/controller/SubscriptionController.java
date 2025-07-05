package com.example.payment_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.SubscriptionRequest;
import com.example.payment_service.model.Subscription;
import com.example.payment_service.service.SubscriptionService;

import lombok.RequiredArgsConstructor;


import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/add_subscription")
    public ResponseEntity<Subscription> createSubscription(@RequestBody SubscriptionRequest request,Authentication authentication) {
         String email = authentication.getName();
        Subscription subscription = subscriptionService.createSubscription(request,email);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptionsByUser(Authentication authentication) {
          String email = authentication.getName();
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(email);
        return ResponseEntity.ok(subscriptions);
    }


    
}
