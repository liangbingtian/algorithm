(select cost as '花费',
impression as '展现',
click as '点击',
total_cart_cnt as '总加购数',
total_order_cnt as '总订单数',
total_order_sum as 'GMV',
cname2 as '二级类目',
business as '渠道',
'本周数据' as '时间所属'
from dwd_jd_center_industry_whole
left join basic_category_info on dwd_jd_center_industry_whole.cid3 = basic_category_info.cid3
where
trans_days = 15 and caliber = 1 and order_status=1
and date_str>='${startDay}'
and date_str<='${endDay}')
union all
(select cost as '花费',
impression as '展现',
click as '点击',
total_cart_cnt as '总加购数',
total_order_cnt as '总订单数',
total_order_sum as 'GMV',
cname2 as '二级类目',
business as '渠道',
'上周数据' as '时间所属'
from dwd_jd_center_industry_whole
left join basic_category_info on dwd_jd_center_industry_whole.cid3 = basic_category_info.cid3
where
trans_days = 15 and caliber = 1 and order_status=1
and date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
and date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d'))



