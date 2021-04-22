package com.ae.visuavid.service.dto;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class UserSubscriptionPermissionDTO implements BaseDTO {
    private UUID userSubscriptionId;
    private Boolean isSubscribedUser;
    private Boolean isSubscriptionActive;
    private Boolean isSubscribedCategory;
    private Boolean isDownloadLimitPerDayExceeded;
    private Boolean isEligibleToDownload;

    public UUID getUserSubscriptionId() {
        return userSubscriptionId;
    }

    public void setUserSubscriptionId(UUID userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    public Boolean getSubscribedUser() {
        return isSubscribedUser;
    }

    public void setSubscribedUser(Boolean subscribedUser) {
        isSubscribedUser = subscribedUser;
    }

    public Boolean getSubscriptionActive() {
        return isSubscriptionActive;
    }

    public void setSubscriptionActive(Boolean subscriptionActive) {
        isSubscriptionActive = subscriptionActive;
    }

    public Boolean getSubscribedCategory() {
        return isSubscribedCategory;
    }

    public void setSubscribedCategory(Boolean subscribedCategory) {
        isSubscribedCategory = subscribedCategory;
    }

    public Boolean getDownloadLimitPerDayExceeded() {
        return isDownloadLimitPerDayExceeded;
    }

    public void setDownloadLimitPerDayExceeded(Boolean downloadLimitPerDayExceeded) {
        isDownloadLimitPerDayExceeded = downloadLimitPerDayExceeded;
    }

    public Boolean getEligibleToDownload() {
        return isEligibleToDownload;
    }

    public void setEligibleToDownload(Boolean eligibleToDownload) {
        isEligibleToDownload = eligibleToDownload;
    }
}
