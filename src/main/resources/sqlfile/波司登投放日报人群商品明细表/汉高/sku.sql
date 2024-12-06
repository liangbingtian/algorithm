(SELECT  ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(ifnull(max(c.cid3_name), max(skuCName3)), '其他三级类目') as '三级类目名称', ifnull(skuCid2, '其他二级类目id') as '二级类目id',
         ifnull(max(d.sku_name), '无SKU名称') as 'sku名称',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称',
         SUM(all_cost) AS '花费', SUM(tot_ord_sum) AS 'GMV', sum( tot_cart_cnt ) AS '加购数', sum(tot_ord_cnt) as '总订单行', SUM(impressions) AS '展现量', SUM(clicks) AS '点击量', sum(dir_ord_sum) as '直接成交金额', case
            when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '全店推广'
            when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type not in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '商品推广'
            when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type ='京选店铺' then '京选店铺'
            when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type !='京选店铺' then '快车'
            when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
            when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
            else business
            end AS '业务类型', case
            when date_str >= DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y-%m-31') then '本月数据'
            when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-31') then '去年本月数据'
            end AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report a
          left join dwd_jd_self_category_info b on a.username = b.username and a.sku_c3_id = b.skuCid3
          left join basic_jd_udf_adserving_shk_sku_category_set c on a.username = c.username and a.sku_id = c.sku_id
          left join basic_jd_udf_adserving_shk_sku_set d on a.sku_id = d.sku_id and a.username = d.username
 WHERE a.username in ('施华蔻专业投放')
   AND trans_days = 15
   AND caliber = 1
   and gift_flag = 0
   and order_status = 1
   AND (date_str between DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') and DATE_FORMAT(CURRENT_DATE, '%Y-%m-31')
     or date_str between DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-01') and DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-31'))
 group by
          a.sku_id,
          ifnull(skuCid2, '其他二级类目id'),
          ifnull(skuCid3, '其他三级类目id'),
          case
              when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '全店推广'
              when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type not in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '商品推广'
              when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type ='京选店铺' then '京选店铺'
              when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type !='京选店铺' then '快车'
              when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
              when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
              else business
              end,
          case
              when date_str = DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y-%m-31') then '本月数据'
              when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y-%m-31') then '去年本月数据'
              end)
union all
(SELECT ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(ifnull(max(c.cid3_name), max(skuCName3)), '其他三级类目') as '三级类目名称', ifnull(skuCid2, '其他二级类目id') as '二级类目id',
         ifnull(max(d.sku_name), '无SKU名称') as 'sku名称',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称',
         SUM(all_cost) AS '花费', SUM(tot_ord_sum) AS 'GMV', sum( tot_cart_cnt ) AS '加购数', sum(tot_ord_cnt) as '总订单行', SUM(impressions) AS '展现量', SUM(clicks) AS '点击量', sum(dir_ord_sum) as '直接成交金额', case
            when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '全店推广'
            when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type not in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '商品推广'
            when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type ='京选店铺' then '京选店铺'
            when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type !='京选店铺' then '快车'
            when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
            when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
            else business
            end AS '业务类型', case
            when date_str >= '2024-11-18' and date_str <= '2024-11-24' then '本周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') and
                 date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') then '上周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-01') and
                 date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-31') then '去年本周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') and
                 date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年上周数据'
            end AS '时间所属'
 FROM dwd_jd_jzt_kc_sku_report a
          left join dwd_jd_self_category_info b on a.username = b.username and a.sku_c3_id = b.skuCid3
          left join basic_jd_udf_adserving_shk_sku_category_set c on a.username = c.username and a.sku_id = c.sku_id
          left join basic_jd_udf_adserving_shk_sku_set d on a.sku_id = d.sku_id and a.username = d.username
 WHERE a.username in ('施华蔻专业投放')
   AND trans_days = 15
   AND caliber = 1
   and gift_flag = 0
   and order_status = 1
   AND (date_str between '2024-11-18' and '2024-11-24'
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d')
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-01') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-31')
     or
        date_str between DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') and DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d')
     )
 group by
          a.sku_id,
          ifnull(skuCid2, '其他二级类目id'),
          ifnull(skuCid3, '其他三级类目id'),
          case
              when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '全店推广'
              when business in ('智能投放', '京速推', '智能投放（原京速推）') and campaign_type not in ('全店推广-全店推广', '经典海投计划', '全店海投-首焦海投') then '商品推广'
              when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type ='京选店铺' then '京选店铺'
              when business in ('快车', '京东快车（原京东快车）', '京东快车') and campaign_type !='京选店铺' then '快车'
              when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '触点'
              when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '直投'
              else business
              end,
          case
              when date_str >= '2024-11-18' and date_str <= '2024-11-24' then '本周数据'
              when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') and
                   date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') then '上周数据'
              when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-01') and
                   date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y-%m-31') then '去年本周数据'
              when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-18', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') and
                   date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('2024-11-24', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年上周数据'
              end)