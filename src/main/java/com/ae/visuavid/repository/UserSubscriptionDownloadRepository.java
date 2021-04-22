package com.ae.visuavid.repository;

import com.ae.visuavid.domain.UserSubscriptionDownloadEntity;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionDownloadRepository extends JpaRepository<UserSubscriptionDownloadEntity, UUID> {
    public List<UserSubscriptionDownloadEntity> findByDownloadedDate(Date currentDate);
}
