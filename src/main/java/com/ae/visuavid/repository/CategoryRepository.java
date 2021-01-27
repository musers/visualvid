package com.ae.visuavid.repository;

import com.ae.visuavid.domain.CategoryEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    public CategoryEntity findByCategoryId(String categoryId);
}
