package com.ae.visuavid.repository;

import com.ae.visuavid.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    public List<OrderEntity> findByAdminMediaId(UUID adminMediaId);
}
