(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
campaignname as '计划名称',
sum( cost ) AS '花费',
sum( cost )/SUM(clicks) AS CPC,
sum( totalOrderSum )/sum( cost ) AS ROI,
sum( totalOrderSum ) AS GMV,
sum(clicks) as '点击',
sum(totalCartCnt) AS '加购数',
sum( cost )/SUM(totalCartCnt) AS '加购成本',
SUM(totalOrderCnt) AS '总订单行',
sum( totalOrderCnt )/SUM(clicks) AS CVR,
'本期数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=0
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName!='京挑客'
AND date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('${weekEndDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), INTERVAL 1 DAY), '%Y-%m-%d')
AND date_str<='${weekEndDay}'
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end, campaignname)
union ALL
(select
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
CONVERT(sum( cost )/SUM(clicks),decimal(15,2)) AS CPC,
CONVERT(sum( totalOrderSum )/sum( cost ),decimal(15,2)) AS ROI,
sum( totalOrderSum ) AS GMV,
sum(clicks) as '点击',
'上期数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=0
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName!='京挑客'
AND date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('${weekEndDay}', '%Y-%m-%d'), INTERVAL 2 WEEK), INTERVAL 1 DAY), '%Y-%m-%d')
AND date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekEndDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
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
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end AS '店铺名称',
sum( cost ) AS '花费',
CONVERT(sum( cost )/SUM(clicks),decimal(15,2)) AS CPC,
CONVERT(sum( totalOrderSum )/sum( cost ),decimal(15,2)) AS ROI,
sum( totalOrderSum ) AS GMV,
sum(clicks) as '点击',
'同期数据' AS '时间所属'
from jd_digital_marketing.dm_jd_api_campaign_report_v2
where
shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=0
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and productName!='京挑客'
AND date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE('${periodEndDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), INTERVAL 1 DAY), '%Y-%m-%d')
AND date_str<='${periodEndDay}'
group by
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end)