package com.ae.visuavid.domain;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_subscription_downloads")
public class UserSubscriptionDownloadEntity extends AbstractAuditingEntity implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_subscription_id")
    private UserSubscriptionEntity userSubscription;

    @Column(name = "media_id")
    private UUID mediaId;

    @Column(name = "downloaded_date")
    private Date downloadedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserSubscriptionEntity getUserSubscription() {
        return userSubscription;
    }

    public void setUserSubscription(UserSubscriptionEntity userSubscription) {
        this.userSubscription = userSubscription;
    }

    public UUID getMediaId() {
        return mediaId;
    }

    public void setMediaId(UUID mediaId) {
        this.mediaId = mediaId;
    }

    public Date getDownloadedDate() {
        return downloadedDate;
    }

    public void setDownloadedDate(Date downloadedDate) {
        this.downloadedDate = downloadedDate;
    }
}
