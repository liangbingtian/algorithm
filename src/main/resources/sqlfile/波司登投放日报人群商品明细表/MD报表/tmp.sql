SELECT
    SUM(all_cost) AS '花费',
        SUM(tot_ord_sum) AS 'GMV',
        sum(tot_ord_cnt) as '总订单行',
        SUM(impressions) AS '展现量',
        SUM(tot_cart_cnt) AS '加购数',
        SUM(clicks) AS '点击量',
        case
            when business in ('智能投放','京速推','智能投放（原京速推）')  then '智能投放'
            when business in ('快车', '京东快车（原京东快车）', '京东快车') then '搜索快车'
            when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '购物触点'
            when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '站外广告'
            else business
            end AS '业务类型'
FROM dwd_jd_jzt_kc_sku_report
         LEFT JOIN basic_jd_udf_adserving_md_sku_set ON dwd_jd_jzt_kc_sku_report.sku_id = basic_jd_udf_adserving_md_sku_set.sku_id
WHERE
    username in ('MD2024-投放','MDtoufang2024')
  AND trans_days=7
  AND caliber=1
  and gift_flag=0
  and order_status=1
  AND dwd_jd_jzt_kc_sku_report.date_str>='2024-08-19'
  AND dwd_jd_jzt_kc_sku_report.date_str<='2024-08-25'
GROUP BY
    case
        when business in ('智能投放','京速推','智能投放（原京速推）')  then '智能投放'
        when business in ('快车', '京东快车（原京东快车）', '京东快车') then '搜索快车'
        when business in ('购物触点', '推荐广告（原购物触点）', '推荐广告') then '购物触点'
        when business in ('站外广告', '站外广告（原京东直投）', '京东直投') then '站外广告'
        else business
        end