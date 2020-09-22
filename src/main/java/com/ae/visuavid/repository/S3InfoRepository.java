package com.ae.visuavid.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.visuavid.domain.S3InfoEntity;

@Repository
public interface S3InfoRepository extends JpaRepository<S3InfoEntity, UUID>{

}
