package com.ae.visuavid.service;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubscriptionEntity;
import com.ae.visuavid.enumeration.SubscriptionStatusType;
import com.ae.visuavid.repository.CategoryRepository;
import com.ae.visuavid.repository.SubscriptionRepository;
import com.ae.visuavid.service.dto.SubscriptionDTO;
import com.ae.visuavid.service.mapper.SubscriptionMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionMapper subscriptionMapper;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public SubscriptionDTO save(SubscriptionDTO subscriptionDTO) {
        SubscriptionEntity entity = subscriptionMapper.toEntity(subscriptionDTO);
        List<UUID> categoriesIds = new ArrayList<>();
        categoriesIds.add(UUID.fromString("4dd486a0-0623-11eb-adc1-0242ac120002"));
        //subscriptionDTO.getCategories().stream().forEach(c -> categoriesIds.add(c.getId()));
        List<CategoryEntity> categories = categoryRepository.findByIdIn(categoriesIds);
        if (CollectionUtils.isEmpty(categories)) {
            throw new ApiRuntimeException("no categories found");
        }
        if (subscriptionDTO.getUnLimitedDownloadsEnable()) {
            entity.setUnLimitedDownloadsEnable(Boolean.TRUE);
        }
        entity.setStatus(SubscriptionStatusType.ACTIVE.name());
        entity.setCategories(categories);
        entity = subscriptionRepository.save(entity);
        return subscriptionMapper.toDto(entity);
    }

    public SubscriptionEntity findOne(UUID id) {
        Optional<SubscriptionEntity> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            return subscription.get();
        }
        return null;
    }

    public SubscriptionDTO findById(UUID id) {
        SubscriptionEntity entity = findOne(id);
        return subscriptionMapper.toDto(entity);
    }

    public List<SubscriptionDTO> findAll() {
        return subscriptionMapper.toDtos(subscriptionRepository.findAll());
    }

    public List<SubscriptionDTO> findByCategory(String categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(UUID.fromString(categoryId));
        if (categoryEntity.isPresent()) {
            List<CategoryEntity> categories = new ArrayList<>();
            categories.add(categoryEntity.get());
            List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findByCategoriesIn(categories);
            return subscriptionMapper.toDtos(subscriptionEntities);
        }
        return null;
    }

    public SubscriptionDTO updateStatus(UUID id, String status) {
        SubscriptionEntity entity = findOne(id);
        entity.setStatus(SubscriptionStatusType.valueOf(status).name());
        entity = subscriptionRepository.save(entity);
        return subscriptionMapper.toDto(entity);
    }
}
