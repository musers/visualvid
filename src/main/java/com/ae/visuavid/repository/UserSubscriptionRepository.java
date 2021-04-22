package com.ae.visuavid.repository;

import com.ae.visuavid.domain.User;
import com.ae.visuavid.domain.UserSubscriptionDownloadEntity;
import com.ae.visuavid.domain.UserSubscriptionEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionEntity, UUID> {
    UserSubscriptionEntity findByRazorPayOrderId(String razorPayOrderId);
    List<UserSubscriptionEntity> findByUserNameContains(String userName);
    Page<UserSubscriptionEntity> findAll(Pageable pageable);
    List<UserSubscriptionEntity> findByUser(User user);
}
