package com.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.edu.bean.Category;
import com.edu.dao.CategoryDao;
import com.edu.util.DataSourceUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getAll() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 查询所有的父分类
		List<Category> categoryList = query.query("select * from tbl_category where pid is null", 
				new BeanListHandler<Category>(Category.class));
		for(Category category:categoryList) {
			List<Category> childrenCategory = query.query("select * from tbl_category where pid =?", 
					new BeanListHandler<Category>(Category.class),category.getId());
			category.setChildrens(childrenCategory);
		}
		
		return categoryList;
	}

	@Override
	public List<Category> getBigCategory() throws Exception {
		// TODO Auto-generated method stub
		return getAll();
	}

	@Override
	public List<Category> getParentCategory() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 查询所有的父分类
		List<Category> categoryList = query.query("select * from tbl_category where pid is null", 
				new BeanListHandler<Category>(Category.class));
		return categoryList ;
	}

	@Override
	public void save(Category category) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 判断到底是增加的父分类还是子分类
		if(category.getPid() != null) {
			// 子分类
			String sql = "insert into tbl_category(name,pid) values(?,?)";
			query.update(sql, category.getName(),category.getPid()) ;
		} else {
			String sql = "insert into tbl_category(name) values(?)";
			query.update(sql, category.getName()) ;
		}
	}

	@Override
	public Category getById(Integer id) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return query.query("select * from tbl_category where id=?", 
				new BeanHandler<Category>(Category.class),id);
	}

	@Override
	public void update(Category category) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 判断你更新的是父分类，还是子分类
		if(category.getPid() !=null) {
			// 更新的是子分类
			String sql = "update tbl_category set name=?,pid=? where id=?";
			query.update(sql, category.getName(),category.getPid(),category.getId()) ;
		} else {
			String sql = "update tbl_category set name=? where id=?";
			query.update(sql, category.getName(),category.getId()) ;
		}
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		// 先判断它是大类，还是小类
		Category category = getById(id);
		if(category.getPid() != null) {
			//这是小类
			query.update("delete from tbl_category where id=?", id) ;
		} else {
			// 大类，先删除小类，再删除大类
			List<Category> categoryList = query.query("select * from tbl_category where pid=?",
					new BeanListHandler<Category>(Category.class),id);
			for(Category c:categoryList) {
				query.update("delete from tbl_category where id=?", c.getId()) ;
			}
			query.update("delete from tbl_category where id=?",id) ;
		}
	}

}
