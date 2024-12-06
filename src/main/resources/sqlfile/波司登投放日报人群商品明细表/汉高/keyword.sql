(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         case
             when type is null then '无词类'
             ELSE type
             end as '词类',
         '海投' AS '业务类型',
         '本周数据' AS '时间所属'
 FROM dwd_jd_jzt_keyword_report
          LEFT JOIN basic_jd_udf_adserving_bsd_keyword_set ON trim(dwd_jd_jzt_keyword_report.keyword) = basic_jd_udf_adserving_bsd_keyword_set.keyword
     and dwd_jd_jzt_keyword_report.username=basic_jd_udf_adserving_bsd_keyword_set.username
 WHERE
     dwd_jd_jzt_keyword_report.username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('智能投放')
   AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
   AND dwd_jd_jzt_keyword_report.date_str>='${weekStartDay}'
   AND dwd_jd_jzt_keyword_report.date_str<='${endDay}'
 GROUP BY
     case
         when type is null then '无词类'
         ELSE type end)
union ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '快车' AS '业务类型',
         '本周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('快车', '京东快车（原京东快车）', '京东快车')
   AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
   AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}'
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '触点' AS '业务类型',
         '本周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
   AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
   AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}'
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '直投' AS '业务类型',
         '本周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('站外广告', '站外广告（原京东直投）', '京东直投')
   AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
   AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}'
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '京速推' AS '业务类型',
         '本周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
   AND campaign_name not in ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
   AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
   AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}'
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
union all
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '海投' AS '业务类型',
         '上周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('智能投放')
   AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
   AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
   AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
union ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '快车' AS '业务类型',
         '上周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('快车', '京东快车（原京东快车）', '京东快车')
   AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
   AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '触点' AS '业务类型',
         '上周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
   AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
   AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '直投' AS '业务类型',
         '上周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('站外广告', '站外广告（原京东直投）', '京东直投')
   AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
   AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)
UNION ALL
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         if(sum( tot_cart_cnt )=0, 0, sum( all_cost )/sum( tot_cart_cnt )) AS '加购成本',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         if(SUM(clicks)=0, 0, sum( all_cost )/SUM(clicks)) AS CPC,
     if(SUM(all_cost)=0, 0, sum(tot_ord_sum)/SUM(all_cost)) AS ROI,
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade
         end as '分级',
         '京速推' AS '业务类型',
         '上周数据' AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report
          LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
 WHERE
     username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
   AND trans_days=15
   AND caliber=0
   and gift_flag=0
   and order_status=1
   AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
   AND campaign_name not in ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
   AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
   AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
 GROUP BY
     case when grade = '1S' then 'S'
          when grade is null then '无分级货品'
          ELSE grade end)