CREATE VIEW `dm_jd_api_campaign_report_v2` (
	`id`,
	`pid`,
	`shopname`,
	`productName`,
	`date_str`,
	`clickOrOrderDay`,
	`orderStatusCategory`,
	`clickOrOrderCaliber`,
	`campaignid`,
	`campaignname`,
	`impressions`,
	`clicks`,
	`cost`,
	`directOrderCnt`,
	`indirectOrderCnt`,
	`totalOrderCnt`,
	`directOrderSum`,
	`indirectOrderSum`,
	`totalOrderSum`,
	`directCartCnt`,
	`indirectCartCnt`,
	`totalCartCnt`,
	`newCustomersCnt`,
	`totalPresaleOrderCnt`,
	`totalPresaleOrderSum`,
	`createtime`,
	`source`,
	`batchstatus`,
	`sku_id`,
	`visitorCnt`,
	`update_time`
) AS SELECT
1 AS `id`,
`t`.`pid`,
`t`.`shopname`,
`t`.`request_productName` AS `productName`,
`t`.`date_str`,
`t`.`request_clickOrOrderDay` AS `clickOrOrderDay`,
`t`.`request_orderStatusCategory` AS `orderStatusCategory`,
`t`.`request_clickOrOrderCaliber` AS `clickOrOrderCaliber`,
`t`.`campaignid`,
`t`.`campaignname`,
`t`.`impressions`,
`t`.`clicks`,
`t`.`cost`,
`t`.`directOrderCnt`,
`t`.`indirectOrderCnt`,
`t`.`totalOrderCnt`,
`t`.`directOrderSum`,
`t`.`indirectOrderSum`,
`t`.`totalOrderSum`,
`t`.`directCartCnt`,
`t`.`indirectCartCnt`,
`t`.`totalCartCnt`,
`t`.`newCustomersCnt`,
`t`.`totalPresaleOrderCnt`,
`t`.`totalPresaleOrderSum`,
`t`.`createtime`,
`t`.`source`,
`t`.`batchstatus`,
`t`.`sku_id`,
`t`.`visitorCnt`,
now() AS `update_time`
FROM
	(
	SELECT
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`pid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`shopname`,
	CASE

			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'kuaiche' ) THEN
			'京东快车'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'touchPoint' ) THEN
			'购物触点'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'zhitou' ) THEN
			'京东直投'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'zhanwei' ) THEN
			'京东展位'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'jst' ) THEN
			'京速推'
		END AS `request_productName`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`date_str`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderDay`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_orderStatusCategory`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderCaliber`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`impressions`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`clicks`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`cost`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`newCustomersCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`createtime`,
		'1' AS `source`,
		1 AS `batchstatus`,
		NULL AS `sku_id`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`visitorCnt`
	FROM
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`
	WHERE
		((((
						`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_giftFlag` = 0
						)
					AND ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`batchstatus` = 1 ))
				AND (
				NOT ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` LIKE '%京选店铺%' )))
			AND (
			`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` NOT IN ( '全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划' )))
		AND (
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` IN ( 'kuaiche', 'touchPoint', 'zhitou', 'jst' )) UNION ALL
	SELECT
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`pid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`shopname`,
		'京选店铺' AS `京选店铺`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`date_str`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderDay`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_orderStatusCategory`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderCaliber`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`impressions`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`clicks`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`cost`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`newCustomersCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`createtime`,
		'3' AS `source`,
		1 AS `batchstatus`,
		NULL AS `sku_id`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`visitorCnt`
	FROM
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`
	WHERE
		((
				`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_giftFlag` = 0
				)
		AND ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`batchstatus` = 1 ))
		AND ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` LIKE '%京选店铺%' ) UNION ALL
	SELECT
		concat( `A`.`pid`, `B`.`clickOrOrderDay`, `B`.`orderStatusCategory`, `B`.`clickOrOrderCaliber` ) AS `pid`,
		`A`.`shopname`,
		'京挑客' AS `京挑客`,
		`A`.`finishTime`,
		`B`.`clickOrOrderDay`,
		`B`.`orderStatusCategory`,
		`B`.`clickOrOrderCaliber`,
		`A`.`planId`,
		`A`.`planName`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL1`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL2`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL3`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		`A`.`createtime`,
		'4' AS `source`,
		1 AS `batchstatus`,
		NULL AS `NULL`,
		NULL AS `NULL`
	FROM
		(
		SELECT
			concat(
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`shopname`,
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`finishTime`,
			ifnull( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`planName`, '' )) AS `pid`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`finishTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`planName`,
			sum(( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`cosFee` + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`platformServiceFee` ) + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`cpFee` ) AS `COL1`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`skuCount` ) AS `COL2`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`cosPrice` ) AS `COL3`,
			max( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`createtime` ) AS `createtime`,
			'4' AS `source`,
			1 AS `batchstatus`
		FROM
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`
		WHERE
			( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`batchstatus` = 1 )
			AND (
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`shopname` NOT IN ( 'FTSJDZY', 'vivess716' ))
		GROUP BY
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`finishTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders`.`planName`
		) `A`
		INNER JOIN (
		SELECT
			0 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			0 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber`
		) `B` ON 1 = 1 UNION ALL
	SELECT
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`pid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`shopname`,
	CASE

			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'kuaiche' ) THEN
			'京东快车'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'touchPoint' ) THEN
			'购物触点'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'zhitou' ) THEN
			'京东直投'
			WHEN ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` = 'zhanwei' ) THEN
			'京东展位'
		END AS `request_productName`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`date_str`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderDay`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_orderStatusCategory`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderCaliber`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`impressions`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`clicks`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`cost`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`newCustomersCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`createtime`,
		'6' AS `source`,
		1 AS `batchstatus`,
		NULL AS `sku_id`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`visitorCnt`
	FROM
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`
	WHERE
		(((
					`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_giftFlag` = 0
					)
				AND ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`batchstatus` = 1 ))
			AND (
			NOT ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` LIKE '%京选店铺%' )))
		AND (
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` IN ( 'zhanwei' )) UNION ALL
	SELECT
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`pid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`shopname`,
		'京东海投' AS `request_productName`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`date_str`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderDay`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_orderStatusCategory`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_clickOrOrderCaliber`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignid`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`impressions`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`clicks`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`cost`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`directCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`indirectCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalCartCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`newCustomersCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderCnt`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`totalPresaleOrderSum`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`createtime`,
		'7' AS `source`,
		1 AS `batchstatus`,
		NULL AS `sku_id`,
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`visitorCnt`
	FROM
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`
	WHERE
		((((
						`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_giftFlag` = 0
						)
					AND ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`batchstatus` = 1 ))
				AND (
				NOT ( `jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` LIKE '%京选店铺%' )))
			AND (
			`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`campaignname` IN ( '全店推广计划', '首焦海投计划', '经典海投计划', 'PUSH海投计划', '海投新品计划', '测款计划', '频道活动计划', '预售推广计划', '直降促销计划' )))
		AND (
		`jd_digital_marketing`.`ods_jd_api_sjzx_campaign_query`.`request_productName` IN ( 'haitou', 'jst' )) UNION ALL
	SELECT
		concat( `A`.`pid`, `B`.`clickOrOrderDay`, `B`.`orderStatusCategory`, `B`.`clickOrOrderCaliber` ) AS `pid`,
		`A`.`shopname`,
		'京挑客' AS `京挑客`,
		`A`.`orderTime`,
		`B`.`clickOrOrderDay`,
		`B`.`orderStatusCategory`,
		`B`.`clickOrOrderCaliber`,
		`A`.`planId`,
		`A`.`planName`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL1`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL2`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL3`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		`A`.`createtime`,
		'8' AS `source`,
		1 AS `batchstatus`,
		NULL AS `NULL`,
		NULL AS `NULL`
	FROM
		(
		SELECT
			concat(
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			ifnull( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`, '' )) AS `pid`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`,
			sum(( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cosFee` + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`platformServiceFee` ) + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cpFee` ) AS `COL1`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`skuCount` ) AS `COL2`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cosPrice` ) AS `COL3`,
			max( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`createtime` ) AS `createtime`,
			'8' AS `source`,
			1 AS `batchstatus`
		FROM
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`
		WHERE
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`batchstatus` = 1
		GROUP BY
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`
		) `A`
		INNER JOIN (
		SELECT
			0 AS `clickOrOrderDay`,
			0 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			0 AS `clickOrOrderDay`,
			0 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			0 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			0 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber`
		) `B` ON 1 = 1 UNION ALL
	SELECT
		concat( `A`.`pid`, `B`.`clickOrOrderDay`, `B`.`orderStatusCategory`, `B`.`clickOrOrderCaliber` ) AS `pid`,
		`A`.`shopname`,
		'京挑客' AS `京挑客`,
		`A`.`orderTime`,
		`B`.`clickOrOrderDay`,
		`B`.`orderStatusCategory`,
		`B`.`clickOrOrderCaliber`,
		`A`.`planId`,
		`A`.`planName`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL1`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL2`,
		0 AS `0`,
		0 AS `0`,
		`A`.`COL3`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		0 AS `0`,
		`A`.`createtime`,
		'9' AS `source`,
		1 AS `batchstatus`,
		NULL AS `NULL`,
		NULL AS `NULL`
	FROM
		(
		SELECT
			concat(
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
				`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			ifnull( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`, '' )) AS `pid`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`,
			sum(( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cosFee` + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`platformServiceFee` ) + `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cpFee` ) AS `COL1`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`skuCount` ) AS `COL2`,
			sum( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`cosPrice` ) AS `COL3`,
			max( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`createtime` ) AS `createtime`,
			'9' AS `source`,
			1 AS `batchstatus`
		FROM
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`
		WHERE
			( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`batchstatus` = 1 )
			AND ( `jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`request_orderState` = 1 )
		GROUP BY
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`shopname`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`orderTime`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planId`,
			`jd_digital_marketing`.`ods_jd_api_jtk_detail_orders_time_flag`.`planName`
		) `A`
		INNER JOIN (
		SELECT
			0 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			0 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			0 AS `clickOrOrderCaliber` UNION ALL
		SELECT
			15 AS `clickOrOrderDay`,
			1 AS `orderStatusCategory`,
			1 AS `clickOrOrderCaliber`
		) `B` ON 1 = 1
	) `t`;