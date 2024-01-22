with datas as (

select
	shopname,
	`产品线` as productName,
	`日期` as date_str,
	`跟单SKU ID` as sku_id,
	b.sku_name as `商品名称`,
	if(`品牌`!='--',`品牌`,b.brand) as '品牌',
	cast(IFNULL(if(`所属类目` = '--',b.category,`所属类目`),'--') as char) as '所属类目',
	`展现数` as impressions,
	`点击数` as clicks,
	`总费用` as cost,
	`总订单行` as totalOrderCnt,
	`总订单金额` as totalOrderSum,
	`总购物车数` as totalCartCnt,
	`下单新客数` as newCustomersCnt
from (SELECT
	username as shopname,
	date_str AS "日期",
	CASE WHEN campaign_name LIKE '%京选店铺%' THEN '京选店铺'
	WHEN business in ('京东快车','京东快车（原京东快车）') THEN '快车'
	WHEN business in ('购物触点','推荐广告（原购物触点）') THEN '推荐广告'
	WHEN business in ('京东展位','互动广告（原京东展位）') THEN '互动广告'
	ELSE  business end AS "产品线",
	campaign_type AS "计划类型",
	campaign_id AS "计划ID",
	campaign_name AS "推广计划",
	group_id AS "单元ID",
	group_name AS "推广单元",
	ad_id AS "创意ID",
	ad_name AS "推广创意",
	a.sku_id AS "跟单SKU ID",
	'' AS "商品名称",
	sku_brand_name AS "品牌",
	sku_c3_name AS "所属类目",
	impressions AS "展现数",
	clicks AS "点击数",
  clicks/impressions  as "点击率(%)",
	all_cost AS "总费用",
	impressions/all_cost*1000 as "千次展现成本",
	clicks/all_cost as "平均点击成本",
	dir_ord_cnt AS "直接订单行",
	dir_ord_sum AS "直接订单金额",
	ind_ord_cnt AS "间接订单行",
	ind_ord_sum AS "间接订单金额",
	tot_ord_cnt AS "总订单行",
	tot_ord_sum AS "总订单金额",
	dir_cart_cnt AS "直接购物车数",
	ind_cart_cnt AS "间接购物车数",
	tot_cart_cnt AS "总购物车数",
	tot_ord_cnt/clicks as "转化率(%)",
	tot_ord_cnt/all_cost as "CPA",
	tot_ord_sum/all_cost as "ROI",
	new_customer AS "下单新客数"
FROM
	jd_digital_marketing.dwd_jd_jzt_kc_sku_report as a
WHERE
	date_str>=DATE_SUB(DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','（',2),'~',1),'.','-')),'%Y-%m-%d'),INTERVAL 8 day)
	and date_str<=DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','~',2),'）',1),'.','-')),'%Y-%m-%d')
	AND username in ('FTSJDZY','菲婷丝-惠润','vivess716')
	AND trans_days = '15' -- 0/15
	AND caliber = '1' -- 口径 点击/下单: 0/1: null/1"
	AND gift_flag = '0'
	AND if('${下单or成交}' = '0',order_status = 0,order_status = 1) -- 下单null/成交1
	AND is_daily = '1'
	-- 		and effect=1
	AND business IN (  '京东快车','快车','京东快车（原京东快车）','购物触点','推荐广告（原购物触点）','推荐广告','京东展位','互动广告（原京东展位）','互动广告')
union all
select
	shopname,
	`date_str` as '日期',
	case request_productName
	when 'kuaiche' then '快车'
	when 'zhitou' then '站外广告'
	when 'touchPoint' then '推荐广告'
	when 'zhanwei' then '互动广告'
	else `request_productName`
	end as '产品线',
	campaigntype as '计划类型',
	`campaignid` as '计划ID',
	`campaignname` as '推广计划',
	`adGroupid` as '单元ID',
	`adgroupname` as '推广单元',
	`adid` as '创意ID',
	`adname` as '推广创意',
	'--' as '跟单SKU ID',
	'--' as '商品名称',
	'--' as '品牌',
	'--' as '所属类目',
	`impressions` as '展现数',
	`clicks` as '点击数',
	clicks/impressions  as '点击率(%)',
	`cost` as '总费用',
	impressions/cost*1000 as '千次展现成本',
	clicks/cost as '平均点击成本',
	`directOrderCnt` as '直接订单行',
	`directOrderSum` as '直接订单金额',
	`indirectOrderCnt` as '间接订单行',
	`indirectOrderSum` as '间接订单金额',
	`totalOrderCnt` as '总订单行',
	`totalOrderSum` as '总订单金额',
	`directCartCnt` as '直接购物车数',
	`indirectCartCnt` as '间接购物车数',
	`totalCartCnt` as '总购物车数',
	totalOrderCnt/clicks as '转化率(%)',
	totalOrderCnt/cost as 'CPA',
	totalOrderSum/cost as 'ROI',
	`newCustomersCnt` as '下单新客数'
from jd_digital_marketing.ods_jd_api_sjzx_ad_query
where
	date_str>=DATE_SUB(DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','（',2),'~',1),'.','-')),'%Y-%m-%d'),INTERVAL 8 day)
	and date_str<=DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','~',2),'）',1),'.','-')),'%Y-%m-%d')
	and `shopname` in ('FTSJDZY','菲婷丝-惠润','vivess716')
	and `request_giftFlag` = '0'
	and `request_clickOrOrderDay` = '15'
	and if('${下单or成交}' = '0',request_orderStatusCategory = 0,request_orderStatusCategory = 1)
	and `request_clickOrOrderCaliber` = '1'
	and `request_impressionOrClickEffect` = '1'
	and `request_productName` in ('zhitou')
union all
select
	shopname,
	`date_str` as '日期',
	if(campaignname IN ('首焦海投计划','经典海投计划','PUSH海投计划', '海投新品计划','测款计划','频道活动计划','预售推广计划','直降促销计划','全店推广计划' ),'京东海投','智能投放') AS "产品线",
	campaigntype as '计划类型',
	`campaignid` as '计划ID',
	`campaignname` as '推广计划',
	'--' as '单元ID',
	'--' as '推广单元',
	'--' as '创意ID',
	'--' as '推广创意',
	a.skuid AS "跟单SKU ID",
	'--' AS "商品名称",
	'--' AS "品牌",
	'--' AS "所属类目",
	`impressions` as '展现数',
	`clicks` as '点击数',
	clicks/impressions  as '点击率(%)',
	`cost` as '总费用',
	impressions/cost*1000 as '千次展现成本',
	clicks/cost as '平均点击成本',
	`directOrderCnt` as '直接订单行',
	`directOrderSum` as '直接订单金额',
	`indirectOrderCnt` as '间接订单行',
	`indirectOrderSum` as '间接订单金额',
	`totalOrderCnt` as '总订单行',
	`totalOrderSum` as '总订单金额',
	`directCartCnt` as '直接购物车数',
	`indirectCartCnt` as '间接购物车数',
	`totalCartCnt` as '总购物车数',
	totalOrderCnt/clicks as '转化率(%)',
	totalOrderCnt/cost as 'CPA',
	totalOrderSum/cost as 'ROI',
	`newCustomersCnt` as '下单新客数'
from jd_digital_marketing.ods_jd_api_sjzx_sku_query as a
where
	date_str>=DATE_SUB(DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','（',2),'~',1),'.','-')),'%Y-%m-%d'),INTERVAL 8 day)
	and date_str<=DATE_FORMAT(concat(SUBSTR('${自然周}' , 1 , 4),'-',REPLACE(split_part(split_part('${自然周}','~',2),'）',1),'.','-')),'%Y-%m-%d')
	and `shopname` in ('FTSJDZY','菲婷丝-惠润','vivess716')
	and `request_giftFlag` = '0'
	and `request_clickOrOrderDay` = '15'
	and if('${下单or成交}' = '0',request_orderStatusCategory = 0,request_orderStatusCategory = 1)
	and `request_clickOrOrderCaliber` = '1'
	) as a
	left join jd_digital_marketing.basic_jd_ft_sku_contrast as b on cast(`跟单SKU ID` as char) = b.sku_id
),
basic_data as
(
		select
			T1.*,
			T2.weekofmonth
		from datas as T1
		left join
		(
			select
				dateid,
				weekofmonth,
				weekrange
			from jd_digital_marketing.basic_date
				where yearid>='2021' and yearid<=year(CURRENT_DATE)
		) as T2 on T1.date_str=T2.dateid
)


SELECT
		shopname,
		weekofmonth,
		productName,
		`所属类目` as '所属类目',
		'类目汇总' as descs,
		sum(impressions) as IMP,
		sum(clicks) as CLK,
		sum(cost) as cost,
		sum(totalOrderCnt) as order_cnt,
		sum(totalOrderSum) as GMV,
		sum(totalCartCnt) as carts,
		sum(newCustomersCnt) as new_buyers,
		sum(clicks)/sum(impressions) as CTR,
		sum(cost)/sum(clicks) as CPC,
		sum(totalCartCnt)/sum(clicks) as 加购率,
		sum(cost)/sum(totalCartCnt) as 加购成本,
		sum(totalOrderCnt)/sum(clicks) as CVR,
		sum(totalOrderSum)/sum(totalOrderCnt) as 客单价,
		sum(totalOrderSum)/sum(cost) as ROI,
		sum(cost)/(sum(sum(cost))over(partition by t1.weekofmonth)) as cost_p,
	sum(t1.totalOrderSum)/(sum(sum(t1.totalOrderSum))over(partition by t1.weekofmonth)) as GMV_p
from
(
	select
		shopname,
		weekofmonth,
		productName,
		`所属类目` as '所属类目',
		sum(impressions) as impressions,
		sum(clicks) as clicks,
		sum(cost) as cost,
		sum(totalOrderCnt) as totalOrderCnt,
		sum(totalOrderSum) as totalOrderSum,
		sum(totalCartCnt) as totalCartCnt,
		sum(newCustomersCnt) as newCustomersCnt
	from basic_data
	group by
		shopname,
		weekofmonth,
		productName,
		`所属类目`
) as t1
group by shopname,
		weekofmonth,
		productName,
		`所属类目`
union all
	SELECT
		shopname,
		weekofmonth,
		productName,
		`所属类目` as '所属类目',
		'类目汇总汇总' as descs,
		sum(impressions) as IMP,
		sum(clicks) as CLK,
		sum(cost) as cost,
		sum(totalOrderCnt) as order_cnt,
		sum(totalOrderSum) as GMV,
		sum(totalCartCnt) as carts,
		sum(newCustomersCnt) as new_buyers,
		sum(clicks)/sum(impressions) as CTR,
		sum(cost)/sum(clicks) as CPC,
		sum(totalCartCnt)/sum(clicks) as 加购率,
		sum(cost)/sum(totalCartCnt) as 加购成本,
		sum(totalOrderCnt)/sum(clicks) as CVR,
		sum(totalOrderSum)/sum(totalOrderCnt) as 客单价,
		sum(totalOrderSum)/sum(cost) as ROI,
		sum(cost)/(sum(sum(cost))over(partition by t1.weekofmonth)) as cost_p,
	sum(t1.totalOrderSum)/(sum(sum(t1.totalOrderSum))over(partition by t1.weekofmonth)) as GMV_p
from
(
	select
			shopname,
			weekofmonth,
			'汇总' as productName,
			`所属类目` as '所属类目',
			sum(impressions) as impressions,
			sum(clicks) as clicks,
			sum(cost) as cost,
			sum(totalOrderCnt) as totalOrderCnt,
			sum(totalOrderSum) as totalOrderSum,
			sum(totalCartCnt) as totalCartCnt,
			sum(newCustomersCnt) as newCustomersCnt
		from basic_data
		group by
			shopname,
			weekofmonth,
			`所属类目`
) as t1
group by shopname,
		weekofmonth,
		`所属类目`,
		productName
union all
SELECT
		shopname,
		weekofmonth,
		productName,
		'1111111111111111' as '所属类目',
		'渠道汇总' as descs,
		sum(impressions) as IMP,
		sum(clicks) as CLK,
		sum(cost) as cost,
		sum(totalOrderCnt) as order_cnt,
		sum(totalOrderSum) as GMV,
		sum(totalCartCnt) as carts,
		sum(newCustomersCnt) as new_buyers,
		sum(clicks)/sum(impressions) as CTR,
		sum(cost)/sum(clicks) as CPC,
		sum(totalCartCnt)/sum(clicks) as 加购率,
		sum(cost)/sum(totalCartCnt) as 加购成本,
		sum(totalOrderCnt)/sum(clicks) as CVR,
		sum(totalOrderSum)/sum(totalOrderCnt) as 客单价,
		sum(totalOrderSum)/sum(cost) as ROI,
		sum(cost)/(sum(sum(cost))over(partition by t1.weekofmonth)) as cost_p,
	sum(t1.totalOrderSum)/(sum(sum(t1.totalOrderSum))over(partition by t1.weekofmonth)) as GMV_p
from
(
	select
		shopname,
		weekofmonth,
		productName,
		sum(impressions) as impressions,
		sum(clicks) as clicks,
		sum(cost) as cost,
		sum(totalOrderCnt) as totalOrderCnt,
		sum(totalOrderSum) as totalOrderSum,
		sum(totalCartCnt) as totalCartCnt,
		sum(newCustomersCnt) as newCustomersCnt
	from basic_data
	group by
		shopname,
		weekofmonth,
		productName
) as t1
group by shopname,
		weekofmonth,
		productName

union all
	SELECT
		shopname,
		weekofmonth,
		productName,
		'1111111111111111' as '所属类目',
		'汇总' as descs,
		sum(impressions) as IMP,
		sum(clicks) as CLK,
		sum(cost) as cost,
		sum(totalOrderCnt) as order_cnt,
		sum(totalOrderSum) as GMV,
		sum(totalCartCnt) as carts,
		sum(newCustomersCnt) as new_buyers,
		sum(clicks)/sum(impressions) as CTR,
		sum(cost)/sum(clicks) as CPC,
		sum(totalCartCnt)/sum(clicks) as 加购率,
		sum(cost)/sum(totalCartCnt) as 加购成本,
		sum(totalOrderCnt)/sum(clicks) as CVR,
		sum(totalOrderSum)/sum(totalOrderCnt) as 客单价,
		sum(totalOrderSum)/sum(cost) as ROI,
		sum(cost)/(sum(sum(cost))over(partition by t1.weekofmonth)) as cost_p,
	sum(t1.totalOrderSum)/(sum(sum(t1.totalOrderSum))over(partition by t1.weekofmonth)) as GMV_p
from
(
	select
			shopname,
			weekofmonth,
			'汇总' as productName,
			sum(impressions) as impressions,
			sum(clicks) as clicks,
			sum(cost) as cost,
			sum(totalOrderCnt) as totalOrderCnt,
			sum(totalOrderSum) as totalOrderSum,
			sum(totalCartCnt) as totalCartCnt,
			sum(newCustomersCnt) as newCustomersCnt
		from basic_data
		group by
			shopname,
			weekofmonth
) as t1
group by shopname,
		weekofmonth,
		productName