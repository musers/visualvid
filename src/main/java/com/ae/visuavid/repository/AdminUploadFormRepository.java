package com.ae.visuavid.repository;

import com.ae.visuavid.domain.AdminMediaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUploadFormRepository extends JpaRepository<AdminMediaEntity, UUID> {}
