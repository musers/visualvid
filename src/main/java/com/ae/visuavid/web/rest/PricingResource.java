package com.ae.visuavid.web.rest;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.service.PricingService;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
public class PricingResource {
    private static final Logger log = LoggerFactory.getLogger(PricingResource.class);

    @Autowired
    private PricingService pricingService;

    @Autowired
    private AdminUploadFormRepository adminUploadFormRepository;

    @PostMapping("/computeprice")
    public ResponseEntity<PricingDTO> computePricing(@RequestBody ItemCustomizationDTO itemCustomizationDTO) {
        Optional<AdminMediaEntity> adminMediaEntity = adminUploadFormRepository.findById(itemCustomizationDTO.getAdminMediaId());
        PricingDTO pricingDTO = pricingService.computePrice(adminMediaEntity.get(), itemCustomizationDTO);
        return new ResponseEntity<>(pricingDTO, HttpStatus.OK);
    }
}
