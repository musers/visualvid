package com.ae.visuavid.service;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class PricingService {
    public PricingDTO computePrice(@NotNull AdminMediaEntity adminMediaEntity, ItemCustomizationDTO itemCustomization) {
        PricingDTO pricing = new PricingDTO();
        // TODO compute
        return pricing;
    }
}
