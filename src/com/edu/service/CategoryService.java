package com.edu.service;

import java.util.List;

import com.edu.bean.Category;

public interface CategoryService {

	String getAll() throws Exception;

	List<Category> getBigCategory() throws Exception;

	List<Category> getParentCategory() throws Exception;

	void save(Category category) throws Exception;

	Category getById(Integer id)  throws Exception;

	void update(Category category)  throws Exception;

	void deleteById(Integer id) throws Exception;


}
