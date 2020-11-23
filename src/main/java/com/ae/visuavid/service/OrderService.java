package com.ae.visuavid.service;

import com.ae.visuavid.domain.OrderEntity;
import com.ae.visuavid.repository.OrderRepository;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.service.mapper.OrderMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected OrderMapper orderMapper;

    public OrderDTO create(OrderDTO orderDTO) {
        log.info("Inside create order-service");
        try {
            OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
            orderEntity.setStatus("Accepted");
            orderRepository.save(orderEntity);
            log.info("order saved successfully & OrderId is: {}  ", orderEntity.getId());
            return orderMapper.toDto(orderEntity);
        } catch (Exception e) {
            log.error("error while saving order {} ", e);
            throw new ApiRuntimeException("error creating while order " + e.getMessage());
        }
    }

    public OrderDTO update(OrderDTO orderDTO) {
        log.info("Inside update order-service");
        try {
            OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
            orderRepository.save(orderEntity);
            log.info("order updated successfully & OrderId is: {}  ", orderEntity.getId());
            return orderMapper.toDto(orderEntity);
        } catch (Exception e) {
            log.error("error while updating order {} ", e);
            throw new ApiRuntimeException("error updating while order " + e.getMessage());
        }
    }

    public OrderDTO findOrderById(UUID id) {
        log.info("Finding order by Id {} ", id);
        try {
            Optional<OrderEntity> orderEntity = orderRepository.findById(id);
            if (orderEntity.isPresent()) {
                return orderMapper.toDto(orderEntity.get());
            }
            return null;
        } catch (Exception e) {
            log.error("error while getting order by ID : {} : {} ", id, e);
            throw new ApiRuntimeException("error creating while getting order " + e.getMessage());
        }
    }
}
