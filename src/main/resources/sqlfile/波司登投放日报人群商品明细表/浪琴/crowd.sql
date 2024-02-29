(SELECT
sum(all_cost) AS '花费',
sum(tot_ord_sum) AS 'GMV',
sum(tot_cart_cnt) AS '加购数',
sum(impressions) AS '展现量',
sum(clicks) AS '点击量',
case when max(basic_jd_udf_adserving_longines_crowd_set.type) is null then '无人群类别'
ELSE max(basic_jd_udf_adserving_longines_crowd_set.type) end as '人群类别',
case when dwd_jd_jzt_crowd_report.username='Longines-2021' then '自营'
when dwd_jd_jzt_crowd_report.username='Longines_POP2023'   then '官旗'
end AS '店铺名称',
case
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') then '快车'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
else business
end AS '业务类型',
date_str AS '日期信息',
sum(tot_ord_cnt) as '总成交笔数',
sum(tot_ord_sum) as '总成交金额',
case
when date_str>=DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str<=DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') then '去年数据'
end as '时间所属'
FROM dwd_jd_jzt_crowd_report left join basic_jd_udf_adserving_longines_crowd_set
on dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_longines_crowd_set.username
and dwd_jd_jzt_crowd_report.crowd_name=basic_jd_udf_adserving_longines_crowd_set.crowd_name
WHERE
dwd_jd_jzt_crowd_report.username in ('Longines-2021', 'Longines_POP2023')
AND trans_days=15
AND caliber=1
and gift_flag=0
and order_status=1
AND (date_str between DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d'))
GROUP BY
case when dwd_jd_jzt_crowd_report.username='Longines-2021' then '自营'
when dwd_jd_jzt_crowd_report.username='Longines_POP2023'   then '官旗'
end,
case
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') then '快车'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
else business
end,
case
when date_str>=DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str<=DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') then '去年数据'
end,
date_str,
crowd_id)
union all
(SELECT
sum(all_cost) AS '花费',
sum(tot_ord_sum) AS 'GMV',
sum(tot_cart_cnt) AS '加购数',
sum(impressions) AS '展现量',
sum(clicks) AS '点击量',
case when max(basic_jd_udf_adserving_longines_crowd_set.type) is null then '无人群类别'
ELSE max(basic_jd_udf_adserving_longines_crowd_set.type) end as '人群类别',
case when dwd_jd_jzt_crowd_report.username='Longines-2021' then '自营'
when dwd_jd_jzt_crowd_report.username='Longines_POP2023'   then '官旗'
end AS '店铺名称',
case
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') then '快车'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
else business
end AS '业务类型',
date_str AS '日期信息',
sum(tot_ord_cnt) as '总成交笔数',
sum(tot_ord_sum) as '总成交金额',
case
when date_str>=DATE_FORMAT(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), '%Y%m%d') and date_str<=DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then 'MTD本年数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') then 'MTD去年数据'
end as '时间所属'
FROM dwd_jd_jzt_crowd_report left join basic_jd_udf_adserving_longines_crowd_set
on dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_longines_crowd_set.username
and dwd_jd_jzt_crowd_report.crowd_name=basic_jd_udf_adserving_longines_crowd_set.crowd_name
WHERE
dwd_jd_jzt_crowd_report.username in ('Longines-2021', 'Longines_POP2023')
AND trans_days=15
AND caliber=1
and gift_flag=0
and order_status=1
AND (date_str between DATE_FORMAT(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d'))
GROUP BY
case when dwd_jd_jzt_crowd_report.username='Longines-2021' then '自营'
when dwd_jd_jzt_crowd_report.username='Longines_POP2023'   then '官旗'
end,
case
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') then '快车'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
else business
end,
case
when date_str>=DATE_FORMAT(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), '%Y%m%d') and date_str<=DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then 'MTD本年数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m%d') then 'MTD去年数据'
end,
date_str,
crowd_id)