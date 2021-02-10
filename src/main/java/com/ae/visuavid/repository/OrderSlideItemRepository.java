package com.ae.visuavid.repository;

import com.ae.visuavid.domain.OrderSlideItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderSlideItemRepository extends JpaRepository<OrderSlideItemEntity, UUID> {}
