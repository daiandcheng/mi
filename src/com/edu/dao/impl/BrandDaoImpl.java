package com.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.edu.bean.Brand;
import com.edu.dao.BrandDao;
import com.edu.util.DataSourceUtil;

public class BrandDaoImpl implements BrandDao {

	@Override
	public List<Brand> getAll() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource());
		String sql = "select * from tbl_brand" ;
		return query.query(sql, new BeanListHandler<Brand>(Brand.class));
	}

}
