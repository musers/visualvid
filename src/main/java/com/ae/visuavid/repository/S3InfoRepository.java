package com.ae.visuavid.repository;

import com.ae.visuavid.domain.S3InfoEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3InfoRepository extends JpaRepository<S3InfoEntity, UUID> {
    public S3InfoEntity findByS3Key(String s3Key);

    public List<S3InfoEntity> findByS3KeyIn(List<String> s3Keys);
}
