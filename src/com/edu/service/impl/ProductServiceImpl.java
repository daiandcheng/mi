package com.edu.service.impl;

import java.util.List;

import com.edu.bean.PageBean;
import com.edu.bean.Product;
import com.edu.dao.ProductDao;
import com.edu.dao.impl.ProductDaoImpl;
import com.edu.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean getPageBean(int pageNo, int pagesize) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		List<Product> productList = productDao.getPageList(pageNo,pagesize);
		int rowCount = productDao.getRowCount();
		return new PageBean(pageNo, pagesize, productList, rowCount);
	}

	@Override
	public void save(Product product) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		productDao.save(product);
	}

	@Override
	public List<Product> getProduct(int num) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.getProduct(num);
	}

	@Override
	public Product getProductById(String id) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.getProductById(id);
	}

}
