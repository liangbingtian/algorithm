(select
sum(totalOrderSum) AS GMV,
sum(clicks) as '点击',
sum(impressions) as '展现',
sum(totalOrderCnt) as '总订单行',
sum(totalCartCnt) as '总加购数',
sum(newCustomersCnt) as '下单新客数',
sum(cost) as '花费',
productName as '渠道',
date_str as '日期信息'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ('Zenith真力时POP-代理')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source not in (2, 9, 8)
and date_str>=DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y-01-01')
group by
productName,
date_str)
union all
(select
sum(totalOrderSum) AS GMV,
sum(clicks) as '点击',
sum(impressions) as '展现',
sum(totalOrderCnt) as '总订单行',
sum(totalCartCnt) as '总加购数',
sum(newCustomersCnt) as '下单新客数',
sum(cost) as '花费',
productName as '渠道',
date_str as '日期信息'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ('Zenith真力时POP')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source = 4
and productName = '京挑客'
and date_str>=DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y-01-01')
group by
productName,
date_str)