 CREATE TABLE PUBLIC.sub_category (
id UUID NOT NULL,
category_id UUID,
sub_category_name VARCHAR(20),
created_by         VARCHAR(50) NOT NULL,
created_date       TIMESTAMP,
last_modified_by   VARCHAR(50),
last_modified_date TIMESTAMP,
CONSTRAINT pk_sub_category_id PRIMARY KEY (id)
);

ALTER TABLE media ADD COLUMN sub_category VARCHAR(50);


ALTER TABLE PUBLIC.sub_category ADD CONSTRAINT fk_category_id1 FOREIGN KEY (category_id) REFERENCES PUBLIC.category (id);


