SELECT
    date_str AS '日期',
        SUM( cost ) AS '花费',
        SUM( total_order_sum ) AS 'GMV',
        sum( total_cart_cnt ) AS '加购数',
        sum( total_order_cnt ) AS '总订单行',
        SUM( impression ) AS '展现量',
        SUM( click ) AS '点击量',
        business AS '业务类型',
        CASE

            WHEN date_str >= DATE_FORMAT( CURRENT_DATE, '%Y-%m-01' )
                AND date_str <= DATE_FORMAT( CURRENT_DATE, '%Y-%m-31' ) THEN '本期数据' WHEN date_str >= DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-01' )
            AND date_str <= DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-31' ) THEN
            '去年同期数据'
            END AS '时间所属'
FROM
    dwd_jd_center_industry_whole
WHERE
    cid3 IN ( 16757,16760,16765,16767,16756,16768,16758, 16766)
  AND trans_days = 15
  AND caliber = 1
  AND gift_flag = 0
  AND order_status = 1
  AND (
    date_str BETWEEN DATE_FORMAT( CURRENT_DATE, '%Y-%m-01' )
        AND DATE_FORMAT( CURRENT_DATE, '%Y-%m-31' )
        OR date_str BETWEEN DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-01' )
        AND DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-31' ))
GROUP BY
    date_str,
    business,
    CASE

        WHEN date_str = DATE_FORMAT( CURRENT_DATE, '%Y-%m-01' )
            AND date_str <= DATE_FORMAT( CURRENT_DATE, '%Y-%m-31' ) THEN '本期数据' WHEN date_str >= DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-01' )
        AND date_str <= DATE_FORMAT( DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ), '%Y-%m-31' ) THEN
            '去年同期数据'
        END
order by date_str ASC