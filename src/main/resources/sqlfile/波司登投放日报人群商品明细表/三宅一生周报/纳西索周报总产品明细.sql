
SELECT
date_format(date_str,'%Y-%m-%d') date_str,
date_format(date_str,'%Y-%m') year_month_id,
username as shopname,
campaign_name as campaignname,
group_name as adgroupname,
case when campaign_name like '%京选店铺%' then '京选店铺' else business end as channel_type,
ad_name as adname,
sku_id as skuId,
impressions,
clicks,
all_cost as cost,
tot_ord_cnt as TotalOrderCnt,
tot_ord_sum as TotalOrderSum,
tot_cart_cnt as TotalCartCnt

FROM `jd_digital_marketing`.`dwd_jd_jzt_kc_sku_report`
where 1=1
and username='SANZHAIYISHENG587'
AND DATE_STR>=date_format(concat('${年月}','-01'),'%Y-%m-%d')
AND DATE_STR<=DATE_ADD(DATE_ADD(date_format(concat('${年月}','-01'),'%Y%m%d'), INTERVAL 1 MONTH), INTERVAL -1 DAY)
and date_str<CURDATE()
and business in ('推荐广告（原购物触点）','京东快车（原京东快车）','购物触点','京东快车')
and caliber=0
and trans_days=15
and order_status=1
and gift_flag=0


union all
select
date_str,
date_format(date_str,'%Y-%m') as year_month_id,
shopname,
campaignname,
'' as adgroupname,
case when campaignname in ('全店推广计划','首焦海投计划',  '经典海投计划', 'PUSH海投计划',  '海投新品计划',  '测款计划',  '频道活动计划', '预售推广计划',  '直降促销计划'  ) then '京东海投' else '京速推' end channel_type,
skuid as adname,
skuId,
impressions,
clicks,
cost,
totalOrderCnt,
totalOrderSum,
totalCartCnt

from jd_digital_marketing.ods_jd_api_sjzx_sku_query
where
`shopname` = 'SANZHAIYISHENG587'
AND DATE_STR>=date_format(concat('${年月}','-01'),'%Y-%m-%d')
AND DATE_STR<=DATE_ADD(DATE_ADD(date_format(concat('${年月}','-01'),'%Y-%m-%d') ,INTERVAL 1 MONTH), INTERVAL -1 DAY)
and date_str<CURDATE()
and request_clickOrOrderDay=15
and request_clickOrOrderCaliber=0
and request_orderStatusCategory=1
and batchstatus=1
UNION ALL
SELECT
DATE_FORMAT(finishTime,'%Y-%m-%d') as date_str,
date_format(finishTime,'%Y-%m') as year_month_id,
shopname,
planName as campaignName,
'' as adgroupname,
'京挑客' as channel_type,
skuId as adname,
skuId,
0 as impressions,
0 as clicks,
(cosFee + cpFee + platformServiceFee) AS cost,
 1 AS totalOrderCnt,
 cosPrice AS totalOrderSum,
 0 as totalCartCnt

FROM
	`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`
WHERE
	`shopname` = 'SANZHAIYISHENG587'
	and finishTime>=date_format(concat('${年月}','-01'),'%Y-%m-%d')
	AND FINISHTIME<=DATE_ADD(DATE_ADD(date_format(concat('${年月}','-01'),'%Y-%m-%d'), INTERVAL 1 MONTH), INTERVAL -1 DAY)
	AND FINISHTIME<CURDATE()
	and batchstatus=1
	and isValid=1