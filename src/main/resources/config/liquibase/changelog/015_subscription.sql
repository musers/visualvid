CREATE TABLE PUBLIC.subscription (
id UUID NOT NULL,
subscription_type VARCHAR(20),
name VARCHAR(50) NOT NULL,
description TEXT,
price  NUMERIC(12,2),
discount_amount NUMERIC(12,2),
discount_percentage NUMERIC(12,2),
order_count INTEGER,
order_limit INTEGER,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30),
CONSTRAINT pk_subscription_id PRIMARY KEY (id)
);

CREATE TABLE PUBLIC.user_subscription (
id UUID NOT NULL,
subscription_id UUID NOT NULL,
user_id UUID NOT NULL,
status VARCHAR(20),
basic_amount  NUMERIC(12,2),
discount_amount NUMERIC(12,2),
total_amount INTEGER,
order_count INTEGER,
availed_orders INTEGER,
start_date TIMESTAMP,
end_date TIMESTAMP,
razor_pay_order_id VARCHAR(50),
razor_pay_payment_id VARCHAR(50),
currency_code VARCHAR(10),
payment_order_id VARCHAR(50),
category_id VARCHAR(50),
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30),
CONSTRAINT pk_user_subscription_id PRIMARY KEY (id)
);

CREATE TABLE PUBLIC.subscription_category (
subscription_id UUID NOT NULL,
category_id UUID NOT NULL,
PRIMARY KEY(subscription_id, category_id),
CONSTRAINT fk_subscription_id FOREIGN  KEY (subscription_id) REFERENCES  PUBLIC.subscription(id),
CONSTRAINT fk_category_id FOREIGN  KEY (category_id) REFERENCES  PUBLIC.category(id)
);

ALTER TABLE category ADD COLUMN s3_cover_image_key TEXT;
ALTER TABLE category ADD COLUMN s3_cover_image_url TEXT;

