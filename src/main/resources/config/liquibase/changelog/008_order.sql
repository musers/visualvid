create table vvid_order(
    id UUID PRIMARY KEY,
    admin_media_id UUID,
    name TEXT,
    description TEXT,
    order_id VARCHAR(30) UNIQUE,
    sales_id VARCHAR(30) UNIQUE,
    order_status VARCHAR(30),
    assigned_user_id VARCHAR(30),
    assigned_user_name VARCHAR(30),
    category_id VARCHAR(30),
    category_name VARCHAR(30),
    preview_video_s3_url TEXT,
    thumb_nail_s3_url TEXT,
    thumb_nail_s3_key TEXT,
    preview_video_s3_key TEXT,
    media_placeholders TEXT,
    text_placeholders TEXT,
    tags TEXT,
    razor_pay_order_id TEXT,
    razor_pay_payment_id TEXT,
    currency_code VARCHAR(5),
    turn_around_time INTEGER,
    basic_amount NUMERIC(12,2),
    discount_amount NUMERIC(12,2),
    adv_customization_amount NUMERIC(12,2),
    premium_delivery_amount NUMERIC(12,2),
    gst_amount NUMERIC(12,2),
    total_amount NUMERIC(12,2),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    created_by VARCHAR(30),
    last_modified_by VARCHAR(30),
    coupon_discount_amount NUMERIC(12,2),
    coupon_discount_percentage INTEGER,
    gst_percentage INTEGER,
    coupon_code VARCHAR(30),
    payment_order_id VARCHAR(50)
);

create table vvid_order_slide (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    screen_shot_s3_url TEXT,
    screen_shot_s3_key TEXT,
    slide_name VARCHAR(30),
    slide_order INTEGER,
    admin_slide_id UUID NOT NULL,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    created_by VARCHAR(30),
    last_modified_by VARCHAR(30)
);

create table vvid_order_slide_items (
    id UUID PRIMARY KEY,
    order_slide_id UUID NOT NULL,
    item_type VARCHAR(10),
    item_label TEXT,
    item_value TEXT,
    s3_url TEXT,
    s3_key TEXT,
    item_order INTEGER,
    admin_slide_item_id UUID NOT NULL,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    created_by VARCHAR(30),
    last_modified_by VARCHAR(30)
);

ALTER TABLE PUBLIC.vvid_order_slide
    ADD CONSTRAINT fk_vvid_order_id FOREIGN KEY (order_id) REFERENCES PUBLIC.vvid_order (id);
ALTER TABLE PUBLIC.vvid_order_slide_items
    ADD CONSTRAINT fk_vvid_order_slide_id FOREIGN KEY (order_slide_id) REFERENCES PUBLIC.vvid_order_slide (id);

create table coupon_info(
  id UUID PRIMARY KEY,
  description TEXT,
  coupon_code VARCHAR(30),
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  coupon_discount_percentage INTEGER,
  coupon_discount_amount NUMERIC(12,2),
  active BOOLEAN NOT NULL,
  created_date TIMESTAMP,
  last_modified_date TIMESTAMP,
  created_by VARCHAR(30),
  last_modified_by VARCHAR(30)
);

CREATE SEQUENCE "ORDER_ID_SEQ" START WITH 1;
CREATE SEQUENCE "SALES_ID_SEQ" START WITH 1;
