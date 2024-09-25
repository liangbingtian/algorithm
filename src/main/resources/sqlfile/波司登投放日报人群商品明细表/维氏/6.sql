SELECT
	psn.company,
	e.organization_name,
	psn_name,
	account,
	post_name,
	job_name,
	psn.dept_code,
	d.dept_name,
	psn.business_unit_code,
	psn.business_unit_name,
	we_chart,
	concat(account,"@ilarge.cn") as email,
  psn.`enablestate`,
  psn.`update_by`,
  psn.`update_date`,
  psn.`create_by`,
  psn.`create_date`
FROM
	`obc_product`.`psn_info` as psn
	left join obc_data_center.basic_organization_info as e on psn.company = e.organization_code and e.enable_state =1
	left join dept_info as d on psn.dept_code = d.dept_code and d.enablestate = 2
where psn.enablestate = 2