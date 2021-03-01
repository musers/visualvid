ALTER TABLE user_subscription ADD COLUMN user_name VARCHAR(50);
ALTER TABLE subscription ADD COLUMN status VARCHAR(50);

CREATE TABLE PUBLIC.user_subscription_downloads (
id UUID NOT NULL,
user_subscription_id UUID NOT NULL,
download_count INTEGER,
media_id UUID,
created_date TIMESTAMP,
last_modified_date TIMESTAMP,
created_by VARCHAR(30),
last_modified_by VARCHAR(30),
CONSTRAINT pk_user_subscription_download_id PRIMARY KEY (id)
);


ALTER TABLE PUBLIC.user_subscription_downloads ADD CONSTRAINT fk_user_subscription_id FOREIGN KEY (user_subscription_id) REFERENCES PUBLIC.user_subscription (id);





