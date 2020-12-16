package com.ae.visuavid.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "coupon_info")
public class CouponInfoEntity extends AbstractAuditingEntity implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "coupon_discount_amount")
    private BigDecimal couponDiscountAmount;

    @Column(name = "coupon_discount_percentage")
    private Integer couponDiscountPercentage;

    @Column(name = "coupon_code")
    private String couponCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getCouponDiscountAmount() {
        return couponDiscountAmount;
    }

    public void setCouponDiscountAmount(BigDecimal couponDiscountAmount) {
        this.couponDiscountAmount = couponDiscountAmount;
    }

    public Integer getCouponDiscountPercentage() {
        return couponDiscountPercentage;
    }

    public void setCouponDiscountPercentage(Integer couponDiscountPercentage) {
        this.couponDiscountPercentage = couponDiscountPercentage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
