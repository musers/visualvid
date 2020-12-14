package com.ae.visuavid.repository;

import com.ae.visuavid.domain.OrderSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderSlideRepository extends JpaRepository<OrderSlideEntity, UUID> {}
