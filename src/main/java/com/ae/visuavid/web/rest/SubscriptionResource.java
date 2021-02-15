package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.SubscriptionService;
import com.ae.visuavid.service.dto.SubscriptionDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionResource {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionResource.class);

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("/")
    public ResponseEntity<SubscriptionDTO> create(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionDTO = subscriptionService.save(subscriptionDTO);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<SubscriptionDTO> update(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        if (subscriptionDTO.getId() == null) {
            throw new ApiRuntimeException("ID cannot be null");
        }
        subscriptionDTO = subscriptionService.save(subscriptionDTO);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> findById(@PathVariable(name = "id") String id) {
        SubscriptionDTO dto = subscriptionService.findById(UUID.fromString(id));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/")
    public ResponseEntity<List<SubscriptionDTO>> findAll() {
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<SubscriptionDTO>> findByCateogory(@PathVariable(name = "id") String categoryId) {
        return ResponseEntity.ok(subscriptionService.findByCategory(categoryId));
    }
}
