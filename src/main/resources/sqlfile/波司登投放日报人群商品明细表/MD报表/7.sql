(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         case
             when type is null then '无人群分类'
             when type ='A0人群' then 'A0'
             when type ='A2人群' then 'A2'
             when type ='A1人群' then 'A1'
             when type ='A34人群' then 'A34'
             when type ='京选人群' then '京选'
             ELSE type
             end as '人群分类',
         IFNULL(max(dwd_jd_jzt_crowd_report.crowd_name), dwd_jd_jzt_crowd_report.crowd_id) as '人群名称',
         case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
             when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
             when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
             when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
             when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
             when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
             when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
             end AS '店铺名称',
         '本周数据' AS '时间所属',
         '触点' as '业务类型'
 FROM dwd_jd_jzt_crowd_report
          LEFT JOIN basic_jd_udf_adserving_bsd_crowd_set ON trim(dwd_jd_jzt_crowd_report.crowd_name) = basic_jd_udf_adserving_bsd_crowd_set.crowd_name
     and dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_bsd_crowd_set.username
 WHERE
     dwd_jd_jzt_crowd_report.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND dwd_jd_jzt_crowd_report.date_str>=DATE_FORMAT(STR_TO_DATE('2024-09-01', '%Y-%m-%d'), '%Y%m%d')
   AND dwd_jd_jzt_crowd_report.date_str<=DATE_FORMAT(STR_TO_DATE('2024-10-31', '%Y-%m-%d'), '%Y%m%d')
   and business in ('购物触点','推荐广告（原购物触点）','推荐广告')
 GROUP BY
     case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
          when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
          when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
          when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
          when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
          when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
          when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
         end,
     case
         when type is null then '无人群分类'
         when type ='A0人群' then 'A0'
         when type ='A2人群' then 'A2'
         when type ='A1人群' then 'A1'
         when type ='A34人群' then 'A34'
         when type ='京选人群' then '京选'
         ELSE type
         end,dwd_jd_jzt_crowd_report.crowd_id)
union all
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         case
             when type is null then '无人群分类'
             when type ='A0人群' then 'A0'
             when type ='A2人群' then 'A2'
             when type ='A1人群' then 'A1'
             when type ='A34人群' then 'A34'
             when type ='京选人群' then '京选'
             ELSE type
             end as '人群分类',
         IFNULL(max(dwd_jd_jzt_crowd_report.crowd_name), dwd_jd_jzt_crowd_report.crowd_id) as '人群名称',
         case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
             when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
             when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
             when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
             when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
             when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
             when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
             end AS '店铺名称',
         '本周数据' AS '时间所属',
         '直投' as '业务类型'
 FROM dwd_jd_jzt_crowd_report
          LEFT JOIN basic_jd_udf_adserving_bsd_crowd_set ON trim(dwd_jd_jzt_crowd_report.crowd_name) = basic_jd_udf_adserving_bsd_crowd_set.crowd_name
     and dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_bsd_crowd_set.username
 WHERE
     dwd_jd_jzt_crowd_report.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND dwd_jd_jzt_crowd_report.date_str>=DATE_FORMAT(STR_TO_DATE('2024-09-01', '%Y-%m-%d'), '%Y%m%d')
   AND dwd_jd_jzt_crowd_report.date_str<=DATE_FORMAT(STR_TO_DATE('2024-10-31', '%Y-%m-%d'), '%Y%m%d')
   and business in ('站外广告')
 GROUP BY
     case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
          when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
          when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
          when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
          when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
          when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
          when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
         end,
     case
         when type is null then '无人群分类'
         when type ='A0人群' then 'A0'
         when type ='A2人群' then 'A2'
         when type ='A1人群' then 'A1'
         when type ='A34人群' then 'A34'
         when type ='京选人群' then '京选'
         ELSE type
         end,dwd_jd_jzt_crowd_report.crowd_id)
union all
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         case
             when type is null then '无人群分类'
             when type ='A0人群' then 'A0'
             when type ='A2人群' then 'A2'
             when type ='A1人群' then 'A1'
             when type ='A34人群' then 'A34'
             when type ='京选人群' then '京选'
             ELSE type
             end as '人群分类',
         IFNULL(max(dwd_jd_jzt_crowd_report.crowd_name), dwd_jd_jzt_crowd_report.crowd_id) as '人群名称',
         case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
             when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
             when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
             when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
             when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
             when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
             when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
             end AS '店铺名称',
         '上周数据' AS '时间所属',
         '触点' as '业务类型'
 FROM dwd_jd_jzt_crowd_report
          LEFT JOIN basic_jd_udf_adserving_bsd_crowd_set ON trim(dwd_jd_jzt_crowd_report.crowd_name) = basic_jd_udf_adserving_bsd_crowd_set.crowd_name
     and dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_bsd_crowd_set.username
 WHERE
     dwd_jd_jzt_crowd_report.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND dwd_jd_jzt_crowd_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-09-01', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d')
   AND dwd_jd_jzt_crowd_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-10-31', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d')
   and business in ('购物触点','推荐广告（原购物触点）','推荐广告')
 GROUP BY
     case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
          when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
          when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
          when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
          when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
          when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
          when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
         end,
     case
         when type is null then '无人群分类'
         when type ='A0人群' then 'A0'
         when type ='A2人群' then 'A2'
         when type ='A1人群' then 'A1'
         when type ='A34人群' then 'A34'
         when type ='京选人群' then '京选'
         ELSE type
         end,dwd_jd_jzt_crowd_report.crowd_id)
union all
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         case
             when type is null then '无人群分类'
             when type ='A0人群' then 'A0'
             when type ='A2人群' then 'A2'
             when type ='A1人群' then 'A1'
             when type ='A34人群' then 'A34'
             when type ='京选人群' then '京选'
             ELSE type
             end as '人群分类',
         IFNULL(max(dwd_jd_jzt_crowd_report.crowd_name), dwd_jd_jzt_crowd_report.crowd_id) as '人群名称',
         case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
             when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
             when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
             when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
             when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
             when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
             when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
             when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
             end AS '店铺名称',
         '上周数据' AS '时间所属',
         '直投' as '业务类型'
 FROM dwd_jd_jzt_crowd_report
          LEFT JOIN basic_jd_udf_adserving_bsd_crowd_set ON trim(dwd_jd_jzt_crowd_report.crowd_name) = basic_jd_udf_adserving_bsd_crowd_set.crowd_name
     and dwd_jd_jzt_crowd_report.username=basic_jd_udf_adserving_bsd_crowd_set.username
 WHERE
     dwd_jd_jzt_crowd_report.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND dwd_jd_jzt_crowd_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-09-01', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d')
   AND dwd_jd_jzt_crowd_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-10-31', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y%m%d')
   and business in ('站外广告')
 GROUP BY
     case when dwd_jd_jzt_crowd_report.username='BSD彭艾云' then '官旗'
          when dwd_jd_jzt_crowd_report.username='jd_nvbsd'   then '女店'
          when dwd_jd_jzt_crowd_report.username='bvp06121666' then '自营'
          when dwd_jd_jzt_crowd_report.username='BSD-户外代理' then '户外'
          when dwd_jd_jzt_crowd_report.username='BSD-奥莱代理' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD-童装代理' then '童装'
          when dwd_jd_jzt_crowd_report.username='BSD奥莱店铺推广' then '奥莱'
          when dwd_jd_jzt_crowd_report.username='BSD户外店铺推广' then '户外'
          when dwd_jd_jzt_crowd_report.username='波司登童装自营' then '童装'
         end,
     case
         when type is null then '无人群分类'
         when type ='A0人群' then 'A0'
         when type ='A2人群' then 'A2'
         when type ='A1人群' then 'A1'
         when type ='A34人群' then 'A34'
         when type ='京选人群' then '京选'
         ELSE type
         end,dwd_jd_jzt_crowd_report.crowd_id)