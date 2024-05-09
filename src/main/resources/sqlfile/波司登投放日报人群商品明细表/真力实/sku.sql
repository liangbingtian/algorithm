SELECT
sum(all_cost) AS '花费',
sum(tot_ord_sum) AS 'GMV',
sum(tot_cart_cnt) AS '加购数',
sum(impressions) AS '展现量',
sum(clicks) AS '点击量',
sku_id,
case
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京东海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and dwd_jd_jzt_kc_sku_report.campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name not like '%京选店铺%' then '京东快车'
when business in ('快车', '京东快车（原京东快车）', '京东快车') and dwd_jd_jzt_kc_sku_report.campaign_name like '%京选店铺%' then '京选店铺'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '购物触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '京东直投'
else business
end AS '业务类型',
date_str AS '日期信息',
sum(tot_ord_cnt) as '总成交笔数',
sum(tot_ord_sum) as '总成交金额',
case
when date_str>='${startDay}' and date_str<='${endDay}' then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年数据'
end as '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
dwd_jd_jzt_kc_sku_report.username in ('Zenith真力时-代理')
AND trans_days=15
AND caliber=1
and gift_flag=0
and order_status=1
AND (date_str between '${startDay}' and '${endDay}'
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d'))
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
when date_str>='${startDay}' and date_str<='${endDay}' then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年数据'
end,
date_str,
sku_id
