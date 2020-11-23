package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.OrderService;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderResource {
    private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

    @Autowired
    protected OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO orderDTO) {
        log.info("Inside create order Resource ");
        orderDTO = orderService.create(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO orderDTO) {
        log.info("Inside create order Resource ");
        if (orderDTO.getId() != null) {
            orderDTO = orderService.create(orderDTO);
            return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
        } else {
            throw new ApiRuntimeException("cannot update order without Id");
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> create(@PathVariable("id") String id) {
        log.info("Inside get order Resource ");
        OrderDTO orderDTO = orderService.findOrderById(UUID.fromString(id));
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
