create table template (
id UUID PRIMARY KEY,
admin_media_id UUID NOT NULL,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30)
);

create table template_slide (
id UUID PRIMARY KEY,
template_id UUID NOT NULL,
screen_shot_s3_url TEXT,
screen_shot_s3_key TEXT,
slide_name VARCHAR(30),
slide_order INT,
instructions TEXT,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30)
);

create table template_slide_items (
id UUID PRIMARY KEY,
template_slide_id UUID NOT NULL,
item_type VARCHAR(10),
item_label VARCHAR(30),
item_value TEXT,
screen_shot_s3_url TEXT,
screen_shot_s3_key TEXT,
item_order INT,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30)
);

ALTER TABLE PUBLIC.template_slide ADD CONSTRAINT fk_template_id FOREIGN KEY (template_id) REFERENCES PUBLIC.template (id);
ALTER TABLE PUBLIC.template_slide_items ADD CONSTRAINT fk_template_slide_id FOREIGN KEY (template_slide_id) REFERENCES PUBLIC.template_slide (id);

