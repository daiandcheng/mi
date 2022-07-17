package com.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.edu.bean.Advice;
import com.edu.dao.AdviceDao;
import com.edu.util.DataSourceUtil;

public class AdviceDaoImpl implements AdviceDao {

	@Override
	public List<Advice> getLatestData() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql ="select * from tbl_advice order by pub desc limit 7" ;
		return query.query(sql, new BeanListHandler<Advice>(Advice.class));
	}

}
