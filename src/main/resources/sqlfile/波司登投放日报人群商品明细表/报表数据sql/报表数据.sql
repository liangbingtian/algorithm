CREATE VIEW
`jd_custom_report_keyword`
(`username`, `date_str`, `trans_days`, `caliber`, `order_status`, `gift_flag`, `effect`, `is_daily`, `start_date`, `end_date`, `period`, `pin`, `business`, `campaign_type`, `campaign_id`, `campaign_name`, `group_id`, `group_name`, `kw_id`, `keyword`, `target_type`, `sku_brand_id`, `sku_brand_name`, `impressions`, `clicks`, `all_cost`, `dir_ord_cnt`, `dir_ord_sum`, `ind_ord_cnt`, `ind_ord_sum`, `tot_ord_cnt`, `tot_ord_sum`, `dir_cart_cnt`, `ind_cart_cnt`, `tot_cart_cnt`, `new_customer`, `ad_id`, `ad_name`, `search_term`, `download_id`, `record_load_time`, `batch`)
 AS
 SELECT `ods`.`a`.`username`, `ods`.`a`.`date_str`, `ods`.`a`.`trans_days`, `ods`.`a`.`caliber`, `ods`.`a`.`order_status`, `ods`.`a`.`gift_flag`, `ods`.`a`.`effect`, `ods`.`a`.`is_daily`, `ods`.`a`.`start_date`, `ods`.`a`.`end_date`, `ods`.`a`.`period`, `ods`.`a`.`pin`, `ods`.`a`.`business`, `ods`.`a`.`campaign_type`, `ods`.`a`.`campaign_id`, `ods`.`a`.`campaign_name`, `ods`.`a`.`group_id`, `ods`.`a`.`group_name`, `ods`.`a`.`kw_id`, `ods`.`a`.`keyword`, `ods`.`a`.`target_type`, `ods`.`a`.`sku_brand_id`, `ods`.`a`.`sku_brand_name`, `ods`.`a`.`impressions`, `ods`.`a`.`clicks`, `ods`.`a`.`all_cost`, `ods`.`a`.`dir_ord_cnt`, `ods`.`a`.`dir_ord_sum`, `ods`.`a`.`ind_ord_cnt`, `ods`.`a`.`ind_ord_sum`, `ods`.`a`.`tot_ord_cnt`, `ods`.`a`.`tot_ord_sum`, `ods`.`a`.`dir_cart_cnt`, `ods`.`a`.`ind_cart_cnt`, `ods`.`a`.`tot_cart_cnt`, `ods`.`a`.`new_customer`, `ods`.`a`.`ad_id`, `ods`.`a`.`ad_name`, `ods`.`a`.`search_term`, `ods`.`a`.`download_id`, `ods`.`a`.`record_load_time`, `ods`.`a`.`batch`
FROM `ods`.`jd_custom_report_keyword`
AS `a`
INNER JOIN
`ods`.`jd_custom_report_batch` AS `b`
ON (((((`ods`.`a`.`username` = `ods`.`b`.`username`)
AND (`ods`.`a`.`date_str` = `ods`.`b`.`date_str`))
AND (`ods`.`a`.`trans_days` = `ods`.`b`.`trans_days`))
AND (`ods`.`a`.`caliber` = `ods`.`b`.`caliber`))
AND (`ods`.`a`.`order_status` = `ods`.`b`.`order_status`))
AND (`ods`.`b`.`template_key` = 'jd_custom_report_keyword');;