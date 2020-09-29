alter table media rename column category to category_id;
alter table media drop column s3_info_id;
alter table media add column thumb_nail_s3_key text;
alter table media add column preview_video_s3_key text;

alter table media_slide add column slide_name text;
alter table media_slide add column screen_shot_s3_key text;
alter table media_slide add column slide_order int;
