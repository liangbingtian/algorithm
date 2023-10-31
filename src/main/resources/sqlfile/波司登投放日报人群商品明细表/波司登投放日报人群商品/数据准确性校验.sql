SELECT
IF
	( sum( a.a ) >0, '昨日数据已拉取完成，可以使用', '昨日数据尚未拉取完成，请耐心等待' ) AS a
FROM
	(
SELECT
	count( DISTINCT username ) AS a

FROM `jd_digital_marketing`.`dwd_jd_jzt_crowd_report` dat

WHERE dat.username IN ( 'jd_nvbsd','BSD彭艾云','bvp06121666','BSD-户外代理','BSD-奥莱代理','BSD-童装代理','BSD奥莱店铺推广','BSD户外店铺推广','波司登童装自营')
and dat.date_str=DATE_FORMAT( DATE_SUB( NOW( ), INTERVAL 1 DAY ), '%Y%m%d' )
and dat.trans_days=15
and dat.caliber=0
and dat.gift_flag=0
and dat.order_status=1

	) AS a

