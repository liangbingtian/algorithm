(SELECT  date_str as '日期',a.keyword as '关键词', ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(max(skuCName3), '其他三级类目') as '三级类目名称'
, ifnull(skuCid2, '其他二级类目id') as '二级类目id',ifnull(max(c.sku_name), '无SKU名称') as 'sku名称',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称'
, SUM(all_cost) AS '花费', SUM(tot_ord_sum) AS 'GMV', sum( tot_cart_cnt ) AS '加购数', sum(tot_ord_cnt) as '总订单行', SUM(impressions) AS '展现量', SUM(clicks) AS '点击量', sum(dir_ord_sum) as '直接成交金额', case
            when date_str >= DATE_FORMAT(CURRENT_DATE, '%Y%m01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y%m31') then '本月数据'
            when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31') then '去年本月数据'
            end AS '时间所属',
         case
             when ifnull(kt1.keyword_type, kt.keyword_type) is null then '无词类'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 1 then '类目词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 2 then '品牌词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 4 then '功效词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 8 then '集团词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 16 then '竞品词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 32 then '礼物词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 64 then '明星词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 128 then '通用词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 256 then '智能词'
             ELSE ifnull(kt1.keyword_type, kt.keyword_type)
             end as '词类'
 FROM dwd_jd_jzt_keyword_report a
          left join dwd_jd_self_category_info b on a.username = b.username and a.sku_c3_id = b.skuCid3
          left join  jd_keyword_type kt on trim(a.keyword) = kt.keyword and kt.tf_public=1
          LEFT JOIN jd_keyword_type kt1 ON trim(a.keyword) = kt1.keyword and kt1.brand = 'JD_施华蔻' and kt1.tf_public=0
          left join basic_jd_udf_adserving_shk_sku_set c on a.sku_id = c.sku_id and c.username='施华蔻专业投放'
 WHERE a.username in ('施华蔻专业投放')
   AND trans_days = 15
   AND caliber = 1
   and gift_flag = 0
   and order_status = 1
   AND (date_str between DATE_FORMAT(CURRENT_DATE, '%Y%m01') and DATE_FORMAT(CURRENT_DATE, '%Y%m31')
     or date_str between DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31'))
 group by date_str,
          a.sku_id,
          a.keyword,
          ifnull(skuCid2, '其他二级类目id'),
          ifnull(skuCid3, '其他三级类目id'),
          case
              when ifnull(kt1.keyword_type, kt.keyword_type) is null then '无词类'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 1 then '类目词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 2 then '品牌词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 4 then '功效词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 8 then '集团词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 16 then '竞品词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 32 then '礼物词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 64 then '明星词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 128 then '通用词'
              when ifnull(kt1.keyword_type, kt.keyword_type) = 256 then '智能词'
              ELSE ifnull(kt1.keyword_type, kt.keyword_type)
              end,
          case
              when date_str = DATE_FORMAT(CURRENT_DATE, '%Y%m01') and date_str <= DATE_FORMAT(CURRENT_DATE, '%Y%m31') then '本月数据'
              when date_str >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m01') and date_str <= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR), '%Y%m31') then '去年本月数据'
              end)
union all
(SELECT  date_str as '日期', a.keyword as '关键词', ifnull(skuCid3, '其他三级类目id') as '三级类目id', ifnull(max(skuCName3), '其他三级类目') as '三级类目名称', ifnull(skuCid2, '其他二级类目id') as '二级类目id',ifnull(max(c.sku_name), '无SKU名称') as 'sku名称',
         case
             when ifnull(max(skuCname2), '其他二级类目') = '洗发护发' then '洗护'
             when ifnull(max(skuCname2), '其他二级类目') = '美发造型' then '造型'
             else ifnull(max(skuCname2), '其他二级类目')
             end as '二级类目名称'
         , SUM(all_cost) AS '花费', SUM(tot_ord_sum) AS 'GMV', sum( tot_cart_cnt ) AS '加购数', sum(tot_ord_cnt) as '总订单行', SUM(impressions) AS '展现量', SUM(clicks) AS '点击量', sum(dir_ord_sum) as '直接成交金额',  case
            when date_str >= DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str <= DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') and
                 date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') then '上周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and
                 date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31') then '去年本周数据'
            when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') and
                 date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') then '去年上周数据'
            end AS '时间所属',
         case
             when ifnull(kt1.keyword_type, kt.keyword_type) is null then '无词类'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 1 then '类目词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 2 then '品牌词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 4 then '功效词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 8 then '集团词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 16 then '竞品词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 32 then '礼物词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 64 then '明星词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 128 then '通用词'
             when ifnull(kt1.keyword_type, kt.keyword_type) = 256 then '智能词'
             ELSE ifnull(kt1.keyword_type, kt.keyword_type)
             end as '词类'
 FROM dwd_jd_jzt_keyword_report a
          left join dwd_jd_self_category_info b on a.username = b.username and a.sku_c3_id = b.skuCid3
          left join  jd_keyword_type kt on trim(a.keyword) = kt.keyword and kt.tf_public=1
          LEFT JOIN jd_keyword_type kt1 ON trim(a.keyword) = kt1.keyword and kt1.brand = 'JD_施华蔻' and kt1.tf_public=0
          left join basic_jd_udf_adserving_shk_sku_set c on a.sku_id = c.sku_id and c.username='施华蔻专业投放'
 WHERE a.username in ('施华蔻专业投放')
   AND trans_days = 15
   AND caliber = 1
   and gift_flag = 0
   and order_status = 1
   AND (date_str between DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d')
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y%m%d')
     or
        date_str between DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31')
     or
        date_str between DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d') and DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y%m%d')
     )
 group by
     date_str,
     a.keyword,
     a.sku_id,
     ifnull(skuCid2, '其他二级类目id'),
     ifnull(skuCid3, '其他三级类目id'),
     case
         when ifnull(kt1.keyword_type, kt.keyword_type) is null then '无词类'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 1 then '类目词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 2 then '品牌词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 4 then '功效词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 8 then '集团词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 16 then '竞品词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 32 then '礼物词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 64 then '明星词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 128 then '通用词'
         when ifnull(kt1.keyword_type, kt.keyword_type) = 256 then '智能词'
         ELSE ifnull(kt1.keyword_type, kt.keyword_type)
         end,
     case
         when date_str >= DATE_FORMAT(STR_TO_DATE('${startDay}', '%Y-%m-%d'), '%Y%m%d') and date_str <= DATE_FORMAT(STR_TO_DATE('${endDay}', '%Y-%m-%d'), '%Y%m%d') then '本周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') and
              date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), '%Y-%m-%d') then '上周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m01') and
              date_str <= DATE_FORMAT(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 1 YEAR), '%Y%m31') then '去年本周数据'
         when date_str >= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${startDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') and
              date_str <= DATE_FORMAT(DATE_SUB(DATE_SUB(STR_TO_DATE('${endDay}', '%Y-%m-%d'), INTERVAL 7 DAY), INTERVAL 1 YEAR), '%Y-%m-%d') then '去年上周数据'
         end)