package com.example.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payment_service.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

      boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}

