alter table media add column indian_disc_price numeric(12,2);
alter table media add column indian_adv_cust_price numeric(12,2);
alter table media add column indian_prem_delivery_price numeric(12,2);
alter table media add column usd_disc_price numeric(12,2);
alter table media add column usd_adv_cust_price numeric(12,2);
alter table media add column usd_prem_delivery_price numeric(12,2);

alter table media drop column advanced_customization;
alter table media drop column premium_delivery;
