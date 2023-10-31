SELECT
date_format(dat.date_str,'%Y-%m-%d') as date_str,
case when dat.username='BSD彭艾云' then '官旗'
when dat.username='jd_nvbsd'   then '服饰'
when dat.username='bvp06121666' then '自营'
when dat.username='BSD-户外代理' then '户外'
when dat.username='BSD-奥莱代理' then '奥莱'
when dat.username='BSD-童装代理' then '童装'
when dat.username='BSD奥莱店铺推广' then '奥莱'
when dat.username='BSD户外店铺推广' then '户外'
when dat.username='波司登童装自营' then '童装' end shopname,
dat.business,
dat.crowd_name,
tp.dmptype,
dat.sku_doc_id as sku_id,
sum(impressions) as impressions,
sum(clicks) as clicks,
sum(all_cost) as cost,
sum(tot_ord_cnt) as tot_ord_cnt,
sum(tot_ord_sum) as tot_ord_sum,
sum(tot_cart_cnt) as tot_cart_cnt,
sum(new_customer) as new_customer

FROM `jd_digital_marketing`.`dwd_jd_jzt_crowd_report` dat
left join jd_digital_marketing.basic_jd_bosideng_dmp_matching_table  tp
on  trim(dat.crowd_name)=  trim(tp.dmpname)
WHERE dat.username IN ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and dat.date_str>=date_format('${开始日期}','%Y%m%d')
and dat.date_str<=date_format('${结束日期}','%Y%m%d')
and dat.date_str<current_date()
and dat.trans_days=15
and dat.caliber=0
and dat.gift_flag=0
and dat.order_status=1
group by
date_format(dat.date_str,'%Y-%m-%d'),
case when dat.username='BSD彭艾云' then '官旗'
when dat.username='jd_nvbsd'   then '服饰'
when dat.username='bvp06121666' then '自营'
when dat.username='BSD-户外代理' then '户外'
when dat.username='BSD-奥莱代理' then '奥莱'
when dat.username='BSD-童装代理' then '童装'
when dat.username='BSD奥莱店铺推广' then '奥莱'
when dat.username='BSD户外店铺推广' then '户外'
when dat.username='波司登童装自营' then '童装' end,
dat.business,
dat.crowd_name,
tp.dmptype,
dat.sku_doc_id;