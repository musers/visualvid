package com.ae.visuavid.repository;

import com.ae.visuavid.domain.MediaSlideEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaSlideRepository extends JpaRepository<MediaSlideEntity, UUID> {}
