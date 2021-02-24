ALTER TABLE subscription DROP COLUMN price;
ALTER TABLE subscription DROP COLUMN order_count;
ALTER TABLE subscription DROP COLUMN order_limit;
ALTER TABLE subscription DROP COLUMN description;

ALTER TABLE subscription ADD COLUMN monthly_price_local NUMERIC(12,2);
ALTER TABLE subscription ADD COLUMN yearly_price_local NUMERIC(12,2);
ALTER TABLE subscription ADD COLUMN monthly_price_usd NUMERIC(12,2);
ALTER TABLE subscription ADD COLUMN yearly_price_usd NUMERIC(12,2);
ALTER TABLE subscription ADD COLUMN download_count INTEGER;
ALTER TABLE subscription ADD COLUMN download_limit_per_day INTEGER;
ALTER TABLE subscription ADD COLUMN unlimited_download_enable BOOLEAN;
ALTER TABLE subscription ADD COLUMN text_line_01 VARCHAR(250);
ALTER TABLE subscription ADD COLUMN text_line_02 VARCHAR(250);
ALTER TABLE subscription ADD COLUMN text_line_03 VARCHAR(250);
ALTER TABLE subscription ADD COLUMN text_line_04 VARCHAR(250);

ALTER TABLE user_subscription ADD COLUMN unlimited_download_enable BOOLEAN;
ALTER TABLE user_subscription ADD COLUMN latest_download_time TIMESTAMP;
