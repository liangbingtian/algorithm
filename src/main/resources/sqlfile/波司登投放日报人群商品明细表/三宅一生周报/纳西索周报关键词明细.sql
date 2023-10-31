SELECT
	username,
	date_format( date_str, '%Y-%m' ) date_month,
	date_format( date_str, '%Y-%m-%d' ) date_str,
CASE WHEN campaign_type LIKE '%京选店铺%' THEN
	'京选店铺' ELSE business
	END channel,
case when ad_name regexp '[0-9]{3}'=1 then
case
   when LEFT ( trim( ad_name ), 1 ) REGEXP '[0-9.]' = 1 THEN
	  substr( ad_name, 1, instr( ad_name, '-' ) - 1 )
   WHEN RIGHT(TRIM(ad_name),1) REGEXP'[0-9.]'=1 THEN
	 SUBSTR(ad_name,INSTR(ad_name,'-')+1,LENGTH(ad_name))
	 else
	    case WHEN length(ad_name)- length(replace(ad_name,'-','')) =2 THEN
      trim(split_part(ad_name,'-',2))
end
end
END  AS skuId,
	ad_name,
	group_name,
	campaign_name,
	keyword,
	target_type,
	sum( impressions ) impressions,
	sum( clicks ) clicks,
	sum( all_cost ) cost,
	sum( tot_ord_cnt ) AS totalOrderCnt,
	sum( tot_ord_sum ) AS totalOrderSum,
	sum( tot_cart_cnt ) AS totalCartCnt
FROM
	`jd_digital_marketing`.`dwd_jd_jzt_keyword_report`
WHERE
	1 = 1

	AND USERNAME = 'SANZHAIYISHENG587'
	AND DATE_STR>=date_format(concat('${年月}','-01'),'%Y%m%d')
     AND DATE_STR<=DATE_ADD(DATE_ADD(date_format(concat('${年月}','-01'),'%Y-%m-%d'), INTERVAL 1 MONTH), INTERVAL -1 DAY)
     AND DATE_STR<CURDATE()
	AND caliber = 0
	AND order_status = 1
	AND trans_days = 15
	AND gift_flag = 0
GROUP BY
	username,
	date_format( date_str, '%Y-%m' ),
	date_format( date_str, '%Y-%m-%d' ),
CASE

	WHEN campaign_type LIKE '%京选店铺%' THEN
	'京选店铺' ELSE business
	END,
	group_name,
	keyword,
	target_type,
	CASE

	WHEN ad_name REGEXP '[0-9]' = 1 THEN
CASE

	WHEN LEFT ( trim( ad_name ), 1 ) REGEXP '[0-9.]' = 1 THEN
	substr( ad_name, 1, instr( ad_name, '-' ) - 1 ) ELSE REPLACE ( reverse( round( reverse( ad_name ), 0 ) ), ',', '' )
	END ELSE ad_name
	END,
	ad_name,
	campaign_name;
