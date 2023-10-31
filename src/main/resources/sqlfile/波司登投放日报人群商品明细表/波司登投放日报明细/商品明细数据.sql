select
sk.date_str,
case when sk.username='BSD彭艾云' then '官旗'
when sk.username='jd_nvbsd'   then '服饰'
when sk.username='bvp06121666' then '自营'
when sk.username='BSD-户外代理' then '户外'
when sk.username='BSD-奥莱代理' then '奥莱'
when sk.username='BSD-童装代理' then '童装'
when sk.username='BSD奥莱店铺推广' then '奥莱'
when sk.username='BSD户外店铺推广' then '户外'
when sk.username='波司登童装自营' then '童装'
end shopname,
case when sk.business in ('京速推','智能投放（原京速推）','智能投放') and sk.campaign_name in ('全店推广计划','首焦海投计划','经典海投计划','PUSH海投计划','海投新品计划','测款计划','频道活动计划','预售推广计划','直降促销计划') then '京东海投' else sk.business end  as productName,
sk.sku_id,
sum(sk.impressions) as impressions,
sum(sk.clicks) as clicks,
sum(sk.all_cost) as cost,
sum(sk.tot_ord_cnt) as tot_ord_cnt,
sum(sk.tot_ord_sum) as tot_ord_sum,
sum(sk.tot_cart_cnt) as tot_cart_cnt

from jd_digital_marketing.dwd_jd_jzt_kc_sku_report sk


where sk.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND sk.trans_days=15
AND sk.caliber=0
and sk.gift_flag=0
and sk.order_status=1
-- and sk.effect=1

AND sk.DATE_STR>=date_format('${开始日期}','%Y-%m-%d')
AND sk.DATE_STR<=date_format('${结束日期}','%Y-%m-%d')
and sk.date_str<current_date()
group by
sk.date_str,
case when sk.username='BSD彭艾云' then '官旗'
when sk.username='jd_nvbsd'   then '服饰'
when sk.username='bvp06121666' then '自营'
when sk.username='BSD-户外代理' then '户外'
when sk.username='BSD-奥莱代理' then '奥莱'
when sk.username='BSD-童装代理' then '童装'
when sk.username='BSD奥莱店铺推广' then '奥莱'
when sk.username='BSD户外店铺推广' then '户外'
when sk.username='波司登童装自营' then '童装'
end ,
case when sk.business in ('京速推','智能投放（原京速推）','智能投放') and sk.campaign_name in ('全店推广计划','首焦海投计划','经典海投计划','PUSH海投计划','海投新品计划','测款计划','频道活动计划','预售推广计划','直降促销计划') then '京东海投' else sk.business end ,
sk.sku_id

;



