 CREATE TABLE PUBLIC.category (
id UUID NOT NULL,
category_id VARCHAR(20),
category_name VARCHAR(20),
created_by         VARCHAR(50) NOT NULL,
created_date       TIMESTAMP,
last_modified_by   VARCHAR(50),
last_modified_date TIMESTAMP,
CONSTRAINT pk_id PRIMARY KEY (id)
);

INSERT INTO category (id,category_id,category_name,created_by,created_date,last_modified_by,last_modified_date) values ('4dd486a0-0623-11eb-adc1-0242ac120002','Family_Invitations','Family Invitations','ADMIN',Now(),'ADMIN',Now());
INSERT INTO category (id,category_id,category_name,created_by,created_date,last_modified_by,last_modified_date) values ('4dd4889e-0623-11eb-adc1-0242ac120002','Logo_Reveals','Logo Reveals','ADMIN',Now(),'ADMIN',Now());
INSERT INTO category (id,category_id,category_name,created_by,created_date,last_modified_by,last_modified_date) values ('4dd4898e-0623-11eb-adc1-0242ac120002','Corporate','Corporate','ADMIN',Now(),'ADMIN',Now());
INSERT INTO category (id,category_id,category_name,created_by,created_date,last_modified_by,last_modified_date) values ('4dd48a60-0623-11eb-adc1-0242ac120002','Restaurant_Displays','Restaurant Displays','ADMIN',Now(),'ADMIN',Now());
INSERT INTO category (id,category_id,category_name,created_by,created_date,last_modified_by,last_modified_date) values ('4dd48c90-0623-11eb-adc1-0242ac120002','Party_Invitations','Party Invitations','ADMIN',Now(),'ADMIN',Now());
