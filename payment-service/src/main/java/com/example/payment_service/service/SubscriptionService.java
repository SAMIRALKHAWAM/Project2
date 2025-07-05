package com.example.payment_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.payment_service.client.CourseClient;
import com.example.payment_service.client.UserClient;
import com.example.payment_service.dto.SubscriptionRequest;
import com.example.payment_service.model.Subscription;
import com.example.payment_service.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserClient userClient;
    private final CourseClient courseClient;

    public Subscription createSubscription(SubscriptionRequest request,String studentEmail) {
        Subscription subscription = new Subscription();
        Long UserId = userClient.getStuduntIdByEmail(studentEmail);
        Long CourseId = courseClient.getCourseIdIfExistAndApproved(request.getCourseId());

        boolean exists = subscriptionRepository.existsByUserIdAndCourseId(UserId, CourseId);
    if (exists) {
        throw new IllegalStateException("Subscription already exists for user and course");
    }

        subscription.setUserId(UserId);
        subscription.setCourseId(CourseId);
        subscription.setStatus("PENDING");
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByUser(String studentEmail) {
        Long UserId = userClient.getStuduntIdByEmail(studentEmail);
        return subscriptionRepository.findByUserId(UserId);
    }
}

