package com.edu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.edu.bean.Pie;
import com.edu.dao.PieDao;
import com.edu.util.DataSourceUtil;

public class PieDaoImpl implements PieDao {

	@Override
	public List<Pie> getPieList() {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "select s.name name,count(*) value from tbl_category t,(select id,name from tbl_category where pid is null) s where t.pid = s.id GROUP BY s.name" ;
		try {
			return query.query(sql, new BeanListHandler<Pie>(Pie.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
