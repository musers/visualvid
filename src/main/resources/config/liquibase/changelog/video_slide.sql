create table media (
 id UUID NOT NULL  PRIMARY KEY,
 name VARCHAR(30),
 category VARCHAR(30),
 description TEXT,
 indian_price NUMERIC(12,2),
 usd_price NUMERIC(12,2),
 s3_info_id UUID NOT NULL,
 preview_video_s3_url TEXT,
 thumb_nail_s3_url TEXT,
 media_placeholders TEXT,
 text_placeholders TEXT,
 turn_around_time VARCHAR(30),
 created_date TIMESTAMP,
 last_modified_date TIMESTAMP,
 created_by VARCHAR(30),
 last_modified_by VARCHAR(30)
 );

 create table media_slide (
 id UUID NOT NULL  PRIMARY KEY,
 media_id UUID NOT NULL,
 screen_shot_s3_url TEXT,
 created_date TIMESTAMP,
 last_modified_date TIMESTAMP,
 created_by VARCHAR(30),
 last_modified_by VARCHAR(30)
 );

 create table slide_items (
 id UUID NOT NULL  PRIMARY KEY,
 media_slide_id UUID NOT NULL,
 type VARCHAR(10),
 label VARCHAR(100),
 media_order INT,
 created_date TIMESTAMP,
 last_modified_date TIMESTAMP,
 created_by VARCHAR(30),
 last_modified_by VARCHAR(30)
 );

 create table s3_info (
 id UUID NOT NULL  PRIMARY KEY,
 media_name VARCHAR(30),
 media_s3_key VARCHAR(30),
 url TEXT,
 status VARCHAR(30),
 media_size INT,
 created_date TIMESTAMP,
 last_modified_date TIMESTAMP,
 created_by VARCHAR(30),
 last_modified_by VARCHAR(30)
 );

 ALTER TABLE PUBLIC.media ADD CONSTRAINT fk_s3_info_id FOREIGN KEY (s3_info_id) REFERENCES PUBLIC.s3_info (id);
 ALTER TABLE PUBLIC.media_slide ADD CONSTRAINT fk_media_id FOREIGN KEY (media_id) REFERENCES PUBLIC.media (id);
 ALTER TABLE PUBLIC.slide_items ADD CONSTRAINT fk_media_slide_id FOREIGN KEY (media_slide_id) REFERENCES PUBLIC.media_slide (id);

 INSERT INTO s3_info (id, media_name,media_s3_key,url,status,media_size) VALUES
('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'IMAGE', 's3_key', 's3_url', 'InProgress', 250);
