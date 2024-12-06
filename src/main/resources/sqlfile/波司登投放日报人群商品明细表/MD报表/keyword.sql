SELECT SUM(all_cost) AS '花费', SUM(tot_ord_sum) AS 'GMV', sum(tot_ord_cnt) as '总订单行', SUM(impressions) AS '展现量', SUM(tot_cart_cnt) AS '加购数', SUM(clicks) AS '点击量',
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
            end as '词类',
        dwd_jd_jzt_keyword_report.keyword AS '关键词'
FROM dwd_jd_jzt_keyword_report
         left join  jd_keyword_type kt on trim(dwd_jd_jzt_keyword_report.keyword) = kt.keyword and kt.tf_public=1
         LEFT JOIN jd_keyword_type kt1 ON trim(dwd_jd_jzt_keyword_report.keyword) = kt1.keyword and kt1.brand = 'JD_MD' and kt1.tf_public=0
WHERE dwd_jd_jzt_keyword_report.username in ('MD2024-投放', 'MDtoufang2024')
  AND trans_days = 7
  AND caliber = 1
  and gift_flag = 0
  and order_status = 1
  AND dwd_jd_jzt_keyword_report.date_str >= DATE_FORMAT(STR_TO_DATE('${开始时间}', '%Y-%m-%d'), '%Y%m%d')
  AND dwd_jd_jzt_keyword_report.date_str <= DATE_FORMAT(STR_TO_DATE('${结束时间}', '%Y-%m-%d'), '%Y%m%d')
GROUP BY
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
    dwd_jd_jzt_keyword_report.keyword