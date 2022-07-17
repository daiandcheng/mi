package com.edu.service;

import java.util.List;

import com.edu.bean.PageBean;
import com.edu.bean.Product;

public interface ProductService {

	PageBean getPageBean(int pageNo, int pagesize) throws Exception;

	void save(Product product) throws Exception;

	List<Product> getProduct(int num) throws Exception;

	Product getProductById(String id)  throws Exception;

}
