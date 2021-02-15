package com.ae.visuavid.repository;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubscriptionEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
    //@Query("SELECT s FROM SubscriptionEntity s WHERE s.categories IN ?1")
    public List<SubscriptionEntity> findByCategoriesIn(List<CategoryEntity> categories);
}
