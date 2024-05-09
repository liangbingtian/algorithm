select
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
where
shopname in ('Zenith真力时-代理')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source not in (2, 9, 8)
AND (date_str between '${startDay}' and '${endDay}'
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d'))
group by
productName,
case
when date_str>='${startDay}' and date_str<='${endDay}' then '本周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d') then '上周数据'
when date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年数据'
end,
date_str