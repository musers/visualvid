ALTER TABLE media ALTER COLUMN name TYPE TEXT;
ALTER TABLE media ADD COLUMN advanced_customization numeric(12,2);
ALTER TABLE media ADD COLUMN premium_delivery numeric(12,2);

create table cart_order (
id UUID PRIMARY KEY,
user_id VARCHAR(30)  NOT NULL,
status VARCHAR(10),
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30)
);

create table order_line (
id UUID PRIMARY KEY,
order_id UUID NOT NULL,
admin_media_id UUID NOT NULL,
template_id UUID,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30)
);

ALTER TABLE PUBLIC.order_line ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES PUBLIC.cart_order (id);

