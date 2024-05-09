CREATE VIEW `dwd_jd_jzt_real_time_report` (
	`id`,
	`record_load_time`,
	`username`,
	`trans_days`,
	`caliber`,
	`gift_flag`,
	`order_status`,
	`effect`,
	`is_daily`,
	`date_str`,
	`pin`,
	`business`,
	`campaign_type`,
	`campaign_id`,
	`campaign_name`,
	`group_id`,
	`group_name`,
	`impressions`,
	`clicks`,
	`all_cost`,
	`tot_ord_cnt`,
	`tot_ord_sum`,
	`tot_cart_cnt`,
	`new_customer`
) AS SELECT
`ods`.`sku_report`.`id`,
`ods`.`sku_report`.`record_load_time`,
`jd_digital_marketing`.`conditions`.`username`,
`jd_digital_marketing`.`conditions`.`trans_days`,
`jd_digital_marketing`.`conditions`.`caliber`,
`ods`.`sku_report`.`gift_flag`,
`jd_digital_marketing`.`conditions`.`order_status`,
`ods`.`sku_report`.`effect`,
`ods`.`sku_report`.`is_daily`,
date_format( `jd_digital_marketing`.`conditions`.`date_str`, '%Y-%m-%d' ) AS `date_str`,
`ods`.`sku_report`.`pin`,
CASE

		WHEN (
			`ods`.`sku_report`.`business` IN ( '快车', '京东快车', '京东快车（原京东快车）', '搜索快车' )) THEN
			'快车'
			WHEN (
				`ods`.`sku_report`.`business` IN ( '推荐广告', '购物触点', '推荐广告（原购物触点）' )) THEN
				'推荐广告'
				WHEN (
					`ods`.`sku_report`.`business` IN ( '智能投放', '京速推', '智能投放（原京速推）', '海投' )) THEN
					'智能投放'
					WHEN (
						`ods`.`sku_report`.`business` IN ( '京东展位', '互动广告', '互动广告（原京东展位）' )) THEN
						'互动广告'
						WHEN (
							`ods`.`sku_report`.`business` IN ( '京东直投', '站外广告（原京东直投）', '站外广告' )) THEN
							'站外广告' ELSE `ods`.`sku_report`.`business`
						END AS `business`,
						`ods`.`sku_report`.`campaign_type`,
						`ods`.`sku_report`.`campaign_id`,
						`ods`.`sku_report`.`campaign_name`,
						`ods`.`sku_report`.`group_id`,
						`ods`.`sku_report`.`group_name`,
						`ods`.`sku_report`.`impressions`,
						`ods`.`sku_report`.`clicks`,
						`ods`.`sku_report`.`all_cost`,
						`ods`.`sku_report`.`tot_ord_cnt`,
						`ods`.`sku_report`.`tot_ord_sum`,
						`ods`.`sku_report`.`tot_cart_cnt`,
						`ods`.`sku_report`.`new_customer`
					FROM
						`jd_digital_marketing`.`jd_custom_report_batch` AS `conditions`
						INNER JOIN `ods`.`jd_custom_report_real_time` AS `sku_report` ON (((((
											`jd_digital_marketing`.`conditions`.`username` = `ods`.`sku_report`.`username`
											)
										AND ( `jd_digital_marketing`.`conditions`.`date_str` = `ods`.`sku_report`.`date_str` ))
									AND ( `jd_digital_marketing`.`conditions`.`trans_days` = `ods`.`sku_report`.`trans_days` ))
								AND ( `jd_digital_marketing`.`conditions`.`caliber` = `ods`.`sku_report`.`caliber` ))
						AND ( `jd_digital_marketing`.`conditions`.`order_status` = `ods`.`sku_report`.`order_status` ))
						AND ( `jd_digital_marketing`.`conditions`.`batch` = `ods`.`sku_report`.`batch` )
				WHERE
	`jd_digital_marketing`.`conditions`.`template_key` = 'jd_custom_report_real_time';