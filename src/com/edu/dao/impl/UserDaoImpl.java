package com.edu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.edu.bean.PageBean;
import com.edu.bean.User;
import com.edu.contant.Constant;
import com.edu.dao.UserDao;
import com.edu.util.DataSourceUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User user) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "insert into tbl_user(id,name,pwd,phone,email,birthday,sex,code,active) values(?,?,?,?,?,?,?,?,?)" ;
		query.update(sql, user.getId(),user.getName(),user.getPwd(),user.getPhone(),user.getEmail(),user.getBirthday(),user.getSex(),user.getCode(),user.getActive());
		
	}

	@Override
	public void update(String code) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "update tbl_user set active=?,code=? where code=?" ;
		query.update(sql, Constant.ACTIVE,null,code) ;
	}

	@Override
	public User findUserByName(String name) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql ="select * from tbl_user where name=?";
		return query.query(sql, new BeanHandler<User>(User.class), name);
	}

	@Override
	public PageBean getPageBean(int pageNo, int pagesize) throws Exception {
		
		return new PageBean(pageNo, pagesize, getList(pageNo,pagesize), getRowCount());
	}
	/**
	 * 获取所有的数据
	 * @return
	 * @throws SQLException
	 */
	public List<User> getList(int pageNo, int pagesize) throws SQLException {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return query.query("select * from tbl_user limit ?,?", new BeanListHandler<User>(User.class),pagesize *(pageNo-1),pagesize);
	}
	/**
	 * 获取所有的数据
	 * @return
	 * @throws SQLException
	 */
	public Integer getRowCount() throws SQLException {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return ((Long) query.query("select count(*) from tbl_user", new ScalarHandler())).intValue();
	}
//	public static void main(String[] args) throws Exception{
//		int count = new UserDaoImpl().getRowCount() ;
//		System.out.println(count);
//	}

	@Override
	public User getUserById(String id) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return query.query("select * from tbl_user where id=?", new BeanHandler<User>(User.class), id);
	}

	@Override
	public void update(User user) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		query.update("update tbl_user set name=?,pwd=?,sex=?,birthday=?,phone=? where id=?", 
				user.getName(),user.getPwd(),user.getSex(),user.getBirthday(),user.getPhone(),user.getId());
	}

	@Override
	public void deleteById(String id) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		query.update("delete from tbl_user where id=?",id);
		
	}

}
