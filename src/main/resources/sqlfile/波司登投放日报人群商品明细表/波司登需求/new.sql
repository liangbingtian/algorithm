SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
IFNULL(style,'无分类货品') AS '分类',
IFNULL(category_weekly,'无货品分类') AS '货品分类',
case
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京东海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name not like '%京选店铺%' then '京东快车'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name like '%京选店铺%' then '京选店铺'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '购物触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '京东直投'
else business
end AS '业务类型',
case
when dwd_jd_jzt_kc_sku_report.date_str between '${weekStartDay}' and '${endDay}' then '本周数据'
when dwd_jd_jzt_kc_sku_report.date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when dwd_jd_jzt_kc_sku_report.date_str between '${monthStartDay}' and '${endDay}' then '本期月数据'
end as '时间所属'
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND (date_str between '${weekStartDay}' and '${endDay}'
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
or date_str between'${monthStartDay}' and '${endDay}')
GROUP BY
case
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京东海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name not like '%京选店铺%' then '京东快车'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name like '%京选店铺%' then '京选店铺'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '购物触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '京东直投'
else business
end,
case
when dwd_jd_jzt_kc_sku_report.date_str between '${weekStartDay}' and '${endDay}' then '本周数据'
when dwd_jd_jzt_kc_sku_report.date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when dwd_jd_jzt_kc_sku_report.date_str between '${monthStartDay}' and '${endDay}' then '本期月数据'
end,
style, category_weekly