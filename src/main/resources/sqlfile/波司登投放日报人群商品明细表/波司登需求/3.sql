(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'海投' AS '业务类型',
'本周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('智能投放')
AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')
union ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'快车' AS '业务类型',
'本周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('快车', '京东快车（原京东快车）', '京东快车')
AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')
UNION ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'触点' AS '业务类型',
'本周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
AND dwd_jd_jzt_kc_sku_report.date_str>='${weekStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')
union all
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'海投' AS '业务类型',
'上周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('智能投放')
AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d'))
union ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'快车' AS '业务类型',
'上周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('快车', '京东快车（原京东快车）', '京东快车')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d'))
UNION ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'触点' AS '业务类型',
'上周数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${weekStartDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 1 WEEK), '%Y-%m-%d'))
union all
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'海投' AS '业务类型',
'本期月数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('智能投放')
AND campaign_name IN ('全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划')
AND dwd_jd_jzt_kc_sku_report.date_str>='${monthStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')
union ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'快车' AS '业务类型',
'本期月数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('快车', '京东快车（原京东快车）', '京东快车')
AND dwd_jd_jzt_kc_sku_report.date_str>='${monthStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')
UNION ALL
(SELECT
SUM(all_cost) AS '花费',
SUM(tot_ord_sum) AS 'GMV',
sum( tot_cart_cnt ) AS '加购数',
sum(tot_ord_cnt) as '总订单行',
SUM(impressions) AS '展现量',
SUM(clicks) AS '点击量',
'触点' AS '业务类型',
'本期月数据' AS '时间所属'
FROM dwd_jd_jzt_kc_sku_report
WHERE
username in ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN ('购物触点', '推荐广告（原购物触点）', '推荐广告')
AND dwd_jd_jzt_kc_sku_report.date_str>='${monthStartDay}'
AND dwd_jd_jzt_kc_sku_report.date_str<='${endDay}')