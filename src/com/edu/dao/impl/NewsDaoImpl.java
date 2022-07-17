package com.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.edu.bean.News;
import com.edu.dao.NewsDao;
import com.edu.util.DataSourceUtil;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> getLatestData() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return query.query("select * from tbl_news order by pub desc limit 7", new BeanListHandler<News>(News.class));
	}

}
