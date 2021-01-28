ALTER TABLE media DROP COLUMN category_id;
ALTER TABLE media ADD COLUMN category_id VARCHAR(50);
ALTER TABLE media ADD COLUMN sub_category_id VARCHAR(50);
