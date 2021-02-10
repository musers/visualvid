package com.ae.visuavid.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vvid_order_slide_items")
public class OrderSlideItemEntity extends AbstractAuditingEntity implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_label")
    private String itemLabel;

    @Column(name = "item_value")
    private String itemValue;

    @Column(name = "item_order")
    private Integer itemOrder;

    @Column(name = "s3_url")
    private String s3Url;

    @Column(name = "s3_key")
    private String s3Key;

    @Column(name = "admin_slide_item_id")
    private UUID adminSlideItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_slide_id")
    private OrderSlideEntity orderSlide;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public UUID getAdminSlideItemId() {
        return adminSlideItemId;
    }

    public void setAdminSlideItemId(UUID adminSlideItemId) {
        this.adminSlideItemId = adminSlideItemId;
    }

    public OrderSlideEntity getOrderSlide() {
        return orderSlide;
    }

    public void setOrderSlide(OrderSlideEntity orderSlide) {
        this.orderSlide = orderSlide;
    }

}
