create table media (
 id UUID NOT NULL  PRIMARY KEY,
 name VARCHAR(30),
 category VARCHAR(30),
 description TEXT,
 indian_price NUMERIC(12,2),
 usd_price NUMERIC(12,2),
 preview_video_s3_url TEXT,
 thumb_nail_s3_url TEXT,
 media_placeholders TEXT,
 text_placeholders TEXT,
 turn_around_time VARCHAR(30),
 created_on DATETIME,
 updated_on DATETIME,
 created_by VARCHAR(30),
 updated_by VARCHAR(30)
 );
 
 create table media_slide (
 id UUID NOT NULL  PRIMARY KEY,
 media_id UUID NOT NULL,
 screen_shot_s3_url TEXT,
 created_on DATETIME,
 updated_on DATETIME,
 created_by VARCHAR(30),
 updated_by VARCHAR(30)
 );
 
 create table slide_items (
 id UUID NOT NULL  PRIMARY KEY,
 media_slide_id UUID NOT NULL,
 type VARCHAR(10),
 label VARCHAR(20),
 media_order INT,
 created_on DATETIME,
 updated_on DATETIME,
 created_by VARCHAR(30),
 updated_by VARCHAR(30)
 );
 
 ALTER TABLE PUBLIC.media_slide ADD CONSTRAINT fk_media_id FOREIGN KEY (media_id) REFERENCES PUBLIC.media (id);
 ALTER TABLE PUBLIC.slide_items ADD CONSTRAINT fk_media_slide_id FOREIGN KEY (media_slide_id) REFERENCES PUBLIC.media_slide (id);