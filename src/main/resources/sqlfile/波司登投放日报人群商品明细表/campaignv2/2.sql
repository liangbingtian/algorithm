(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'本期周数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>='${weekStartDay}'
AND date_str<='${endDay}'
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'本期月数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>='${monthStartDay}'
AND date_str<='${endDay}'
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'本期年数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>='${yearStartDay}'
AND date_str<='${endDay}'
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'上期周数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
AND date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'上期月数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${monthStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
AND date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
SUM(clicks) as '点击量',
sum(impressions) as '展现量',
if(SUM(clicks)=0, 0, sum( cost )/SUM(clicks)) AS CPC,
if(sum( cost )=0, 0, sum( totalOrderSum )/sum( cost )) AS ROI,
sum( totalOrderSum ) AS GMV,
if(sum( totalCartCnt )=0, 0, sum( cost )/sum( totalCartCnt )) AS 加购成本,
'上期年数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=1
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName not in ('京挑客','京东展位')
AND date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${yearStartDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
AND date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-%d')
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '女店'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)