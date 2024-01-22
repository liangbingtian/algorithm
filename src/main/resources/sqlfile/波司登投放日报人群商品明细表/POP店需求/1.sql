(SELECT
sku_id AS 'SKU',
cname1 AS '类目',
impressions AS '展现量',
clicks AS '点击量',
all_cost AS '总费用',
tot_ord_cnt AS '总订单行',
tot_ord_sum AS '总订单金额',
tot_cart_cnt AS '总加购数',
new_customer AS '下单新客数',
'快车' AS '渠道',
date_str
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_category_info ON dwd_jd_jzt_kc_sku_report.sku_c3_id = basic_category_info.cid3
WHERE
trans_days = 15
AND order_status = 1
AND caliber = 1
AND username IN ('POP直营店-投放', 'POP直营店-京挑客')
AND date_str >= '${start}'
AND date_str <= '${end}'
and business = '快车')
union all
(SELECT
sku_id AS 'SKU',
cname1 AS '类目',
impressions AS '展现量',
clicks AS '点击量',
all_cost AS '总费用',
tot_ord_cnt AS '总订单行',
tot_ord_sum AS '总订单金额',
tot_cart_cnt AS '总加购数',
new_customer AS '下单新客数',
'海投' AS '渠道',
date_str
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_category_info ON dwd_jd_jzt_kc_sku_report.sku_c3_id = basic_category_info.cid3
WHERE
trans_days = 15
AND order_status = 1
AND caliber = 1
AND username IN ('POP直营店-投放', 'POP直营店-京挑客')
AND date_str >= '${start}'
AND date_str <= '${end}'
AND business IN ('智能投放')
AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
)