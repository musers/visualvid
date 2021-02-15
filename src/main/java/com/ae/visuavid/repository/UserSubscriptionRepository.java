package com.ae.visuavid.repository;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionEntity, UUID> {
    public UserSubscriptionEntity findByRazorPayOrderId(String razorPayOrderId);
}
