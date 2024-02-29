(select
case when shopname='Longines-2021' then '自营'
when shopname='Longines_POP2023'   then '官旗'
end AS '店铺名称',
case
when date_str>='${startDay}' and date_str<='${endDay}' then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年数据'
end as '时间所属',
sum(cost) AS '花费',
sum(totalOrderSum) AS GMV,
sum(clicks) as '点击',
sum(impressions) as '展现',
sum(totalOrderCnt) as '总订单行',
sum(totalCartCnt) as '总加购数',
sum(newCustomersCnt) as '下单新客数',
productName as '渠道',
date_str as '日期信息'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
left join basic_jd_udf_adserving_longines_campaign_set on dm_jd_api_campaign_report_v2.shopname=basic_jd_udf_adserving_longines_campaign_set.username
and dm_jd_api_campaign_report_v2.campaign_name=basic_jd_udf_adserving_longines_campaign_set.campaign_name
where
shopname in ('Longines-2021', 'Longines_POP2023')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source not in (2, 9, 8)
AND date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-01')
AND date_str<='${endDay}'
group by
case when shopname='Longines-2021' then '自营'
when shopname='Longines_POP2023'   then '官旗'
end,
productName,
basic_jd_udf_adserving_longines_campaign_set.type,
case
when date_str>='${startDay}' and date_str<='${endDay}' then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年数据'
end,
date_str)
union ALL
(select
case when shopname='Longines-2021' then '自营'
when shopname='Longines_POP2023'   then '官旗'
end AS '店铺名称',
case
when date_str>='${monthStartDay}' and date_str<='${endDay}' then 'MTD本年数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then 'MTD去年数据'
end as '时间所属',
sum(cost) AS '花费',
sum(totalOrderSum) AS GMV,
sum(clicks) as '点击',
sum(impressions) as '展现',
sum(totalOrderCnt) as '总订单行',
sum(totalCartCnt) as '总加购数',
sum(newCustomersCnt) as '下单新客数',
productName as '渠道',
date_str as '日期信息'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ('Longines-2021', 'Longines_POP2023')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source not in (2, 9, 8)
AND date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-01')
AND date_str<='${endDay}'
group by
case when shopname='Longines-2021' then '自营'
when shopname='Longines_POP2023'   then '官旗'
end,
productName,
case
when date_str>='${monthStartDay}' and date_str<='${endDay}' then 'MTD本年数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then 'MTD去年数据'
end,
date_str)