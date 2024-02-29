SELECT
sum(all_cost) AS '花费',
sum(tot_ord_sum) AS 'GMV',
sum(tot_cart_cnt) AS '加购数',
sum(impressions) AS '展现量',
sum(clicks) AS '点击量',
max(campaign_name) as '计划名称',
case when username='yhx_vx999' then '户外店'
when username='yhx_vx888'   then '礼品店'
when username='yhx-vx箱包' then '箱包店'
when username='维氏厨具自营旗舰店' then '厨具店'
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
sku_id as 'SKU NO',
sum(tot_ord_cnt) as '总成交笔数',
sum(tot_ord_sum) as '总成交金额'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ('yhx_vx999', 'yhx_vx888', 'yhx-vx箱包', '维氏厨具自营旗舰店')
AND trans_days=15
AND caliber=1
and gift_flag=0
and order_status=1
AND dwd_jd_jzt_kc_sku_report.date_str>='${startDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}'
GROUP BY
case when username='yhx_vx999' then '户外店'
when username='yhx_vx888'   then '礼品店'
when username='yhx-vx箱包' then '箱包店'
when username='维氏厨具自营旗舰店' then '厨具店'
end,
case
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '海投'
when business in ('智能投放','京速推','智能投放（原京速推）') and campaign_name not IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划') then '京速推'
when business in ('快车', '京东快车（原京东快车）', '京东快车') then '快车'
when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
else business
end,
date_str,
campaign_id,
sku_id
order by sum(all_cost) DESC