package com.ae.visuavid.repository;

import com.ae.visuavid.domain.AdminMediaEntity;
import java.util.List;
import java.util.UUID;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUploadFormRepository extends JpaRepository<AdminMediaEntity, UUID> {
    List<AdminMediaEntity> findByCategoryId(String categoryId);

    Page<AdminMediaEntity> findByCategoryId(Pageable pageable, String categoryId);

    Page<AdminMediaEntity> findByNameIgnoreCaseContaining(Pageable pageable, String mediaName);

    Page<AdminMediaEntity> findAll(Pageable pageable);

    Page<AdminMediaEntity> findByTagsIgnoreCaseContaining(Pageable pageable, Predicate predicate);
}
