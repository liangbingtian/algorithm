(SELECT
    SUM(all_cost) AS '花费',
        SUM(tot_ord_sum) AS 'GMV',
        sum(tot_ord_cnt) as '总订单行',
        SUM(impressions) AS '展现量',
        SUM(tot_cart_cnt) AS '加购数',
        SUM(clicks) AS '点击量',
        sum(dir_ord_sum) as '直接成交金额',
        ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(ifnull(max(d.cid3_name), max(skuCName3)), '其他三级类目') as '三级类目名称', ifnull(skuCid2, '其他二级类目id') as '二级类目id',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称',
        case
            when max(e.sku_name) is null then '无SKU名称'
            when max(e.sku_name) = '保丽玫瑰精油100ml' then '玫瑰精油100ml'
            when max(e.sku_name) = '质感随意发蜡85ml' then '随意发蜡'
            when max(e.sku_name) = 'BC保丽清爽洁净洗发露1L' then '清爽洁净'
            when max(e.sku_name) = '专属修护洗护套装' then '专属套装'
            else max(e.sku_name)
            end as 'sku名称',
        case
            when b.crowd_category_desc is null then '无人群分类'
            when b.crowd_category_desc like '%A0%' then 'A0'
            ELSE b.crowd_category_desc
            end as '人群分类',
        case
            when date_str >= DATE_FORMAT(CURRENT_DATE, '%Y%m01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y%m31') then '本月数据'
            when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31') then '去年本月数据'
            end AS '时间所属'
FROM dwd_jd_jzt_crowd_report a
         LEFT JOIN jd_crowd_category_layer b ON a.crowd_id = b.crowd_id
         left join dwd_jd_self_category_info c on a.username = c.username and a.sku_c3_id = c.skuCid3
         left join basic_jd_udf_adserving_shk_sku_category_set d on a.username = d.username and a.sku_id = d.sku_id
         left join basic_jd_udf_adserving_shk_sku_set e on a.sku_id = e.sku_id and a.username = e.username
WHERE
    a.username in ('施华蔻专业投放')
  AND trans_days=15
  AND caliber=1
  and gift_flag=0
  and order_status=1
  AND (date_str between DATE_FORMAT(CURRENT_DATE, '%Y%m01') and DATE_FORMAT(CURRENT_DATE, '%Y%m31')
    or date_str between DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31'))
  and business in ('购物触点','推荐广告（原购物触点）','推荐广告')
GROUP BY
     case
         when b.crowd_category_desc is null then '无人群分类'
         when b.crowd_category_desc like '%A0%' then 'A0'
         ELSE b.crowd_category_desc
         end,
    a.sku_id,
    ifnull(skuCid2, '其他二级类目id'),
    ifnull(skuCid3, '其他三级类目id'),
    case
        when date_str >= DATE_FORMAT(CURRENT_DATE, '%Y%m01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y%m31') then '本月数据'
        when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31') then '去年本月数据'
        end)
union all
(SELECT
     SUM(all_cost) AS '花费',
         SUM(tot_ord_sum) AS 'GMV',
         sum(tot_ord_cnt) as '总订单行',
         SUM(impressions) AS '展现量',
         SUM(tot_cart_cnt) AS '加购数',
         SUM(clicks) AS '点击量',
         sum(dir_ord_sum) as '直接成交金额',
         ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(ifnull(max(d.cid3_name), max(skuCName3)), '其他三级类目') as '三级类目名称', ifnull(skuCid2, '其他二级类目id') as '二级类目id',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称',
         case
             when max(e.sku_name) is null then '无SKU名称'
             when max(e.sku_name) = '保丽玫瑰精油100ml' then '玫瑰精油100ml'
             when max(e.sku_name) = '质感随意发蜡85ml' then '随意发蜡'
             when max(e.sku_name) = 'BC保丽清爽洁净洗发露1L' then '清爽洁净'
             when max(e.sku_name) = '专属修护洗护套装' then '专属套装'
             else max(e.sku_name)
             end as 'sku名称',
         case
             when b.crowd_category_desc is null then '无人群分类'
             when b.crowd_category_desc like '%A0%' then 'A0'
             ELSE b.crowd_category_desc
             end as '人群分类',
         case
             when date_str >= DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str <= DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
             when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') and
                  date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') then '上周数据'
             when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and
                  date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31') then '去年本周数据'
             when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') and
                  date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') then '去年上周数据'
             end AS '时间所属'
 FROM dwd_jd_jzt_crowd_report a
          LEFT JOIN jd_crowd_category_layer b ON a.crowd_id = b.crowd_id
          left join dwd_jd_self_category_info c on a.username = c.username and a.sku_c3_id = c.skuCid3
          left join basic_jd_udf_adserving_shk_sku_category_set d on a.username = d.username and a.sku_id = d.sku_id
          left join basic_jd_udf_adserving_shk_sku_set e on a.sku_id = e.sku_id and a.username = e.username
 WHERE
     a.username in ('施华蔻专业投放')
   AND trans_days=15
   AND caliber=1
   and gift_flag=0
   and order_status=1
   AND (date_str between DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d')
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31')
     or
        date_str between DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') and DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d')
     )
   and business in ('购物触点','推荐广告（原购物触点）','推荐广告')
 GROUP BY
     case
         when b.crowd_category_desc is null then '无人群分类'
         when b.crowd_category_desc like '%A0%' then 'A0'
         ELSE b.crowd_category_desc
         end,
     a.sku_id,
     ifnull(skuCid2, '其他二级类目id'),
     ifnull(skuCid3, '其他三级类目id'),
     case
         when date_str >= DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str <= DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') and
              date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') then '上周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and
              date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31') then '去年本周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') and
              date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') then '去年上周数据'
         end)