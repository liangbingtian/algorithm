(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''海投'' AS ''业务类型'',
''本期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''智能投放'')
AND campaign_name IN (''全店推广计划'', ''首焦海投计划'', ''经典海投计划'', ''PUSH海投计划'', ''海投新品计划'', ''测款计划'', ''频道活动计划'', ''预售推广计划'', ''直降促销计划'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=''${weekEndDay}''
GROUP BY grade)
union ALL
(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''快车'' AS ''业务类型'',
''本期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''快车'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=''${weekEndDay}''
GROUP BY grade)
UNION ALL
(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''触点'' AS ''业务类型'',
''本期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''购物触点'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=''${weekEndDay}''
GROUP BY grade)
union all
(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''海投'' AS ''业务类型'',
''上期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''智能投放'')
AND campaign_name IN (''全店推广计划'', ''首焦海投计划'', ''经典海投计划'', ''PUSH海投计划'', ''海投新品计划'', ''测款计划'', ''频道活动计划'', ''预售推广计划'', ''直降促销计划'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 2 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), ''%Y-%m-%d'')
GROUP BY grade)
union ALL
(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''快车'' AS ''业务类型'',
''上期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''快车'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 2 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), ''%Y-%m-%d'')
GROUP BY grade)
UNION ALL
(SELECT
CONVERT(SUM(all_cost), decimal(15,2)) AS ''花费'',
CONVERT(SUM(tot_ord_sum), decimal(15,2)) AS ''GMV'',
sum(tot_ord_cnt) as ''总订单行'',
SUM(impressions) AS ''展现量'',
SUM(clicks) AS ''点击量'',
SUM(tot_cart_cnt) AS ''加购数'',
IFNULL(grade,''无分类货品'') AS ''货品分类'',
''触点'' AS ''业务类型'',
''上期数据'' AS ''时间所属''
FROM dwd_jd_jzt_kc_sku_report
LEFT JOIN basic_jd_udf_adserving_bsd_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_bsd_sku_set.sku_id
WHERE
username in ( ''jd_nvbsd'',''BSD彭艾云'',''bvp06121666'',''BSD-户外代理'',''BSD-奥莱代理'',''BSD-童装代理'',''BSD奥莱店铺推广'',''BSD户外店铺推广'',''波司登童装自营'')
AND trans_days=15
AND caliber=0
and gift_flag=0
and order_status=1
AND business IN (''购物触点'')
AND dwd_jd_jzt_kc_sku_report.date_str>=DATE_FORMAT(DATE_ADD(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 2 WEEK), INTERVAL 1 DAY), ''%Y-%m-%d'')
AND dwd_jd_jzt_kc_sku_report.date_str<=DATE_FORMAT(DATE_SUB(STR_TO_DATE(''${weekEndDay}'', ''%Y-%m-%d''), INTERVAL 1 WEEK), ''%Y-%m-%d'')
GROUP BY grade)