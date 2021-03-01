package com.ae.visuavid.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subscription")
public class SubscriptionEntity extends AbstractAuditingEntity implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "subscription_type")
    private String type;

    @Column(name = "monthly_price_local")
    private BigDecimal monthlyPriceLocal;

    @Column(name = "yearly_price_local")
    private BigDecimal yearlyPriceLocal;

    @Column(name = "monthly_price_usd")
    private BigDecimal monthlyPriceUsd;

    @Column(name = "yearly_price_usd")
    private BigDecimal yearlyPriceUsd;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage;

    @Column(name = "download_count")
    private Integer downloads;

    @Column(name = "download_limit_per_day")
    private Integer downloadPerDay;

    @Column(name = "unlimited_download_enable")
    private Boolean unLimitedDownloadsEnable;

    @Column(name = "text_line_01")
    private String textLine01;

    @Column(name = "text_line_02")
    private String textLine02;

    @Column(name = "text_line_03")
    private String textLine03;

    @Column(name = "text_line_04")
    private String textLine04;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "subscription")
    private UserSubscriptionEntity userSubscriptionEntity;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
        name = "subscription_category",
        joinColumns = @JoinColumn(name = "subscription_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<CategoryEntity> categories = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMonthlyPriceLocal() {
        return monthlyPriceLocal;
    }

    public void setMonthlyPriceLocal(BigDecimal monthlyPriceLocal) {
        this.monthlyPriceLocal = monthlyPriceLocal;
    }

    public BigDecimal getYearlyPriceLocal() {
        return yearlyPriceLocal;
    }

    public void setYearlyPriceLocal(BigDecimal yearlyPriceLocal) {
        this.yearlyPriceLocal = yearlyPriceLocal;
    }

    public BigDecimal getMonthlyPriceUsd() {
        return monthlyPriceUsd;
    }

    public void setMonthlyPriceUsd(BigDecimal monthlyPriceUsd) {
        this.monthlyPriceUsd = monthlyPriceUsd;
    }

    public BigDecimal getYearlyPriceUsd() {
        return yearlyPriceUsd;
    }

    public void setYearlyPriceUsd(BigDecimal yearlyPriceUsd) {
        this.yearlyPriceUsd = yearlyPriceUsd;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getDownloadPerDay() {
        return downloadPerDay;
    }

    public void setDownloadPerDay(Integer downloadPerDay) {
        this.downloadPerDay = downloadPerDay;
    }

    public String getTextLine01() {
        return textLine01;
    }

    public void setTextLine01(String textLine01) {
        this.textLine01 = textLine01;
    }

    public String getTextLine02() {
        return textLine02;
    }

    public void setTextLine02(String textLine02) {
        this.textLine02 = textLine02;
    }

    public String getTextLine03() {
        return textLine03;
    }

    public void setTextLine03(String textLine03) {
        this.textLine03 = textLine03;
    }

    public String getTextLine04() {
        return textLine04;
    }

    public void setTextLine04(String textLine04) {
        this.textLine04 = textLine04;
    }

    public UserSubscriptionEntity getUserSubscriptionEntity() {
        return userSubscriptionEntity;
    }

    public void setUserSubscriptionEntity(UserSubscriptionEntity userSubscriptionEntity) {
        this.userSubscriptionEntity = userSubscriptionEntity;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public Boolean getUnLimitedDownloadsEnable() {
        return unLimitedDownloadsEnable;
    }

    public void setUnLimitedDownloadsEnable(Boolean unLimitedDownloadsEnable) {
        this.unLimitedDownloadsEnable = unLimitedDownloadsEnable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
