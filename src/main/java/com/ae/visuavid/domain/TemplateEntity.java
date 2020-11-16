package com.ae.visuavid.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TemplateEntity extends AbstractAuditingEntity implements BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "admin_media_id")
    private UUID adminMediaId;

    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<TemplateSlideEntity> slides;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(UUID adminMediaId) {
        this.adminMediaId = adminMediaId;
    }

    public List<TemplateSlideEntity> getSlides() {
        return slides;
    }

    public void setSlides(List<TemplateSlideEntity> slides) {
        this.slides = slides;
    }
}
