SELECT
   SUM(all_cost) AS '花费',
   SUM(tot_ord_sum) AS 'GMV',
   sum(tot_ord_cnt) as '总订单行',
   SUM(impressions) AS '展现量',
   SUM(tot_cart_cnt) AS '加购数',
   SUM(clicks) AS '点击量',
   case
    when type is null then '无人群分类'
    ELSE type
    end as '人群分类',
    IFNULL(max(dwd_jd_jzt_crowd_report.crowd_name), dwd_jd_jzt_crowd_report.crowd_id) as '人群名称'
 FROM dwd_jd_jzt_crowd_report
    LEFT JOIN basic_jd_udf_adserving_md_crowd_set ON trim(dwd_jd_jzt_crowd_report.crowd_name) = basic_jd_udf_adserving_md_crowd_set.crowd_name
    and dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_md_crowd_set.username
 WHERE
     dwd_jd_jzt_crowd_report.username in ('MD2024-投放','MDtoufang2024')
   AND trans_days=7
   AND caliber=1
   and gift_flag=0
   and order_status=1
   AND dwd_jd_jzt_crowd_report.date_str>=DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d')
   AND dwd_jd_jzt_crowd_report.date_str<=DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
   and business in ('购物触点','推荐广告（原购物触点）','推荐广告')
 GROUP BY
     case
         when type is null then '无人群分类'
         ELSE type
         end,dwd_jd_jzt_crowd_report.crowd_id