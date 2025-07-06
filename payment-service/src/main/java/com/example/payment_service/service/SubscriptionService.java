package com.example.payment_service.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.payment_service.client.CourseClient;
import com.example.payment_service.client.UserClient;
import com.example.payment_service.dto.SubscriptionRequest;
import com.example.payment_service.dto.UpdateSubscriptionRequest;
import com.example.payment_service.model.Payment;
import com.example.payment_service.model.Subscription;
import com.example.payment_service.model.SubscriptionStatusEnum;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.SubscriptionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final PaymentRepository paymentRepository;
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
        subscription.setStatus(SubscriptionStatusEnum.PENDING);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByUser(String studentEmail) {
        Long UserId = userClient.getStuduntIdByEmail(studentEmail);
        return subscriptionRepository.findByUserId(UserId);
    }


    public String ChangeSubscriptionStatus(Long id,UpdateSubscriptionRequest status, String  email) {
        Long adminId = userClient.getAdminIdByEmail(email);
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("subscription not found with id: " + id));

        if (!subscription.getStatus().equals(SubscriptionStatusEnum.PENDING)) {
            throw new IllegalStateException("Subscription status is not Pending");
        }

             subscription.setStatus(status.getStatus());
             if (status.getStatus().equals(SubscriptionStatusEnum.APPROVED)) {
                 Payment payment = new Payment();
                 payment.setSubscriptionId(id);
                payment = paymentRepository.save(payment);

                  subscription.setPaymentId(payment.getId());
                  
             }
               subscriptionRepository.save(subscription);
       
         return "subscription Update Successfully";
    

}

}