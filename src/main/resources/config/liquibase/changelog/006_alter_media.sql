ALTER TABLE media ADD COLUMN template_count numeric(12,2);
ALTER TABLE media ADD COLUMN earnings numeric(12,2);
ALTER TABLE media ALTER COLUMN name TYPE TEXT;
