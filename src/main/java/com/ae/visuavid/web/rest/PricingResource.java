package com.ae.visuavid.web.rest;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.service.PricingService;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pricing")
public class PricingResource {
    private static final Logger log = LoggerFactory.getLogger(PricingResource.class);

    @Autowired
    private PricingService pricingService;

    @Autowired
    private AdminUploadFormRepository adminUploadFormRepository;

    @GetMapping("/{adminMediaId}")
    public ResponseEntity<PricingDTO> computePricing(@PathVariable("adminMediaId") UUID adminMediaId, @RequestBody ItemCustomizationDTO itemCustomizationDTO) {
        Optional<AdminMediaEntity> adminMediaEntity = adminUploadFormRepository.findById(adminMediaId);
        PricingDTO pricingDTO = pricingService.computePrice(adminMediaEntity.get(), itemCustomizationDTO);
        return new ResponseEntity<>(pricingDTO, HttpStatus.OK);
    }
}
