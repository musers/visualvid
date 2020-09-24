package com.ae.visuavid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.visuavid.domain.MediaEntity;

@Repository
public interface AdminUploadFormRepository extends JpaRepository<MediaEntity, UUID> {

}
