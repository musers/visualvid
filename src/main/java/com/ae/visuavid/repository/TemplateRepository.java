package com.ae.visuavid.repository;

import com.ae.visuavid.domain.TemplateEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, UUID> {
    public List<TemplateEntity> findByAdminMediaId(UUID adminMediaId);
}
