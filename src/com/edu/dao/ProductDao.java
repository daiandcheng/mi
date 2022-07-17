package com.edu.dao;

import java.util.List;

import com.edu.bean.Product;

public interface ProductDao {

	List<Product> getPageList(int pageNo, int pagesize) throws Exception;

	int getRowCount() throws Exception;

	void save(Product product) throws Exception;

	List<Product> getProduct(int num) throws Exception;

	Product getProductById(String id) throws Exception;

}
