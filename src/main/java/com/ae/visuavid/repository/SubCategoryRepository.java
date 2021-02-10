package com.ae.visuavid.repository;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubCategoryEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {
    public List<SubCategoryEntity> findByCategory(CategoryEntity categoryEntity);
}
