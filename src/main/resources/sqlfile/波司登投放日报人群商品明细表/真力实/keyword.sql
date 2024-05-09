SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(tot_cart_cnt) AS '加购数',
SUM(clicks) AS '点击量',
case
when dwd_jd_jzt_keyword_report.campaign_name like '%京选店铺%' then '品牌词'
else group_name
end AS '关键词类',
max(keyword) as '关键词',
date_str as '日期信息',
case
when dwd_jd_jzt_keyword_report.campaign_name like '%京选店铺%' then '京选店铺'
when dwd_jd_jzt_keyword_report.campaign_name not like '%京选店铺%' then '京东快车'
end AS '业务类型',
case
when date_str between DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
when date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') then '上周数据'
end as '时间所属'
FROM dwd_jd_jzt_keyword_report
WHERE
dwd_jd_jzt_keyword_report.username in ('Zenith真力时POP-代理')
AND trans_days=15
AND caliber=1
and gift_flag=0
and order_status=1
AND (date_str between DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
or date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d'))
GROUP BY
date_str,
case
when  dwd_jd_jzt_keyword_report.campaign_name like '%京选店铺%' then '京选店铺'
when dwd_jd_jzt_keyword_report.campaign_name not like '%京选店铺%' then '京东快车'
end,
case
when dwd_jd_jzt_keyword_report.campaign_name like '%京选店铺%' then '品牌词'
else group_name
end,
dwd_jd_jzt_keyword_report.kw_id
order by SUM(all_cost) desc