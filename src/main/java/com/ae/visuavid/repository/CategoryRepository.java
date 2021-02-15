package com.ae.visuavid.repository;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubscriptionEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findByNameIgnoreCase(String newName);
    List<CategoryEntity> findByIdIn(List<UUID> ids);
}
