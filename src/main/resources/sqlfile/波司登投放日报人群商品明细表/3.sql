(select
date_str,
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'
end shopname,
productName as channel,
'MTD' as data_type,
sum(impressions) as impressions,
sum(clicks) as clicks,
sum(cost) as cost,
sum(totalOrderCnt) as tot_ord_cnt,
sum(totalOrderSum) as tot_ord_sum,
sum(totalCartCnt) as tot_cart_cnt,
sum(newCustomersCnt) as new_customer

from jd_digital_marketing.dm_jd_api_campaign_report_v2
where 1=1
and shopname in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and clickOrOrderCaliber=0
and clickOrOrderDay=15
and orderStatusCategory=1
and batchstatus=1
and source !=2
and source !=9
and date_str>=date_format('2023-03-30','%Y-%m-%d')
AND DATE_STR>=date_format((date_sub(date_format('${start_date}','%Y-%m-%d'), interval -6 day)),'%Y-%m-01')
AND DATE_STR<=date_format(date_ADD(concat(date_format(DATE_ADD(date_ADD(date_format('2023-04-06','%Y-%m-%d'), interval 6 day), INTERVAL 1 MONTH),'%Y-%m'),'-01'),interval -1 day),'%Y-%m-%d')
and date_str<=date_sub(date_format('${start_date}','%Y-%m-%d'), interval -6 day)
and date_str<current_date()
and productName!='京挑客'
group by
date_str,
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装'
when shopname='BSD奥莱店铺推广' then '奥莱'
when shopname='BSD户外店铺推广' then '户外'
when shopname='波司登童装自营' then '童装'end,
productName
)
union all
(
select
date_format(date,'%Y-%m-%d') date_str,
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装' end as shopname,
case when channel='kuaiche' then '京东快车'
when channel='touchpoint' then '购物触点'
when channel='jst' then  '京速推'
when channel='haitou'  then '京东海投'
when channel='zhitou'  then '京东直投'
end channel,
'LAST_YEAR' as data_type,
sum(impressions) as impressions,
sum(clicks) as clicks,
sum(cost) as cost,
sum(tot_ord_cnt) as tot_ord_cnt,
sum(tot_ord_sum) as tot_ord_sum,
sum(tot_cart_cnt) as tot_cart_cnt,
sum(new_customer) as new_customer
from jd_digital_marketing.basic_jd_bosideng_martketing_data
where 1=1
and date_format(date,'%Y-%m-%d')>=date_sub(date_format((date_sub(date_format('${start_date}','%Y-%m-%d'), interval -6 day)),'%Y-%m-01') , interval 12 month)

and date_format(date,'%Y-%m-%d')<=date_format(date_sub(date_ADD(concat(date_format(DATE_ADD(date_ADD(date_format('2023-04-06','%Y-%m-%d'), interval 6 day), INTERVAL 1 MONTH),'%Y-%m'),'-01'),interval -1 day),interval 12 month),'%Y-%m-%d')
and date_format(date,'%Y-%m-%d')<=date_sub(date_sub(date_format('${start_date}','%Y-%m-%d'), interval -6 day),interval 12 month)
and date_format(date,'%Y-%m-%d')<date_sub(current_date(), interval 12 month)
group by
date_format(date,'%Y-%m-%d'),
case when shopname='BSD彭艾云' then '官旗'
when shopname='jd_nvbsd'   then '服饰'
when shopname='bvp06121666' then '自营'
when shopname='BSD-户外代理' then '户外'
when shopname='BSD-奥莱代理' then '奥莱'
when shopname='BSD-童装代理' then '童装' end ,
case when channel='kuaiche' then '京东快车'
when channel='touchpoint' then '购物触点'
when channel='jst' then  '京速推'
when channel='haitou'  then '京东海投'
when channel='zhitou'  then '京东直投'
end

)