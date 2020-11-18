CREATE TABLE PUBLIC.jhi_user 
  ( 
     id                 UUID NOT NULL, 
     login              VARCHAR(50) NOT NULL, 
     password_hash      VARCHAR(60), 
     first_name         VARCHAR(50), 
     last_name          VARCHAR(50), 
     email              VARCHAR(191), 
     image_url          VARCHAR(256), 
     activated          BOOLEAN NOT NULL, 
     lang_key           VARCHAR(10), 
     activation_key     VARCHAR(20), 
     reset_key          VARCHAR(20), 
     created_by         VARCHAR(50) NOT NULL, 
     created_date       TIMESTAMP, 
     reset_date         TIMESTAMP, 
     last_modified_by   VARCHAR(50), 
     last_modified_date TIMESTAMP, 
     CONSTRAINT pk_jhi_user PRIMARY KEY (id), 
     CONSTRAINT ux_user_email UNIQUE (email), 
     CONSTRAINT ux_user_login UNIQUE (login) 
  ); 
  
  CREATE TABLE PUBLIC.jhi_authority 
  ( 
     NAME VARCHAR(50) NOT NULL, 
     CONSTRAINT pk_jhi_authority PRIMARY KEY (NAME) 
  );
  CREATE TABLE PUBLIC.jhi_role 
  ( 
     NAME VARCHAR(50) NOT NULL, 
     CONSTRAINT pk_jhi_role PRIMARY KEY (NAME) 
  );
  
  CREATE TABLE PUBLIC.jhi_user_role 
  ( 
     user_id   UUID NOT NULL, 
     role_name VARCHAR(50) NOT NULL 
  );
  
  CREATE TABLE PUBLIC.jhi_role_authority 
  ( 
     role_name      VARCHAR(50) NOT NULL, 
     authority_name VARCHAR(50) NOT NULL 
  );
 
 CREATE TABLE PUBLIC.jhi_persistent_audit_event 
  ( 
     event_id   UUID NOT NULL, 
     principal  VARCHAR(50) NOT NULL, 
     event_date TIMESTAMP, 
     event_type VARCHAR(255), 
     CONSTRAINT pk_jhi_persistent_audit_event PRIMARY KEY (event_id) 
  );
  
  CREATE TABLE PUBLIC.jhi_persistent_audit_evt_data 
  ( 
     event_id UUID NOT NULL, 
     NAME     VARCHAR(150) NOT NULL, 
     value    VARCHAR(255) 
  );
 

ALTER TABLE PUBLIC.jhi_user_role ADD PRIMARY KEY (user_id, role_name);
ALTER TABLE PUBLIC.jhi_role_authority ADD PRIMARY KEY (role_name, authority_name);
ALTER TABLE PUBLIC.jhi_user_role ADD CONSTRAINT fk_role_name FOREIGN KEY (role_name) REFERENCES PUBLIC.jhi_role (name);
ALTER TABLE PUBLIC.jhi_role_authority ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES PUBLIC.jhi_authority (name);
ALTER TABLE PUBLIC.jhi_user_role ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES PUBLIC.jhi_user (id);
ALTER TABLE PUBLIC.jhi_user ALTER COLUMN password_hash SET NOT NULL;
  
INSERT INTO PUBLIC.jhi_user(id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by) 
VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa1', 'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', 
true, 'en', 'system', 'system');
 
INSERT INTO PUBLIC.jhi_user(id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by) 
VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa2', 'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', 
 true, 'en', 'system', 'system');
 
 
 INSERT INTO PUBLIC.jhi_user(id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by) 
 VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa3', 'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', 
 true, 'en', 'system', 'system');
 
 INSERT INTO PUBLIC.jhi_user(id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, created_by, last_modified_by) 
 VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa4', 'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', 
 true, 'en', 'system', 'system');
 
INSERT INTO PUBLIC.jhi_role(name) VALUES('ROLE_ADMIN');
INSERT INTO PUBLIC.jhi_role(name) VALUES('ROLE_USER');
 
INSERT INTO PUBLIC.jhi_authority(name) VALUES('ADD_USER');
INSERT INTO PUBLIC.jhi_authority(name) VALUES('EDIT_USER');
INSERT INTO PUBLIC.jhi_authority(name) VALUES('DELETE_USER');
INSERT INTO PUBLIC.jhi_authority(name) VALUES('DEFAULT');
INSERT INTO PUBLIC.jhi_authority(name) VALUES('ADMIN_USER');
 
INSERT INTO PUBLIC.jhi_user_role(user_id, role_name) VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa1', 'ROLE_ADMIN');
INSERT INTO PUBLIC.jhi_user_role(user_id, role_name) VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa2', 'ROLE_USER');
INSERT INTO PUBLIC.jhi_user_role(user_id, role_name) VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa3', 'ROLE_ADMIN');
INSERT INTO PUBLIC.jhi_user_role(user_id, role_name) VALUES('3fa85f64-5717-4562-b3fc-2c963f66afa4', 'ROLE_USER');


INSERT INTO PUBLIC.jhi_role_authority(role_name, authority_name) VALUES('ROLE_ADMIN', 'ADD_USER');
INSERT INTO PUBLIC.jhi_role_authority(role_name, authority_name) VALUES('ROLE_ADMIN', 'EDIT_USER');
INSERT INTO PUBLIC.jhi_role_authority(role_name, authority_name) VALUES('ROLE_ADMIN', 'DELETE_USER');
INSERT INTO PUBLIC.jhi_role_authority(role_name, authority_name) VALUES('ROLE_USER', 'DEFAULT');
INSERT INTO PUBLIC.jhi_role_authority(role_name, authority_name) VALUES('ROLE_ADMIN', 'ADMIN_USER');
 
 
 
ALTER TABLE PUBLIC.jhi_user ALTER COLUMN  created_date SET DEFAULT NULL;

ALTER TABLE PUBLIC.jhi_persistent_audit_evt_data ADD PRIMARY KEY (event_id, name);
CREATE INDEX idx_jhi_persistent_audit_evt_data ON PUBLIC.jhi_persistent_audit_event(principal, event_date);
CREATE INDEX idx_jhi_persistent_audit_evt_id ON PUBLIC.jhi_persistent_audit_evt_data(event_id);
ALTER TABLE PUBLIC.jhi_persistent_audit_evt_data ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES PUBLIC.jhi_persistent_audit_event (event_id);
   
INSERT INTO PUBLIC.DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) 
VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/initial_schema.sql', NOW(), 2, '8:b7397351cd15e88c5404e394e884dd63', 'createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_role; createTable tableName=jhi_user_role; createTable tableName=jhi_role_authority; addPrimaryKey tableName=jhi_user_role; addPrimaryKey tableName=jhi_...', '', 'EXECUTED', NULL, NULL, '3.9.0', '0619264042');
 