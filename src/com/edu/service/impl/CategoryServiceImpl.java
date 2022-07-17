package com.edu.service.impl;

import java.util.List;

import com.edu.bean.Category;
import com.edu.dao.CategoryDao;
import com.edu.dao.impl.CategoryDaoImpl;
import com.edu.service.CategoryService;
import com.edu.util.JacksonUtil;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String getAll() throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		List<Category> categoryList = categoryDao.getAll();
		return JacksonUtil.convertObjectToString(categoryList);
	}

	@Override
	public List<Category> getBigCategory() throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.getBigCategory();
	}

	@Override
	public List<Category> getParentCategory() throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.getParentCategory();
	}

	@Override
	public void save(Category category) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
	
		categoryDao.save(category);
	}

	@Override
	public Category getById(Integer id) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.getById(id);
	}

	@Override
	public void update(Category category) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.update(category);
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.deleteById(id);
		
	}

}
