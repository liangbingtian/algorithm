CREATE VIEW
`dwd_jd_jzt_keyword_report`
(`record_load_time`, `username`, `period`, `trans_days`, `caliber`,
`gift_flag`, `order_status`, `effect`, `is_daily`, `date_str`, `pin`,
`business`, `campaign_type`, `campaign_id`, `campaign_name`, `group_id`,
`group_name`, `ad_id`, `ad_name`, `kw_id`, `keyword`, `target_type`, `sku_brand_id`,
`sku_brand_name`, `impressions`, `clicks`, `all_cost`, `dir_ord_cnt`, `dir_ord_sum`,
`ind_ord_cnt`, `ind_ord_sum`, `tot_ord_cnt`, `tot_ord_sum`, `dir_cart_cnt`, `ind_cart_cnt`,
`tot_cart_cnt`, `new_customer`)
AS
SELECT `crawler_pg`.`src_jzt_keyword_report`.`record_load_time`,
`crawler_pg`.`src_jzt_keyword_report`.`username`,
ifnull(`crawler_pg`.`src_jzt_keyword_report`.`period`, ' ') AS `period`,
ifnull(`crawler_pg`.`src_jzt_keyword_report`.`trans_days`, -1) AS `trans_days`,
ifnull(`crawler_pg`.`src_jzt_keyword_report`.`caliber`, -1) AS `caliber`, `crawler_pg`.`src_jzt_keyword_report`.`gift_flag`, if(`crawler_pg`.`src_jzt_keyword_report`.`order_status` = '', '0', '1') AS `order_status`, `crawler_pg`.`src_jzt_keyword_report`.`effect`, ifnull(`crawler_pg`.`src_jzt_keyword_report`.`is_daily`, -1) AS `is_daily`, ifnull(`crawler_pg`.`src_jzt_keyword_report`.`date_str`, '20000101') AS `date_str`, `crawler_pg`.`src_jzt_keyword_report`.`pin`, `crawler_pg`.`src_jzt_keyword_report`.`business`, `crawler_pg`.`src_jzt_keyword_report`.`campaign_type`, `crawler_pg`.`src_jzt_keyword_report`.`campaign_id`, `crawler_pg`.`src_jzt_keyword_report`.`campaign_name`, `crawler_pg`.`src_jzt_keyword_report`.`group_id`, `crawler_pg`.`src_jzt_keyword_report`.`group_name`, `crawler_pg`.`src_jzt_keyword_report`.`ad_id`, `crawler_pg`.`src_jzt_keyword_report`.`ad_name`, `crawler_pg`.`src_jzt_keyword_report`.`kw_id`, `crawler_pg`.`src_jzt_keyword_report`.`keyword`, `crawler_pg`.`src_jzt_keyword_report`.`target_type`, `crawler_pg`.`src_jzt_keyword_report`.`sku_brand_id`, `crawler_pg`.`src_jzt_keyword_report`.`sku_brand_name`, `crawler_pg`.`src_jzt_keyword_report`.`impressions`, `crawler_pg`.`src_jzt_keyword_report`.`clicks`, `crawler_pg`.`src_jzt_keyword_report`.`all_cost`, `crawler_pg`.`src_jzt_keyword_report`.`dir_ord_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`dir_ord_sum`, `crawler_pg`.`src_jzt_keyword_report`.`ind_ord_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`ind_ord_sum`, `crawler_pg`.`src_jzt_keyword_report`.`tot_ord_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`tot_ord_sum`, `crawler_pg`.`src_jzt_keyword_report`.`dir_cart_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`ind_cart_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`tot_cart_cnt`, `crawler_pg`.`src_jzt_keyword_report`.`new_customer`
FROM `crawler_pg`.`src_jzt_keyword_report`;;