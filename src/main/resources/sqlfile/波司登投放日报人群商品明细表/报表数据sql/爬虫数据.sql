CREATE VIEW `dwd_jd_industry_brand_data` (
                                          `username`,
                                          `date_str`,
                                          `brand_type`,
                                          `business`,
                                          `cid3`,
                                          `trans_days`,
                                          `caliber`,
                                          `order_status`,
                                          `giftFlag`,
                                          `brand_id`,
                                          `cost`,
                                          `impression`,
                                          `click`,
                                          `old_customer_cost`,
                                          `new_customer_cost`,
                                          `new_customer_cnt`,
                                          `old_customer_cnt`,
                                          `total_cart_cnt`,
                                          `total_order_cnt`,
                                          `total_deal_order_cnt`,
                                          `total_order_sum`,
                                          `cpc`,
                                          `roi`
    ) AS ((
    SELECT
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`username`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`date_str`,
        '竞争品牌' AS `brand_type`,
        CASE
            `jd_digital_marketing`.`jd_industry_compete_brand_data`.`businessType`
            WHEN '1' THEN
                '互动广告'
            WHEN '2' THEN
                '快车'
            WHEN '16777216' THEN
                '推荐广告'
            WHEN '134217728' THEN
                '智能投放'
            WHEN '256' THEN
                '站外广告' ELSE `jd_digital_marketing`.`jd_industry_compete_brand_data`.`businessType`
            END AS `business`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`cid3`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`clickOrOrderDay` AS `trans_days`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`clickOrOrderCaliber` AS `caliber`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`orderStatusCategory` AS `order_status`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`giftFlag`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`brand_id`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`costExponent` AS `cost`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`impressionExponent` AS `impression`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`clickExponent` AS `click`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`oldCustomerCost` AS `old_customer_cost`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`newCustomerCost` AS `new_customer_cost`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`newCustomerExponent` AS `new_customer_cnt`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`oldCustomerExponent` AS `old_customer_cnt`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`totalCartCntExponent` AS `total_cart_cnt`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`totalOrderCntExponent` AS `total_order_cnt`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`totalDealOrderCntExponent` AS `total_deal_order_cnt`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`roi` * `jd_digital_marketing`.`jd_industry_compete_brand_data`.`costExponent` AS `total_order_sum`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`cpc`,
        `jd_digital_marketing`.`jd_industry_compete_brand_data`.`roi`
    FROM
        `jd_digital_marketing`.`jd_industry_compete_brand_data`
) UNION ALL
         (
             SELECT
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`username`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`date_str`,
                 '自身品牌' AS `brand_type`,
                 CASE
                     `jd_digital_marketing`.`jd_industry_self_brand_data`.`businessType`
                     WHEN '1' THEN
                         '互动广告'
                     WHEN '2' THEN
                         '快车'
                     WHEN '16777216' THEN
                         '推荐广告'
                     WHEN '134217728' THEN
                         '智能投放'
                     WHEN '256' THEN
                         '站外广告' ELSE `jd_digital_marketing`.`jd_industry_self_brand_data`.`businessType`
                     END AS `business`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`cid3`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`clickOrOrderDay` AS `trans_days`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`clickOrOrderCaliber` AS `caliber`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`orderStatusCategory` AS `order_status`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`giftFlag`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`brand_id`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`costExponent` AS `cost`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`impressionExponent` AS `impression`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`clickExponent` AS `click`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`oldCustomerCost` AS `old_customer_cost`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`newCustomerCost` AS `new_customer_cost`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`newCustomerExponent` AS `new_customer_cnt`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`oldCustomerExponent` AS `old_customer_cnt`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`totalCartCntExponent` AS `total_cart_cnt`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`totalOrderCntExponent` AS `total_order_cnt`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`totalDealOrderCntExponent` AS `total_deal_order_cnt`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`roi` * `jd_digital_marketing`.`jd_industry_self_brand_data`.`costExponent` AS `total_order_sum`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`cpc`,
                 `jd_digital_marketing`.`jd_industry_self_brand_data`.`roi`
             FROM
                 `jd_digital_marketing`.`jd_industry_self_brand_data`
         ));